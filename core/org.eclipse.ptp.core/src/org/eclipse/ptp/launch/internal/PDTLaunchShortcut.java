package org.eclipse.ptp.launch.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ptp.ParallelPlugin;
import org.eclipse.ptp.core.IPDTLaunchConfigurationConstants;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

/**
 * @author clement
 *
 */
public class PDTLaunchShortcut implements ILaunchShortcut {
    public static final String LauncherGroupID = "org.eclipse.debug.ui.launchGroup.run";
    
	/**
	 * @see ILaunchShortcut#launch(IEditorPart, String)
	 */
	public void launch(IEditorPart editor, String mode) {
		IEditorInput input = editor.getEditorInput();
		IProject element = (IProject) input.getAdapter(IProject.class);
		launch(element, mode);
	}
	
	/**
	 * @see ILaunchShortcut#launch(ISelection, String)
	 */
	public void launch(ISelection selection, String mode) {
		if (selection instanceof IStructuredSelection) {
		    launch(((IStructuredSelection)selection).getFirstElement(), mode);
		} 		
	}
	
	public void launch(Object element, String mode) {
	    if (!(element instanceof IFile)) {
	        MessageDialog.openInformation(ParallelPlugin.getActiveWorkbenchShell(), "Incorrect file", "Please select parallel program file");
	        return;
	    }
	        
	    IFile file = (IFile)element;
	        
	    ILaunchConfiguration config = getILaunchConfigure(file);
	    IStructuredSelection selection = null;
	    if (config == null)
	        selection  = new StructuredSelection();
	    else 
	        selection = new StructuredSelection(config);
	        
	    ILaunchGroup group = DebugUITools.getLaunchGroup(config, mode);
		DebugUITools.openLaunchConfigurationDialogOnGroup(DebugUIPlugin.getShell(), selection, group.getIdentifier());
	}
	
	public ILaunchConfiguration getILaunchConfigure(IFile file) {
	    String projectName = file.getProject().getName();
	    ILaunchManager lm = getLaunchManager();
	    ILaunchConfigurationType configType = lm.getLaunchConfigurationType(IPDTLaunchConfigurationConstants.PDT_LAUNCHCONFIGURETYPE_ID);
		try {
		    ILaunchConfiguration[] configs = lm.getLaunchConfigurations(configType);
		    for (int i=0; i<configs.length; i++) {
		        if (configs[i].getAttribute(IPDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, "").equals(projectName))
		            return configs[i];
		    }
		    ILaunchConfigurationWorkingCopy wc = configType.newInstance(file.getProject(), projectName);		    
		    wc.setAttribute(IPDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, projectName);
		    wc.setAttribute(IPDTLaunchConfigurationConstants.ATTR_APPLICATION_NAME, file.getName());
	        wc.setAttribute(IPDTLaunchConfigurationConstants.NUMBER_OF_PROCESSES, IPDTLaunchConfigurationConstants.DEF_NUMBER_OF_PROCESSES);
	        wc.setAttribute(IPDTLaunchConfigurationConstants.NETWORK_TYPE, IPDTLaunchConfigurationConstants.DEF_NETWORK_TYPE);
	        wc.setAttribute(IPDTLaunchConfigurationConstants.PROCESSES_PER_NODE, IPDTLaunchConfigurationConstants.DEF_PROCESSES_PER_NODE);
	        wc.setAttribute(IPDTLaunchConfigurationConstants.FIRST_NODE_NUMBER, IPDTLaunchConfigurationConstants.DEF_FIRST_NODE_NUMBER);

		    return wc.doSave();
		} catch (CoreException e) {
		}
		return null;
	}
	
	
	private ILaunchManager getLaunchManager() {
	    return DebugPlugin.getDefault().getLaunchManager();
	}
}

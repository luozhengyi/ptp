package org.eclipse.ptp.launch.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.ptp.ParallelPlugin;
import org.eclipse.ptp.core.IPDTLaunchConfigurationConstants;
import org.eclipse.ptp.launch.core.ILaunchManager;
import org.eclipse.ptp.ui.UIMessage;

/**
 * @author Clement
 *
 */
public abstract class AbstractParallelLaunchConfigurationDelegate extends LaunchConfigurationDelegate {
    public static final String HYPHEN = "-";
    public static final String NUM_PROC = HYPHEN + "p";
    public static final String PROC_PER_NODE = HYPHEN + "N";
    public static final String START_NODE = HYPHEN + "o";
    public static final String PROG_NAME = HYPHEN + HYPHEN;

    protected ILaunchManager getLaunchManager() {
        return ParallelPlugin.getDefault().getLaunchManager();
    }
    
    protected IWorkspaceRoot getWorkspaceRoot() {
    	return ResourcesPlugin.getWorkspace().getRoot();
    }    
    protected IProject getProject(String proName) {
        return getWorkspaceRoot().getProject(proName);
    }
    
    protected IProject verifyProject(ILaunchConfiguration configuration) throws CoreException {
        String proName = getProjectName(configuration);
        if (proName == null)
   			abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Project_not_specified"), null, IStatus.INFO);

        IProject project = getProject(proName);
        if (project == null || !project.exists() || !project.isOpen())
			abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Project_does_not_exist_or_is_not_a_project"), null, IStatus.INFO);
        
        return project;
    }

	protected IFile getProgramFile(ILaunchConfiguration configuration) throws CoreException {
		IProject project = verifyProject(configuration);
		String fileName = getProgramName(configuration);
		if (fileName == null)
			abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Application_file_not_specified"), null, IStatus.INFO);

		IFile programPath = project.getFile(fileName);
		if (programPath == null || !programPath.exists() || !programPath.getLocation().toFile().exists())
			abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Application_file_does_not_exist"), new FileNotFoundException(UIMessage.getFormattedResourceString("AbstractParallelLaunchConfigurationDelegate.Application_path_not_found", programPath.getLocation().toString())), IStatus.INFO);

		return programPath;
	}
	
	protected IPath verifyProgramFile(ILaunchConfiguration configuration) throws CoreException {
		return getProgramFile(configuration).getLocation();
	}	

	protected String[] getProgramParameters(ILaunchConfiguration configuration) throws CoreException {
		List arguments = new ArrayList();
		String temp = getNumberOfProcess(configuration);
		if (temp == null) 
		    abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Number_of_process_not_specified"), null, IStatus.INFO);
		
		arguments.add(NUM_PROC);		
		arguments.add(temp);
		arguments.add(HYPHEN + getCommunication(configuration));
		temp = getNumberOfProcessStart(configuration);
		if (temp != null  && !temp.equals("0")) {
			arguments.add(PROC_PER_NODE);		
		    arguments.add(temp);
		}
		temp = getFirstNodeNumber(configuration);
		if (temp != null  && !temp.equals("0")) {
			arguments.add(START_NODE);		
		    arguments.add(temp);
		}
		
		return (String[]) arguments.toArray(new String[arguments.size()]);
	}
	
	protected String[] vertifyArgument(ILaunchConfiguration configuration) throws CoreException {
		IFile programFile = getProgramFile(configuration);
		String[] arguments = getProgramParameters(configuration);
		List cmdLine = new ArrayList(arguments.length);
		cmdLine.addAll(Arrays.asList(arguments));
		cmdLine.add(PROG_NAME);
		
		cmdLine.add(programFile.getLocation().toString());
		
		String temp = getArgument(configuration);
		if (temp != null && temp.length() > 0) 
		    cmdLine.add(temp);
		
		return (String[]) cmdLine.toArray(new String[cmdLine.size()]);
	}
	 
    protected File vertifyWorkDirectory(ILaunchConfiguration configuration) throws CoreException {
        String workPath = getWorkDirectory(configuration);
        if (workPath == null) {
			IProject project = verifyProject(configuration);
			if (project != null)
				return project.getLocation().toFile();
		} else {
			IPath path = new Path(workPath);
			if (path.isAbsolute()) {
				File dir = new File(path.toOSString());
				if (dir.isDirectory())
					return dir;

				abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Working_directory_does_not_exist"), new FileNotFoundException(UIMessage.getFormattedResourceString("AbstractParallelLaunchConfigurationDelegate.Application_path_not_found", path.toOSString())), IStatus.INFO);
			} else {
				IResource res = getWorkspaceRoot().findMember(path);
				if (res instanceof IContainer && res.exists())
					return res.getLocation().toFile();

				abort(UIMessage.getResourceString("AbstractParallelLaunchConfigurationDelegate.Working_directory_does_not_exist"), new FileNotFoundException(UIMessage.getFormattedResourceString("AbstractParallelLaunchConfigurationDelegate.Application_path_not_found", path.toOSString())), IStatus.INFO);
			}
		}
		return null;        
    }
    	
	protected void abort(String message, Throwable exception, int code) throws CoreException {
	    throw new CoreException(new Status(IStatus.ERROR, ParallelPlugin.getUniqueIdentifier(), code, message, exception));
	}
	
	protected static String getProjectName(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, (String)null);
	}
	protected static String getProgramName(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.ATTR_APPLICATION_NAME, (String)null);
	}
	protected static String getNumberOfProcess(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.NUMBER_OF_PROCESSES, (String)null);
	}
	protected static String getCommunication(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.NETWORK_TYPE, (String)null);
	}
	protected static String getNumberOfProcessStart(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.PROCESSES_PER_NODE, (String)null);
	}
	protected static String getFirstNodeNumber(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.FIRST_NODE_NUMBER, (String)null);
	}
	protected static String getArgument(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.ATTR_ARGUMENT, (String)null);
	}
	protected static String getWorkDirectory(ILaunchConfiguration configuration) throws CoreException {
	    return configuration.getAttribute(IPDTLaunchConfigurationConstants.ATTR_WORK_DIRECTORY, (String)null);
	}
}

package org.eclipse.ptp.internal.ui.search;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ptp.core.IPElement;
import org.eclipse.ptp.core.IPProcess;
import org.eclipse.swt.graphics.Image;

/**
 * @author Clement
 *
 */
public class PSearchResultLabelProvider extends LabelProvider {
	public static final int SHOW_NUMBER = 0;
	public static final int SHOW_PID = 1;
	public static final int SHOW_EXITCODE = 2;

	private int sortOrder;
	
	public PSearchResultLabelProvider(){
		sortOrder = SHOW_NUMBER;
	}
    
	public int getOrder(){
		return sortOrder;
	}
	public void setOrder(int orderFlag) {
		sortOrder = orderFlag;
	}

	public Image getImage(Object element) {
		/*
		if (element instanceof IPNode) {
			IPNode node = (IPNode)element;
			
	        if(node.hasChildren()) {
	        	if (!node.isAllStop())
	        		return ParallelImages.getImage(ParallelImages.IMG_NODE_RUNNING);
	        	else
	        		return ParallelImages.getImage(ParallelImages.IMG_NODE_EXITED);
	        }
	        else {
		        String nodeState = node.getState();
			
		        if (nodeState != null && nodeState.equals("up")) {
	                String user = node.getUser();
	                if (node.isCurrentUser()) {
	                    String mode = node.getMode();
	                    if (mode != null && mode.equals("0100"))
	                    	return ParallelImages.getImage(ParallelImages.IMG_NODE_USER_ALLOC_EXCL);
	                    else if (mode != null && (mode.equals("0110") || mode.equals("0111") || mode.equals("0101")))
	                    	return ParallelImages.getImage(ParallelImages.IMG_NODE_USER_ALLOC_SHARED);
	                }
	                else if (user != null && !user.equals("root")) {
	                    String mode = node.getMode();
	                    if (mode != null && mode.equals("0100"))
	                    	return ParallelImages.getImage(ParallelImages.IMG_NODE_OTHER_ALLOC_EXCL);
	                    else if (mode != null && (mode.equals("0110") || mode.equals("0111") || mode.equals("0101")))
	                    	return ParallelImages.getImage(ParallelImages.IMG_NODE_OTHER_ALLOC_SHARED);
	                }
	                return ParallelImages.getImage(ParallelImages.IMG_NODE_UP);
	            }
	       	  	else if(nodeState != null && nodeState.equals("down")) {
	       	  		return ParallelImages.getImage(ParallelImages.IMG_NODE_DOWN);
	       	  	}
	       	  	else if(nodeState != null && nodeState.equals("error")) {
	       	  		return ParallelImages.getImage(ParallelImages.IMG_NODE_ERROR);
	       	  	}
	        }
		} else if (element instanceof IPProcess) {
	        IPProcess process = (IPProcess)element;
	        String state = process.getStatus();
	        if (state == null)
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_ERROR);
	        else if (state.equals(IPProcess.STARTING))
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_STARTING);
	        else if (state.equals(IPProcess.RUNNING))
    			return ParallelImages.getImage(ParallelImages.IMG_PROC_RUNNING);
        	else if(state.equals(IPProcess.EXITED))
        		return ParallelImages.getImage(ParallelImages.IMG_PROC_EXITED);
        	else if(state.equals(IPProcess.EXITED_SIGNALLED))
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_EXITED_SIGNAL);
        	else if(state.equals(IPProcess.STOPPED))
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_STOPPED);
        	else if(state.equals(IPProcess.ERROR))
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_ERROR);
        	else
	        	return ParallelImages.getImage(ParallelImages.IMG_PROC_ERROR);			
		}*/
		return null;
	}
	public String getText(Object element) {
	    /*
		if (element instanceof IPElement){
			return getElementText((IPElement) element);
		}
		*/
		switch (getOrder()){
			case SHOW_NUMBER:
			    if (element instanceof IPElement)
			        return ((IPElement)element).getElementName();
			    
				return "";			
			case SHOW_PID:
			    if (element instanceof IPProcess)
			        return ((IPProcess)element).getPid();
			   
			    return "";
			case SHOW_EXITCODE:
			    if (element instanceof IPProcess)
			        return ((IPProcess)element).getExitCode();
			   
			    return "";
		}

		return "";
	}	


	private String getElementText(IPElement element){
		IPElement parent = element.getParent();
		return element.getElementName();
	}	
}

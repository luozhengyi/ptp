/**
 * Copyright (c) 2007 ORNL and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the term of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 
 * @author - Feiyi Wang
 * initial API and implementation
 * 
 */

package org.eclipse.ptp.ui.consoles;

import org.eclipse.ptp.core.attributes.IntegerAttribute;
import org.eclipse.ptp.core.attributes.StringAttribute;
import org.eclipse.ptp.core.elements.IPJob;
import org.eclipse.ptp.core.elements.IPProcess;
import org.eclipse.ptp.core.elements.attributes.ProcessAttributes;
import org.eclipse.ptp.core.elements.events.IChangedProcessEvent;
import org.eclipse.ptp.core.elements.events.INewProcessEvent;
import org.eclipse.ptp.core.elements.events.IProcessChangeEvent;
import org.eclipse.ptp.core.elements.events.IRemoveProcessEvent;
import org.eclipse.ptp.core.elements.listeners.IJobChildListener;
import org.eclipse.ptp.core.elements.listeners.IProcessListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class JobConsole implements IJobChildListener, IProcessListener {
	/** 
	 * search for console name first, if non-existed, 
	 * create a new one
	 * 
	 * @param name
	 * @return
	 */
	public static MessageConsole findAndCreateConsole(String name) {
		MessageConsole myConsole = findConsole(name);
		if (myConsole == null) {
			// no console found, so create a new one
			ConsolePlugin plugin = ConsolePlugin.getDefault();
			myConsole = new MessageConsole(name, null);
			IConsoleManager conMan = plugin.getConsoleManager();
			conMan.addConsoles(new IConsole[] {myConsole});
		}
		return myConsole;
	}
	
	/**
	 * search and return a message console with given name
	 * 
	 * @param name
	 * @return null if not found
	 */
	public static MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++) {
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		}
		return null;
	}

	/**
	 * utility method that allow direct output to a particular console
	 * 
	 * @param name - console name
	 * @param msg  - method for the output
	 */
	public static void jout(String name, String msg) {
		MessageConsole myConsole = findAndCreateConsole(name);
		MessageConsoleStream out = myConsole.newMessageStream();
		out.println(msg);		
	}

	private Color red;
	
	private MessageConsole myConsole;	// MessageConsole associated with this job
	private MessageConsoleStream outputStream;	// Output stream for the console
	private MessageConsoleStream errorStream;	// Error stream for the console
	// TODO get this flag from preferences
	private boolean prefix = false;	// Flag indicating if output should be prefixed with process index
	
	public JobConsole(IPJob job) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		boolean haveConsole = false;
		ConsolePlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				red = ConsolePlugin.getStandardDisplay().getSystemColor(SWT.COLOR_RED);
			}
		});
		// check if this id is already associated with a console
		// if it is, no new console will be created
		IConsole[] existing = conMan.getConsoles();
		String id = getUniqueName(job);
		for (int i = 0; i < existing.length; i++) {
			if (id.equals(existing[i].getName())) {
				myConsole =  (MessageConsole) existing[i];
				haveConsole = true;
				break;
			}
		}
		if (!haveConsole) {
			myConsole = new MessageConsole(id, null);
			outputStream = myConsole.newMessageStream();
			errorStream = myConsole.newMessageStream();
			errorStream.setColor(red);
			conMan.addConsoles(new IConsole[] {myConsole});
		}
	}
	
	/**
	 * Send output from a process to the console. If the prefix flag is true,
	 * each line of the output will be prefixed by the process index.
	 * 
	 * @param index prefix added to each line of output
	 * @param msg output from process
	 */
	public void cout(String index, String msg, MessageConsoleStream stream) {
		String output = ""; //$NON-NLS-1$
		
		if (prefix) {
			String[] lines = msg.split("\n"); //$NON-NLS-1$
			for (int i = 0; i < lines.length; i++) {
				if (i > 0) {
					output += "\n"; //$NON-NLS-1$
				}
				output += "[" + index + "] " + lines[i]; //$NON-NLS-1$ //$NON-NLS-2$
			}
		} else {
			output = msg;
		}
		
		stream.print(output);				
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ptp.core.elements.listeners.IJobChildListener#handleEvent(org.eclipse.ptp.core.elements.events.IChangedProcessEvent)
	 */
	public void handleEvent(IChangedProcessEvent e) {
		// Ignore event
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ptp.core.elements.listeners.IJobChildListener#handleEvent(org.eclipse.ptp.core.elements.events.INewProcessEvent)
	 */
	public void handleEvent(INewProcessEvent e) {
		for (IPProcess process : e.getProcesses()) {
			process.addElementListener(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ptp.core.elements.listeners.IProcessListener#handleEvent(org.eclipse.ptp.core.elements.events.IProcessChangeEvent)
	 */
	public void handleEvent(IProcessChangeEvent e) {
		IntegerAttribute index = e.getSource().getAttribute(
				ProcessAttributes.getIndexAttributeDefinition());
		if (index != null) {
			StringAttribute stdout = e.getAttributes().getAttribute(
					ProcessAttributes.getStdoutAttributeDefinition());
			if (stdout != null) {
				cout(index.getValueAsString(), stdout.getValueAsString(), outputStream);
			}
			StringAttribute stderr = e.getAttributes().getAttribute(
					ProcessAttributes.getStderrAttributeDefinition());
			if (stderr != null) {
				cout(index.getValueAsString(), stderr.getValueAsString(), errorStream);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ptp.core.elements.listeners.IJobChildListener#handleEvent(org.eclipse.ptp.core.elements.events.IRemoveProcessEvent)
	 */
	public void handleEvent(IRemoveProcessEvent e) {
		for (IPProcess process : e.getProcesses()) {
			process.removeElementListener(this);
		}
	}
	
	/**
	 * @param name
	 * @return
	 */
	public boolean removeConsole() {
		if (myConsole != null) {
			ConsolePlugin plugin = ConsolePlugin.getDefault();
			IConsoleManager conMan = plugin.getConsoleManager();
			conMan.removeConsoles(new IConsole[] {myConsole});
			return true;
		}
		return false;
	}

	/**
	 * Generate a unique name for the job
	 * 
	 * @param job
	 * @return unique name for the job
	 */
	private String getUniqueName(IPJob job) {
		String rmName    = job.getQueue().getResourceManager().getName();
		String queueName = job.getQueue().getName();
		String jobName   = job.getName();
		return rmName + ":" + queueName + ":" + jobName; //$NON-NLS-1$ //$NON-NLS-2$
	}
}

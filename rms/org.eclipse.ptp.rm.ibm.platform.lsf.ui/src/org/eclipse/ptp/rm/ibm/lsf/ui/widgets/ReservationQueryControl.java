// Copyright (c) 2013 IBM Corporation and others. All rights reserved. 
// This program and the accompanying materials are made available under the 
// terms of the Eclipse Public License v1.0s which accompanies this distribution, 
// and is available at http://www.eclipse.org/legal/epl-v10.html

package org.eclipse.ptp.rm.ibm.lsf.ui.widgets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ptp.remote.core.IRemoteConnection;
import org.eclipse.ptp.remote.core.IRemoteProcess;
import org.eclipse.ptp.remote.core.IRemoteProcessBuilder;
import org.eclipse.ptp.remote.core.IRemoteServices;
import org.eclipse.ptp.rm.ibm.lsf.ui.LSFCommand;
import org.eclipse.ptp.rm.jaxb.control.ui.IWidgetDescriptor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ReservationQueryControl extends LSFQueryControl {
	private static final String queryCommand[] = {"brsvs", "-w"}; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Create the custom widget for the JAXB ui. In this case the widget is a
	 * push button that pops up a dialog with a list of active reservations when
	 * the button is pushed.
	 * 
	 * @param parent
	 *            : Container for the widget
	 * @param wd
	 *            : Information about the custom widget
	 */
	public ReservationQueryControl(Composite parent, final IWidgetDescriptor wd) {
		super(parent, wd);
		queryTitle = Messages.ReservationQueryTitle;
	}

	@Override
	protected void configureQueryButton(Button button,
			final IRemoteConnection connection) {
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * Handle button press event. Pop up a dialog listing reservations. If the user
			 * selects a reservation and clicks the ok button notify listeners that this
			 * widget has been modified.
			 * 
			 * @param e: The selection event
			 */
			public void widgetSelected(SelectionEvent e) {
				getQueryResponse(connection);
			}
		});
	}

	/**
	 * Issue the 'brsvs' command to query the reservation list and set up the
	 * column heading and reservation data arrays.
	 * 
	 * @param connection
	 *            : Connection to the remote system
	 */
	@Override
	protected void getQueryResponse(IRemoteConnection connection) {
		queueQuery = new LSFCommand(Messages.ReservationCommandDesc, connection, queryCommand);
		queueQuery.setUser(true);
		queueQuery.addJobChangeListener(jobListener);
		queueQuery.schedule();
	}
}

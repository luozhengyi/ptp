/*******************************************************************************
 * Copyright (c) 2000, 2004 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ptp.debug.internal.core;

import org.eclipse.cdt.debug.core.cdi.CDIException;
import org.eclipse.cdt.debug.core.cdi.ICDISession;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.ptp.debug.core.PTPDebugCorePlugin;
import org.eclipse.ptp.debug.core.cdi.model.IPCDITarget;

/**
 * Default implementation of the session manager. Terminates the session when the last target is terminated;
 */
public class SessionManager implements IDebugEventSetListener {

	public SessionManager() {
		DebugPlugin.getDefault().addDebugEventListener( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter( Class adapter ) {
		if ( SessionManager.class.equals( adapter ) )
			return this;
		return null;
	}

	public void dispose() {
		DebugPlugin.getDefault().removeDebugEventListener( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(org.eclipse.debug.core.DebugEvent[])
	 */
	public void handleDebugEvents( DebugEvent[] events ) {
		for( int i = 0; i < events.length; i++ ) {
			DebugEvent event = events[i];
			if ( event.getKind() == DebugEvent.TERMINATE ) {
				Object element = event.getSource();
				if ( element instanceof IDebugTarget && ((IDebugTarget)element).getAdapter( IPCDITarget.class ) != null ) {
					handleTerminateEvent( ((IDebugTarget)element).getLaunch(), ((IPCDITarget)((IDebugTarget)element).getAdapter( IPCDITarget.class )).getSession() );
				}
			}
		}
	}

	private void handleTerminateEvent( ILaunch launch, ICDISession session ) {
		IDebugTarget[] targets = launch.getDebugTargets();
		boolean terminate = true;
		for( int i = 0; i < targets.length; ++i ) {
			if ( targets[i].getAdapter( IPCDITarget.class ) != null && session.equals( ((IPCDITarget)targets[i].getAdapter( IPCDITarget.class )).getSession() ) && !targets[i].isTerminated() && !targets[i].isDisconnected() )
				terminate = false;
		}
		if ( terminate ) {
			try {
				session.terminate();
			}
			catch( CDIException e ) {
				PTPDebugCorePlugin.log( e );
			}
		}
	}
}

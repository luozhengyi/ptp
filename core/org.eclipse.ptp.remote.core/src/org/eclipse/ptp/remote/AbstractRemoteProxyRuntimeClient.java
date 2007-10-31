/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ptp.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.ptp.core.PTPCorePlugin;
import org.eclipse.ptp.proxy.runtime.client.AbstractProxyRuntimeClient;

public class AbstractRemoteProxyRuntimeClient extends AbstractProxyRuntimeClient {
	
	private boolean				proxyDebugOutput = true;
	private final String		proxyName;
	private final String		proxyPath;
	private final String		localAddr;
	private final int	 		proxyOptions;
	private final String		remoteServicesId;
	private final String		connectionName;
	private final List<String>	invocationOptions;

	public AbstractRemoteProxyRuntimeClient(AbstractRemoteResourceManagerConfiguration config, 
			int baseModelId) {
		super(config.getName(), baseModelId);
		this.remoteServicesId = config.getRemoteServicesId();
		this.connectionName = config.getConnectionName();
		this.proxyName = config.getName();
		this.proxyPath = config.getProxyServerPath();
		this.localAddr = config.getLocalAddress();
		this.proxyOptions = config.getOptions();
		this.invocationOptions = config.getInvocationOptions();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ptp.rtsystem.proxy.AbstractProxyRuntimeClient#shutdownProxyServer()
	 */
	@Override
	protected void shutdownProxyServer() {
		try {
			sessionFinish();
		} catch (IOException e) {
			e.printStackTrace();
			PTPCorePlugin.log(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ptp.rtsystem.proxy.AbstractProxyRuntimeClient#startupProxyServer()
	 */
	@Override
	protected void startupProxyServer() throws IOException {
		if (getEventLogging()) {
			System.out.println(toString() + " - firing up proxy, waiting for connection.  Please wait!  This can take a minute . . .");
			System.out.println("PROXY_SERVER path = '" + proxyPath + "'");
		}
		
		boolean stdio = (proxyOptions & IRemoteProxyOptions.STDIO) == IRemoteProxyOptions.STDIO;
		boolean portForwarding = (proxyOptions & IRemoteProxyOptions.PORT_FORWARDING) == IRemoteProxyOptions.PORT_FORWARDING;
		boolean manualLaunch = (proxyOptions & IRemoteProxyOptions.MANUAL_LAUNCH) == IRemoteProxyOptions.MANUAL_LAUNCH;
		
		try {
			/*
			 * This can fail if we are restarting the RM from saved information and the saved remote
			 * services provider is no longer available...
			 */
			IRemoteServices remoteServices = PTPRemotePlugin.getDefault().getRemoteServices(remoteServicesId);
			if (remoteServices == null) {
				throw new IOException("Could not find remote services ID " + remoteServicesId);
			}

			if (manualLaunch) {
				sessionCreate();
				
				List<String> args = new ArrayList<String>();
				args.add(proxyPath);
				args.add("--proxy=tcp");
				if (portForwarding) {
					args.add("--host=localhost");
				} else {
					args.add("--host=" + localAddr);
				}
				args.add("--port="+getSessionPort());
				args.addAll(invocationOptions);
				
				if (getEventLogging()) {
					System.out.println("Launch command: " + args.toString());
				}
				
				final String msg = "Waiting for manual launch of proxy: " + args.toString();
				System.out.println(msg);
				Status info = new Status(IStatus.INFO, PTPCorePlugin.getUniqueIdentifier(), IStatus.INFO, msg, null);
				PTPCorePlugin.log(info);
			} else {
				IRemoteConnectionManager connMgr = remoteServices.getConnectionManager();
				IRemoteConnection conn = connMgr.getConnection(connectionName);

				/*
				 * Check the remote proxy exists
				 */
				IRemoteFileManager fileManager = remoteServices.getFileManager(conn);
				try {
					if (!fileManager.getResource(new Path(proxyPath), new NullProgressMonitor()).fetchInfo().exists()){
						throw new IOException("Could not find proxy executable \"" + proxyPath + "\"");
					}
				} catch (CoreException e1) {
					throw new IOException(e1.getMessage());
				}

				if (!stdio) {
					sessionCreate();
					
					ArrayList<String> args = new ArrayList<String>();
					args.add(proxyPath);
					args.add("--proxy=tcp");
					if (portForwarding) {
						args.add("--host=localhost");
					} else {
						args.add("--host=" + localAddr);
					}
					args.add("--port="+getSessionPort());
					args.addAll(invocationOptions);
					
					if (getEventLogging()) {
						System.out.println("Launch command: " + args.toString());
					}

					IRemoteProcessBuilder processBuilder = remoteServices.getProcessBuilder(conn, args);
					IRemoteProcess process = processBuilder.asyncStart();
					
					final BufferedReader err_reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					final BufferedReader out_reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

					new Thread(new Runnable() {
						public void run() {
							try {
								String output;
								while ((output = out_reader.readLine()) != null) {
									if (proxyDebugOutput) System.out.println(proxyName + ": " + output);
								}
							} catch (IOException e) {
								// Ignore
							}
						}
					}, "Program output Thread").start();
					
					new Thread(new Runnable() {
						public void run() {
							try {
								String line;
								while ((line = err_reader.readLine()) != null) {
									if (proxyDebugOutput) System.err.println(proxyName + ": " + line);
								}
							} catch (IOException e) {
								// Ignore
							}
						}
					}, "Error output Thread").start();
					
					if (getEventLogging()) {
						System.out.println(toString() + ": Waiting on accept.");
					}
				} else {
					ArrayList<String> args = new ArrayList<String>();
					args.add(proxyPath);
					args.add("--proxy=stdio");
					args.addAll(invocationOptions);

					IRemoteProcessBuilder processBuilder = remoteServices.getProcessBuilder(conn, args);
					IRemoteProcess process = processBuilder.asyncStart();
					
					sessionCreate(process.getOutputStream(), process.getInputStream());
					
					final BufferedReader err_reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					
					new Thread(new Runnable() {
						public void run() {
							try {
								String line;
								while ((line = err_reader.readLine()) != null) {
									if (proxyDebugOutput) System.err.println(proxyName + ": " + line);
								}
							} catch (IOException e) {
								// Ignore
							}
						}
					}, "Error output Thread").start();
					
					if (getEventLogging()) {
						System.out.println(toString() + ": Waiting on accept.");
					}
				}
			}
		} catch (IOException e) {
			try {
				sessionFinish();
			} catch (IOException e1) {
				PTPCorePlugin.log(e1);
			}
			throw new IOException("Failed to start proxy: " + e.getMessage());
		}
	}
}

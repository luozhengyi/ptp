/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.ptp.rm.ibm.pe.ui.wizards;

import org.eclipse.ptp.rm.ibm.pe.core.rmsystem.PEServiceProviderFactory;
import org.eclipse.ptp.rmsystem.IResourceManagerFactory;
import org.eclipse.ptp.ui.wizards.IRMConfigurationWizard;
import org.eclipse.ptp.ui.wizards.RMConfigurationWizardPage;
import org.eclipse.ptp.ui.wizards.RMConfigurationWizardPageFactory;


/**
 * This class is only used to bridge between the old RM factory wizard and the new RMs backed
 * by service configurations. It will be removed prior to the 3.0 release.
 * 
 * @author greg
 *
 */
public class PERMServiceProviderConfigurationWizardPageFactory extends RMConfigurationWizardPageFactory {

	public PERMServiceProviderConfigurationWizardPageFactory() {
		// no-op
	}

	@Override
	public RMConfigurationWizardPage[] getPages(IRMConfigurationWizard wizard) {
		return new RMConfigurationWizardPage[]{new PEResourceManagerConfigurationWizardPage(wizard)};
	}

	@Override
	public Class<? extends IResourceManagerFactory> getRMFactoryClass() {
		return PEServiceProviderFactory.class;
	}
}

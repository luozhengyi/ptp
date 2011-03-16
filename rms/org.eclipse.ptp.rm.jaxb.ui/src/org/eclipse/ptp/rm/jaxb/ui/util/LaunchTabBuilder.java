package org.eclipse.ptp.rm.jaxb.ui.util;

import java.util.Map;

import org.eclipse.ptp.rm.jaxb.core.data.TabController;
import org.eclipse.ptp.rm.jaxb.core.data.Widget;
import org.eclipse.ptp.rm.jaxb.ui.IJAXBUINonNLSConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class LaunchTabBuilder implements IJAXBUINonNLSConstants {

	private final TabController tabController;
	private final Map<Control, Widget> valueWidgets;
	private final Map<String, Boolean> selected;

	public LaunchTabBuilder(TabController tabController, Map<Control, Widget> valueWidgets, Map<String, Boolean> selected) {
		this.tabController = tabController;
		this.valueWidgets = valueWidgets;
		this.selected = selected;
	}

	public void build(Composite parent) throws Throwable {
	}
}

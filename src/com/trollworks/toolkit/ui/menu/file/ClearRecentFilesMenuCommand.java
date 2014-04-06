/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.trollworks.toolkit.ui.menu.file;

import com.trollworks.toolkit.annotation.Localize;
import com.trollworks.toolkit.ui.menu.Command;
import com.trollworks.toolkit.utility.Localization;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

/** Provides the "Clear" command in the {@link RecentFilesMenu}. */
public class ClearRecentFilesMenuCommand extends Command {
	@Localize("Clear")
	private static String							CLEAR;

	static {
		Localization.initialize();
	}

	/** The action command this command will issue. */
	public static final String						CMD_CLEAR_RECENT_FILES_MENU	= "ClearRecentFilesMenu";				//$NON-NLS-1$

	/** The singleton {@link ClearRecentFilesMenuCommand}. */
	public static final ClearRecentFilesMenuCommand	INSTANCE					= new ClearRecentFilesMenuCommand();

	private ClearRecentFilesMenuCommand() {
		super(CLEAR, CMD_CLEAR_RECENT_FILES_MENU);
	}

	@Override
	public void adjustForMenu(JMenuItem item) {
		setEnabled(RecentFilesMenu.getRecentCount() > 0);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		RecentFilesMenu.clearRecents();
	}
}

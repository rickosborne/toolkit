/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.trollworks.toolkit.ui.menu.help;

import com.trollworks.toolkit.ui.menu.Command;
import com.trollworks.toolkit.ui.widget.WindowUtils;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;

/** A command that will open a specific file in the preferred browser. */
public class OpenLocalFileCommand extends Command {
	private File	mFile;

	/**
	 * Creates a new {@link OpenLocalFileCommand}.
	 *
	 * @param title The title to use.
	 * @param file The file to open.
	 */
	public OpenLocalFileCommand(String title, File file) {
		super(title, "OpenLocalFile[" + file.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		mFile = file;
	}

	@Override
	public void adjustForMenu(JMenuItem item) {
		// Not used. Always enabled.
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			Desktop.getDesktop().open(mFile);
		} catch (IOException exception) {
			WindowUtils.showError(null, exception.getMessage());
		}
	}
}

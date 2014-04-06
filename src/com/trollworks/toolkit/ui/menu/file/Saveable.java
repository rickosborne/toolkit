/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as defined
 * by the Mozilla Public License, version 2.0.
 */

package com.trollworks.toolkit.ui.menu.file;

import java.io.File;

/**
 * Windows that want to participate in the standard {@link SaveCommand} and {@link SaveAsCommand}
 * processing must implement this interface.
 */
public interface Saveable extends FileProxy {
	/** @return Whether the changes have been made that could be saved. */
	boolean isModified();

	/**
	 * @return The file extensions allowed when saving. The first one should be used if the user
	 *         doesn't specify an extension.
	 */
	String[] getAllowedExtensions();

	/** @return The preferred file path to use when saving. */
	String getPreferredSavePath();

	/**
	 * Called to actually save the contents to a file.
	 *
	 * @param file The file to save to.
	 * @return The file(s) actually written to.
	 */
	File[] saveTo(File file);
}
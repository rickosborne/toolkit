/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.trollworks.toolkit.collections;

import java.awt.Rectangle;

/** All objects which want to be stored in a {@link AreaTree} must implement this interface. */
public interface AreaObject {
	/** @return The {@link Rectangle} which defines this object's bounds. */
	Rectangle getBounds();
}

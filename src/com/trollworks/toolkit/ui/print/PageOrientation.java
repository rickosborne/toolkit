/*
 * Copyright (c) 1998-2014 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.trollworks.toolkit.ui.print;

import com.trollworks.toolkit.annotation.Localize;
import com.trollworks.toolkit.utility.Localization;

import java.awt.print.PageFormat;

import javax.print.attribute.standard.OrientationRequested;

/** Constants representing the various page orientation possibilities. */
public enum PageOrientation {
	/** Maps to {@link OrientationRequested#PORTRAIT}. */
	PORTRAIT {
		@Override
		public OrientationRequested getOrientationRequested() {
			return OrientationRequested.PORTRAIT;
		}

		@Override
		public String toString() {
			return PORTRAIT_TITLE;
		}
	},
	/** Maps to {@link OrientationRequested#LANDSCAPE}. */
	LANDSCAPE {
		@Override
		public OrientationRequested getOrientationRequested() {
			return OrientationRequested.LANDSCAPE;
		}

		@Override
		public String toString() {
			return LANDSCAPE_TITLE;
		}
	},
	/** Maps to {@link OrientationRequested#REVERSE_PORTRAIT}. */
	REVERSE_PORTRAIT {
		@Override
		public OrientationRequested getOrientationRequested() {
			return OrientationRequested.REVERSE_PORTRAIT;
		}

		@Override
		public String toString() {
			return REVERSED_PORTRAIT_TITLE;
		}
	},
	/** Maps to {@link OrientationRequested#REVERSE_LANDSCAPE}. */
	REVERSE_LANDSCAPE {
		@Override
		public OrientationRequested getOrientationRequested() {
			return OrientationRequested.REVERSE_LANDSCAPE;
		}

		@Override
		public String toString() {
			return REVERSED_LANDSCAPE_TITLE;
		}
	};

	@Localize("Portrait")
	static String	PORTRAIT_TITLE;
	@Localize("Landscape")
	static String	LANDSCAPE_TITLE;
	@Localize("Reversed Portrait")
	static String	REVERSED_PORTRAIT_TITLE;
	@Localize("Reversed Landscape")
	static String	REVERSED_LANDSCAPE_TITLE;

	static {
		Localization.initialize();
	}

	/** @return The orientation attribute. */
	public abstract OrientationRequested getOrientationRequested();

	/**
	 * @param orientation The {@link OrientationRequested} to load from.
	 * @return The page orientation.
	 */
	public static final PageOrientation get(OrientationRequested orientation) {
		for (PageOrientation one : values()) {
			if (one.getOrientationRequested() == orientation) {
				return one;
			}
		}
		return PORTRAIT;
	}

	/**
	 * @param format The {@link PageFormat} to load from.
	 * @return The page orientation.
	 */
	public static final PageOrientation get(PageFormat format) {
		switch (format.getOrientation()) {
			case PageFormat.LANDSCAPE:
				return PageOrientation.LANDSCAPE;
			case PageFormat.REVERSE_LANDSCAPE:
				return PageOrientation.REVERSE_LANDSCAPE;
			case PageFormat.PORTRAIT:
			default:
				return PageOrientation.PORTRAIT;
		}
	}
}

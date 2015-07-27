/*
 * Copyright (c) 1998-2015 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as defined
 * by the Mozilla Public License, version 2.0.
 */

package com.trollworks.toolkit.expression;

import com.trollworks.toolkit.annotation.Localize;
import com.trollworks.toolkit.utility.Localization;
import com.trollworks.toolkit.utility.text.Numbers;

import java.util.Enumeration;

public class ArgumentTokenizer implements Enumeration<String> {
	@Localize("Invalid operand: ")
	private static String	INVALID_OPERAND;
	@Localize("Invalid argument: ")
	private static String	INVALID_ARGUMENT;

	static {
		Localization.initialize();
	}

	private String			mArguments	= null;

	public ArgumentTokenizer(String arguments) {
		mArguments = arguments;
	}

	@Override
	public final boolean hasMoreElements() {
		return hasMoreTokens();
	}

	public final boolean hasMoreTokens() {
		return mArguments.length() > 0;
	}

	@Override
	public final String nextElement() {
		return nextToken();
	}

	public final String nextToken() {
		int length = mArguments.length();
		int parens = 0;
		for (int i = 0; i < length; i++) {
			char ch = mArguments.charAt(i);
			if (ch == '(') {
				parens++;
			} else if (ch == ')') {
				parens--;
			} else if (ch == ',' && parens == 0) {
				String token = mArguments.substring(0, i);
				mArguments = mArguments.substring(i + 1);
				return token;
			}
		}
		String token = mArguments;
		mArguments = ""; //$NON-NLS-1$
		return token;
	}

	public static final double getDouble(Object arg) {
		if (arg instanceof Double) {
			return ((Double) arg).doubleValue();
		}
		return Numbers.extractDouble(arg.toString(), 0, false);
	}

	public static final double getDoubleOperand(Object arg) throws EvaluationException {
		try {
			return getDouble(arg);
		} catch (Exception exception) {
			throw new EvaluationException(INVALID_OPERAND + arg, exception);
		}
	}

	public static final double getDoubleArgument(Evaluator evaluator, String arguments) throws EvaluationException {
		try {
			return getDouble(new Evaluator(evaluator).evaluate(arguments));
		} catch (Exception exception) {
			throw new EvaluationException(INVALID_ARGUMENT + arguments, exception);
		}
	}
}
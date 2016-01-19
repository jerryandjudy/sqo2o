package com.jspgou.common.web.freemarker;

import freemarker.template.TemplateModelException;

/**
* This class should preserve.
* @preserve
*/
@SuppressWarnings("serial")
public class MustDateException extends TemplateModelException {
	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
}

package com.jspgou.common.web.freemarker;

import freemarker.template.TemplateModelException;

/**
* This class should preserve.
* @preserve
*/
@SuppressWarnings("serial")
public class ParamsRequiredException extends TemplateModelException {
	public ParamsRequiredException(String paramName) {
		super("The required \"" + paramName + "\" paramter is missing.");
	}
}

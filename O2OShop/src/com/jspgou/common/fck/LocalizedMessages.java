package com.jspgou.common.fck;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.LocaleResolver;

import com.jspgou.common.web.springmvc.MessageResolver;

/**
* This class should preserve.
* @preserve
*/
public class LocalizedMessages {
	/**
	 * Searches for the message with the specified key in this message list.
	 * 
	 * @see Properties#getProperty(String)
	 */
	private static String getMessage(HttpServletRequest request, String key,
			Object... args) {
		return MessageResolver.getMessage(request, key, args);
	}

	/**
	 * Returns localized <code>fck.editor.compatibleBrowser.yes</code> property.
	 */
	public static String getCompatibleBrowserYes(HttpServletRequest request) {
		return getMessage(request, "fck.editor.compatibleBrowser.yes"); //$NON-NLS-1$
	}

	/** Returns localized <code>fck.editor.compatibleBrowser.no</code> property. */
	public static String getCompatibleBrowserNo(HttpServletRequest request) {
		return getMessage(request, "fck.editor.compatibleBrowser.no"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.enabled</code> property.
	 */
	public static String getFileUploadEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.disabled</code>
	 * property.
	 */
	public static String getFileUploadDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.file_renamed_warning</code>
	 * property.
	 * 
	 * @param newFileName
	 *            the new filename of the warning
	 * @return localized message with new filename
	 */
	public static String getFileRenamedWarning(HttpServletRequest request,
			String newFileName) {
		return getMessage(request,
				"fck.connector.fileUpload.file_renamed_warning", newFileName); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.fileUpload.invalid_file_type_specified</code>
	 * property.
	 */
	public static String getInvalidFileTypeSpecified(HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.fileUpload.invalid_file_type_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.fileUpload.write_error</code>
	 * property.
	 */
	public static String getFileUploadWriteError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.fileUpload.write_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.enabled</code>
	 * property.
	 */
	public static String getGetResourcesEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.disabled</code>
	 * property.
	 */
	public static String getGetResourcesDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.getResources.read_error</code>
	 * property.
	 */
	public static String getGetResourcesReadError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.getResources.read_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.enabled</code>
	 * property.
	 */
	public static String getCreateFolderEnabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.enabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.disabled</code>
	 * property.
	 */
	public static String getCreateFolderDisabled(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.disabled"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.invalid_command_specified</code>
	 * property.
	 */
	public static String getInvalidCommandSpecified(HttpServletRequest request) {
		return getMessage(request, "fck.connector.invalid_command_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.createFolder.folder_already_exists_error</code>
	 * property.
	 */
	public static String getFolderAlreadyExistsError(HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.createFolder.folder_already_exists_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.createFolder.invalid_new_folder_name_specified</code>
	 * property.
	 */
	public static String getInvalidNewFolderNameSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.createFolder.invalid_new_folder_name_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized <code>fck.connector.createFolder.write_error</code>
	 * property.
	 */
	public static String getCreateFolderWriteError(HttpServletRequest request) {
		return getMessage(request, "fck.connector.createFolder.write_error"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.invalid_resource_type_specified</code> property.
	 */
	public static String getInvalidResouceTypeSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.invalid_resource_type_specified"); //$NON-NLS-1$
	}

	/**
	 * Returns localized
	 * <code>fck.connector.invalid_current_folder_specified</code> property.
	 */
	public static String getInvalidCurrentFolderSpecified(
			HttpServletRequest request) {
		return getMessage(request,
				"fck.connector.invalid_current_folder_specified"); //$NON-NLS-1$
	}

}

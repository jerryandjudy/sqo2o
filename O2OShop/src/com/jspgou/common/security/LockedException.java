package com.jspgou.common.security;

/**
 * 账号被锁定异常
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
@SuppressWarnings("serial")
public class LockedException extends AccountStatusException {
	public LockedException() {
	}

	public LockedException(String msg) {
		super(msg);
	}

	public LockedException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}

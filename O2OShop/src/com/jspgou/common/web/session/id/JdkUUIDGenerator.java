package com.jspgou.common.web.session.id;

import java.util.UUID;
import org.apache.commons.lang.StringUtils;
/**
* This class should preserve.
* @preserve
*/
public class JdkUUIDGenerator implements SessionIdGenerator {

	public String get() {
		return StringUtils.remove(UUID.randomUUID().toString(), '-');
	}
}

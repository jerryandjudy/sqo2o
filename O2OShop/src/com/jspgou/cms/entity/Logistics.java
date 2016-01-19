package com.jspgou.cms.entity;

import java.util.Iterator;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseLogistics;


/**
* This class should preserve.
* @preserve
*/
public class Logistics extends BaseLogistics {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Logistics () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Logistics (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Logistics (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Integer priority) {

		super (
			id,
			name,
			priority);
	}

/*[CONSTRUCTOR MARKER END]*/


	public LogisticsText getLogisticsText() {
		Set<LogisticsText> set = getLogisticsTextSet();
		if (set != null) {
			Iterator<LogisticsText> it = set.iterator();
			if (it.hasNext()) {
				return it.next();
			}
		}
		return null;
	}
	
	/**
	 * 获得品牌详细信息
	 * 
	 * @return
	 */
	public String getText() {
		LogisticsText logisticsText = getLogisticsText();
		if (logisticsText != null) {
			return logisticsText.getText();
		}
		return null;
	}
}
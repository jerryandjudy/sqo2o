package com.jspgou.cms.entity;

import java.util.ArrayList;
import java.util.List;
import com.jspgou.cms.entity.base.BaseOrderReturn;


/**
* This class should preserve.
* @preserve
*/
public class OrderReturn extends BaseOrderReturn {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public OrderReturn () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public OrderReturn (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public OrderReturn (
		java.lang.Long id,
		com.jspgou.cms.entity.Order order,
		com.jspgou.cms.entity.ShopDictionary shopDictionary,
		java.lang.Integer payType,
		java.lang.Integer status,
		java.lang.Double money,
		java.lang.Integer returnType,
		java.util.Date createTime) {

		super (
			id,
			order,
			shopDictionary,
			payType,
			status,
			money,
			returnType,
			createTime);
	}

	/**
	 * 退款类型
	 * 
	 * @author liufang
	 * 
	 */
	
	public void addToPictures(String path, String desc) {
		List<OrderReturnPicture> list = getPictures();
		if (list == null) {
			list = new ArrayList<OrderReturnPicture>();
			setPictures(list);
		}
		OrderReturnPicture cp = new OrderReturnPicture();
		cp.setImgPath(path);
		cp.setDescription(desc);
		list.add(cp);
	}
	
	/**
	 * 获得Enum类型的退款类型
	 * 
	 * @return
	 */
	public OrderReturnStatus getOrderReturnStatus() {
		Integer status = getReturnType();
		if (status != null) {
			return OrderReturnStatus.valueOf(status);
		} else {
			return null;
		}
	}
	
	/**
	 * 退款类型
	 * 
	 * @author liufang
     * This class should preserve.
     * @preserve private
    */
	public static enum OrderReturnStatus {
		//等待付款、等待发货、卖家已发货、买家确认收货、退货、卖家同意退货、买家寄出退货、卖家已收退货、退款
		/**
		 * 已发货，申请退款
		 */
		SELLER_NODELIVERY_RETURN(1),
		/**
		 * 未发货，商量退款
		 */
		SELLER_DELIVERY_RETURN(2);


		private OrderReturnStatus(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}

		public static OrderReturnStatus valueOf(int value) {
			for (OrderReturnStatus s : OrderReturnStatus.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
			throw new IllegalArgumentException("Connot find value " + value
					+ " in eunu OrderStatus.");
		}
	}

}
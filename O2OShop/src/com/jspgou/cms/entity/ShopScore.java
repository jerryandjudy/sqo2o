package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShopScore;


/**
* This class should preserve.
* @preserve
*/
public class ShopScore extends BaseShopScore {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ShopScore () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopScore (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopScore (
		java.lang.Long id,
		java.lang.Integer scoreType,
		boolean useStatus,
		boolean status) {

		super (
			id,
			scoreType,
			useStatus,
			status);
	}

/*[CONSTRUCTOR MARKER END]*/

	/**
	 * 订单状态
	 * 
	 * @author liufang
     * This class should preserve.
     * @preserve private
    */
	public static enum ScoreTypes {
		//等待付款、等待发货、卖家已发货、买家确认收货、退货、卖家同意退货、买家寄出退货、卖家已收退货、退款
		/**
		 *  邮箱验证
		 */
		EMAIL_VALIDATE(10),
		/**
		 * 订单积分
		 */
		ORDER_SCORE(11),
		/**
		 * 已收退款（买家已收退款）
		 */
		BUYER_RETURN_PAY(22);

		private ScoreTypes(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}

		public static ScoreTypes valueOf(int value) {
			for (ScoreTypes s : ScoreTypes.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
			throw new IllegalArgumentException("Connot find value " + value
					+ " in eunu OrderStatus.");
		}
	}
}
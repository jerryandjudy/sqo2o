package com.jspgou.cms.entity;

import java.math.BigDecimal;

import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.base.BaseOrderItem;
/**
* This class should preserve.
* @preserve
*/
public class OrderItem extends BaseOrderItem {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得小计
	 * 
	 * @return
	 */
	public Double getSubtotal() {
//		return getFinalPrice().multiply(new BigDecimal(getCount()));
		return getFinalPrice()*getCount();
	}
	/**
	 * 获得秒杀产品小计
	 * 
	 * @return
	 */
	public Double getLimitSubtotal() {
//		return this.getSeckillprice().multiply(new BigDecimal(getCount()));
		return getSeckillprice()*getCount();
	}

	/**
	 * 获得重量小计，免运费的商品重量记0。
	 * 
	 * @return
	 */
	public int getWeightForFreight() {
		Product p = super.getProductSite().getProduct();
		// TODO 免运费?
		return p.getWeight() * getCount();
	}

	/**
	 * 获得商品数量小计，免运费的商品数量记0。
	 * 
	 * @return
	 */
	public int getCountForFreigt() {
		// TODO 免运费?
		return getCount();
	}

	/**
	 * CartItem解析成OrderItem
	 * 
	 * @return
	 */
	public static OrderItem parse(final CartItem cartItem, ShopMemberGroup group) {
		return parse(cartItem.getProductSite(), cartItem.getCount(), group,
				cartItem.getWebsite());
	}
	/**
	 * CartItem解析成OrderItem
	 * 
	 * @return
	 */
	public static OrderItem parse1(final CartItem cartItem, ShopMemberGroup group) {//增加商品款式（wangzewu）
		if(cartItem.getProductFash()==null)
		return parse(cartItem.getProductSite(), cartItem.getCount(), group,
				cartItem.getWebsite());
		else
			return parse1(cartItem.getProductSite(),cartItem.getProductFash(), cartItem.getCount(), group,
					cartItem.getWebsite());
	}
	
	/**
	 * 将商品、数量等信息解析成OrderItem。
	 * 
	 * @param product
	 * @param count
	 * @param group
	 * @param website
	 * @return
	 */
	public static OrderItem parse(ProductSite productSite, int count,
			ShopMemberGroup group, Website website) {
//		product.setStockCount(product.getStockCount()-count);//计算商品库存量
		Product product = productSite.getProduct();
		product.setSaleCount(count+product.getSaleCount());
		OrderItem item = new OrderItem();
		item.setCount(count);
		item.setWebsite(website);
		item.setProductSite(productSite);

		if(product.getProductExt().getIslimitTime()!=null&&product.getProductExt().getIslimitTime()){
			if(product.getProductExt().getSeckillprice()!=null)
			item.setSeckillprice(product.getProductExt().getSeckillprice());
		}
		item.setSalePrice(productSite.getSalePrice());
		item.setCostPrice(productSite.getMarketPrice());
		item.setMemberPrice(productSite.getMemberPrice(group));
		item.setFinalPrice(item.getMemberPrice());
		return item;
	}
	
	/**
	 * 将商品、数量等信息解析成OrderItem。
	 * 
	 * @param product
	 * @param count
	 * @param group
	 * @param website
	 * @return
	 */
	public static OrderItem parse1(ProductSite productSite,ProductFashion productFash, int count,//增加商品款式（wangzewu）
			ShopMemberGroup group, Website website) {
		productFash.setStockCount(productFash.getStockCount()-count);//计算商品款式库存量
		productFash.setSaleCount(count+productFash.getSaleCount());//计算商品款式销售量
		OrderItem item = new OrderItem();
		item.setCount(count);
		item.setWebsite(website);
		item.setProductSite(productSite);
        Product product = productSite.getProduct();
		if(product.getProductExt().getIslimitTime()!=null&&product.getProductExt().getIslimitTime()){
			if(product.getProductExt().getSeckillprice()!=null)
			item.setSeckillprice(product.getProductExt().getSeckillprice());
		}
		item.setSalePrice(productSite.getSalePrice());
		item.setCostPrice(productSite.getMarketPrice());
		item.setMemberPrice(productSite.getMemberPrice(group));
		item.setFinalPrice(item.getMemberPrice());
		item.setProductFash(productFash);
		return item;
	}
	
	

	/* [CONSTRUCTOR MARKER BEGIN] */
	public OrderItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public OrderItem (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public OrderItem (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ProductSite productSite,
		com.jspgou.cms.entity.Order order,
		java.lang.Integer count) {

		super (
			id,
			website,
			productSite,
			order,
			count);
	}

	/* [CONSTRUCTOR MARKER END] */

}
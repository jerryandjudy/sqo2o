package com.jspgou.cms.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.jspgou.cms.entity.base.BaseCart;
/**
* This class should preserve.
* @preserve
*/
public class Cart extends BaseCart {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得购物车总价
	 * 
	 * 包括商品价格和运费
	 * 
	 * @return
	 */
	public Double getTotalPrice() {
//		return getSubtotal().add(getFreight());
		return getSubtotal();
	}
	/**
	 * 获得购物车总价
	 * 
	 * 包括商品价格
	 * 不包括运费
	 */
	public Double getProductTotalPrice() {
		return getSubtotal();
	}

	

	/**
	 * 获得购物车中商品总重量，免运费的商品不记重量。
	 * 
	 * @return
	 */
	public int getWeightForFreight() {
		int weight = 0;
		for (CartItem item : getItems()) {
			weight += item.getWeightForFreight();
		}
		return weight;
	}

	/**
	 * 获得购物车中商品总数量，免运费的商品不计数量。
	 * 
	 * @return
	 */
	public int getCountForFreight() {
		int count = 0;
		for (CartItem item : getItems()) {
			count += item.getCountForFreigt();
		}
		return count;
	}

	/**
	 * 购物车小计
	 * 
	 * @return
	 */
	public Double getSubtotal() {
		Set<CartItem> items = getItems();
		Double total = 0.0;
		if (items != null) {
			for (CartItem item : items) {
				total = total+item.getSubtotal();
			}
		}
		return total;
	}
	public Double getTotalSubtotal() {
		Set<CartItem> items = getItems();
		Double total = 0.0;
		if (items != null) {
			for (CartItem item : items) {
				if((item.getProduct().getProductExt().getIslimitTime()!=null)&&(item.getProduct().getProductExt().getIslimitTime())){
					total = total+item.getLimitSubtotal();
				}else{
					total=total+item.getSubtotal();
				}
//				
			}
		}
		return total;
	}
	/**
	 * 计算商品数量
	 * 
	 * @return
	 */
	public int calTotalItem() {
		Set<CartItem> items = getItems();
		int count = 0;
		if (items != null) {
			for (CartItem item : items) {
				count += item.getCount();
			}
		}
		setTotalItems(count);
		return count;
	}
	/**
	 * 计算购物车总积分(wang ze wu s)
	 * @return
	 */
	public int getTotalScore(){
		Set<CartItem> items = getItems();
		int score = 0;
		if (items != null) {
			for (CartItem item : items) {
				score += item.getScore();
			}
		}
		return score;
	}
	/**
	 * 计算礼品数量
	 * 
	 * @return
	 */
	public int calTotalGift() {
		Set<CartGift> items = getGifts();
		int count = 0;
		if (items != null) {
			for (CartGift item : items) {
				count += item.getCount();
			}
		}
		setTotalGifts(count);
		return count;
	}

	
	
	
	/**
	 * 增加购物车订单项
	 * 
	 * @param product
	 *            商品Entity
	 * @param count
	 *            数量
	 * @param isAdd
	 *            增加或重置
	 */
	public void addItem(ProductSite productSite,ProductFashion productFashion,PopularityGroup popularityGroup,int count, boolean isAdd) {//王泽武（增加了商品款式）
		Product product=productSite.getProduct();
		CartItem item = extensionItem(product,productFashion,popularityGroup);
		count = extensionCount(product,productFashion,popularityGroup,count,isAdd);	
		if (count > 0){
			if(item==null){
				item = new CartItem();
			}
			item.setCount(count);
			item.setScore(count*product.getScore());//购物车商品item积分
			if(extensionItem(product,productFashion,popularityGroup)==null){
				if(productFashion!=null){
					item.setProductFash(productFashion);
				}
				if(popularityGroup!=null){
					item.setPopularityGroup(popularityGroup);
				}
				item.setProductSite(productSite);
				addToItems(item);
			}
		}else if(item!=null){
			getItems().remove(item);
		}
		// 计算商品数量
		calTotalItem();
	}
	
	
	public CartItem  extensionItem(Product product,ProductFashion productFashion,PopularityGroup popularityGroup){
		CartItem item = null;
		if(productFashion!=null){
			item = findItemfashion(productFashion.getId(),popularityGroup);
		}else{
			item = findItem(product.getId(),popularityGroup);
		}
		return item;
	}
	
	public int extensionCount(Product product,ProductFashion productFashion,PopularityGroup popularityGroup,int count, boolean isAdd){
		CartItem item =  extensionItem(product,productFashion,popularityGroup);
		if(item!=null){
			if (isAdd) {
				count += item.getCount();
			}
		}
//		if(productFashion!=null){
//			if (count > productFashion.getStockCount()) {
//				count = productFashion.getStockCount();
//			}
//		}else{
//			if (count > product.getStockCount()) {
//				count = product.getStockCount();
//			}
//		}
		return count;
	}

	public void addGift(Gift gift, int count, boolean isAdd) {
		CartGift gifts = findGift(gift.getId());
		if (gifts != null) {
			if (isAdd) {
				count += gifts.getCount();
			}
			// 小于0则清除
			if (count <= 0) {
				getGifts().remove(gifts);
			} else {
				if (count > gift.getGiftStock()) {
					count = gift.getGiftStock();
				}
				gifts.setCount(count);
			}
		} else {
			if (count > 0) {
				if (count > gift.getGiftStock()) {
					count = gift.getGiftStock();
				}
				gifts = new CartGift();
				gifts.setGift(gift);
				gifts.setCount(count);
				addToGift(gifts);
			}
		}
		// 计算礼品数量
		calTotalGift();
	}

	/**
	 * 通过商品ID查找订单项 TODO 增加规格，如颜色、尺寸等。
	 * 
	 * @param productId
	 *            商品ID
	 * @return 如果没有找到，则返回null
	 */
	public CartItem findItem(Long productId,PopularityGroup popularityGroup) {
		Set<CartItem> items = getItems();
		if (items != null && items.size() > 0) {
			if(popularityGroup!=null){
				for (CartItem item : items) {
						if(item.getPopularityGroup()!=null){
							if (item.getProduct().getId().equals(productId)&&item.getPopularityGroup().getId().equals(popularityGroup.getId())) {
								return item;
							}
						}
				}
			}else{
				for (CartItem item : items) {
						if (item.getProduct().getId().equals(productId)&&item.getPopularityGroup()==null) {
							return item;
						}
				}
			}	
		}
		return null;
	}
	public CartItem findItemfashion(Long productFashId,PopularityGroup popularityGroup) {//王泽武(增加了商品款式)
		Set<CartItem> items = getItems();
		if (items != null && items.size() > 0) {
			if(popularityGroup!=null){
				for (CartItem item : items) {
					if(item.getProductFash()!=null&&item.getPopularityGroup()!=null){
						if (item.getProductFash().getId().equals(productFashId)&&item.getPopularityGroup().getId().equals(popularityGroup.getId())) {
							return item;
						}		
					}
				}
			}else{
				for (CartItem item : items) {
					if(item.getProductFash()!=null){
						if (item.getProductFash().getId().equals(productFashId)&&item.getPopularityGroup()==null) {
							return item;
						}
					}
				}
			}
		}
		return null;
	}
	
	public CartGift findGift(Long giftId) {
		Set<CartGift> gifts = this.getGifts();
		if (gifts != null && gifts.size() > 0) {
			for (CartGift gift : gifts) {
				if (gift.getGift().getId().equals(giftId)) {
					return gift;
				}
			}
		}
		return null;
	}
	
	public void addToGift(CartGift item) {
		Set<CartGift> items = getGifts();
		if (items == null) {
			items = new LinkedHashSet<CartGift>();
			setGifts(items);
		}
		item.setWebsite(getWebsite());
		item.setCart(this);
		items.add(item);
	}

	public void addToItems(CartItem item) {
		Set<CartItem> items = getItems();
		if (items == null) {
			items = new LinkedHashSet<CartItem>();
			setItems(items);
		}
		item.setWebsite(getWebsite());
		item.setCart(this);
		items.add(item);
	}
	
	public List<PopularityGroup> getPopularits(){
		List<PopularityGroup> list = new ArrayList();
		Set<CartItem> items = getItems();
		if(items!=null){
			for(CartItem item:items){
				if(item.getPopularityGroup()!=null){
					if(!list.contains(item.getPopularityGroup())){
						list.add(item.getPopularityGroup());
					}
				}
			}
		}

		return list;	
	}
	

	public void init() {
		if (getTotalItems() == null) {
			setTotalItems(0);
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Cart () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Cart (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Cart (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.Integer totalItems) {

		super (
			id,
			website,
			totalItems);
	}

	/* [CONSTRUCTOR MARKER END] */

}
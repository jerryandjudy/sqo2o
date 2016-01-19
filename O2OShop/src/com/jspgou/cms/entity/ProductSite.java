package com.jspgou.cms.entity;

import static com.jspgou.common.web.Constants.SPT;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseProductSite;
import com.jspgou.cms.web.threadvariable.GroupThread;

/**
 * 商品实体类
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
 */
public class ProductSite extends BaseProductSite {
	private static final long serialVersionUID = 1L;


	/* [CONSTRUCTOR MARKER BEGIN] */
	public ProductSite () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductSite (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductSite (
			java.lang.Long id,
			com.jspgou.cms.entity.Product product,
			com.jspgou.core.entity.Website website,
			java.lang.Double marketPrice,
			java.lang.Double salePrice,
			java.lang.Double costPrice,
			java.lang.Long viewCount,
			java.lang.Integer saleCount,
			java.lang.Integer stockCount,
			java.util.Date createTime,
			java.lang.Boolean special,
			java.lang.Boolean recommend,
			java.lang.Boolean hotsale,
			java.lang.Boolean newProduct,
			java.lang.Boolean onSale) {

		super (
				 id,
				product,
				website,
				marketPrice,
				salePrice,
				costPrice,
				viewCount,
				saleCount,
				stockCount,
				createTime,
				special,
				recommend,
				hotsale,
				newProduct,
				onSale);
	}

	/* [CONSTRUCTOR MARKER END] */
	public Double getLr(){
		return getMarketPrice()-getCostPrice();
	}
	
	public Double getPtFcs(){
		BigDecimal bd = new BigDecimal(getLr()*getPtFc()/(getDlsFc()+getPtFc()));  
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() ;
	}
	public Double getDlsFcs(){
		return getLr()-getPtFcs();
	}
	
	
	/*public Boolean getHasActivity(){
		Boolean flag=false;
		Set<Activitys> set = super.getActivitys();
		if(!set.isEmpty()){
			for (Activitys bean : set) {  
				if(bean.getIsusing() &&new Date().getTime()>bean.getBeginTime().getTime()&& new Date().getTime()<=bean.getEndTime().getTime()){
					flag=true;
					break;
				}
			} 
	
		}
		return  flag;
	}*/
	/**
	 * 获得URL访问地址
	 * 
	 * @return
	 */
	public String getUrl() {
		return getWebsite().getUrlBuff(false).append(SPT).append(
				getProduct().getCategory().getPath()).append(SPT).append(getId()).append(
				getWebsite().getSuffix()).toString();
	}
	/**
	 * 获得会员价
	 * 
	 * @return
	 */
	public Double getMemberPrice() {
		ShopMemberGroup group = GroupThread.get();
		if (group == null) {
			return getSalePrice();
		}
		return getMemberPrice(group);
	}

	/**
	 * 获得会员价
	 * 
	 * @param group
	 *            会员组
	 * @return
	 */
	public Double getMemberPrice(ShopMemberGroup group) {
//		return getSalePrice().multiply(new BigDecimal(group.getDiscount()))
//				.divide(new BigDecimal(100));
//		return getSalePrice()*group.getDiscount()/100;
		return getSalePrice(); //暂时不需要会员价格
	}
}
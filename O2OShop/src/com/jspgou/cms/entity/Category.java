package com.jspgou.cms.entity;

import static com.jspgou.cms.Constants.TPLDIR_CATEGORY;
import static com.jspgou.cms.Constants.TPLDIR_PRODUCT;
import static com.jspgou.common.web.Constants.SPT;
import static com.jspgou.core.web.front.URLHelper.INDEX;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.jspgou.cms.entity.base.BaseCategory;
import com.jspgou.common.hibernate3.HibernateTree;
import com.jspgou.common.hibernate3.PriorityComparator;
import com.jspgou.common.hibernate3.PriorityInterface;
import com.jspgou.core.entity.Website;

/**
 * 产品类别实体类
 * 
 * @author liufang
* This class should preserve.
* @preserve
 */
public class Category extends BaseCategory implements HibernateTree,
		PriorityInterface {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得URL
	 * 
	 * @return
	 */
	public String getUrl() {
		 StringBuilder stringbuilder = new StringBuilder();
		 Website websites = this.getWebsite();
	        if(websites.getContextPath()!=null){
	        stringbuilder.append(websites.getContextPath());
	        }
		return stringbuilder.append(SPT).append(getPath())
				.append(SPT).append(INDEX).append(getWebsite().getSuffix())
				.toString();
	}

	/**
	 * 获得栏目页模板
	 */
	public String getTplChannelRel() {
		String tpl = getTplChannel();
		if (StringUtils.isBlank(tpl)) {
			tpl = getType().getChannelPrefix();
			return getWebsite().getTemplate(TPLDIR_CATEGORY, tpl);
		}
		return getWebsite().getTemplateRel(tpl);
	}

	/**
	 * 获得内容页模板
	 */
	public String getTplContentRel() {
		String tpl = getTplContent();
		if (StringUtils.isBlank(tpl)) {
			tpl = getType().getContentPrefix();
			return getWebsite().getTemplate(TPLDIR_PRODUCT, tpl);
		}
		return getWebsite().getTemplateRel(tpl);//  tpl="/shop/develop/content_商品页.html"
	}

	/**
	 * 获得顶级分类
	 * 
	 * @return
	 */
	public Category getTopCategory() {
		Category ctg = this;
		Category parent = ctg.getParent();
		while (parent != null) {
			ctg = parent;
			parent = parent.getParent();
		}
		return ctg;
	}

	/**
	 * 获得类别列表。从顶级类别到自身。
	 * 
	 * @return
	 */
	public List<Category> getCategoryList() {
		List<Category> list = new LinkedList<Category>();
		Category ctg = this;
		while (ctg != null) {
			list.add(0, ctg);
			ctg = ctg.getParent();
		}
		return list;
	}

	/**
	 * 获得类别深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		Category parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}

	/**
	 * 是否末级类别
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		Set<Category> set = getChild();
		return set != null && set.size() > 0;
	}

	/**
	 * 添加至child
	 * 
	 * @param category
	 */
	public void addToChild(Category category) {
		Set<Category> set = getChild();
		if (set == null) {
			set = new TreeSet<Category>(PriorityComparator.INSTANCE);
			setChild(set);
		}
		set.add(category);
	}

	/**
	 * 从child中删除
	 * 
	 * @param category
	 */
	public void removeFromChild(Category category) {
		Set<Category> set = getChild();
		if (set != null) {
			set.remove(category);
		}
	}

	/**
	 * 每个站点各自维护独立的树结构
	 * 
	 * @see HibernateTree#getTreeCondition()
	 */
	public String getTreeCondition() {
		return "bean.website.id=" + getWebsite().getId();
	}

	/**
	 * @see HibernateTree#getParentId()
	 */
	public Long getParentId() {
		Category parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得品牌ID数组(wang ze wu)
	 * 
	 * @return
	 */
	public Long[] getBrandIds() {
		Set<Brand> set = getBrands();
		if (set == null) {
			return null;
		} else {
			Long[] ids = new Long[set.size()];
			int i = 0;
			for (Brand brand : set) {
				ids[i] = brand.getId();
				i++;
			}
			return ids;
		}
	}
	
	/**
	 * 添加至Brands
	 * 
	 * @param brand
	 *            待添加品牌
	 */
	public void addToBrands(Brand brand) {
		Set<Brand> set = getBrands();
		if (set == null) {
			set = new HashSet<Brand>();
			setBrands(set);
		}
		set.add(brand);
		brand.addToCategory(this);
	}
	
	
	/**
	 * 添加至StandardType
	 * 
	 * @param sType
	 *            待添加规则类型
	 */
	public void addToStandardTypes(StandardType sType) {
		Set<StandardType> set = getStandardType();
		if (set == null) {
			set = new HashSet<StandardType>();
			setStandardType(set);
		}
		set.add(sType);
		sType.addToCategory(this);
	}
	
	/**
	 * 获得规格组ID数组(wang ze wu)
	 * 
	 * @return
	 */
	public Long[] getStandardTypeIds() {
		Set<StandardType> set =getStandardType();
		if (set == null) {
			return null;
		} else {
			Long[] ids = new Long[set.size()];
			int i = 0;
			for (StandardType st : set) {
				ids[i] = st.getId();
				i++;
			}
			return ids;
		}
	}
	

	/**
	 * @see HibernateTree#getLftName()
	 */
	public String getLftName() {
		return DEF_LEFT_NAME;
	}

	/**
	 * @see HibernateTree#getParentName()
	 */
	public String getParentName() {
		return DEF_PARENT_NAME;
	}

	/**
	 * @see HibernateTree#getRgtName()
	 */
	public String getRgtName() {
		return DEF_RIGHT_NAME;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Category () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Category (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Category (
		java.lang.Long id,
		com.jspgou.cms.entity.ProductType type,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.String path,
		java.lang.Integer lft,
		java.lang.Integer rgt,
		java.lang.Float ptFc,
		java.lang.Float dlsFc,
		java.lang.Integer priority) {

		super (
			id,
			type,
			website,
			name,
			path,
			lft,
			rgt,
			
			ptFc,
			dlsFc,
			
			priority);
	}

	/* [CONSTRUCTOR MARKER END] */

}
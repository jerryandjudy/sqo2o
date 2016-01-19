package com.jspgou.cms.entity;

import static com.jspgou.common.web.Constants.SPT;
import static com.jspgou.core.web.front.URLHelper.INDEX;
import static com.jspgou.cms.Constants.SHOP_SYS;
import static com.jspgou.cms.Constants.TPLDIR_ARTICLE;
import static com.jspgou.cms.Constants.TPLDIR_CHANNEL;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.jspgou.common.hibernate3.HibernateTree;
import com.jspgou.common.hibernate3.PriorityComparator;
import com.jspgou.common.hibernate3.PriorityInterface;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.base.BaseShopChannel;
/**
* This class should preserve.
* @preserve
*/
public class ShopChannel extends BaseShopChannel implements HibernateTree,
		PriorityInterface {
	private static final long serialVersionUID = 1L;
	/**
	 * 单页栏目
	 */
	public static final int ALONE = 1;
	/**
	 * 文章栏目
	 */
	public static final int ARTICLE = 2;
	/**
	 * 外部链接
	 */
	public static final int OUTER_URL = 3;
	/**
	 * 栏目页模板前缀
	 */
	public static final String CHANNEL_SUFFIX = "栏目";
	/**
	 * 内容页模板前缀
	 */
	public static final String CONTENT_SUFFIX = "内容";
	/**
	 * 单页模板前缀
	 */
	public static final String ALONE_SUFFIX = "单页";

	/**
	 * 获得栏目模板目录的相对路径
	 * 
	 * @return
	 */
	public static String getChannelTplDirRel(Website web) {
		return web.getTemplateRelBuff().append(SPT).append(TPLDIR_CHANNEL).toString();
	}
	
	/**
	 * 获得内容模板目录的相对路径
	 * 
	 * @return
	 */
	public static String getContentTplDirRel(Website web) {
		return web.getTemplateRelBuff().append(SPT).append(TPLDIR_ARTICLE).toString();
	}

	/**
	 * 获得栏目模板列表
	 * 
	 * @param realChannelDir
	 * @param relChannelPath
	 * @return
	 */
	public static String[] getChannelTpls(Integer type, String realChannelDir,
			String relChannelPath) {
		String prefix = getPrefix(type, true);
		if (prefix != null) {
			return ProductType.getTpls(realChannelDir, relChannelPath, prefix);
		} else {
			return null;
		}
	}

	/**
	 * 获得内容模板列表
	 * 
	 * @param realContentDir
	 * @param relContentPath
	 * @return
	 */
	public static String[] getContentTpls(Integer type, String realContentDir,
			String relContentPath) {
		String prefix = getPrefix(type, false);
		if (prefix != null) {
			return ProductType.getTpls(realContentDir, relContentPath, prefix);
		} else {
			return null;
		}
	}

	/**
	 * 获得模板前缀
	 * 
	 * @param type
	 * @param isChannel
	 * @return
	 */
	public static String getPrefix(Integer type, boolean isChannel) {
		if (type == null) {
			throw new IllegalStateException("ShopChannle type connot be null");
		}
		if (type == 1) {
			return ALONE_SUFFIX;
		} else if (type == 2) {
			return isChannel ? CHANNEL_SUFFIX : CONTENT_SUFFIX;
		} else {
			return null;
		}
	}



	/**
	 * 获得栏目页模板
	 */
	public String getTplChannelRel() {
		String tpl = getTplChannel();
		if (StringUtils.isBlank(tpl)) {
			String suffix = getPrefix(getType(), true);
			if (suffix != null) {
				return getWebsite().getTemplate(TPLDIR_CHANNEL, CHANNEL_SUFFIX);
			} else {
				return null;
			}
		}
		return getWebsite().getTemplateRel(tpl);
	}

	/**
	 * 获得内容页模板
	 */
	public String getTplContentRel() {
		String tpl = getTplContent();
		if (StringUtils.isBlank(tpl)) {
			String suffix = getPrefix(getType(), false);

			if (suffix != null) {
				return getWebsite().getTemplate(TPLDIR_ARTICLE, CONTENT_SUFFIX);
			} else {
				return null;
			}
		}
		return getWebsite().getTemplateRel(tpl);
	}

	/**
	 * 获得URL
	 * 
	 * @return
	 */
	public String getUrl() {
		int type = getType();
		if (type == OUTER_URL) {
			String url = getOuterUrl();
			if (StringUtils.isBlank(url)) {
				throw new IllegalStateException(
						"ShopChannel outerUrl cannot be blank while type is OUTER_URL, ID="
								+ getId());
			}
			if (url.startsWith(SPT)) {
				return getWebsite().getUrlBuff(false).append(url).toString();
			} else if (url.startsWith("http")) {
				return url;
			} else {
				return "http://" + url;
			}
		} else if (type == ALONE) {
			return getWebsite().getUrlBuff(false).append(SPT).append(getPath())
					.append(getWebsite().getSuffix()).toString();
		} else if (type == ARTICLE) {
			return getWebsite().getUrlBuff(false).append(SPT).append(getPath())
					.append(SPT).append(INDEX).append(getWebsite().getSuffix())
					.toString();
		} else {
			throw new IllegalStateException(
					"ShopChannel type not supported: id=" + getId() + " type="
							+ type);
		}
	}

	/**
	 * 获得内容
	 * 
	 * @return
	 */
	public String getContent() {
		ShopChannelContent content = getChannelContent();
		if (content != null) {
			return content.getContent();
		} else {
			return null;
		}
	}

	/**
	 * 获得节点深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		ShopChannel parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}
	
	/**
	 * 添加至child
	 * 
	 * @param category
	 */
	public void addToChild(ShopChannel shopChannel) {
		Set<ShopChannel> set = getChild();
		if (set == null) {
			set = new TreeSet<ShopChannel>(PriorityComparator.INSTANCE);
			setChild(set);
		}
		set.add(shopChannel);
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
		ShopChannel parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
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
	public ShopChannel() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopChannel(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopChannel(java.lang.Long id,
			com.jspgou.core.entity.Website website, java.lang.Integer lft,
			java.lang.Integer rgt, java.lang.Integer type,
			java.lang.String name, java.lang.Integer priority) {

		super(id, website, lft, rgt, type, name, priority);
	}

	/* [CONSTRUCTOR MARKER END] */
}
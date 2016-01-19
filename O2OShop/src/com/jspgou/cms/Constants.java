package com.jspgou.cms;
 
/**
 * JspGou常量
 * 
 * @author liufang
 * @preserve
 */
public abstract class Constants extends com.jspgou.core.web.Constants {
	
	public static final String REQUEST_SHOP_CONFIG_KEY = "_shop_config_key";
	/**
	 * 首页模板
	 */
	public static final String TPLDIR_INDEX = "index";
	/**
	 * 商城系统
	 */
	public static final String SHOP_SYS = "shop";
	/**
	 * 商城栏目模板
	 */
	public static final String TPLDIR_CHANNEL = "channel";
	/**
	 * 商城内容模板
	 */
	public static final String TPLDIR_ARTICLE = "article";
	/**
	 * 商城类别模板
	 */
	public static final String TPLDIR_CATEGORY = "category";
	/**
	 * 商城模板
	 */
	public static final String TPLDIR_PRODUCT = "product";
	/**
	 * 会员系统
	 */
	public static final String MEMBER_SYS = "member";
	/**
	 * 积分商城
	 */
	public static final String GIFT = "gift";
	/**
	 * 公用模板
	 */
	public static final String COMMON = "common";
	/**
	 * 静态系统
	 */
	public static final String STATIC_SYS = "static";
	/**
	 * 标签模板
	 */
	public static final String TPLDIR_TAG = "tag";
	/**
	 * 系统名称数组
	 */
	public static final String[] SYSTEMS = { SHOP_SYS, MEMBER_SYS, COMMON_SYS };
	/**
	 * Lucene索引文件存放地址
	 */
	public static final String LUCENE_JSPGOU = "/WEB-INF/lucene";
	/**
	 * 模板后缀
	 */
	public static final String TPL_SUFFIX = ".html";
	/**
	 * 列表样式模板
	 */
	public static final String TPLDIR_STYLE_LIST = "style_list";
	/**
	 * 列表样式模板
	 */
	public static final String TPLDIR_STYLE_PAGE = "style_page";
	/**
	 * 列表分页模板路径
	 */
	public static final String TPL_STYLE_PAGE_CHANNEL = "/WEB-INF/t/gou_sys_defined/style_page/channel_";
	
	/**
	 * 内容分页模板路径
	 */
	public static final String TPL_STYLE_PAGE_CONTENT = "/WEB-INF/t/gou_sys_defined/style_page/content_";
	/**
	 * 页码
	 */
	public static final String PAGE_NO = "pageNo";
	/**
	 * 页面翻页地址
	 */
	public static final String HREF = "href";
	/**
	 * href前半部（相对于分页）
	 */
	public static final String HREF_FORMER = "hrefFormer";
	/**
	 * href后半部（相对于分页）
	 */
	public static final String HREF_LATTER = "hrefLatter";
	/**
	 * href后半部（相对于分页）
	 */
	public static final String COUPON = "coupon";
	/**
	 * 上传路径
	 */
	public static final String UPLOAD_PATH = "/u/cms/";
	/**
	 * 截图路径
	 */
	public static final String SNAP_PATH = "/u/cms/snap";
	/**
	 * 上传路径
	 */
	public static final String LIBRARY_PATH = "/wenku/";
	/**
	 * 文库上传文件后缀
	 */
	public static final String LIBRARY_SUFFIX = "odt,txt,pdf,wps,et,dps,vsd,pot,pps,rtf,doc,docx,xls,xlsx,ppt,pptx";
	/**
	 * 资源路径
	 */
	public static final String RES_PATH = "/r/cms";
}

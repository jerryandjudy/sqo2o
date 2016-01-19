package com.jspgou.cms.lucene;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.cms.action.directive.abs.WebDirective;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
* This class should preserve.
* @preserve
*/
public abstract class LuceneDirectiveAbstract extends WebDirective implements TemplateDirectiveModel {
	/**
	 * 输入参数，搜索关键字
	 */
	public static final String PARAM_QUERY = "q";
	/**
	 * 输入参数，站点ID
	 */
	public static final String PARAM_WEBSITE_ID = "websiteId";
	/**
	 * 输入参数，栏目ID
	 */
	public static final String PARAM_PTYPE_ID = "ptypeId";
	/**
	 * 输入参数，开始日期
	 */
	public static final String PARAM_START_DATE = "startDate";
	/**
	 * 输入参数，结束日期
	 */
	public static final String PARAM_END_DATE = "endDate";
	
	/**
	 * 输入参数，父类别ID。null or Long
	 */
	public static final String CTG_ID = "ctgId";
	
	/**
	 * 输入参数，TAG ID。null or Long
	 */
	public static final String PARAM_TAG_ID = "tagId";
	/**
	 * 输入参数，是否推荐。null or 0 or 1
	 */
	public static final String PARAM_RECOMMEND = "recommend";
	/**
	 * 输入参数，是否特价。null or 0 or 1
	 */
	public static final String PARAM_SPECIAL = "special";
	
	public static final String BRAND_ID ="brandId";
	

	protected String getQuery(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getString(PARAM_QUERY, params);
	}

	protected Long getWebSiteId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getLong(PARAM_WEBSITE_ID, params);
	}

	protected Long getPtypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getLong(PARAM_PTYPE_ID, params);
	}

	protected Date getStartDate(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getDate(PARAM_START_DATE, params);
	}

	protected Date getEndDate(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getDate(PARAM_END_DATE, params);
	}
	
	protected Long getCtgId(Map<String, TemplateModel> params)
	         throws TemplateException {
          return getLong(CTG_ID, params);
    }
	
	protected Long getBrandId(Map<String, TemplateModel> params)
    		throws TemplateException {
		Long brandId = getLong(BRAND_ID, params);
		if(brandId==0){
			brandId = null;
		}
		 return brandId;
	}
	

    protected Long getTagId(Map<String, TemplateModel> params)
	         throws TemplateException {
          return getLong(PARAM_TAG_ID, params);
    }

    protected Boolean isRecommend(Map<String, TemplateModel> params)
 	        throws TemplateException {
         return getBool(PARAM_RECOMMEND, params);
    }

    protected Boolean isSpecial(Map<String, TemplateModel> params)
	       throws TemplateException {
       return getBool(PARAM_SPECIAL, params);
    }

    protected Boolean isHostSale(Map<String, TemplateModel> params)
	       throws TemplateException {
       return getBool("hostSale", params);
    }

    protected Boolean isNewProduct(Map<String, TemplateModel> params)
	       throws TemplateException {
       return getBool("newProduct", params);
     }
    
	protected void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}
}

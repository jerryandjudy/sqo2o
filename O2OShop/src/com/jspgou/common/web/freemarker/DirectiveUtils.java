package com.jspgou.common.web.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.util.*;

import org.apache.commons.lang.StringUtils;

import com.jspgou.common.web.springmvc.DateTypeEditor;
/**
* This class should preserve.
* @preserve
*/
public abstract class DirectiveUtils{

	/**
	 * 将params的值复制到variable中
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws TemplateException
	 */
    	public static Map<String, TemplateModel> addParamsToVariable(
    			Environment env, Map<String, TemplateModel> params)
    			throws TemplateException {
    	Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
    }

    /**
     * 将variable中的params值移除
     * 
     * @param env
     * @param params
     * @param origMap
     * @throws TemplateException
     */
    public static void removeParamsFromVariable(Environment env,
    			Map<String, TemplateModel> params,
    			Map<String, TemplateModel> origMap) throws TemplateException {
    		if (params.size() <= 0) {
    			return;
    		}
    		for (String key : params.keySet()) {
    			env.setVariable(key, origMap.get(key));
    		}
    }

    public static boolean getBoolean(TemplateScalarModel templatescalarmodel)
        throws TemplateModelException {
        return "1".equals(templatescalarmodel.getAsString());
    }
    
	public static String getString(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		} else {
			throw new MustStringException(name);
		}
	}
	public static Long getLong(String name, 
			Map<String, TemplateModel> params)throws TemplateException {
          TemplateModel model = params.get(name);
          if (model == null) {
	         return null;
          }
          if (model instanceof TemplateScalarModel) {
	          String s = ((TemplateScalarModel) model).getAsString();
	          if (StringUtils.isBlank(s)) {
		        return null;
	          }
	      try {
		        return Long.parseLong(s);
	       } catch (NumberFormatException e) {
	          	throw new MustNumberException(name);
	        }
        } else if (model instanceof TemplateNumberModel) {
             return ((TemplateNumberModel) model).getAsNumber().longValue();
        } else {
	            throw new MustNumberException(name);
       }
    }
	
	public static Integer getInt(String name, 
			  Map<String, TemplateModel> params) throws TemplateException {
           TemplateModel model = params.get(name);
           if (model == null) {
	           return null;
           }
           if (model instanceof TemplateScalarModel) {
	         String s = ((TemplateScalarModel) model).getAsString();
	       if (StringUtils.isBlank(s)) {
		        return null;
	       }
	       try {
		         return Integer.parseInt(s);
	           } catch (NumberFormatException e) {
		       throw new MustNumberException(name);
	       }
          } else if (model instanceof TemplateNumberModel) {
	           return ((TemplateNumberModel) model).getAsNumber().intValue();
         } else {
	          throw new MustNumberException(name);
         }
     }
	
	public static Date getDate(String name, 
			Map<String, TemplateModel> params)throws TemplateException {
         TemplateModel model = params.get(name);
         if (model == null) {
	         return null;
         }
         if (model instanceof TemplateDateModel) {
	          return ((TemplateDateModel) model).getAsDate();
         } else if (model instanceof TemplateScalarModel) {
	             DateTypeEditor editor = new DateTypeEditor();
	             editor.setAsText(((TemplateScalarModel) model).getAsString());
	             return (Date) editor.getValue();
         } else {
	         throw new MustDateException(name);
        }
   }
}

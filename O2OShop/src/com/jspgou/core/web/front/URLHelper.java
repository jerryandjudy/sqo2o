package com.jspgou.core.web.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UrlPathHelper;

/**
 * URI帮助类
 * 
 * @author liufang
* This class should preserve.
* @preserve
*/
public class URLHelper{
    public static final String INDEX = "index";
    
    public static class URLInfo{
    	
        private String paths[];
        private int pageNo;
        private String params[];
        private String queryString;
        private String urlPrefix;
        private String urlSuffix;

        public URLInfo(String[] paths, int pageNo, String[] params, 
        		String urlPrefix, String urlSuffix, String queryString){
            this.paths = paths;
            this.pageNo =pageNo;
            this.params = params;
            this.urlPrefix = urlPrefix;
            this.urlSuffix = urlSuffix;
            this.queryString = queryString;
        }

        public String[] getPaths(){
            return paths;
        }

        public void setPaths(String[] paths){
            this.paths = paths;
        }

        public int getPageNo(){
            return pageNo;
        }

        public void setPageNo(int pageNo){
            this.pageNo = pageNo;
        }

        public String[] getParams(){
            return params;
        }

        public void setParams(String[] params){
            this.params = params;
        }

        public String getUrlPrefix(){
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix){
            this.urlPrefix = urlPrefix;
        }

        public String getUrlSuffix(){
            return urlSuffix;
        }

        public void setUrlSuffix(String urlSuffix){
            this.urlSuffix = urlSuffix;
        }

        public String getQueryString(){
            return queryString;
        }

        public void setQueryString(String queryString){
            this.queryString = queryString;
        }
    }

    public static String removeSuffix(String s) {
        if(StringUtils.isBlank(s)){
            return s;
        }
        int i = s.lastIndexOf("/");
        if(i < 0){ i = 0; }
        return s.substring(0, s.indexOf(".", i));
    }

    public static boolean isIndex(String s){
        return false;
    }
    
	/**
	 * 获得页号
	 * 
	 * @param request
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request) {
		return getPageNo(getURI(request));
	}
	
	private static String getURI(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctx = helper.getOriginatingContextPath(request);
		if (!StringUtils.isBlank(ctx)) {
			return uri.substring(ctx.length());
		} else {
			return uri;
		}
	}
	
	/**
	 * 获得翻页信息
	 * 
	 * @param request
	 * @return
	 */
	public static PageInfo getPageInfo(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String queryString = helper.getOriginatingQueryString(request);
		return getPageInfo(uri, queryString);
	}
	
	/**
	 * 获得URL信息
	 * 
	 * @param uri
	 *            URI {@link HttpServletRequest#getRequestURI()}
	 * @param queryString
	 *            查询字符串 {@link HttpServletRequest#getQueryString()}
	 * @return
	 */
	public static PageInfo getPageInfo(String uri, String queryString) {
		if (uri == null) {
			return null;
		}
		if (!uri.startsWith("/")) {
			throw new IllegalArgumentException("URI must start width '/'");
		}
		int bi = uri.indexOf("_");
		int mi = uri.indexOf("-");
		int pi = uri.indexOf(".");
		int lastSpt = uri.lastIndexOf("/") + 1;
		String url;
		if (!StringUtils.isBlank(queryString)) {
			url = uri + "?" + queryString;
		} else {
			url = uri;
		}
		// 翻页前半部
		String urlFormer;
		if (bi != -1) {
			urlFormer = uri.substring(lastSpt, bi);
		} else if (mi != -1) {
			urlFormer = uri.substring(lastSpt, mi);
		} else if (pi != -1) {
			urlFormer = uri.substring(lastSpt, pi);
		} else {
			urlFormer = uri.substring(lastSpt);
		}
		// 翻页后半部
		String urlLater;
		if (mi != -1) {
			urlLater = url.substring(mi);
		} else if (pi != -1) {
			urlLater = url.substring(pi);
		} else {
			urlLater = url.substring(uri.length());
		}
		String href = url.substring(lastSpt);
		return new PageInfo(href, urlFormer, urlLater);
	}
	
	/**
	 * 获得页号
	 * 
	 * @param uri
	 *            URI {@link HttpServletRequest#getRequestURI()}
	 * @return
	 */
	public static int getPageNo(String uri) {
		if (uri == null) {
			throw new IllegalArgumentException("URI can not be null");
		}
		if (!uri.startsWith("/")) {
			throw new IllegalArgumentException("URI must start width '/'");
		}
		int pageNo = 1;
		int bi = uri.indexOf("_");
		int mi = uri.indexOf("-");
		int pi = uri.indexOf(".");
		if (bi != -1) {
			String pageNoStr;
			if (mi != -1) {
				pageNoStr = uri.substring(bi + 1, mi);
			} else {
				if (pi != -1) {
					pageNoStr = uri.substring(bi + 1, pi);
				} else {
					pageNoStr = uri.substring(bi + 1);
				}
			}
			try {
				pageNo = Integer.valueOf(pageNoStr);
			} catch (Exception e) {
			}
		}
		return pageNo;
	}
	
	/**
	 * URI信息
	 * 
	 * @author liufang
     * This class should preserve.
     * @preserve
    */
	public static class PageInfo {
		/**
		 * 页面地址
		 */
		private String href;
		/**
		 * href前半部（相对于分页）
		 */
		private String hrefFormer;
		/**
		 * href后半部（相对于分页）
		 */
		private String hrefLatter;

		public PageInfo(String href, String hrefFormer, String hrefLatter) {
			this.href = href;
			this.hrefFormer = hrefFormer;
			this.hrefLatter = hrefLatter;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String getHrefFormer() {
			return hrefFormer;
		}

		public void setHrefFormer(String hrefFormer) {
			this.hrefFormer = hrefFormer;
		}

		public String getHrefLatter() {
			return hrefLatter;
		}

		public void setHrefLatter(String hrefLatter) {
			this.hrefLatter = hrefLatter;
		}
	}

	/**
	 * 
     * This class should preserve.
     * @preserve
     */
    public static URLInfo getURLInfo(String s, String s1){
        if(s == null) return null;
        int i = 0;
        if(s.startsWith("http://")){
            i += s.indexOf("/", "http://".length());
        }
        if(!StringUtils.isBlank(s1)){
            i += s1.length();
        }
        if(s.startsWith("/", i)){
            i++;
        }
        String s2 = null;
        int j = s.indexOf("?");
        int k = s.indexOf(".", i);
        if(j >= 0){
            s2 = s.substring(j + 1);
        }
        String s3;
        if(k >= 0){
            s3 = s.substring(i, k);
        }else{
             if(j >= 0){
            s3 = s.substring(i, j);
             }else{
            s3 = s.substring(i);
             }
        }
        int l = s3.lastIndexOf("/") + 1 + i;
        int i1 = s3.indexOf("_");
        int j1 = s3.indexOf("-");
        String s4;
        String s5;
        String s6;
        String s7;
        String s8;
        if(i1 >= 0){
            s4 = s3.substring(0, i1);
            s7 = s.substring(l, i1 + i);
            if(j1 >= 0){
                s6 = s3.substring(j1 + 1);
                s5 = s3.substring(i1 + 1, j1);
                s8 = s.substring(j1 + i);
            } else{
                s6 = null;
                s5 = s3.substring(i1 + 1);
                if(k >= 0)
                    s8 = s.substring(k);
                else
                    s8 = s.substring(i1 + i + 1);
            }
        } else {
            s5 = null;
            if(j1 >= 0){
                s4 = s3.substring(0, j1);
                s6 = s3.substring(j1 + 1);
                s7 = s.substring(l, j1 + i);
                s8 = s.substring(j1 + i);
            } else{
                s4 = s3;
                s6 = null;
                if(k >= 0){
                    s7 = s.substring(l, k);
                    s8 = s.substring(k);
                } else{
                    if(j >= 0){
                       s7 = s.substring(l, j);
                       s8 = s.substring(j);
                    } else{
                       s7 = s.substring(l);
                       s8 = "";
                    }
                }
            }
        }
        String as[];
        if(!StringUtils.isBlank(s4)){
            as = s4.split("/");
        }else{
            as = new String[0];
        }
        int k1;
        try{
            k1 = Integer.parseInt(s5);
        }catch(NumberFormatException e){
            k1 = 1;
        }
        String as1[];
        if(s6 != null){
            as1 = s6.split("-");
        }else{
            as1 = new String[0];
        }
        return new URLInfo(as, k1, as1, s7, s8, s2);
    }

}

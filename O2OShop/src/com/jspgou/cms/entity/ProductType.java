package com.jspgou.cms.entity;

import static com.jspgou.common.web.Constants.SPT;
import static com.jspgou.cms.Constants.TPLDIR_CATEGORY;
import static com.jspgou.cms.Constants.TPLDIR_PRODUCT;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseProductType;

/**
* This class should preserve.
* @preserve
*/
public class ProductType extends BaseProductType {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得模板目录的相对路径
	 * 
	 * @return
	 */
	public String getTplDirRel() {
//		String solution = getWebsite().getSolution(SHOP_SYS);
//		return getWebsite().getTemplateRelBuff().append(SPT).append(SHOP_SYS)
//				.append(SPT).append(solution).toString();
		return getWebsite().getTemplateRelBuff().append(SPT).append(TPLDIR_CATEGORY).toString();
	}
	
	/**
	 * 获得类型模板目录的相对路径
	 * 
	 * @return
	 */
	public String getCtgTplDirRel() {
		return getWebsite().getTemplateRelBuff().append(SPT).append(TPLDIR_CATEGORY).toString();
	}
	
	/**
	 * 获得内容模板目录的相对路径
	 * 
	 * @return
	 */
	public String getTxtTplDirRel() {
		return getWebsite().getTemplateRelBuff().append(SPT).append(TPLDIR_PRODUCT).toString();
	}


	/**
	 * 获得栏目模板列表
	 * 
	 * @param realDir
	 * @param relPath
	 * @return
	 */
	public String[] getChannelTpls(String realDir, String relPath) {
		return getTpls(realDir, relPath, getChannelPrefix());
	}

	/**
	 * 获得内容模板列表
	 * 
	 * @param realDir
	 * @param relPath
	 * @return
	 */
	public String[] getContentTpls(String realDir, String relPath) {
		return getTpls(realDir, relPath, getContentPrefix());
	}

	/**
	 * 获得模板路径
	 * 
	 * @param realDir
	 *            模板目录绝对路径
	 * @param relPath
	 *            模板相对路径
	 * @param prefix
	 *            模板名前缀
	 * @return
	 */
	public static String[] getTpls(String realDir, String relPath,
			final String prefix) {
		File dir = new File(realDir);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				//取.号以前的
				name=name.substring(0, name.indexOf("."));
				return name.endsWith(prefix);
			}
		});
		int len = files.length;
		String[] paths = new String[len];
		for (int i = 0; i < len; i++) {
			paths[i] = relPath + SPT + files[i].getName();
		}
		return paths;
	}

	
	

	public void addToexendeds(Exended exended) {
		Set<Exended> set =  getExendeds();
		if (set == null) {
			set = new HashSet<Exended>();
			setExendeds(set);
		}
		set.add(exended);
		exended.addToProductTypes(this);
	}
	
	
	public void removeFromExendeds(Exended exended) {
		Set<Exended> set =  getExendeds();
		if (set != null) {
			set.remove(exended);
		}
	}


	/* [CONSTRUCTOR MARKER BEGIN] */
	public ProductType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductType (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductType (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.String path,
		java.lang.String channelPrefix,
		java.lang.String contentPrefix,
		java.lang.Boolean refBrand,
		java.lang.Integer detailImgWidth,
		java.lang.Integer detailImgHeight,
		java.lang.Integer listImgWidth,
		java.lang.Integer listImgHeight,
		java.lang.Integer minImgWidth,
		java.lang.Integer minImgHeight) {

		super (
			id,
			website,
			name,
			//path,
			channelPrefix,
			contentPrefix,
			refBrand,
			detailImgWidth,
			detailImgHeight,
			listImgWidth,
			listImgHeight,
			minImgWidth,
			minImgHeight);
	}

	/* [CONSTRUCTOR MARKER END] */

}
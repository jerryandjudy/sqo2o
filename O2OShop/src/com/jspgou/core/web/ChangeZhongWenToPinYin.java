package com.jspgou.core.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Change chinese to pin yin 中文转拼音
 * 
 * @author chengbao_zhu(chanball)
 * 
 */
public class ChangeZhongWenToPinYin {
	// log
	private static Log log = LogFactory.getLog(ChangeZhongWenToPinYin.class);

	/**
	 * 获取拼音
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYin(String zhongwen)
			throws BadHanyuPinyinOutputFormatCombination {
		log.debug("-------->>Input ZhongWen=" + zhongwen);

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
					getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += capitalize(pinYin[0]);
			} else {
				zhongWenPinYin += chars[i];
			}
		}

		log.debug("-------->>Output PinYin=" + zhongWenPinYin);
		return zhongWenPinYin;
	}

	/**
	 * Default Format 默认输出格式
	 * 
	 * @return
	 */
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示

		return format;
	}

	/**
	 * Capitalize 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		char ch[];
		ch = s.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}

}


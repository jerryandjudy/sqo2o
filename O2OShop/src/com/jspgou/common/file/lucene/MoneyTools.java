package com.jspgou.common.file.lucene;

import java.math.BigDecimal;

import org.apache.lucene.document.NumberTools;
import org.springframework.util.Assert;

/**
 * 将BigDecimal类型的金额转换成String类型，便于Lucene搜索。
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
*/
public class MoneyTools{
    private static final Double MULTIPLE = new Double(1000);

	/**
	 * 将money*1000，转换成long，再转换成string。
	 * 
	 * @param money
	 * @return
	 * @see NumberTools#longToString(long)
	 */
    public static String moneyToString(Double bigdecimal){
        Assert.notNull(bigdecimal);
        return NumberTools.longToString((long) (bigdecimal*MULTIPLE));
    }
    
	/**
	 * 将s转换成long，再转换成BigDecimal，除以1000。
	 * 
	 * @param s
	 * @return
	 */
    public static Double stringToMoney(String s){
        Double bigdecimal = new Double(NumberTools.stringToLong(s));
        return bigdecimal/MULTIPLE;
    }
}

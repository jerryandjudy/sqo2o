/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年5月29日
* @author liuwang
* @version 1.0
*/
package com.jspgou.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;






import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.jspgou.cms.web.Excel;

public class ImportExcel {
	/**
	 * 导入 excel
	 * @param file
	 * @param pojoClass
	 * @param pattern
	 * @return
	 */
	public static Collection importExcel(File file ,Class pojoClass) {
		Collection dist = new ArrayList<Object>();
		try {
		// 得到目标目标类的所有的字段列表
		Field filed[] = pojoClass.getDeclaredFields();
		// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
		Map<String,Method> fieldSetMap = new HashMap<String,Method>();
		Map<String,Method> fieldSetConvertMap = new HashMap<String,Method>();
		// 循环读取所有字段
		for (int i = 0; i < filed.length; i++) {
		Field f = filed[i];
		// 得到单个字段上的Annotation
		Excel excel = f.getAnnotation(Excel.class);
		// 如果标识了Annotationd的话
		if (excel != null) {
		// 构造设置了Annotation的字段的Setter方法
		String fieldname = f.getName();
		String setMethodName = "set"
		+ fieldname.substring(0, 1).toUpperCase()
		+ fieldname.substring(1);
		// 构造调用的method，
		Method setMethod = pojoClass.getMethod(setMethodName,
		new Class[] { f.getType() });
		// 将这个method以Annotaion的名字为key来存入。
		//对于重名将导致 覆盖 失败，对于此处的限制需要
		fieldSetMap.put(excel.exportName(), setMethod);
		if(excel.importConvertSign()==1)
		{
		StringBuffer setConvertMethodName = new StringBuffer("set");
		setConvertMethodName.append(fieldname.substring(0, 1)
		.toUpperCase());
		setConvertMethodName.append(fieldname.substring(1));
		setConvertMethodName.append("Convert");
		Method getConvertMethod = pojoClass.getMethod(setConvertMethodName.toString(),
		new Class[] {String.class});
		fieldSetConvertMap.put(excel.exportName(), getConvertMethod);
		}
		}
		}
		// 将传入的File构造为FileInputStream;
		FileInputStream in = new FileInputStream(file);
		// // 得到工作表
		HSSFWorkbook book = new HSSFWorkbook(in);
		// // 得到第一页
		HSSFSheet sheet = book.getSheetAt(0);
		// // 得到第一面的所有行
		Iterator<Row> row = sheet.rowIterator();
		// 得到第一行，也就是标题行
		Row title = row.next();
		// 得到第一行的所有列
		Iterator<Cell> cellTitle = title.cellIterator();
		// 将标题的文字内容放入到一个map中。
		Map titlemap = new HashMap();
		// 从标题第一列开始
		int i = 0;
		// 循环标题所有的列
		while (cellTitle.hasNext()) {
		Cell cell = cellTitle.next();
		String value = cell.getStringCellValue();
		titlemap.put(i, value);
		i = i + 1;
		}
		//用来格式化日期的DateFormat
		SimpleDateFormat sf;
		while (row.hasNext()) {
		// 标题下的第一行
		Row rown = row.next();
		// 行的所有列
		Iterator<Cell> cellbody = rown.cellIterator();
		// 得到传入类的实例
		Object tObject = pojoClass.newInstance();
		int k = 0;
		// 遍历一行的列
		while (cellbody.hasNext()) {
		Cell cell = cellbody.next();
		// 这里得到此列的对应的标题
		String titleString = (String) titlemap.get(k);
		// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
		if (fieldSetMap.containsKey(titleString)) {
		Method setMethod = (Method) fieldSetMap.get(titleString);
		//得到setter方法的参数
		Type[] ts = setMethod.getGenericParameterTypes();
		//只要一个参数
		String xclass = ts[0].toString();
		//判断参数类型
		if (fieldSetConvertMap.containsKey(titleString)) {
		fieldSetConvertMap.get(titleString).invoke(tObject,
		cell.getStringCellValue());
		} else {
		if (xclass.equals("class java.lang.String")) {
			//先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
			cell.setCellType(Cell.CELL_TYPE_STRING);
		setMethod.invoke(tObject, cell
		.getStringCellValue());
		}
		else if (xclass.equals("class java.util.Date")) {
			try{
				setMethod.invoke(tObject, cell
						.getDateCellValue());
											
			}catch(Exception e){
				
			}
		}
		else if (xclass.equals("class java.lang.Boolean")) {
		setMethod.invoke(tObject, cell
		.getBooleanCellValue());
		}
		else if (xclass.equals("class java.lang.Integer")) {
		setMethod.invoke(tObject, new Integer(cell
		.getStringCellValue()));
		}else if(xclass. equals("class java.lang.Long"))
		{
		setMethod.invoke(tObject,new Long( cell.getStringCellValue()));
		}
		}
		}
		// 下一列
		k = k + 1;
		}
		dist.add(tObject);
		}
		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
		return dist;
	}
}

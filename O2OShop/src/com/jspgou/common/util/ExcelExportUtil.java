/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年5月29日
* @author liuwang
* @version 1.0
*/
package com.jspgou.common.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jspgou.cms.web.Excel;

public class ExcelExportUtil {
	/**
	 * 
	 * @param title     Sheet名字
	 * @param pojoClass Excel对象Class
	 * @param dataSet   Excel对象数据List
	 * @param out       输出流
	 */
	public static void exportExcel(String title, Class<?> pojoClass,
			Collection<?> dataSet, OutputStream out) {
		// 使用userModel模式实现的，当excel文档出现10万级别的大数据文件可能导致OOM内存溢出
		exportExcelInUserModel(title, pojoClass, dataSet, out);
		// 使用eventModel实现，可以一边读一边处理，效率较高，但是实现复杂，暂时未实现
	}

	private static void exportExcelInUserModel(String title, Class<?> pojoClass,
			Collection<?> dataSet, OutputStream out) {
		try {
			// 首先检查数据看是否是正确的
			if (dataSet == null || dataSet.size() == 0) {
				throw new Exception("导出数据为空！");
			}
			if (title == null || out == null || pojoClass == null) {
				throw new Exception("传入参数不能为空！");
			}
			// 声明一个工作薄
			Workbook workbook = new HSSFWorkbook();
			// 生成一个表格
			Sheet sheet = workbook.createSheet(title);

			// 标题
			List<String> exportFieldTitle = new ArrayList<String>();
			List<Integer> exportFieldWidth = new ArrayList<Integer>();
			// 拿到所有列名，以及导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			Map<String, Method> convertMethod = new HashMap<String, Method>();
			// 得到所有字段
			Field fileds[] = pojoClass.getDeclaredFields();
			// 遍历整个filed
			for (int i = 0; i < fileds.length; i++) {
				Field field = fileds[i];
				Excel excel = field.getAnnotation(Excel.class);
				// 如果设置了annottion
				if (excel != null) {
					// 添加到标题
					exportFieldTitle.add(excel.exportName());
					// 添加标题的列宽
					exportFieldWidth.add(excel.exportFieldWidth());
					// 添加到需要导出的字段的方法
					String fieldname = field.getName();
					// System.out.println(i+"列宽"+excel.exportName()+" "+excel.exportFieldWidth());
					StringBuffer getMethodName = new StringBuffer("get");
					getMethodName.append(fieldname.substring(0, 1)
							.toUpperCase());
					getMethodName.append(fieldname.substring(1));

					Method getMethod = pojoClass.getMethod(getMethodName
							.toString(), new Class[] {});

					methodObj.add(getMethod);
					if (excel.exportConvertSign() == 1) {
						StringBuffer getConvertMethodName = new StringBuffer(
								"get");
						getConvertMethodName.append(fieldname.substring(0, 1)
								.toUpperCase());
						getConvertMethodName.append(fieldname.substring(1));
						getConvertMethodName.append("Convert");
						// System.out.println("convert: "+getConvertMethodName.toString());
						Method getConvertMethod = pojoClass
								.getMethod(getConvertMethodName.toString(),
										new Class[] {});
						convertMethod.put(getMethodName.toString(),
								getConvertMethod);
					}
				}
			}
			int index = 0;
			// 产生表格标题行
			Row row = sheet.createRow(index);
			for (int i = 0, exportFieldTitleSize = exportFieldTitle.size(); i < exportFieldTitleSize; i++) {
				Cell cell = row.createCell(i);
				// cell.setCellStyle(style);
				RichTextString text = new HSSFRichTextString(exportFieldTitle
						.get(i));
				cell.setCellValue(text);
			}

			// 设置每行的列宽
			for (int i = 0; i < exportFieldWidth.size(); i++) {
				// 256=65280/255
				sheet.setColumnWidth(i, 256 * exportFieldWidth.get(i));
			}
			Iterator its = dataSet.iterator();
			// 循环插入剩下的集合
			while (its.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				Object t = its.next();
				for (int k = 0, methodObjSize = methodObj.size(); k < methodObjSize; k++) {
					Cell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = null;
					if (convertMethod.containsKey(getMethod.getName())) {
						Method cm = convertMethod.get(getMethod.getName());
						value = cm.invoke(t, new Object[] {});
					} else {
						value = getMethod.invoke(t, new Object[] {});
					}
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}

			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

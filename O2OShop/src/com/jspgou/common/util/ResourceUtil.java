/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年5月29日
* @author liuwang
* @version 1.0
*/
package com.jspgou.common.util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
/**
 * 
 * 功能：读取资源文件类 <br>
 * 时间：2010-11-24 <br>
 * 备注： <br>
 * 
 * @author Lin.~
 * 
 */
public  class ResourceUtil {
	public static String  filePath=new ResourceUtil().getClass().getResource("/").getPath().replace("classes/", "");   
	public String porper(String fileName,String keyName){
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath+fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 Properties p  =   new  Properties();    
		  try   {    
		  p.load(in);    
		   }   catch  (IOException e1)  {    
		  e1.printStackTrace();    
		  }     
		return p.getProperty(keyName);

		}

   public static void main(String[] args){
	  
	   System.out.println(filePath+"languages/jspgou_admin/messages_zh_CN.properties");
	  System.out.println( new ResourceUtil().porper(filePath+"languages\\jspgou_admin\\messages_zh_CN.properties", "order.status.0"));
   }


}

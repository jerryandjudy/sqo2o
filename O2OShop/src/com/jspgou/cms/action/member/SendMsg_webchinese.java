/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年5月11日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.action.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg_webchinese {
	public static void main(String[] args)throws Exception
	{

	HttpClient client = new HttpClient();
	PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
	NameValuePair[] data ={ new NameValuePair("Uid", "吉林德勤电子商务有限公司"),new NameValuePair("Key", "c65b9855d329e03e8672"),new NameValuePair("smsMob","15643083961"),new NameValuePair("smsText","程序测试")};
	post.setRequestBody(data);

	client.executeMethod(post);
	Header[] headers = post.getResponseHeaders();
	int statusCode = post.getStatusCode();
	System.out.println("statusCode:"+statusCode);
	for(Header h : headers)
	{
	System.out.println(h.toString());
	}
	String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
	System.out.println(result); //打印返回消息状态


	post.releaseConnection();

	}
	public static String    sendMsg(String tel,String content){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", "吉林德勤电子商务有限公司"),new NameValuePair("Key", "c65b9855d329e03e8672"),new NameValuePair("smsMob",tel),new NameValuePair("smsText",content)};
		
		post.setRequestBody(data);
		String result ="";
		String msg ="";
		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			result =new String(post.getResponseBodyAsString().getBytes("gbk")); 
			if("-1".equals(result)){
				msg="没有该用户账户";
			}else if("-2".equals(result)){
				msg="不是账户登陆密码";
			}else if("-21".equals(result)){
				msg="MD5接口密钥加密不正确";
			}else if("-3".equals(result)){
				msg="短信数量不足";
			}else if("-11".equals(result)){
				msg="该用户被禁用";
			}else if("-14".equals(result)){
				msg="短信内容出现非法字符";
			}else if("-4".equals(result)){
				msg="手机号格式不正确";
			}else if("-41".equals(result)){
				msg="手机号码为空";
			}else if("-42".equals(result)){
				msg="短信内容为空";
			}else if("-51".equals(result)){
				msg="短信签名格式不正确接口签名格式为：【签名内容】";
			}else if("-6".equals(result)){
				msg="IP限制";
			}else{
				msg="";
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
//		System.out.println("statusCode:"+statusCode);
		
//		for(Header h : headers)
//		{
//		System.out.println(h.toString());
//		}
		 
		System.out.println(result); //打印返回消息状态
    
		post.releaseConnection();
		return msg;
	}
}

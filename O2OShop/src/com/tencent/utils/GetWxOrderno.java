package com.tencent.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.utils.http.HttpClientConnectionManager;

public class GetWxOrderno {
	public static DefaultHttpClient httpclient;
	private static final Logger log = LoggerFactory.getLogger(GetWxOrderno.class);
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager
				.getSSLInstance(httpclient);
	}

	/**
	 * description:获取预支付id
	 * 
	 * @param url
	 * @param xmlParam
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	public static String getPayNo(String url, String xmlParam) {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
				true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
		String prepay_id = "";
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonStr+"55555555555555555");
			if (jsonStr.indexOf("FAIL") != -1) {
				log.info(xmlParam);
				return prepay_id;
			}
			
			Map map = doXMLParse(jsonStr);
			prepay_id = (String) map.get("prepay_id");
			//System.out.println("获取到的预支付ID：" + prepay_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prepay_id;
	}

	/**
	 * description:获取扫码支付连接
	 * 
	 * @param url
	 * @param xmlParam
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	public static WxPayCodeRes getCodeUrl(String url, String xmlParam) {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
				true);
		// 请求超时
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000    );
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);
		WxPayCodeRes wxPayCodeRes = new WxPayCodeRes();
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = client.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			Map map = doXMLParse(jsonStr);

			if (map.get("return_code").toString().indexOf("FAIL") != -1) {
				wxPayCodeRes.setCode(map.get("return_code").toString());
				wxPayCodeRes.setMsg(map.get("return_msg").toString());
				;
			} else {
				wxPayCodeRes.setCode(map.get("return_code").toString());
				wxPayCodeRes.setMsg(map.get("code_url").toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			wxPayCodeRes.setCode("FAIL");
			wxPayCodeRes.setMsg(e.getMessage());
		}
		return wxPayCodeRes;
	}

	/**
	 * description:获取退款结果
	 * 
	 * @param url
	 * @param xmlParam
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	/*
	 * public static Map getTkRes(String url,String xmlParam){ DefaultHttpClient
	 * client = new DefaultHttpClient();
	 * client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,
	 * true); HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	 * Map map = new HashMap(); try { httpost.setEntity(new
	 * StringEntity(xmlParam, "UTF-8")); HttpResponse response =
	 * httpclient.execute(httpost); String jsonStr =
	 * EntityUtils.toString(response.getEntity(), "UTF-8");
	 * System.out.println(jsonStr); map = doXMLParse(jsonStr);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return map; }
	 */
	/**
	 * description:获取退款结果
	 * 
	 * @param url
	 * @param xmlParam
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	public static Map getTkRes(String shh,String certpath,String url,String xmlParam){
	  KeyStore keyStore = null;
	  FileInputStream instream =null;
	  SSLContext sslcontext=null;
	  CloseableHttpResponse response =null;
	  CloseableHttpClient httpclients =null;
	  Map map = new HashMap();
	try {
		keyStore = KeyStore.getInstance("PKCS12");
     instream = new FileInputStream(new File(certpath));//放退款证书的路径
          keyStore.load(instream, shh.toCharArray());
          sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, shh.toCharArray()).build();
          SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                  sslcontext,
                  new String[] { "TLSv1" },
                  null,
                  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
           httpclients = HttpClients.custom().setSSLSocketFactory(sslsf).build();
          HttpPost httpPost = new HttpPost(url);//退款接口
          
//          System.out.println("executing request" + httpPost.getRequestLine());
          StringEntity  reqEntity  = new StringEntity(xmlParam);
          // 设置类型 
         reqEntity.setContentType("application/x-www-form-urlencoded"); 
          httpPost.setEntity(reqEntity);
        	   response = httpclients.execute(httpPost);
              String jsonStr =
            	EntityUtils.toString(response.getEntity(), "UTF-8");
//            	System.out.println(jsonStr); 
            	map = doXMLParse(jsonStr);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
		map.put("err_code_des", e.getMessage());
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} catch (CertificateException e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} catch (KeyStoreException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
	} catch (KeyManagementException e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} catch (UnrecoverableKeyException e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		map.put("err_code_des", e.getMessage());
	} finally {
          try {
        	  if(instream!=null){
			instream.close();
        	  }
        	  if(response!=null){
        	  response.close();
        	  }
        	  if(httpclients!=null){
        		  httpclients.close();
        	  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
	return map;
  }

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	/**
	 * 获取OpenId 与 access_token
	 * @return
	 */
	public static Map<String,String> getOpenIdAndAccess_token(String url){
		Map<String,String> map=new HashMap<String,String>();
		try {
		URL reqURL = new URL(url);
		HttpsURLConnection httpsConn = (HttpsURLConnection) reqURL.openConnection();
	        // 取得该连接的输入流，以读取响应内容
	        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
	        BufferedReader br=new BufferedReader(insr);
	        String str="";
	        String jsonstr="";
	        while((str=br.readLine()) !=null){
	        	jsonstr+=str;
	        }
	//System.out.println(jsonstr+"=========");
		if (jsonstr.indexOf("errcode") != -1) {
			return map;
		}
		
		 map = doJSONParse(jsonstr);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
	}
	
	public static Map<String,String> doJSONParse(String jsonstr){
		Map<String,String> map=new HashMap<String,String>();
		try {
			JSONObject json=new JSONObject(jsonstr);
			map.put("access_token", json.getString("access_token"));
			map.put("refresh_token", json.getString("refresh_token"));
			map.put("openid", json.getString("openid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
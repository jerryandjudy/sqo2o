package com.jspgou.cms;

import java.util.HashMap;
import java.util.Map;


/**
* This class should preserve.
* @preserve
*/
public class AdminMap {
	
	public  static Map<String,Integer> adminmap=new HashMap<String,Integer>();
	public static Integer getAdminMapVal(String username){
		return adminmap.get(username);
	}
	
	public static void addAdminMapVal(String username){
		if(adminmap.get(username)==null){
			adminmap.put(username, 1);
		}else{
			adminmap.put(username, adminmap.get(username)+1);
		}
	}
	
	public static void unLoadAdmin(String username){
		adminmap.put(username, 0);
		adminmap.remove(username);
	}

}

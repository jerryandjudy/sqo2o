package com.jspgou.cms.action.admin.main;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.web.ResponseUtils;
import com.jspgou.cms.AdminMap;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class UnLoadAdminAct {
	private static final Logger log=LoggerFactory.getLogger(UnLoadAdminAct.class);
	
	@RequestMapping(value = "/commonAdmin/v_list.do", method = RequestMethod.GET)
	public String unLoad(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Map<String,Integer> adminMap=AdminMap.adminmap;
		model.addAttribute("map1", adminMap);
            Set<String> keySet=adminMap.keySet();
            for(String username : keySet){
            	if(adminMap.get(username)>=3){
            		
            	}
            }
            return "admin/uplocklist";
	}
	
	@RequestMapping(value="/commonAdmin/v_unlock.do",method=RequestMethod.POST)
	public String unlock(HttpServletResponse response,String username){
		AdminMap.unLoadAdmin(username);
		ResponseUtils.renderJson(response, "解锁成功 !");
		return null;
	}

}

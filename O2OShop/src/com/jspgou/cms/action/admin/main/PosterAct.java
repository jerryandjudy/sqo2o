package com.jspgou.cms.action.admin.main;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.web.ResponseUtils;
import com.jspgou.cms.entity.Poster;
import com.jspgou.cms.manager.PosterMng;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class PosterAct {

	@RequestMapping("/poster/v_list.do")
	public String list( HttpServletRequest request,
			ModelMap model) {
		List<Poster> listPoster=posterMng.getPage();
		model.addAttribute("listPoster", listPoster);
		return "poster/list";
	}
	
	@RequestMapping("/poster/o_updateCare.do")
	public String o_updateCare(String val,Integer id, HttpServletRequest request,HttpServletResponse response,
			ModelMap model) {
		Poster bean=posterMng.findById(id);
		bean.setInterUrl(val);
		posterMng.update(bean);
		ResponseUtils.renderJson(response, "success");
		return null;
	}
	
	@RequestMapping("/poster/o_update.do")
	public String edit(String[] picUrl,String[] interUrl, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		if(picUrl!=null && picUrl.length>0){
			for(int i=0;i<picUrl.length;i++){
				Poster p=new Poster();
				p.setTime(new Date());
				p.setPicUrl(picUrl[i]);
				p.setInterUrl(interUrl[i]);
				posterMng.saveOrUpdate(p);
			}
		}
		
		return "redirect:v_list.do";
	}

	@RequestMapping("/poster/o_delPoster.do")
	public String delete(Integer id, Integer pageNo,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		posterMng.deleteById(id);
		ResponseUtils.renderJson(response, "success");
		return null;
	}

	@Autowired
	private PosterMng posterMng;
}
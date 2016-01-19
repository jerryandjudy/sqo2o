package com.jspgou.cms.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Adspace;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.manager.AdspaceMng;
import com.jspgou.cms.manager.CategoryMng;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class AdspaceAct {
	
	@RequestMapping("/adspace/v_list.do")
	public String list(HttpServletRequest request,HttpServletResponse response,
			ModelMap model){
		List<Adspace> list=adspaceMng.getList();
		model.addAttribute("list", list);
		return "adspace/list";
	}
	
	@RequestMapping("/adspace/v_add.do")
	public String add(HttpServletRequest request,HttpServletResponse response,
			ModelMap model){
		List<Category> categorys=categoryMng.getChildList(null, null, true);
		model.addAttribute("categorys", categorys);
		return "adspace/add";
	}
	
	@RequestMapping("/adspace/v_edit.do")
	public String edit(Integer pageNo,Integer id,HttpServletRequest request,
			HttpServletResponse response,ModelMap model){
		Adspace bean =adspaceMng.findById(id);
		List<Category> categorys=categoryMng.getChildList(null, null, true);
		model.addAttribute("categorys", categorys);
		model.addAttribute("adspace", bean);
		return "adspace/edit";
	}
	
	@RequestMapping("/adspace/o_update.do")
	public String update(Adspace bean,HttpServletRequest request ,
			HttpServletResponse response,ModelMap model){
		adspaceMng.updateByUpdater(bean);
		return list(request,response,model);
	}
	
	@RequestMapping("/adspace/o_save.do")
	public String save(Integer pageNo,Adspace bean,HttpServletRequest request ,
			HttpServletResponse response,ModelMap model){
		adspaceMng.save(bean);
		return list(request,response,model);
	}
	
	@RequestMapping("/adspace/o_delete.do")
	public String delete(Integer[] ids,HttpServletRequest request ,
			HttpServletResponse response,ModelMap model){
		adspaceMng.deleteByIds(ids);
		return list(request ,response,model);
	}
	
	@Autowired
	private AdspaceMng adspaceMng;
	@Autowired
	private CategoryMng categoryMng;
}

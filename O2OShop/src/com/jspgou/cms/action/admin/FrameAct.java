package com.jspgou.cms.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* This class should preserve.
* @preserve
*/
@Controller
public class FrameAct {
	@RequestMapping("/frame/config_main.do")
	public String configMain(ModelMap model) {
		return "frame/config_main";
	}

	@RequestMapping("/frame/config_left.do")
	public String configLeft(ModelMap model) {
		return "frame/config_left";
	}

	@RequestMapping("/frame/config_right.do")
	public String configRight(ModelMap model) {
		return "frame/config_right";
	}

	@RequestMapping("/frame/category_main.do")
	public String categoryMain(ModelMap model) {
		return "frame/category_main";
	}
	
	@RequestMapping("/frame/channel_main.do")
	public String channelMain(ModelMap model) {
		return "frame/channel_main";
	}
	
	@RequestMapping("/frame/article_main.do")
	public String articleMain(ModelMap model) {
		return "frame/article_main";
	}

	@RequestMapping("/frame/product_main.do")
	public String productMain(ModelMap model) {
		return "frame/product_main";
	}
	@RequestMapping("/frame/site_product_main.do")
	public String productSiteMain(ModelMap model) {
		return "frame/site_product_main";
	}

	@RequestMapping("/frame/template_main.do")
	public String templateMain(ModelMap model) {
		return "frame/template_main";
	}

	@RequestMapping("/frame/resource_main.do")
	public String resourceMain(ModelMap model) {
		return "frame/resource_main";
	}

	@RequestMapping("/frame/member_main.do")
	public String memberMain(ModelMap model) {
		return "frame/member_main";
	}

	@RequestMapping("/frame/member_left.do")
	public String memberLeft(ModelMap model) {
		return "frame/member_left";
	}

	@RequestMapping("/frame/member_right.do")
	public String memberRight(ModelMap model) {
		return "frame/member_right";
	}
	
	@RequestMapping("/frame/order_main.do")
	public String orderMain(ModelMap model) {
		return "frame/order_main";
	}

	@RequestMapping("/frame/order_left.do")
	public String orderLeft(ModelMap model) {
		return "frame/order_left";
	}

	@RequestMapping("/frame/order_right.do")
	public String orderRight(ModelMap model) {
		return "frame/order_right";
	}
	
	@RequestMapping("/frame/assistant_main.do")
	public String pageMain(ModelMap model) {
		return "frame/assistant_main";
	}

	@RequestMapping("/frame/assistant_left.do")
	public String pageLeft(ModelMap model) {
		return "frame/assistant_left";
	}

	@RequestMapping("/frame/assistant_right.do")
	public String pageRight(ModelMap model) {
		return "frame/assistant_right";
	}
	
	@RequestMapping("/frame/financial_main.do")
	public String financialMain(ModelMap model) {
		return "frame/financial_main";
	}
	@RequestMapping("/frame/marketing_main.do")
	public String marketingMain(ModelMap model) {
		return "frame/marketing_main";
	}
	
	@RequestMapping("/frame/marketing_right.do")
	public String marketingRight(ModelMap model) {
		return "frame/marketing_right";
	}
	
	@RequestMapping("/frame/marketing_left.do")
	public String marketingLeft(ModelMap model) {
		return "frame/marketing_left";
	}
	@RequestMapping("/frame/financial_right.do")
	public String financialRight(ModelMap model) {
		return "frame/financial_right";
	}
	
	@RequestMapping("/frame/financial_left.do")
	public String financialLeft(ModelMap model) {
		return "frame/financial_left";
	}
}

package com.jspgou.core.manager;

import java.util.Map;

import com.jspgou.core.entity.Config;
import com.jspgou.core.entity.Config.ConfigLogin;


public interface ConfigMng {
	public Map<String, String> getMap();

	public ConfigLogin getConfigLogin();


	public String getValue(String id);

	public void updateOrSave(Map<String, String> map);

	public Config updateOrSave(String key, String value);

	public Config deleteById(String id);

	public Config[] deleteByIds(String[] ids);
}
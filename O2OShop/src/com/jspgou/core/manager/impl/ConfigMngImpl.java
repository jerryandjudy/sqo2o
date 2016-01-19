package com.jspgou.core.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.core.dao.ConfigDao;
import com.jspgou.core.entity.Config;
import com.jspgou.core.entity.Config.ConfigLogin;
import com.jspgou.core.manager.ConfigMng;

@Service
@Transactional
public class ConfigMngImpl implements ConfigMng {
	@Transactional(readOnly = true)
	public Map<String, String> getMap() {
		List<Config> list = configDao.getList();
		Map<String, String> map = new HashMap<String, String>(list.size());
		for (Config config : list) {
			map.put(config.getId(), config.getValue());
		}
		return map;
	}

	@Transactional(readOnly = true)
	public String getValue(String id) {
		Config entity = configDao.findById(id);
		if (entity != null) {
			return entity.getValue();
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public ConfigLogin getConfigLogin() {
		return ConfigLogin.create(getMap());
	}

	


	public void updateOrSave(Map<String, String> map) {
		if (map != null && map.size() > 0) {
			for (Entry<String, String> entry : map.entrySet()) {
				updateOrSave(entry.getKey(), entry.getValue());
			}
		}
	}

	public Config updateOrSave(String key, String value) {
		Config config = configDao.findById(key);
		if (config != null) {
			config.setValue(value);
		} else {
			config = new Config(key);
			config.setValue(value);
			configDao.save(config);
		}
		return config;
	}

	public Config deleteById(String id) {
		Config bean = configDao.deleteById(id);
		return bean;
	}

	public Config[] deleteByIds(String[] ids) {
		Config[] beans = new Config[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ConfigDao configDao;
}
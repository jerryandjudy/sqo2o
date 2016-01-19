package com.jspgou.cms.manager.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;

import com.jspgou.cms.dao.CouponDao;
import com.jspgou.cms.entity.Coupon;
import com.jspgou.cms.manager.CouponMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CouponMngImpl implements CouponMng {
	
	public Pagination getPage(int pageNo, int pageSize){
		return couponDao.getPage(pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPageByUsing(int pageNo, int pageSize){
		return couponDao.getPageByUsing(new Date(), pageNo, pageSize);
	}
	
	
	@Transactional(readOnly = true)
	public List<Coupon> getList() {
		List<Coupon> list = couponDao.getList();
		return list;
	}

	
	@Transactional(readOnly = true)
	public Coupon findById(Long id) {
		Coupon entity = couponDao.findById(id);
		return entity;
	}

	public Coupon save(Coupon bean,Long webId) {
		Website site=websiteMng.findById(webId);
		bean.setWebsite(site);
		Coupon entity = couponDao.save(bean);
		return entity;
	}

	public Coupon update(Coupon bean) {
		Updater<Coupon> updater = new Updater<Coupon>(bean);
		Coupon entity = couponDao.updateByUpdater(updater);
		
		return entity;
	}

	public Coupon deleteById(Long id,String url) {
		Coupon entity = findById(id);
		String path=entity.getCouponPicture();
		String path1 = realPathResolver.get(path).replace("\\", File.separator).replace("/", File.separator).replace(url.replace("\\", File.separator).replace("/",File.separator)+url.replace("\\", File.separator).replace("/",File.separator), url.replace("\\", File.separator).replace("/",File.separator));
		File f=new File(path1);
		if(f!=null){
			f.delete();
		}
		entity = couponDao.deleteById(id);
		return entity;
	}

	public Coupon[] deleteByIds(Long[] ids,String url) {
		Coupon[] beans = new Coupon[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i],url);
		}
		return beans;
	}
	
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private WebsiteMng websiteMng;
}
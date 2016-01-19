package com.jspgou.cms.manager.impl;

import static com.jspgou.common.web.Constants.POINT;
import static com.jspgou.common.web.Constants.SPT;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.dao.ShopMemberDao;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ShopMemberGroup;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ShopMemberGroupMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.common.file.FileNameUtils;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.image.ImageScale;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.Member;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;


/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopMemberMngImpl implements ShopMemberMng {
	public ShopMember getByUserId(Long webId, Long userId) {
		Member coreMember = memberMng.getByUserId(webId, userId);
		if (coreMember != null) {
			return findById(coreMember.getId());
		} else {
			return null;
		}
	}

	public ShopMember getByUsername(Long webId, String username) {
		Member coreMember = memberMng.getByUsername(webId, username);
		if (coreMember == null) {
			return null;
		}
		return findById(coreMember.getId());
	}
	public ShopMember getByUsername(String username) {
		Member coreMember = memberMng.getByUsername(username);
		if (coreMember == null) {
			return null;
		}
		return findById(coreMember.getId());
	}

	public ShopMember register(String username, String password, String email,Boolean active,String activationCode,
			String ip, Boolean disabled, Long webId,Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			ShopMember member) {
		Website web = websiteMng.findById(webId);
		if (member == null) {
			member = new ShopMember();
		}
		Member coreMember = memberMng.register(username, password, email,active,activationCode, ip,//jc_core_member
				disabled, web);
		member.setMember(coreMember);
		member.setMobile(username);
		member.setTel(username);
		member.setWebsite(web);
		member.setFreezeScore(0);
		if (groupId != null) {
			member.setGroup(shopMemberGroupMng.findById(groupId));
		}
		
		if (userDegreeId != null) {
			member.setUserDegree(shopDictionaryMng.findById(userDegreeId));
		}
		if (degreeId != null) {
            member.setDegree(shopDictionaryMng.findById(degreeId));
		}
		if (incomeDescId != null) {
           member.setIncomeDesc(shopDictionaryMng.findById(incomeDescId));
		}
		if (workSeniorityId != null) {
           member.setWorkSeniority(shopDictionaryMng.findById(workSeniorityId));
		}
		if (familyMembersId != null) {
           member.setFamilyMembers(shopDictionaryMng.findById(familyMembersId));
		}	
		return save(member);
	}

	public ShopMember join(String username, Long webId, Long groupId) {
		Website web = websiteMng.findById(webId);
		Member coreMember = memberMng.join(username, web);
		ShopMember member = new ShopMember();
		member.setMember(coreMember);
		member.setWebsite(web);
		member.setGroup(shopMemberGroupMng.findById(groupId));
		return save(member);
	}

	public ShopMember join(Long userId, Long webId, ShopMemberGroup group) {
		User user = userMng.findById(userId);
		return join(user, webId, group);
	}

	public ShopMember join(User user, Long webId, ShopMemberGroup group) {
		Website web = websiteMng.findById(webId);
		Member coreMember = memberMng.join(user, web);
		ShopMember member = new ShopMember();
		member.setMember(coreMember);
		member.setWebsite(web);
		member.setGroup(group);
		return save(member);
	}

	public ShopMember register(String username, String password, String email,Boolean active,String activationCode,
			String ip, Boolean disabled, Long webId, Long groupId) {
		return register(username, password, email,active,activationCode,ip, disabled, webId,
				groupId,null,null,null,null,null, new ShopMember());
	}

	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, int pageNo, int pageSize) {
		Pagination page = shopMemberDao.getPage(webId, pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopMember findById(Long id) {
		ShopMember entity = shopMemberDao.findById(id);
		return entity;
	}
	
	public ShopMember save(ShopMember bean) {
		bean.init();
		if (bean.getGroup() == null) {
			bean.setGroup(shopMemberGroupMng.findGroupByScore(bean.getWebsite()
					.getId(), bean.getScore()));
		}
		shopMemberDao.save(bean);
		return bean;
	}

	public ShopMember update(ShopMember bean,  Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			String password, String email, Boolean disabled) {
		ShopMember entity = findById(bean.getId());
		// 修改密码，User
		userMng.updateUser(entity.getMember().getUser().getId(), password,
				email);
		// 是否禁用，Member，级联更新。
		if (disabled != null) {
			entity.getMember().setDisabled(disabled);
		}
		if (groupId != null) {
			entity.setGroup(shopMemberGroupMng.findById(groupId));
		}
		
		if (userDegreeId != null) {
			entity.setUserDegree(shopDictionaryMng.findById(userDegreeId));
		}
		if (degreeId != null) {
			entity.setDegree(shopDictionaryMng.findById(degreeId));
		}
		if (incomeDescId != null) {
			entity.setIncomeDesc(shopDictionaryMng.findById(incomeDescId));
		}
		if (workSeniorityId != null) {
			entity.setWorkSeniority(shopDictionaryMng.findById(workSeniorityId));
		}
		if (familyMembersId != null) {
			entity.setFamilyMembers(shopDictionaryMng.findById(familyMembersId));
		}
		Updater<ShopMember> updater = new Updater<ShopMember>(bean);
		shopMemberDao.updateByUpdater(updater);
		entity.setGroup(shopMemberGroupMng.findGroupByScore(entity.getWebsite()
				.getId(), entity.getScore()));
		return entity;
	}
	
	public ShopMember update(ShopMember bean){
		Updater<ShopMember> updater = new Updater<ShopMember>(bean);
		shopMemberDao.updateByUpdater(updater);
		return bean;
	}
	
	public ShopMember updateScore(ShopMember bean,Integer score){
		Integer scoreDb=shopMemberDao.findById(bean.getId()).getScore();
		Integer scoreTotal=scoreDb+score;
		bean.setScore(scoreTotal);
		bean.setGroup(shopMemberGroupMng.findGroupByScore(bean.getWebsite()
				.getId(), scoreTotal));
		Updater<ShopMember> updater = new Updater<ShopMember>(bean);
		shopMemberDao.updateByUpdater(updater);
		return bean;
	}

	

	
	public ShopMember update(ShopMember bean, Long groupId,
			Long userDegreeId,Long degreeId,Long incomeDescId,Long workSeniorityId,
			Long familyMembersId){
		ShopMember entity = findById(bean.getId());
	
		if (userDegreeId != null) {
			entity.setUserDegree(shopDictionaryMng.findById(userDegreeId));
		}
		if (degreeId != null) {
			entity.setDegree(shopDictionaryMng.findById(degreeId));
		}
		if (incomeDescId != null) {
			entity.setIncomeDesc(shopDictionaryMng.findById(incomeDescId));
		}
		if (workSeniorityId != null) {
			entity.setWorkSeniority(shopDictionaryMng.findById(workSeniorityId));
		}
		if (familyMembersId != null) {
			entity.setFamilyMembers(shopDictionaryMng.findById(familyMembersId));
		}
		Updater<ShopMember> updater = new Updater<ShopMember>(bean);
		updater.exclude(ShopMember.PROP_SCORE);
		shopMemberDao.updateByUpdater(updater);
		return entity;
	}

	public ShopMember deleteById(Long id) {
		ShopMember bean=shopMemberDao.findById(id);
		Long userId=bean.getMember().getUser().getId();
		bean = shopMemberDao.deleteById(id);
		userMng.deleteById(userId);
		return bean;
	}

	public ShopMember[] deleteByIds(Long[] ids) {
		ShopMember[] beans = new ShopMember[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			List<ShopMemberAddress> list=shopMemberAddressMng.findByMemberId(ids[i]);
			for(ShopMemberAddress address :list){
				shopMemberAddressMng.deleteById(address.getId(),ids[i]);
			}
			cartMng.deleteById(ids[i]);
			//还要删除订单哦 收藏夹 积分明细
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private CartMng cartMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private ShopMemberDao shopMemberDao;
	@Autowired
	private MemberMng memberMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ImageScale imageScale;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
	@Autowired
	private ShopMemberGroupMng shopMemberGroupMng; 
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
}
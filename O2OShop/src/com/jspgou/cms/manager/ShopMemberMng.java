package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.User;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberGroup;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMemberMng {
	public ShopMember getByUsername(Long webId, String username);
	public ShopMember getByUsername( String username);

	public ShopMember getByUserId(Long webId, Long userId);

	public ShopMember register(String username, String password, String email,Boolean active,
			String activationCode,String ip, Boolean disabled, Long webId, 
			Long groupId,Long userDegreeId,Long degreeId,Long incomeDescId,
			Long workSeniorityId,Long familyMembersId,
			ShopMember member);

	public ShopMember join(String username, Long webId, Long groupId);

	public ShopMember join(Long userId, Long webId, ShopMemberGroup group);

	public ShopMember join(User user, Long webId, ShopMemberGroup group);

	public ShopMember register(String username, String password, String email,Boolean active,
			String activationCode,String ip, Boolean disabled, Long webId, Long groupId);

	public Pagination getPage(Long webId, int pageNo, int pageSize);

	public ShopMember findById(Long id);

	public ShopMember save(ShopMember bean);

	//后台更新个人资料
	public ShopMember update(ShopMember bean, Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId,
			String password, String email, Boolean disabled);

	//前台更新个人资料
	public ShopMember update(ShopMember bean, Long groupId,Long userDegreeId,
			Long degreeId,Long incomeDescId,Long workSeniorityId,Long familyMembersId);

	public ShopMember deleteById(Long id);

	public ShopMember[] deleteByIds(Long[] ids);
	
	public ShopMember update(ShopMember bean);
	
	public ShopMember updateScore(ShopMember bean,Integer score);
	
}
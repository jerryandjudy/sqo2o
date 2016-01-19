package com.jspgou.core.manager.impl;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.common.security.userdetails.UserDetails;
import com.jspgou.common.security.userdetails.UserDetailsService;
import com.jspgou.core.dao.MemberDao;
import com.jspgou.core.entity.*;
import com.jspgou.core.manager.MemberMng;
import com.jspgou.core.manager.UserMng;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class MemberMngImpl implements MemberMng, UserDetailsService{

    public UserDetails loadUser(Long id, String s) throws UsernameNotFoundException{
        Member member = findById(id);
        if(member == null){
            throw new UsernameNotFoundException("member id not found '"+id+"'");
        }else{
            return member;
        }
    }

    public Member getByUsername(Long webId, String username){
        User entity = userMng.getByUsername(username);
        if(entity == null){
            return null;
        }else{
            return getByUserId(webId, entity.getId());
        }
    }
    public Member getByUsername(String username){
    	User entity = userMng.getByUsername(username);
    	if(entity == null){
    		return null;
    	}else{
    		return getByUserId(entity.getId());
    	}
    }
    
    
    public Member getByUserIdAndActive(String activationCode, Long userId){
            return dao.getByUserIdAndActive(activationCode,userId);
    }

    public Member getByUserId(Long webId, Long userId){
    	if(webId==null || webId==0){
    		return dao.getByUserId( userId);
    	}
        return dao.getByUserId(webId, userId);
    }
    public Member getByUserId( Long userId){
    	return dao.getByUserId( userId);
    }

    public Member register(String username, String password, String email,Boolean active,
    		String activationCode,String ip, Boolean disabled, Website website){
        Member member = new Member();
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        User user = userMng.register(username, password, email, ip, createtime);
        member.setCreateTime(createtime);
        member.setUser(user);
        member.setWebsite(website);
        member.setDisabled(disabled);
        member.setActive(active);
        member.setActivationCode(activationCode);
        return save(member);
    }

    public Member join(String username, Website website){
        User entity = userMng.getByUsername(username);
        if(entity == null){
            throw new IllegalStateException("User not found: "+username);
        }else{
            return join(entity, website);
        }
    }

    public Member join(User user, Website website){
        Member entity = getByUserId(website.getId(), user.getId());
        if(entity != null){
            return entity;
        } else{
            Member member = new Member();
            member.setUser(user);
            member.setWebsite(website);
            return save(member);
        }
    }

    public Pagination getPage(int pageNo, int pageSize){
        return dao.getPage(pageNo, pageSize);
    }

    public Member findById(Long id){
        return dao.findById(id);
    }

    public Member save(Member bean){
        bean.init();
        return dao.save(bean);
    }

    @SuppressWarnings("unchecked")
	public Member update(Member bean){
        return dao.updateByUpdater(new Updater(bean));
    }

    public Member deleteById(Long id){
        return dao.deleteById(id);
    }

    public Member[] deleteByIds(Long[] ids){
        Member[] beans = new Member[ids.length];
        for(int i = 0; i < ids.length; i++)
        	beans[i] = deleteById(ids[i]);

        return beans;
    }
    
    private UserMng userMng;
    private MemberDao dao;

	@Autowired
    public void setDao(MemberDao dao){
        this.dao = dao;
    }

	@Autowired
    public void setUserMng(UserMng userMng){
        this.userMng = userMng;
    }
}

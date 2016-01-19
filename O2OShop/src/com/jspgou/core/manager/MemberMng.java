package com.jspgou.core.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.*;
/**
* This class should preserve.
* @preserve
*/
public interface MemberMng{

    public Member getByUsername(Long webId, String username);
    public Member getByUsername(String username);
    
    public Member getByUserIdAndActive(String activationCode, Long userId);

    public Member getByUserId(Long webId, Long userId);

    public Member register(String username, String password, String email,Boolean active,
    		String activationCode,String ip, Boolean disabled, Website website);

    public Member join(String username, Website website);

    public Member join(User user, Website website);

    public Pagination getPage(int pageNo, int pageSize);

    public Member findById(Long id);

    public Member save(Member bean);

    public Member update(Member bean);

    public Member deleteById(Long id);

    public Member[] deleteByIds(Long[] ids);
}

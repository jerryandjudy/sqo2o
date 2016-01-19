package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Member;
/**
* This class should preserve.
* @preserve
*/
public interface MemberDao{
    public Member getByUserId(Long webId, Long userId);
    public Member getByUserId(Long userId);
    
    public Member getByUserIdAndActive(String activationCode, Long userId);

    public Pagination getPage(int pageNo, int pageSize);

    public Member findById(Long id);

    public Member save(Member bean);

    public Member updateByUpdater(Updater<Member> updater);

    public Member deleteById(Long id);
}

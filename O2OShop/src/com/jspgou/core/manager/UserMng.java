package com.jspgou.core.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.*;
import java.util.Date;
import org.springframework.mail.MailException;
/**
* This class should preserve.
* @preserve
*/
public interface UserMng{
	/**
	 * 忘记密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param base
	 *             网站基本地址
	 * @param email
	 *            发送者邮件信息
	 * @param tpl
	 *            邮件模板。内容模板可用变量${uid}、${username}、${resetKey}、${resetPwd}。
	 * @return
	 */
    public User passwordForgotten(Long id, String base,EmailSender emailsender, MessageTemplate tpl);

    public void senderEmail(Long uid, String username, String base, String to, String resetKey, String resetPwd,
    		EmailSender email, MessageTemplate tpl)
        throws MailException;
    
	/**
	 * 发送激活邮件
	 * 
	 * @param userId
	 *            接收者的用户ID
	 * @param userName
	 *             接受者的用户名
	 * @param base
	 *             网站基本地址
	 * @param receiverEmail
	 *             接收者邮箱地址  
	 * @param activationCode
	 *            激活码                    
	 * @param emailSender
	 *            发送者邮件信息
	 * @param tpl
	 *            邮件模板。内容模板可用变量${userId}、${activationCode}。
	 * @return
	 */
    public void senderActiveEmail(String userName,String base,String receiverEmail,String activationCode, 
    		 EmailSender emailSender, MessageTemplate messagetemplate)throws MailException;

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @return
	 */
    public User resetPassword(Long userId);

	/**
	 * 密码是否正确
	 * 
	 * @param id
	 *            用户ID
	 * @param password
	 *            未加密密码
	 * @return
	 */
    public boolean isPasswordValid(Long id, String password);

    public boolean usernameExist(String username);

    public boolean emailExist(String email);

    public User getByUsername(String username);

    public User getByEmail(String email);

    public void updateLoginInfo(Long userId, String ip);

    public User register(String username, String password, String email, String ip, Date date);

    public User register(String username, String password, String email, String ip);

    public Pagination getPage(int pageNo, int pageSize);

    public User findById(Long id);

    public User save(User bean);
    public User updateErrorCount(User bean);

    public User updateUser(Long id, String password, String email);

    public User deleteById(Long id);

    public User[] deleteByIds(Long[] ids);
}

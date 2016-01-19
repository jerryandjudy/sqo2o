package com.jspgou.core.manager.impl;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.security.encoder.PwdEncoder;
import com.jspgou.core.dao.UserDao;
import com.jspgou.core.entity.*;
import com.jspgou.core.manager.UserMng;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class UserMngImpl implements UserMng{

    public User passwordForgotten(Long id,String base,EmailSender email, MessageTemplate tpl){
        User entity = findById(id);
        String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
        entity.setResetKey(uuid);
        String resetPwd = RandomStringUtils.randomNumeric(10);
        entity.setResetPwd(resetPwd);
        senderEmail(entity.getId(), entity.getUsername(),base, entity.getEmail(), entity
        		.getResetKey(), entity.getResetPwd(), email, tpl);
        return entity;
    }
    
    //发送激活邮件
    public void senderActiveEmail(final String userName,final String base,final String receiverEmail, 
    		final String activationCode, final EmailSender email, 
    		final MessageTemplate tpl) throws MailException{
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(email.getHost());
        sender.setUsername(email.getUsername());
        sender.setPassword(email.getPassword());
        sender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimemessage)
            throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper msg = new MimeMessageHelper(mimemessage, 
                		false, email.getEncoding());
                msg.setSubject(tpl.getActiveTitle());
                msg.setTo(receiverEmail);
                msg.setFrom(email.getUsername(), email.getPersonal());
                String text = tpl.getActiveTxt();
                text = StringUtils.replace(text, "${userName}",userName);
                text = StringUtils.replace(text, "${activationCode}", activationCode);
                text = StringUtils.replace(text, "${base}", base);
                msg.setText(text,true);
            }
        });
    }
    
    
    public void senderEmail(final Long uid, final String username,final String base,
    		final String to, final String resetKey, final String resetPwd,
    		final EmailSender email, final MessageTemplate tpl) throws MailException{
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(email.getHost());
        sender.setUsername(email.getUsername());
        sender.setPassword(email.getPassword());
        sender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimemessage)
            throws MessagingException, UnsupportedEncodingException {
                MimeMessageHelper msg = new MimeMessageHelper(mimemessage, 
                		false, email.getEncoding());
                msg.setSubject(tpl.getSubject());
                msg.setTo(to);
                msg.setFrom(email.getUsername(), email.getPersonal());
                String text = tpl.getText();
                text = StringUtils.replace(text, "${uid}", String.valueOf(uid));
                text = StringUtils.replace(text, "${username}", username);
                text = StringUtils.replace(text, "${activationCode}", resetKey);
                text = StringUtils.replace(text, "${resetPwd}", resetPwd);
                text = StringUtils.replace(text, "${base}", base);
                msg.setText(text,true);
            }
        });
    }

    public User resetPassword(Long userId) {
        User entity = findById(userId);
        entity.setPassword(pwdEncoder.encodePassword(entity.getResetPwd()));
        entity.setResetKey(null);
        entity.setResetPwd(null);
        return entity;
    }

    public boolean isPasswordValid(Long id, String password){
        User entity = findById(id);
        return pwdEncoder.isPasswordValid(entity.getPassword(), password);
    }

    public boolean usernameExist(String username){
        return getByUsername(username) != null;
    }

    public boolean emailExist(String email){
        return getByEmail(email) != null;
    }

    public User getByUsername(String username){
        return dao.getByUsername(username);
    }

    public User getByEmail(String email){
        return dao.getByEmail(email);
    }

    public void updateLoginInfo(Long userId, String ip){
        User entity = findById(userId);
        entity.setLoginCount(Long.valueOf(entity.getLoginCount().longValue() + 1L));
        String s1 = entity.getCurrentLoginIp();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if(StringUtils.isBlank(s1)){
        	entity.setLastLoginIp(ip);
        	entity.setLastLoginTime(time);
        } else{
        	entity.setLastLoginIp(entity.getCurrentLoginIp());
        	entity.setLastLoginTime(entity.getCurrentLoginTime());
        }
        entity.setCurrentLoginIp(ip);
        entity.setCurrentLoginTime(time);
    }
    public User updateErrorCount(User bean){
    	Updater updater=new Updater<User>(bean);
    	dao.updateByUpdater(updater);
    	return bean;
    }

    public User register(String username, String password, String email, String ip, Date date){
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setRegisterIp(ip);
        if(date != null){
        	entity.setCreateTime(date);
        }
        return save(entity);
    }

    public User register(String username, String password, String email, String ip){
        return register(username, password, email, ip, new Date());
    }

    public Pagination getPage(int pageNo, int pageSize){
        return dao.getPage(pageNo, pageSize);
    }

    public User findById(Long id){
        return dao.findById(id);
    }

    public User save(User bean){
        String password = pwdEncoder.encodePassword(bean.getPassword());
        bean.setPassword(password);
        bean.init();
        return dao.save(bean);
    }

    public User updateUser(Long id, String password, String email){
        User entity = findById(id);
        if(!StringUtils.isBlank(password)){
        	entity.setPassword(pwdEncoder.encodePassword(password));
        }
        if(!StringUtils.isBlank(email)){
        	entity.setEmail(email);
        }
        return entity;
    }

    public User deleteById(Long id){
        return dao.deleteById(id);
    }

    public User[] deleteByIds(Long[] ids){
        User beans[] = new User[ids.length];
        for(int i = 0; i < ids.length; i++){
            beans[i] = deleteById(ids[i]);
        }
        return beans;
    }


    private PwdEncoder pwdEncoder;
    private UserDao dao;
    
	@Autowired
    public void setPwdEncoder(PwdEncoder pwdEncoder) {
        this.pwdEncoder = pwdEncoder;
    }

	@Autowired
    public void setDao(UserDao dao){
        this.dao = dao;
    }
}

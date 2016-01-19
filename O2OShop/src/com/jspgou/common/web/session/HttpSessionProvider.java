package com.jspgou.common.web.session;

import java.io.Serializable;
import javax.servlet.http.*;

/**
* This class should preserve.
* @preserve
*/
public class HttpSessionProvider implements SessionProvider {
	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(true);
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}
    }

    public void setAttribute(HttpServletRequest request,
    		HttpServletResponse response, String name, Serializable value){
        HttpSession httpsession = request.getSession();
        httpsession.setAttribute(name, value);
    }

	public String getSessionId(HttpServletRequest request,
			HttpServletResponse response) {
		return request.getSession().getId();
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Object defsite=null;
		Object defstreet=null;
		Object imei=null;
		if (session != null) {
			if(session.getAttribute("defsite")!=null){
				defsite=request.getSession().getAttribute("defsite");
			}
			if(session.getAttribute("defstreet")!=null){
				defstreet=session.getAttribute("defstreet");
			}
			if(session.getAttribute("imei")!=null){
				imei=session.getAttribute("imei");
			}
			session.invalidate();
			session = request.getSession(true);
			session.setAttribute("imei", imei);
			session.setAttribute("defstreet", defstreet);
			session.setAttribute("defsite", defsite);
		}
	}
}

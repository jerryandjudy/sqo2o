Core={};
Core.username = function(def) {
	var rememberMe = $.cookie('remember_me_cookie');
	if(rememberMe!=null) {
		alert(Base64.decode(rememberMe));
		return "";
	} else {
		return def;		
	}
}
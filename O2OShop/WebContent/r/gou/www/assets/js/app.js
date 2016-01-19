function fleshVerify() {
	$('#verifyImg').click();
}
function showalert(info){
	var e = $('#modal-alert');
	e.find('.am-modal-bd').text(info);
	e.modal('open');
}
function showconfirm(info,b){
	var e = $('#modal-confirm');
	e.find('.am-modal-bd').text(info);
	e.modal({
		relatedTarget: b,
		onConfirm: function(options) {
			var $link = $(this.relatedTarget);
			try
			{
				if(typeof(eval(confrimed))=="function")
				{
					confrimed($link);
				}
			}catch(e){}
			return true;
		}
	});
}

function getCode(){
	var verify = $('#verify').val();
	var account = $('#account').val();
	if(account == ''){
		showalert('请输入手机号码~');
		return;
	}
	if(verify == ''){
		showalert('请输入验证码~');
		return;
	}
	var phone = $('input[name="account"]').val();
	$('#getCodeBtn').button('loading');
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/sendCode.jspx",
		data: {
			username: phone,
			checkcode: verify
		},
		success: function(result) {
			if (result.status) {
				$('#verify').prop('disabled',true);
				$('#code').prop('disabled',false);
				$('#moreinfo').removeClass('am-hide');
				showalert('短信验证码已下发，请注意接收查看！');
				var count = 60;
                var countdown = setInterval(CountDown, 1000);
                function CountDown() {
                    $("#getCodeBtn").text("重新获取("+count+")");
                    if (count == 0) {
                        $("#getCodeBtn").text("获取验证码").button("reset");
						fleshVerify();
						$('#verify').prop('disabled',false);
                        clearInterval(countdown);
                    }
                    count--;
                }
			}else{
				showalert(result.info);
				$('#getCodeBtn').button('reset');
				fleshVerify();
			}
		}
	});
}
function getCodes(){
	var verify = $('#verify').val();
	var account = $('#account').val();
	var types = $('#types').val();
	if(account == ''){
		showalert('请输入手机号码~');
		return;
	}
	if(verify == ''){
		showalert('请输入验证码~');
		return;
	}
	var phone = $('input[name="account"]').val();
	$('#getCodeBtn').button('loading');
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/sendCode.jspx",
		data: {
			username: phone,
			checkcode: verify,
			types:types
		},
		success: function(result) {
			if (result.status) {
				$('#verify').prop('disabled',true);
				$('#code').prop('disabled',false);
				$('#moreinfo').removeClass('am-hide');
				showalert('短信验证码已下发，请注意接收查看！');
				var count = 60;
				function CountDown() {
					$("#getCodeBtn").text("重新获取("+count+")");
					if (count == 0) {
						$("#getCodeBtn").text("获取验证码").button("reset");
						fleshVerify();
						$('#verify').prop('disabled',false);
						clearInterval(countdown);
					}
					count--;
				}
				var countdown = setInterval(CountDown, 1000);
				
			}else{
				showalert(result.info);
				$('#getCodeBtn').button('reset');
				fleshVerify();
			}
		}
	});
}
function getResetCode(){
	var verify = $('#verify').val();
	var account = $('#account').val();
	if(account == ''){
		showalert('请输入手机号码~');
		$('#regsubmit').button('reset');
		return;
	}
	if(verify == ''){
		showalert('请输入验证码~');
		$('#regsubmit').button('reset');
		return;
	}
	var phone = $('input[name="account"]').val();
	$('#getCodeBtn').button('loading');
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/resetsendCode.jspx",
		data: {
			username: phone,
			checkcode: verify
		},
		success: function(result) {
			if (result.status) {
				$('#verify').prop('disabled',true);
				$('#code').prop('disabled',false);
				$('#moreinfo').removeClass('am-hide');
				showalert('短信验证码已下发，请注意接收查看！');
				var count = 60;
				function CountDown() {
					$("#getCodeBtn").text("重新获取("+count+")");
					if (count == 0) {
						$("#getCodeBtn").text("获取验证码").button("reset");
						fleshVerify();
						$('#verify').prop('disabled',false);
						clearInterval(countdown);
					}
					count--;
				}
				var countdown = setInterval(CountDown, 1000);
				
			}else{
				showalert(result.info);
				$('#getCodeBtn').button('reset');
				fleshVerify();
			}
		}
	});
}


function toggleLoading(active){
	var loading = $('#modal-loading');
	var dimmer = $(".am-loading-dimmer");
	if("close" === active){
		dimmer.addClass("am-hide");
		loading.removeClass("am-modal-active");
		loading.css("display","none");
	}else{
		var top = -parseInt(loading.height() / 2, 10) + 'px';
		dimmer.removeClass("am-hide");
		loading.addClass("am-modal-active");
		loading.css("display","block");
		loading.css("margin-top",top);
	}
}





$(function(){
	$.fn.scrollLoading = function(options) {
        var defaults = {
            attr: "data-url",
            container: $(window),
            callback: $.noop
        };
        var params = $.extend({}, defaults, options || {});
        params.cache = [];
        $(this).each(function() {
            var node = this.nodeName.toLowerCase(),
                url = $(this).attr(params["attr"]);
            //重组
            var data = {
                obj: $(this),
                tag: node,
                url: url
            };
            params.cache.push(data);
        });

        var callback = function(call) {
            if ($.isFunction(params.callback)) {
                params.callback.call(call.get(0));
            }
        };
        //动态显示数据
        var loading = function() {

            var contHeight = params.container.height();
            if ($(window).get(0) === window) {
                contop = $(window).scrollTop();
            } else {
                contop = params.container.offset().top;
            }

            $.each(params.cache, function(i, data) {

                var o = data.obj,
                    tag = data.tag,
                    url = data.url,
                    post, posb;

                if (o) {
                    post = o.offset().top - contop, posb = post + o.height();

                    if ((post >= 0 && post < contHeight) || (posb > 0 && posb <= contHeight)) {
                        if (url) {
                            //在浏览器窗口内
                            if (tag === "img") {
                                //图片，改变src
                                callback(o.attr("src", url));
                            } else {
                                o.load(url, {}, function() {
                                    callback(o);
                                });
                            }
                        } else {
                            // 无地址，直接触发回调
                            callback(o);
                        }
                        data.obj = null;
                    }
                }
            });
        };

        //事件触发
        //加载完毕即执行
        loading();
        //滚动执行
        params.container.bind("scroll", loading);
    };

	$.ajaxSetup({
        beforeSend: function() {
        	toggleLoading('open');
        	return true;
        },
        complete: function() {
        	toggleLoading('close');
        	return true;
        }
    });
    $(".scrollLoading").scrollLoading();
   	$('#chooseLocation').on('close.modal.amui', function(){
		window.location.href = SELF;
	});
	$('[data-toggle="modalhelper"]').on('click', function (e) {
		var url = $(this).attr('data-url');
		var title = $(this).attr('data-title');
		var helper = $('#modal-helper');
		helper.find('.am-popup-title').text(title);
		helper.find('.am-popup-bd').text('');
        helper.find('.am-popup-bd').load(url);
		helper.modal('open');
	});
	$('#loginsubmit').click(function(){
		$('#loginsubmit').button('loading');
		var $form = $('#login-form');
        $.post(SELF, $form.serialize(), function(result) {
			if(result.status){
				if(result.url!==''){
					window.location.href=result.url;
				}else{
					window.location.href=APP;
				}
			}else{
				showalert(result.info);
				$('#loginsubmit').button('reset');
			}
		});
	});
	$('#regsubmit').click(function(){
		$('#regsubmit').button('loading');
		var $form = $('#reg-form');
		var account = $('#account').val();
		var passwd = $('#password').val();
		var passwd2 = $('#repassword').val();
		var verifyCode = $('#code').val();
		if(account == ''){
			showalert('请输入手机号码~');
			$('#regsubmit').button('reset');
			return;
		}else if(verifyCode == ''){
			showalert('请输入短信验证码~');
			$('#regsubmit').button('reset');
			return;
		}else if(passwd == ''){
		   showalert('请填写您的密码~');
		   $('#regsubmit').button('reset');
			return;
		}else if(passwd2 == ''){
			showalert('请再次输入您的密码~');
			$('#regsubmit').button('reset');
			return;
		}else if(passwd != passwd2 || passwd2 != passwd){
			showalert('两次密码输入不一致呢~');
			$('#regsubmit').button('reset');
			return;
		} else{
        $.post(SELF, $form.serialize(), function(result) {
			if(result.status){
				if(result.url!=''){
					window.location.href=result.url;
				}else{
					window.location.href=APP;
				}
			}else{
				showalert(result.info);
				$('#regsubmit').button('reset');
			}
		});
		}
	});
	$('#bldgysregsubmit').click(function(){
		$('#bldgysregsubmit').button('loading');
		var $form = $('#reg-form');
		var account = $('#account').val();
		var companyName = $('#companyName').val();
		var passwd = $('#password').val();
		var passwd2 = $('#repassword').val();
		var verifyCode = $('#code').val();
		if(account == ''){
			showalert('请输入手机号码~');
			$('#bldgysregsubmit').button('reset');
			return;
		}else if(companyName == ''){
			showalert('请输入名称~');
			$('#bldgysregsubmit').button('reset');
			return;
		}else if(verifyCode == ''){
			showalert('请输入短信验证码~');
			$('#bldgysregsubmit').button('reset');
			return;
		}else if(passwd == ''){
			showalert('请填写您的密码~');
			$('#bldgysregsubmit').button('reset');
			return;
		}else if(passwd2 == ''){
			showalert('请再次输入您的密码~');
			$('#bldgysregsubmit').button('reset');
			return;
		}else if(passwd != passwd2 || passwd2 != passwd){
			showalert('两次密码输入不一致呢~');
			$('#bldgysregsubmit').button('reset');
			return;
		} else{
			$.post(SELF, $form.serialize(), function(result) {
				if(result.status){
					if(result.url==2){
						window.location.href='http://kcgl.nhaocang.com/kcgl';
						return;
					}else{
						window.location.href='http://kcgl.nhaocang.com/kcgl';
						return;
					}
				}else{
					showalert(result.info);
					$('#bldgysregsubmit').button('reset');
				}
			});
		}
	});
	$('#completesubmit').click(function(){
		$('#completesubmit').button('loading');
		var $form = $('#complete-form');
        $.post(SELF, $form.serialize(), function(result) {
			if(result.status){
				if(result.url!==''){
					window.location.href=result.url;
				}else{
					window.location.href=APP+'/login.jspx';
				}
			}else{
				showalert(result.info);
				$('#completesubmit').button('reset');
			}
		});
	});
	$('#resetpasssubmit').click(function(){
		$('#resetpasssubmit').button('loading');
		var $form = $('#reg-form');
		var account = $('#account').val();
		
		var passwd = $('#password').val();
		var passwd2 = $('#repassword').val();
		var verifyCode = $('#code').val();
		if(account == ''){
			showalert('请输入手机号码~');
			$('#resetpasssubmit').button('reset');
			return;
		}else if(verifyCode == ''){
			showalert('请输入短信验证码~');
			$('#resetpasssubmit').button('reset');
			return;
		}else if(passwd == ''){
		   showalert('请填写您的密码~');
		   $('#resetpasssubmit').button('reset');
			return;
		}else if(passwd2 == ''){
			showalert('请再次输入您的密码~');
			$('#regsubmit').button('reset');
			return;
		}else if(passwd != passwd2 || passwd2 != passwd){
			showalert('两次密码输入不一致呢~');
			$('#resetpasssubmit').button('reset');
			return;
		} else{
        $.post(SELF, $form.serialize(), function(result) {
			if(result.status){
				if(result.url!==''){
					window.location.href=result.url;
				}else{
					window.location.href=APP;
				}
			}else{
				showalert(result.info);
				$('#resetpasssubmit').button('reset');
			}
		});
		}
	});
	$('#checkoutsubmit').click(function(){
		$('#checkoutsubmit').button('loading');
		var $form = $('#piorder-form');
        $.post(APP+'/Order/doorder', $form.serialize(), function(result) {
			if(result.status){
				if(result.info!=='ok'){
					window.location.href=APP+"/redirectPay.html?id="+result.info+"&showwxpaytitle=1";
				}else{
					window.location.href=APP+"/myorder.html";
				}
			}else{
				showalert(result.info);
				$('#checkoutsubmit').button('reset');
			}
		});
	});
	$('#presellcheckoutsubmit').click(function(){
		$('#presellcheckoutsubmit').button('loading');
		var $form = $('#piorder-form');
        $.post(APP+'/Order/dopresellorder', $form.serializeArray(), function(result) {
			if(result.status){
				if(result.info!=='ok'){
					window.location.href=APP+"/redirectPay.html?id="+result.info+"&showwxpaytitle=1";
				}else{
					window.location.href=APP+"/myorder.html";
				}
			}else{
				showalert(result.info);
				$('#presellcheckoutsubmit').button('reset');
			}
		});
	});
	$('#latticecheckoutsubmit').click(function(){
		$('#latticecheckoutsubmit').button('loading');
		var $form = $('#piorder-form');
        $.post(APP+'/Order/dolatticeorder', $form.serialize(), function(result) {
			if(result.status){
				if(result.info!=='ok'){
					window.location.href=APP+"/redirectPay.html?id="+result.info+"&showwxpaytitle=1";
				}else{
					showalert('多个店铺订单需要在“我的订单”中分别发起支付哦~~');
					window.location.href=APP+"/myorder.html";
				}
			}else{
				showalert(result.info);
				$('#latticecheckoutsubmit').button('reset');
			}
		});
	});
	$('#selectsubmit').click(function(){
		var $param = [];
		var cat_id = $('#cat_id').val();
		var url = APP+'/list.html?cat=' + cat_id;
		$('select').each(function(){
			if('0' !== $(this).val()){
				url = url + '&' + $(this).attr('name') + '=' + $(this).val();
				$param.push({
					name: $(this).attr('name'),
					value: $(this).val()
				});
			}
		});
		window.location.href = url;
	});

	$('.c-address-list li').click(function(){
		$('.c-address-list li').removeClass('selected');
		var location = $(this).attr('data-location');
		var carttype = $(this).attr('data-carttype');
		$(this).addClass('selected');
		$(this).find('input').prop("checked",'true');
		$('.noshow').removeClass('am-hide');
		if('lattice' == carttype){
			$('#GoodsCoupons').load(APP+"/Order/getLGoods/location/"+location);
		}else{
			$("#DelPay").load(APP+"/Order/getDelPay/location/"+location);
			$('#GoodsCoupons').load(APP+"/Order/getGoodsCoupons/location/"+location);
			reprice();
		}
		//$('#addressBD').collapse('close');
	});

	$('.am-panel-collapse').on('close.collapse.amui',function(){
		$(this).parent().find('.cbh').removeClass('am-icon-chevron-up');
		$(this).parent().find('.cbh').addClass('am-icon-chevron-down');
	});

	$('.am-panel-collapse').on('open.collapse.amui',function(){
		$(this).parent().find('.cbh').removeClass('am-icon-chevron-down');
		$(this).parent().find('.cbh').addClass('am-icon-chevron-up');
	});

});
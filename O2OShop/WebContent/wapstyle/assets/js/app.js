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

function clickOrderBy(a){
	$("#orderBy").val(a);
	var jvForm = $("#jvfrom");
	jvForm.onsubmit=null;
	jvForm.submit();
}
function chooseLocation(id,url){
	//store.set('site', {id:id, url:url});
	$.get(APP + '/wap/chooseLocation.jspx',{id:id},function(result){
		window.location.href = url;
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
		url: APP + "/wap/resetsendCode.jspx",
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
function getCode(){
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
function changearea(value){
	$.ajax({
        type: "POST",
        dataType: "json",
		url: APP + "/Member/getLocation",
		data: {
			id: value
		},
		success: function(msg) {
			if (msg.status) {
				$('#new-location option').remove();
				$("#new-location").append("<option>请选择</option>");//赋值
				$.each(msg.data, function (i, item) {
					$("#new-location").append("<option value=" + item.id + ">" + item.name + "</option>");//赋值
				});
			}
		}
    });
}
function changelocation(value){
	$.ajax({
        type: "POST",
        dataType: "json",
		url: APP + "/Member/getLocation",
		data: {
			id: value
		},
		success: function(msg) {
			if (msg.status) {
				$('#new-building option').remove();
				$("#new-building").append("<option>请选择</option>");//赋值
				$.each(msg.data, function (i, item) {
					$("#new-building").append("<option value=" + item.id + ">" + item.name + "</option>");//赋值
				});
				$('#new-building option:first').prop('selected',true);
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
function addcart(e){
	var parents = $(e).parents(".good-detail");
	var img = parents.find(".good-pic").attr('src');
	var name = parents.find('.good-name').text();
	var goodid = $(e).attr('data-goodid');
	var price = $(e).attr('data-price');
	var store = $(e).attr('data-store');
	var carttype = $(e).attr('data-carttype');
	var good = $(e).attr('good');
	$('body').append('<div class="floatOrder"><i class="am-icon-shopping-cart am-text-primary"></i></div>');
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/cart/add_orderItem.jspx",
		async: false,
		data: {
			productId: goodid,
			name: name,
			price: price,
			productAmount: 1
			
		},
		success: function(msg) {
			if(msg.status==0){
				window.location.href=APP+"/wap/waplogin.jspx?returnUrl="+window.location.href;
			}else if (msg.status==1) {
				var obj = $('.floatOrder');
				var cy = $('#cnum').offset().top;
				var cx = $('#cnum').offset().left;
				var ty = $(e).offset().top;
				var tx = $(e).offset().left;
				obj.css({'left': tx,'top': ty});
				obj.animate({
					"opacity": 0,
					"top":cy,
					"left":cx
				},1000,function(){
					obj.remove();
					var oldnum = $('#cnum').text();
					if(''===oldnum){
						oldnum = 0;
					}
					var num = parseInt(oldnum,0)+1;
					$('#cnum').text(parseInt(num,0));
				});
				$('#inok').show();
				setTimeout("$('#inok').fadeOut(1000)",800);
			}else{
				$('#inoksb').show();
				setTimeout("$('#inoksb').fadeOut(1000)",800);
			}
		},
		error:function(msg){
				$('#inoksb').show();
				setTimeout("$('#inoksb').fadeOut(1000)",800);
		}
	});
}
function reprice(){
	var all_price = 0;
	var location = $('input[name="location"]:checked').parent('li').attr('data-location');
	var coupon = $('input[name="coupon"]:checked').val();
	var delivery = $('input[name="delivery"]:checked').val();
	var payment = $('input[name="payment"]:checked').val();
//	$("#final_price").load(APP+"/Order/reprice/location/"+location+"/coupon/"+coupon+"/delivery/"+delivery+"/payment/"+payment);
}

function inc(id,carttype){
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/cart/ajaxUpdateCartItem.jspx",
		data: {
			cartItemId: id,
			count: parseInt($('#good_num_'+id).val())+1
		},
		success: function(msg) {
			if (msg.status==1) {
				$('#good_num_'+id).val(msg.info);
				$('#spje').text(msg.subtotal);
				$('#yfje').text(msg.subtotal);
				$('#cnum').text(msg.count);
			  //reprice();
			}
		}
	});
}
function dec(id,carttype){
	$.ajax({
		type: "POST",
		dataType: "json",
		url: APP + "/cart/ajaxUpdateCartItem.jspx",
		data: {
			cartItemId: id,
			count: parseInt($('#good_num_'+id).val())-1
		},
		success: function(msg) {
			if (msg.status==1) {
				if(0 == msg.count){
					window.location.href=APP;
				}
				if(0 == msg.info){
					//$('#good_'+id).remove();
					window.location.href=APP+'/wap/cartself.jspx';
					//reprice();
				}else{
					$('#good_num_'+id).val(msg.info);
					//reprice();
				}
				$('#cnum').text(msg.count);
				$('#spje').text(msg.subtotal);
				$('#yfje').text(msg.subtotal);
			}
		}
	});
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
		//window.location.href = SELF;
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
		var rememberme=$("#rememberme").is(':checked');
		if($("#account").val()==''){
			showalert("用户名不能为空");
			$('#loginsubmit').button('reset');
			return;
		}
		if($("#password").val()==''){
			showalert("密码不能为空");
			$('#loginsubmit').button('reset');
			return;
		}
        $.post(APP+"/wap/waplogin.jspx", $form.serialize(), function(result) {
			if(result.status==true){
				if(rememberme==true){
					$.cookie("account", $("#account").val(),{expires:350});
				}else{
					$.cookie("account", null);
				}
				if(result.url!==''){
					window.location.href=result.url;
				}else{
					window.location.href=SELF;
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
				if(result.url!==''){
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
	$('#completesubmit').click(function(){
		$('#completesubmit').button('loading');
		var $form = $('#complete-form');
        $.post(SELF, $form.serialize(), function(result) {
			if(result.status){
				if(result.url!==''){
					window.location.href=result.url;
				}else{
					window.location.href=APP+'/login.html';
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
		if($("input[name='deliveryInfo']:checked").length<=0){
			showalert("请选择收货地址");
		     return;
			} 
			if($("input[name='paymentMethodId']:checked").length<=0){
				showalert("请选择支付方式");
			     return;
				} 
			if ($("input[name='shippingMethodId']:checked").length <= 0) {
				showalert("请选择配送方式");
			     return;
			}
			
			$('#content').text('确定提交订单？');
			 $('#modal-confirm').modal({
			      relatedElement: this,
			      onConfirm: function() {
			    		$('#checkoutsubmit').button('loading');
			    		var $form = $('#piorder-form');
			            $.post(APP+'/order/wap_order_shipping.jspx', $form.serialize(), function(result) {
			    			if(result.status){
			    				if(result.info!='ok'){//在线支付
			    					window.location.href=result.url;
//			    					window.location.href=APP+"/wap/waporderlist.jspx";
			    				}else{ //货到付款
			    					window.location.href=APP+"/wap/waporderlist.jspx";
			    				}
			    			}else{
			    				if(result.info!='' && result.info!='nologin'){
			    					window.location.href=APP+"/wap/waplogin.jspx";
			    					return;
			    				}
			    				showalert(result.info);
			    				$('#checkoutsubmit').button('reset');
			    			}
			    		});
			    	  $('#content').text('');
			      },
			      onCancel: function() {
			      }
			    });
			return;
	
	});
	$('#checkoutfwsubmit').click(function(){
		if($("input[name='deliveryInfo']:checked").length<=0){
			showalert("请选择收货地址");
			return;
		} 
		$('#checkoutfwsubmit').button('loading');
		var $form = $('#piorder-form');
		$.post(APP+'/wap/addSqOrder.jspx', $form.serialize(), function(result) {
			if(result.status){
					window.location.href=APP+"/wap/wapfworderlist.jspx";
			}else{
				if(result.info!='' && result.info=='nologin'){
					window.location.href=APP+"/wap/waplogin.jspx?returnUrl="+APP+result.returnUrl;
					return;
				}
				showalert(result.info);
				$('#checkoutfwsubmit').button('reset');
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
		var cat_id = $('#cat_id').val();
		$("#categoryId").val(cat_id);
		var jvForm = $("#jvfrom");
		jvForm.onsubmit=null;
		jvForm.submit();
	});
	$('#sqfwselectsubmit').click(function(){
		var jvForm = $("#jvfrom");
		jvForm.onsubmit=null;
		jvForm.submit();
	});

	$('.c-address-list li').click(function(){
		$('.c-address-list li').removeClass('selected');
		var location = $(this).attr('data-location');
		var carttype = $(this).attr('data-carttype');
		$(this).addClass('selected');
		$(this).find('input').prop("checked",'true');
		$('.noshow').removeClass('am-hide');
//		if('lattice' == carttype){
//			$('#GoodsCoupons').load(APP+"/Order/getLGoods/location/"+location);
//		}else{
//			$("#DelPay").load(APP+"/Order/getDelPay/location/"+location);
//			$('#GoodsCoupons').load(APP+"/Order/getGoodsCoupons/location/"+location);
//			reprice();
//		}
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

function openlist(){
	$('#list-select').offCanvas('open');
}
function closelist(){
	$('#list-select').offCanvas('close');
}
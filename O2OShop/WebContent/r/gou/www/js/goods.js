// JavaScript Document
$(document).ready(function(){
	
//商品４个大图点击轮回
	$('.pic_index').find('li').hover(function(){
		$(this).addClass('pic_on').siblings().removeClass('pic_on');									  
		$('#pic_box').find('div').eq($('.pic_index').find('li').index(this)).show().siblings().hide();	
	}, function(){			
	});
	// 关闭弹出层	
	$(".close").live("click", function(){
	$("#popbg").fadeOut();
	$(this).parent().parent().parent().fadeOut();
	});	
	$(".jqzoom").jqueryzoom({xzoom:286,yzoom:286,offset:15,position:"right",preload:0,lens:1});
	//$(".list").find("img").lazyload({ effect : "fadeIn",placeholder : "http://i4.quwan.com/themes/default/imgs/global/grey.gif"});
	
	// 分享给好友
	$('.share').click(function(){	
		$('#share_pop').fadeIn();
	});
	// 收藏弹出层
	//$('.collect').click(function(){	
	//	$('#coll_pop').fadeIn();
	//});

	
	// 商品颜色款式 模拟下拉菜单	
	var gstyle_max=211;
	$('.gstyle').hover(function(){	
		$(this).addClass('gstyle_on');
		if($('.gstyle_pop').width()<211)
		{
			$('.gstyle_pop').width(211);
			$('.gstyle_pop').find('a').width(211);
		}
		$('.gstyle_pop').show();			
		if(gstyle_max==211)	
		{ 
			$('.gstyle_pop').find('li').each(function(index) {       
			  if ($('.gstyle_pop').find('li').eq(index).width()>=gstyle_max) 
			  {
			   gstyle_max=$(this).width();
			  }
		  });
		 $('.gstyle_pop').find('li').each(function(index) {       
			 $('.gstyle_pop').find('li').eq(index).width(gstyle_max);		  
		  });
		}		
		$('.gstyle').find('.gstyle_pop').find('li').eq(parseInt($(this).attr('select'))).addClass('gpop_on').siblings().removeClass('gpop_on');
	},function(){
	    $('.gstyle_pop').hide();
		$(this).removeClass('gstyle_on');
	});	
	$('.gstyle_pop').find('li').hover(function(){
		$(this).addClass('gpop_on').siblings().removeClass('gpop_on');
		$('.gstyle').removeClass('gstyle_on');
	}, function(){		
	});		

	//组合商品板块切换(需要添加具体显示隐藏的板块操作)
	$('.cscrip_1').click(function(){
		$(this).addClass('cscrip_on');
		$('#combin_1').show();
		$('#combin_2').hide();
		$('#combin_3').hide();
		$('#combin_4').hide();
		$('#combin_5').hide();
		$('.cscrip_2').removeClass('cscrip_on')	
		$('.cscrip_3').removeClass('cscrip_on')
		$('.cscrip_4').removeClass('cscrip_on')
		$('.cscrip_5').removeClass('cscrip_on')
		
	});	
	$('.cscrip_2').click(function(){
		$(this).addClass('cscrip_on');
		$('#combin_1').hide();
		$('#combin_2').show();
		$('#combin_3').hide();
		$('#combin_4').hide();
		$('#combin_5').hide();
		$('.cscrip_1').removeClass('cscrip_on')	
		$('.cscrip_3').removeClass('cscrip_on')	
		$('.cscrip_4').removeClass('cscrip_on')
		$('.cscrip_5').removeClass('cscrip_on')
	});	
	$('.cscrip_3').click(function(){
		$(this).addClass('cscrip_on');
		$('#combin_1').hide();
		$('#combin_2').hide();
		$('#combin_3').show();
		$('#combin_4').hide();
		$('#combin_5').hide();
		$('.cscrip_1').removeClass('cscrip_on')	
		$('.cscrip_2').removeClass('cscrip_on')	
		$('.cscrip_4').removeClass('cscrip_on')
		$('.cscrip_5').removeClass('cscrip_on')
	});
	$('.cscrip_4').click(function(){
		$(this).addClass('cscrip_on');
		$('#combin_1').hide();
		$('#combin_2').hide();
		$('#combin_3').hide();
		$('#combin_4').show();
		$('#combin_5').hide();
		$('.cscrip_1').removeClass('cscrip_on')	
		$('.cscrip_2').removeClass('cscrip_on')	
		$('.cscrip_3').removeClass('cscrip_on')
		$('.cscrip_5').removeClass('cscrip_on')
	});
	$('.cscrip_5').click(function(){
		$(this).addClass('cscrip_on');
		$('#combin_1').hide();
		$('#combin_2').hide();
		$('#combin_3').hide();
		$('#combin_4').hide();
		$('#combin_5').show();
		$('.cscrip_1').removeClass('cscrip_on')	
		$('.cscrip_2').removeClass('cscrip_on')	
		$('.cscrip_3').removeClass('cscrip_on')
		$('.cscrip_4').removeClass('cscrip_on')
	});

	
	
	//商品详情、买家评论、购买咨询、服务承诺、商品图片板块切换(需要添加具体显示隐藏的板块操作)
	$('.scrip_1').click(function(){
		$(this).addClass('scrip_on');
		$('.scrip_2').removeClass('scrip_on');
		$('.scrip_3').removeClass('scrip_on');
		$('.scrip_4').removeClass('scrip_on');
		$('.scrip_5').removeClass('scrip_on');
		$('.details1').show();
		$('.details2').show();
		$('.details3').show();
		$('.details4').hide();
		$('.details5').hide();
		$('#pl1').addClass('zixun_on');
		$('#pl2').removeClass('zixun_on')
		$('#zx1').addClass('zixun_on');
		$('#zx2').removeClass('zixun_on')		
		//$('#page_box_pl').hide();
		// 获取评论
		get_pl($('#goods_id').text(), 0, 1,0);
		// 获取咨询
		getcomment($('#goods_id').text(),'1',0,0);	
	});	
	$('.scrip_2').click(function(){
		$(this).addClass('scrip_on');
		$('.scrip_1').removeClass('scrip_on');	
		$('.scrip_3').removeClass('scrip_on');	
		$('.scrip_4').removeClass('scrip_on');
		$('.scrip_5').removeClass('scrip_on');
		$('.details1').hide();
		$('.details2').show();
		$('.details3').hide();
		$('.details4').hide();
		$('.details5').hide();
		//$('#page_box_pl').show();
		$('#pl1').addClass('zixun_on');
		$('#pl2').removeClass('zixun_on')
		// 获取评论
		get_pl($('#goods_id').text(), 0, 1,0);
	});	
	$('.scrip_3').click(function(){
		$(this).addClass('scrip_on');
		$('.scrip_1').removeClass('scrip_on');	
		$('.scrip_2').removeClass('scrip_on');	
		$('.scrip_4').removeClass('scrip_on');
		$('.scrip_5').removeClass('scrip_on');
		$('.details1').hide();
		$('.details2').hide();
		$('.details3').show();
		$('.details4').hide();
		$('.details5').hide();
		$('#zx1').addClass('zixun_on');
		$('#zx2').removeClass('zixun_on')
		// 获取咨询
		getcomment($('#goods_id').text(),'1',0,0);
	});
	$('.scrip_4').click(function(){
		$(this).addClass('scrip_on');
		$('.scrip_1').removeClass('scrip_on');	
		$('.scrip_2').removeClass('scrip_on');	
		$('.scrip_3').removeClass('scrip_on');
		$('.scrip_5').removeClass('scrip_on');
		$('.details1').hide();
		$('.details2').hide();
		$('.details3').hide();
		$('.details4').show();
		$('.details5').hide();
	});
	$('.scrip_5').click(function(){
		$(this).addClass('scrip_on');
		$('.scrip_1').removeClass('scrip_on');	
		$('.scrip_2').removeClass('scrip_on');	
		$('.scrip_3').removeClass('scrip_on');
		$('.scrip_4').removeClass('scrip_on');
		$('.details1').hide();
		$('.details2').hide();
		$('.details3').hide();
		$('.details4').hide();
		$('.details5').show();
		// 获取用户传图
		getgoodspic($('#goods_id').text(),'1',0);
	});
	
	
	// 购物数量 加一件
	$('#num_up').click(function(){
		$('#buynum').val(parseInt($('#buynum').val()) + 1);
	});
	// 购物数量 减一件
	$('#num_down').click(function(){
		if(parseInt($('#buynum').val()) >= 2){
			$('#buynum').val( parseInt($('#buynum').val()) - 1);
		}
	});


	//全部评论，最有价值评论标签切换
	$('#pl1').click(function(){															   
		if($(this).hasClass("zixun_on"))
		{
			$('#pl2').removeClass('zixun_on');			
		}
		else
		{
			$(this).addClass('zixun_on');
			$('#pl2').removeClass('zixun_on')
		}
		get_pl($('#goods_id').text(), 0, 1,0);
	});	
	$('#pl2').click(function(){															   
		if($(this).hasClass("zixun_on"))
		{
			$('#pl1').removeClass('zixun_on');			
		}
		else
		{
			$(this).addClass('zixun_on');
			$('#pl1').removeClass('zixun_on');	
		}
		get_pl($('#goods_id').text(), 0, 1,1);
	});
	
	
	//全部咨询，最有价值咨询标签切换
	$('#zx1').click(function(){															   
		if($(this).hasClass("zixun_on"))
		{
			$('#zx2').removeClass('zixun_on');			
		}
		else
		{
			$(this).addClass('zixun_on');
			$('#zx2').removeClass('zixun_on')
		}
		getcomment($('#goods_id').text(),'1',0,0);
	});	
	$('#zx2').click(function(){															   
		if($(this).hasClass("zixun_on"))
		{
			$('#zx1').removeClass('zixun_on');			
		}
		else
		{
			$(this).addClass('zixun_on');
			$('#zx1').removeClass('zixun_on');	
		}
		getcomment($('#goods_id').text(),'1',0,1);
	});
	
	//创意，功能，做工星级查看
	$('.star_in').hover(function(){											 
		$(this).find('.starpop').fadeIn();	
	}, function(){	
		$(this).find('.starpop').hide();		
	});
	
	//送货地址选择操作（计算函数在这里调用）
	$('#addr_list').find('td').hover(function(){
		$('#addr_list').find('td').removeClass('addr_on');									  
		$(this).addClass('addr_on');		
	}, function(){	
	
	});	
	$('#addr_list').find('td').click(function(){			
		$(this).addClass('addr_on');
		$('#addr').html($(this).html());
		$('#addr').attr("name",$(this).attr("name"));
		$('#addr_pop').hide();
		getshopingmoney();
	});
	
	//送货地址列表弹出
	$('#addr_do').hover(function(){
		$('#addr_pop').fadeIn(100);
		$('#gstyle').hide();	
	}, function(){	
		$('#addr_pop').hide();	
		$('#gstyle').show();	
	});
	
	// 初始加载运费（北京）
	getshopingmoney(2);
	// 获取咨询
	getcomment($('#goods_id').text(),'1',0,0);
	// 获取评论
	get_pl($('#goods_id').text(), 0, 1,0);
	<!-- {if $static_div eq 1} -->
	// 调用历史浏览记录
	get_histor();
	<!-- {/if} -->
	// 判断是否提交 了解评论
	//stepCookieAction($('#goods_id').text(),'{$smarty.session.user_id}');
	// 检测是否登录
	setHeadUserStatus();
	//购物车数量
	setHeadFlowNum();
	// 获取首次详情页随机同类商品组合
	//get_load_simila_goods();
	// 获取首次详情页随便看看商品组合
	//get_load_casual_goods();
	
	//更新商品的价格
	update_goods_price($('#goods_id').text());
});	



/*------更新商品的价格 ------*/
/*param    $goods_id  商品ID */
function update_goods_price(goods_id){
	if (goods_id){
		var id = goods_id
	}
	$.ajax({
		type: "GET",
		url: "goods.php?act=ajax_price&id="+id+"&m=" + Math.random(),
		date: "",
		success: function(msg){
			var re = eval("(" + msg + ")");
			if (re.error == "1"){
				$("#shop_price1").html(re.content.price1);
				$("#shop_price2").html(re.content.price2);
			}
		}
	})
}


/*------获取用户咨询 ------*/
/*param    $goods_id  商品ID */
/*param    $page      页数 */
/*param int   $cmt_type  评论类型 */
/*param int   $is_essence  是否精华 */
function getcomment(goods_id, page,cmt_type,is_essence){
	if (goods_id){
		var id = goods_id
	}
	else{
		$("#show_comments").html("非法数据!");
		return false
	}
	var pager = $int(page);
	if(cmt_type == ''){
		var cmt_type = 0;
	}
	$.ajax({
		type: "GET",
		url: "goods_comment.php",
		data: "id=" + id + "&page=" + pager + "&act=gotopage&type=" + cmt_type + "&is_essence=" + is_essence + "&" + Math.random(),
		beforeSend:function(){
			$("#show_comments").text("获取中... ...");
		},
		success: function(msg){
			var re = eval("(" + msg + ")");
			if (re.totalcomments != "0"){
				$("#show_comments").html(re.content);
				$("#user_comment_count").text(re.count);
				$("#user_comment_count2").text(re.count2);
			}
		}
	})
}

/*------获取用户评论 ------*/
function get_pl(goods_id, page_num, pl_location,is_essence){
	if (goods_id){
		var id = goods_id
	}
	else{
		$("#goods_comments_list_box_body").html("非法数据！");
		return false
	}
	
	if($('.scrip_1').hasClass('scrip_on'))
	{
		pl_location = 2;
	}

//	d = $('.goods_tabs_title_box').find('li').eq(0).attr('class');
//	if ('goods_tabs_title_box_li_1 goods_tabs_title_box_li_selected' == d)
//	{
//		pl_location = 2
//	}
//	
//	var order = $("#goods_comments_list_way").val() ? $("#goods_comments_list_way").val() : $int(order);
	var order = 2;
	var pager = $int(page_num);
	
	$.ajax({
		type: "GET",
		url: "goods.php?act=get_pl_page&" + Math.random(),
		data: "goods_id=" + goods_id + "&orders=" + order + "&is_essence=" + is_essence + "&page=" + page_num ,
		beforeSend:function(){
			$("#goods_comments_list_box_body").text("数据查询中... ...");
		},
		success: function(msg){
			var re = eval("(" + msg + ")");
			if (1 == pl_location)
			{
				$("#goods_comments_list_box_body").html(re.content);
				$("#comment_count").text(re.count);
				$("#comment_count2").text(re.count2);				
				$('#page_box_pl').show();
			}
			else if (2 == pl_location)
			{
				$("#goods_comments_list_box_body").html(re.content);
				$("#comment_count").text(re.count);
				$("#comment_count2").text(re.count2);				
				//$('#page_box_pl').hide();// 去掉详情首页首次加载不显示翻页
				$('#page_box_pl').show();
			}
		}
	
	})
}




// ajax 计算运费
function getshopingmoney(A){
	var res;
	var cityid;
	if(!A){
		cityid	= $('#addr').attr("name");
	}else{
		cityid	= A;
	}
	var goods_id  = $('#goods_id').text();
	
	$.ajax({
		type: "GET",
		url: "flow.php?step=get_shipping_money",
		cache: false,
		data: 'goods_id=' + goods_id + '&city=' + cityid + "&m=" + Math.random(),
		beforeSend:function(){
			$("#kuaidi").text("运费计算中... ...");
		},
		success:function(result){
			result = $.evalJSON(result);
			a = result;
			if(a != null)
			{
				// 取6 普通快递
				if(a[6]){
					res_6 = a[6] + ' 元';
				}
				else if(null == a[6]){
					res_6 = '不可达';
				}
				else if(0 == a[6]){
					res_6 = '已免';
				}
				else{
					res_6 = '不可达';
				}
				// 取15 货到付款
				if(a[15]){
					res_15 = a[15] + ' 元';
				}
				else if(null == a[15]){
					res_15 = '不可达';
				}
				else if(0 == a[15]){
					res_15 = '已免';
				}
				else{
					res_15 = '不可达';
				}
				// 取10 邮局平邮
				// 取4 EMS
				if(a[4]){
					res_4 = a[4] + '元';
				}else if(null == a[4]){
					res_4 = '不可达';
				}else if(0 == a[4]){
					res_4 = '已免';
				}else{
					res_4 = '不可达';
				}
					// 取14 顺丰速运
			}
			else
			{
				res_6 = '不可达';
				res_15 = '不可达';
				res_4 = '不可达';
			}
			if(res_15=='不可达'&&res_4=='不可达'){
				var str = "EMS:" + res_4;
			}else if(res_15=='不可达'){
				var str = "快递："+res_6+" EMS:"+res_4;
			}else{
				var str = "快递："+res_6+"　货到付款："+res_15;	
			}
			
			$("#kuaidi").text(str);
		}
	});
}



//  加入购物车 函数
// tylergong 2010-05-28
/*
 *	good_id 	商品id
 *	good_name 	商品名称
 *	isRefesh 	是否有弹出层
 *	isIntegral	是否积分换购
 */
inToCartAction = function (good_id, good_name, isRefesh, isIntegral, position) {
    var gs  = parseInt($("#gs").val(),10);
    var gns = parseInt($("#buynum").val(),10); 
    var lms = parseInt($("#lianmeng_buy_num").val(),10);

    if (gns == 0)
    {
        alert("购物数量不能为0。");
    }
    else if(gs < gns)
    {
        alert("数量超出库存，请重新填写。");
    }
    else
    {
		if(lms > 0 && gns > lms )
		{
			alert("很抱歉，每单最多购买"+lms+"个");
		}
		else
		{
			//alert(1);
        	GoodsinTooCartAction(good_id, good_name,isRefesh,0,isIntegral, position);
		}
    }
}

//  加入购物车 函数
function GoodsinTooCartAction(goodsId, goodsName, isRefesh, parentId, isIntegral, position) {
    var goods = new Object();
    var spec_arr = new Array();
    var fittings_arr = new Array();
    var select = "";
    var p = parentId == "" ? false: true;
    var number = parseInt($("#buynum").val(),10);
    var refresh = 0;
    if (isRefesh) {
        refresh = 1
    }
    if ($("#goods_type_select")) {
        select = $("#goods_type_select").value
    }
    goods = '{"spec": "","select": "' + select + '", "goods_id":"' + goodsId + '", "number": "' + number + '","parent": "0", "isIntegral": "' + isIntegral + '" }';
    $.ajax({
        type: "POST",
        url: "flow.php?step=add_to_cart",
        data: "goods=" + goods,
        success: function(){
			 $.ajax({
					type: "POST",
					url: "/flow.php?step=ajaxcartnum",
					data: "step=ajaxcartnum",
					success: function(A) {
						var re = eval("(" + A + ")");
						$("#ECS_CARTINFO").html(re.num);
						$(".cart_pop").html(re.content);
					}
				})
		},
        complete: function(XMLHttpRequest, status) {
            result = eval("(" + XMLHttpRequest.responseText + ")");
            if (!result.error) {
                if (refresh == 1) {
                    //windowMessage(goodsName)
					//location.reload()
					//$(".pop_inf").text(goodsName);
					//popdiv("#cat_pop","369","auto",0.4);					
					switch(position)
					{
						case 1:
							$('#cat_pop').hide();
							$('#cat_pop2').hide();
							$('#cat_pop1').fadeIn();					
							break;
						case 2:
							$('#cat_pop').hide();
							$('#cat_pop1').hide();
							$('#cat_pop2').fadeIn();		
							break;
						case 3:
							$('#cat_pop1').hide();
							$('#cat_pop2').hide();
							$('#cat_pop').fadeIn();		
							break;
						default:
							break;
					}
					
                } else {
                    //windowMessage(goodsName)
                    window.location="flow.php";
                }
            } else {
                if (result.error == 2) {
                    if (confirm(result.message)) {
                        location.href = "user.php?act=add_booking&id=" + result.goods_id
                    }
                } else {
                    if (result.error == 6) {
                        if (confirm(result.message)) {
                            location.href = "goods.php?id=" + result.goods_id
                        }
                    } else {
                        alert(result.message)
                    }
                }
            }
        }
    })
}


/*------载入用户是否已经投票：商品详情是否足够 ------*/
function stepCookieAction(B, A, D, C) {
	if (!D){
		D = "(感谢您的投票,这对我们完善商品信息将很有帮助)"
	}
	
	if (!C){
		C = "以上的商品详情对您了解这个商品&nbsp;&nbsp;&nbsp;"
	}
	
	if ($.cookie("goods" + B + "text")){
		$(".vote_line").html(D)
	}
}

// 投票：商品详情是否足够
function stepCookieClickAction(goods_id, user_id, e, desc, title) {
	if (!desc){
		desc = "(感谢您的投票,这对我们完善商品信息将很有帮助)"
	}
	
	if (!title){
		title = "以上的商品详情对您了解这个商品&nbsp;&nbsp;&nbsp;"
	}
	
	if (e == "1" || e == 1){
		var content = "enough_msg";
		var showText =  "已经足够";
	}
	else{
		var content = "not_enough_msg"
		var showText =  "还不够";
	}

	$.cookie("goods" + goods_id + "text", showText, {
		expires: 30
	});

	$.ajax({
		type: "GET",
		url: "interface/goods_msg_count.php",
		data: "id=" + goods_id + "&msg_sign=" + content + "&user=" + user_id,
		success: function(msg){
			var msng = eval("(" + msg + ")");
			if (msng.id == "001" || msg == "true"){
				$.cookie("goods" + goods_id, content, {
					expires: 30
				});
				$(".vote_line").html(desc)
			}
			else{
				alert(msg)
			}
		}
	})
}

// 获取用户上传的图片
function getgoodspic(goods_id, page, ext)
{
	if (goods_id){
		var id = goods_id
	}
	else{
		$("#goods_up_pic_tab").html("非法数据!");
		return false
	}
	var pager = parseInt(page);
	
	$.ajax({
		type: "GET",
		url: "goods_pic.php",
		data: "id=" + id + "&page=" + pager + "&act=gotopage&" + Math.random(),
		success: function(msg){
			var re = eval("(" + msg + ")");
			if (re.count != "0"){
				$("#goods_up_pic_tab").html(re.content);
			}
			else{
				$("#goods_up_pic_tab").html('<div style="padding-bottom:20px;text-align:center;">暂时没有图片</div>');
			}
		}
	})
}

// 点击打开大图浏览
//function picClickAction(B, A) {
//	var C = window.open("gallery.php?id=" + B + "&img=" + A + "", "newwindow", "height=800,width=800")
//}

/*
*调用历史浏览商品
*/
function get_histor()
{
  var goods_id  = $('#goods_id').text();
  $.ajax({
    type: "GET",
    url: "goods.php",
    data: 'goods_id=' + goods_id + "&act=get_history" + "&m=" + Math.random(),
    success: function(msg)
    {
      var re = eval("(" + msg + ")");

      if (re.err_msg == "0")
      {
        $("#visited").show();
      }
      $(".goods_history_visited_box").html(re.result);
    }
  })
}


// 获取同类商品  初始化结果
function get_load_simila_goods()
{
	$.ajax({
		type: "POST",
		url: "goods_group.php?act=similar_load&m="+Math.random(),
		data: 'goods_id='+$('#goods_id').text()+'&structurecodetmp='+$("#structurecodetmp").val(),
		cache: false,
		beforeSend:function(){
			//$(".g_1").html('获取中请稍后。。。');
		},
		success:function(result){
			result = eval('('+result+')');
			//alert(result.content);
			$(".g_1").html(result.content);
		}
	})
}

// 正常获取同类商品
function get_simila_goods(type)
{
	$.ajax({
		type: "POST",
		url: "goods_group.php?act=similar&m="+Math.random(),
		data: 'goods_id='+$('#goods_id').text()+'&structurecodetmp='+$("#structurecodetmp").val()+'&up='+$("#group_up_one").val()+'&next='+$("#group_next_one").val()+'&type='+type,
		cache: false,
		beforeSend:function(){
			//$(".g_1").html('获取中请稍后。。。');
		},
		success:function(result){
			result = eval('('+result+')');
			//alert(result.content);
			$(".g_1").html(result.content);
		}
	})	
}

// 获取随便看看 商品
function get_casual_goods(type)
{
	$.ajax({
		type: "POST",
		url: "goods_group.php?act=casual&m="+Math.random(),
		data: 'goods_cookie_id='+$('#group_cookie_tow').val()+'&casual_goods_num='+$('#casual_goods_num').val()+'&type='+type,
		cache: false,
		beforeSend:function(){
			//$(".g_2").html('获取中请稍后。。。');
		},
		success:function(result){
			result = eval('('+result+')');
			//alert(result.content);
			$(".g_2").html(result.content);
		}
	})
}

/* 勾选组合商品时的操作
@ 20100522A 王瑀峰 组合商品销售需求 fuxing 20100628 
*/
function checkGift(A,B)
{
  var giftidArr = document.forms['packaging_'+A].elements['packaging_goods_id'+A+'[]'];
  var giftpriceArr = document.forms['packaging_'+A].elements['packaging_goods_price'+A+'[]'];
  var giftshoppriceArr = document.forms['packaging_'+A].elements['packaging_current_price'+A+'[]'];
  var mainGoodsprice = document.forms['packaging_'+A].elements['main_goods_price'].value;
  var j = 0;
  var k = 0;
  for (i = 0; i < giftidArr.length; i++){
    if(giftidArr[i].checked){
        j += Number(giftpriceArr[i].value);
        k += Number(giftshoppriceArr[i].value);
    }
  }
  if(k == 0){
    alert('请至少保留一件组合购买商品');
    B.checked = true;
  }else{
    k += Number(mainGoodsprice);
    k = formatnumber(k,2);
    j += Number(mainGoodsprice);
    j = formatnumber(j,2);
    
    $('#equ'+A).find('b').eq(0).html('¥'+k);
    $('#equ'+A).find('span').eq(0).html('¥'+j);
  }
}

/* 获取指定小数位数的数字
@ 20100522A 王瑀峰 组合商品销售需求 fuxing 20100628 
*/
function formatnumber(value,num)
{
    var a,b,c,i
    a = value.toString();
    b = a.indexOf('.');
    c = a.length;
    if (num==0)
    {
    if (b!=-1)
    a = a.substring(0,b);
    }
    else
    {
    if (b==-1)
    {
    a = a + ".";
    for (i=1;i<=num;i++)
    a = a + "0";
    }
    else
    {
    a = a.substring(0,b+num+1);
    for (i=c;i<=b+num;i++)
    a = a + "0";
    }
    }
    return a
}
/* 检测 并 生成组合商品
@ 20100522A 王瑀峰 组合商品销售需求 fuxing 20100628 
*/
function checkPackaginggoods(A,B){
    var giftidArr = document.forms['packaging_'+A].elements['packaging_goods_id'+A+'[]'];
    var mainGoodsid = document.forms['packaging_'+A].elements['main_goods_id'].value;
    var selected_goods_id = new Array();
    var j = 0;
    for (i = 0; i < giftidArr.length; i++){
      if(giftidArr[i].checked){
        selected_goods_id[j]=giftidArr[i].value;
        j++;
      }
    }
    selected_goods_id[selected_goods_id.length] = mainGoodsid;
    var goods_id = selected_goods_id.join(',');
    var id = document.forms['packaging_'+A].elements['packaging_act_id'+A].value;
    var act_name = document.forms['packaging_'+A].elements['packaging_act_name'+A].value;
    $.ajax({
      type: "GET",
      url:  "choseinx.php?act=check_packaging_goods&" + Math.random(),
      data: 'id=' + id + '&goods_id=' + goods_id+ '&packaging_type=5',
      success:function(msg){

        var re = eval("(" + msg + ")");
          if (0 == re.err_msg)
          {
            inTooCartAction(re.result, act_name, 1, 0, 0,B);
          }
          else if (1 == re.err_msg)
          {
            alert(re.content);
          }
      }
    })
} 
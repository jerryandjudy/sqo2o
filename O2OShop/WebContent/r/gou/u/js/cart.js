(function(i){
	if(/1\.(0|1|2)\.(0|1|2)/.test(i.fn.jquery)||/^1.1/.test(i.fn.jquery)){
		alert("blockUI requires jQuery v1.2.3 or later! You are using v"+i.fn.jquery);return}
	i.fn._fadeIn=i.fn.fadeIn;
	var c=function(){};
	var j=document.documentMode||0;
	var e=i.browser.msie&&((i.browser.version<8&&!j)||j<8);
	var f=i.browser.msie&&/MSIE 6.0/.test(navigator.userAgent)&&!j;
	i.blockUI=function(p){d(window,p);};
	i.unblockUI=function(p){
		h(window,p);
    };
    i.growlUI=function(t,r,s,p){
    	var q=i('<div class="growlUI"></div>');
    	if(t){q.append("<h1>"+t+"</h1>");}
    	if(r){q.append("<h2>"+r+"</h2>");}
    	if(s==undefined){s=3000;}
    	i.blockUI({
    		message:q,
    		fadeIn:700,
    		fadeOut:1000,
    		centerY:false,
    		timeout:s,
    		showOverlay:false,
    		onUnblock:p,
    		css:i.blockUI.defaults.growlCSS});
    };
    i.fn.block=function(p){
    	return this.unblock({fadeOut:0}).each(function(){
    						if(i.css(this,"position")=="static"){
    							this.style.position="relative";
    						}
    						if(i.browser.msie){
    							this.style.zoom=1;
    						}
    						d(this,p);
    					})
    };
    i.fn.unblock=function(p){
    	return this.each(
    			function(){h(this,p);}
    		)
    };
    i.blockUI.version=2.33;
    i.blockUI.defaults={
    		message:"<h1>Please wait...</h1>",
    		title:null,
    		draggable:true,
    		theme:false,
    		css:{padding:0,margin:0,width:"30%",top:"40%",left:"35%",textAlign:"center",color:"#000",border:"3px solid #aaa",backgroundColor:"#fff",cursor:"wait"},
    		themedCSS:{width:"30%",top:"40%",left:"35%"},
    		overlayCSS:{backgroundColor:"#000",opacity:0.6,cursor:"wait"},
    		growlCSS:{width:"350px",top:"10px",left:"",right:"10px",border:"none",padding:"5px",opacity:0.6,cursor:"default",color:"#fff",backgroundColor:"#000","-webkit-border-radius":"10px","-moz-border-radius":"10px","border-radius":"10px"},
    		iframeSrc:/^https/i.test(window.location.href||"")?"javascript:false":"about:blank",
    		forceIframe:false,
    		baseZ:1000,
    		centerX:true,
    		centerY:true,
    		allowBodyStretch:true,
    		bindEvents:true,
    		constrainTabKey:true,
    		fadeIn:200,
    		fadeOut:400,
    		timeout:0,
    		showOverlay:true,
    		focusInput:true,
    		applyPlatformOpacityRules:true,
    		onBlock:null,
    		onUnblock:null,
    		quirksmodeOffsetHack:4
    };
var b=null;
var g=[];
function d(r,F){
	var A=(r==window);
	var w=F&&F.message!==undefined?F.message:undefined;
	F=i.extend({},i.blockUI.defaults,F||{});
	F.overlayCSS=i.extend({},i.blockUI.defaults.overlayCSS,F.overlayCSS||{});
	var C=i.extend({},i.blockUI.defaults.css,F.css||{});
	var N=i.extend({},i.blockUI.defaults.themedCSS,F.themedCSS||{});
	w=w===undefined?F.message:w;
	if(A&&b){h(window,{fadeOut:0})}
	if(w&&typeof w!="string"&&(w.parentNode||w.jquery)){
		var I=w.jquery?w[0]:w;
		var P={};
		i(r).data("blockUI.history",P);
		P.el=I;
		P.parent=I.parentNode;
		P.display=I.style.display;
		P.position=I.style.position;
		if(P.parent){P.parent.removeChild(I)}
	}
	var B=F.baseZ;
	var M=(i.browser.msie||F.forceIframe)?i('<iframe class="blockUI" style="z-index:'+(B++)+';display:none;border:none;margin:0;padding:0;position:absolute;width:100%;height:100%;top:0;left:0" src="'+F.iframeSrc+'"></iframe>'):i('<div class="blockUI" style="display:none"></div>');
    var L=i('<div class="blockUI blockOverlay" style="z-index:'+(B++)+';display:none;border:none;margin:0;padding:0;width:100%;height:100%;top:0;left:0"></div>');
    var K,G;
    if(F.theme&&A){
    	G='<div class="blockUI blockMsg blockPage ui-dialog ui-widget ui-corner-all" style="z-index:'+B+';display:none;position:fixed"><div class="ui-widget-header ui-dialog-titlebar blockTitle">'+(F.title||"&nbsp;")+'</div><div class="ui-widget-content ui-dialog-content"></div></div>'
    }else{
    	if(F.theme){
    		G='<div class="blockUI blockMsg blockElement ui-dialog ui-widget ui-corner-all" style="z-index:'+B+';display:none;position:absolute"><div class="ui-widget-header ui-dialog-titlebar blockTitle">'+(F.title||"&nbsp;")+'</div><div class="ui-widget-content ui-dialog-content"></div></div>'
    	}else{
    		if(A){
    			G='<div class="blockUI blockMsg blockPage" style="z-index:'+B+';display:none;position:fixed"></div>'
           }else{
            	G='<div class="blockUI blockMsg blockElement" style="z-index:'+B+';display:none;position:absolute"></div>'
           }
    	}
   }
    K=i(G);
    if(w){
    	if(F.theme){
    		K.css(N);K.addClass("ui-widget-content")
    	}else{K.css(C)}
    }
    if(!F.applyPlatformOpacityRules||!(i.browser.mozilla&&/Linux/.test(navigator.platform))){L.css(F.overlayCSS)}
    L.css("position",A?"fixed":"absolute");
    if(i.browser.msie||F.forceIframe){M.css("opacity",0)}
    var y=[M,L,K],O=A?i("body"):i(r);
    i.each(y,function(){this.appendTo(O)});
    if(F.theme&&F.draggable&&i.fn.draggable){K.draggable({handle:".ui-dialog-titlebar",cancel:"li"})}
    var v=e&&(!i.boxModel||i("object,embed",A?null:r).length>0);
    if(f||v){
    	if(A&&F.allowBodyStretch&&i.boxModel){i("html,body").css("height","100%")}
    	if((f||!i.boxModel)&&!A){
    		var E=m(r,"borderTopWidth"),J=m(r,"borderLeftWidth");
    		var x=E?"(0 - "+E+")":0;
            var D=J?"(0 - "+J+")":0
        }
    	i.each([M,L,K],
        function(t,S){
    		var z=S[0].style;z.position="absolute";
    		if(t<2){
    			A?z.setExpression("height","Math.max(document.body.scrollHeight, document.body.offsetHeight) - (jQuery.boxModel?0:"+F.quirksmodeOffsetHack+') + "px"'):z.setExpression("height",'this.parentNode.offsetHeight + "px"');
    			A?z.setExpression("width",'jQuery.boxModel && document.documentElement.clientWidth || document.body.clientWidth + "px"'):z.setExpression("width",'this.parentNode.offsetWidth + "px"');
    			if(D){z.setExpression("left",D)}
    			if(x){z.setExpression("top",x)}
    		}else{
    			if(F.centerY){
    				if(A){
    					z.setExpression("top",'(document.documentElement.clientHeight || document.body.clientHeight) / 2 - (this.offsetHeight / 2) + (blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "px"')
                    }
    				z.marginTop=0
              }else{
            	  if(!F.centerY&&A){
            		  var Q=(F.css&&F.css.top)?parseInt(F.css.top):0;
            		  var R="((document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "+Q+') + "px"';z.setExpression("top",R)
            	   }
             }
    	   }
     })
    }
    if(w){
    	if(F.theme){
    		K.find(".ui-widget-content").append(w)
    	}else{
    		K.append(w)
    	}
    	if(w.jquery||w.nodeType){i(w).show()}
    }
     if((i.browser.msie||F.forceIframe)&&F.showOverlay){M.show()}
     if(F.fadeIn){
    	 var H=F.onBlock?F.onBlock:c;
    	 var q=(F.showOverlay&&!w)?H:c;
    	 var p=w?H:c;if(F.showOverlay){L._fadeIn(F.fadeIn,q)}
    	 if(w){K._fadeIn(F.fadeIn,p)}
    }else{
    	if(F.showOverlay){L.show()}
    	if(w){K.show()}
    	if(F.onBlock){F.onBlock()}
    }
     l(1,r,F);
     if(A){
    	 b=K[0];g=i(":input:enabled:visible",b);
    	 if(F.focusInput){
    		 setTimeout(o,20)
    	 }
    }else{
    	a(K[0],F.centerX,F.centerY)
    }
    if(F.timeout){
    	  var u=setTimeout(function(){
    		  A?i.unblockUI(F):i(r).unblock(F)
               },
               F.timeout);i(r).data("blockUI.timeout",u)
   }
}
function h(s,t){
	var r=(s==window);var q=i(s);
	var u=q.data("blockUI.history");
	var v=q.data("blockUI.timeout");
	if(v){
		clearTimeout(v);
		q.removeData("blockUI.timeout")
	}
	t=i.extend({},i.blockUI.defaults,t||{});
	l(0,s,t);
	var p;
	if(r){
		p=i("body").children().filter(".blockUI").add("body > .blockUI")
	}else{
		p=i(".blockUI",s)
	}
	if(r){b=g=null}
	if(t.fadeOut){
		p.fadeOut(t.fadeOut);setTimeout(function(){k(p,u,t,s)},t.fadeOut)
	}else{
		k(p,u,t,s)
	}
}
function k(p,s,r,q){
	p.each(function(t,u){if(this.parentNode){this.parentNode.removeChild(this)}});
	if(s&&s.el){
		s.el.style.display=s.display;
		s.el.style.position=s.position;
		if(s.parent){s.parent.appendChild(s.el)}
		i(q).removeData("blockUI.history")
	}
	if(typeof r.onUnblock=="function"){r.onUnblock(q,r)}
}
function l(p,t,u){
	var s=t==window,r=i(t);
    if(!p&&(s&&!b||!s&&!r.data("blockUI.isBlocked"))){return}
    if(!s){r.data("blockUI.isBlocked",p)}
    if(!u.bindEvents||(p&&!u.showOverlay)){return}
    var q="mousedown mouseup keydown keypress";
    p?i(document).bind(q,u,n):i(document).unbind(q,n)
}
function n(s){
	if(s.keyCode&&s.keyCode==9){
		if(b&&s.data.constrainTabKey){
			var r=g;
			var q=!s.shiftKey&&s.target==r[r.length-1];
			var p=s.shiftKey&&s.target==r[0];
			if(q||p){setTimeout(function(){o(p)},10);return false}
		}
	}
	if(i(s.target).parents("div.blockMsg").length>0){return true}
	return i(s.target).parents().children().filter("div.blockUI").length==0
}
function o(p){
	if(!g){return}
	var q=g[p===true?g.length-1:0];
	if(q){q.focus()}
}
function a(w,q,A){
	var z=w.parentNode,v=w.style;
	var r=((z.offsetWidth-w.offsetWidth)/2)-m(z,"borderLeftWidth");
	var u=((z.offsetHeight-w.offsetHeight)/2)-m(z,"borderTopWidth");
    if(q){v.left=r>0?(r+"px"):"0"}
    if(A){v.top=u>0?(u+"px"):"0"}
}
function m(q,r){
	return parseInt(i.css(q,r))||0
}
})(jQuery);

/*自动加载方法*/
jQuery(function(){
	aa();
});

/*自动加载总重量，总价格，节省费用*/
function aa(){
	var score=0;
	var weight=0.0;
	var market=0.0;
	var sale=0.0;
	var popularity=0.0;
	$("#[id^='total_score_items_']").each(function(){
		var ss=parseInt(jQuery(this).html());
		var h=this.id;
		var e=h.split("_")[3]; 
		var f=h.split("_")[4]; 
	    if($("#chkMat_"+e+"_"+f).attr("checked")){
	    	score+=ss;
	    }else{
	    	score=score;
	    }
		$("#items_score").html(score);
	});
	$("#[id^='total_weight_items_']").each(function(){
		var ww=parseFloat(jQuery(this).html());
		weight =accAdd(weight,ww);
		$("#items_weight").html(weight);
	});
	
	$("#[id^='total_market_items_']").each(function(){
		var mk=parseFloat(jQuery(this).html());
		var h=this.id;
		var e=h.split("_")[3]; 
		var f=h.split("_")[4]; 
	    if($("#chkMat_"+e+"_"+f).attr("checked")){
	    	market=accAdd(market,mk);
	    }else{
	    	market=market;
	    }
	});
	$("#[id^='total_sale_items_']").each(function(){
		var sl=parseFloat(jQuery(this).html());
		var h=this.id;
		var e=h.split("_")[3]; 
		var f=h.split("_")[4]; 
	    if($("#chkMat_"+e+"_"+f).attr("checked")){
	    	sale=accAdd(sale,sl);
	    }else{
	    	sale=sale;
	    }
	});
	$("#[id^='total_popularity_']").each(function(){
		var ss=parseFloat(jQuery(this).html());
		popularity =accAdd(weight,ss);
	});
	sale=accSub(sale,popularity)
	$("#items_sale").html(sale);
	$("#total_price_bottom").html(sale);
	$("#items_spare").html(accSub(market,sale));
}


function accAdd(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;} 
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;} 
	m=Math.pow(10,Math.max(r1,r2)); 
	return (arg1*m+arg2*m)/m; 
	 
	} 

	 
	Number.prototype.add = function (arg){ 
	return accAdd(arg,this);  
	}; 
	  



function accSub(arg1,arg2){
	　　 var r1,r2,m,n;
	　　try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	　　try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	　　 m=Math.pow(10,Math.max(r1,r2));
	　　 //last modify by deeka
	　　 //动态控制精度长度
	　　 n=(r1>=r2)?r1:r2;
	　　 return ((arg1*m-arg2*m)/m).toFixed(n);
	}


//乘法函数，用来得到精确的乘法结果 
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。 
//调用：accMul(arg1,arg2) 
//返回值：arg1乘以arg2的精确结果 
function accMul(arg1,arg2) 
{ 
var m=0,s1=arg1.toString(),s2=arg2.toString(); 
try{m+=s1.split(".")[1].length}catch(e){} 
try{m+=s2.split(".")[1].length}catch(e){} 
return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m) 
} 
//给Number类型增加一个mul方法，调用起来更加方便。 
Number.prototype.mul = function (arg){ 
return accMul(arg, this); 
} 


/*数量 减法*/
function decrement(base,itemId,j){
	var b=jQuery("#"+j).val();
	var a=/^[1-9]\d{0,2}$/g;
	if(!b.match(a)){
		alert("输入的数量有误,应为[1-99]");
	    j.value="1";b=1;
   }
	var c=parseInt(b)-1;
	if(c<1){c=1;}
	var q=jQuery("#"+j).attr("oriNum");
	var d=parseInt(q);
	if(c>d){alert("该商品库存有限，您最多只能购买"+d+"件");c=d;}
	jQuery("#"+j).val(c);
	var h=jQuery("#"+j).attr("id");
	var e=h.split("_")[1];
	var index=h.split("_")[2];
	var mk=$("#market_items_"+e+"_"+index).html();
	var sl=$("#sale_items_"+e+"_"+index).html();
	var dweight=$("#weight_items_"+e+"_"+index).html();
	var score=$("#score_items_"+e+"_"+index).html();
	$("#total_market_items_"+e+"_"+index).html('');
	$("#total_market_items_"+e+"_"+index).html(accMul(mk,c));
	$("#total_sale_items_"+e+"_"+index).html('');
	$("#total_sale_items_"+e+"_"+index).html(accMul(sl,c));
	$("#total_weight_items_"+e+"_"+index).html('');
	$("#total_weight_items_"+e+"_"+index).html(accMul(dweight,c));
	$("#total_score_items_"+e+"_"+index).html('');
	$("#total_score_items_"+e+"_"+index).html(accMul(score,c));
	aa();
	$.post(base+"/cart/ajaxUpdateCartItem.jspx", {
		'cartItemId':itemId,
		'count':c
	}, function(data) {
		if(data.status==1){
			/*location.reload();*/
		}else{
			alert("更新不成功");
		}
	},'json');
}




/*数量 加法*/
function increment(base,itemId,j){
	var b=jQuery("#"+j).val();
	var a=/^[1-9]\d{0,2}$/g;
	if(!b.match(a)){
		alert("输入的数量有误,应为[1-999]");
		f.value="1";b=1;
	}
	var c=parseInt(b)+1;
	if(c>999){c=999;}
	var q=jQuery("#"+j).attr("oriNum");
	var d=parseInt(q);
	if(c>d){alert("该商品库存有限，您最多只能购买"+d+"件");c=d;}
	jQuery("#"+j).val(c);
	var h=jQuery("#"+j).attr("id");
	var e=h.split("_")[1];
	var index=h.split("_")[2];
	var mk=$("#market_items_"+e+"_"+index).html();
	var sl=$("#sale_items_"+e+"_"+index).html();
	var dweight=$("#weight_items_"+e+"_"+index).html();
	var score=$("#score_items_"+e+"_"+index).html();
	$("#total_market_items_"+e+"_"+index).html('');
	$("#total_market_items_"+e+"_"+index).html(accMul(mk,c));
	$("#total_sale_items_"+e+"_"+index).html('');
	$("#total_sale_items_"+e+"_"+index).html(accMul(sl,c));
	$("#total_weight_items_"+e+"_"+index).html('');
	$("#total_weight_items_"+e+"_"+index).html(accMul(dweight,c));
	$("#total_score_items_"+e+"_"+index).html('');
	$("#total_score_items_"+e+"_"+index).html(accMul(score,c));
	aa();
	$.post(base+"/cart/ajaxUpdateCartItem.jspx", {
		'cartItemId' : itemId,
		'count':c
	}, function(data) {
		if(data.status==1){
			/*location.reload();*/
		}else{
			alert("更新不成功");
		}
	},'json');
}

/*input 数量*/
function calSubTotal(base,itemId,i,h,g,score,dj,dweight){
	var f=jQuery("#"+i).val();
	var c=/^[1-9]\d{0,2}$/g;
	if(!f.match(c)){
		alert("输入的数量有误,应为[1-99]");
        i.value="1"; f=1;
    }
	var a=parseInt(f);
	if(a<1){a=1;}
	if(a>99){a=99;}
	var q=jQuery("#"+i).attr("oriNum");
	var d=parseInt(q);
	if(a>d){alert("该商品库存有限，您最多只能购买"+d+"件");a=d;}
	jQuery("#"+i).val(a);
	var b=jQuery("#"+i).attr("id");
	var e=b.split("_")[1];
	var index=b.split("_")[2];
	var mk=$("#market_items_"+e+"_"+index).html();
	var sl=$("#sale_items_"+e+"_"+index).html();
	var dweight=$("#weight_items_"+e+"_"+index).html();
	var score=$("#score_items_"+e+"_"+index).html();
	$("#total_market_items_"+e+"_"+index).html('');
	$("#total_market_items_"+e+"_"+index).html(accMul(sl,a));
	$("#total_sale_items_"+e+"_"+index).html('');
	$("#total_sale_items_"+e+"_"+index).html(accMul(sl,a));
	$("#total_weight_items_"+e+"_"+index).html('');
	$("#total_weight_items_"+e+"_"+index).html(accMul(dweight,a));
	$("#total_score_items_"+e+"_"+index).html('');
	$("#total_score_items_"+e+"_"+index).html(accMul(score,a));
	aa();
	$.post(base+"/cart/ajaxUpdateCartItem.jspx", {
		'cartItemId' : itemId,
		'count':a
	}, function(data) {
		if(data.status==1){
			/*location.reload();*/
		}else{
			alert("更新不成功");
		}
	},'json');
}

/*继续购物*/
function continueShopping(){window.open(URLPrefix.url+"/");}

/*批量删除*/
function ajaxBatchDelete(){
	$('input[type="checkbox"][name="cart2Checkbox"]:checked').each(function(){
		 $(this).parent().parent().find(".deleteButton").trigger("click");
	});
}

/*清空购物车*/
function ajaxEmpty(){
	$('input[type="checkbox"][name="cart2Checkbox"]').each(function(){
		 $(this).parent().parent().find(".deleteButton").trigger("click");
	});
}

/*删除购物车项*/
function ajaxDeleteCartItem(base,cartItemId){
		$.post(base+"/cart/ajaxDeleteCartItem.jspx", {
			'cartItemId' : cartItemId
		}, function(data) {
			if(data.status==1){
				location.reload();
			}else{
				alert("删除不成功");
			}
		},'json');
}

function checksubmit(){
	
	var sale=0.0;
	$("#[id^='total_sale_items_']").each(function(){
		var sl=parseFloat(jQuery(this).html());
		var h=this.id;
		var e=h.split("_")[3]; 
		var f=h.split("_")[4]; 
	    if($("#chkMat_"+e+"_"+f).attr("checked")){
	    	sale+=sl;
	    }else{
	    	sale=sale;
	    }
		
	});
	if(sale==0){
		alert("请勾选你需要的商品");
		return ;
	}
	ajaxQueue.confirm();
	$("#[id^='item_tr_']").each(function(){
		var sl=parseFloat(jQuery(this).html());
		var h=this.id;
		var e=h.split("_")[2]; 
		var index=h.split("_")[3]; 
		var count=$("#items_"+e+"_"+index).val();
		var fashId=$("#items_fash_"+e+"_"+index).val();
		if($("#chkMat_"+e+"_"+index).attr("checked")){
			$.post(URLPrefix.url+"/cart/checkStockCount.jspx", {
				'productId' : e,
				'productFashionId' :fashId,
				'count':count
			}, function(data) {
				if(data.status==0){
					location=location;
					return ;
				}
				if(data.status==2){
					alert(data.error);
					return ;
				}
			},'json');
		}
	});
	$("#jvForm").submit();
}

var trackerUrl=("https:"==document.location.protocol?"https://":"http://")+"tracker.yihaodian.com/tracker/info.do?1=1";
function gotracker(j,i,f){
	var h=trackerUrl;
	alert(h);
	var g=new RegExp("&linkPosition=\\w*","g");
	h=h.replace(g,"");
	var g=new RegExp("&buttonPosition=\\w*","g");
	h=h.replace(g,"");
	var g=new RegExp("&productId=\\w*","g");
	h=h.replace(g,"");
	if(j!=null){
		if(j==2){
		  h=h+"&buttonPosition="+i
	   }else{
		  h=h+"&linkPosition="+i
	   }
	}
	if(f!=null){h=h+"&productId="+f}
	jQuery.ajax({async:true,url:h,type:"GET",dataType:"jsonp",jsonp:"jsoncallback"})
}
var ajaxQueue=new Array();
ajaxQueue.workingFlag=false;
ajaxQueue.confirmFlag=false;
ajaxQueue.go=function(){
	if(this.workingFlag){return}
	this.workingFlag=true;
	if(this.length<=0){
		this.workingFlag=false;
		if(this.confirmFlag&&!optMan.anyTimeout()){
			this.confirmFlag=false;
			cart2.unblockUI("0");
			if(ckbMan.anyChecked()){confirmToPay()}
		}return
    }
	var a=ajaxQueue.shift();
	if(a.type==null||a.type==""){
		a.stage=3;optMan.map.remove(a.timer);
		this.workingFlag=false;
		this.go();
		return
	}
	if(!ajaxQueue.confirmFlag&&a.type=="other"){cart2.blockUI()}
	jQuery.post(a.getUrl(),a.getParam(),
			function(b){
		       a.stage=3;
		       optMan.map.remove(a.timer);
		       ajaxQueue.workingFlag=false;
		       if(!redirectIf(b)){
		    	   if(!ajaxQueue.confirmFlag&&a.type=="other"){cart2.unblockUI(a.showMsg)}
		    	   if(a.type!="check"){refreshCart2PageContent(b)}
		    	   ajaxQueue.go()
		    	}else{
		    		cart2.unblockUI("0");
		    		if(ajaxQueue.confirmFlag){ajaxQueue.confirmFlag=false}
		    	}
		      })
};
ajaxQueue.add=function(a){a.stage=2;ajaxQueue.push(a);ajaxQueue.go()};

ajaxQueue.confirm=function(){
	cart2.blockUI();
	ajaxQueue.confirmFlag=true;
	var a="/cart/checkCartBeforeConfirm.jspx?rd="+Math.random();
	optMan.mergeReq("check",a,"0")
};

var cart2={
		blockUITimer:null,
		isBlocked:false,
		ajaxSelect:function(){optMan.mergeReq("select",null,"0",1000);},
	    ajaxDelete:function(b,a,c,d){optMan.mergeReq("delete",null,null,c,b,a,d);},
	    ajaxNum:function(a,d,c,b,e){optMan.mergeReq("num",a,d,c,b,e);},
	    ajaxLandingNum:function(b,e,d,c,f,a){optMan.mergeReq("landingNum",b,e,d,c,f,a);},
	    ajaxRefresh:function(a,c,b){optMan.mergeReq("other",a,c,b);},
	    showBlockUI:function(a){
	    	jQuery("#cart2BlockUI").block({
	    	   message:a,
	    	   css:{padding:0,margin:0,width:"35%",border:"1px solid #FFCC00",opacity:0.9,backgroundColor:"#FEFFDF"},
	    	   overlayCSS:{background:"transparent",opacity:0.1,cursor:"default"}
	    	  });
	   },
	   blockUI:function(){
		   cart2.isBlocked=true;
		   var a=(jQuery(window).width())/2-400+jQuery(window).scrollLeft();
		   if(jQuery.browser.msie&&jQuery.browser.version=="6.0"){
			   var b=(jQuery(window).height())/2+jQuery(window).scrollTop();
                a=(jQuery(window).width())/2+jQuery(window).scrollLeft()-300;
                jQuery("#cart2BlockUI").show().css({
                	position:"absolute",width:"60%",height:"20%",zIndex:"9999",top:b,left:a})
            }else{
            	jQuery("#cart2BlockUI").show().css({
            		position:"fixed",
            		width:"60%",
            		height:"20%",
            		zIndex:"9999",
            		top:"200px",
            		left:a});
            }
		   cart2.showBlockUI('<table width="100%" border="0" cellspacing="0" cellpadding="0" id="cart2BlockUIMsg"><tr><td width="100%" height="50" align="center" class="font14"><img src="http://image.yihaodian.com/images/wait_loading.gif"/></td></tr><tr><td height="25" align="center" class="font14">正在处理中,请稍候...</td></tr></table>')
		   },
	  blockUI2:function(d,b){
			   cart2.isBlocked=true;
			   var a=jQuery("#favorites_"+b).offset().left;
			   var e=jQuery("#favorites_"+b).offset().top;
			   if(jQuery.browser.msie&&jQuery.browser.version=="6.0"){
				   jQuery("#cart2BlockUI").show().css({
					   position:"absolute",width:"40%",height:"20%",zIndex:"9999",top:e-32,left:a-215})
                }else{
                	jQuery("#cart2BlockUI").show().css({
                		position:"absolute",width:"30%",height:"50px",zIndex:"9999",top:e-40,left:a-230})
                }
			   var c="";
			   if(d=="已添加入收藏夹"){c="<i></i>";}
			   cart2.showBlockUI('<table width="100%" border="0" cellspacing="0" cellpadding="0" id="cart2BlockUIMsg"><tr><td height="25" class="font14 fontIco">'+c+"&nbsp;"+d+"</td></tr></table>");
	 },
	 unblockUI:function(a){
			 cart2.isBlocked=false;
			 if(a&&a=="0"){
					   jQuery("#cart2BlockUI").unblock().hide();
			 }else{
				cart2.showBlockUI('<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="65" align="center" class="font14">您的操作已成功!</td></tr></table>');
				if(cart2.blockUITimer){
						clearTimeout(cart2.blockUITimer);
				}
				cart2.blockUITimer=setTimeout(function(){jQuery("#cart2BlockUI").unblock().hide();},1000);
			}
	}
 };

var optMan=new Object();
optMan.map=new Map();
optMan.mergeSelect=function(){
	var c=true;
     for(var a in this.map.entry){
    	 var b=this.map.get(a);
    	 if(b!=null&&b.stage==1){b.clearTimeout();b.setTimeout();c=false;break;}
    }
     return c;
 };
 optMan.mergeDelete=function(b,e,a){
	 var f=true;
	 for(var c in this.map.entry){
		 var d=this.map.get(c);
		 if(d!=null&&d.stage==1){
			 if(d.type=="select"){d.clearTimeout();optMan.map.remove(c)}
			 if(d.type=="delete"){d.addDeletePost(b,e,a);d.clearTimeout();d.setTimeout();f=false}
		 }
    }
	 return f;
};
optMan.mergeNum=function(a,c,f){
	var e=true;
	for(var b in this.map.entry){
		var d=this.map.get(b);
		if(d!=null&&d.stage==1){
			if(d.type=="select"){
				d.clearTimeout();
				optMan.map.remove(b);
			}
			if(d.type=="num"&&d.productId==c&&d.merchantId==f){d.url=a;d.clearTimeout();d.setTimeout();e=false}
		}
	}
  return e;
 };
 optMan.mergeLandingNum=function(b,d,g,a){
	 var f=true;
	 for(var c in this.map.entry){
		 var e=this.map.get(c);
		 if(e!=null&&e.stage==1){
			 if(e.type=="select"){
				 e.clearTimeout();
                 optMan.map.remove(c);
              }
			 if(e.type=="landingNum"&&e.productId==d&&e.merchantId==g&&e.promotionId==a){
				 e.url=b;
				 e.clearTimeout();
				 e.setTimeout();
				 f=false;
			}
		}
	}
	 return f;
};
optMan.mergeOther=function(){
	for(var a in this.map.entry){
		var b=this.map.get(a);
		if(b!=null&&b.stage==1&&b.type=="select"){
			b.clearTimeout();
			optMan.map.remove(a);
		}
	}
	return true;
};
optMan.mergeReq=function(b,a,f,e,h,d,c){
	if(ajaxQueue.confirmFlag&&b!="check"){return;}
	if(this.map.size()<=0){
		this.buildReq(b,a,f,e,h,d,c);return;
	}
	var g=true;
	if(b=="select"){
		g=this.mergeSelect();
	}
	if(b=="delete"){
		g=this.mergeDelete(h,d,c);
	}
	if(b=="num"){
		g=this.mergeNum(a,h,d);
	}
	if(b=="landingNum"){
		g=this.mergeLandingNum(a,h,d,c);
	}
	if(b=="other"){
		g=this.mergeOther();
	}
	if(g){
		this.buildReq(b,a,f,e,h,d,c);
	}
};
optMan.buildReq=function(b,a,g,f,h,e,d){
	var c=new Object();
	c.type=b;
	c.url=a;
    c.showMsg=g;
    c.stage=0;
    c.timer=null;
    if(b=="num"){
    	c.productId=h;c.merchantId=e;
    }else{
    	if(b=="landingNum"){
    		c.productId=h;
    		c.merchantId=e;
    		c.promotionId=d;
    	}
    }
    if(b=="delete"){
    	c.deleteRecord=new Map();
    	if(d!=null){
    		c.deleteRecord.put(d,1);
    	}
    	c.deleteQueue=new Array();
    	c.deleteOverPromotionQueue=new Array();
    	c.deleteWarningQueue=new Array();
    	c.deleteGiftQueue=new Array();
    	c.deleteErrorPromotionQueue=new Array();
    	c.addDeletePost=function(k,j,i){
    		switch(j){
    		      case 0:this.deleteQueue.push(k);break;
    		      case 1:this.deleteOverPromotionQueue.push(k);break;
    		      case 2:this.deleteWarningQueue.push(k);break;
    		      case 3:this.deleteGiftQueue.push(k);break;
    		      case 4:this.deleteErrorPromotionQueue.push(k);break
    		 }
    		this.deleteRecord.put(i,1);
    		return true;
    	};
    	c.addDeletePost(h,e)
    }
    c.getParam=function(){
    	var i=$('input[type="checkbox"][name="cart2Checkbox"]').map(
    			function(){return $(this).val()+"="+($(this).attr("checked")?"1":"0")}).get().join(",");
    	if(this.type=="delete"){
    		return{
    			deleteQueue:this.deleteQueue.join(","),
    			deleteOverPromotionQueue:this.deleteOverPromotionQueue.join(","),
    			deleteWarningQueue:this.deleteWarningQueue.join(","),
    			deleteGiftQueue:this.deleteGiftQueue.join(","),
    			deleteErrorPromotionQueue:this.deleteErrorPromotionQueue.join(","),
    			cart2Checkbox:i,
    			source:1
    		}
    	}
    	return{cart2Checkbox:i,source:1}
    };
    c.getUrl=function(){
    	if(this.type=="delete"){
    		return"/cart/ajax.do?action=delete&rd="+Math.random()
    	}
    	if(this.type=="select"){return"/cart/ajax.do?action=select&rd="+Math.random()}
    	return this.url;
    };
    c.post=function(){ajaxQueue.add(this);};
    c.setTimeout=function(){
    		this.timer=setTimeout(function(){c.post();},(f==null||f<0)?0:f);
    		this.stage=1;
    };
    c.clearTimeout=function(){if(this.timer){clearTimeout(this.timer)}this.stage=0};
    if(c.type=="check"){
    		ajaxQueue.add(c);
    }else{
        	c.setTimeout();
    }
    this.map.put(c.timer,c);
};

optMan.reloadDelete=function(){
    $("#cart2_content .gray-box.pro-li .clear.list tr").each(function(){
    	for(var a in optMan.map.entry){
    	  var b=optMan.map.get(a);
    		if(b!=null&&b.type=="delete"&&b.stage<3&&b.deleteRecord.containsKey($(this).attr("id"))){
    					$(this).find(".deleteButton").trigger("click");
    		}
    	}
    });
};
optMan.anyTimeout=function(){
    for(var a in this.map.entry){
    		var b=this.map.get(a);
    		if(b!=null&&b.stage==1){return true;}
    }
    return false;
};

function Map(){
	var a=0;
	this.entry=new Array();
	this.put=function(b,c){
		if(!this.containsKey(b)){a++}
		this.entry[b]=c};
		this.get=function(b){if(this.containsKey(b)){return this.entry[b]}else{return null}};
		this.remove=function(b){if(delete this.entry[b]){a--}};
		this.containsKey=function(b){return(b in this.entry)};
		this.containsValue=function(b){for(var c in this.entry){if(this.entry[c]==b){return true}}return false};
		this.values=function(){var b=new Array(a);for(var c in this.entry){b.push(this.entry[c])}return b};
		this.keys=function(){var b=new Array(a);for(var c in this.entry){b.push(c)}return b};
		this.size=function(){return a};
		this.clear=function(){this.entry=new Array();this.size=0}
}

var ckbMan={
		map:new Map(),
		reset:function(){
		$('input[type="checkbox"][name="cart2Checkbox"]').each(
		     function(){
		        		var a=$(this).val();
                         var b=ckbMan.map.get(a);
                         if(b!=null){
                             $(this).attr("checked",b)
                         }else{
                               ckbMan.map.put($(this).val(),$(this).attr("checked"))
                          }
               })
           },
          click:function(a){this.map.put($(a).val(),$(a).attr("checked"))},
          remove:function(a){this.map.remove($(a).val())},
          removeByKey:function(a){this.map.remove(a)},
          loadStatusFromJs:function(){
               $('input[type="checkbox"][name="cart2Checkbox"]').each(
                   function(){
                    		   var a=$(this).val();
                    		   var b=ckbMan.map.get(a)==null?true:ckbMan.map.get(a);
                    			 $(this).attr("checked",b)
                    		 }
                );
               refreshCheckbox()
           },
           anyChecked:function(){
                    	    	var a=false;
                    	    	$('input[type="checkbox"][name="cart2Checkbox"]').each(
                    	    			function(){
                    	    				if($(this).attr("checked")){
                    	    					a=true;return false
                    	    				}
                    	    			}
                    	    	);
                    	    	if($('input[type="hidden"][name="XYItem"]').size()>0){a=true}
                    	    	return a
          },
           alertMap:function(){
                    		   var b="";
                    		   for(var a in this.map.entry){
                    			   b+=a+"=>"+this.map.get(a)+"\n"
                    			}
                    		   alert(b)
         }
   };
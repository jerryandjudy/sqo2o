/*自动加载方法*/
jQuery(function(){
	jQuery("#productImgA").jqzoom({zoomWidth:400,zoomHeight:400,lens:false,title:false});
	pictureSlider();

});
/*颜色和尺寸切换*/
jQuery(document).ready(function(){
		if(typeof(allSize)!="undefined"&&typeof(allColor)!="undefined"){
				jQuery("#all_size").attr("value",allSize);
				jQuery("#all_color").attr("value",allColor)
		}
		initSizeColor();
		jQuery("#size_list li").hover(
				function(){
					  if(!isEnabled(jQuery(this))){return false;}
							jQuery(this).addClass("current");
					},
				function(){
						jQuery(this).removeClass("current");}
		);
		jQuery("#color_list li").hover(
				   function(){
					     if(!isEnabled(jQuery(this))){return false}
					     jQuery(this).addClass("current")
				   },
				   function(){jQuery(this).removeClass("current")}
		);
		jQuery("#size_list li").click(
				function(){
					if(!isEnabled(jQuery(this))){return false}
					var c=jQuery("#color_list .selected").find("img").attr("title");
					if(!c){
						c=jQuery("#color_list .selected").find("span").attr("title");
					}
					if(jQuery(this).hasClass("selected")){
						 if(jQuery("#color_list").find(".selected").size()<=0){
							 seriesColorSizeInit("size");
		                     seriesColorSizeInit("color")
		                 }
					     jQuery("#current_productID").val("");
					     jQuery(this).removeClass("selected");
					     jQuery("#seriesSelectSize").empty();
					     jQuery("#addFavorite").hide();
					     if(c){
					    	 updateCurrentSizeByColor(c)
					      }
					     return false
				    }else{
					      if(jQuery("#color_list .selected").size()>0){jQuery("#addFavorite").show()}
				    }
					seriesColorSizeInit("color");
					var d=jQuery(this).find("span").attr("title");
					if(jQuery("[ID='size_map_"+d+"']").val()){
						jQuery(this).addClass("selected").removeClass("current").siblings().removeClass("selected");
						jQuery("#seriesSelectSize").html(d);
						showSeriesSelect();
						if(d){updateCurrentColorBySize(d)}
						if(c){updateCurrentSizeByColor(c)}
						if(jQuery("#color_list .selected").size()==1){
							var a=jQuery("[ID='product_"+jQuery(this).find("span").attr("title")+"_"+c+"']").val();
							var b=jQuery("[ID='product_"+jQuery(this).find("span").attr("title")+"_"+c+"']").attr("merchantId");
		                    jQuery("#current_productID").attr("value",a);
		                    jQuery("#current_merchantId").attr("value",b);
		                    jQuery("#productId").val(a);
		                    jQuery("#merchantId").val(b);
		                    refreshSeriesPriceStock(jQuery("#current_productID").val())
		                 }
					}else{
						jQuery("#color_list li").addClass("no_model");
						jQuery(this).addClass("selected").removeClass("current").siblings().removeClass("selected");
						jQuery("#seriesSelectSize").html(d)
					}
	     });
		
		jQuery("#color_list li").click(function(){
			if(!isEnabled(jQuery(this))){return false}
			var d=jQuery("#size_list .selected").find("span").attr("title");
			if(jQuery(this).hasClass("selected")){
				if(jQuery("#size_list").find(".selected").size()<=0){
					seriesColorSizeInit("size");
					seriesColorSizeInit("color")
				}
				jQuery("#current_productID").val("");
				jQuery(this).removeClass("selected");
		        jQuery("#seriesSelectColor").empty();
		        jQuery("#addFavorite").hide();
		        if(d){updateCurrentColorBySize(d)}
		        return false
		       }else{
		    	   if(jQuery("#size_list .selected").size()>0){
		    		   jQuery("#addFavorite").show()
		    		}
		      }
			seriesColorSizeInit("size");
			var c=jQuery(this).find("img").attr("title");
			if(!c){
				c=jQuery(this).find("span").attr("title")
			}
			if(jQuery("[ID='color_map_"+c+"']").val()){
				jQuery(this).addClass("selected").removeClass("current").siblings().removeClass("selected");
				jQuery("#seriesSelectColor").html(c);
				showSeriesSelect();
				if(c){updateCurrentSizeByColor(c)}
				if(d){updateCurrentColorBySize(d)}
				if(jQuery("#size_list .selected").size()==1){
					var a=jQuery("[ID='product_"+jQuery("#size_list .selected").find("span").attr("title")+"_"+c+"']").val();
		            var b=jQuery("[ID='product_"+jQuery("#size_list .selected").find("span").attr("title")+"_"+c+"']").attr("merchantId");
		            jQuery("#current_productID").attr("value",a);
		            jQuery("#current_merchantId").attr("value",b);
		            jQuery("#productId").val(a);
		            jQuery("#merchantId").val(b);
		            refreshSeriesPriceStock(jQuery("#current_productID").val())
		         }
			}else{
				jQuery("#size_list li").addClass("no_model");
				jQuery(this).addClass("selected").removeClass("current").siblings().removeClass("selected");
				jQuery("#seriesSelectColor").html(c)
			}
		});	
			
});


function isEnabled(c){
	var a=new RegExp("no_model");
	var b=c.attr("class");
	if(a.test(b)){return false}
	return true
}

function seriesColorSizeInit(a){
	if(a=="color"){
		jQuery("#color_list li").removeClass("no_model")
	}else{
		jQuery("#size_list li").removeClass("no_model")
	}
}

function showSeriesSelect(){
	if(jQuery("#seriesSelectColor")==""&&jQuery("#seriesSelectSize").text()==""){
		jQuery("#seriesShow").hide()
    }else{
    	jQuery("#seriesShow").show()
    }
}

function updateCurrentColorBySize(d){
	if(jQuery("[ID=size_map_"+d+"]").val()){
		var e=jQuery("[ID=size_map_"+d+"]").val().split(";");
		if(jQuery("#all_color").val()){
			var a=jQuery("#all_color").val().split(";");
            for(var b=0;b<a.length;b++){
        	  if(jQuery.inArray(a[b],e)=="-1"){
        		  seriesCheckboxDisabled(jQuery("[ID='color_"+a[b]+"']").parent())
        	  }
            }
        }
	}else{
		var c=jQuery("#color_list .selected").find("img").attr("title");
		if(!c){c=jQuery("#color_list .selected").find("span").attr("title")}
		if(c){var e=c.split(";")}else{var e="".split(";")}
		if(jQuery("#all_color").val()){
			var a=jQuery("#all_color").val().split(";");
			for(var b=0;b<a.length;b++){
				if(jQuery.inArray(a[b],e)=="-1"){
					seriesCheckboxDisabled(jQuery("[ID='color_"+a[b]+"']").parent())
				}
			}
		}
	}
}

function updateCurrentSizeByColor(a){
	if(jQuery("[ID=color_map_"+a+"]").val()){
		var c=jQuery("[ID=color_map_"+a+"]").val().split(";");
		if(jQuery("#all_size").val()){
			var d=jQuery("#all_size").val().split(";");
			for(var b=0;b<d.length;b++){
				if(jQuery.inArray(d[b],c)=="-1"){
					seriesCheckboxDisabled(jQuery("[ID='size_"+d[b]+"']").parent())
                 }
		   }
	   }
	}else{
		var e=jQuery("#size_list .selected").find("span").attr("title");
		if(e){
			var c=e.split(";")
		}else{
			var c="".split(";")
		}
		    var c="".split(";");
		    if(jQuery("#all_size").val()){
			   var d=jQuery("#all_size").val().split(";");
			   for(var b=0;b<d.length;b++){
				   if(jQuery.inArray(d[b],c)=="-1"){
					  seriesCheckboxDisabled(jQuery("[ID='size_"+d[b]+"']").parent())
				  }
			   }
		  }
	}
}

function seriesCheckboxDisabled(b){
	var a=b.attr("class");
	if(a.indexOf("select")==-1&&a.indexOf("no_model")==-1){b.attr("class",a+" no_model")}
}

function initSizeColor(){
	var c=jQuery("#product_color").val();
	var d=jQuery("#product_size").val();
	if(c&&d){
		var b=jQuery("[ID='product_"+d+"_"+c+"']").val();
	   jQuery("#current_productID").val(b);
	   jQuery("#productId").val(b);
	   var a="";
       if(jQuery("[ID='color_map_"+c+"']").size()>0){a=jQuery("[ID='color_map_"+c+"']").val().split(";")}
       jQuery("[ID='size_"+d+"']").parent().addClass("selected");
       jQuery("[ID='color_"+c+"']").parent().addClass("selected");
       jQuery("#seriesSelectColor").html(c);
       jQuery("#seriesSelectSize").html(d);
       jQuery("#seriesShow").show();
       jQuery("#addFavorite").show();
       jQuery("#btnNotice2").show();
       refreshSeriesPriceStock(jQuery("#current_productID").val())
     }
	updateCurrentColorBySize(d);
	updateCurrentSizeByColor(c)
}

function refreshSeriesPriceStock(c){
	var b=jQuery("#seriesCartButton");
	var a=jQuery("#current_merchantId").val();
	if(!a){a=jQuery("#merchantId").val()}
	ajaxRefreshPurchaseBtnStatusByTrueStockAndPrice(b,c,a)
}

function ajaxRefreshPurchaseBtnStatusByTrueStockAndPrice(btn,productId,merchantId){
	var url=URLPrefix.url+"/ajaxRefreshPriceAndStock.jspx";
        jQuery.getJSON(url,{"productId":productId,"merchantId":merchantId},function(data){
            if(data.success){
        		  if(data.productPrice){
        		    	 var curPriceObj=jQuery("span.productPriceSpan").not(jQuery("span.throughline"));
        		    	 if(data.productPrice&&parseFloat(data.productPrice)>=0){
        		    		 curPriceObj.text(data.productPrice)
        		    	}
        		   }
        		    if(data.marketPrice){
        		    	jQuery("#old_price").text(data.marketPrice+"元")
        		    }
        		    var oldBtnText=btn.val();
        		    var newBtnText="";
        		   if(data.productStock&&parseInt(data.productStock)>0){
        			      if(data.fashionStock&&parseInt(data.fashionStock)>0){
      		    			  newBtnText="加入购物车";
    		    			  initOtherBuyButton("加入购物车")
        			      }else{
        			    	  newBtnText="该款式已售完";
      		    			  initOtherBuyButton("该款式已售完")
        			      }
        		    	btn.val(newBtnText);
        		    	 var url="";
        		    	 if(jQuery("#product_"+productId+"_buttonurl").size()>0){
        		    		 url=jQuery("#product_"+productId+"_buttonurl").val();
        		    		 btn.unbind("click");
        		    		 btn.bind("click",
        		    				function(event){
        		    			        if(url!=null&&url!=""&&checkSizeColor()){eval(url)}
        		    			      });
        		    		btn.attr("class","add_shopping_cart").removeAttr("disabled")
        		    	}
        		    	 updateSubscribeProdBtn(false,productId,merchantId);
        		    }else{
        		    	newBtnText="已售完";
        		    	initOtherBuyButton("已售完");
        		    	if(newBtnText!=oldBtnText){
        		    		btn.val(newBtnText);
        		    		btn.unbind("click");
        		    		btn.attr("onclick","");
        		    		btn.attr("class","add_shopping_cart_over").attr("disabled",true);
        		    	}
        		    	updateSubscribeProdBtn(true);
        		    }
        	}
          })
 }

function updateSubscribeProdBtn(c,b,d){
	return;
	if(c){
		updateSubscribeProdBtnStatus(false);
		return
	}
	if(isSeriesProduct==0){return}
	var a=subscribeProdUrl+"/getSubscribeSaveProductPrice.do?productId="+b+"&merchantId="+d+"&callback=?";
    jQuery.getJSON(a,function(e){
    	if(e&&e.code){
    		if(e.code==1){
    			if(e.data){
    				var g=e.data;
    				if(g.subscribePrice){
    					var f=g.subscribePrice;
    					updateSubscribeProdBtnStatus(true,b,d,f);return}
    			}
    		}else{
    			if(e.code==0){updateSubscribeProdBtnStatus(false)}
    		}
    	   }
    	})
}

function initOtherBuyButton(b){
	if(b!="已售完"&&b!="赠品"){
		for(var a=2;a<=4;a++){
			jQuery("#seriesCartButton"+a).val(b);
		    jQuery("#seriesCartButton"+a).removeClass().addClass("add_shopping_cart");
            jQuery("#doPurchaseBtn"+a).val(b);
            jQuery("#doPurchaseBtn"+a).removeClass().addClass("add_shopping_cart")
         }
      jQuery(".notice_arrival").hide()
    }else{
    	if(b=="已售完"){jQuery(".notice_arrival").show()}
    	for(var a=2;a<=4;a++){
    		jQuery("#seriesCartButton"+a).val(b);
    		jQuery("#seriesCartButton"+a).removeClass().addClass("add_shopping_cart_over");
            jQuery("#doPurchaseBtn"+a).val(b);
            jQuery("#doPurchaseBtn"+a).removeClass().addClass("add_shopping_cart_over")
        }
    }
}
/*商品规格和颜色*/

var lazyLoadImageObjArry=lazyLoadImageObjArry||[];
var isBusy=false;
var threadCount=0;
var imgCountPerTime=1000;
var lazyLoadDelay=50;
(
	function(a){
	  a.YHD={
	    imgLoad:{
		         objArray:[],
	             loadImg:function(d){
	                  if(d&&d.length>0){
	                         for(var c=0,b=d.length;c<b;c++){
	                             if(a.inArray(d[c],a.YHD.imgLoad.objArray)==-1){
	                            	 a.YHD.imgLoad.objArray.push(d[c]);
	                             }
	                         }
	                     }
	             },
	             pageTop:function(){
	            	    return document.documentElement.clientHeight+
	            	        Math.max(document.documentElement.scrollTop,document.body.scrollTop);
	            },
	            load:function(){
	            	if(window.shutdownImgLoad){return false;}
	            	isBusy=true;
	            	threadCount=0;
	            	var k=a.YHD.imgLoad.pageTop();
	            	for(var d=0,c=a.YHD.imgLoad.objArray.length;d<c;d++){
	            		var g=a("#"+a.YHD.imgLoad.objArray[d]);
	            		if(g){
	            			var f=g.find("img");
	            			for(var j=0,h=f.size();j<h;j++){
	            				var e=a(f[j]);
	            				if(e.offset().top<=k+100){
	            					var b=e.attr("original");
	            					if(b){
	            						e.attr("src",b).removeAttr("original");
	            						threadCount++;
	            						if(threadCount>=imgCountPerTime){break;}
	            					}
	            				}
	            			}
	            	  }
	            	   if(threadCount>=imgCountPerTime){break;}
	              }
	              if(threadCount>=imgCountPerTime){
	            	  setTimeout("jQuery.YHD.imgLoad.load()",lazyLoadDelay);
	              }else{
	            	  isBusy=false;
	              }
	            }
	        }
	 }
})(jQuery);
/*商品加减法0-999*/

/*商品减法*/
function decrement(d){
	var lazyLoadImageObjArry=lazyLoadImageObjArry||[];
	var isBusy=false;
	var threadCount=0;
	var imgCountPerTime=1000;
	var lazyLoadDelay=50;
	if(d.size()>0){
		var a=d.val();
		var c=/^[1-9]\d{0,2}$/g;
		if(!a.match(c)){
			d.val(1);
			a=1;
		}
		var b=parseInt(a)-1;
		if(b>999){b=999;}
		if(b<=0){
			b=1;
		}
		d.val(b);
   }
}

function decrementAll(){
	decrement(jQuery("#product_amount"));
	for(var a=2;a<=4;a++){
		decrement(jQuery("#product_amount"+a));
	}
}

/*商品加法*/
function increment(d){
	if(d.size()>0){
		var a=d.val();
		var c=/^[1-9]\d{0,2}$/g;
		if(!a.match(c)){alert("输入的数量有误,应为[1-999]");d.val(1);a=1;}
		var b=parseInt(a)+1;
		if(b>999){b=999;}
		if(jQuery("#perOrderQuotaNum").size()>0){
			var e=jQuery("#perOrderQuotaNum").attr("value");
			if(e!=null){
				e=parseInt(e);
			    if(e>0&&b>e){alert("该商品每单限购 ["+e+"] 件，请修改购物数量,若给您带来不便，我们深表歉意。");b=e;}
			}
		}
		d.val(b);
	}
}

function incrementAll(){
	increment(jQuery("#product_amount"));
	for(var a=2;a<=4;a++){
		increment(jQuery("#product_amount"+a));
	}
}

function buyButtonNumChange(c){
	var a=jQuery("#"+c).val();
	if(c!="product_amount"){
		jQuery("#product_amount").val(a);
	}
	for(var b=2;b<=4;b++){
		if(c!="#product_amount"+b){
			jQuery("#product_amount"+b).val(a);
		}
	}
}

function inputOnlyNum(c,b,a){
	c.value=c.value.replace(/\D+/g,"");
	if(c.value>a){c.value=a;}
	if(c.value<b){c.value=b;}
}


/*
function qwPopup(url,type) {
	  var title = "JSPGOU - 创意生活零售平台：在线销售 家居 厨浴 装饰 办公 潮流等创意商品";
	  var content = '${product.shareContent!}';
	  var img ="http://localhost:8080${root}/u/${product.productExt.detailImg!}";
	  if (type == "qzone") {
		  url = url + "title=" + encodeURIComponent(title) + "&desc=" + encodeURIComponent(content) + "&url=http%3A%2F%2Fgou.jeecms.com";
	  }
	  if (type == "sina") {
		  url = url + "&title=" + encodeURIComponent(content) +  "&url=http%3A%2F%2Fwww.gou.jeecms.com%2Fgoods-30189.html%3Ffm%3Dshareweibo";
	  }
	  if (type == "renren") {
		  url = url + "title=" + encodeURI(title) +"&content="+ encodeURI(content) + "&url=http%3A%2F%2Fwww.gou.jeecms.com%2Fgoods-30189.html%3Ffm%3Dsharerenren";
	  }
	  if (type == "qblog") {
		  url = url + "title=" + encodeURI(title) +"&url=http%3A%2F%2Fwww.gou.com%2Fgoods-.html%3Ffm%3Dsharetqq%26argv2%3Dwoqu"+"&appkey=f92ccbb0e5bd4fdd8cc4343602f4ef98"+"&site=http%3A%2F%2Fwww.gou.jeecms.com%2Fgoods-.html%3Ffm%3Dsharetqq%26argv2%3Dwoqu";
	  }  
	  if(img != ''){
		url +="&pic=" + encodeURI(img);
	  }
	  window.open(url, "", "height=500, width=600");
	}
*/
/*分享微博*/
function posttoWb(url,f,e){
	/*var f=encodeURI(document.title);*/
	var c=encodeURI(window.location);
	/*var e="2fc26c2514bb48ec891170ce68db2683";*/
	var a=encodeURI;
	var b="";
	var d=url+"?title="+f+"&url="+c+"&appkey="+e+"&site="+a+"&pic="+b;
	window.open(d,"转播到腾讯微博","width=700, height=580, top=180, left=320, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no")
}

/*商品小图片左右滑动*/
function pictureSlider(){
	var a=5;
	var e=1;
	var d=jQuery("#sliderImgs ul li").size();
	var c=Math.ceil(d/a);
	var f=jQuery("#sliderImgs").width()+10;
	var b=jQuery("#detailPictureSlider");

jQuery("#prevBtn").click(function(){
	if(e>1){
		if(!b.is(":animated")){
			b.animate({marginLeft:"+="+f},"slow");e--;
		}
	}
});

jQuery("#nextBtn").click(function(){
	if(e<c){
		showOriginalPicture();
		if(!b.is(":animated")){
			b.animate({marginLeft:"-="+f},"slow");
			e++;
		}
	}
 });
}

function showOriginalPicture(){
	jQuery("#detailPictureSlider li").each(function(){
		if(jQuery(this).find("img").attr("original")){
			jQuery(this).find("img").attr("src",jQuery(this).find("img").attr("original"));
		}
	});
}

/*商品小图片切换*/

function initCurrPicture(a,root,bigpic,amppic){
	if(a.hasClass("selected")){return;}
	jQuery("#sliderImgs img").removeClass("selected");
	a.addClass("selected");
	jQuery("#productImg").attr("src",root+bigpic);
	jQuery("#productImgA").attr("href",root+amppic);
}

/*商品小图片切换*/
jQuery("#productImgA").bind("click",
		function(d){
	           var c=jQuery("#productId").val();
	           var e=jQuery("#merchantId").val();
	           var b="detail_productImage_"+c+"_"+e;
	           gotracker(1,b,c);
	           openImageDiv($(".proDtlTip"));
	    }
);

(function($){
	$.fn.jqzoom=function(options){
		var settings={
				zoomType:"standard",
				zoomWidth:200,
				zoomHeight:200,
				xOffset:10,yOffset:0,
				position:"right",
				lens:true,
				lensReset:false,
				imageOpacity:0.2,
				title:true,
				alwaysOn:false,
				showEffect:"show",
				hideEffect:"hide",
				fadeinSpeed:"fast",
				fadeoutSpeed:"slow",
				preloadImages:true,
				showPreload:true,
				preloadText:"Loading zoom",
				preloadPosition:"center"
			};
		    options=options||{};
			$.extend(settings,options);
			return this.each(function(){
					var a=$(this);
					var aTitle=a.attr("title");
					$(a).removeAttr("title");
					$(a).css("outline-style","none");
					var img=$("img",this);
					var imageTitle=img.attr("title");
					img.removeAttr("title");
					var smallimage=new Smallimage(img);
					var smallimagedata={};
					var btop=0;var bleft=0;
					var loader=null;
					loader=new Loader();
					var ZoomTitle=(jQuery.trim(aTitle).length>0)?aTitle:(jQuery.trim(imageTitle).length>0)?imageTitle:null;
                    var ZoomTitleObj=new zoomTitle();
                    var largeimage=new Largeimage(a[0].href);
                    var lens=new Lens();var lensdata={};
                    var largeimageloaded=false;var scale={};
                    var stage=null;
                    var running=false;
                    var mousepos={};
                    var firstime=0;
                    var preloadshow=false;
                    var isMouseDown=false;
                    var dragstatus=false;
                    smallimage.loadimage();
                    $(this).click(function(){return false});
                    $(this).hover(function(e){
                    	mousepos.x=e.pageX;
                    	mousepos.y=e.pageY;
                    	activate()},
                         function(){deactivate()}
                    );
                    if(settings.alwaysOn){setTimeout(function(){activate()},150)}
                    function activate(){
                    	if(!running){
                    		smallimage.findborder();
                    		running=true;
                    		imageTitle=img.attr("title");
                    		img.removeAttr("title");
                    		aTitle=a.attr("title");
                    		$(a).removeAttr("title");
                    		largeimage=new Largeimage(a[0].href);
                    		largeimage.loadimage();
                    		a[0].blur();return false
                    	}
                    }
                    function deactivate(){
                    	if(settings.zoomType=="reverse"&&!settings.alwaysOn){
                    		img.css({opacity:1})
                        }
                    	if(!settings.alwaysOn){
                    		running=false;
                    		largeimageloaded=false;
                    		$(lens.node).unbind("mousemove");
                    		lens.remove();if($("div.jqZoomWindow").length>0){stage.remove()}
                    		if($("div.jqZoomTitle").length>0){ZoomTitleObj.remove()}
                    		img.attr("title",imageTitle);
                    		a.attr("title",aTitle);
                    		$().unbind();
                    		a.unbind("mousemove");
                    		firstime=0;
                    		if(jQuery(".zoom_ieframe").length>0){
                    			jQuery(".zoom_ieframe").remove()
                    		}
                    	}else{
                    		if(settings.lensReset){
                    			switch(settings.zoomType){
                    			      case"innerzoom":largeimage.setcenter();break;default:lens.center();break
                    		     }
                    		}
                    	}
                    	if(settings.alwaysOn){activate()}
                    }
                    function Smallimage(image){
                    	this.node=image[0];
                    	this.loadimage=function(){this.node.src=image[0].src};
                    	this.findborder=function(){
                    	var bordertop="";
                    	bordertop=$(img).css("border-top-width");
                    	btop="";
                    	var borderleft="";
                    	borderleft=$(img).css("border-left-width");
                    	bleft="";
                       if(bordertop){
                    	   for(i=0;i<3;i++){
                    		   var x=[];x=bordertop.substr(i,1);
                    	      if(isNaN(x)==false){
                    	    	  btop=btop+""+bordertop.substr(i,1)
                    	      }else{break}
                    	   }
                       }
                       if(borderleft){
                    	   for(i=0;i<3;i++){
                    		   if(!isNaN(borderleft.substr(i,1))){
                    			   bleft=bleft+borderleft.substr(i,1)
                    			}else{break}
                    		}
                      }
                       btop=(btop.length>0)?eval(btop):0;
                       bleft=(bleft.length>0)?eval(bleft):0};
                       this.node.onload=function(){
                    	   a.css({cursor:"crosshair"});
                    	   if(a.css("position")!="absolute"&&a.parent().css("position")){
                    		   a.css({cursor:"crosshair",position:"relative"})
                    	   }
                    	   if(a.parent().css("position")!="absolute"){
                    		   a.parent().css("position","relative")
                    		}else{}
                    	   if($.browser.safari||$.browser.opera){
                    		   $(img).css({position:"absolute",top:"0px",left:"0px"})}
                    	   smallimagedata.w=$(this).width();
                    	   smallimagedata.h=$(this).height();
                    	   smallimagedata.h=$(this).height();
                    	   smallimagedata.pos=$(this).offset();
                           smallimagedata.pos.l=$(this).offset().left;smallimagedata.pos.t=$(this).offset().top;
                           smallimagedata.pos.r=smallimagedata.w+smallimagedata.pos.l;
                           smallimagedata.pos.b=smallimagedata.h+smallimagedata.pos.t;
                           a.height(smallimagedata.h);
                           a.width(smallimagedata.w);
                           if(settings.preloadImages){largeimage.loadimage()}
                        };
                        return this
                       }
                    function Lens(){
                    	this.node=document.createElement("div");$(this.node).addClass("jqZoomPup");
                    	this.node.onerror=function(){$(lens.node).remove();
                    	lens=new Lens();
                    	lens.activate()};
                    	this.loadlens=function(){
                    		switch(settings.zoomType){
                    		      case"reverse":this.image=new Image();
                    		                    this.image.src=smallimage.node.src;
                    		                    this.node.appendChild(this.image);
                    		                    $(this.node).css({opacity:1});
                    		                    break;
                    		      case"innerzoom":this.image=new Image();
                    		                      this.image.src=largeimage.node.src;
                    		                      this.node.appendChild(this.image);
                    		                      $(this.node).css({opacity:1});
                    		                      break;
                    		      default:break
                    		     }
                    	    switch(settings.zoomType){
                    	         case"innerzoom":lensdata.w=smallimagedata.w;
                    	                         lensdata.h=smallimagedata.h;
                    	                         break;
                    	         default:lensdata.w=(settings.zoomWidth)/scale.x;
                    	                 lensdata.h=(settings.zoomHeight)/scale.y;
                    	                 break
                    	      }
                    	    $(this.node).css({
                    	    	width:lensdata.w+"px",
                    	    	height:lensdata.h+"px",
                    	    	position:"absolute",
                    	    	display:"none",
                    	    	borderWidth:1+"px"});
                    	    a.append(this.node)
                    	   };
                    	   return this
                    	}
                    Lens.prototype.activate=function(){
                    	this.loadlens();
                    	switch(settings.zoomType){
                    	          case"reverse":img.css({opacity:settings.imageOpacity});
                    	                       (settings.alwaysOn)?lens.center():lens.setposition(null);
                    	                       a.bind("mousemove",
                    	                       function(e){mousepos.x=e.pageX;mousepos.y=e.pageY;lens.setposition(e)});
                    	                       break;
                    	           case"innerzoom":$(this.node).css({top:0,left:0});
                    	                           if(settings.title){ZoomTitleObj.loadtitle()}
                    	                           largeimage.setcenter();
                    	                           a.bind("mousemove",function(e){
                    	                        	   mousepos.x=e.pageX;
                                                       mousepos.y=e.pageY;largeimage.setinner(e)});
                    	                           break;
                    	           default:(settings.alwaysOn)?lens.center():lens.setposition(null);
                    	                    $(a).bind("mousemove",function(e){mousepos.x=e.pageX;mousepos.y=e.pageY;lens.setposition(e)});
                    	                    break
                    	}
                    	return this
                    };
                    Lens.prototype.setposition=function(e){
                    	if(e){mousepos.x=e.pageX;mousepos.y=e.pageY}
                    	if(firstime==0){
                    		var lensleft=(smallimagedata.w)/2-(lensdata.w)/2;
                    		var lenstop=(smallimagedata.h)/2-(lensdata.h)/2;
                    		$("div.jqZoomPup").show();
                    		if(settings.lens){this.node.style.visibility="visible"}else{this.node.style.visibility="hidden";$("div.jqZoomPup").hide()}firstime=1}else{var lensleft=mousepos.x-smallimagedata.pos.l-(lensdata.w)/2;var lenstop=mousepos.y-smallimagedata.pos.t-(lensdata.h)/2}if(overleft()){lensleft=0+bleft}else{if(overright()){if($.browser.msie){lensleft=smallimagedata.w-lensdata.w+bleft+1
}else{lensleft=smallimagedata.w-lensdata.w+bleft-1}}}if(overtop()){lenstop=0+btop}else{if(overbottom()){if($.browser.msie){lenstop=smallimagedata.h-lensdata.h+btop+1}else{lenstop=smallimagedata.h-lensdata.h-1+btop}}}lensleft=parseInt(lensleft);lenstop=parseInt(lenstop);$("div.jqZoomPup",a).css({top:lenstop,left:lensleft});if(settings.zoomType=="reverse"){$("div.jqZoomPup img",a).css({position:"absolute",top:-(lenstop-btop+1),left:-(lensleft-bleft+1)})}this.node.style.left=lensleft+"px";this.node.style.top=lenstop+"px";largeimage.setposition();function overleft(){return mousepos.x-(lensdata.w+2*1)/2-bleft<smallimagedata.pos.l}function overright(){return mousepos.x+(lensdata.w+2*1)/2>smallimagedata.pos.r+bleft}function overtop(){return mousepos.y-(lensdata.h+2*1)/2-btop<smallimagedata.pos.t}function overbottom(){return mousepos.y+(lensdata.h+2*1)/2>smallimagedata.pos.b+btop
}return this};Lens.prototype.center=function(){$("div.jqZoomPup",a).css("display","none");var lensleft=(smallimagedata.w)/2-(lensdata.w)/2;var lenstop=(smallimagedata.h)/2-(lensdata.h)/2;this.node.style.left=lensleft+"px";this.node.style.top=lenstop+"px";$("div.jqZoomPup",a).css({top:lenstop,left:lensleft});if(settings.zoomType=="reverse"){$("div.jqZoomPup img",a).css({position:"absolute",top:-(lenstop-btop+1),left:-(lensleft-bleft+1)})}largeimage.setposition();if($.browser.msie){$("div.jqZoomPup",a).show()}else{setTimeout(function(){$("div.jqZoomPup").fadeIn("fast")},10)}};Lens.prototype.getoffset=function(){var o={};o.left=parseInt(this.node.style.left);o.top=parseInt(this.node.style.top);return o};Lens.prototype.remove=function(){if(settings.zoomType=="innerzoom"){$("div.jqZoomPup",a).fadeOut("fast",function(){$(this).remove()
})}else{$("div.jqZoomPup",a).remove()}};Lens.prototype.findborder=function(){var bordertop="";bordertop=$("div.jqZoomPup").css("borderTop");lensbtop="";var borderleft="";borderleft=$("div.jqZoomPup").css("borderLeft");lensbleft="";if($.browser.msie){var temp=bordertop.split(" ");bordertop=temp[1];var temp=borderleft.split(" ");borderleft=temp[1]}if(bordertop){for(i=0;i<3;i++){var x=[];x=bordertop.substr(i,1);if(isNaN(x)==false){lensbtop=lensbtop+""+bordertop.substr(i,1)}else{break}}}if(borderleft){for(i=0;i<3;i++){if(!isNaN(borderleft.substr(i,1))){lensbleft=lensbleft+borderleft.substr(i,1)}else{break}}}lensbtop=(lensbtop.length>0)?eval(lensbtop):0;lensbleft=(lensbleft.length>0)?eval(lensbleft):0};function Largeimage(url){this.url=url;this.node=new Image();this.loadimage=function(){if(!this.node){this.node=new Image()
}this.node.style.position="absolute";this.node.style.display="none";this.node.style.left="-5000px";this.node.style.top="10px";loader=new Loader();if(settings.showPreload&&!preloadshow){loader.show();preloadshow=true}document.body.appendChild(this.node);this.node.src=this.url};this.node.onload=function(){this.style.display="block";var w=Math.round($(this).width());var h=Math.round($(this).height());this.style.display="none";scale.x=(w/smallimagedata.w);scale.y=(h/smallimagedata.h);if($("div.preload").length>0){$("div.preload").remove()}largeimageloaded=true;if(settings.zoomType!="innerzoom"&&running){stage=new Stage();stage.activate()}if(running){lens=new Lens();lens.activate()}if($("div.preload").length>0){$("div.preload").remove()}};return this}Largeimage.prototype.setposition=function(){this.node.style.left=Math.ceil(-scale.x*parseInt(lens.getoffset().left)+bleft)+"px";
this.node.style.top=Math.ceil(-scale.y*parseInt(lens.getoffset().top)+btop)+"px"};Largeimage.prototype.setinner=function(e){this.node.style.left=Math.ceil(-scale.x*Math.abs(e.pageX-smallimagedata.pos.l))+"px";this.node.style.top=Math.ceil(-scale.y*Math.abs(e.pageY-smallimagedata.pos.t))+"px";$("div.jqZoomPup img",a).css({position:"absolute",top:this.node.style.top,left:this.node.style.left})};Largeimage.prototype.setcenter=function(){this.node.style.left=Math.ceil(-scale.x*Math.abs((smallimagedata.w)/2))+"px";this.node.style.top=Math.ceil(-scale.y*Math.abs((smallimagedata.h)/2))+"px";$("div.jqZoomPup img",a).css({position:"absolute",top:this.node.style.top,left:this.node.style.left})};function Stage(){var leftpos=smallimagedata.pos.l;var toppos=smallimagedata.pos.t;this.node=document.createElement("div");
$(this.node).addClass("jqZoomWindow");$(this.node).css({position:"absolute",width:Math.round(settings.zoomWidth)+"px",height:Math.round(settings.zoomHeight)+"px",display:"none",zIndex:10000,overflow:"hidden"});switch(settings.position){case"right":leftpos=(smallimagedata.pos.r+Math.abs(settings.xOffset)+settings.zoomWidth<screen.width)?(smallimagedata.pos.l+smallimagedata.w+Math.abs(settings.xOffset)):(smallimagedata.pos.l-settings.zoomWidth-Math.abs(settings.xOffset));topwindow=smallimagedata.pos.t+settings.yOffset+settings.zoomHeight;toppos=(topwindow<screen.height&&topwindow>0)?smallimagedata.pos.t+settings.yOffset:smallimagedata.pos.t;break;case"left":leftpos=(smallimagedata.pos.l-Math.abs(settings.xOffset)-settings.zoomWidth>0)?(smallimagedata.pos.l-Math.abs(settings.xOffset)-settings.zoomWidth):(smallimagedata.pos.l+smallimagedata.w+Math.abs(settings.xOffset));
topwindow=smallimagedata.pos.t+settings.yOffset+settings.zoomHeight;toppos=(topwindow<screen.height&&topwindow>0)?smallimagedata.pos.t+settings.yOffset:smallimagedata.pos.t;break;case"top":toppos=(smallimagedata.pos.t-Math.abs(settings.yOffset)-settings.zoomHeight>0)?(smallimagedata.pos.t-Math.abs(settings.yOffset)-settings.zoomHeight):(smallimagedata.pos.t+smallimagedata.h+Math.abs(settings.yOffset));leftwindow=smallimagedata.pos.l+settings.xOffset+settings.zoomWidth;leftpos=(leftwindow<screen.width&&leftwindow>0)?smallimagedata.pos.l+settings.xOffset:smallimagedata.pos.l;break;case"bottom":toppos=(smallimagedata.pos.b+Math.abs(settings.yOffset)+settings.zoomHeight<$("body").height())?(smallimagedata.pos.b+Math.abs(settings.yOffset)):(smallimagedata.pos.t-settings.zoomHeight-Math.abs(settings.yOffset));
leftwindow=smallimagedata.pos.l+settings.xOffset+settings.zoomWidth;leftpos=(leftwindow<screen.width&&leftwindow>0)?smallimagedata.pos.l+settings.xOffset:smallimagedata.pos.l;break;default:leftpos=(smallimagedata.pos.l+smallimagedata.w+settings.xOffset+settings.zoomWidth<screen.width)?(smallimagedata.pos.l+smallimagedata.w+Math.abs(settings.xOffset)):(smallimagedata.pos.l-settings.zoomWidth-Math.abs(settings.xOffset));toppos=(smallimagedata.pos.b+Math.abs(settings.yOffset)+settings.zoomHeight<screen.height)?(smallimagedata.pos.b+Math.abs(settings.yOffset)):(smallimagedata.pos.t-settings.zoomHeight-Math.abs(settings.yOffset));break}this.node.style.left=leftpos+"px";this.node.style.top=toppos+"px";return this}Stage.prototype.activate=function(){if(!this.node.firstChild){this.node.appendChild(largeimage.node)
}if(settings.title){ZoomTitleObj.loadtitle()}document.body.appendChild(this.node);switch(settings.showEffect){case"show":$(this.node).show();break;case"fadein":$(this.node).fadeIn(settings.fadeinSpeed);break;default:$(this.node).show();break}$(this.node).show();if($.browser.msie&&$.browser.version<7){this.ieframe=$('<iframe class="zoom_ieframe" frameborder="0" src="#"></iframe>').css({position:"absolute",left:this.node.style.left,top:this.node.style.top,zIndex:99,width:settings.zoomWidth,height:settings.zoomHeight}).insertBefore(this.node)}largeimage.node.style.display="block"};Stage.prototype.remove=function(){switch(settings.hideEffect){case"hide":$(".jqZoomWindow").remove();break;case"fadeout":$(".jqZoomWindow").fadeOut(settings.fadeoutSpeed);break;default:$(".jqZoomWindow").remove();break
}};function zoomTitle(){this.node=jQuery("<div />").addClass("jqZoomTitle").html(""+ZoomTitle+"");this.loadtitle=function(){if(settings.zoomType=="innerzoom"){$(this.node).css({position:"absolute",top:smallimagedata.pos.b+3,left:(smallimagedata.pos.l+1),width:smallimagedata.w}).appendTo("body")}else{$(this.node).appendTo(stage.node)}}}zoomTitle.prototype.remove=function(){$(".jqZoomTitle").remove()};function Loader(){this.node=document.createElement("div");$(this.node).addClass("preload");$(this.node).html(settings.preloadText);$(this.node).appendTo("body").css("visibility","hidden");this.show=function(){switch(settings.preloadPosition){case"center":loadertop=smallimagedata.pos.t+(smallimagedata.h-$(this.node).height())/2;loaderleft=smallimagedata.pos.l+(smallimagedata.w-$(this.node).width())/2;
break;default:var loaderoffset=this.getoffset();loadertop=!isNaN(loaderoffset.top)?smallimagedata.pos.t+loaderoffset.top:smallimagedata.pos.t+0;loaderleft=!isNaN(loaderoffset.left)?smallimagedata.pos.l+loaderoffset.left:smallimagedata.pos.l+0;break}$(this.node).css({top:loadertop,left:loaderleft,position:"absolute",visibility:"visible"})};return this}Loader.prototype.getoffset=function(){var o=null;o=$("div.preload").offset();return o}})}})(jQuery);

/* SVN.committedRevision=328265 */
search_roll_revel("changeBuy",900);
mouseover_add_class("itemSearchResult","li");
nav_search_page();
removeMarginTopDist();
removeFashionListClass();
search_Sort_Margin();
attribute_Selected();survey();
picChange();
function keywordLog(d,c){var b=true;if(b){var a="/fulltext/search_log.do";$.post(a,{keyword:d,resultCount:c})}}function shoppingcountOnclick(a){window.location.href=a}function loadCompleteProductsInfo(data){var divIdList=jQuery("#productsIdList")[0];if(!divIdList){return}var idList=divIdList.value;eval("var search_list = ["+idList+"];");for(var i=0;i<data.length;i++){var product=data[i];if(product==null){continue}if(product.productId==null){continue}var productId=product.productId;var specialType=jQuery("#buyButton_"+productId).attr("specialType");if(specialType==50){var favorButton=jQuery("#favorButton_"+productId)[0];
favorButton.onclick=function(event){void (0)}}if(product.productStock==-1){continue}if(product.defaultMerchantId==-1){continue}if(product.productPrice==-1){continue}var merchantId=product.defaultMerchantId;var productDetailUrlPrefix=URLPrefix.central;if(merchantId>1){var brandicUrl=URLPrefix.shop+"/merchantfront/getServiceURL.action?merchantId="+merchantId+"&siteId="+currSiteId+"&productId="+productId;for(var m=0;m<=3;m++){var bdLinkId="bdlink"+m+"_"+productId;var bdLink=jQuery("#"+bdLinkId)[0];if(bdLink){bdLink.href=brandicUrl;productDetailUrlPrefix=URLPrefix.mall}}var clothesUrl=URLPrefix.shop+"/merchantfront/accessAction.action?productId="+productId+"&merchantId="+merchantId+"&siteId="+currSiteId;var csLinkId="cslink_"+productId;var csLink=jQuery("#"+csLinkId)[0];if(csLink){csLink.href=clothesUrl;
productDetailUrlPrefix=URLPrefix.mall}}for(var m=0;m<=3;m++){var priceSpanId="price"+m+"_"+productId;var priceSpan=jQuery("#"+priceSpanId)[0];if(typeof(priceSpan)!="undefined"){priceSpan.innerHTML="￥"+product.productPrice}var listpriceSpan=jQuery("#listprice"+m+"_"+productId)[0];if(typeof(listpriceSpan)!="undefined"){if(product.marketPrice<product.productPrice){$(listpriceSpan).text("")}else{$(listpriceSpan).text("￥"+product.marketPrice)}}}var productDetailUrl=productDetailUrlPrefix+"/product/"+productId+"_"+merchantId;for(var m=0;m<=3;m++){for(var n=1;n<=3;n++){var pdLinkId="pdlink"+(10*m+n)+"_"+productId;var pdLink=jQuery("#"+pdLinkId)[0];if(!pdLink){continue}pdLink.href=productDetailUrl}}var favorButton=jQuery("#favorButton_"+productId)[0];if(!favorButton){continue}if(specialType!=50){favorButton.index=jQuery("#favorButton_"+productId).attr("index");
favorButton.productId=productId;favorButton.merchantId=merchantId;favorButton.onclick=function(event){submitFavorite(this.productId,this.merchantId);gotracker("2","fav_"+this.index,this.productId)}}var buyButtonA=jQuery("#buyButton_"+productId)[0];buyButtonA.productId=productId;buyButtonA.merchantId=merchantId;buyButtonA.index=jQuery("#buyButton_"+productId).attr("index");buyButtonA.url=productDetailUrl;if(product.productType==1||product.productPrice==0){buyButtonA.onclick=function(event){javascript:window.open(this.url);gotracker("2","btn3_0",this.productId)};buyButtonA.target="_blank"}if(product.productPrice<0.01||specialType==50){continue}if(specialType==16||specialType==17||specialType==18){buyButtonA.onclick=function(event){YHD.alertPrescriotion(16);gotracker("2","btn3_0",this.productId)
};continue}if(product.productStock<=0){buyButtonA.className="sellout";buyButtonA.onclick=function(event){void (0)};buyButtonA.innerHTML="已售完"}else{if(product.productStatus==2){buyButtonA.onclick=function(event){addToCart(event,this.productId,this.merchantId,1,true);gotracker("2","btn2_"+this.index,this.productId)};buyButtonA.innerHTML="预售登记"}else{if(product.shoppingcount>1){buyButtonA.shoppingcount=product.shoppingcount;buyButtonA.onclick=function(event){addToCart(event,this.productId,this.merchantId,this.shoppingcount,true);gotracker("2","shopcount_"+this.index,this.productId)};buyButtonA.innerHTML=product.shoppingcount+"件起购"}else{if(product.productType==1){continue}buyButtonA.onclick=function(event){addToCart(event,this.productId,this.merchantId,1,true);gotracker("2","btn_"+this.index,this.productId)
};buyButtonA.innerHTML="立即购买"}}}}}function ajaxRequestProductInfo(){var e=jQuery("#browsesIdList")[0];if(!e){return}var c=e.value;var b=c.split(",");var d="";for(i=0;i<b.length;i++){d+="&productIds="+b[i]}var a=URLPrefix.products_stock+"/restful/truestock?mcsite="+currSiteId+"&provinceId="+currProvinceId+d+"&callback=?";jQuery.getJSON(a,function(f){if(f.ERROR){alert("ERROR = "+f.ERROR)}else{loadCompleteProductsInfo(f)}})}function getProductPrice(b,c){var a=URLPrefix.products_stock+"/restful/truestock?mcsite="+currSiteId+"&provinceId="+currProvinceId+"&productIds="+b+"&callback=?";jQuery.getJSON(a,function(g){if(g.ERROR){alert("ERROR = "+g.ERROR)}else{var f=g[0];var e=$("#price0_"+c);var d=$("#listprice0_"+c);e.text("￥"+f.productPrice);if(f.marketPrice<f.productPrice){d.text("")}else{d.text("￥"+f.marketPrice)
}}})}function loadCompleteImageAdvertises(c){for(var b in c){var a=$("#divAd_"+b);a.html(c[b]);a.show()}}function ajaxImageAdvertises(b){var a=URLPrefix.central+"/globalsupport/imageAdvertises.do?locationKeys="+b+"&isWidescreen="+isWidescreen+"&callback=?";jQuery.getJSON(a,function(c){if(c.ERROR){alert("ERROR = "+c.ERROR)}else{loadCompleteImageAdvertises(c)}})}function ajaxChannelAdvertises(){var a=jQuery("#advertisesCatId")[0];if(!a){return}var c=a.value;var b=URLPrefix.central+"/globalsupport/channelAdvertises.do?categoryId="+c+"&titlePrefix=&titleLimitLen=20&maxItemCount=6&callback=?";jQuery.getJSON(b,function(e){if(e.ERROR){alert("ERROR = "+e.ERROR)}else{var d=$("#divAd_TopRightChannel");d.html(e.value)}})}if(lazyLoadImageObjArry){$(".producteg").each(function(){lazyLoadImageObjArry.push($(this).attr("id"))
});if(currSiteId==1){$("#hotSale .pic").each(function(){lazyLoadImageObjArry.push($(this).attr("id"))})}}jQuery(document).ready(function(){if(urlSearchType=="s"||urlSearchType=="s2"){ajaxImageAdvertises("SEARCH_SCATTERED_TOP_DEFAULT,SEARCH_SCATTERED_LEFT_FIRST,SEARCH_SCATTERED_LEFT_SEC");lazyLoadPMS_Ad02HtmlData();lazyLoadPMS_Ad01HtmlData();if(currSiteId==1){loadPMS_Ad03HtmlData()}lazyLoadSearchHistoryHtmlData();lazyLoadBrowsesHtmlData();if(searchAbtMax){var a=jQuery.cookie("search_abt");trackerContainer.addParameter(new Parameter("extField3",a))}}jQuery("#jumptopage").bind("click",function(){on_change()})});jQuery(function(){YHD.SPagelazyLoade.init()});function listBrowseHistoryV2(c){var a=$.cookie("search_browse_history");if(a){a=decodeURI(a);var d=a.split(",");var b="/product/requestProductInfo.do?productID="+a;
new Ajax.Request(b,{method:"get",onComplete:loadCompleteV2})}}function loadCompleteV2(response){var searchmap=eval("("+response.responseText+")");for(var i=0;i<hislist.length;i++){var product=searchmap["key_"+hislist[i]];if(product){}}}function clearBrowseHistoryV2(){var a={path:"/",domain:URLPrefix.sitedomain,expireDays:-1};jQuery.cookie("search_browse_history","",a);jQuery("#browseDiv")[0].innerHTML=""}function clearSearchHistory(){var a={path:"/",domain:URLPrefix.sitedomain,expireDays:-1};jQuery.cookie("search_keyword_history","",a);jQuery("#searchHistoryDiv")[0].innerHTML=""}function popupSerialProduct(a,c){var b="/product/"+c;b="http://www.cnblogs.com/QLeelulu/archive/2008/03/30/1130270.html";var d=jQuery("#"+a)[0];d.style.display="";d.load(b)}function replaceDivInnerHtml(b){var a=$(".showMoreFlag");
if(a.prev().is("dd")){a.parent().children("dd").hide();a.nextAll("dd").show()}else{a.parent().children().hide();a.nextAll().show()}}function replaceDivDisplay(b,a){var d=jQuery("#"+b)[0];var c=jQuery("#"+a)[0];c.style.display="none";d.style.display="";$("#TreeCat0").find("ul").slideUp();$("#TreeCat0").find(".icon_btn").removeClass("open");$("#Div0").find("ul").slideUp();$("#Div0").find(".icon_btn").removeClass("open");if(jQuery("#FacetBrandsMoreBtn")[0]&&jQuery("#FacetBrandsBackBtn")[0]){if(b=="FacetBrandsAll"){jQuery("#FacetBrandsMoreBtn")[0].style.display="none";jQuery("#FacetBrandsBackBtn")[0].style.display="block"}if(b=="FacetBrands"){jQuery("#FacetBrandsMoreBtn")[0].style.display="block";jQuery("#FacetBrandsBackBtn")[0].style.display="none"}}}

function showMore(a,d){
	if(jQuery("#FacetBrandsMoreBtn")[0]&&jQuery("#FacetBrandsBackBtn")[0]){
		if(d&&d=="more"){
			jQuery("#FacetBrandsMoreBtn")[0].style.display="none";
            jQuery("#FacetBrandsBackBtn")[0].style.display="block"
        }else{
        	jQuery("#FacetBrandsMoreBtn")[0].style.display="block";
        	jQuery("#FacetBrandsBackBtn")[0].style.display="none"}
		}
	var c=23;
	if(typeof(cateType)!="undefined"&&cateType==1){c=17}
	var b=$("#"+a).children();
	if(d&&d=="more"){
		b.each(function(e){$(this).show()})
	}else{
		b.each(function(e){if(e>c){$(this).hide()}})
	}
}

function showOrHideMoreAttr(a,b){if(a=="1"){$(".attrMore").each(function(){$(this).show()});$(b).hide().next().show()}else{$(".attrMore").each(function(){$(this).hide()});$(b).hide().prev().show()}}

function clickSearchPriceRange(b,a){
	var c=searchPriceRangeUrl.replace("-price-","-price"+b+","+a+"-");
	window.location.href=c
}

function searchPriceRange(){
	var d=jQuery("#searchPriceRangeMin")[0];
	var a=jQuery("#searchPriceRangeMax")[0];
	var c=/^[0-9]*$/;
    if(!c.test(d.value)){
    	 alert("请输入大于0的整数");
    	 d.focus();return
     }
   if(!c.test(a.value)){ 
	   alert("请输入大于0的整数");a.focus();return
   }
   if((d.value=="")&&(a.value=="")){
	   alert("请输入价格区间");d.focus();return
   }
   if((d.value!="")&&(a.value!="")){
	   if(Number(a.value)<Number(d.value)){
		   alert("输入价格区间 右值必须大于或等于左值");
		   a.focus();return
		}
	}
   var b=searchPriceRangeUrl.replace("-price-","-price"+d.value+","+a.value+"-");
   window.location.href=b;return
}

function on_change(){
	var to=jQuery("#jumpto").val();
	var total=jQuery("#pageCountPage").val();
	var regu="^([0-9]*[.0-9])$";
	var re=new RegExp(regu);
	if(to==""){
		alert("请输入页码");return
    }
	if(isNaN(to)||eval(to)==0){
		alert("输入页码不能为0或其他非数字字符,请重新输入");return
	}
	if(eval(to)<0){
		alert("输入页码不能为负数,请重新输入");return
	}
	if(to.search(re)==-1){
		alert("输入页码不能为小数,请重新输入");return
	}
	if(/^0\d*?/.test(to)){
		to=to.replace(/^0+/g,"");jQuery("#jumpto").val(to)
    }
	if(eval(to)>eval(total)){
		alert("输入页码不能大于最大页码数,请重新输入");return
	}
	var url=0;
	if(jQuery(".page_next").size()>0){
		url=jQuery(".page_next").attr("href")
	}else{
		url=jQuery(".page_prev").attr("href")
	}
	if(url==undefined){return}
	var s=url.split("-p");
	if(currSiteId=="2"){
		if(s.length==3){
			window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]
	     }
		if(s.length==4){
			window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]+"-p"+s[3]
	    }
		if(s.length==5){
			window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]+"-p"+s[3]+"-p"+s[4]
		}
   }else{
	   if(s.length==3){
	       window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]
       }
       if(s.length==4){
	       window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]+"-p"+s[3]
       }
       if(s.length==5){
	      window.location.href=s[0]+"-p"+jQuery("#jumpto").val()+"-p"+s[2]+"-p"+s[3]+"-p"+s[4]
      }
  }
}

function clickPageSize(a){
	addTrackPositionToCookie("1","page_num");
	$.cookie("spz",null);
	$.cookie("spz",a,{path:"/",domain:URLPrefix.sitedomain,expires:365})
}

function clickFilterBox(){
	var a=searchPriceRangeUrl;
	var c="0";
	$("[name='filter']").each(function(){
		if($(this).attr("checked")){var d=$(this).val();c+=d}
	});
	var b;
	if(a.indexOf("-m")>0){
		b=a.substring(0,a.indexOf("-f")+2)+c+a.substring(a.indexOf("-m"),searchPriceRangeUrl.length);
		if($("#muyingcheckbox").attr("checked")!=undefined){
			b=a.substring(0,a.indexOf("/a")+2)+a.substring(a.indexOf("-s"),a.indexOf("-f")+2)+c+a.substring(a.indexOf("-m"),searchPriceRangeUrl.length)
		}
	}else{
			b=a.substring(0,a.indexOf("-f")+2)+c+a.substring(a.indexOf("-k"),searchPriceRangeUrl.length)
	}
	addTrackPositionToCookie("1","search_filter");window.location.href=b
}
function clickFilterTypeGoods(){var a=searchPriceRangeUrl;
var c;$("[name='type_goods']").each(function(){if($(this).attr("checked")){c=$(this).val()}});var b=a.replace(/-d\d-/,"-d"+c+"-");addTrackPositionToCookie("1","type_goods_filter_"+c);window.location.href=b}function lazyLoadBrowsesHtmlData(){var a=URLPrefix.search+"/browses.do?callback=?";jQuery.getJSON(a,function(b){if(b&&b.value){jQuery("#advertisesCatIdDiv").after(b.value);ajaxRequestProductInfo();if(lazyLoadImageObjArry){$("#browseDiv .pic").each(function(){lazyLoadImageObjArry.push($(this).attr("id"))});jQuery.YHD.imgLoad.loadImg(lazyLoadImageObjArry)}}})}function lazyLoadSearchHistoryHtmlData(){var a=URLPrefix.search+"/searchHistory.do?req.keyword="+searchKey+"&callback=?";jQuery.getJSON(a,function(b){if(b&&b.value){jQuery("#advertisesCatIdDiv").before(b.value)}})}function clickAttriItem(){if($("#muyingcheckbox").attr("checked")){$("#muyingcheckbox").attr("checked",false)
}}function locateBabyInfoUrl(a,b){newUrl=window.location.href;if(a==0){window.location.href=URLPrefix.channel+"/muying/dispatchCreate.do?merchant="+b+"&returnUrl="+newUrl}else{if($("#muyingcheckbox").attr("checked")){window.location.href=URLPrefix.channel+"/muying/dispatchCreate.do?merchant="+b+"&returnUrl="+newUrl}}}function lazyLoadPMS_Ad01HtmlData(){var a=function(){var g=jQuery("#hisAssociated");if(!g.size()){return}var c=document.documentElement.clientHeight+Math.max(document.documentElement.scrollTop,document.body.scrollTop);if(g.offset().top>c+1600||g.data("loaded")){return}else{g.data("loaded",true)}var f=jQuery.cookie("provinceId");var d=jQuery.cookie("yihaodian_uid");var b=jQuery.cookie("search_browse_history");var e=URLPrefix.pms+"/pms/getLookProductByBrowse.do?searchKey="+searchKey+"&type=html&urlstatic="+URLPrefix.statics+"&userid="+d+"&searchBrowseHistory="+b+"&currSiteId="+currSiteId+"&provinceId="+f+"&callback=?";
jQuery.getJSON(e,function(h){g.html("");if(h&&h.value){jQuery("#hisAssociated").html(h.value);if(lazyLoadImageObjArry){$("#hisAssociated .pmsCoc").each(function(){lazyLoadImageObjArry.push($(this).attr("id"))});jQuery.YHD.imgLoad.loadImg(lazyLoadImageObjArry);jQuery.YHD.imgLoad.load()}

search_roll_revel("correlationProductTurnB",900)}jQuery(window).unbind("scroll",a)})};jQuery(window).bind("scroll",a);a()}function lazyLoadPMS_Ad02HtmlData(){var d=jQuery.cookie("provinceId");var b=jQuery.cookie("yihaodian_uid");var a=jQuery.cookie("search_browse_history");var c=URLPrefix.pms+"/pms/getLookProductBySearch.do?searchKey="+searchKey+"&brand="+brand+"&category="+category+"&currSiteId="+currSiteId+"&provinceId="+d+"&userid="+b+"&searchBrowseHistory="+a+"&type=html&callback=?";jQuery.getJSON(c,function(e){if(e&&e.value){jQuery("#hotSale").before(e.value);
if(lazyLoadImageObjArry){$("#pms_searchlook .pmsSas").each(function(){lazyLoadImageObjArry.push($(this).attr("id"))});jQuery.YHD.imgLoad.loadImg(lazyLoadImageObjArry)}}})}function loadPMS_Ad03HtmlData(){var adO3Url=URLPrefix.pms+"/pms/getCmsBySearchCondition.do?searchKey="+searchKey+"&type=html&urlstatic="+URLPrefix.statics+"&callback=?";jQuery.getJSON(adO3Url,function(rs){if(rs&&rs.value){var searchKey=$("#keyword").val();var searchBrand="";$("#FacetBrandsAll a").each(function(){if($(this).is(".cur")){searchBrand=$(this).text();return}});var searchCategory=$("div .searchCrumb a:last").text();var tbdSearchKey="#searchKey#";var tbdSearchBrand="#searchBrand#";var tbdSearchCategory="#searchCategory#";var reg1=eval("/"+tbdSearchKey+"/gi");var reg2=eval("/"+tbdSearchBrand+"/gi");var reg3=eval("/"+tbdSearchCategory+"/gi");
var html=rs.value.replace(reg1,searchKey);html=html.replace(reg2,searchBrand);html=html.replace(reg3,searchCategory);jQuery("#scence_guide").html(html);scence_guide()}})}function scence_guide(){var f="#scence_guide",b=$(".searchwrap").position().left,d=$(".searchwrap").width(),e;var c=$(".scont").width()+38;var a=$("#scence_guide").height()/2;$(window).resize(function(){var g=$(window).width()-$(".searchwrap").width();if(g&&g>=110){$(f).css({left:($(".searchwrap").width()+g/2+2)+"px"})}else{if(g&&g<110){$(f).css({right:0})}}}).trigger("resize");setTimeout(function(){$(f+" > .scence_guide").animate({width:0},240);$("h4",f).removeClass("cur")},2400);$(f).css({top:"50%",marginTop:"-"+a+"px",bottom:"auto"});if($.browser.msie&&$.browser.version=="6.0"){$(window).scroll(function(){var g=$(window).scrollTop()+a;
$("#scence_guide").css("top",g+"px")})}$(f).hover(function(){$("h4",this).addClass("cur");clearTimeout(e);$(".scence_guide",f).animate({width:c},240)},function(){$("h4",this).removeClass("cur");e=setTimeout(function(){$(".scence_guide",f).animate({width:0})},1800)}).trigger("mouseover");$(".close",f).click(function(){$(".scence_guide",f).animate({width:0},240)})}var busystcok=URLPrefix.busystock?URLPrefix.busystock:"http://busystock.i.yihaodian.com";jQuery((function(a){YHD=YHD||{};YHD.SPagelazyLoade=new function(){var f=false;var d=0;var g=false;var b=50;var c=this;this.lazyPrice;var e=function(){c.loadPrice();if(c.lazyPrice&&!c.lazyPrice.length){a(window).unbind("scroll",e)}};this.init=function(){e();window.scrollTo(0,0);a(window).bind("scroll",e)};this.pageTop=function(){return document.documentElement.clientHeight+Math.max(document.documentElement.scrollTop,document.body.scrollTop)
};this.loadPrice=function(){if(g){return}g=true;var l=0;try{if(!a.cookie("provinceId")){return}var h=this.pageTop();if(!this.lazyLoadPrice){this.lazyLoadPrice=a("[productid]").get()}var o="?mcsite="+currSiteId+"&provinceId="+a.cookie("provinceId");var m=[];var j={};a.each(this.lazyLoadPrice,function(p,q){if(a(q).attr("productid")&&b>l&&a(q).offset().top<=h+500){o+="&productIds="+a(q).attr("productid");l++;if(j[a(q).attr("productid")]){j[a(q).attr("productid")].add(q)}else{j[a(q).attr("productid")]=a(this)}}else{m.push(q)}});this.lazyLoadPrice=m;if(l>0){try{var k=busystcok+"/busystock/restful/truestock";a.getJSON(k+o+"&callback=?",function(p){loadCompleteProductsInfo(p)})}catch(n){}}}catch(n){setTimeout("YHD.SPagelazyLoade.loadPrice()",d)}if(l>=b){setTimeout("YHD.SPagelazyLoade.loadPrice()",d)
}g=false}}})(jQuery));var car_brand=$("#car_brand");var car_serial=$("#car_serial");var car_displacement=$("#car_displacement");var car_year=$("#car_year");var selectedIndex=0;var brand_url=URLPrefix.channel+"/qiche/searchBrand.do";var serial_url=URLPrefix.channel+"/qiche/searchSerial.do";var displacement_url=URLPrefix.channel+"/qiche/searchDisplacement.do";var year_url=URLPrefix.channel+"/qiche/searchYear.do";jQuery(function(){if(typeof(car_brand.val())!="undefined"){var a=choosedAttrItemsCar.substring(1,choosedAttrItemsCar.length-1).split(",");var e=a[0];var c=a[1];var b=a[2];var d=a[3];$.ajax({url:brand_url,dataType:"jsonp",jsonp:"callback",success:function(h){var g=h.value;car_brand.html("<option value ='0'>请选择品牌</option>");for(var f=0;f<g.length;f++){var j="<option value ='";if(g[f].id==e){j="<option selected value ='"
}car_brand.append(j+g[f].id+"'>"+g[f].name+"</option>")}}});carAttr(car_brand,serial_url,"brandId=",car_serial,e,c,"<option value ='0'>请选择型号</option>");carAttr(car_serial,displacement_url,"serialsId=",car_displacement,c,b,"<option value ='0'>请选择排量</option>");carAttr(car_displacement,year_url,"displacementId=",car_year,b,d,"<option value ='0'>请选择年份</option>")}});function carAttr(b,e,f,g,d,c,a){if(c!=""&&typeof(c)!="undefined"){$.ajax({url:e,data:f+d,dataType:"jsonp",jsonp:"callback",success:function(k){var j=k.value;g.html(a);for(var h=0;h<j.length;h++){var l="<option value ='";if(j[h].id==0){l="<option disabled='disabled' style='color:#999999' value ='"}else{if(j[h].id==c){l="<option selected value ='"}}g.append(l+j[h].id+"'>"+j[h].name+"</option>")}if(b==car_brand){selectedIndex=car_serial.get(0).selectedIndex
}}})}b.change(function(){var h=b.val();if(h!=0){$.ajax({url:e,data:f+h,dataType:"jsonp",jsonp:"callback",success:function(l){var k=l.value;attrChanged(b);for(var j=0;j<k.length;j++){var m="<option value ='";if(k[j].id==0){m="<option disabled='disabled' style='color:#999999' value ='"}g.append(m+k[j].id+"'>"+k[j].name+"</option>")}}})}else{attrChanged(b)}})}function attrChanged(a){if(a==car_brand){car_serial.html("<option value ='0'>请选择型号</option>");car_displacement.html("<option value ='0'>请选择排量</option>");car_year.html("<option value ='0'>请选择年份</option>")}else{if(a==car_serial){if(navigator.appVersion.indexOf("MSIE 5.5")>=0||navigator.appVersion.indexOf("MSIE 6.0")>=0||navigator.appVersion.indexOf("MSIE 7.0")>=0){var d=document.getElementById("car_serial").options;var b=d.length;for(var c=0;
c<b;c++){if(d[c].disabled&&d[c].selected){car_serial.get(0).selectedIndex=selectedIndex;break}}}if(car_serial.get(0).selectedIndex!=selectedIndex){car_displacement.html("<option value ='0'>请选择排量</option>");car_year.html("<option value ='0'>请选择年份</option>")}selectedIndex=car_serial.get(0).selectedIndex}else{car_year.html("<option value ='0'>请选择年份</option>")}}}function clear_car(){addTrackPositionToCookie("1","clear_car");window.location.href=searchCarUrl.replace("-car","")}function search_car(){if(car_brand.val()==0){alert("请选择车型品牌")}else{if(car_serial.val()==0){alert("请选择车型型号")}else{if(car_displacement.val()==0){alert("请选择车型排量")}else{if(car_year.val()==0){alert("请选择车型年份")}else{var a=car_brand.val()+","+car_serial.val()+","+car_displacement.val()+","+car_year.val();var b=searchCarUrl.replace("-car-","-car"+a+"-");
addTrackPositionToCookie("2","search_car");window.location.href=b}}}}}var suggestLength2=0;var curSuggestIndex2=-1;function findNames2(c,e,d){var a="0";if(jQuery("#leaf").size()>0){a=jQuery("#leaf").val()}var f=e.keyCode;if(jQuery(c).val()!=d&&f!="38"&&f!="40"){var b=URLPrefix.search+"/get_keywords2.do?keyword="+encodeURIComponent(encodeURIComponent(jQuery(c).val()))+"&leaf="+a+"&callback=?";jQuery.getJSON(b,function(h){if(h.ERROR){}else{var g=false;jQuery("#searchSuggest2").html(h.value).find("a").each(function(){var j=jQuery(this).find("span").html();if(j){jQuery(this).html(j);jQuery(this).addClass(g?"odd":"even");g=!g;if(jQuery("#searchSuggest2 ul li").size()>0){jQuery("#searchSuggest2 ul li").hover(function(){jQuery(this).addClass("select").siblings().removeClass("select")},function(){jQuery(this).removeClass("select")
})}}});loadComplete_findNames2()}})}}function loadComplete_findNames2(){suggestLength2=jQuery("#searchSuggest2 li").length;curSuggestIndex2=-1;jQuery("#searchSuggest2").show()}function searchMe2(e,g,f){var b=null;var d=document.getElementById("recommendId2");if(d){b=d.value}var h=null;var c=document.getElementById("recommendName2");if(c){h=c.value}if(!e){e=jQuery("#keyword2").val()}if(e!=null&&e!=""){addKeywordHistory(e)}e=e.replace(/\//gi," ");var a="0";if(jQuery("#leaf").size()>0){a=jQuery("#leaf").val()}if(g!=null&&g!="0"){window.location=URLPrefix.search_keyword+"/s2/c"+g+"-"+f+"/k"+encodeURIComponent(encodeURIComponent(e))+"/"}else{if(b!=null&&b!=""){window.location=URLPrefix.search_keyword+"/s2/c"+b+"-"+h+"/k"+encodeURIComponent(encodeURIComponent(e))+"/"}else{window.location=URLPrefix.search_keyword+"/s2/c"+a+"-0/k"+encodeURIComponent(encodeURIComponent(e))+"/"
}}}function roll2(c){c=c||window.event;var d=c.keyCode;suggestLength2=jQuery("#searchSuggest2 li").length;var b="";var f="";if(jQuery("#searchSuggest2 ul li").size()>0){if(d=="38"){jQuery("#searchSuggest2 ul li").removeClass("select");if(curSuggestIndex2<=0){curSuggestIndex2=suggestLength2-1}else{if(curSuggestIndex2==1){curSuggestIndex2=0}else{curSuggestIndex2=curSuggestIndex2-1}}jQuery("#searchSuggest2 ul li").eq(curSuggestIndex2).addClass("select")}else{if(d=="40"){jQuery("#searchSuggest2 ul li").removeClass("select");if(curSuggestIndex2==0){curSuggestIndex2=1}else{if(curSuggestIndex2>=(suggestLength2-1)){curSuggestIndex2=0}else{curSuggestIndex2=curSuggestIndex2+1}}jQuery("#searchSuggest2 ul li").eq(curSuggestIndex2).addClass("select")}}if(d=="38"||d=="40"){var e=jQuery("#searchSuggest2  ul li a").eq(curSuggestIndex2).text();
if(jQuery("#searchSuggest2 ul li").eq(curSuggestIndex2).attr("id")!=null&&jQuery("#searchSuggest2 ul li").eq(curSuggestIndex2).attr("id")!=""){var a=jQuery("#searchSuggest2  ul li a").eq(0).text();jQuery("#keyword2").val(a);if(jQuery("#searchSuggest2 ul li ").eq(curSuggestIndex2).attr("id")=="recom3"){b=document.getElementById("recom1Id2").value;f=document.getElementById("recom1Name2").value}if(jQuery("#searchSuggest2 ul li").eq(curSuggestIndex2).attr("id")=="recom4"){b=document.getElementById("recom2Id2").value;f=document.getElementById("recom2Name2").value}}else{jQuery("#keyword2").val(e)}document.getElementById("recommendId2").value=b;document.getElementById("recommendName2").value=f}}if(d=="13"){if(b!=""){searchMe2(jQuery("#keyword2").val(),b,f)}else{searchMe2(jQuery("#keyword2").val(),"0","0")
}stopDefault(c);stopBubble(c)}}function emptySearchBar2(){if(jQuery("#keyword2").data("default")&&!jQuery("#keyword2").val().indexOf(jQuery("#keyword2").data("default"))){jQuery("#keyword2").val(jQuery("#keyword2").val().substring(jQuery("#keyword2").data("default").length));jQuery("#keyword2").trigger("blur")}}function stopDefault(a){if(a&&a.preventDefault){a.preventDefault()}else{if(window.event){window.event.returnValue=false}}return false}function stopBubble(a){if(a&&a.stopPropagation){a.stopPropagation()}else{window.event.cancelBubble=true}return false};/*
 * jQuery Tools v1.2.5 - The missing UI library for the Web
 * 
 * scrollable/scrollable.js
 * scrollable/scrollable.autoscroll.js
 * scrollable/scrollable.navigator.js
 * 
 * NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.
 * 
 * http://flowplayer.org/tools/
 * 
 */
(function(g){
	g.tools=g.tools||{version:"v1.2.5"},
	g.tools.scrollable={
		conf:{activeClass:"active",
		circular:!1,clonedClass:"cloned",
		disabledClass:"disabled",
		easing:"swing",initialIndex:0,
		item:null,items:".items",
		keyboard:!0,
		mousewheel:!1,
		next:".next",
		prev:".prev",
		speed:400,
		vertical:!1,
		touch:!0,
		wheelSpeed:0}
	};
	function f(k,e){
		var m=parseInt(k.css(e),10);
		if(m){return m}
		var l=k[0].currentStyle;return l&&l.width&&parseInt(l.width,10)}
	function j(a,k){
		var e=g(k);return e.length<2?e:a.parent().find(k)
	}
	var i;
	function h(x,w){
		var v=this,u=x.add(v),t=x.children(),s=0,r=w.vertical;
           i||(i=v),t.length>1&&(t=g(w.items,x)),
           g.extend(v,{getConf:function(){return w},
           getIndex:function(){return s},
           getSize:function(){return v.getItems().size()},
           getNaviButtons:function(){return d.add(c)},getRoot:function(){return x},
           getItemWrap:function(){return t},
           getItems:function(){return t.children(w.item).not("."+w.clonedClass)},
           move:function(k,e){return v.seekTo(s+k,e)},
           next:function(b){return v.move(1,b)},
           prev:function(b){return v.move(-1,b)},
           begin:function(b){return v.seekTo(0,b)},
           end:function(b){return v.seekTo(v.getSize()-1,b)},
           focus:function(){i=v;return v},
           addItem:function(e){
        	   e=g(e),
        	   w.circular?(t.children("."+w.clonedClass+":last").before(e),
        	   t.children("."+w.clonedClass+":first").replaceWith(e.clone().addClass(w.clonedClass))):t.append(e),u.trigger("onAddItem",[e]);return v},
          seekTo:function(o,B,z){
        	 o.jquery||(o*=1);
             if(w.circular&&o===0&&s==-1&&B!==0){return v}
             if(!w.circular&&o<0||o>v.getSize()||o<-1){return v}
             var y=o;o.jquery?o=v.getItems().index(o):y=v.getItems().eq(o);
          var e=g.Event("onBeforeSeek");
          if(!z){
        	  u.trigger(e,[o,B]);
             if(e.isDefaultPrevented()||!y.length){return v}
             }
          var A=r?{top:-y.position().top}:{left:-y.position().left};
          s=o,i=v,B===undefined&&(B=w.speed),
          t.animate(A,B,w.easing,z||function(){u.trigger("onSeek",[o])});
          return v}
        	   }
           ),
           g.each(
        		   ["onBeforeSeek","onSeek","onAddItem"],
                   function(e,k){g.isFunction(w[k])&&g(v).bind(k,w[k]),v[k]=function(l){l&&g(v).bind(k,l);return v}}
           );
           if(w.circular){
        	   var q=v.getItems().slice(-1).clone().prependTo(t),p=v.getItems().eq(1).clone().appendTo(t);
        	   q.add(p).addClass(w.clonedClass),
        	   v.onBeforeSeek(
        			   function(k,e,l){if(!k.isDefaultPrevented()){
        				   if(e==-1){v.seekTo(q,l,function(){v.end(0)});
        				   return k.preventDefault()}e==v.getSize()&&v.seekTo(p,l,function(){v.begin(0)})}}
        			   ),
        			  v.seekTo(0,0,function(){})
        	}
           var d=j(x,w.prev).click(
        		   function(){v.prev()}),
        	c=j(x,w.next).click(function(){v.next()});
           !w.circular&&v.getSize()>1&&(v.onBeforeSeek(
        		   function(k,e){
        			   setTimeout(
        					   function(){k.isDefaultPrevented()||(d.toggleClass(w.disabledClass,e<=0),
        							   c.toggleClass(w.disabledClass,e>=v.getSize()-1))},1)}
        		   ),
         w.initialIndex||d.addClass(w.disabledClass)),
         w.mousewheel&&g.fn.mousewheel&&x.mousewheel(
        		 function(k,e){if(w.mousewheel){v.move(e<0?1:-1,w.wheelSpeed||50);return !1}});
          if(w.touch){
        	  var a={};
        	  t[0].ontouchstart=function(k){var e=k.touches[0];a.x=e.clientX,a.y=e.clientY},
        	  t[0].ontouchmove=function(k){if(k.touches.length==1&&!t.is(":animated")){var e=k.touches[0],m=a.x-e.clientX,l=a.y-e.clientY;v[r&&l>0||!r&&m>0?"next":"prev"](),k.preventDefault()
           }}}
          w.keyboard&&g(document).bind("keydown.scrollable",function(e){if(w.keyboard&&!e.altKey&&!e.ctrlKey&&!g(e.target).is(":input")){if(w.keyboard!="static"&&i!=v){return}var k=e.keyCode;if(r&&(k==38||k==40)){v.move(k==38?-1:1);return e.preventDefault()}if(!r&&(k==37||k==39)){v.move(k==37?-1:1);return e.preventDefault()}}}),w.initialIndex&&v.seekTo(w.initialIndex,0,function(){})}g.fn.scrollable=function(a){var d=this.data("scrollable");if(d){return d}a=g.extend({},g.tools.scrollable.conf,a),this.each(function(){d=new h(g(this),a),g(this).data("scrollable",d)});return a.api?d:this}})(jQuery);(function(d){var c=d.tools.scrollable;c.autoscroll={conf:{autoplay:!0,interval:3000,autopause:!0}},d.fn.autoscroll=function(f){typeof f=="number"&&(f={interval:f});var b=d.extend({},c.autoscroll.conf,f),a;this.each(function(){var e=d(this).data("scrollable");
e&&(a=e);var h,g=!0;e.play=function(){h||(g=!1,h=setInterval(function(){e.next()},b.interval))},e.pause=function(){h=clearInterval(h)},e.stop=function(){e.pause(),g=!0},b.autopause&&e.getRoot().add(e.getNaviButtons()).hover(e.pause,e.play),b.autoplay&&e.play()});return b.api?a:this}})(jQuery);(function(e){var d=e.tools.scrollable;d.navigator={conf:{navi:".navi",naviItem:null,activeClass:"active",indexed:!1,idPrefix:null,history:!1}};function f(a,h){var g=e(h);return g.length<2?g:a.parent().find(h)}e.fn.navigator=function(b){typeof b=="string"&&(b={navi:b}),b=e.extend({},d.navigator.conf,b);var a;this.each(function(){var u=e(this).data("scrollable"),t=b.navi.jquery?b.navi:f(u.getRoot(),b.navi),s=u.getNaviButtons(),r=b.activeClass,q=b.history&&e.fn.history;u&&(a=u),u.getNaviButtons=function(){return s.add(t)
};
function p(g,i,h){u.seekTo(i);if(q){location.hash&&(location.hash=g.attr("href").replace("#",""))}else{return h.preventDefault()}}function o(){return t.find(b.naviItem||"> *")}function n(g){var h=e("<"+(b.naviItem||"a")+"/>").click(function(i){p(e(this),g,i)}).attr("href","#"+g);g===0&&h.addClass(r),b.indexed&&h.text(g+1),b.idPrefix&&h.attr("id",b.idPrefix+g);return h.appendTo(t)}o().length?o().each(function(g){e(this).click(function(h){p(e(this),g,h)})}):e.each(u.getItems(),function(g){n(g)}),u.onBeforeSeek(function(h,g){setTimeout(function(){if(!h.isDefaultPrevented()){var i=o().eq(g);!h.isDefaultPrevented()&&i.length&&o().removeClass(r).eq(g).addClass(r)}},1)});function c(h,g){var i=o().eq(g.replace("#",""));i.length||(i=o().filter("[href="+g+"]")),i.click()}u.onAddItem(function(g,h){h=n(u.getItems().index(h)),q&&h.history(c)
}),q&&o().history(c)});return b.api?a:this}})(jQuery);

function search_roll_revel(f,d){
	var k="."+f,h=k+" > .conBox",j=h+" > ul",i=$("li",h).length,l=$("li",h).eq(0).outerWidth(true);
	var c=screen.width>=1200?6:5,e=l*c,g="",b=i%c>0?parseInt(i/c)+1:i/c;
	if(i<=c){
		$(k+" > .prev").hide(0);$(k+" > .next").hide(0)
	}else{
		$(k+" > .prev").hide(0)
	}
	$(h+"> ul").width(l*i);
	for(var a=b-1;a>=1;a--){g+="<span>"+(b-a+1)+"</span>"}$(g).appendTo($(k+" > .turn"));
	$(k+" > .prev").click(function(){var m=$(k+" > .turn > span").index($(k+" > .turn > .cur"));
	if(m>0){$(k+" > .turn > span").eq(m-1).addClass("cur").siblings().removeClass("cur");$(j).animate({marginLeft:"+="+e},d);
	if(m-1==0){
		$(k+" > .prev").hide(0)
	}else{$(k+" > .prev").show(0)}
	$(k+" > .next").show(0)}});$(k+" > .next").click(
			function(){var m=$(k+" > .turn > span").index($(k+" > .turn > .cur"));
	if(m<b-1){$(k+" > .turn > span").eq(m+1).addClass("cur").siblings().removeClass("cur");
$(j).animate({marginLeft:"-="+e},d);

if(m==b-2){
	$(k+" > .next").hide(0)
}else{
	$(k+" > .next").show(0)
}
$(k+" > .prev").show(0)}});
$(k+" > .turn > span").click(
		function(){
			var n=$(k+" > .turn > span").index($(k+" > .turn > .cur")),
			m=$(k+" > .turn > span").index(this);
			$(this).addClass("cur").siblings().removeClass("cur");
			if(m<n){
				$(j).animate({marginLeft:"+="+e*(n-m)},d);
			if(m==0){
				$(k+" > .prev").hide(0)
			}else{
				$(k+" > .prev").show(0)
			}
			$(k+" > .next").show(0)
			}
			if(m>n){
				$(j).animate({marginLeft:"-="+e*(m-n)},d);
				if(m==b-1){
					$(k+" > .next").hide(0)
				}else{$(k+" > .next").show(0)}
				$(k+" > .prev").show(0)}})
			}

function mouseover_add_class(a,b){
	$(b,"."+a).hover(
			function(){
				$(this).addClass("cur")},function(){$(this).removeClass("cur")})
}

function nav_search_page(){
	$(".itemChoose").find(".current").parents("ul").addClass("show");

$(".itemChoose h3 .icon_btn").click(
		function(){
			var a=$(this).parent("h3").next("ul");
			if(a.length<=0){return false}
			var b=$(".itemChooseBox").children("ul:visible");
			if(a.is(":visible")){
				$(this).removeClass("open");a.slideUp();a.find("ul").slideUp();
				a.find(".icon_btn").removeClass("open")
			}else{
				$(this).addClass("open");$(this).parent("h3").siblings("h3").find(".icon_btn").removeClass("open");
				b.slideUp();b.find("ul").slideUp();b.find(".icon_btn").removeClass("open");a.find(".show").show();
				a.find(".show").siblings(".icon_btn").addClass("open");a.slideDown()}
			});
$(".itemChoose li.child .icon_btn").click(
		function(a){
			var b=$(this).siblings("ul");
			if(b.is(":hidden")){
				b.find(".show").show();b.find(".show").siblings(".icon_btn").addClass("open");
				b.slideDown();$(this).addClass("open");
				$(this).parent("li").siblings("li").find(".icon_btn").removeClass("open");

     $(this).parent("li").siblings("li").find("ul").slideUp();
     a.stopPropagation()
   }else{
	  $(this).removeClass("open");b.slideUp();b.find("ul").slideUp();
      b.find(".icon_btn").removeClass("open");a.stopPropagation()
	}
   });
$(".itemChoose ul a.current").parents("ul").show().siblings(".icon_btn").addClass("open")}

function removeMarginTopDist(){
	var a=$("#bodyRight").children("div:first");
	if(a.attr("id")!="hotcontainer"){
		a.removeClass("mt")
		}
	}
function removeFashionListClass(){
	if(typeof(cateType)!="undefined"&&typeof(isWidescreen)!="undefined"){
		if(cateType==1&&isWidescreen==0){
			$(".itemSearchResult").removeClass("fashionList")
		}
	}
}
function search_Sort_Margin(){
	   var a=$(".searchResultSort ul li").length;
       if(a==5){
    	     $(".searchResultSort ul").addClass("five")
    	   }else{
    	      if(a==4){
    		      $(".searchResultSort ul").addClass("four")
    		  }else{
    			  if(a==3){
    				  $(".searchResultSort ul").addClass("three")
                   }
    		  }
    	   }
       }
function attribute_Selected(){$(".attrMore .all a").each(function(){if($(this).attr("class")!="cur"){$(".searchResultOpMore .unfold").hide().next().show()}})}function survey(){$(window).scroll(function(){if($(window).scrollTop()>0){$(".fixedRight").addClass("show")}else{$(".fixedRight").removeClass("show")}})}function picChange(){$(".pic_list span").click(function(){var c=$(this).attr("id");var d=$(this).attr("oId");$(this).addClass("current").siblings("span").removeClass("current");var f=$(this).find("img").attr("src");var g=f.split("_")[0]+"_200x200.jpg";$(this).parents(".pic_list").prev(".product_pic").find("img").attr("src",g);getProductPrice(c,d);var j=$(this).attr("title");$("#pdlink1_"+d+" img").attr("title",j);var e=$(this).attr("subTitle");if(j.length>=25&&e.length>0){j=j.substring(0,25)
}$("#pdlink2_"+d).html(j+" <em>"+e+"</em>");var a=$(this).parents(".pic_list").prev(".product_pic").attr("href");var h=a.indexOf("product/")+8;var i=a.indexOf("_");var b=a.substring(h,i);a=a.replace(b,c);$(this).parents(".pic_list").prev(".product_pic").attr("href",a);$(this).parents(".pic_list").next(".title").attr("href",a);addTrackPositionToCookie("1","serial_product_preview_"+c)})};
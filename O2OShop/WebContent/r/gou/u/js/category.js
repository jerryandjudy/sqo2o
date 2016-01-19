$(document).ready(function(){nav_search_page();});

function nav_search_page(){
$(".itemChoose").find(".current").parents("ul").addClass("show");
$(".itemChoose h3 .icon_btn").click(function(){
			var a=$(this).parent("h3").next("ul");
            if(a.length<=0){return false;};
            var b=$(".itemChooseBox").children("ul:visible");
            if(a.is(":visible")){
        	   $(this).removeClass("open");a.slideUp();a.find("ul").slideUp();
        	   a.find(".icon_btn").removeClass("open");
            }else{
        	   $(this).addClass("open");$(this).parent("h3").siblings("h3").find(".icon_btn").removeClass("open");
               b.slideUp();b.find("ul").slideUp();b.find(".icon_btn").removeClass("open");a.find(".show").show();
               a.find(".show").siblings(".icon_btn").addClass("open");a.slideDown();
          }});
$(".itemChoose li.child .icon_btn").click(function(a){
	var b=$(this).siblings("ul");
    if(b.is(":hidden")){
	    b.find(".show").show();b.find(".show").siblings(".icon_btn").addClass("open");b.slideDown();
        $(this).addClass("open");$(this).parent("li").siblings("li").find(".icon_btn").removeClass("open");
        $(this).parent("li").siblings("li").find("ul").slideUp();a.stopPropagation();
    }else{
    	 $(this).removeClass("open");
          b.slideUp();b.find("ul").slideUp();b.find(".icon_btn").removeClass("open");a.stopPropagation()
    }});
$(".itemChoose ul a.current").parents("ul").show().siblings(".icon_btn").addClass("open");

}

function getJvForm(){
	return document.getElementById("jvForm");
}

function clickBrand(brandId,isShow){
	$("#brandId").val(brandId);
	$("#isShow").val(isShow);
	var jvForm = getJvForm();
	jvForm.onsubmit=null;
	jvForm.submit();
}

function clickctg(ctgId){
	$("#ctgId").val(ctgId);
	var jvForm = getJvForm();
	jvForm.onsubmit=null;
	jvForm.submit();
}

function clickPageSize(a){
	$("#pageSize").val(a);
	var jvForm = getJvForm();
	jvForm.onsubmit=null;
	jvForm.submit();
}

function clickOrderBy(a){
	$("#orderBy").val(a);
	var jvForm = getJvForm();
	jvForm.onsubmit=null;
	jvForm.submit();
}

function clickchang(a,b){
	$("#"+a).val(b);
	var jvForm = getJvForm();
	jvForm.submit();
}

function searchPriceRange(){
	var d=jQuery("#startPrice")[0];
	var a=jQuery("#startPrice")[0];
	var c=/^[0-9]*$/;
    if(!c.test(d.value)){
    	 alert("请输入大于0的整数");
    	 d.focus();return;
     }
   if(!c.test(a.value)){ 
	   alert("请输入大于0的整数");a.focus();return;
   }
   if((d.value=="")&&(a.value=="")){
	   alert("请输入价格区间");d.focus();return;
   }
   if((d.value!="")&&(a.value!="")){
	   if(Number(a.value)<Number(d.value)){
		   alert("输入价格区间 右值必须大于或等于左值");
		   a.focus();return;
		}
	}
	var jvForm = getJvForm();
	jvForm.onsubmit=null;
	jvForm.submit();
}

/*更多和收起js*/
function showMore(a,d){
	if(jQuery("#FacetBrandsMoreBtn")[0]&&jQuery("#FacetBrandsBackBtn")[0]){
		if(d&&d=="more"){
			jQuery("#FacetBrandsMoreBtn")[0].style.display="none";
            jQuery("#FacetBrandsBackBtn")[0].style.display="block";
        }else{
        	jQuery("#FacetBrandsMoreBtn")[0].style.display="block";
        	jQuery("#FacetBrandsBackBtn")[0].style.display="none";
        }
    }
	var c=9;
	if(typeof(cateType)!="undefined"&&cateType==1){c=9;}
	var b=$("#"+a).children();
	if(d&&d=="more"){
		b.each(function(e){$(this).show()})
	}else{
		b.each(function(e){if(e>c){$(this).hide()}})
	}
}
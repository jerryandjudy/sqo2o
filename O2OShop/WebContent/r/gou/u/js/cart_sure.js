jQuery(document).ready(function(){
	var price=0.0;
	var weight=0.0;
	var spid;
	$("#[id^='cart_price_']").each(function(){
		var ss=jQuery(this).html();
		var h=jQuery(this).attr("id");
		var index=h.split("_")[2];
		var count=$("#cart_count_"+index).html();
		price+=accMul(ss,count);
		$("#cart_price").html(price);
	});
	$("#[id^='cart_weight_']").each(function(){
		var ss=parseInt(jQuery(this).html());
		var h=jQuery(this).attr("id");
		var index=h.split("_")[2];
		var count=$("#cart_count_"+index).html();
		weight+=accMul(ss,count);
		$("#cart_weight").html(weight);
	});
	$("#[id^='shippingMethod_']").each(function(){
		if(jQuery(this).attr("checked")){
			spid=jQuery(this).val();
		}
	});
	ajaxtotalDeliveryFee(spid,weight);
	
	
});

function ajaxtotalDeliveryFee(d,w){
	$.post(URLPrefix.url+"/cart/ajaxtotalDeliveryFee.jspx", {
		'deliveryMethod':d,
		'weight':w
	}, function(data) {
		if(data.status==1){
			var freight=data.freight;
			var cart_price= $("#cart_price").html();
			var deduceAmount=$("#deduceAmount").html();  
			var popularityPrice=$("#popularityPrice").html(); 
			$("#totalDeliveryFee").html(data.freight);
			$("#amount_payable").html(parseFloat(cart_price)+freight-parseFloat(deduceAmount)-parseFloat(popularityPrice));
		}
	},'json');
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


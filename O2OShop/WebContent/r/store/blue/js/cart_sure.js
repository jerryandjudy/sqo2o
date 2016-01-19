jQuery(document).ready(function(){
	var price=0.0;
	var weight=0.0;
	var spid;
	$("#[id^='cart_price_']").each(function(){
		var ss=parseInt(jQuery(this).html());
		var h=jQuery(this).attr("id");
		var index=h.split("_")[2];
		var count=$("#cart_count_"+index).html();
		price+=ss*count;
		$("#cart_price").html(price);
	});
	$("#[id^='cart_weight_']").each(function(){
		var ss=parseInt(jQuery(this).html());
		var h=jQuery(this).attr("id");
		var index=h.split("_")[2];
		var count=$("#cart_count_"+index).html();
		weight+=ss*count;
		$("#cart_weight").html(weight);
	});
	$("#deliverMethodSelector").html()
	$("#[id='deliverMethodSelector']").each(function(){
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
			$("#totalDeliveryFee").html(data.freight);
		}
	},'json');
}


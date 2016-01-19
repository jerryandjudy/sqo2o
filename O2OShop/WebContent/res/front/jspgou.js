Shop={}
Shop.cartTotalItems = function() {
	var count = $.cookie('cart_total_items');
	return count || 0;
}
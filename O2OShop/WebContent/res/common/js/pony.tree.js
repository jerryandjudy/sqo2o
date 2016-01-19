Pn.ns('Pn.Tree');
Pn.Tree.switchDisplay=function(id,open) {	
	var isDisplay = $('#'+id+'-s').attr("isDisplay");
	if(open&&isDisplay=="true") {
		return;
	}
	if(isDisplay=="true") {		
		$('#'+id+'-co').hide();
		$('#'+id+'-fo').hide();
		$('#'+id+'-cc').show();
		$('#'+id+'-fc').show();
		$('#'+id+'-').hide();
		$('#'+id+'-s').attr("isDisplay","false");
	} else {
		$('#'+id+'-cc').hide();
		$('#'+id+'-fc').hide();
		$('#'+id+'-co').show();
		$('#'+id+'-fo').show();
		$('#'+id+'-').show();
		$('#'+id+'-s').attr("isDisplay","true");
	}
}
Pn.Tree.switchSelect=function(element,id,treeId) {
	if(element.checked) {
		$("input:checkbox",$('#'+id)).each(function(){this.checked=true;});
		var head = treeId;
		var tail = "-chk";
		//取去除头，取中间部分：t-0-6-1=-0-6-1
		var middle = id.substring(treeId.length);
		while(true) {
			middle = middle.substring(0,middle.lastIndexOf("-"));
			if(middle.length > 0) {
				$('#'+head+middle+tail).attr("checked",true);
			} else {
				break;
			}
		}
	} else {
		$("input:checkbox",$('#'+id)).each(function(){this.checked=false;});
	}
}
Pn.Tree.lineOver=function(element) {
	$(element).addClass("pn-tree-hover");
}
Pn.Tree.lineOut=function(element) {
	$(element).removeClass("pn-tree-hover");
}
Pn.Tree.lineSelected=function(element,treeId) {
	var selectedId = $('#'+treeId).attr("selectedId");
	if(selectedId){
		$('#'+selectedId).removeClass("pn-tree-selected");
	}
	$('#'+treeId).attr("selectedId",$(element).attr("id"));
	$(element).addClass("pn-tree-selected");	
}
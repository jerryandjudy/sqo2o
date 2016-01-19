$.metadata.setType("attr", "vld");

$.validator.AlertError={
	invalidHandler: function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			for(var name in validator.invalid) {
				alert(validator.invalid[name]);
				return;
			}
		}
	},
	showErrors: function(errors){}
};
$.validator.addMethod("username",
	function(value) {
		var p=/^[0-9a-zA-Z\u4e00-\u9fa5\.\-@_]+$/;
		return p.exec(value)?true:false;
	},
	"Please enter only letters,digits,chinese and '_','-','@'");
$.validator.addMethod("path",
	function(value) {
		var p=/^[0-9a-zA-Z]+$/;
		return p.exec(value)?true:false;
	},
	"Please enter only letters and digits");

$.fn.extend( {
	showBy : function(target) {
		var offset = target.offset();
		var top, left;
		var b = $(window).height() + $(document).scrollTop() - offset.top
				- target.outerHeight();
		var t = offset.top - $(document).scrollTop();
		var r = $(window).width() + $(document).scrollLeft() - offset.left;
		var l = offset.left + target.outerWidth() - $(document).scrollLeft();
		if (b - this.outerHeight() < 0 && t > b) {
			top = offset.top - this.outerHeight() - 1;
		} else {
			top = offset.top + target.outerHeight() + 1;
		}
		if (r - this.outerWidth() < 0 && l > r) {
			left = offset.left + target.outerWidth() - this.outerWidth();
		} else {
			left = offset.left;
		}
		this.css("top", top).css("left", left).show();
	}
});



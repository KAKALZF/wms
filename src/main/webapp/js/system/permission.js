//权限加载
$(function() {
	$(".btn_reload").click(function() {
		var url = $(this).data("url");
		$.get(url, function(data) {
			// 执行成功就给用户一个提示
			art.dialog({
				title : "温馨提示",
				content : data,
				ok : function() {
					window.location.reload();
				},
				okVal : "确认"
			})
		});
	});
});

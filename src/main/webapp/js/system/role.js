//角色和权限关联
$(function() {
	$("#selectAll").click(function() {
		$(".allPermissions  option").appendTo($(".selectedPermissions"));
	});
	$("#deselectAll").click(function() {
		$(".selectedPermissions  option").appendTo($(".allPermissions"));
	});
	$("#select").click(
			function() {
				$(".allPermissions  option:selected").appendTo(
						$(".selectedPermissions"));
			});
	$("#deselect").click(
			function() {
				$(".selectedPermissions  option:selected").appendTo(
						$(".allPermissions"));
			});
});
// 页面完毕后,将已经分配的权限从左下拉框中删除
$(function() {
	var ids = $.map($(".selectedPermissions option"), function(option) {
		return $(option).val();
	})
	// 循环遍历
	$.each($(".allPermissions  option"), function(index, option) {
		if ($.inArray($(option).val(), ids) >= 0) {

			$(option).remove();
		}
	});
	// 提交表单的时候将已选择的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedPermissions option").prop("selected", true);
	});
});
// 角色和菜单关联
$(function() {
	$("#mselectAll").click(function() {
		$(".allMenus  option").appendTo($(".selectedMenus"));
	});
	$("#mdeselectAll").click(function() {
		$(".selectedMenus   option").appendTo($(".allMenus"));
	});
	$("#mselect").click(function() {
		$(".allMenus   option:selected").appendTo($(".selectedMenus"));
	});
	$("#mdeselect").click(function() {
		$(".selectedMenus   option:selected").appendTo($(".allMenus"));
	});
});
// 页面完毕后,将已经分配的角色从左下拉框中删除
$(function() {
	var ids = $.map($(".selectedMenus option"), function(option) {
		return $(option).val();
	})
	// 循环遍历
	$.each($(".allMenus option"), function(index, option) {
		if ($.inArray($(option).val(), ids) >= 0) {

			$(option).remove();
		}
	});
	// 提交表单的时候将已选择的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedMenus option").prop("selected", true);
	});
});
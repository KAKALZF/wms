// 数据校验
// $(function(){
// $("#editForm").validate(function(){
// rules : {
// "emp.name":{
// require : true
// }
// },
// messages:{
// "emp.name":{ require : "名字不能为空"}
// }
// });
//	
// })

$(function() {
	$("#editForm").validate({
		// 规则
		rules : {
			'emp.name' : {
				required : true,
				minlength : 4
			},
			'emp.password' : {
				required : true,
				minlength : 4
			},
			'repassword' : {
				equalTo : "#password"
			},
			'emp.email' : 'email',
			'emp.age' : {
				range : [ 18, 60 ]
			},

		},
		messages : {
			'emp.name' : {
				required : '用户名必填',
				minlength : '长度至少4位'
			},
			'emp.password' : {
				required : '密码必填',
				minlength : '长度至少4位'
			},
			'repassword' : {
				equalTo : "必须和上面的密码一致"
			},
			'emp.email' : "必须为邮箱的格式",
			'emp.age' : {
				range : "必须是18到60岁之间"
			},

		}

	});

});
// 角色和权限关联
$(function() {
	$("#selectAll").click(function() {
		$(".allRoles  option").appendTo($(".selectedRoles"));
	});
	$("#deselectAll").click(function() {
		$(".selectedRoles  option").appendTo($(".allRoles"));
	});
	$("#select").click(
			function() {
				$(".allRoles  option:selected").appendTo(
						$(".selectedRoles"));
			});
	$("#deselect").click(
			function() {
				$(".selectedRoles  option:selected").appendTo(
						$(".allRoles"));
			});
});
// 页面完毕后,将已经分配的权限从左下拉框中删除
$(function() {
	var ids = $.map($(".selectedRoles option"), function(option) {
		return $(option).val();
	})
	// 循环遍历
	$.each($(".allRoles  option"), function(index, option) {
		if ($.inArray($(option).val(), ids) >= 0) {

			$(option).remove();
		}
	});
	// 提交表单的时候将已选择的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedRoles option").prop("selected", true);
	});
});
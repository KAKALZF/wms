jQuery.ajaxSettings.traditional = true;
$(function() {
	// 加载时,取消所有的被选中的checkbox
	$(".acb").prop("checked", false);

	// 新增绑定跳转
	$(".btn_input").click(function() {
		console.debug("123");
		window.location.href = $(this).data("url");
	});
	// 查询提交
	$(".btn_search").click(function() {
		$("input[name='qo.currentPage']").val(1);
		$("#searchForm").submit();
	});
	// 页面跳转
	$(".btn_page").click(
			function() {
				var pageNo = $(this).data("page")
						|| $("input[name='qo.currentPage']").val();
				$("input[name='qo.currentPage']").val(pageNo);
				$("#searchForm").submit();
			});
	// 页面容量改变
	$("select[name='qo.pageSize']").change(function() {
		$("input[name='qo.currentPage']").val(1);
		$("#searchForm").submit();
	});
	// 批量删除
	$(".btn_batchDelete").click(function() {
		var url = $(this).data("url");
		var checkBox = $(".acb:checked");
		var ids = checkBox.map(function(index, item) {
			return $(item).data("id");
		}).get();
		if (ids.length == 0) {
			art.dialog({
				title : "温馨提示",
				content : "请选择要删除的数据",
				ok : true,
				okVal : "确认"
			});
			return;
		}
		art.dialog({
			title : "温馨提示",
			content : "批量删除的数据不能恢复,确认删除吗?",
			ok : function() {
				$.get(url, {
					ids : ids
				}, function(data) {
					// 执行成功就给用户一个提示
					art.dialog({
						title : "温馨提示",
						content : data,
						ok : function() {
							window.location.reload();
						},
						okVal : "确认"
					});
				});
			},
			cancel : true,
			okVal : "是",
			cancelVal : "取消",
			icon : "warning"
		});

	});
	// 删除
	$(".btn_delete").click(function() {
		var id = $(this).data("id");
		var url = $(this).data("url");
		art.dialog({
			title : "温馨提示",
			content : "删除的数据不能恢复,确认删除吗?",
			ok : function() {
				$.get(url, function(data) {
					// 执行成功就给用户一个提示
					art.dialog({
						title : "温馨提示",
						content : data,
						//点击确认后执行的函数
						ok : function() {
							window.location.reload();
						},
						okVal : "确认"
					});
				});
			},
			cancel : true,
			okVal : "是",
			cancelVal : "取消",
			icon : "warning"
		});
	});
	// 审核
	$(".btn_audit").click(function() {
		var id = $(this).data("id");
		var url = $(this).data("url");
		art.dialog({
			title : "温馨提示",
			content : "确认审核吗?",
			ok : function() {
				$.get(url, function(data) {
					// 执行成功就给用户一个提示
					art.dialog({
						title : "温馨提示",
						content : data,
						ok : function() {
							window.location.reload();
						},
						okVal : "确认"
					});
				});
			},
			cancel : true,
			okVal : "是",
			cancelVal : "取消",
			icon : "warning"
		});
	});
	
	// 全选全不选
	$("#all").change(function() {
		var checked = $(this).prop("checked");
		console.debug(checked);
		$(".acb").prop("checked", checked);
	});

	// 鼠标悬停效果
	$(".data_tr").mouseover(function() {
		$(this).css("background-color", "#D8BFD8");
	});
	$(".data_tr").mouseout(function() {
		$(this).css("background-color", "#FFFFFF");
	});
});
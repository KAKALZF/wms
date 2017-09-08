<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<title>PSS-账户管理</title>
<style>
.alt td {
	background: black !important;
}
</style>

</head>
<body>
	<s:actionerror />
	<s:actionmessage />
	<s:form id="searchForm" namespace="/" action="employee" method="post">
		<s:debug />
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<s:textfield class="ui_input_txt02" name="qo.keywords" />
							所属部门
							<s:select list="depts" listValue="name" listKey="id"
								class="ui_select01" headerKey="-1" headerValue="请选择"
								name="qo.deptId" />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_search" />
							<input type="button" value="批量删除"
								data-url="<s:url namespace='/' action='employee_batchDelete'></s:url>"
								class="ui_input_btn01 btn_batchDelete" id="batchDelete" />

							<input type="button" value="新增"
								data-url="<s:url namespace='/' action='employee_input'></s:url>"
								class="ui_input_btn01 btn_input" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30">
								<input type="checkbox" id="all" />
							</th>
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.listData">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value='id' />
										" />
									</td>
									<td>
										<s:property value="id" />
									</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="email" />
									</td>
									<td>
										<s:property value="age" />
									</td>
									<td>
										<s:property value="dept.name" />
									</td>
									<td>
										<s:property value="roleNames" />
									</td>
									<td>
										<s:a namespace="/" action="employee_input">
											<s:param name="emp.id" value="id"></s:param>
										编辑</s:a>
										<s:url namespace='/' action='employee_delete' var="deleteURL">
											<s:param name="emp.id" value="id"></s:param>
										</s:url>
										<a href="javascript:;" data-id="<s:property value='id' />"
											data-url='<s:property value='deleteURL'/>' class="btn_delete">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 分页 -->
				<jsp:include page="/commons/commons_page.jsp"></jsp:include>
				<!--提示  -->
				<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
			</div>
		</div>
	</s:form>
</body>

</html>
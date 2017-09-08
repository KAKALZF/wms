<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
<script type="text/javascript" src="/js/system/role.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
	<s:form name="editForm" action="role_saveOrUpdate" method="post"
		id="editForm">
		<s:hidden name="role.id"></s:hidden>
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">角色编辑</span>
				<div id="page_close">
					<a>
						<img src="images/common/page_close.png" width="20" height="20"
							style="vertical-align: text-top;" />
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left"
					border="0">
					<tr>
						<td class="ui_text_rt" width="140">角色名称</td>
						<td class="ui_text_lt">
							<s:textfield name="role.name" class="ui_input_txt02" />
						</td>

					</tr>
					<tr>
						<td class="ui_text_rt" width="140">角色编号</td>
						<td class="ui_text_lt">
							<s:textfield name="role.sn" class="ui_input_txt02" />
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">权限</td>
						<td class="ui_text_lt">
							<table>
								<tr>
									<td>
										<s:select list="#permissions" listKey="id" listValue="name"
											name="" multiple="true"
											class="ui_multiselect01 allPermissions"></s:select>
									</td>
									<td align="center">
										<input type="button" id="select" value="-->"
											class="left2right" />
										<br />
										<input type="button" id="selectAll" value="==>"
											class="left2right" />
										<br />
										<input type="button" id="deselect" value="<--"
											class="left2right" />
										<br />
										<input type="button" id="deselectAll" value="<=="
											class="left2right" />
									</td>
									<td>
										<s:select list="role.permissions" listKey="id"
											listValue="name" name="role.permissions.id" multiple="true"
											class="ui_multiselect01 selectedPermissions"></s:select>
									</td>
								</tr>
								<!-- ===================================================== -->
								<tr>
									<td class="ui_text_rt" width="140">菜单</td>
									<td class="ui_text_lt">
										<table>
											<tr>
												<td>
													<s:select list="#menus" listKey="id" listValue="name"
														name="" multiple="true"
														class="ui_multiselect01 allMenus"></s:select>
												</td>
												<td align="center">
													<input type="button" id="mselect" value="-->"
														class="left2right" />
													<br />
													<input type="button" id="mselectAll" value="==>"
														class="left2right" />
													<br />
													<input type="button" id="mdeselect" value="<--"
														class="left2right" />
													<br />
													<input type="button" id="mdeselectAll" value="<=="
														class="left2right" />
												</td>
												<td>
													<s:select list="role.menus" listKey="id" listValue="name"
														name="role.menus.id" multiple="true"
														class="ui_multiselect01 selectedMenus"></s:select>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td class="ui_text_lt">
													&nbsp;
													<input type="submit" value="确定保存" class="ui_input_btn01" />
													&nbsp;
													<input id="cancelbutton" type="button" value="重置"
														class="ui_input_btn01" />
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
			</div>
		</div>
	</s:form>
	<s:if test="hasActionErrors()">
		<script type="text/javascript">
			alert("<s:property value='errorMessages[0]'/>");
		</script>
	</s:if>
</body>
</html>
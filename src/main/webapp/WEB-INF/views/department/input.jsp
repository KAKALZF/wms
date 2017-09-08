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
<script type="text/javascript" src="/js/system/commonAll.js"></script>
</head>
<body>
	<s:form name="editForm" action="department_saveOrUpdate" method="post"
		id="editForm">
		<s:hidden name="dept.id"></s:hidden>
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">部门编辑</span>
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
						<td class="ui_text_rt" width="140">部门名称</td>
						<td class="ui_text_lt">
							<s:textfield name="dept.name" class="ui_input_txt02" />
						</td>

					</tr>
					<tr>
						<td class="ui_text_rt" width="140">部门编号</td>
						<td class="ui_text_lt">
							<s:textfield name="dept.sn" class="ui_input_txt02" />
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
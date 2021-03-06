<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery-validation-1.8.1/jquery.validate.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
	<!-- =============================================== -->
	<%@include file="/commons/commons_msg.jsp"%>
	<s:form name="editForm" namespace="/" action="systemMenu_saveOrUpdate"
		id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">系统菜单编辑</span>
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
					<%-- 	<s:hidden name="systemMenu.id"></s:hidden>
					<s:hidden name="systemMenu.parent.id" value="%{#parentId}"></s:hidden> --%>
					<s:hidden name="systemMenu.id"></s:hidden>
					<%--将父类的id传递给systemMenu的parent对象的Id属性--%>
					<s:hidden name="systemMenu.parent.id" value="%{#parentId}"></s:hidden>
					<s:hidden name="qo.parentId"></s:hidden>
					<tr>
						<td class="ui_text_rt" width="140">父菜单</td>
						<td class="ui_text_lt">
							<s:textfield name="systemMenu.parent.name"
								cssClass="ui_input_txt02" value="%{#parentName}"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">编码</td>
						<td class="ui_text_lt">
							<s:textfield name="systemMenu.sn" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">URL</td>
						<td class="ui_text_lt">
							<s:textfield name="systemMenu.url" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">名称</td>
						<td class="ui_text_lt">
							<s:textfield name="systemMenu.name" cssClass="ui_input_txt02"></s:textfield>
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
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<title>PSS-系统菜单管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="systemMenu" namespace="/" method="post">
		<s:hidden name="qo.parentId"></s:hidden>
		<!--为什么???此值能显示  -->
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<s:url action="systemMenu_input" namespace="/" var="inputVar">
								<s:param name="qo.parentId" value="qo.parentId"></s:param>
							</s:url>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url='<s:property value='#inputVar'/>' />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					当前:
					<s:a action="systemMenu" namespace="/">
							根目录
					</s:a>
					<s:iterator value="#parents">
					>
						<s:a action="systemMenu" namespace="/">
							<s:property value="name" />
							<s:param name="qo.parentId" value="id"></s:param>
						</s:a>
					</s:iterator>
					<!-- <a href="/systemMenu.action">根目录</a>
					>
					<a href="/systemMenu.action?qo.parentId=1">系统模块</a> -->
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30">
								<input type="checkbox" id="all" />
							</th>
							<th>编号</th>
							<th>名称</th>
							<th>父菜单</th>
							<th>编码</th>
							<th>URL</th>

							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#result.listData">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value="id"/>" />
									</td>
									<td>
										<s:property value="id" />
									</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="parentName" />
									</td>
									<td>
										<s:property value="sn" />
									</td>

									<td>
										<s:property value="url" />
									</td>

									<td>
										<s:a namespace="" action="systemMenu_input">
											<s:param name="systemMenu.id" value="id"></s:param>
											<s:param name="qo.parentId" value="qo.parentId"></s:param>
                                    		编辑
                                    	</s:a>
										<s:url namespace="" action="systemMenu_delete" var="deleteUrl">
											<s:param name="systemMenu.id" value="id"></s:param>
										</s:url>

										<a href="javascript:;" class="btn_delete"
											data-url="<s:property value="#deleteUrl"/>"> 删除 </a>
										<s:url namespace="" action="systemMenu" var="deleteChildsUrl">
											<s:param name="qo.parentId" value="id"></s:param>
										</s:url>
										<a href="<s:property value="#deleteChildsUrl"/>"> 查询子菜单 </a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 分页条 -->
			<jsp:include page="/commons/commons_page.jsp"></jsp:include>
		</div>
	</s:form>
</body>
</html>

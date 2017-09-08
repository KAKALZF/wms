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
</head>
<body>
	<!-- =============================================== -->
	<s:debug />
	<%@include file="/commons/commons_msg.jsp"%>
	<s:form name="editForm" namespace="/" action="product_saveOrUpdate"
		id="editForm" enctype="multipart/form-data">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">商品编辑</span>
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
					<s:hidden name="product.id"></s:hidden>
					<s:hidden name="product.imagePath"></s:hidden>
					<tr>
						<td class="ui_text_rt" width="140">商品编码</td>
						<td class="ui_text_lt">
							<s:textfield name="product.sn" cssClass="ui_input_txt02"></s:textfield>
							<%-- <s:select name="product.sn" cssClass="ui_input_txt02"></s:select> --%>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">商品名称</td>
						<td class="ui_text_lt">
							<s:textfield name="product.name" cssClass="ui_input_txt02"></s:textfield>

						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">商品品牌</td>
						<td class="ui_text_lt">
							<s:select list="#brands" listKey="id" listValue="name"
								headerKey="-1" headerValue="---所有品牌---" name="product.brand.id"
								class="ui_select01"></s:select>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">成本价格</td>
						<td class="ui_text_lt">
							<s:textfield name="product.costPrice" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">商品图片</td>
						<td class="ui_text_lt">
							<%-- <s:textfield name="product.imagePath" cssClass="ui_input_txt02"></s:textfield> --%>
							<s:file name="pic" style="ui_file" />
							<img src='<s:property value ='product.imagePath'/>'
								Class="list_img" />
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">销售价格</td>
						<td class="ui_text_lt">
							<s:textfield name="product.salePrice" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">货品介绍</td>
						<td class="ui_text_lt">
							<s:textfield name="product.intro" cssClass="ui_area01"></s:textfield>
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
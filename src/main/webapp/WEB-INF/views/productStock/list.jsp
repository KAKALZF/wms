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
<title>PSS-库存管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="productStock" namespace="/"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						货品
						<s:textfield class="ui_input_txt02" name="qo.keyword"
							placeholder="货品编码/货品名称" />

						所属品牌
						<s:select list="#brands" listValue="name" listKey="id"
							class="ui_select01" headerKey="-1" headerValue="请选择"
							name="qo.brandId" />
						所属仓库
						<s:select list="#depots" listValue="name" listKey="id"
							class="ui_select01" headerKey="-1" headerValue="请选择"
							name="qo.depotId" />
						阈值
						<s:textfield class="ui_input_txt02" name="qo.limitNumber" />
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_search" />
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
							<th>编码</th>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>
						</tr>
						<tbody>
							<s:iterator value="#result.listData">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value="id"/>" />
									</td>
									<td>
										<s:property value="product.sn" />
									</td>
									<td>
										<s:property value="product.name" />
									</td>
									<td>
										<s:property value="depot.name" />
									</td>
									<td>
										<s:property value="product.brand.name" />
									</td>
									<td>
										<s:property value="price" />
									</td>

									<td>
										<s:property value="storeNumber" />
									</td>
									<td>
										<s:property value="amount" />
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

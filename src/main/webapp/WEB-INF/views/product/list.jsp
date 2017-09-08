<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/js/plugins/fancyBox/jquery.fancybox.css" rel="stylesheet"
	type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/fancyBox/jquery.fancybox.js"></script>

<script type="text/javascript"
	src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<title>PSS-商品管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
<script type="text/javascript">
	$(function() {
		$('.fc').fancybox();
	})
</script>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="product" namespace="/" method="post">
		<div id="box_center"></div>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						名称/编码
						<s:textfield class="ui_input_txt02" name="qo.keyword" />
						所属品牌
						<s:select list="brands" listValue="name" listKey="id"
							class="ui_select01" headerKey="-1" headerValue="请选择"
							name="qo.brandId"/>
						<div id="box_bottom">
						<input type="button" value="查询" class="ui_input_btn01 btn_search" />
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url="<s:url namespace="/" action="product_input"/>" />
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
							<th>图片</th>
							<th>商品名称</th>
							<th>商品编码</th>
							<th>品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
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
										<a class="fc" href="<s:property value ='imagePath'/>"
											title="<s:property value ='name'/>">
											<img src='<s:property value ='smallImagePath'/>'
												Class="list_img" />
										</a>
									</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="sn" />
									</td>
									<td>
										<s:property value="brand.name" />
									</td>
									<td>
										<s:property value="costPrice" />
									</td>
									<td>
										<s:property value="salePrice" />
									</td>
									<td>
										<s:a namespace="" action="product_input">
											<s:param name="product.id" value="id"></s:param>
                                    		编辑
                                    	</s:a>
										<s:url namespace="" action="product_delete" var="deleteUrl"
											escapeAmp="false">
											<s:param name="product.id" value="id"></s:param>
											<s:param name="product.imagePath" value="imagePath"></s:param>
										</s:url>
										<a href="javascript:;" class="btn_delete"
											data-url="<s:property value="#deleteUrl"/>"> 删除 </a>
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

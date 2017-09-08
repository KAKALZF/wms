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
<script type="text/javascript"
	src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<title>PSS-采购订单管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
<script type="text/javascript">
	//日期选择器
	$(function() {
		$(".inp_time").addClass("Wdate").click(function() {
			//alert(0);
			WdatePicker();
		});
	});
</script>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="chart_orderChart" namespace="/"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:date name="qo.beginDate" format="yyyy-MM-dd" var="bd" />
							<input name="qo.beginDate" value='<s:property value = '#bd'/>'
								class="ui_input_txt02 inp_time" />
							~
							<s:date name="qo.endDate" format="yyyy-MM-dd" var="ed" />
							<input name="qo.endDate" class="ui_input_txt02 inp_time"
								value='<s:property value = '#ed'/>' />
							货品
							<s:textfield class="ui_input_txt02" name="qo.keyword" />
							供应商
							<s:select list="#suppliers" listValue="name" listKey="id"
								class="ui_select01" headerKey="-1" headerValue="全部供应商"
								name="qo.supplierId" />
							品牌
							<s:select list="#brands" listValue="name" listKey="id"
								class="ui_select01" headerKey="-1" headerValue="全部品牌"
								name="qo.brandId" />
							分组
							<s:select list="#groupTypes" class="ui_select01"
								name="qo.groupByType" />
						</div>

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
							<th>分组类型</th>
							<th>采购总数量</th>
							<th>采购总金额</th>

						</tr>
						<tbody>
							<s:iterator value="#listData">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value="id"/>" />
									</td>
									<td>
										<s:property value="groupByType" />
									</td>
									<td>
										<s:property value="totalNumber" />
									</td>
									<td>
										<s:property value="totalAmout" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</s:form>
</body>
</html>

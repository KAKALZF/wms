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
<script type="text/javascript"
	src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript">
	$(function() {
		$("input").prop("readOnly", true);
	});
</script>

</head>
<body>
	<!-- =============================================== -->
	<%@include file="/commons/commons_msg.jsp"%>
	<s:debug />
	<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate"
		id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">采购订单查看</span>
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
					<s:hidden name="orderBill.id"></s:hidden>


					<tr>
						<td class="ui_text_rt" width="140">订单编码</td>
						<td class="ui_text_lt">
							<s:textfield name="stockIncomeBill.sn" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">供应商</td>
						<td class="ui_text_lt">
							<%-- <s:textfield name="orderBill.supplier" cssClass="ui_input_txt02"></s:textfield> --%>
							<s:select list="depots" listKey="id" listValue="name"
								name="stockIncomeBill.depot.id" class="ui_select01">
							</s:select>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt">
							<s:textfield name="stockIncomeBill.vdate"
								cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<!-- 更新明细 -->
					<tr>
						<td class="ui_text_rt" width="140">明细</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="button" value="添加明细"
								class="ui_input_btn01 appendRow" />
							<table class="edit_table" cellspacing="0" cellpadding="0"
								border="0" style="width: auto">
								<thead>
									<tr>
										<th width="10"></th>
										<th width="200">货品</th>
										<th width="120">品牌</th>
										<th width="80">价格</th>
										<th width="80">数量</th>
										<th width="80">金额小计</th>
										<th width="150">备注</th>
										<th width="60"></th>
									</tr>
								</thead>
								<tbody id="edit_table_body">
									<s:iterator value="stockIncomeBill.items">
										<tr>
											<td></td>
											<td>
												<s:textfield disabled="true" readonly="true"
													name="product.name" cssClass="ui_input_txt02" tag="name" />
												<img src="/images/common/search.png" class="searchproduct" />
												<s:hidden name="stockIncomeBill.items.product.id" tag="pid" />
											</td>
											<td>
												<span tag="brand">
													<s:property value="product.brand.name" />
												</span>
											</td>
											<td>
												<s:textfield tag="costPrice" name="costPrice"
													cssClass="ui_input_txt04" />
											</td>
											<td>
												<s:textfield tag="number" name="number"
													cssClass="ui_input_txt04" />
											</td>
											<td>
												<span tag="amount">
													<s:property value="amount" />
												</span>
											</td>
											<td>
												<s:textfield tag="remark" name="remark"
													cssClass="ui_input_txt02" />
											</td>
											<td>
												<a href="javascript:;" class="removeItem">删除明细</a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>

					<!-- 更新明细 -->


					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;
							<input type="button" value="回到列表"
								class="ui_input_btn01 btn_submit"
								onclick="window.history.back();" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>
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
<script type="text/javascript"
	src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		//日期选择器
		$("[name='orderBill.vdate']").addClass("Wdate").click(function() {
			//alert(0);
			WdatePicker();
		});

		$("#edit_table_body").on("click", ".searchproduct", function() {
			var currentTr = $(this).closest("tr");
			var url = "/product_selectProductList.action";
			art.dialog.open(url, {
				id : 'ajxxList',
				title : '货品选择列表',
				width : 900,
				height : 460,
				close : function() {
					var json = art.dialog.data('jsonStirng'); // 读取子窗口返回的数据  
					if (json) {
						currentTr.find("[tag=name]").val(json.name);
						currentTr.find("[tag=pid]").val(json.id);
						currentTr.find("[tag=costPrice]").val(json.costPrice);
						currentTr.find("[tag=brand]").html(json.brandName);
					}
				}
			});
		}).on(
				"change",
				"[tag=costPrice],[tag=number]",
				function() {
					var currentTr = $(this).closest("tr");
					var costPrice = currentTr.find("[tag=costPrice]").val();
					var number = currentTr.find("[tag=number]").val();
					console.debug("=============");
					if (costPrice && number) {
						currentTr.find("[tag=amount]").html(
								(costPrice * number).toFixed(2));
						console.debug("=============");
					}
				}).on("click", ".removeItem", function() {
			var currentTr = $(this).closest("tr");
			if ($("#edit_table_body tr").size() > 1) {
				currentTr.remove();
			} else {
				currentTr.find("[tag=pid]").val("");
				currentTr.find("[tag=name]").val("");
				currentTr.find("[tag=costPrice]").val("");
				currentTr.find("[tag=number]").val("");
				currentTr.find("[tag=remark]").val("");
				currentTr.find("[tag=amount]").html("");
				currentTr.find("[tag=brand]").html("");
				currentTr.appendTo($("#edit_table_body"));
			}
		});

		//添加一行明细
		$(".appendRow").click(function() {
			var newTr = $("#edit_table_body tr:first").clone(true);
			newTr.find("[tag=pid]").val("");
			newTr.find("[tag=name]").val("");
			newTr.find("[tag=costPrice]").val("");
			newTr.find("[tag=number]").val("");
			newTr.find("[tag=remark]").val("");
			newTr.find("[tag=amount]").html("");
			newTr.find("[tag=brand]").html("");
			newTr.appendTo($("#edit_table_body"));
		});
		//点击提交按钮
		$(".btn_submit").click(
				function() {
					$("#edit_table_body tr").each(
							function(index, item) {
								$(item).find("[tag=pid]").prop(
										"name",
										"orderBill.items[" + index
												+ "].product.id");
								$(item).find("[tag=costPrice]").prop(
										"name",
										"orderBill.items[" + index
												+ "].costPrice");
								$(item).find("[tag=number]")
										.prop(
												"name",
												"orderBill.items[" + index
														+ "].number");
								$(item).find("[tag=remark]")
										.prop(
												"name",
												"orderBill.items[" + index
														+ "].remark");
							});
					$("#editForm").submit();
				});
	});
</script>

</head>
<body>
	<!-- =============================================== -->
	<%@include file="/commons/commons_msg.jsp"%>
	<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate"
		id="editForm">
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">采购订单编辑</span>
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
							<s:textfield name="orderBill.sn" cssClass="ui_input_txt02"></s:textfield>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">供应商</td>
						<td class="ui_text_lt">
							<s:select list="#suppliers" listKey="id" listValue="name"
								name="orderBill.supplier.id" class="ui_select01">
							</s:select>
						</td>
					</tr>

					<tr>
						<td class="ui_text_rt" width="140">业务时间</td>
						<td class="ui_text_lt">
							<s:date name="orderBill.vdate" format="yyyy-MM-dd" var="vdate" />
							<%-- <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02"></s:textfield> --%>
							<input type="text" name="orderBill.vdate" class="ui_input_txt02"
								value="<s:property value='#vdate'/>" />
						</td>
					</tr>
					<!-- 更新明细 -->
					<s:if test="orderBill.id==null">
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
										<tr>
											<td></td>
											<td>
												<s:textfield disabled="true" readonly="true"
													cssClass="ui_input_txt02" tag="name" />
												<img src="/images/common/search.png" class="searchproduct" />
												<s:hidden name="orderBill.items.product.id" tag="pid" />
											</td>
											<td>
												<span tag="brand"></span>
											</td>
											<td>
												<s:textfield tag="costPrice"
													name="orderBill.items.costPrice" cssClass="ui_input_txt04" />
											</td>
											<td>
												<s:textfield tag="number" cssClass="ui_input_txt04" />
											</td>
											<td>
												<span tag="amount"></span>
											</td>
											<td>
												<s:textfield tag="remark" name="orderBill.items.remark"
													cssClass="ui_input_txt02" />
											</td>
											<td>
												<a href="javascript:;" class="removeItem">删除明细</a>
											</td>
										</tr>

									</tbody>
								</table>
							</td>
						</tr>
					</s:if>
					<!-- 新增明细 -->
					<!-- 更新明细 -->
					<s:else>
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
										<s:iterator value="orderBill.items">
											<tr>
												<td></td>
												<td>
													<s:textfield disabled="true" readonly="true"
														name="product.name" cssClass="ui_input_txt02" tag="name" />
													<img src="/images/common/search.png" class="searchproduct" />
													<s:hidden name="product.id" tag="pid" />
												</td>
												<td>
													<span tag="brand">
														<s:property value="product.brand.name" />
													</span>
												</td>
												<td>
													<s:textfield tag="costPrice" value="%{costPrice}"
														name="items.costPrice" cssClass="ui_input_txt04" />
												</td>
												<td>
													<s:textfield tag="number" name="items.number"
														value="%{number}" cssClass="ui_input_txt04" />
												</td>
												<td>
													<span tag="amount">
														<s:property value="amount" />
													</span>
												</td>
												<td>
													<s:textfield tag="remark" name="items.remark"
														value="%{remark}" cssClass="ui_input_txt02" />
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

					</s:else>
					<!-- 更新明细 -->


					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;
							<input type="button" value="确定保存"
								class="ui_input_btn01 btn_submit" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>
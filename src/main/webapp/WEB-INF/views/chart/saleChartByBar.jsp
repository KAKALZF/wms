<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/echarts/echarts-all.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="main" style="height: 400px; width: 800px"></div>
	<script type="text/javascript">
		$(function() {
			var myChart = echarts.init(document.getElementById('main'));

			option = {
				title : {
					text : '销售报表',
					subtext : ' <s:property value="#groupByType" escape="false" />'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '销售总额' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : <s:property value="#groupByTypeList" escape="false" />
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					name : '销售总额',
					type : 'bar',
					data : <s:property value="#saleAmountList" escape="false" />,
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				} ]
			};

			// 为echarts对象加载数据 
			myChart.setOption(option);

		})
	</script>
	<s:property value="#groupByTypeList" />

</body>
</html>
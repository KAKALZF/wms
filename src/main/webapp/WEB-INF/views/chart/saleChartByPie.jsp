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
<s:debug/>
	<div id="main" style="height: 400px ;width:800px"></div>
	<script type="text/javascript">
		$(function() {
			var myChart = echarts.init(document.getElementById('main'));

			option = {
				    title : {
				        text: '销售报表',
				        subtext: ' <s:property value="#groupByType" escape="false" />',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:<s:property value="#groupByTypeList" escape="false" />
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {
				                show: true, 
				                type: ['pie', 'funnel'],
				                option: {
				                    funnel: {
				                        x: '25%',
				                        width: '50%',
				                        funnelAlign: 'left',
				                        max: 1000000
				                    }
				                }
				            },
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    series : [
				        {
				            name:'访问来源',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:<s:property value="#dataList" escape="false" />
				        }
				    ]
				};

			// 为echarts对象加载数据 
			myChart.setOption(option);

		})
	</script>
	<s:property value="#groupByTypeList" />

</body>
</html>
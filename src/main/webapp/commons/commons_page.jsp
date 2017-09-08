<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> -->
<div class="ui_tb_h30">
	<div class="ui_flt" style="height: 30px; line-height: 30px;">
		共有
		<span class="ui_txt_bold04">
			<s:property value='#result.totalCount' />
		</span>
		条记录，当前第
		<span class="ui_txt_bold04">
			<s:property value='#result.currentPage' />
			/
			<s:property value='#result.totalPage' />
		</span>
		页
	</div>
	<div class="ui_frt">
		<input type="button" value="首页" class="ui_input_btn01 btn_page"
			data-page="1" />
		<input type="button" value="上一页" class="ui_input_btn01 btn_page"
			data-page="<s:property value='#result.prePage'/>" />
		<input type="button" value="下一页" class="ui_input_btn01 btn_page"
			data-page="<s:property value='#result.nextPage'/>" />
		<input type="button" value="尾页" class="ui_input_btn01 btn_page"
			data-page="<s:property value='#result.totalPage'/>" />
		<s:select list="{3,5,10}" name="qo.pageSize" class="ui_select02" />
		转到第
		<s:textfield id="currentPage" type="text" name="qo.currentPage"
			class="ui_input_txt01" />
		页
		<input type="button" class="ui_input_btn01 btn_page" value="跳转" />
	</div>
</div>
<!-- </body>
</html> -->
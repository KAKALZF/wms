<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:if test="hasActionErrors()">
		<script type="text/javascript">
			var msg = "<s:property value='errorMessages[0]'/>";
			art.dialog({
				title : "温馨提示",
				content : msg,
				ok : true,
				cancel : true
			});
		</script>
	</s:if>
	<s:if test="hasActionMessages()">
		<script type="text/javascript">
			var msg = "<s:property value='actionMessages[0]'/>";
			art.dialog({
				title : "温馨提示",
				content : msg,
				ok : true,
				cancel : true
			});
		</script>
	</s:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>uploadfile</title>
	</head>
	<body>

		<%
			String updatesuccess = "";
			updatesuccess = request.getParameter("updatesuccess");

			if (updatesuccess == null) {
				updatesuccess = "0";
			}

			if (updatesuccess.equals("1")) {
		%>

		<script type="text/javascript">
alert("导入成功！");
//window.parent.leftframe.location.reload(true);
</script>
		<%
			} else if (updatesuccess.equals("2")) {
		%>

		<script type="text/javascript">
alert("导入失败！");
</script>
		<%
			}
		%>

		<form action="uploaduser.action" method="post"
			enctype="multipart/form-data">
			请选择文件：
			<input type="file" name="upload">
			<br>
			<input type="submit" value="上传">
			<br>
			<a href="downloaduserimport.jsp">下载导入模板</a>
		</form>

	</body>
</html>
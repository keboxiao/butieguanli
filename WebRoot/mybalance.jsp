<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>
		<title>EASYUI DEMO</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<script type="text/javascript"
			src="js/jquery-easyui-1.5.1/jquery.min.js">
</script>
		<script type="text/javascript"
			src="js/jquery-easyui-1.5.1/jquery.easyui.min.js">
</script>
		<script type="text/javascript"
			src="js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js">
</script>
		<link rel="stylesheet"
			href="js/jquery-easyui-1.5.1/themes/default/easyui.css"
			type="text/css"></link>
		<link rel="stylesheet" href="js/jquery-easyui-1.5.1/themes/icon.css"
			type="text/css"></link>

		<style type="text/css">
body {
	margin: 1px
}

.leftdiv{
	margin-left: 60px;
}
</style>
		<script type="text/javascript">
$(function() {
	$('#infoform').form('load', 'getbalance.action?timestamp='+Math.random());
});
</script>
	</head>
	<body>
		<div id="p" class="easyui-panel" title="我的余额"
			style="width: 600px; height: 180px; padding: 10px;">
			<form id="infoform">
				<table>
					<tr>
						<td>
							<div style="margin-bottom: 20px">
								<input id="xingming" class="easyui-textbox" name="xingming"
									style="width: 100%" data-options="label:'姓名:',readonly:true">
							</div>
						</td>
						<td>
							<div style="margin-bottom: 20px" class="leftdiv">
								<input id="id" class="easyui-textbox" name="id"
									style="width: 100%" data-options="label:'员工编号:',readonly:true">
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div style="margin-bottom: 20px">
								<input id="shenfenzheng"  class="easyui-textbox" name="shenfenzheng"
									style="width: 100%" data-options="label:'身份证:',readonly:true">
							</div>
						</td>
						<td>
							<div style="margin-bottom: 20px" class="leftdiv">
								<input id="balance" class="easyui-textbox" name="balance" style="width: 100%"
									data-options="label:'余额（元）:',readonly:true">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</body>
	
</html>
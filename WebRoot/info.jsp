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

	</head>
	<body>
		<div id="tb" style="padding: 3px"
			data-options="title:'查询条件',border:false">
				工作证ID:
				<input id="emcardid" type="text" name="emcardid" />
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="showinfo();">查询</a>
		</div>
		<div id="p" class="easyui-panel" title="基本信息"
			style="width: 600px; height: 250px; padding: 10px;">
			<form id="infoform" name="infoform">
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
									data-options="label:'余额:',readonly:true">
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div style="margin-bottom: 20px">
								<input id="expenses" class="easyui-textbox" name="expenses"
									style="width: 100%" data-options="label:'消费额度:'">
							</div>
						</td>
						<td>
							<div style="margin-bottom: 20px" class="leftdiv">
								<input id="psw" class="easyui-textbox" name="psw" style="width: 100%"
									data-options="label:'消费密码:',type:'password'">
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div style="margin-bottom: 20px" class="leftdiv">
								<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" onclick="xiaofei();">提交消费</a>
							</div>
						</td>
						<td>
							<div style="margin-bottom: 20px" class="leftdiv">
								<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="clearFun()">清空</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</body>
		<script type="text/javascript">

var input = document.getElementById("emcardid");
input.addEventListener('keyup',function(e){
	var keynum;
	if(window.event){ // IE
  		keynum = e.keyCode
  	}else if(e.which){ // Netscape/Firefox/Opera
  		keynum = e.which
  	}
	if(keynum===13){
		showinfo();
	}
});

$("#emcardid").on("input",function(){
	if($("#emcardid").val().length==10){
		showinfo();
	}
})

function showinfo() {
	var id = document.getElementById("emcardid").value;
	$('#infoform').form('load', 'get.action?emcardid=' + id +'&timestamp='+Math.random());
}

function clearFun() {
	$('#id').textbox('setValue','');
	$('#xingming').textbox('setValue','');
	$('#shenfenzheng').textbox('setValue','');
	$('#balance').textbox('setValue','');
	$('#emcardid').val('');
	document.getElementById("emcardid").focus();
}

	function xiaofei() {
		var bianhao = document.getElementById("id").value;
		if (bianhao.length > 0) {
			$.messager.confirm('确认', '您是否要消费？', function(r) {
				$.ajax({
				type: 'POST',
					url : '${pageContext.request.contextPath}/xiaofei.action',
					data:$('#infoform').serialize(),
					dataType : 'json',
					success : function(r) {
						showinfo();
						$('#expenses').textbox('setValue','');
						$('#psw').textbox('setValue','');
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}
				});
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请查询出您要消费的记录！'
			});
		}
	}

</script>
</html>
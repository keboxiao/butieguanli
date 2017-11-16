<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>
		<title>EASYUI DEMO</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" href="js/jquery-easyui-1.5.1/themes/default/easyui.css" type="text/css"></link>
		<link rel="stylesheet" href="js/jquery-easyui-1.5.1/themes/icon.css" type="text/css"></link>

		<script type="text/javascript">
$(function() {
	$('#grid').datagrid( {
		url : 'xiaofeishow.action',
		pageSize : 15,
		pageList : [ 15, 20, 30 ],
		columns : [ [ {
			field : 'zhouqi',
			title : '周期',
			width : 100,
			align : 'center'
		}, {
			field : 'yuangongbianhao',
			title : '员工编号',
			width : 100,
			align : 'center'
		}, {
			field : 'xingming',
			title : '姓名',
			width : 100,
			align : 'center'
		}, {
			field : 'quyu',
			title : '区域',
			width : 120,
			align : 'center'
		}, {
			field : 'caozuo',
			title : '操作类型',
			width : 80,
			align : 'center'
		},{
			field : 'amount',
			title : '额度',
			width : 80,
			align : 'center'
		},{
			field : 'balance',
			title : '余额',
			width : 80,
			align : 'center'
		}, {
			field : 'shenqingren',
			title : '操作人',
			width : 100,
			align : 'center'
		}, {
			field : 'caozuoquyu',
			title : '操作单位',
			width : 160,
			align : 'center'
		}, {
			field : 'shenheriqi',
			title : '完成日期',
			width : 160,
			align : 'center'
		} ] ]
	});
});

serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

function searchFun() {
	$('#grid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
}
function clearFun() {
	$('#yuangongbianhao').textbox('setValue','');
	$('#begintime').datebox('setValue','');
	$('#endtime').datebox('setValue','');
	$('#grid').datagrid('load', {});
}

	function toDownLoadExcel(){  
		var begintime = $('#begintime').val();
		var endtime = $('#endtime').val(); 
		var yuangongbianhao = $('#yuangongbianhao').val();
		document.getElementById("downLoadExcel").href ="${pageContext.request.contextPath}/daochu.action?begintime=" + begintime +"&endtime=" + endtime + "&yuangongbianhao=" + yuangongbianhao;    
	}

</script>

<style type="text/css">
body {
	margin:1px
}
</style>

	</head>
	<body align="center">

		<div id="tb" style="padding: 3px"
			data-options="region:'north',title:'查询条件',border:false">
			<form id="admin_yhgl_searchForm">
				员工编码：
				<input id="yuangongbianhao" class="easyui-textbox" name="yuangongbianhao" />
				起始日期：<input id="begintime" class="easyui-datebox" name="begintime" />
				终止日期：<input id="endtime" class="easyui-datebox" name="endtime" />
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" onclick="clearFun();">清空</a>
				<a href="javascript:void(0);" id="downLoadExcel" class="easyui-linkbutton" iconCls="icon-add" onclick="toDownLoadExcel();">导出excel</a> 
			</form>
		</div>
		<div>
			<table id="grid" toolbar="#tb" title="发放记录查询"
				 iconCls="icon-search"
				data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true"></table>
		</div>

	</body>

</html>

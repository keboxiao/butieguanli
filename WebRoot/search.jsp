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
		url : 'xiaofeisearch.action',
		pageSize : 15,
		pageList : [ 15, 20, 30 ],
		columns : [ [ {
			field : 'yuangongbianhao',
			title : '员工编号',
			width : 100,
			align : 'center'
		},{
			field : 'xingming',
			title : '姓名',
			width : 100,
			align : 'center'
		}, {
			field : 'quyu',
			title : '区域',
			width : 160,
			align : 'center'
		},{
			field : 'shouji',
			title : '手机',
			width : 100,
			align : 'center'
		},{
			field : 'expenses',
			title : '消费金额',
			width : 70,
			align : 'center'
		},{
			field : 'xiaofeiyuan',
			title : '操作人员',
			width : 100,
			align : 'center'
		},{
			field : 'danweimingcheng',
			title : '消费单位',
			width : 160,
			align : 'center'
		},{
			field : 'lianxidianhua',
			title : '消费单位联系电话',
			width : 120,
			align : 'center'
		}, {
			field : 'xiaofeiriqi',
			title : '消费日期',
			width : 180,
			align : 'center'
		}] ]
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
	$('#xingming').textbox('setValue','');
	$('#grid').datagrid('load', {});
}

	function toDownLoadExcel(){  
		var begintime = $('#begintime').val();
		var endtime = $('#endtime').val(); 
		var xingming = $('#xingming').val();
		document.getElementById("downLoadExcel").href ="${pageContext.request.contextPath}/daochu.action?begintime=" + begintime +"&endtime=" + endtime + "&xingming=" + xingming;    
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
				姓名：
				<input id="xingming" class="easyui-textbox" name="xingming" />
				员工编码：
				<input id="yuangongbianhao" class="easyui-textbox" name="yuangongbianhao" />
				起始日期：<input id="begintime" class="easyui-datebox" name="begintime" />
				终止日期：<input id="endtime" class="easyui-datebox" name="endtime" />
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" onclick="clearFun();">清空</a>
			</form>
		</div>
		<div>
			<table id="grid" toolbar="#tb" title="消费记录查询"
				 iconCls="icon-search"
				data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true"></table>
		</div>

	</body>

</html>
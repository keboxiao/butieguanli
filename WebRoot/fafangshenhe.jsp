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

		<script type="text/javascript">
$(function() {
	$('#grid').datagrid( {
		url : 'shenheshow.action',
		pageSize : 300,
		pageList : [ 300, 400, 500 ],
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'id',
			title : 'id',
			checkbox : true
		} ] ],
		columns : [ [ {
			field : 'zhouqi',
			title : '发放周期',
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
			field : 'shenfenzheng',
			title : '身份证',
			width : 200,
			align : 'center'
		}, {
			field : 'caozuo',
			title : '操作类型',
			width : 80,
			align : 'center'
		},{
			field : 'amount',
			title : '发放额度',
			width : 80,
			align : 'center'
		}, {
			field : 'shenqingren',
			title : '申请人',
			width : 100,
			align : 'center'
		}, {
			field : 'shenqingriqi',
			title : '申请日期',
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

function shenheFun() {
	$('#grid').datagrid('load', serializeObject($('#shenheForm')));
}

	function rollback() {
		var rows = $('#grid').datagrid('getChecked');
		//var rows = $('#grid').datagrid('getSelected');
		//var rows = $('#grid').datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要退回当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						type: 'POST',
						url : '${pageContext.request.contextPath}/rollback.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#grid').datagrid('load');
							$('#grid').datagrid('uncheckAll');
							$('#grid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要退回的记录！'
			});
		}
	}
	
	function submit() {
		var rows = $('#grid').datagrid('getChecked');
		//var rows = $('#grid').datagrid('getSelected');
		//var rows = $('#grid').datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要审批当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						type: 'POST',
						url : '${pageContext.request.contextPath}/shenhe.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#grid').datagrid('load');
							$('#grid').datagrid('uncheckAll');
							$('#grid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}


</script>

		<style type="text/css">
body {
	margin: 1px
}
</style>

	</head>
	<body align="center">

		<div id="tb" style="padding: 3px"
			data-options="title:'查询条件',border:false">
			<form id="shenheForm">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" onclick="submit();">审批通过</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="rollback();">回退</a>
			</form>
		</div>
		<div>
			<table id="grid" style="height:700px" toolbar="#tb" title="请审批" iconCls="icon-search"
				data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true"></table>
		</div>

	</body>

</html>
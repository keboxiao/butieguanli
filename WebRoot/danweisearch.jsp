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
		url : 'firmsearch.action',
		pageSize : 15,
		pageList : [ 15, 20, 30 ],
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 50,
			align : 'center'
		}, {
			field : 'mingcheng',
			title : '名称',
			width : 120,
			align : 'center'
		}, {
			field : 'dianhua',
			title : '联系电话',
			width : 100,
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
	$('#mingcheng').textbox('setValue', '');
	$('#grid').datagrid('load', {});
}

function edituser() {
	var row = $("#grid").datagrid("getSelected");
	if (row) {
		$("#dlg").dialog("open").dialog('setTitle', 'Edit User');
		$("#fm").form("load", row);
		$('#firm').combobox( {
			url : 'firmshow.action',
			valueField : 'id',
			textField : 'mingcheng'
		});
		$('#firm').combobox('setValue', row.firmid);
	}
}

function addfirmdlg() {
	$("#dlgadd").dialog("open").dialog('setTitle', 'add');
}

function addfirm() {
	$.messager.confirm('确认', '您确定要保存？', function(r) {
		$.ajax( {
			type : 'POST',
			url : 'addfirm.action',
			data : $('#addfm').serialize(),
			dataType : 'json',
			success : function(r) {
				$.messager.show( {
					title : '提示',
					msg : r.msg
				});
				$("#dlgadd").dialog("close");
				$("#grid").datagrid("load");
			}
		});
	});
}

function saveuser() {
	$.messager.confirm('确认', '您确定要保存？', function(r) {
		$.ajax( {
			type : 'POST',
			url : 'edit.action',
			data : $('#fm').serialize(),
			dataType : 'json',
			success : function(r) {
				$.messager.show( {
					title : '提示',
					msg : r.msg
				});
				$("#dlg").dialog("close");
				$("#grid").datagrid("load");
			}
		});
	});
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
			data-options="region:'north',title:'查询条件',border:false">
			<form id="admin_yhgl_searchForm">
				名称：
				<input id="mingcheng" class="easyui-textbox" name="mingcheng" />
				<a href="javascript:void(0);" id="downLoadExcel"
					class="easyui-linkbutton" iconCls="icon-add" onclick="addfirmdlg()">添加</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" onclick="clearFun();">清空</a>
			</form>
		</div>
		<div>
			<table id="grid" toolbar="#tb" title="单位查询" iconCls="icon-search"
				data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true"></table>
		</div>
		
		<div id="dlgadd" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px;"
			closed="true" buttons="#adddlg-buttons">
			<div class="ftitle">
				信息编辑
			</div>
			<form id="addfm" method="post">
				<div class="fitem">
					<label>
						名称
					</label>
					<input name="mingcheng" class="easyui-validatebox" required="true" />
				</div>
				<div class="fitem">
					<label>
						联系电话
					</label>
					<input name="dianhua" class="easyui-validatebox" required="true" />
				</div>
			</form>
		</div>

		<div id="adddlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addfirm()" iconcls="icon-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:$('#dlgadd').dialog('close')" iconcls="icon-cancel">取消</a>
		</div>

	</body>

</html>
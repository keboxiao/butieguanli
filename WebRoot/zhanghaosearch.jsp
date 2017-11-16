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
		url : 'usershow.action',
		pageSize : 15,
		pageList : [ 15, 20, 30 ],
		columns : [ [ {
			field : 'id',
			title : '员工编号',
			width : 100,
			align : 'center'
		}, {
			field : 'xingming',
			title : '姓名',
			width : 100,
			align : 'center'
		}, {
			field : 'username',
			title : '帐号',
			width : 120,
			align : 'center'
		}, {
			field : 'emcardid',
			title : '工作证ID',
			width : 100,
			align : 'center'
		}, {
			field : 'firm',
			title : '单位',
			width : 120,
			align : 'center'
		}, {
			field : 'shenfenzheng',
			title : '身份证',
			width : 200,
			align : 'center'
		}, {
			field : 'balance',
			title : '余额',
			width : 80,
			align : 'center'
		}, {
			field : 'shouji',
			title : '联系电话',
			width : 100,
			align : 'center'
		} , {
			field : 'role',
			title : '权限码',
			width : 60,
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
	$('#yuangongbianhao').textbox('setValue', '');
	$('#xingming').textbox('setValue', '');
	$('#grid').datagrid('load', {});
}

function toDownLoadExcel() {
	var xingming = $('#xingming').val();
	var yuangongbianhao = $('#yuangongbianhao').val();
	document.getElementById("downLoadExcel").href = "${pageContext.request.contextPath}/daochuuser.action?yuangongbianhao="
			+ yuangongbianhao
			+ "&xingming="
			+ xingming;
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

      function saveuser() {
			$.messager.confirm('确认', '您确定要保存？', function(r) {
				$.ajax({
				type: 'POST',
					url : 'edit.action',
					data:$('#fm').serialize(),
					dataType : 'json',
					success : function(r) {
						$.messager.show({
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
				员工编码：
				<input id="yuangongbianhao" class="easyui-textbox"
					name="yuangongbianhao" />
				姓名：
				<input id="xingming" class="easyui-textbox" name="xingming" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconcls="icon-edit" onclick="edituser()" >修改</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" onclick="clearFun();">清空</a>
				<a href="javascript:void(0);" id="downLoadExcel"
					class="easyui-linkbutton" iconCls="icon-add"
					onclick="toDownLoadExcel();">导出excel</a>
			</form>
		</div>
		<div>
			<table id="grid" toolbar="#tb" title="发放记录查询" iconCls="icon-search"
				data-options="singleSelect:true,rownumbers:true,pagination:true,striped:true"></table>
		</div>

		<div id="dlg" class="easyui-dialog"
			style="width: 400px; height: 280px; padding: 10px 20px;"
			closed="true" buttons="#dlg-buttons">
			<div class="ftitle">
				信息编辑
			</div>
			<form id="fm" method="post">
				<div class="fitem">
					<label>
						编号
					</label>
					<input name="id" data-options="readonly:true"
						class="easyui-validatebox" required="true" />
				</div>
				<div class="fitem">
					<label>
						姓名
					</label>
					<input name="xingming" class="easyui-validatebox" required="true" />
				</div>
				<div class="fitem">
					<label>
						工作证ID
					</label>
					<input name="emcardid" class="easyui-validatebox"
						required="true" />
				</div>
				<div class="fitem">
					<label>
						单位
					</label>
					<input id="firm" name="firmid" style="width: 50%" />
				</div>
				<div class="fitem">
					<label>
						电话
					</label>
					<input name="shouji" class="easyui-vlidatebox" />
				</div>
				<div class="fitem">
					<label>
						密码
					</label>
					<input name="psw" class="easyui-validatebox"/>
				</div>
				<div class="fitem">
					<label>
						权限
					</label>
				<select name="role" id="role" class="inputtext">
					<option value="" selected>
						请选择
					</option>
					<option value="1">
						1
					</option>
					<option value="2">
						2
					</option>
					<option value="3">
						3
					</option>
					<option value="4">
						4
					</option>
				</select>
				</div>
			</form>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="saveuser()" iconcls="icon-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
		</div>

	</body>

</html>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*" import="com.po.*" import="com.page.*"%>

<html>
	<head>
		<title>无标题文档</title>
		<SCRIPT LANGUAGE="JavaScript">
<!--
function isNum(passedVal){
       if(passedVal==""){
	   return false;
	   }
	   for(i=0;i<passedVal.length;i++){
	   if(passedVal.charAt(i)<"0") {//如果输入值小于0，则错误
	       return false;
		   }
		 if(passedVal.charAt(i)>"9") {//如果输入值不在0-9范围内，则错误
		 return false;
		 }
	   return true;
	   }
	 }
	 function isValid() 
{
	 var oq=document.form1.psw.value;
	 var oq1=document.form1.wpswd1.value;
	 var oq2=document.form1.wpswd2.value;
	 if(oq!="")
	 {
		if(oq1!="")
		{
			if(oq2!="")
			{
				if(oq1!=oq2)
				{
					alert("两次输入的密码，不一样！");
					document.form1.wpswd1.focus();
					return false;
				}
			}
			else
			{
				alert("请输入确定密码");
				document.form1.wpswd2.focus();
				return false;
			}

		}
		else
		{
			alert("请输入新密码！");
			document.form1.wpswd1.focus();
			return false;
		}
		
	 }
	 else 
	 {
		 alert("请输入旧密码！请填写完整！"); 
		 document.form1.psw.focus();
		 return false;
	 }
}
// -->
</SCRIPT>
		<style type="text/css">
<!--
a:link {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}

a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.STYLE3 {
	font-size: 12px;
	color: #435255;
}

.STYLE4 {
	font-size: 12px
}

.STYLE5 {
	font-size: 12px;
	font-weight: bold;
}
-->
</style>
	</head>

	<body>
		<%
			String th_wnum = "", u_spri = "", updatesuccess = "";
			if (session.getAttribute("yuangong") == null)//判断是否有登录
			{
				response.sendRedirect("checklogin.jsp");
			} else {
				TuserDTO u = (TuserDTO) session.getAttribute("yuangong");//获取工作证号

				updatesuccess = request.getParameter("updatesuccess");

				if (updatesuccess == null) {
					updatesuccess = "0";
				}

				if (updatesuccess.equals("1")) {
		%>

		<script type="text/javascript">
alert("修改成功！");
//window.parent.leftframe.location.reload(true);
</script>

		<%
			} else if (updatesuccess.equals("2")) {
		%>

		<script type="text/javascript">
alert("修改失败！");
</script>
		<%
			}
		%>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td valign="top">
					<!--========================== 主区 =================================-->

					<form name="form1" method="post" action="updatepass.action"
						onsubmit="return isValid();">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">

							<tr>
								<td>
									<table width="100%" border="0" cellpadding="5" cellspacing="1"
										bgcolor="#a8c7ce">
										<tr>
											<td colspan=2 bgcolor="d3eaef" height="40">
												<div align="center">
													<span class="STYLE10">修改密码</span>
												</div>
											</td>
										</tr>
										<tr>
											<td width="15%" bgcolor="#FFFFFF" class="ccc">
												<div align="center" bgcolor="#FFFFFF">
													<span class="STYLE10">旧 密 码:</span>
												</div>
											</td>
											<td width="122" bgcolor="#FFFFFF">
												<div align="left">
													<input name="psw" type="password" size="12">
												</div>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFFFFF" width="15%" class="ccc">
												<div align="center">
													<span class="STYLE10">新 密 码:</span>
												</div>
											</td>
											<td bgcolor="#FFFFFF">
												<div align="left">
													<input name="wpswd1" type="password" size="12">
												</div>
											</td>
										</tr>
										<tr>
											<td width="15%" bgcolor="#FFFFFF" class="ccc">
												<div align="center">
													<span class="STYLE10">确定密码:</span>
												</div>
											</td>
											<td bgcolor="#FFFFFF">
												<div align="left">
													<input name="wpswd2" type="password" size="12">
												</div>
											</td>
										</tr>

										<tr>
											<td colspan="2" align="center" class="STYLE6"
												bgcolor="d3eaef">
												<input type="submit" value="保存">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" value="重置">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<%
							}
						%>
					</form>
					<!--  end 主区-->
				</td>

			</tr>
		</table>
	</body>
</html>

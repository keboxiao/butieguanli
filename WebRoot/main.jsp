<%@ page language="java" import="java.util.*" import="com.po.*"
	import="com.page.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>补贴发放系统</title>
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
		<script language="JavaScript">
$(document).ready(function () {
            $('.easyui-accordion li a').click(function () {
                var tabTitle = $(this).text();
                var url = $(this).attr("href");
                addTab(tabTitle, url);
                $('.easyui-accordion li div').removeClass("selected");
                $(this).parent().addClass("selected");
            }).hover(function () {
                $(this).parent().addClass("hover");
            }, function () {
                $(this).parent().removeClass("hover");
            });

            function addTab(subtitle, url) {
                if (!$('#tabs').tabs('exists', subtitle)) {
                    $('#tabs').tabs('add', {
                        title: subtitle,
                        content: createFrame(url),
                        closable: true,
                        width: $('#mainPanle').width(),
                        height: $('#mainPanle').height()
                    });
                } else {
                    $('#tabs').tabs('select', subtitle);
                }
                tabClose();
            }

            function createFrame(url) {
                var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                return s;
            }

            function tabClose() {
                /*双击关闭TAB选项卡*/
                $(".tabs-inner").dblclick(function () {
                    var subtitle = $(this).children("span").text();
                    $('#tabs').tabs('close', subtitle);
                })

                $(".tabs-inner").bind('contextmenu', function (e) {
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY,
                    });

                    var subtitle = $(this).children("span").text();
                    $('#mm').data("currtab", subtitle);

                    return false;
                });
            }
            //绑定右键菜单事件
            function tabCloseEven() {
                //关闭当前
                $('#mm-tabclose').click(function () {
                    var currtab_title = $('#mm').data("currtab");
                    $('#tabs').tabs('close', currtab_title);
                })
                //全部关闭
                $('#mm-tabcloseall').click(function () {
                    $('.tabs-inner span').each(function (i, n) {
                        var t = $(n).text();
                        $('#tabs').tabs('close', t);
                    });
                });
                //关闭除当前之外的TAB
                $('#mm-tabcloseother').click(function () {
                    var currtab_title = $('#mm').data("currtab");
                    $('.tabs-inner span').each(function (i, n) {
                        var t = $(n).text();
                        if (t != currtab_title)
                            $('#tabs').tabs('close', t);
                    });
                });
                //关闭当前右侧的TAB
                $('#mm-tabcloseright').click(function () {
                    var nextall = $('.tabs-selected').nextAll();
                    if (nextall.length == 0) {
                        //msgShow('系统提示','后边没有啦~~','error');
                        alert('后边没有啦~~');
                        return false;
                    }
                    nextall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    });
                    return false;
                });
                //关闭当前左侧的TAB
                $('#mm-tabcloseleft').click(function () {
                    var prevall = $('.tabs-selected').prevAll();
                    if (prevall.length == 0) {
                        alert('到头了，前边没有啦~~');
                        return false;
                    }
                    prevall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    });
                    return false;
                });

                //退出
                $("#mm-exit").click(function () {
                    $('#mm').menu('hide');
                })
            }


        });
        
            function createFrame(url) {
                var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                return s;
            }
            
            function addTab(subtitle, url) {
                if (!$('#tabs').tabs('exists', subtitle)) {
                    $('#tabs').tabs('add', {
                        title: subtitle,
                        content: createFrame(url),
                        closable: true,
                        width: $('#mainPanle').width()-10 ,
                        height: $('#mainPanle').height()-26
                    });
                } else {
                    $('#tabs').tabs('select', subtitle);
                }
                tabClose();
            }
            
            function tabClose() {
                /*双击关闭TAB选项卡*/
                $(".tabs-inner").dblclick(function () {
                    var subtitle = $(this).children("span").text();
                    $('#tabs').tabs('close', subtitle);
                })

                $(".tabs-inner").bind('contextmenu', function (e) {
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY,
                    });

                    var subtitle = $(this).children("span").text();
                    $('#mm').data("currtab", subtitle);

                    return false;
                });
            }
            
            //绑定右键菜单事件
            function tabCloseEven() {
                //关闭当前
                $('#mm-tabclose').click(function () {
                    var currtab_title = $('#mm').data("currtab");
                    $('#tabs').tabs('close', currtab_title);
                })
                //全部关闭
                $('#mm-tabcloseall').click(function () {
                    $('.tabs-inner span').each(function (i, n) {
                        var t = $(n).text();
                        $('#tabs').tabs('close', t);
                    });
                });
                //关闭除当前之外的TAB
                $('#mm-tabcloseother').click(function () {
                    var currtab_title = $('#mm').data("currtab");
                    $('.tabs-inner span').each(function (i, n) {
                        var t = $(n).text();
                        if (t != currtab_title)
                            $('#tabs').tabs('close', t);
                    });
                });
                //关闭当前右侧的TAB
                $('#mm-tabcloseright').click(function () {
                    var nextall = $('.tabs-selected').nextAll();
                    if (nextall.length == 0) {
                        //msgShow('系统提示','后边没有啦~~','error');
                        alert('后边没有啦~~');
                        return false;
                    }
                    nextall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    });
                    return false;
                });
                //关闭当前左侧的TAB
                $('#mm-tabcloseleft').click(function () {
                    var prevall = $('.tabs-selected').prevAll();
                    if (prevall.length == 0) {
                        alert('到头了，前边没有啦~~');
                        return false;
                    }
                    prevall.each(function (i, n) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    });
                    return false;
                });

                //退出
                $("#mm-exit").click(function () {
                    $('#mm').menu('hide');
                })
            }
    </script>
		<style>
.footer {
	width: 100%;
	text-align: center;
	line-height: 35px;
}

.top-bg {
	background-color: #d8e4fe;
	height: 80px;
}
</style>
	</head>
	<body class="easyui-layout">
		<div region="north" border="true"
			style="overflow: hidden; height: 80px;">
			<div class="top-bg">
				<table width=100% height="80">
					<tr>
						<td width="160px">
							<img src="images/telecom1.png" style="height: 80px" />
						</td>
						<td align="left">
							<h1>
								<p align="left"
									style="filter: shadow (   color =     #111000, direction =     135, strength :     3 ); font-size: 20px; color: #0000C6; font-weight: bold; padding-right: 100px; font-style: italic">
									&nbsp; &nbsp; 补贴发放系统
								</p>
							</h1>
						</td>
						<td width="281" valign="bottom">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="27">
										<div align="right">
											<a href="logout.action"><img src="images/quit.gif"
													width="69" /> </a>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div region="south" border="true" split="true"
			style="overflow: hidden; height: 40px;">
			<div class="footer">
				技术支持：人力资源部柯博晓 联系电话：06682993022
			</div>
		</div>
		<div region="west" split="true" title="导航菜单" style="width: 200px;">
			<div id="aa" class="easyui-accordion"
				style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
				<div title="发放管理" iconcls="icon-save" selected="true"
					style="overflow: auto; padding: 10px;">
					<%
						int level;

						if (session.getAttribute("yuangong") == null) {
							response.sendRedirect("checklogin.jsp");
						} else {
							TuserDTO u = (TuserDTO) session.getAttribute("yuangong");
					%>
					<ul class="easyui-tree">
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('员工对账单','fafangsearch.jsp')">员工对账单</a>
							</span>
						</li>
						<%
							if (u.getRole() < 3 && u.getFirmid() == 1) {
						%>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('发放导入','upload.jsp')">发放导入</a> </span>
						</li>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('发放申请','fafangshenqing.jsp')">发放申请</a>
							</span>
						</li>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('发放审批','fafangshenhe.jsp')">发放审批</a>
							</span>
						</li>
						<%
							}
						%>
					</ul>

				</div>

				<div title="消费管理" iconcls="icon-save"
					style="overflow: auto; padding: 10px;">
					<ul class="easyui-tree">
						<%
							if (u.getRole() == 4) {
						%>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('消费操作','info.jsp')">消费操作</a> </span>

						</li>
						<%
							} else {
						%>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('我的余额','mybalance.jsp')">我的余额</a> </span>
						</li>
						<%
							}
						%>
					</ul>
				</div>
				<div title="帐号管理" iconcls="icon-man"
					style="overflow: auto; padding: 10px;">
					<ul class="easyui-tree">
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('修改密码','updatepsw.jsp')">修改密码</a> </span>
						</li>
						<%
							if (u.getRole() < 3 && u.getFirmid() == 1) {
						%>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('帐号导入','userupload.jsp')">帐号导入</a> </span>
						</li>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('用户查询','zhanghaosearch.jsp')">用户查询</a>
							</span>
						</li>
						<li>
							<span><a href="javascript:void(0)"
								onclick="javascript:addTab('单位查询','danweisearch.jsp')">单位查询</a>
							</span>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>
		<div id="mainPanle" region="center" style="overflow: hidden;">
			<div id="tabs" class="easyui-tabs" fit="true" border="false">
				<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
					<h1>
						<%=u.getXingming()%>，欢迎您登录系统。
						<br>
							<%
								}
							%>
						
					</h1>
				</div>
			</div>
		</div>
	</body>
</html>
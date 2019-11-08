<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>
<!-- 引入shiro的标签库 -->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<style type="text/css">
	.sidebar:before{
		border: 0px;
		background-color: #1e282c;
	}
</style>
<script type="text/javascript">
	$(function () {
		init_menu();
	});
	function init_menu() {
		$.ajax({
			url: '${pageContext.request.contextPath}/admin/findAll',
			type: 'get',
			contentType: 'application/json;charset=utf-8',
			success: function (data) {
				/*console.log(data);*/
				var str = '<li class="header">菜单</li><li id="admin-index"><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>';
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						if (data[i].children != null&&data[i].children.length>0) {
							str += '<li class="treeview"><a id="aa" href="#"  > <i class="fa fa-cogs"></i>' +
									'<span>' + data[i].desc + '</span> <span class="pull-right-container"> <i' +
									'class="fa fa-angle-left pull-right"></i>' +
									'</span>' +
									'</a><ul class="treeview-menu">';
							for (var j = 0; j < data[i].children.length; j++) {

								str += '<li id="system-setting"><a id="aa" href="${pageContext.request.contextPath}'+ data[i].children[j].url+'" > <i class="fa fa-circle-o"></i>' + data[i].children[j].desc + '</a></li>';

							}
							str += '</ul>';
						}else{
							str += '';
						}
						str += '</li>';
					}
				}

				/*console.log(str);*/
				$("#sidebar").html(str);
			}
		});

	}
</script>

<aside class="main-sidebar " style="background-color: #1e282c">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar" style="border: 0px;width: 100%;background-color: #1e282c" >
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<%--<security:authentication property="principal.username"></security:authentication>--%>
						<shiro:authenticated>
							${subject.principal}
						</shiro:authenticated>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" id="sidebar">
			<%--<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/index"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>


				<ul class="treeview-menu">
					<li id="system-setting">
						<a
						href="${pageContext.request.contextPath}/admin/listUser"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a>
					</li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/admin/listRole"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/admin/listPermission">
							<i class="fa fa-circle-o"></i> 资源权限管理
					</a></li>

				</ul>

			</li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>考勤数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/attendance/findByCreateDate">
							<i class="fa fa-circle-o"></i> 考勤信息
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/student/findAll"> <i
							class="fa fa-circle-o"></i>学生详细信息
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/note/findAll"> <i
							class="fa fa-circle-o"></i>短信记录
					</a></li>
					<li id="system-setting">
						<a
								href="${pageContext.request.contextPath}/attendance/upload1"> <i
								class="fa fa-circle-o"></i> 考勤Excel表上传
						</a>
					</li>

				</ul></li>
--%>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
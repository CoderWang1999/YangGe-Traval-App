<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${username}</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/WEB-INF/pages/home/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li id="system-setting1"><a
						href="${pageContext.request.contextPath}/system/user/findAll"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>
					<li id="system-setting2"><a
						href="${pageContext.request.contextPath}/servlet/role/findAll"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li id="system-setting3"><a
						href="${pageContext.request.contextPath}/permission/findAll">
							<i class="fa fa-circle-o"></i> 资源权限管理
					</a></li>
					<li id="system-setting4"><a
						href="${pageContext.request.contextPath}/system/syslog/findAll?pageNum=1&pageSize=5"> <i
							class="fa fa-circle-o"></i> 访问日志
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting5"><a
						href="${pageContext.request.contextPath}/product/findAll">
							<i class="fa fa-circle-o"></i> 产品管理
					</a></li>
					<li id="system-setting6"><a
						href="${pageContext.request.contextPath}/basedata/ordres/findAll"> <i
							class="fa fa-circle-o"></i> 订单管理
					</a></li>

				</ul></li>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
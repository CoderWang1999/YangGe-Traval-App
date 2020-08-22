<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>数据 - AdminLTE2定制版</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<jsp:include page="../../home/header.jsp"></jsp:include>
		<!-- 页面头部 /-->

		<!-- 导航侧栏 -->
		<jsp:include page="../../home/aside.jsp"></jsp:include>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				日志管理 <small>全部日志</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/sysLog/findAll.do">日志管理</a></li>

				<li class="active">全部日志</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content"> <!-- .box-body -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<input type="hidden" name="find" value="${find}"/>
					<h3 class="box-title">列表</h3>
				</div>

				<div class="box-body">

					<!-- 数据表格 -->
					<div class="table-box">

						<!--工具栏-->
						<div class="pull-left">
							<div class="form-group form-inline">
								<div class="btn-group">
									<button type="button" class="btn btn-default" title="刷新"
										id="fresh">
										<i class="fa fa-refresh"></i> 刷新
									</button>
									<button type="button" class="btn btn-default" title="删除"
											onclick='deleteById()'>
										<i class="fa fa-trash"></i> 删除
									</button>
									<button type="button" class="btn btn-default" title="导出" onclick=location.href="${pageContext.request.contextPath}/system/syslog/downloadReport">
										<i class="fa fa-arrow-up"></i> 导出
									</button>
								</div>
							</div>
						</div>
						<div class="box-tools pull-right">
							<div class="has-feedback">
								<input id="findText" type="text" class="form-control input-sm" name="findText"
									   placeholder="搜索" value=${find}> <span
									class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</div>
						<!--工具栏/-->

						<!--数据列表-->
						<table id="dataList"
							class="table table-bordered table-striped table-hover dataTable">
							<thead>
								<tr>
									<th class="" style="padding-right: 0px"><input id="selall"
										type="checkbox" class="icheckbox_square-blue"></th>
									<th class="sorting_asc">ID</th>
									<th class="sorting">访问时间</th>
									<th class="sorting">访问用户</th>
									<th class="sorting">访问IP</th>
									<th class="sorting">资源URL</th>
									<th class="sorting">执行时间</th>
									<th class="sorting">访问方法</th>
								</tr>
							</thead>
							<c:forEach items="${page.list}" var="list" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${list.id}"/></td>
									<td>${list.id}</td>
									<td>${list.visitTimeStr }</td>
									<td>${list.username }</td>
									<td>${list.ip }</td>
									<td>${list.url}</td>
									<td>${list.executionTime}毫秒</td>
									<td>${list.method}</td>
								</tr>
							</c:forEach>
							</tbody>

						</table>
						<!--数据列表/-->

					</div>
					<!-- 数据表格 /-->

				</div>
				<!-- /.box-body -->

				<!-- .box-footer-->
				<!-- .box-footer-->
				<form action="${pageContext.request.contextPath}/system/syslog/findAll" method="post" id = "sizeChangeFrom">
					<input type="hidden" name="pageNum" value="1"/>
					<input type="hidden" name="find" value="${find}"/>
					<div class="box-footer">
						<div class="pull-left">
							<div class="form-group form-inline">
								总共 ${page.pages}页，共 ${page.total} 条数据。 每页
								<select id = "pageSizeSelect" name="pageSize" class="form-control">
									<option value="5" ${page.pageSize == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${page.pageSize == 10 ? 'selected' : ''}>10</option>
									<option value="20" ${page.pageSize == 20 ? 'selected' : ''}>20</option>
									<option value="30" ${page.pageSize == 30 ? 'selected' : ''}>30</option>
									<option value="40" ${page.pageSize == 40 ? 'selected' : ''}>40</option>
								</select> 条
							</div>
						</div>
					</div>
				</form>

				<div>
					<div class="box-tools pull-right">
						<ul class="pagination">
							<li><a href="${pageContext.request.contextPath}/system/syslog/findAll?find=${find}&pageNum=1&pageSize=${page.pageSize}" aria-label="Previous">首页</a></li>
							<c:if test="${page.hasPreviousPage}">
								<li><a href="${pageContext.request.contextPath}/system/syslog/findAll?find=${find}&pageNum=${page.pageNum-1}&pageSize=${page.pageSize}">上一页</a></li>
							</c:if>

							<c:forEach begin="1" end="${page.pages}" var="i">
								<li><a href="${pageContext.request.contextPath}/system/syslog/findAll?find=${find}&pageNum=${i}&pageSize=${page.pageSize}">${i}</a></li>
							</c:forEach>

							<c:if test="${page.hasNextPage}">
								<li><a  href="${pageContext.request.contextPath}/system/syslog/findAll?find=${find}&pageNum=${page.pageNum + 1}&pageSize=${page.pageSize}">下一页</a></li>
							</c:if>

							<li><a href="${pageContext.request.contextPath}/system/syslog/findAll?find=${find}&pageNum=${page.pages}&pageSize=${page.pageSize}" aria-label="Next">尾页</a></li>

						</ul>
					</div>

				</div>
				<!-- /.box-footer-->
				

			</div>

			</section>
			<!-- 正文区域 /-->

		</div>
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0.8
		</div>
		<strong>Copyright &copy; 2014-2017 <a
			href="http://www.itcast.cn">研究院研发部</a>.
		</strong> All rights reserved. </footer>
		<!-- 底部导航 /-->

	</div>

	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

	<script>
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}
		function deleteById() {
			let id = getCheckId();
			// alert(id);
			if (id) {
				if (confirm("你确认要删除这些记录吗？")) {
					location.href = "${pageContext.request.contextPath}/system/syslog/deleteByIds?pageNum=${page.pageNum}&pageSize=${page.pageSize}&ids=" + id;
				}
			} else {
				alert("请选择要删除的数据")
			}
		}

		function getCheckId() {
			let size = $("input:checkbox:checked").length;
			if (size == 0) {
				return;
			} else {
				let input = $('input[type=checkbox]:checked')
				//定义数组 储存inputId的值
				let arr = new Array();
				for (let i = 0; i < size; i++) {
					// alert(inputId[i].value())
					//使用push防止空值
					arr.push(input[i].value);
				}
				return arr;
			}
		}



		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("order-manage");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});

		let freshBtn = document.getElementById("fresh");
		freshBtn.onclick = function(){

			location.href = "${pageContext.request.contextPath}/system/syslog/findAll?pageNum=${page.pageNum}&pageSize=${page.pageSize}";


		}

		let findText = document.getElementById("findText");

		findText.onblur=function () {
			let find = document.getElementById("findText").value;
			location.href="${pageContext.request.contextPath}/system/syslog/findAll?find="+find+"&pageNum=1&pageSize=${page.pageSize}";
		}


		let pageSizeSelect = document.getElementById("pageSizeSelect");
		pageSizeSelect.onchange = function(){

			let sizeChangeFrom = document.getElementById("sizeChangeFrom");

			sizeChangeFrom.submit();

		}

	</script>
</body>

</html>
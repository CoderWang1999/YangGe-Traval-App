<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>


<body>
<div class="pull-right">
    <div class="form-group form-inline">
        总共${page.pages} 页，共${page.total} 条数据。 每页
        <select class="form-control" onchange="changePageSize()" id="changePageSize">
            <option hidden selected="selected">${page.pageSize}</option>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="40">40</option>
            <option value="80">80</option>
        </select> 条
        <font>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font>
        <div class="box-tools pull-right">
            <ul class="pagination" style="margin: 0px;">
                <li>
                    <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
                </li>
                <li><a href="javascript:goPage(${page.prePage})">上一页</a></li>
                <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" var="i">
                    <li class="paginate_button ${page.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
                </c:forEach>
                <li><a href="javascript:goPage(${page.nextPage})">下一页</a></li>
                <li>
                    <a href="javascript:goPage(${page.pages})" aria-label="Next">尾页</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</div>


<form id="pageForm" action="${param.pageUrl}" method="post">
    <input type="hidden" name="pageNum" id="pageNum">
    <input type="hidden" name="pageSize" id="pageSize">
    <input type="hidden" name="find" value="${find}">
</form>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript">

</script>
<script>
    function goPage(page) {
        document.getElementById("pageNum").value = page;
        document.getElementById("pageSize").value = document.getElementById("changePageSize").value;
        document.getElementById("pageForm").submit()
    }

    function changePageSize() {
        //获取下拉框的值
        let pageSize = $("#changePageSize").val();
        //向服务器发送请求，改变每页页显示条数
        location.href = "${param.pageUrl}?pageNum=1&pageSize="
            + pageSize;
    }
</script>
</body>
</html>

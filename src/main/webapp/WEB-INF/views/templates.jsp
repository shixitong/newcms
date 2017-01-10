<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <title></title>


        <link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap/css/bootstrap.css"/>
        <script src="assets/plugins/jquery/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="assets/plugins/bootstrap-paginator.js"></script>
        <style>
            .form-control-xxx {
                width:400px;
                display:inline;
            }
        </style>
        <script>
            $(function () {
                var carId = 1;
                $.ajax({
                    url: "<%=basePath%>/rest/user/query1",
                    datatype: 'json',
                    type: "Post",
                    data: "id=" + carId,
                    success: function (data) {
                        if (data != null) {
                            $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
                                var str = "<tr>";
                                str=str+"<td>" + item.username + "</td><td>" + item.password +"</td><td>" + item.createTime + "</td>";
                                var button_str = '<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">修改</button>'+'    <button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">删除</button>';
                                str=str+"<td>"+button_str+"</td></tr>";

                                $("#mytable").append(str);

                            });
                            var pageCount = eval("(" + data + ")").pageCount; //取到pageCount的值(把返回数据转成object类型)
                            var currentPage = eval("(" + data + ")").CurrentPage; //得到urrentPage
                            var options = {
                                bootstrapMajorVersion: 3, //版本
                                currentPage: currentPage, //当前页数
                                totalPages: pageCount, //总页数
                                itemTexts: function (type, page, current) {
                                    switch (type) {
                                        case "first":
                                            return "首页";
                                        case "prev":
                                            return "上一页";
                                        case "next":
                                            return "下一页";
                                        case "last":
                                            return "末页";
                                        case "page":
                                            return page;
                                    }
                                },//点击事件，用于通过Ajax来刷新整个list列表
                                onPageClicked: function (event, originalEvent, type, page) {
                                    $.ajax({
                                        url: "<%=basePath%>/rest/user/query1?id=" + page,
                                        type: "Post",
                                        data: "page=" + page,
                                        success: function (data1) {
                                            if (data1 != null) {
                                                $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
                                                    $("#mytable").clear();
                                                    var str = "<tr>";
                                                    str=str+"<td>" + item.username + "</td><td>" + item.password +"</td><td>" + item.createTime + "</td>";
                                                    var button_str = '<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">修改</button>'+'&nbsp;&nbsp;<button class="btn btn-warning" onclick="Edit(' + item.Id + ' );">删除</button>';
                                                    str=str+"<td>"+button_str+"</td></tr>";
                                                    $("#mytable").append(str);
                                                });
                                            }
                                        }
                                    });
                                }
                            };
                            $('#example').bootstrapPaginator(options);
                        }
                    }
                });
            })
        </script>
    </head>
    <body>



        <form name="myform" action="rest/user/query">
            <div class="form-group" width="auto">
                <label for="name">用户名</label>
                <input class="form-control form-control-xxx" type="text"  id="name" placeholder="请输入用户名">
                <button class="btn btn-success" id="search_submit" type="submit">查询</button>
            </div>

            <div class="span9">
            <table class="table table-hover" id="mytable">
                <thead >
                <tr>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="mytable">
               <%-- <c:forEach  items="${userlist}" var="user">

                    <tr>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.createTime}</td>
                        <td><button class="btn btn-modify" id="search_submit" type="submit">修改</button>&nbsp;<button class="btn btn-warning" id="search_submit" type="submit">删除</button></td>
                    </tr>
                </c:forEach>--%>
                </tbody>
            </table>
            <ul id="example"></ul>
                </div>
        </form>

    </body>
</html>
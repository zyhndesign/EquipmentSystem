    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">
        <head>
        <%@ include file="head.jsp"%>
    <link href="resources/css/lib/materialicons.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/lib/materialize.min.css">
    <link rel="stylesheet" href="resources/css/lib/jquery.dataTables.css">
    <link rel="stylesheet" href="resources/css/src/main.css">
    <link rel="stylesheet" href="resources/css/src/components/header/header.css">
    <link rel="stylesheet" href="resources/css/src/components/menu/menu.css">
</head>
<body>


        

        <div class="zyMain">
        <%@ include file="header.jsp"%>
        <div id="slide-out" class="zyLeft side-nav">

        <%@ include file="menu.jsp"%>

        </div>

    <div class="zyRight zyPd20">
        <div class="zyPanel zyBd2">
            <div class="zyPanelTitle">品牌列表</div>
            <div class="zyPanelContent zyPd20">
                <a class="waves-effect waves-light btn" href="brand/brandCOU">
                    <i class="material-icons left">add</i>新增品牌
                </a>

                <table id="myTable" class="dataTable">
                    <thead>
                    <tr>
                        <th>图标</th>
                        <th>名称</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <!--<tr>
                            <td><img src="../../images/brand.png"></td>
                            <td>xxxx</td>
                            <td><a href="1">修改</a>&nbsp;&nbsp;<a href="1">删除</a></td>
                        </tr>-->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<!-- Compiled and minified JavaScript -->
<script>
    var pageName="brand";
</script>
<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/materialize.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/ZYTableHandler.js"></script>
<script src="resources/js/src/common_init.js"></script>
<script src="resources/js/src/pages/brand/brand.js"></script>
</body>
</html>




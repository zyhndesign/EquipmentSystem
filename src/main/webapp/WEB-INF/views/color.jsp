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


        <%@ include file="header.jsp"%>

        <div class="zyMain">
        <div class="zyLeft">

        <%@ include file="menu.jsp"%>

        </div>

    <div class="zyRight zyPd20">
        <div class="zyPanel zyBd2">
            <div class="zyPanelTitle">颜色设置</div>
            <div class="zyPanelContent zyPd20">
                <a class="waves-effect waves-light btn" href="color/colorCOU">
                    <i class="material-icons left">add</i>新建
                </a>

                <table id="myTable" class="dataTable">
                    <thead>
                    <tr>
                        <th>色彩</th>
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <!--<tr>
                            <td><span style="width:100px;height:20px;background:red"></span></td>
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
    var pageName="color";
</script>
<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/materialize.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/lib/juicer-min.js"></script>
<script src="resources/js/src/config.js"></script>
        <script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/ZYTableHandler.js"></script>
<script src="resources/js/src/pages/color/color.js"></script>

</body>
</html>




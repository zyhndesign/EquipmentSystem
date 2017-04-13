<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp"%>
    <link href="/resources/css/lib/materialicons.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/lib/materialize.min.css">
    <link rel="stylesheet" href="/resources/css/src/main.css">
</head>
<body>

    <%@ include file="header.jsp"%>

    <div class="zyMain">
    <div class="zyLeft">

    <%@ include file="menu.jsp"%>

    </div>

    <div class="zyRight">
        <div class="zyPanel">
            <div class="zyPanelTitle">
                欢迎使用此系统。
            </div>
        </div>

    </div>
</div>

<script>
    var pageName="index";
</script>
<script src="/resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="/resources/js/src/config.js"></script>
<script src="/resources/js/src/index.js"></script>

</body>
</html>
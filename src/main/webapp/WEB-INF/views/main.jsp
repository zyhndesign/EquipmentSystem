<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <%@ include file="head.jsp"%>
                <link href="resources/css/lib/materialicons.css" rel="stylesheet">
                <link rel="stylesheet" href="resources/css/lib/materialize.min.css">
                <link rel="stylesheet" href="resources/css/src/main.css">
                <link rel="stylesheet" href="resources/css/src/media-query.css">
                <link rel="stylesheet" href="resources/css/src/components/header/header.css">
                <link rel="stylesheet" href="resources/css/src/components/menu/menu.css">
        </head>

        <body>
                <div class="zyMain">
                <%@ include file="header.jsp"%>
                
                <div id="slide-out" class="zyLeft side-nav fixed">
                    <%@ include file="menu.jsp"%>
                </div>

                    <div class="zyRight">
                        <div class="zyPanel zyPd20">
                        </div>

                    </div>
                    
                </div>

                <script>
                    var pageName = "index";
                    var pageTitle = "欢迎使用此系统"

                </script>
                <script src="resources/js/lib/jquery-2.0.3.min.js"></script>
                <script src="resources/js/lib/materialize.min.js"></script>
                <script src="resources/js/src/config.js"></script>
                <script src="resources/js/src/functions.js"></script>
                <script src="resources/js/src/common_init.js"></script>
        </body>

        </html>

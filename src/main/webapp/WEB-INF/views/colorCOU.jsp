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
                <link rel="stylesheet" href="resources/css/src/media-query.css">
                <link rel="stylesheet" href="resources/css/src/components/header/header.css">
                <link rel="stylesheet" href="resources/css/src/components/menu/menu.css">

                <script>
                    var id = "${color.id}";

                </script>
        </head>

        <body>




            <div class="zyMain">
                <%@ include file="header.jsp"%>
                    <div id="slide-out" class="zyLeft side-nav fixed">

                        <%@ include file="menu.jsp"%>

                    </div>
                    <div class="zyRight zyPd20">
                        <div class="zyPanel">
                            <div class="zyPanelContent zyPd20">
                                <form class="zyForm" id="myForm" action="#">
                                    <div class="row">
                                        <label class="zyFormLabel">颜色：</label>
                                        <input class="zyInput" type="color" id="color" name="colorValue">
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">名称：</label>
                                        <input class="zyInput" type="text" id="name" name="name">
                                    </div>
                                    <div class="row zyFormSubmitRow">
                                        <input type="submit" class="btn" value="确定">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            </div>



            <!-- Compiled and minified JavaScript -->
            <script>
                var pageName = "color";
                var pageTitle = "新增／修改颜色"


            </script>
            <script src="resources/js/lib/jquery-2.0.3.min.js"></script>
            <script src="resources/js/lib/materialize.min.js"></script>
            <script src="resources/js/lib/jquery.form.js"></script>
            <script src="resources/js/lib/jquery.validate.min.js"></script>
            <script src="resources/js/src/config.js"></script>
            <script src="resources/js/src/functions.js"></script>
            <script src="resources/js/src/ZYFormHandler.js"></script>
            <script src="resources/js/src/ZYCtrlDataHandler.js"></script>
            <script src="resources/js/src/common_init.js"></script>
            <script src="resources/js/src/pages/color/colorCOU.js"></script>
        </body>

        </html>

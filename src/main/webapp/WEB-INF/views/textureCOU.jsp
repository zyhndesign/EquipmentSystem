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
                    var id = "${texture.id}";

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
                                    <div class="row" id="uploadContainer">
                                        <label class="zyFormLabel">图像：</label>
                                        <div class="btn" id="uploadBtn">
                                            上传
                                        </div>
                                        <img id="imageShow" style="margin-left: 50px;width:100px;vertical-align: top" src="resources/images/defaultTexture.png">
                                        <input type="hidden" name="icon" id="image">
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">名称：</label>
                                        <input id="name" class="zyInput" type="text" name="name">
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">描述：</label>
                                        <textarea class="zyTextarea" id="description" name="description"></textarea>
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
                var pageName = "texture";
                var pageTitle = "新建／修改纹理"

            </script>
            <script src="resources/js/lib/jquery-2.0.3.min.js"></script>
            <script src="resources/js/lib/materialize.min.js"></script>
            <script src="resources/js/lib/jquery.form.js"></script>
            <script src="resources/js/lib/jquery.validate.min.js"></script>
            <script src="resources/js/lib/plupload.full.min.js"></script>
            <script src="resources/js/lib/qiniu.js"></script>
            <script src="resources/js/src/config.js"></script>
            <script src="resources/js/src/functions.js"></script>
            <script src="resources/js/src/ZYCtrlDataHandler.js"></script>
            <script src="resources/js/src/ZYFormHandler.js"></script>
            <script src="resources/js/src/common_init.js"></script>
            <script src="resources/js/src/pages/texture/textureCOU.js"></script>
        </body>

        </html>

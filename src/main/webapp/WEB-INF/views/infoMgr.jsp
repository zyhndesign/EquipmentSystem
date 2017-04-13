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
    <link rel="stylesheet" href="resources/css/src/pages/infoMgr/info.css">
</head>
<body>


        <%@ include file="header.jsp"%>

        <div class="zyMain">
        <div class="zyLeft">

        <%@ include file="menu.jsp"%>

        </div>

        <div class="zyRight">
        <div class="zyPanel">
     <!--   <div class="zyPanelTitle">知识库</div>  -->
            <div class="zyPanelContent">
            
                
<div class="zySearch" id="search">
    <div class="zySearchRow">
        <a class="waves-effect waves-light btn" href="vehicleInfo/infoCOU">
            <i class="material-icons left">backup</i>新建</a>

        <div class="right">
            <input type="text" class="zyInput1" placeholder="搜索风格" id="searchStyleInput">
            <button class="btn waves-effect waves-light grey lighten-2 black-text zyActionSearchByClick" id="searchStyle">搜索
                <i class="material-icons left">search</i>
            </button>
        </div>
    </div>
    <div class="zySearchRow">
        <h4 class="zySearchTitle">上市时间:</h4>
        <div class="zySearchContent">
            <input type="date" class="zyInput zyActionSearchByChange" id="searchStartDate">
            <div class="line"></div>
            <input type="date" class="zyInput zyActionSearchByChange" id="searchEndDate">
        </div>
    </div>
    <div class="zySearchRow">
        <h4 class="zySearchTitle">产品类型:</h4>
        <div class="zySearchContent row" id="searchMarketType">
            <div class="col">
                <input type="checkbox" class="filled-in" id="type1" value="上市产品"/>
                <label for="type1">上市产品</label>
            </div>
            <div class="col">
                <input type="checkbox" class="filled-in" id="type2" value="概念产品"/>
                <label for="type2">概念产品</label>
            </div>
        </div>
    </div>
    <div class="zySearchRow">
        <h4 class="zySearchTitle">品牌:</h4>
        <div class="zySearchContent" id="searchBrand">
            <!--<input type="checkbox" class="filled-in" id="brand1" checked="checked" />
                <label for="brand1"><img src="/images/brand.png">三一重工</label>-->
        </div>
    </div>
    <div class="zySearchRow">
        <h4 class="zySearchTitle">颜色:</h4>
        <div class="zySearchContent row">
            <div class="col">
                <div class="zySelect zyNoSelect">
                    <span class="zySShow"></span>
                    <span class="zySName">主色:</span>
                    <select class="zySSelect zyActionSearchByChange" id="searchMainColor">
                        <option value="">主色</option>
                    </select>
                </div>
            </div>

            <div class="col">
                <div class="zySelect zyNoSelect">
                    <span class="zySShow"></span>
                    <span class="zySName">辅色1:</span>
                    <select class="zySSelect zyActionSearchByChange" id="searchAssistColor1">
                        <option value="">辅色1</option>
                    </select>
                </div>
            </div>

            <div class="col">
                <div class="zySelect zyNoSelect">
                    <span class="zySShow"></span>
                    <span class="zySName">辅色2:</span>
                    <select class="zySSelect zyActionSearchByChange" id="searchAssistColor2">
                        <option value="">辅色2</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="zySearchRow">
        <h4 class="zySearchTitle">纹理:</h4>
        <div class="zySearchContent" id="searchTexture">
            <!--<input type="checkbox" class="filled-in" id="texture1"  />
                <label for="wenli1">亮光</label>-->
        </div>
    </div>


</div>

<div class="zyCollapseCtrl" id="zySearchCollapse" data-target="up">
    <div class="zyCollapse">
        <i class="material-icons">keyboard_arrow_up</i>
        <span class="zyCCText ">收起选项</span>
    </div>
</div>

                <table id="myTable" class="dataTable">
    <thead>
    <tr>
        <th>图片</th>
        <th>品牌</th>
        <th>类型</th>
        <th>纹理</th>
        <th>时间</th>
        <th>颜色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <!--<tr>
            <td><img src="../../images/brand.png"></td>
            <td>xxxx</td>
            <td>xxxx</td>
            <td>xxxx</td>
            <td>xxxx</td>
            <td>xxxx</td>
            <td><a href="1">编辑</a></td>
        </tr>-->
    </tbody>
</table>
            </div>
        </div>
    </div>
</div>



<!-- Compiled and minified JavaScript -->
<script>
    var pageName="mgr";
</script>
<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/materialize.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/lib/juicer-min.js"></script>
<script src="resources/js/src/config.js"></script>
        <script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/ZYTableHandler.js"></script>
<script src="resources/js/src/ZYCtrlDataHandler.js"></script>
<script src="resources/js/src/pages/infoMgr/infoMgr.js"></script>

</body>
</html>




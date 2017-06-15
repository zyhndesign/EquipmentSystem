
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							<a class="waves-effect waves-light btn" href="vehicleInfo/infoCOU"> <i class="material-icons left">backup</i>新建
							</a>

							<div class="right">
								<input type="text" class="zyInput1" placeholder="搜索名称" id="searchStyleInput">
								<button class="btn waves-effect waves-light grey lighten-2 black-text zyActionSearchByClick" id="searchStyle">
									搜索 <i class="material-icons left">search</i>
								</button>
							</div>
						</div>
						<div class="zySearchRow">
							<h4 class="zySearchTitle">上市时间:</h4>
							<div class="zySearchContent">
								<select class="zyInput zyActionSearchByChange" id="searchStartDate">
									<option value="">请选择</option>
									<option value="2030">2030</option>
									<option value="2029">2029</option>
									<option value="2028">2028</option>
									<option value="2027">2027</option>
									<option value="2026">2026</option>
									<option value="2025">2025</option>
									<option value="2024">2024</option>
									<option value="2023">2023</option>
									<option value="2022">2022</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>
									<option value="2016">2016</option>
									<option value="2015">2015</option>
									<option value="2014">2014</option>
									<option value="2013">2013</option>
									<option value="2012">2012</option>
									<option value="2011">2011</option>
									<option value="2010">2010</option>
									<option value="2009">2009</option>
									<option value="2008">2008</option>
									<option value="2007">2007</option>
									<option value="2006">2006</option>
									<option value="2005">2005</option>
									<option value="2004">2004</option>
									<option value="2003">2003</option>
									<option value="2002">2002</option>
									<option value="2001">2001</option>
									<option value="2000">2000</option>
								</select>
								<div class="zyLine"></div>
								<select class="zyInput zyActionSearchByChange" id="searchEndDate">
									<option value="">请选择</option>
									<option value="2030">2030</option>
									<option value="2029">2029</option>
									<option value="2028">2028</option>
									<option value="2027">2027</option>
									<option value="2026">2026</option>
									<option value="2025">2025</option>
									<option value="2024">2024</option>
									<option value="2023">2023</option>
									<option value="2022">2022</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>
									<option value="2016">2016</option>
									<option value="2015">2015</option>
									<option value="2014">2014</option>
									<option value="2013">2013</option>
									<option value="2012">2012</option>
									<option value="2011">2011</option>
									<option value="2010">2010</option>
									<option value="2009">2009</option>
									<option value="2008">2008</option>
									<option value="2007">2007</option>
									<option value="2006">2006</option>
									<option value="2005">2005</option>
									<option value="2004">2004</option>
									<option value="2003">2003</option>
									<option value="2002">2002</option>
									<option value="2001">2001</option>
									<option value="2000">2000</option>
								</select>
							</div>
						</div>
						<div class="zySearchRow">
							<h4 class="zySearchTitle">产品类型:</h4>
							<div class="zySearchContent row" id="searchMarketType">
								<div class="col">
									<input type="checkbox" class="filled-in" id="type1" value="上市产品" /> <label for="type1">上市产品</label>
								</div>
								<div class="col">
									<input type="checkbox" class="filled-in" id="type2" value="概念产品" /> <label for="type2">概念产品</label>
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
										<span class="zySShow"></span> <span class="zySName">主色:</span> <select class="zySSelect zyActionSearchByChange" id="searchMainColor">
											<option value="">主色</option>
										</select>
									</div>
								</div>

								<div class="col">
									<div class="zySelect zyNoSelect">
										<span class="zySShow"></span> <span class="zySName">辅色1:</span> <select class="zySSelect zyActionSearchByChange" id="searchAssistColor1">
											<option value="">辅色1</option>
										</select>
									</div>
								</div>

								<div class="col">
									<div class="zySelect zyNoSelect">
										<span class="zySShow"></span> <span class="zySName">辅色2:</span> <select class="zySSelect zyActionSearchByChange" id="searchAssistColor2">
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
							<i class="material-icons">keyboard_arrow_up</i> <span class="zyCCText ">收起选项</span>
						</div>
					</div>

					<table id="myTable" class="dataTable">
						<thead>
							<tr>
								<th>图片</th>
								<th>品牌</th>
								<th>类型</th>
								<th>型号</th>
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


	<div id="previewModal" class="modal zyModal">
		<div class="modal-header">预览</div>
		<div class="modal-content">
			<ul class="tabs">
				<li class="tab"><a class="active" href="#pInfo">整体信息</a></li>
				<li class="tab"><a href="#pInfoChild">分结构信息</a></li>
			</ul>
			<div id="pInfo" class="zyTabPanel">
				<div class="row zyForm" style="padding: 10px 0px !important; position: relative">
					<div class="col s6">
						<div class="zyBgFFFFFF zyPd10">
							<div class="row">
								<label class="zyFormLabel">产品类别:</label> <label class="zyFormLabel" id="pInfoCategory">xxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">条目类别:</label> <label class="zyFormLabel" id="pInfoType">xxxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">量产时间:</label> <label class="zyFormLabel" id="pInfoMarketDate">xxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">品牌:</label> <label class="zyFormLabel" id="pInfoBrand">xxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">产品型号:</label> <label class="zyFormLabel" id="pInfoProductCategory">xxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">风格：</label> <label class="zyFormLabel" id="pInfoStyle">xxx,xxxx</label>
							</div>
							<div class="row">
								<label class="zyFormLabel">模型：</label> <a class="zyFormLabel" target="_blank" id="pInfoModal" href="#">xxxx</a>
							</div>
						</div>
						<div class="zyMT20 zyPanelWrapper">
							<div class="row zyBgFFFFFF zyColorPanel zyPd10">
								<label class="zyFormLabel">颜色:</label>

								<div class="row">
									<span class="zySName" style="margin-right: 8px;">主色:</span>

									<div class="zyColorItem">
										<span class="zyColorShow" id="pInfoMainColor" style="background: orange"></span>
									</div>
								</div>

								<div class="row">
									<span class="zySName">辅色1:</span>

									<div class="zyColorItem">
										<span class="zyColorShow" id="pInfoAssistColor1" style="background: orange"></span>
									</div>
								</div>

								<div class="row">
									<span class="zySName">辅色2:</span>

									<div class="zyColorItem">
										<span class="zyColorShow" id="pInfoAssistColor2" style="background: orange"></span>
									</div>
								</div>
							</div>

							<div class="row zyBgFFFFFF zyTexturePanel zyPd10">
								<label class="zyFormLabel">纹理:</label>

								<div id="pInfoTexture"></div>
							</div>
						</div>


					</div>
					<div class="col s6" style="position: absolute; left: 50%; top: 10px; bottom: 10px">
						<div class="zyBgFFFFFF">
							<div class="switch">
								<label> <input type="checkbox" id="pChangeImage"> <span class="lever"></span> 显示图片2
								</label>
							</div>
							<img src="resources/images/upload.png" id="pImage" style="width: 100%; height: auto">
						</div>

						<video class="zyMT20" style="width: 100%" id="pInfoVideo" controls src=""></video>
					</div>
				</div>
				<!--
        <div class="row">
        <div class="col s6 offset-s6">
        <div class="switch">
        <label>

        <input type="checkbox" id="pChangeImage">
        <span class="lever"></span>
        显示特征线
        </label>
        </div>
        </div>
        </div>
        -->
			</div>
			<div id="pInfoChild" class="zyTabPanel">
				<select class="zyInput right input-field zyActionCategory" id="pInfoChildTableSearch">
					<!--<optgroup label="team 1">
            <option value="1">Option 1</option>
            <option value="2">Option 2</option>
            </optgroup>
            <optgroup label="team 2">
            <option value="3">Option 3</option>
            <option value="4">Option 4</option>
            </optgroup>-->
				</select>

				<table class="dataTable" id="pInfoChildTable">
					<thead>
						<tr>
							<th>图片</th>
							<th>名称</th>
							<th>颜色</th>
							<th>纹理</th>
							<th>标识</th>
							<th>信息完整</th>
						</tr>
					</thead>
					<tbody data-type="pInfoChild">

					</tbody>
				</table>
			</div>
		</div>
		<div class="modal-footer center-align">
			<a class="modal-action modal-close btn">关闭</a>
		</div>
	</div>

	<!-- Compiled and minified JavaScript -->
	<script>
		var pageName = "mgr";
	</script>
	<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script src="resources/js/lib/materialize.min.js"></script>
	<script src="resources/js/lib/jquery.dataTables.min.js"></script>
	<script src="resources/js/lib/juicer-min.js"></script>
	<script src="resources/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYTableHandler.js"></script>
	<script src="resources/js/src/ZYCtrlDataHandler.js"></script>
	<script src="resources/js/src/ZYPreviewHandler.js"></script>
	<script src="resources/js/src/pages/infoMgr/infoMgr.js"></script>
<script src="resources/js/src/header.js"></script>
</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <%@ include file="head.jsp"%>
                <link rel="stylesheet" href="resources/css/lib/materialicons.css">
                <link rel="stylesheet" href="resources/css/lib/materialize.min.css">
                <link rel="stylesheet" href="resources/css/lib/jquery.Jcrop.min.css">
                <link rel="stylesheet" href="resources/css/src/main.css">
                <link rel="stylesheet" href="resources/css/src/media-query.css">
                <link rel="stylesheet" href="resources/css/src/components/header/header.css">
                <link rel="stylesheet" href="resources/css/src/components/menu/menu.css">
                <link rel="stylesheet" href="resources/css/src/pages/infoMgr/info.css">
                <script>
                    var id = "${info.id}";

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
                            <div class="zyPanelTitle">
                                <button id="saveBtn" class="btn right hide">上传</button>
                                <button id="previewBtn" class="btn right hide">预览</button>

                                <button class="btn right hide" id="infoChildSave">保存</button>
                                <button class="btn right grey hide" id="infoChildCancel">取消</button>
                            </div>
                            <div class="zyPanelContent">

                                <ul class="tabs" id="tab">
                                    <li class="tab "><a class="active" href="#info">1.整体信息</a></li>
                                    <li class="tab "><a href="#infoChild">2.部件信息</a></li>
                                </ul>
                                <div id="info" class="zyTabPanel">
                                    <div class="zyForm">
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">产品类别：</label> <select class="zyInput zyActionRequired" id="infoCategory">
									<option value="">请选择产品类别</option>
								</select>
                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">条目类别：</label>

                                            <div class="zyFormAllInlineCtrl">

                                                <input class="with-gap" name="infoMarketType" value="1" type="radio" id="type1" checked /> <label for="type1">上市产品</label> <input class="with-gap" name="infoMarketType" value="2" type="radio" id="type2" /> <label style="margin-left: 50px" for="type2">概念产品</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">量产时间：</label> <select class="zyInput zyActionRequired" id="infoMarketDate">
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
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">品牌：</label>

                                            <div class="zySelect zySelectHasImg zyNoSelect ">
                                                <img class="zySShow"> <select class="zySSelect zyActionRequired" id="infoBrand">
										<option value="">请选择品牌</option>
									</select>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel">产品型号：</label> <input class="zyInput" id="infoProductCategory">
                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">图像：</label>
                                            <font class="zyFormHelp">（上传两张不同角度的图像，SVG格式）</font> <br>

                                            <div class="zyFormChildCtrl thumbWrapper">
                                                <label class="zyFormLabel zyRequired zyFormLabelHasSub thumbLabel">图片1
									</label>

                                                <div class="zyImageUpload" id="uploadChanPinImageContainer">
                                                    <img src="resources/images/upload.png" id="uploadChanPinImageBtn"> <input type="hidden" class="zyActionRequired" id="infoImageChanPin">
                                                </div>
                                            </div>
                                            <div class="zyFormChildCtrl thumbWrapper">
                                                <label class="zyFormLabel zyFormLabelHasSub thumbLabel">图片2
									</label>

                                                <div class="zyImageUpload" id="uploadXianXinImageContainer">
                                                    <img src="resources/images/upload.png" id="uploadXianXinImageBtn"> <input type="hidden" id="infoImageXianXin">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">纹理：</label>

                                            <div class="zyFormAllInlineCtrl zyActionRequired zyActionCheckbox" id="infoTexture">

                                                <!--<input class="filled-in" name="type" value="0" type="checkbox" id="texture1" />
                <label for="texture1" style="margin-right: 10px;">亮光</label>-->
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">颜色：</label>

                                            <div class="zyFormAllInlineCtrl">

                                                <div class="zySelect zyNoSelect ">
                                                    <span class="zySShow"></span> <span class="zySName">主色：</span> <select class="zySSelect zyActionRequired" id="infoMainColor">
											<option value="">主色</option>
										</select>
                                                </div>
                                                <div class="zySelect zyNoSelect ">
                                                    <span class="zySShow"></span> <span class="zySName">辅色1：</span> <select class="zySSelect zyActionRequired" id="infoAssistColor1">
											<option value="">辅色1</option>
										</select>
                                                </div>

                                                <div class="zySelect zyNoSelect">
                                                    <span class="zySShow"></span> <span class="zySName">辅色2：</span> <select class="zySSelect zyActionRequired" id="infoAssistColor2">
											<option value="">辅色2</option>
										</select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <label class="zyFormLabel zyRequired">风格：</label> <input type="text" class="zyInput" id="infoStyleInput">
                                            <button class="btn waves-effect waves-light" id="infoAddStyle">
									添加 <i class="material-icons left">add</i>
								</button>

                                            <div id="infoStyle" class="zyFormChildCtrl zyActionRequired zyActionHidden">
                                                <!--<div class="chip">
                简单
                <i class="close material-icons">close</i>
                <input type="hidden" value="简单" name="infoStyle">
                </div>-->
                                            </div>
                                        </div>
                                        <div class="row" id="uploadModalContainer">
                                            <label class="zyFormLabel">模型：</label>

                                            <div class="btn" id="uploadModalBtn">上传FBX</div>
                                            <span style="margin-left: 20px;" id="infoModalShow"></span> <input type="hidden" id="infoModal" value="">
                                        </div>
                                        <div class="row" id="uploadVideoContainer">
                                            <label class="zyFormLabel">视频：</label>

                                            <div class="btn" id="uploadVideoBtn">上传视频（MP4）</div>
                                            <span style="margin-left: 20px;" id="infoVideoShow"></span> <input type="hidden" id="infoVideo" value="">
                                            <button class="btn waves-effect waves-light zyHidden" id="infoVideoPlay">预览</button>
                                        </div>
                                    </div>
                                </div>


                                <div id="infoChild" class="zyTabPanel">
                                    <div id="infoChildMgr">
                                        <div class="row zyForm">
                                            <label class="zyFormLabel">分结构位置:</label> <select class="zyInput input-field zyActionCategory" id="infoChildSearch">
									<!--<optgroup label="team 1">
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                </optgroup>
                <optgroup label="team 2">
                <option value="3">Option 3</option>
                <option value="4">Option 4</option>
                </optgroup>-->
								</select>
                                        </div>
                                        <table class="dataTable" id="infoChildTable">
                                            <thead>
                                                <tr>
                                                    <th>图片</th>
                                                    <th>名称</th>
                                                    <th>颜色</th>
                                                    <th>纹理</th>
                                                    <th>标识</th>
                                                    <th>信息完整</th>
                                                    <th>操作</th>
                                                </tr>
                                            </thead>
                                            <tbody data-type="infoChild">


                                            </tbody>
                                        </table>
                                    </div>


                                    <div id="infoChildAdd" class="zyPanel hide">
                                        <div class="zyPanelTitle" id="iCAddTitle">xxxx</div>
                                        <div class="zyPanelContent" style="padding: 20px 0px;">
                                            <div class="row zyForm">
                                                <div class="col s6">
                                                    <div class="row">
                                                        <label class="zyFormLabel zyTARLabel">图像截取:</label>

                                                        <div style="width: auto; height: 100px; overflow: hidden; display: inline-block">
                                                            <img src="resources/images/cut.png" id="cutImage">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <label class="zyFormLabel zyTARLabel">颜色:</label>

                                                        <div class="zySelect">
                                                            <span class="zySShow"></span> <select class="zySSelect" id="iCAddColor">
													<option value="">颜色</option>
												</select>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <label class="zyFormLabel">纹理:</label>

                                                        <div class="zyFormAllInlineCtrl" id="iCAddTexture">

                                                            <!--<input class="filled-in" name="texture" value="亮光" type="checkbox" id="textureChild1" />
                            <label for="textureChild1">亮光</label>-->
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <label class="zyFormLabel">标识:</label>

                                                        <div class="zyFormAllInlineCtrl">

                                                            <input class="with-gap" name="biaoZhi" value="有" type="radio" id="biaoZhi1" checked /> <label for="biaoZhi1">有</label> <input class="with-gap" name="biaoZhi" value="无" type="radio" id="biaoZhi2" /> <label style="margin-left: 50px" for="biaoZhi2">无</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col s6 zyHasLeftBorder" style="padding-left: 80px; margin-right: 0px;">
                                                    <div class="row">
                                                        <label class="zyFormLabel">造型评价:</label>
                                                        <textarea class="zyTextarea" placeholder="添加额外的造型评价" id="iCAddAppraise"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
            </div>

            <div id="cutImageModal" class="modal zyModal">
                <div class="modal-header" id="cutImageTitle">图像截取</div>
                <div class="modal-content">
                    <div id="cutImageType" class="zyGroupButtons">
                        <span class="zyGBItem zyGBItemActive" data-type="tezhengxian">图片2</span> <span class="zyGBItem" data-type="changpin">图片1</span>
                    </div>
                    <div class="zyTabPanel">
                        <img id="toCutImage" style="width: 600px" src="">
                    </div>
                </div>
                <div class="modal-footer center-align">
                    <a class="btn" id="saveCutImage">保存</a> <a class="modal-action modal-close btn" style="margin-left: 30px;">关闭</a>
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
                        <div class="row zyForm">
                            <div class="col s6 pInfoLeft">
                                <div class="zyBgFFFFFF zyPd10 zyProfilePanel">
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
                                        <label class="zyFormLabel">产品型号:</label> <label class="zyFormLabel" id="pInfoProductCategory">xxx</label>
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">品牌:</label> <label class="zyFormLabel" id="pInfoBrand">xxx</label>
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">风格：</label> <label class="zyFormLabel" id="pInfoStyle">xxx,xxxx</label>
                                    </div>
                                    <div class="row">
                                        <label class="zyFormLabel">模型：</label>
                                        <a class="zyFormLabel" target="_blank" id="pInfoModal" href="#"></a>
                                    </div>
                                </div>
                                <div class="zyMT20 zyPanelWrapper">
                                    <div class="row zyBgFFFFFF zyColorPanel zyPd10">
                                        <label class="zyFormLabel">颜色:</label>

                                        <div class="row">
                                            <span class="zySName zyFormLabel">主色</span>

                                            <div class="zyColorItem">
                                                <span class="zyColorShow" id="pInfoMainColor" style="background: gray"></span>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <span class="zySName zyFormLabel">辅色1</span>

                                            <div class="zyColorItem">
                                                <span class="zyColorShow" id="pInfoAssistColor1" style="background: orange"></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <span class="zySName zyFormLabel">辅色2</span>

                                            <div class="zyColorItem">
                                                <span class="zyColorShow" id="pInfoAssistColor2" style="background: gray"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row zyBgFFFFFF zyTexturePanel zyPd10">
                                        <label class="zyFormLabel">纹理</label>

                                        <div id="pInfoTexture"></div>


                                    </div>
                                </div>


                            </div>
                            <div class="col s6 pInfoRight">
                                <!--
                               <div class="zyBgFFFFFF">
                                    <div class="switch">
                                        <label> <input type="checkbox" id="pChangeImage"> <span class="lever"></span> 显示特征线
								</label>
                                    </div>
                                    <img src="resources/images/upload.png" id="pImage" style="width: 100%; height: auto">
                                </div>
-->
                                <div class="zyBgFFFFFF pImageWrapper">
                                    <div class="switch">
                                        <label> <input type="checkbox" id="toggleLineMode1"> <span class="lever"></span> 显示特征线
								</label>
                                    </div>
                                    <div id="pImage1"><svg></svg></div>
                                </div>
                                <div class="zyBgFFFFFF pImageWrapper">
                                    <div class="switch">
                                        <label> <input type="checkbox" id="toggleLineMode2"> <span class="lever"></span> 显示特征线
								</label>
                                    </div>
                                    <div id="pImage2"><svg></svg></div>
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
                                <!--<tr>
                <td><img class="thumb" src="/images/brand.png"></td>
                <td>上车/操纵室</td>
                <td>
                <div class="zyColorItem">
                <span class="zyColorShow" style="background: red;"></span>
                <span class="zyColorName">红色</span>
                </div>
                </td>
                <td>亮光</td>
                <td>有</td>
                <td><i class="material-icons green-text">done</i></td>
                </tr>
                <tr>
                <td><img class="thumb" src="/images/brand.png"></td>
                <td>上车/操纵室</td>
                <td>
                <div class="zyColorItem">
                <span class="zyColorShow" style="background: red;"></span>
                <span class="zyColorName">红色</span>
                </div>
                </td>
                <td>亮光</td>
                <td>有</td>
                <td><i class="material-icons red-text">close</i></td>
                </tr>-->
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer center-align">
                    <a class="modal-action modal-close btn">关闭</a>
                </div>
            </div>


            <div id="videoModal" class="modal zyModal">
                <div class="modal-header">预览视频</div>
                <div class="modal-content" id="zyVideoModalContent"></div>
                <div class="modal-footer center-align">
                    <a class="modal-action modal-close btn">关闭</a>
                </div>
            </div>


            <!-- Compiled and minified JavaScript -->
            <script>
                var pageName = "mgr";
                var pageTitle = "新增数据"

            </script>
            <script src="resources/js/lib/jquery-2.0.3.min.js"></script>
            <script src="resources/js/lib/materialize.min.js"></script>
            <script src="resources/js/lib/jquery.Jcrop.min.js"></script>
            <script src="resources/js/lib/jquery.form.js"></script>
            <script src="resources/js/lib/juicer-min.js"></script>
            <script src="resources/js/lib/plupload.full.min.js"></script>
            <script src="resources/js/lib/qiniu.js"></script>
            <script src="resources/js/lib/snap.svg-min.js"></script>

            <script src="resources/js/src/config.js"></script>
            <script src="resources/js/src/functions.js"></script>
            <script src="resources/js/src/ZYCtrlDataHandler.js"></script>
            <script src="resources/js/src/ZYFormHandler.js"></script>
            <script src="resources/js/src/ZYPreviewHandler.js"></script>
            <script src="resources/js/src/common_init.js"></script>
            <script src="resources/js/src/pages/infoMgr/infoCOU.js"></script>
        </body>

        </html>

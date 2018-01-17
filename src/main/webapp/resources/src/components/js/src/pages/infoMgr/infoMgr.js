var infoMgr = (function (config, ZYCtrlDataHandler) {

    return {
        table: null,
        loadedData: {},
        getSearchInfo: function () {
            var params = {};
            params.style = $("#searchStyleInput").val();
            params.startDate = $("#searchStartDate").val();
            params.endDate = $("#searchEndDate").val();
            params.marketType = [];
            $("#searchMarketType").find("input[type='checkbox']").each(function (index, el) {
                el = $(el);
                if (el.prop("checked")) {
                    params.marketType.push(el.val());
                }
            });

            params.brand = $("#searchBrand").val();
            params.mainColor = $("#searchMainColor").val();
            params.assistColor1 = $("#searchAssistColor1").val();
            params.assistColor2 = $("#searchAssistColor2").val();
            params.texture = [];
            $("#searchTexture").find("input[type='checkbox']").each(function (index, el) {
                el = $(el);
                if (el.prop("checked")) {
                    params.texture.push(el.val());
                }
            });

            return params;

        },
        search: function () {
            this.table.tableRedraw();
        },
        initSearchHandler: function () {
            var me = this;
            $("#search").on("click", "input[type='checkbox'],.zyActionSearchByClick",function () {
                me.search();
            }).on("change", ".zyActionSearchByChange", function () {
                    me.search();
                });
        },
        initData: function () {
            ZYCtrlDataHandler.getBrandItems("", function (stringOption, stringCheckbox) {
                $("#searchBrand").html(stringCheckbox);
            });
            ZYCtrlDataHandler.getTextureItems("infoMgrTexture", function (stringOption, stringCheckbox) {
                $("#searchTexture").html(stringOption);
            });
            ZYCtrlDataHandler.getColorItems("option", function (stringOption, stringCheckbox) {
                $("#searchMainColor").append(stringOption);
                $("#searchAssistColor1").append(stringOption);
                $("#searchAssistColor2").append(stringOption);
            });
        }
    };
})(config, ZYCtrlDataHandler);

$(document).ready(function () {

    infoMgr.initData();
    infoMgr.initSearchHandler();

    $("#zySearchCollapse").click(function () {
        if ($(this).data("target").indexOf("up") != -1) {
            $("#search .zySearchRow").hide(400);
            $(this).find(".material-icons").text("keyboard_arrow_down");
            $(this).data("target", "down");
            $(this).find(".zyCCText").text("展开选项");
        } else {
            $("#search .zySearchRow").show(400);
            $(this).find(".material-icons").text("keyboard_arrow_up");
            $(this).data("target", "up");
            $(this).find(".zyCCText").text("收起选项");
        }
    });

    infoMgr.table = new ZYTableHandler({
        removeUrl: config.ajaxUrls.infoDelete,
        ownTable: function (data) {
            var dtTable = $('#myTable').dataTable({
                "bServerSide": true,
                "bInfo": true,
                "sAjaxSource": config.ajaxUrls.infoGetByPage,
                "bProcessing": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": false,
                "bAutoWidth": false,
                "iDisplayLength": config.perLoadCounts.table,
                "sPaginationType": "full_numbers",
                "oLanguage": {
                    "sUrl": config.dataTable.langUrl
                },
                aoColumns: [
                    { "mDataProp": "imageUrl1",
                        "fnRender": function (oObj) {
                            return  "<img class='thumb' src='" + oObj.aData.imageUrl1 + "'>";
                        }
                    },
                    { "mDataProp": "brandName"},
                    { "mDataProp": "categoryName"},
                    { "mDataProp": "productCategory"},
                    { "mDataProp": "vehicleTextures",
                        "fnRender": function (oObj) {
                            var arr = [];
                            for (var i = 0, len = oObj.aData.vehicleTextures.length; i < len; i++) {
                                arr.push(oObj.aData.vehicleTextures[i].texture.name);
                            }
                            return arr.join(",");
                        }},
                    { "mDataProp": "onSaleDate"},
                    { "mDataProp": "vehicleColors",
                        "fnRender": function (oObj) {
                            oObj.aData.vehicleColors.sort(function (a, b) {
                                return b.id - a.id;
                            });
                            var arr = [];
                            for (var i = 0, len = oObj.aData.vehicleColors.length; i < len; i++) {
                                arr.push(oObj.aData.vehicleColors[i].color.colorValue);
                            }
                            return juicer(config.colorItemsTpl, {
                                items: arr
                            });
                        }},
                    { "mDataProp": "opt",
                        "fnRender": function (oObj) {
                            return  '<a class="preview" href="' + oObj.aData.id + '">预览</a>&nbsp;&nbsp;' +
                                '<a href="vehicleInfo/infoCOU/' + oObj.aData.id + '">编辑</a>&nbsp;&nbsp;' +
                                '<a href="' + oObj.aData.id + '" class="remove">删除</a>';
                        }
                    }
                ],
                "fnServerParams": function (aoData) {

                },
                "fnServerData": function (sSource, aoData, fnCallback) {
                	
                	
                    //回调函数
                	
                    $.ajax({
                        "dataType": 'json',
                        "type": "get",
                        "url": sSource,
                        "data": aoData,
                        "success": function (response) {
                            if (response.success === false) {
                                functions.ajaxReturnErrorHandler(response.message);
                            } else {
                                var json = {
                                    "sEcho": response.sEcho
                                };

                                for (var i = 0, iLen = response.aaData.length; i < iLen; i++) {
                                    response.aaData[i].opt = "opt";
                                    infoMgr.loadedData[response.aaData[i].id] = response.aaData[i];
                                }

                                json.aaData = response.aaData;
                                json.iTotalRecords = response.iTotalRecords;
                                json.iTotalDisplayRecords = response.iTotalDisplayRecords;
                                fnCallback(json);
                            }

                        }
                    });
                },
                "fnFormatNumber": function (iIn) {
                    return iIn;
                }
            });

            return dtTable;
        }
    });

    $("#myTable").on("click", "a.remove",function () {
        infoMgr.table.delete({id: $(this).attr("href")});
        return false;
    }).on("click", "a.preview", function () {
            if (!infoMgr.zyPreview) {
                infoMgr.zyPreview = new ZYPreviewHandler();
            }
            infoMgr.zyPreview.show(infoMgr.loadedData[$(this).attr("href")]);
            return false;
        });
});
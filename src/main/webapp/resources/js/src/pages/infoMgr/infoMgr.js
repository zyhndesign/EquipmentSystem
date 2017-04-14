var infoMgr=(function(config,ZYCtrlDataHandler){

    return {
        table:null,
        getSearchInfo:function(){
            var params={};
            params.style=$("#searchStyleInput").val();
            params.startDate=$("#searchStartDate").val();
            params.endDate=$("#searchEndDate").val();
            params.marketType=[];
            $("#searchMarketType").find("input[type='checkbox']").each(function(index,el){
                el=$(el);
                if(el.prop("checked")){
                    params.marketType.push(el.val());
                }
            });

            params.brand=$("#searchBrand").val();
            params.mainColor=$("#searchMainColor").val();
            params.assistColor1=$("#searchAssistColor1").val();
            params.assistColor2=$("#searchAssistColor2").val();
            params.texture=[];
            $("#searchTexture").find("input[type='checkbox']").each(function(index,el){
                el=$(el);
                if(el.prop("checked")){
                    params.texture.push(el.val());
                }
            });

            return params;

        },
        search:function(){
            this.table.tableRedraw();
        },
        initSearchHandler:function(){
            var me=this;
            $("#search").on("click","input[type='checkbox'],.zyActionSearchByClick",function(){
                me.search();
            }).on("change",".zyActionSearchByChange",function(){
                me.search();
            });
        },
        initData:function(){
            ZYCtrlDataHandler.getBrandItems("",function(stringOption,stringCheckbox){
                $("#searchBrand").html(stringCheckbox);
            });
            ZYCtrlDataHandler.getTextureItems("infoMgrTexture",function(stringOption,stringCheckbox){
                $("#searchTexture").html(stringOption);
            });
            ZYCtrlDataHandler.getColorItems("option",function(stringOption,stringCheckbox){
                $("#searchMainColor").append(stringOption);
                $("#searchAssistColor1").append(stringOption);
                $("#searchAssistColor2").append(stringOption);
            });


        }
    };
})(config,ZYCtrlDataHandler);

$(document).ready(function(){

    infoMgr.initData();
    infoMgr.initSearchHandler();

    $("#zySearchCollapse").click(function(){
        if($(this).data("target").indexOf("up")!=-1){
            $("#search .zySearchRow").hide(400);
            $(this).find(".material-icons").text("keyboard_arrow_down");
            $(this).data("target","down");
            $(this).find(".zyCCText").text("展开选项");
        }else{
            $("#search .zySearchRow").show(400);
            $(this).find(".material-icons").text("keyboard_arrow_up");
            $(this).data("target","up");
            $(this).find(".zyCCText").text("收起选项");
        }
    });

    infoMgr.table=new ZYTableHandler({
        removeUrl:config.ajaxUrls.infoDelete,
        ownTable:function(data){
            var dtTable=$('#myTable').dataTable( {
                "bServerSide": true,
                "bInfo":true,
                "sAjaxSource": config.ajaxUrls.infoGetByPage,
                "bProcessing": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort":false,
                "bAutoWidth": false,
                "iDisplayLength":config.perLoadCounts.table,
                "sPaginationType":"full_numbers",
                "oLanguage": {
                    "sUrl":config.dataTable.langUrl
                },
                aoColumns: [
                    { "mDataProp": "imageChanPin",
                        "fnRender":function(oObj){
                            return  "<img class='thumb' src='"+oObj.aData.imageChanPin+"'>";
                        }
                    },
                    { "mDataProp": "brand"},
                    { "mDataProp": "category"},
                    { "mDataProp": "texture",
                        "fnRender":function(oObj){
                            return  oObj.aData.texture.join("/");
                        }},
                    { "mDataProp":"marketDate"},
                    { "mDataProp": "color",
                        "fnRender":function(oObj){
                            return juicer(config.colorItemsTpl,{
                                items:oObj.aData.color
                            })
                        }},
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return  '<a href="vehicleInfo/infoCOU.html?'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;'+
                                '<a href="'+oObj.aData.id+'" class="remove">删除</a>';
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

    $("#myTable").on("click","a.remove",function(){
        mgr.table.delete({id:$(this).attr("href")});
        return false;
    })
});
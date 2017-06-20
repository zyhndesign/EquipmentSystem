$(document).ready(function() {
    var table=new ZYTableHandler({
        removeUrl:config.ajaxUrls.colorDelete,
        ownTable:function(){
            var dtTable=$('#myTable').dataTable( {
                "bServerSide": true,
                "bInfo":true,
                "sAjaxSource": config.ajaxUrls.colorGetByPage,
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
                    { "mDataProp": "colorValue",
                        "fnRender":function(oObj){
                            return juicer(config.colorItemsTpl,{
                                items:[oObj.aData.colorValue]
                            });
                        }
                    },
                    { "mDataProp": "name"},
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return  '<a href="color/colorCOU?'+oObj.aData.id+'">编辑</a>&nbsp;&nbsp;'+
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
        table.delete({id:$(this).attr("href")});
        return false;
    })

} );
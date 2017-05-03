function ZYPreviewHandler(){

}
ZYPreviewHandler.prototype.setHtmlForInfoChildTable=function(list, tableTBody){
    var item;
    for (var j = 0, jLen = list.length; j < jLen; j++) {
        item = list[j];

        //imageString在编辑的时候会处理
        if (!item.imageString) {
            item.imageString = "";
        }

        if (!item.infoFullString) {
            item.infoFullString = item.infoFull == 1 ? '<i class="material-icons green-text">done</i>' : '<i class="material-icons red-text">close</i>';
        }

        if (!item.colorString) {
            item.colorString = item.color ? '<div class="zyColorItem">' +
                '<span class="zyColorShow" style="width: 20px;height: 20px;background: ' + item.color + '"></span>' +
                '</div>' : "";
        }

    }

    tableTBody.html(juicer(config.infoChildTrsTpl, {
        type: tableTBody.data("type"),
        items: list
    }));
}
ZYPreviewHandler.prototype.show=function(data){
    $("#pInfoCategory").text(data.category);
    $("#pInfoType").text(data.type);
    $("#pInfoMarketDate").text(data.onSaleDate);
    $("#pInfoBrand").text(data.brand);
    $("#pImage").attr("src", data.imageUrl1);
    $("#pChangeImage").prop("checked",false);
    $("#pInfoMainColor").css("background", data.color1);
    $("#pInfoAssistColor1").css("background", data.color2);
    $("#pInfoAssistColor2").css("background", data.color3);
    $("#pInfoTexture").html(juicer(config.textureItemsTpl, {
        items: data.textures
    }));
    $("#pInfoStyle").text(JSON.parse(data.style).join(","));
    $("#pInfoModal").attr("href", data.modelUrl).text(data.modelUrl);

    infoCou.setHtmlForInfoChildTable(JSON.parse(data.componentInfo), $("#pInfoChildTable tbody"));

    $("#previewModal").modal("open");
}
ZYPreviewHandler.prototype.filterInfoChildTable=function(componentInfo,filter,tableTBody){
    var arr = [];
    if(filter!=""){
        for (var i = 0, len = componentInfo.length; i < len; i++) {
            if (componentInfo[i].id == filter) {
                arr.push(componentInfo[i]);
            }
        }
    }else{
        arr=componentInfo;
    }
    this.setHtmlForInfoChildTable(arr, tableTBody);
}
ZYPreviewHandler.prototype.initEvent=function(form,editId,data,isJsonString){
    var me=this;
    $("#previewModal").modal();
    $("#pChangeImage").change(function () {
        if ($(this).prop("checked")) {
            $("#pImage").attr("src", infoCou.submitData.imageUrl2);
        } else {
            $("#pImage").attr("src", infoCou.submitData.imageUrl1);
        }
    });
    $("#pInfoChildTableSearch").change(function () {
        me.filterInfoChildTable($(this).val(), $("#pInfoChildTable tbody"));
    });
};
function ZYPreviewHandler() {
    this.initEvent();
}
ZYPreviewHandler.prototype.setHtmlForInfoChildTable = function (list, tableTBody) {
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
ZYPreviewHandler.prototype.show = function (data) {
    var vehicleTextures = [], me = this;
    data.componentInfo=JSON.parse(data.componentInfo);

    ZYCtrlDataHandler.getCategoryGroupOptions(data.categoryId, function (string) {
        $("#pInfoChildTableSearch").html(string).material_select();

        $("#pInfoCategory").text(data.categoryName);
        $("#pInfoType").text(data.entry);
        $("#pInfoMarketDate").text(data.onSaleDate);
        $("#pInfoBrand").text(data.brandName);
        $("#pImage").attr("src", data.imageUrl1);
        $("#pInfoProductCategory").text(data.productCategory);
        $("#pChangeImage").prop("checked", false);
        $("#pInfoMainColor").css("background", data.vehicleColors[0].color.colorValue);
        $("#pInfoAssistColor1").css("background", data.vehicleColors[1].color.colorValue);
        $("#pInfoAssistColor2").css("background", data.vehicleColors[2].color.colorValue);
        for (var i = 0, len = data.vehicleTextures.length; i < len; i++) {
            vehicleTextures.push(data.vehicleTextures[i].texture);
        }
        $("#pInfoTexture").html(juicer(config.textureItemsTpl, {
            items: vehicleTextures
        }));
        $("#pInfoStyle").text(JSON.parse(data.style).join(","));
        $("#pInfoModal").attr("href", data.modelUrl).text(data.modelUrl);
        $("#pInfoVideo").attr("src",data.videoUrl);

        me.setHtmlForInfoChildTable(data.componentInfo, $("#pInfoChildTable tbody"));

        me.showData=data;

        $("#previewModal").modal("open");
    });

}
ZYPreviewHandler.prototype.filterInfoChildTable = function (componentInfo, filter, tableTBody) {
    var arr = [];
    if (filter != "") {
        for (var i = 0, len = componentInfo.length; i < len; i++) {
            if (componentInfo[i].id == filter) {
                arr.push(componentInfo[i]);
            }
        }
    } else {
        arr = componentInfo;
    }
    this.setHtmlForInfoChildTable(arr, tableTBody);
}
ZYPreviewHandler.prototype.initEvent = function () {
    var me = this;
    $("#previewModal").modal({
        complete:function(){
            $("#pInfoVideo").removeAttr("src");
        }
    });
    $("#pChangeImage").change(function () {
        if ($(this).prop("checked")) {
            $("#pImage").attr("src", me.showData.imageUrl2);
        } else {
            $("#pImage").attr("src", me.showData.imageUrl1);
        }
    });
    $("#pInfoChildTableSearch").change(function () {
        me.filterInfoChildTable(me.showData.componentInfo,$(this).val(), $("#pInfoChildTable tbody"));
    });
};
var infoCou = (function (config, ZYCtrlDataHandler) {

    return {
        cutCtrl: null,
        componentInfo: [],
        changeMainBtnStatus: function (showSave) {

            if (showSave) {
                //显示保存按钮，隐藏infoChild页面的保存和取消
                $("#saveBtn").removeClass("hide");
                $("#previewBtn").removeClass("hide");

                $("#infoChildSave").addClass("hide");
                $("#infoChildCancel").addClass("hide");
            } else {
                $("#saveBtn").addClass("hide");
                $("#previewBtn").addClass("hide");

                $("#infoChildSave").removeClass("hide");
                $("#infoChildCancel").removeClass("hide");
            }
        },
        initJCrop: function () {
            var me = this;

            if (me.cutCtrl) {
                me.cutCtrl.destroy();
            }

            $('#toCutImage').Jcrop({
                boxWidth: 600,
                boxHeight: 600,
                bgColor: "#fff",
                onSelect: function (c) {
                    me.cutCtrl.customData.x = c.x / me.cutCtrl.customData.boundW;
                    me.cutCtrl.customData.y = c.y / me.cutCtrl.customData.boundH;
                    me.cutCtrl.customData.ratio = c.w / c.h;
                    me.cutCtrl.customData.w = c.w;
                    me.cutCtrl.customData.h = c.h;
                    me.cutCtrl.customData.src = $('#toCutImage').attr("src");
                },
                aspectRatio: 0
            }, function () {
                me.cutCtrl = this;
                var bounds = this.getBounds();
                me.cutCtrl.customData = {
                    boundW: bounds[0],
                    boundH: bounds[1]
                }
            });
        },
        updateCutImage: function (customData) {
            var cutImage = $("#cutImage");
            var cssObj = ZYCtrlDataHandler.computeImageCss(100, customData);

            cutImage.attr("src", customData.src);
            cutImage.parent().css("width", cssObj.showWidth);
            cutImage.css({
                width: cssObj.realW,
                height: cssObj.realH,
                marginLeft: cssObj.marginL,
                marginTop: cssObj.marginT
            });
        },
        validInfo: function (el) {
            var requiredEls = el.find(".zyActionRequired"),
                valid = true;
            $.each(requiredEls, function (index, el) {
                el = $(el);
                if (el.hasClass("zyActionCheckbox")) {
                    if (el.find("input:checked").length == 0) {
                        valid = false;
                        return false;
                    }
                } else if (el.hasClass("zyActionHidden")) {
                    if (el.find("input[type='hidden']").length == 0) {
                        valid = false;
                        return false;
                    }
                } else {
                    if (el.val() == "") {
                        valid = false;
                        return false;
                    }
                }
            });
            if (valid == false) {
                Materialize.toast(config.messages.pleaseFillRequired, 4000);
            }

            return valid;
        },
        setHtmlForInfoChildTable: function (list, tableTBody) {

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
        },
        /**
         * 填充子部件信息，这里每次都要生成，以免用户更改了category的树结构，导致数据不对
         * @param tableTBody
         */
        initInfoChildTable: function (tableTBody) {

            var list = ZYCtrlDataHandler.categoryGroupData,
                arr = [], parent, item;
            for (var i = 0, len = list.length; i < len; i++) {
                parent = list[i];
                for (var j = 0, jLen = parent.childs.length; j < jLen; j++) {
                    item = parent.childs[j];

                    if (config.findInArray(this.componentInfo, "id", item.id) == -1) {
                        this.componentInfo.push({
                            id: item.id,
                            image: {},
                            name: parent.name + "/" + item.name,
                            texture: [],
                            textureText:"",
                            color: [],
                            hasBiaoZhi: "无",
                            infoFull: 0
                        });
                    }
                }
            }

            this.setHtmlForInfoChildTable(this.componentInfo, tableTBody);
        },
        filterInfoChildTable: function (filter, tableTbody) {
            var arr = [];
            if(filter!=""){
                for (var i = 0, len = this.componentInfo.length; i < len; i++) {
                    if (this.componentInfo[i].id == filter) {
                        arr.push(this.componentInfo[i]);
                    }
                }
            }else{
                arr=this.componentInfo;
            }
            this.setHtmlForInfoChildTable(arr, tableTbody);
        },
        infoChildSave: function () {
            var component = this.componentInfo[this.infoChildIndex];

            if (this.cutCtrl && this.cutCtrl.customData) {
                component.image.src = this.cutCtrl.customData.src;
                component.image.customData = this.cutCtrl.customData;
                component.imageString = ZYCtrlDataHandler.getCutImageEl(100, this.cutCtrl.customData);
            }
            component.hasBiaoZhi = $("#infoChildAdd input[name='biaoZhi']").val();
            component.color = $("#iCAddColor").val();
            component.textureText=[];
            component.texture=[];
            $("#iCAddTexture input:checked").each(function () {
                component.texture.push($(this).val());
                component.textureText.push($(this).next().text());
            });
            component.textureText = component.textureText.join(",");
            component.infoFull = component.image.src && component.color && component.hasBiaoZhi && component.texture ? 1 : 0;
            component.appraise = $("#iCAddAppraise").val();


            component.infoFullString = component.infoFull == 1 ? '<i class="material-icons green-text">done</i>' : '<i class="material-icons red-text">close</i>';
            component.colorString = component.color ? '<div class="zyColorItem">' +
                '<span class="zyColorShow" style="width: 20px;height: 20px;background: ' + component.color + '"></span>' +
                '</div>' : "";


            this.currentInfoChildTr.replaceWith(juicer(config.infoChildTrTpl, component));

            this.showInfoChildMgr();
        },
        clearICAdd: function () {
            $("#iCAddColor").val("").prev().removeAttr("style");
            $("#iCAddTexture input[type='checkbox']").prop("checked", false);
            $("#iCAddAppraise").val("");
            $("#cutImage").attr("src", "").removeAttr("style");

            if (this.cutCtrl) {
                delete this.cutCtrl.customData;
            }
        },
        initICAdd: function () {
            var component = this.componentInfo[this.infoChildIndex];
            $("#iCAddColor").val(component.color).prev().css("background", component.color);
            $("#iCAddTexture input[type='checkbox']").each(function (index, el) {
                if (component.texture.indexOf($(this).val()) != -1) {
                    $(this).prop("checked", true);
                }
            });
            $("#iCAddAppraise").val(component.appraise);
            $("#iCAddTitle").text(component.name);
            $("#cutImageTitle").text("图像裁剪----" + component.name);
            if (component.image.customData) {
                this.updateCutImage(component.image.customData);
            }
        },
        showInfoChildMgr: function () {
            $("#infoChildMgr").removeClass("hide");
            $("#infoChildAdd").addClass("hide");

            //此时显示保存和预览
            this.changeMainBtnStatus(true);

            this.clearICAdd();
        },
        hideInfoChildMgr: function () {
            $("#infoChildMgr").addClass("hide");
            $("#infoChildAdd").removeClass("hide");

            //此时不显示保存和预览
            this.changeMainBtnStatus(false);


            this.initICAdd();
        },
        getSubmitData: function () {
            var data = {};

            data.imageUrl1 = $("#infoImageChanPin").val();
            data.imageUrl2 = $("#infoImageXianXin").val();
            data.brandId = $("#infoBrand").val();
            data.categoryId = $("#infoCategory").val();
            data.entry = $("input[name='infoMarketType']:checked").val();
            data.onSaleDate = $("#infoMarketDate").val();
            data.style = [];
            $("input[name='infoStyle']").each(function (index, el) {
                data.style.push($(el).val());
            });
            data.style = JSON.stringify(data.style);
            data.modelUrl = $("#infoModal").val();
            data.vehicleTextures = [];
            $("#infoTexture").find("input[type='checkbox']").each(function (index, el) {
                data.vehicleTextures.push({texture: {id: $(el).val()}});
            });
            data.vehicleColors = [
                {color: {id: $("#infoMainColor").val()}},
                {color: {id: $("#infoAssistColor1").val()} },
                {color: {id: $("#infoAssistColor2").val()}}
            ];
            data.componentInfo = JSON.stringify(this.componentInfo);

            this.submitData = data;

            return data;

        },
        initCtrlData: function (data) {
            var textureArr=[];
            data.vehicleColors.sort(function(a,b){
                return b.id- a.id;
            });
            for(var i= 0,len=data.vehicleTextures.length;i<len;i++){
                textureArr.push(data.vehicleTextures[i].texture.id);
            }

            this.componentInfo = JSON.parse(data.componentInfo);
            $("#infoCategory").val(data.categoryId);
            $("input[name='infoMarketType']").val(data.entry);
            $("#infoMarketDate").val(data.onSaleDate);
            $("#infoBrand").val(data.brandId);
            $("#infoImageChanPin").val(data.imageUrl1).parent().find("img").attr("src", data.imageUrl1);
            $("#infoImageXianXin").val(data.imageUrl2).parent().find("img").attr("src", data.imageUrl2);
            $("#infoMainColor").val(data.vehicleColors[0].color.id).
                parent().removeClass("zyNoSelect").find(".zySShow").css("background", data.vehicleColors[0].color.colorValue);
            $("#infoAssistColor1").val(data.vehicleColors[1].color.id).
                parent().removeClass("zyNoSelect").find(".zySShow").css("background", data.vehicleColors[1].color.colorValue);
            $("#infoAssistColor2").val(data.vehicleColors[2].color.id).
                parent().removeClass("zyNoSelect").find(".zySShow").css("background", data.vehicleColors[2].color.colorValue);
            $("#infoTexture input[type='checkbox']").each(function (index, el) {
                el = $(el);
                if (textureArr.indexOf(parseInt(el.val())) != -1) {
                    el.prop("checked", true);
                }
            });
            $("#infoStyle").html(juicer(config.styleAllTpl, {
                items: JSON.parse(data.style)
            }));

            $("#infoModal").val(data.modal).prev().text(data.modal);

        },
        initData: function () {
            var me = this;

            ZYCtrlDataHandler.getBrandItems("", function (stringOption, stringCheckbox) {
                $("#infoBrand").html(stringOption);
            });
            ZYCtrlDataHandler.getTextureItems("infoCOUTexture", function (stringOption, stringCheckbox) {
                $("#infoTexture").html(stringCheckbox);

                ZYCtrlDataHandler.getTextureItems("iCAdd", function (stringOption, stringCheckbox) {
                    $("#iCAddTexture").html(stringCheckbox);
                });
            });

            ZYCtrlDataHandler.getColorItems("", function (stringOption, stringCheckbox) {
                $("#infoMainColor").append(stringOption);
                $("#infoAssistColor1").append(stringOption);
                $("#infoAssistColor2").append(stringOption);
                $("#iCAddColor").append(stringOption);
            });
            ZYCtrlDataHandler.getCategoryFirstLevelItems("", function (stringOption, stringCheckbox) {
                $("#infoCategory").html(stringOption);
            });

            if (id) {
                ZYCtrlDataHandler.getDataForUpdate(config.ajaxUrls.infoGetDetail, {id: id}, function (data) {
                    me.initCtrlData(data);
                    me.initChildInfo(data.categoryId);
                });
            }

        },
        initChildInfo: function (categoryId) {
            var me = this;
            ZYCtrlDataHandler.getCategoryGroupOptions(categoryId, function (string) {
                $(".zyActionCategory").html(string).material_select();
            });

            me.initInfoChildTable($("#infoChildTable tbody"));
        }
    }
})(config, ZYCtrlDataHandler);
$(document).ready(function () {
    var formHandler = new ZYFormHandler({
        redirectUrl: "vehicleInfo/infoMgr",
        createUrl: config.ajaxUrls.infoCreate,
        updateUrl: config.ajaxUrls.infoUpdate
    });

    infoCou.initData();

    functions.createQiNiuUploader({
        maxSize: config.uploader.sizes.all,
        filter: config.uploader.filters.all,
        uploadBtn: "uploadModalBtn",
        multiSelection: false,
        multipartParams: null,
        uploadContainer: "uploadModalContainer",
        fileAddCb: null,
        progressCb: null,
        uploadedCb: function (info, file, up) {
            $("#infoModal").val(info.url);

            $("#infoModalShow").text(file.name);
        }
    });
    functions.createQiNiuUploader({
        maxSize: config.uploader.sizes.img,
        filter: config.uploader.filters.img,
        uploadBtn: "uploadChanPinImageBtn",
        multiSelection: false,
        multipartParams: null,
        uploadContainer: "uploadChanPinImageContainer",
        fileAddCb: null,
        progressCb: null,
        uploadedCb: function (info, file, up) {
            $("#infoImageChanPin").val(info.url);

            $("#uploadChanPinImageBtn").attr("src", info.url);

            $(".error[for='image']").remove();
        }
    });
    functions.createQiNiuUploader({
        maxSize: config.uploader.sizes.img,
        filter: config.uploader.filters.img,
        uploadBtn: "uploadXianXinImageBtn",
        multiSelection: false,
        multipartParams: null,
        uploadContainer: "uploadXianXinImageContainer",
        fileAddCb: null,
        progressCb: null,
        uploadedCb: function (info, file, up) {
            $("#infoImageXianXin").val(info.url);

            $("#uploadXianXinImageBtn").attr("src", info.url);

            $(".error[for='image']").remove();
        }
    });

    $("#tab a").click(function (e) {
        if ($(this).attr("href") == "#infoChild") {
            if (!infoCou.validInfo($("#info"))) {
                return false;
            }

        } else {
            infoCou.showInfoChildMgr();
        }

        infoCou.changeMainBtnStatus(true);
    });
    $("input[name='infoImage']").change(function () {
        var name = this.files[0].name,
            url = "/data/" + name;

        $(this).next().val(url);
        $(this).prev().attr("src", url);
    });
    $("#infoAddStyle").click(function () {
        var value = $("#infoStyleInput").val();
        if (value) {
            $("#infoStyle").append(juicer(config.styleTpl, {
                data: value
            }));

            $("#infoStyleInput").val("");
        }
    });
    $("#infoStyle").on("click", ".close", function () {
        $(this).parent().remove();
    });
    $("#infoCategory").change(function () {
        if ($(this).val()) {
            infoCou.initChildInfo($(this).val());
        }
    });

    /**********************************分结构信息*******************************/
    $("#infoChildTable").on("click", ".zyActionEdit", function () {
        infoCou.currentInfoChildTr = $(this).parents("tr");
        infoCou.infoChildIndex = config.findInArray(infoCou.componentInfo, "id", $(this).attr("href"));
        infoCou.hideInfoChildMgr();
        return false;
    });
    $("#infoChildSearch").change(function () {
        infoCou.filterInfoChildTable($(this).val(), $("#infoChildTable tbody"));
    });

    $('#cutImageModal').modal();
    $("#cutImageType").on("click", ".zyGBItem", function () {
        var type = $(this).data("type"),
            xianXinSrc = $("#infoImageXianXin").val(),
            chanPinSrc = $("#infoImageChanPin").val(),
            imageSrc;

        switch (type) {
            case "tezhengxian":
                imageSrc = xianXinSrc ? xianXinSrc : chanPinSrc;

                break;
            case "changpin":
                imageSrc = chanPinSrc;

                break;
        }

        $("#cutImageType").find(".zyGBItemActive").removeClass("zyGBItemActive");
        $(this).addClass("zyGBItemActive");
        $("#toCutImage").attr("src", imageSrc);

        infoCou.initJCrop();
    });

    $("#cutImage").click(function () {
        $('#cutImageModal').modal('open');
        $("#toCutImage").attr("src", $("#infoImageChanPin").val());
        infoCou.initJCrop();
    });

    $("#saveCutImage").on("click", function () {
        infoCou.updateCutImage(infoCou.cutCtrl.customData);
        $('#cutImageModal').modal('close');
    });

    $("#infoChildCancel").click(function () {
        infoCou.showInfoChildMgr();
    });

    $("#infoChildSave").click(function () {
        infoCou.infoChildSave();
    });


    /*****************************预览部分************************/
    var zyPreview=new ZYPreviewHandler();
    $("#previewBtn").click(function () {
        var submitData = infoCou.getSubmitData();

        var textures = [], colors = [], index;

        for (var i = 0, len = submitData.vehicleTextures.length; i < len; i++) {
            index = config.findInArray(ZYCtrlDataHandler.textureData, "id", submitData.vehicleTextures[i].texture.id);
            if (index != -1) {
                textures.push(ZYCtrlDataHandler.textureData[index]);
            }
        }
        for (var j = 0, jLen = submitData.vehicleColors.length; j < jLen; j++) {
            index = config.findInArray(ZYCtrlDataHandler.colorData, "id", submitData.vehicleColors[j].color.id);
            if (index != -1) {
                colors.push(ZYCtrlDataHandler.colorData[index]);
            }
        }

        zyPreview.show({
            category:$("#infoCategory option:selected").text(),
            type:$("input[name='infoMarketType']:checked").next().text(),
            onSaleDate:submitData.onSaleDate,
            brand:$("#infoBrand option:selected").text(),
            imageUrl1:submitData.imageUrl1,
            color1:colors[0].colorValue,
            color2:colors[1].colorValue,
            color3:colors[2].colorValue,
            textures:textures,
            style:submitData.style,
            modelUrl:submitData.modelUrl,
            componentInfo:submitData.componentInfo
        });
    });
    $("#saveBtn").click(function () {
        if (!infoCou.submitData) {
            infoCou.getSubmitData();
        }

        formHandler.submitForm(null,
            id, infoCou.submitData, true);

    });

});


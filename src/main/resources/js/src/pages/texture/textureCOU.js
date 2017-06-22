$(document).ready(function(){
    if(id){
        ZYCtrlDataHandler.getDataForUpdate(config.ajaxUrls.textureDetail,{id:id},function(data){
            $("#name").val(data.name);
            $("#description").val(data.description);
            $("#image").val(data.icon);
            $("#imageShow").attr("src",data.icon);
        });
    }
    var formHandler=new ZYFormHandler({
        redirectUrl:"texture/texture",
        createUrl:config.ajaxUrls.textureCreate,
        updateUrl:config.ajaxUrls.textureUpdate
    });
    $("#myForm").validate({
        ignore:[],
        rules:{
            name:{
                required:true,
                maxlength:32
            },
            icon:{
                required:true
            }
        },
        messages:{
            name:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            icon:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            formHandler.submitForm(form,id);
        }
    });

    functions.createQiNiuUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:null,
        uploadContainer:"uploadContainer",
        fileAddCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            $("#image").val(info.url);

            $("#imageShow").attr("src",info.url);

            $(".error[for='image']").remove();
        }
    });
});
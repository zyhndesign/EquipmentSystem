function ZYFormHandler(params){
    this.redirectUrl=params.redirectUrl;
    this.createUrl=params.createUrl;
    this.updateUrl=params.updateUrl;
}
ZYFormHandler.prototype.submitForm=function(form,editId,data,isJsonString){
    var me=this;
    functions.showLoading();
    if(editId){
        data=data||{};
        data.id=editId;
    }
    if(isJsonString){
        $.ajax({
            dataType:"json",
            type:"post",
            url:editId?me.updateUrl:me.createUrl,
            contentType :"application/json; charset=UTF-8",
            data:data,
            success:function(response){
                if(response.success){
                    Materialize.toast(config.messages.optSuccRedirect, 4000);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })
    }else{
        $(form).ajaxSubmit({
            url:editId?me.updateUrl:me.createUrl,
            dataType:"json",
            type:"post",
            data:data,
            success:function(response){
                if(response.success){
                    Materialize.toast(config.messages.optSuccRedirect, 4000);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        });
    }
};
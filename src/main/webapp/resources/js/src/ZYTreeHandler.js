function ZYTreeHandler(params) {
    this.newDefaultName = params.newDefaultName;
    this.removeUrl=params.removeUrl;
    this.renameUrl=params.renameUrl;
    this.addUrl=params.addUrl;
    this.getNodes(function(data){
        params.init(data);
    });
}
ZYTreeHandler.prototype.getNodes=function(){
    return this.nodes;
}
ZYTreeHandler.prototype.getNodes=function(callback){
    $.ajax({
        dataType:"json",
        type:"post",
        url:config.ajaxUrls.categoryGetAll,
        success:function(response){
            var data=response.object;
            if(response.success){
                data.unshift({id:0,name:"品类",parentId:-1,open:true,isParent:true});
                callback(data);
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }
        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    })
};
ZYTreeHandler.prototype.getSettings = function () {
    var me = this;
    return {
        edit: {
            enable: true,
            editNameSelectAll: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        view: {
            expandSpeed: "",
            addHoverDom: function (treeId, treeNode) {
                var sObj = $("#" + treeNode.tId + "_span");

                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add' onfocus='this.blur();'></span>";
                sObj.after(addStr);
                var btn = $("#addBtn_" + treeNode.tId);
                if (btn) {
                    btn.bind("click", function () {

                        //这里讲数据发送给后台
                        me.add(treeNode);
                        return false;
                    });
                }
            },
            removeHoverDom: function (treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).unbind().remove();
            },
            selectedMulti: false
        },
        callback: {
            beforeRemove: function (treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("zyTree");
                zTree.selectNode(treeNode);
                return confirm("确认删除 -- " + treeNode.name + " 吗？");
            },
            onRemove: function (e, treeId, treeNode) {
                //这里讲数据发给后台
                me.remove(treeNode.id, treeNode.parentId);
            },
            onRename: function (e, treeId, treeNode) {
                me.rename(treeNode.id, treeNode.parentId, treeNode.name);
            },
            beforeRename: function (treeId, treeNode, newName) {
                if (newName.length == 0) {
                    Materialize.toast(config.validErrors.required,4000);
                    return false;
                }
                return true;
            },
            beforeDrag: function (treeId, treeNodes) {
                return false;
            }
        }
    }
};
ZYTreeHandler.prototype.remove = function (id, parentId) {

    functions.showLoading();
    var me=this;
    $.ajax({
        url:me.removeUrl,
        type:"post",
        data:{
            parentId:parentId,
            id:id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                Materialize.toast(config.messages.optSuccess, 4000);
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });

};
ZYTreeHandler.prototype.rename = function (id, parentId, name) {
    functions.showLoading();
    var me=this;
    $.ajax({
        url:me.renameUrl,
        type:"post",
        data:{
            name:name,
            parentId:parentId,
            id:id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                Materialize.toast(config.messages.optSuccess, 4000);
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });

};
ZYTreeHandler.prototype.add = function (treeNode) {
    functions.showLoading();
    var me=this;
    var no=(new Date()).getTime();
    $.ajax({
        url:me.addUrl,
        type:"post",
        data:{
            name:me.newDefaultName+no,
            parentId:treeNode.id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                Materialize.toast(config.messages.optSuccess, 4000);

                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if(treeNode.check_Child_State!=-1||treeNode.open==true){
                    zTree.addNodes(treeNode, {id:response.object, parentId:treeNode.id, name:me.newDefaultName +no,isParent:true});
                }else{
                    zTree.expandNode(treeNode);
                }

            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
};

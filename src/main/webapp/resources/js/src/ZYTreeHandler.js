function ZYTreeHandler(params) {
    this.newDefaultName = params.newDefaultName;
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

    var index=config.findInArray(this.data,"id",id);

    if(index!=-1){
        this.data.splice(index,1);
    }

    localStorage.setItem(this.keyName,JSON.stringify(this.data));
    Materialize.toast(config.messages.optSuccess, 4000);
};
ZYTreeHandler.prototype.rename = function (id, parentId, name) {
    var index=config.findInArray(this.data,"id",id);

    if(index!=-1){
        this.data[index].name=name;
    }

    localStorage.setItem(this.keyName,JSON.stringify(this.data));
    Materialize.toast(config.messages.optSuccess, 4000);
};
ZYTreeHandler.prototype.add = function (treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("zyTree");
    var no=(new Date()).getTime();
    var node={
        parentId: treeNode.id,
        isLeaf:true,
        name: this.newDefaultName + no
    };

    Materialize.toast(config.messages.optSuccess, 4000);

    zTree.expandNode(treeNode);
    zTree.addNodes(treeNode, node);
};

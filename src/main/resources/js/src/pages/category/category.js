$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        newDefaultName:"新结构",
        removeUrl:config.ajaxUrls.categoryDelete,
        renameUrl:config.ajaxUrls.categoryUpdate,
        addUrl:config.ajaxUrls.categoryCreate
    });
    treeHandler.getNodes(function(data){
        $.fn.zTree.init($("#zyTree"), treeHandler.getSettings(), data);
    });

});

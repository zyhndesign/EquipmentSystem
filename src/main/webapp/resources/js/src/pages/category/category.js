$(document).ready(function(){
    var treeHandler=new ZYTreeHandler({
        newDefaultName:"新结构",
        init:function(data){
            $.fn.zTree.init($("#zyTree"), treeHandler.getSettings(),data);
        }
    });
});

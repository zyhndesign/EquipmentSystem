$(document).ready(function() {
    
    $("#logoIcon").on("click", function() {
		console.log('click');
		
        
        if($(".zyLeft.zySideNav").hasClass("active")){
            $(".zyLeft.zySideNav").removeClass("active");
            functions.hideBlackout(".zySideNav");
        }else{
            $(".zyLeft.zySideNav").addClass("active");
            functions.showBlackout(".zySideNav");
        }
    
    });
    $(".zySideNav").on("click",function(evt){
        evt.stopPropagation()
    })
    $(".zySideNav>.zyBlackout").on("click",function(){
            $(".zyLeft.zySideNav").removeClass("active");
            functions.hideBlackout(".zySideNav");
    })
});
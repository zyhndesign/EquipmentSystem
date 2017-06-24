$(document).ready(function () {
    //根据页面名称初始化状态
    if (pageName) {
        var target = $("a[data-page-name='" + pageName + "']");
        if (target) {
            target.addClass("active");

            if (target.parents(".subMenu")) {
                target.parents(".subMenu").prev(".collapsible-header").addClass("active");
            }
        }
    }
    if(pageTitle){
        $(".zySubTitle").html(pageTitle)
    }
    

    $(".button-collapse").sideNav({
        menuWidth: 250, // Default is 300
        edge: 'left', // Choose the horizontal origin
        //closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
        draggable: true // Choose whether you can drag to open on touch screens
    });
    $('.collapsible').collapsible();



    $("input[type='text'],input[type='email']").blur(function () {
        $(this).val($(this).val().trim());
    });

    //火狐里面阻止form提交
    $("input[type='text'],input[type='password']").keydown(function (e) {
        if (e.keyCode == 13) {
            return false;
        }
    });

    /*$(".link").click(function () {
        var subMenu = $(this).parent().find(".subMenu");
        if (subMenu) {
            subMenu.toggle();
        }
    });*/

    $(".zySelect .zySSelect").change(function () {
        var parent = $(this).parent(".zySelect"),
            dataValue = $(this).find("option:selected").data("value") || $(this).val();
        if (dataValue) {
            if (parent.hasClass("zySelectHasImg")) {
                parent.find(".zySShow").attr("src", dataValue);
            } else {
                parent.find(".zySShow").css("background-color", dataValue);
            }
            parent.removeClass("zyNoSelect");
        } else {
            parent.addClass("zyNoSelect");
        }
    });



});

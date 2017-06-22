function ZYTableHandler(params) {
    this.removeUrl=params.removeUrl;
    this.ownTable = params.ownTable();
}
ZYTableHandler.prototype.tableRedraw = function () {
    this.ownTable.fnSettings()._iDisplayStart = 0;
    this.ownTable.fnDraw();
};
ZYTableHandler.prototype.delete = function (param) {

    if(!confirm(config.messages.confirmDelete)){
        return ;
    }

    functions.showLoading();
    var me = this;
    $.ajax({
        url: me.removeUrl,
        type: "post",
        data:param,
        dataType: "json",
        success: function (response) {
            if (response.success) {
                Materialize.toast(config.messages.optSuccess, 4000);
                me.ownTable.fnDraw();
                functions.hideLoading();
            } else {
                functions.ajaxReturnErrorHandler(response.error_code);
            }

        },
        error: function () {
            functions.ajaxErrorHandler();
        }
    });
};

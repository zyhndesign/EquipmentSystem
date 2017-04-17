/**
 * Created with JetBrains WebStorm.
 * User: ty
 * Date: 14-10-13
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
var functions = (function (config) {
    return {
        /**
         * 格式化日期
         * @param format {string} 格式化
         * y:四位数的年份
         * m:两位数的月份
         * d:两位数的日期
         * h:两位数的时间
         * i:两位数的分钟
         * s:两位数的秒钟
         * 支持格式:
         * @param dateTime {number} 毫秒表示的日期
         * @returns string {string}格式好的字符串日期，默认是2014-09-10 12:03:23格式
         */
        formatDate:function(format,dateTime){
            var string,currentDate,year,month,day, h, m, s,fYear,fMonth,fDay,fH,fM,fS;

            if(typeof format ==="number"){
                dateTime=format;
                format=null;
            }

            currentDate =dateTime?new Date(dateTime):new Date();
            fYear=currentDate.getFullYear();
            year=fYear.toString().slice(2);
            fMonth=month=currentDate.getMonth()+1;
            fDay=day=currentDate.getDate();
            fH=h=currentDate.getHours();
            fM=m=currentDate.getMinutes();
            fS=s=currentDate.getSeconds();

            if(fMonth<10){
                fMonth="0"+fMonth;
            }
            if(fDay<10){
                fDay="0"+fDay;
            }
            if(fH<10){
                fH="0"+fH;
            }
            if(fM<10){
                fM="0"+fM;
            }
            if(fS<10){
                fS="0"+fS;
            }

            switch(format){
                case "y-m-d":
                    string=fYear+"-"+fMonth+"-"+fDay;
                    break;
                case "y-m-d h:m":
                    string=fYear+"-"+fMonth+"-"+fDay+" "+fH+":"+fM;
                    break;
                case "y/m/d h:m:s":
                    string=fYear+"/"+fYear+"/"+day+" "+fH+":"+fH+":"+fH;
                    break;
                case "y/m/d h:m":
                    string=fYear+"/"+fMonth+"/"+fDay+" "+fH+":"+fM;
                    break;
                default :
                    string=fYear+"-"+fMonth+"-"+fDay+" "+fH+":"+fM+":"+fS;
                    break;
            }

            return string;
        },
        /**
         * 显示loading遮盖层
         */
        showLoading: function () {
            $("#loading").removeClass("hidden");
        },
        /**
         * 隐藏loading遮盖层
         */
        hideLoading: function () {
            $("#loading").addClass("hidden");
        },
        /**
         * ajax网络错误处理
         */
        ajaxErrorHandler: function () {
            this.hideLoading();
            Materialize.toast(config.messages.networkError, 4000);
        },
        /**
         * ajax后台返回错误处理
         * @param errorCode {string} 错误代码
         */
        ajaxReturnErrorHandler: function (errorCode) {
            var me = this;
            var message = "";
            switch (errorCode) {
                default :
                    message = config.messages.systemError;
                    break;
            }
            this.hideLoading();
            Materialize.toast(message, 4000);
        },

        /**
         * plupload版本1.2.1,采用all版本，需要使用qiniu.js
         * @param params
         * @returns {*}
         */
        createQiNiuUploader:function(params){
            var uploader = Qiniu.uploader({
                runtimes: 'html5',    //上传模式,依次退化
                browse_button: params.uploadBtn,       //上传选择的点选按钮，**必需**
                uptoken_url:  config.uploader.qiNiu.upTokenUrl,
                multi_selection:params.multiSelection,
                domain: config.uploader.qiNiu.uploadDomain,
                container: params.uploadContainer,//上传区域DOM ID，默认是browser_button的父元素，
                filters: {
                    mime_types : [
                        { title : "media files", extensions : params.filter }
                    ]
                    //max_file_size:'1m'
                },
                multipart_params:params.multipartParams,
                max_file_size: params.maxSize,    //最大文件体积限制,qiniu中需要写在这里，而不是卸载filters中
                max_retries: 3,                   //上传失败最大重试次数
                chunk_size: '4mb',                //分块上传时，每片的体积
                auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
                init: {
                    'Init':function(up,info){
                        //console.log(up.getOption("max_file_size"));
                    },
                    'FilesAdded': function(up, files) {
                        if(typeof params.filesAddedCb ==="function"){
                            params.filesAddedCb(files,up);
                        }
                    },
                    'BeforeUpload':function(up,file){

                    },
                    'UploadProgress': function(up, file) {
                        if(typeof params.progressCb ==="function"){
                            params.progressCb(file);
                        }
                    },
                    'FileUploaded': function(up, file, info) {
                        if(typeof params.uploadedCb === "function"){
                            var response = JSON.parse(info);
                            response.url=config.uploader.qiNiu.bucketDomain + response.key;
                            params.uploadedCb(response,file,up);
                        }
                    },
                    'Error': function(up, err, errTip) {
                        Materialize.toast(errTip, 4000);

                        up.refresh();
                    },
                    'Key': function(up, file) {

                        // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                        // 该配置必须要在 unique_names: false , save_key: false 时才生效
                        var random=Math.floor(Math.random()*10+1)*(new Date().getTime());
                        var filename=file.name;
                        var extPos=filename.lastIndexOf(".");


                        // do something with key here
                        return random+filename.substring(extPos);

                        //return file.name;
                    }
                }
            });

            return uploader;
        }
    }

})(config);

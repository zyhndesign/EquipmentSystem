var ZYCtrlDataHandler={
    getDataForUpdate:function(url,data,callback){
        $.ajax({
            dataType:"json",
            type:"get",
            url:url,
            data:data,
            success:function(response){
                if(response.success){
                    if(callback){
                        callback(response.object);
                    }
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })
    },
    generateCategoryItems:function(list,idFlag,callback){
        var arr=[],stringOption,stringCheckbox;
        idFlag=idFlag||"";

        for(var i=0,len=list.length;i<len;i++){
            if(list[i].parentId===0){
                arr.push(list[i]);
            }
        }

        stringOption=juicer(config.categoryAllOptionTpl,{
            idFlag:idFlag,
            items:arr
        });

        stringCheckbox=juicer(config.categoryAllCheckboxTpl,{
            idFlag:idFlag,
            items:arr
        });

        if(callback){
            callback(stringOption,stringCheckbox);
        }

    },
    getCategoryFirstLevelItems:function(idFlag,callback){
        var me=this;
        if(me.categoryData){
            this.generateCategoryItems(me.categoryData,idFlag,callback);

            return ;
        }

        $.ajax({
            dataType:"json",
            type:"post",
            url:config.ajaxUrls.categoryGetAll,
            success:function(response){
                if(response.success){
                    me.categoryData=response.object;
                    me.generateCategoryItems(me.categoryData,idFlag,callback);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })
    },
    generateCategoryGroupOptions:function(list,callback){
        var items=[],item,string,
            parentIndexMap={};

        for(var i=0,len=list.length;i<len;i++){
            item=list[i];

            if(item.id===0){
                continue;
            }
            if(item.parentId===0){
                item.childs=[];
                items.push(item);
                parentIndexMap[item.id]=items.length-1;

                continue;
            }

            if(item.id===0&&item.parentId!==0){
                items[parentIndexMap[item.parentId]].childs.push(item);
            }
        }

        string = juicer(config.categoryTreeTpl,{
            items:items
        });

        if(callback){
            callback(string);
        }
    },
    getCategoryGroupOptions:function(callback){
        var me=this;
        if(me.categoryData){
            me.generateCategoryGroupOptions(me.categoryData,callback);
            return ;
        }

        $.ajax({
            dataType:"json",
            type:"post",
            url:config.ajaxUrls.categoryGetAll,
            success:function(response){
                if(response.success){
                    me.categoryData=response.object;
                    me.generateCategoryGroupOptions(me.categoryData,callback);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })

    },
    generateBrandItems:function(list,idFlag,callback){
        var stringOption,stringCheckbox;
        idFlag=idFlag||"";

        stringOption=juicer(config.brandAllOptionTpl,{
            idFlag:idFlag,
            items:list
        });
        stringCheckbox=juicer(config.brandAllCheckboxTpl,{
            idFlag:idFlag,
            items:list
        });

        if(callback){
            callback(stringOption,stringCheckbox);
        }
    },
    getBrandItems:function(idFlag,callback){
        var me=this;
        if(me.brandData){
            me.generateBrandItems(me.brandData,idFlag,callback);
            return ;
        }
        $.ajax({
            dataType:"json",
            type:"post",
            url:config.ajaxUrls.brandGetAll,
            success:function(response){
                if(response.success){
                    me.brandData=response.object;
                    me.generateBrandItems(me.brandData,idFlag,callback);

                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })

    },
    generateTextureItems:function(list,idFlag,callback){
        var stringOption,stringCheckbox;
        idFlag=idFlag||"";

        stringOption=juicer(config.textureAllCheckboxTpl,{
            idFlag:idFlag,
            items:list
        });
        stringCheckbox=juicer(config.brandAllCheckboxTpl,{
            idFlag:idFlag,
            items:list
        });

        if(callback){
            callback(stringOption,stringCheckbox);
        }
    },
    getTextureItems:function(idFlag,callback){
        var me=this;
        if(me.textureData){
            me.generateTextureItems(me.textureData,idFlag,callback);
            return ;
        }

        $.ajax({
            dataType:"json",
            type:"post",
            url:config.ajaxUrls.textureGetAll,
            success:function(response){
                if(response.success){
                    me.textureData=response.object;
                    me.generateTextureItems(me.textureData,idFlag,callback);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })

    },
    generateColorItems:function(list,idFlag,callback){
        var stringOption,stringCheckbox;
        idFlag=idFlag||"";

        stringOption=juicer(config.colorAllOptionTpl,{
            idFlag:idFlag,
            items:list
        });
        stringCheckbox=juicer(config.colorAllOptionTpl,{
            idFlag:idFlag,
            items:list
        });

        if(callback){
            callback(stringOption,stringCheckbox);
        }
    },
    getColorItems:function(type,idFlag,callback){
        var me=this;
        if(me.colorData){
            me.generateColorItems(me.colorData,idFlag,callback);
            return ;
        }
        $.ajax({
            dataType:"json",
            type:"post",
            url:config.ajaxUrls.textureGetAll,
            success:function(response){
                if(response.success){
                    me.colorData=response.object;
                    me.generateColorItems(me.colorData,idFlag,callback);
                }else{
                    functions.ajaxReturnErrorHandler(response.message);
                }
            },
            error:function(){
                functions.ajaxErrorHandler();
            }
        })

    },
    /**
     * 计算实际image的尺寸和margin
     *  customData中x，y记录的是百分比
     *  boundW,boundH是裁剪时原图的大小
     *  ratio是裁剪的形状的w和h比值
     *  原理：固定高度
     *  当前裁剪框的高度是div的高度，和原始的裁剪高度的比值乘以boundH就是当前image的高度
     *  宽度的计算原理一样
     * @param showHeight
     * @param customData
     * @returns {{realW: number, realH: number, marginL: number, marginT: number}}
     */
    computeImageCss:function(showHeight,customData){
        var ratio=showHeight/customData.h,
            realW=ratio*customData.boundW,realH=ratio*customData.boundH,
            marginL=-customData.x*realW,marginT=-customData.y*realH;

        return {
            showHeight:showHeight,
            showWidth:showHeight*customData.ratio,
            realW:realW,
            realH:realH,
            marginL:marginL,
            marginT:marginT
        }
    },
    getCutImageEl:function(sizeH,customData){
        var imageCssObj,imageString;

        imageCssObj=this.computeImageCss(sizeH,customData);
        imageCssObj.src=customData.src;
        imageString=juicer(config.cutImageTpl,imageCssObj);


        return imageString;
    }

};

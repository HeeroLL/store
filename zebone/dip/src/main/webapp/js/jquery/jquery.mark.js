$.extend($.fn,{
    mask: function(msg,maskDivClass){
        this.unmask();
        // 参数
        var op = {
            opacity: 0.4,
            z: 10000,
            bgcolor: '#999'
        };
        var original=$(document.body);
        var position={top:0,left:0};
                    if(this[0] && this[0]!==window.document){
                        original=this;
                        position=original.position();
                    }
        // 创建一个 Mask 层，追加到对象中
        var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
        var maskWidth=original.outerWidth();
        if(!maskWidth){
            maskWidth=original.width();
        }
        var maskHeight=original.outerHeight();
        if(!maskHeight){
            maskHeight=original.height();
        }
        maskDiv.css({
            position: 'absolute',
            top: position.top,
            left: position.left,
            //'z-index': op.z,
            width: maskWidth,
            height:maskHeight,
            'background-color': op.bgcolor,
			opacity: op.opacity
        });
        maskDiv.appendTo(original);
        if(maskDivClass){
            maskDiv.addClass(maskDivClass);
        }
        if(msg){
            var msgDiv=$('<div class="maskdivgenMessage" style="position:absolute;border:#6593cf 1px solid; padding:2px;background:#ccca;"><div style="line-height:32px;border:#a3bad9 1px solid;padding:2px 10px 2px 35px; background: #FFFFFF url(\'images/loading.gif\') no-repeat;font-size: 12px;">'+msg+'</div></div>');
            //msgDiv.appendTo(maskDiv);
            msgDiv.appendTo(original);
            //var otherWidth = $(document.body).width() - maskDiv.width();
            //var otherHeight = $(document.body).height() - maskDiv.height();
            var widthspace=(maskDiv.width()-msgDiv.width());
            var heightspace=(maskDiv.height()-msgDiv.height());
            msgDiv.css({
                        cursor:'wait',
                        top:(heightspace/2-2) +position.top,
                        left:(widthspace/2-2)  + position.left
              });
          }
//          maskDiv.fadeIn('fast', function(){
//            // 淡入淡出效果
//            $(this).fadeTo('slow', op.opacity);
//        })
        return maskDiv;
    },
 unmask: function(){
             var original=$(document.body);
                 if(this[0] && this[0]!==window.document){
                    original=$(this[0]);
              }
              original.find("> div.maskdivgen").fadeOut('fast',0,function(){
                  $(this).remove();
              });
              original.find("> div.maskdivgenMessage").fadeOut('fast',0,function(){
                  $(this).remove();
              });
    }
});
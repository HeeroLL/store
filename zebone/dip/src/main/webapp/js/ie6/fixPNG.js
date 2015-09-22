function fixPng() {
var arVersion = navigator.appVersion.split("MSIE")
var version = parseFloat(arVersion[1])

if ((version >= 5.5 && version < 7.0) && (document.body.filters)) {
    for(var i=0; i<document.images.length; i++) {
      var img = document.images[i];
      var imgName = img.src.toUpperCase();
      if (imgName.indexOf(".PNG") > 0) {
        var width = img.width;
        var height = img.height;
        var sizingMethod = (img.className.toLowerCase().indexOf("scale") >= 0)? "scale" : "image";
        img.runtimeStyle.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img.src.replace('%23', '%2523').replace("'", "%27") + "', sizingMethod='" + sizingMethod + "')";
        img.src = "images/blank.gif";
        img.width = width;
        img.height = height;
        }
      }
    }
}

fixPng();
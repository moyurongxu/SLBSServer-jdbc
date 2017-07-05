//点击时候显示子菜单
$("#menu").click(function(){
	if ($(this).hasClass('up')) {
      $(this).removeClass('up');
      $(this).addClass('down');
      var objc = $(this).next().get(0);
      alphaPlay(objc, "show");
    } else if ($(this).hasClass('down')) {
      $(this).removeClass('down');
      $(this).addClass('up');
      var objc = $(this).next().get(0);
      alphaPlay(objc, "hiden");
    }
});
//控制是隐藏还是显示子菜单
function alphaPlay(obj,method){
	var n = (method == "show") ? 0 : 100;
	//判断是否是ie
	var ie = (window.ActiveXObject)?true:false;
	if(method=='show'){
		if(n<100){
			n+=10;
			if (ie) {
				obj.style.cssText= "filter:alpha(opacity=" + n + ")";
			} else{
				(n == 100) ? obj.style.opacity = 1: obj.style.opacity = "0." + n;
			}
		}else{
          	obj.style.display = "block";
		}
	}else{
		if (n > 0) {
        	n -= 10;
        	if (ie) {
          		bj.style.cssText = "filter:alpha(opacity=" + n + ")";
          	} else {
          		obj.style.opacity = "0." + n;
          	}
		} else {
    		clearTimeout(time);
	    	bj.style.display = "none";
        }
	}
}

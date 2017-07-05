//function dealWithAjaxData(e, o) {
//  if (common.log(e, o), o.success) return ! 0;
//  switch ("" + o.errorCode) {
//  case "40001":
//      reLogin();
//      break;
//  case "42032":
//      common.wechatAuthorize();
//      break;
//  }
//  return ! 1
//}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}



AJAXFlag = !0;
var common = {
    isDebug: !1,
//  getApi: function(e) {
////      var o = parseInt(getCookie("BackendPort"));
//      return "http://localhost:8080/bishe/" + "/" + e;
//  },
    ifNull:function(e) {
    	if( typeof(e) !== "undefined" && e !== null  && e !== ''){
    		return true;
    	}
    },
    invokeApi: function(e, o, n, t, i, r) {
//      if (alert("url: " + o), AJAXFlag) { (null === t || void 0 === t) && (t = function() {}),
//          (null === i || void 0 === i) && (i = function() {}),
//          (null === r || void 0 === r) && (r = function() {});
            var a = {
                url: "http://localhost:8080/SLBSserver-jdbc/" + o,
                type: e,
                xhrFields: {
                    withCredentials: !0
                },
                dataType: "json",
                beforeSend: t,
                success: function(e) {
//                    alert("success data: " + JSON.stringify(e).substr(0, 200))
                	console.log("success data: " + JSON.stringify(e).substr(0, 200))
                	console.log(e.success)
                	if (e.success) {
                		i(e)
                	} else{
                		r(e)
                	}
                      
                },
                error: function(e) {
//                    alert("error data: " + JSON.stringify(e));
                	if(common.ifNull(e.message)){
                		r(e)
                	}
                    //commonui.hideAjaxLoading();
       	    		//commonui.initPage();
                }
            };
            null !== n && void 0 !== n && (a.data = JSON.stringify(n), ("PUT" == e || "POST" == e) && (a.contentType = "application/json; charset=UTF-8")),
            
           
            $.ajaxSetup({     
                contentType:"application/x-www-form-urlencoded;charset=utf-8",     
             complete:function(XMLHttpRequest,textStatus){     
                   var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，  
                     if(sessionstatus=="timeout"){     
                           // 如果超时就处理 ，指定要跳转的页面     
                       alert("页面过期，请重新登录");   
                           window.top.location.href="/SLBSserver/logins.html";  
                          }    
                     if(sessionstatus=="repeatlogin"){     
                           alert("您的帐号已在其他机器登录，请重新登录");    
                           window.top.location.href="/SLBSserver/logins.html";     
                           }  
                        }       
                     }   
                );
            $.ajax(a);
   }
}
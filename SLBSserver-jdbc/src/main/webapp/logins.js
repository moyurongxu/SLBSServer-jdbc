avalon.ready(function() {
	
		function login() {
			var n = "POST",
		    a = "user/login",
		    i = {
					name:o.userid,
					password:o.password,
			},
		    e = function(n) {
		    		alert("登陆成功！");
		    		window.location.href="forum/forumhome.html";
		    },
		    r = function(n) {
				alert(n.message);
		    };
		    common.invokeApi(n, a, i, null, e, r)
		}
    o = avalon.define({
        $id: "root",
        userid:"",
        password:"",
        submit : function(){
        	if(o.userid != "" && o.password !=""){
        		document.getElementById("login_tip").innerHTML = "登录中...";
        		login()
        	}else{
        		alert("账号或密码不能为空！")
        	}
        },
        
        reset: function(){
        	o.userid="",
        	o.password=""
        },
    });
    avalon.scan(document.body);
    $(function(){
	  var d="<div class='snow'>❅<div>"
		setInterval(function(){
			var f=$(document).width();
			var e=Math.random()*f-100;
			var o=0.3+Math.random();
			var fon=10+Math.random()*30;
			var l = e - 100 + 200 * Math.random();
			var k=2000 + 5000 * Math.random();
			$(d).clone().appendTo(".snowbg").css({
				left:e+"px",
				opacity:o,
				"font-size":fon,
			}).animate({
			  top:"400px",
				left:l+"px",
				opacity:0.1,
			},k,"linear",function(){$(this).remove()})
		},200)
	})
});
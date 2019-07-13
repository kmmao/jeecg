<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title><t:mutiLang langKey="jeect.platform"/></title>
   <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
  <link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
  <link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
  <!-- text fonts -->
  <link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

  <link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
  <!-- ace styles -->
  <link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
  
  <![endif]-->

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
  <![endif]-->
  <!-- ace settings handler -->
  <style>
    .form-control{
      border: none;
    }
     input[type="text"]{
    border: none;
    }
    input[type="password"]{
      border: none;
    }
    .login-layout label{
      margin-bottom: 0px;
      border-bottom: 1px solid #e1e1e1;
    }
    .inline{
      border-bottom: none!important;
    }
    .inline .lbl a{
      font-size: 12px;
    color: #858585;
    }
    span.input-icon {
      display: inline-block;
    width: 295px;
    }
    @media (min-width: 768px){
    .col-sm-offset-1 {
      margin-top: 92px;
      }
    }
    .input-icon .ace-icon img {
      width: 12px;
    float: left;
    position: absolute;
    top: -13px;
    bottom: 1px;
    left: 3px;
    line-height: 30px;
    }
  </style>
 
  <script src="plug-in/ace/js/ace-extra.js"></script>

  <!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->

</head>
<body class="login-layout light-login">
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
          <div class="center">
              <p><img src="plug-in/ace/css/images/logo.png" alt=""></p>
              <!-- <h1 id="id-text2" class="grey">
                <i class="ace-icon fa fa-leaf green"></i>
                 JEECG 演示系统
              </h1> -->
              <!-- <h4 class="blue" id="id-company-text">www.jeecg.org</h4> -->
            </div>
        <div class="login-container">
          <div class="bj_left">
              <img src="plug-in/ace/css/images/hrp.png" alt="">  
          </div>
          <!-- <div class="space-6"></div> -->
          <!-- <div class="position-relative"> -->
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <form id="loinForm" class="form-horizontal"    method="post">
                <!-- 单点登录参数 -->
                <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>
                <div class="widget-main">
                 <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <div id="showErrMsg"></div>
				</div>
                  <h4 class="header blue lighter bigger">
                    <!-- <i class="ace-icon fa fa-coffee green"></i> -->
                    医院综合运营管理平台
                  </h4>
                  <!-- <div class="space-6"></div> -->
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="text"  name="userName" iscookie="true" class="form-control" placeholder="请输入用户名"  id="userName" value="admin"/>
									<span class="ace-icon"><img src="plug-in/ace/css/images/login.png" alt=""></span>
								</span>
                      </label>
                      <label class="block clearfix" style="margin-top: 15px">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" value="123456"/>
									<span class="ace-icon"><img src="plug-in/ace/css/images/mima.png" alt=""></span>
								</span>
                      </label>
                      <label class="block clearfix" style="margin-top: 11px;padding-bottom: 9px;">
                        <div class="input-group">
                          <input type="text" style="width:150px" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>
                          <span class="input-group-addon" style="padding: 0px;"><img id="randCodeImage" src="randCodeImage"  /></span>
                        </div>
                      </label>
                      <!-- <div class="space"></div> -->
                      <div class="clearfix">
                        <label class="inline">
                          <!-- <input type="checkbox" class="ace" id="on_off"  name="remember" value="yes"/> -->
                          <img class="ace" id="on_off" src="plug-in/ace/css/images/no_pit.png" />
                          <span class="lbl"><a style="margin-left: 10px;">记住密码</a></span>
                        </label>
                        <!-- <span> | <a href="http://demo.jeecg.org/mLoginController.do?login&from=singlemessage&isappinstalled=0"><i class="ace-icon fa fa-location-arrow"></i><font color='#428bca'>移动OA</font></a></span> -->
                         <!-- <span> | <a href="http://yun.jeecg.org" target="_blank"><i class="ace-icon fa fa-cube"></i><font color='#428bca'>插件中心</font></a></span> -->
                      
                        <!-- <a href="loginController.do?goResetPwdMail" class="btn btn-link">忘记密码 ?</a> -->
                      </div>
                      <div class="button_wrap">
                          <button type="button" id="but_login"  onclick="checkUser()" class="width-35 pull-right btn btn-sm btn-primary">
                              <!-- <i class="ace-icon fa fa-key"></i> -->
                              <span class="bigger-110" >登录</span>
                            </button>
                      </div>
                      <!-- <div class="space-4"></div> -->

                </div>
                <!-- <div class="toolbar clearfix">
                  <div style="float: right">
                    <a href="#"  class="forgot-password-link">
                    	  语言
                      <i class="ace-icon fa fa-arrow-right"></i>
                      <t:dictSelect id="langCode" field="langCode" typeGroupCode="lang" hasLabel="false" extendJson="{style:'padding:2px; width:80px;'}" defaultVal="zh-cn"></t:dictSelect>
                    </a>
                  </div>
                </div> -->
                </form>
              </div>
            </div>
            <!-- <div class="center"><h4 class="blue" id="id-company-text">&copy; JEECG版权所有 v_3.8</h4></div> -->
            <!-- <div class="navbar-fixed-top align-right">
              <br />
              &nbsp;
              <a id="btn-login-dark" class="blue" href="#" onclick="darkStyle()">Dark</a>
              &nbsp;
              <span class="blue">/</span>
              &nbsp;
              <a id="btn-login-blur" class="blue" href="#" onclick="blurStyle()">Blur</a>
              &nbsp;
              <span class="blue">/</span>
              &nbsp;
              <a id="btn-login-light" class="blue" href="#" onclick="lightStyle()">Light</a>
              &nbsp; &nbsp; &nbsp;
            </div> -->
              <!-- </div> -->
            </div>
            <div class="wrap_footer">
              <img src="plug-in/ace/css/images/footer.png" alt="">
            </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="webpage/login/login-ace.js"></script>
<%=lhgdialogTheme %>
<script>  
    //记住密码复选框点击切换
    var toggle = true;
    $("#on_off").on('click', function() {
      // alert(2);
      if (toggle) {
        $("#on_off").attr("src", "plug-in/ace/css/images/yes_pit.png")
    
        toggle = false;
      } else {
        $("#on_off").attr("src", "plug-in/ace/css/images/no_pit.png")
        toggle = true;
      }
    });
    </script>
<script type="text/javascript">
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();

   //输入验证码，回车登录
  $(document).bind('keyup', function(event) {
	　　if (event.keyCode == "13") {
	　　　　$('#but_login').click();
	　　}
  });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }
  
  /**
   * 刷新验证码
   */
  $('#randCodeImage').click(function(){
	    reloadRandCodeImage();
  });
	
</script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?098e6e84ab585bf0c2e6853604192b8b";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</body>
</html>
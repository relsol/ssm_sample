<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Login Page - Ace Admin</title>

    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/static/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path}/static/assets/css/font-awesome.min.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="${path}/static/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${path}/static/assets/css/ace.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${path}/static/assets/css/ace-part2.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="${path}/static/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${path}/static/assets/css/ace-ie.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="${path}/static/assets/css/ace.onpage-help.css" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${path}/static/assets/js/html5shiv.js"></script>
    <script src="${path}/static/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-layout">
<form id="loginform" action="${path}/login" method="post">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="red">Ace</span>
                            <span class="white" id="id-text2">Application</span>
                        </h1>
                        <h4 class="blue" id="id-company-text">&copy; Company Name</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        Please Enter Your Information
                                    </h4>

                                    <div class="space-6"></div>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" id="loginName" name="loginName" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" id="password" name="password" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" />
                                                    <span class="lbl"> Remember Me</span>
                                                </label>

                                                <button onclick="doLogin()" type="button" class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">Login</span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                </div><!-- /.widget-main -->

                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->
                    </div><!-- /.position-relative -->

                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
</form>
<!-- basic scripts -->
<script src="${path}/static/js/jquery/jquery-1.9.1.min.js"></script>
<script src="${path}/static/js/jquery/jquery.form.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">

    $(function(){
        $("#loginName").focus();
        $('#loginName, #password').keyup(function(eventObject){
            if(13 == eventObject.keyCode){
                doLogin();
            }
        });

        if(window.parent.length>0)
            window.parent.location=location;
    });

    function doLogin(){
        var options = {
            type:'post',
            dataType:'json',
            beforeSubmit:function(){
                var loginName = $("#loginName").val();
                var password = $("#password").val();
                if (!loginName) {
                    alert('请输入用户名!');
                    return false;
                }
                if(!password){
                    alert('请输入密码!');
                    return false;
                }
                return true;
            },
            success:function(json){
                if(!json.success){
                    alert(json.msg);
//                    $('#errorMsg').html(json.msg);
                    return;
                }
//                $('#errorMsg').html('登录成功,页面跳转中.');
//                if(json.data.requestUrl){
//                    location.href=json.data.requestUrl;
//                } else {
                    location.href='${path}/home';
//                }
            },
            error:function(){
                alert('请求服务器失败,请检查网络!');
            }
        };

        $('#loginform').ajaxSubmit(options);
        return false;
    }

</script>
</body>
</html>

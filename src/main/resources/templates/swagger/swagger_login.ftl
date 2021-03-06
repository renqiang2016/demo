<!DOCTYPE html>
<html lang="en">
<#assign ctx=request.getContextPath()>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="mobile service monitor">
    <meta name="author" content="yufei.liu">
    <link rel="shortcut icon" href="${ctx!}/favicon.ico">
    <title>Phicomm Account</title>
    <!-- Bootstrap Core CSS -->
    <link href="${ctx!}/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${ctx!}/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${ctx!}/dist/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${ctx!}/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx!}/ie/html5shiv.js"></script>
    <script src="${ctx!}/ie/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Username" name="username" type="text"
                                       id="username" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password"
                                       id="password" value="" required autocomplete="">
                            </div>
                            <div class="form-group" id="verifyCodeContain">
                                <input class="form-control" placeholder="Verify Code" maxlength="4" name="verify code"
                                       type="text" id="verifyCode" style="float: left;width: 61%">
                                <img src="${ctx!}/swagger/verify/code" id="verifyCodeImage" style="float: right"
                                     onclick='freshVerifyCode()'>
                            </div>
                            <div class="form-group" style="height: 37px">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a class="btn btn-lg btn-success btn-block" onclick="login()">Login</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${ctx!}/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${ctx!}/bootstrap/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${ctx!}/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${ctx!}/dist/sb-admin-2.js"></script>
<script>
    order = 1;
    lastFreshTime = new Date();
    function login() {
        var username = $("#username").val();
        if (username.length < 1) {
            $("#username").focus();
            return;
        }
        var password = $("#password").val();
        if (password.length < 1) {
            $("#password").focus();
            return;
        }
        try {
            window.btoa(password);
        } catch (error) {
            $("#password").val("");
            return;
        }
        var verifyCode = $("#verifyCode").val();
        if (verifyCode.length < 1) {
            $("#verifyCode").focus();
            return;
        }
        $.ajax({
            type: "POST",
            url: "${ctx!}/swagger/login/check",
            dataType: "json",
            data: {
                "username": username,
                "password": password,
                "verifyCode": verifyCode
            }, success: function (data) {
                console.log(data);
                if (data === 0) {
                    window.location.href = "${ctx!}/swagger-ui.html";
                } else {
                    $("#password").val("");
                    $("#verifyCode").val("");
                    freshVerifyCode(true);
                }
            }
        });
    }
    function freshVerifyCode() {
        $("#verifyCodeImage").remove();
        var html = "<img src='${ctx!}/swagger/verify/code?#seed#' id='verifyCodeImage' style='float: right' onclick='freshVerifyCode()'>";
        html = html.replace(/#seed#/g, order++);
        $("#verifyCodeContain").append(html);
    }
</script>
</body>
</html>
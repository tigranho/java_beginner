<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
<title>Օֆերտա Ադմին</title>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Sign In | Օֆերտա Ադմին</title>
    <!-- Favicon-->
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet"
          type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="plugins/node-waves/waves.css" rel="stylesheet"/>

    <!-- Animation Css -->
    <link href="plugins/animate-css/animate.css" rel="stylesheet"/>

    <!-- Custom Css -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="login-page">
<div class="login-box">
    <div class="logo">
        <a href="javascript:void(0);">Օֆերդա<b> Ադմին</b></a>

    </div>
    <div class="card">
        <div class="body">
            <%
                if (request.getAttribute("message") != null) {
            %>
            <%=request.getAttribute("message")%><br />
            <%
                }
            %>

            <form action="<%=request.getContextPath()%>/AdminSignInCheck" method="post">
                <div class="msg">Մուտք գործեք, վերահսկելու համար</div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">person</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="username" placeholder="Օգտագործողի ծածկանուն" required
                               autofocus>
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                    <div class="form-line">
                        <input type="password" class="form-control" name="password" placeholder="Գաղտնաբառ" required>
                    </div>
                </div>
                <div class="row">
                    <%-- <div class="col-xs-8 p-t-5">
                         <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                         <label for="rememberme">Remember Me</label>
                     </div>--%>
                    <div class="col-xs-4">
                        <button class="btn btn-block bg-pink waves-effect" type="submit">ՄՈՒՏՔ </button>
                    </div>
                </div>
                <div class="row m-t-15 m-b--20">
                    <%-- <div class="col-xs-6">
                         <a href="sign-up.html">Register Now!</a>
                     </div>
                     <div class="col-xs-6 align-right">
                         <a href="forgot-password.html">Forgot Password?</a>
                     </div>--%>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Jquery Core Js -->
<script src="plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="plugins/bootstrap/js/bootstrap.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="plugins/node-waves/waves.js"></script>

<!-- Validation Plugin Js -->
<script src="plugins/jquery-validation/jquery.validate.js"></script>

<!-- Custom Js -->
<script src="js/admin.js"></script>
<script src="js/pages/examples/sign-in.js"></script>
</body>
</html>


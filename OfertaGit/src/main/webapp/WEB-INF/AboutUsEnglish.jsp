<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

   <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet"
              type="text/css">

        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Օֆերդա - Հիմնական վարիչ</title>
        <!-- Favicon-->
        <link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon">
        <!-- Bootstrap Core Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/node-waves/waves.css" rel="stylesheet"/>

        <!-- Animation Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/animate-css/animate.css" rel="stylesheet"/>

        <!-- Colorpicker Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css"
              rel="stylesheet"/>

        <!-- Dropzone Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/dropzone/dropzone.css" rel="stylesheet">

        <!-- Multi Select Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/multi-select/css/multi-select.css" rel="stylesheet">

        <!-- Bootstrap Spinner Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/jquery-spinner/css/bootstrap-spinner.css"
              rel="stylesheet">

        <!-- Bootstrap Tagsinput Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
              rel="stylesheet">

        <!-- Bootstrap Select Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-select/css/bootstrap-select.css"
              rel="stylesheet"/>

        <!-- noUISlider Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/nouislider/nouislider.min.css" rel="stylesheet"/>

        <!-- Custom Css -->
        <link href="<%=request.getContextPath()%>/admin/css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="<%=request.getContextPath()%>/admin/css/themes/all-themes.css" rel="stylesheet"/>

</head>
<body class="theme-deep-purple">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="preloader">
            <div class="spinner-layer pl-red">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
        <p>Խնդրում ենք սպասել...</p>
    </div>
</div>
<!-- #END# Page Loader -->
<!-- Overlay For Sidebars -->
<div class="overlay"></div>
<!-- #END# Overlay For Sidebars -->
<!-- Search Bar -->
<div class="search-bar">
    <div class="search-icon">
        <i class="material-icons">search</i>
    </div>
    <input type="text" placeholder="START TYPING...">
    <div class="close-search">
        <i class="material-icons">close</i>
    </div>
</div>
<!-- #END# Search Bar -->
<!-- Top Bar -->
<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse"
               data-target="#navbar-collapse" aria-expanded="false"></a>
            <a href="javascript:void(0);" class="bars"></a>
            <a class="navbar-brand" href="">Օֆերդա - Հիմնական Ադմին</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">

        </div>
    </div>
</nav>
<!-- #Top Bar -->
<section>
    <!-- Left Sidebar -->
    <aside id="leftsidebar" class="sidebar">
        <!-- User Info -->
        <div class="user-info">
            <div class="image">
                <%--<img src="<%=request.getAttribute("adminPhoto")%>" width="100" height="100"
                     alt="<%=request.getAttribute("username")%>"/>--%>
            </div>
            <div class="info-container">
                <div class="name" data-toggle="dropdown" aria-haspopup="true"
                     aria-expanded="false">Բարի օր <%=request.getAttribute("username")%>
                </div>
                <%--<div class="email"><%=request.getAttribute("adminPhoneNumber")%>--%>
            </div>

        </div>
        </div>
        <!-- #User Info -->
        <!-- Menu -->
        <jsp:include page="/include/admin_menu.jsp"/>
        <!-- #Menu -->
        <!-- Footer -->
        <div class="legal">
            <div class="copyright">
                &copy; 2019 - 2020 <a href="javascript:void(0);">ItHome</a>.
            </div>
            <div class="version">
                <b>Տարբերակ: </b> 1.0
            </div>
        </div>
        <!-- #Footer -->
    </aside>
    <!-- #END# Left Sidebar -->
    <!-- Right Sidebar -->

    <!-- #END# Right Sidebar -->
</section>

<section class="content">
    <div class="container-fluid">
        <div class="block-header">

        </div>

        <!-- CKEditor -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="alert-info " >
                        <%
                            if (request.getAttribute("message") != null) {
                        %>
                        <%=request.getAttribute("message")%>
                        <%
                            }
                        %>
                    </div>
                    <form action="UpdateAboutUsEnglishInData" method="post">
                        <div class="header">
                            <h2>
                                Մեր մասին հայերեն
                                <%-- <small>CKEditor is a ready-for-use HTML text editor designed to simplify web content creation. Taken from <a href="http://ckeditor.com/" target="_blank">ckeditor.com</a></small>
                           --%>  </h2>
                            <%-- <ul class="header-dropdown m-r--5">
                                 <li class="dropdown">
                                     <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                         <i class="material-icons">more_vert</i>
                                     </a>
                                     <ul class="dropdown-menu pull-right">
                                         <li><a href="javascript:void(0);">Action</a></li>
                                         <li><a href="javascript:void(0);">Another action</a></li>
                                         <li><a href="javascript:void(0);">Something else here</a></li>
                                     </ul>
                                 </li>
                             </ul>--%>
                        </div>
                        <div class="body">
                            <c:if test="${requestScope.aboutUsList != null}">
                                <c:forEach items="${requestScope.aboutUsList}" var="aboutus">


                            <textarea id="ckeditor" name="AboutUsText">
                                <c:out value="${aboutus.about_arm}"/>
                                 </textarea>

                                </c:forEach>
                            </c:if>
                        </div>

                        <button type="submit" class="btn btn-info waves-effect right">Թարմացնել</button>

                </div>
                </form>
            </div>
        </div>
        <!-- #END# CKEditor -->

    </div>
</section>
<section class="content">
    <div style="height: 600px">

    </div>
</section>
<!-- Jquery Core Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap/js/bootstrap.js"></script>

<!-- Select Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap-select/js/bootstrap-select.js"></script>

<!-- Slimscroll Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/node-waves/waves.js"></script>

<!-- Ckeditor -->
<script src="<%=request.getContextPath()%>/admin/plugins/ckeditor/ckeditor.js"></script>

<!-- TinyMCE -->
<script src="<%=request.getContextPath()%>/admin/plugins/tinymce/tinymce.js"></script>

<!-- Custom Js -->
<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/admin/js/pages/forms/editors.js"></script>

<!-- Demo Js -->
<script src="<%=request.getContextPath()%>/admin/js/demo.js"></script>
</body>

</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Օֆերդա - Հիմնական վարիչ </title>
    <head><meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet"
              type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/node-waves/waves.css" rel="stylesheet"/>

        <!-- Animation Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/animate-css/animate.css" rel="stylesheet"/>

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
        <!-- File Upload | Drag & Drop OR With Click & Choose -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="header">
                        <h2>
                            ֆայլի վերբեռնուման համար, քաշեք եւ թողեք նկարը այստեղ կամ սեղմեք եւ ընտրեք այն։
                        </h2>
                        <h2><%
                            if (request.getAttribute("message") != null) {
                        %>
                            <%=request.getAttribute("message")%><br/>
                            <%
                                }
                            %>
                        </h2>
                        <ul class="header-dropdown m-r--5">

                        </ul>
                    </div>
                    <div class="body">

                        <form action="<%=request.getContextPath()%>/UpdateBankSmallLogoInData?bankId=<%=request.getAttribute("newBankId")%>"
                              id="frmFileUpload" class="dropzone" method="post"
                              enctype="multipart/form-data">
                            <div class="dz-message">
                                <div class="drag-icon-cph">
                                    <i class="material-icons">touch_app</i>
                                </div>
                                <h3>Թողնել ֆայլեր այստեղ կամ սեղմեք վերբեռնելու համար:</h3>

                            </div>
                            <div class="fallback">
                                <input name="file" type="file" />
                                <%-- <input type="hidden" name="bankId" value="<%=request.getAttribute("newBankId")%>"/>--%>
                            </div>
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                        <div class="card profile-card">
                            <div class="profile-header">&nbsp;</div>

                            <div class="profile-body">
                                <div class="image-area">
                                    <c:if test="${requestScope.BankFullList != null}">
                                    <c:forEach items="${requestScope.BankFullList}" var="bankInfo">
                                    <img src="${bankInfo.bankSmallLogo}"
                                         alt="<c:out value="${bankInfo.bankName}"/>" height="250" width="250"/>
                                </div>

                                </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# File Upload | Drag & Drop OR With Click & Choose -->
        <div class="body">
            <div class="demo-masked-input">
                <div class="row clearfix">
                    <div class="col-md-3">
                        <form action="BankResultPage" method="post">
                            <button type="submit" class="btn btn-success m-t-15 waves-effect">Շարունակել
                            </button>
                            <input type="hidden" name="bankId" value="<%=request.getAttribute("newBankId")%>">
                        </form>
                    </div>
                </div>

            </div>
        </div>
        </form>

        <!-- #END# Masked Input -->
    </div>
</section>
<section class="content">
    <div style="height: 600px">

    </div>
</section>
<!-- #END# File Upload | Drag & Drop OR With Click & Choose -->

<!-- Jquery Core Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap/js/bootstrap.js"></script>

<!-- Select Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap-select/js/bootstrap-select.js"></script>

<!-- Slimscroll Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

<!-- Dropzone Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/dropzone/dropzone.js"></script>

<!-- Input Mask Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery-inputmask/jquery.inputmask.bundle.js"></script>

<!-- Multi Select Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/multi-select/js/jquery.multi-select.js"></script>

<!-- Jquery Spinner Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/jquery-spinner/js/jquery.spinner.js"></script>

<!-- Bootstrap Tags Input Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

<!-- noUISlider Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/nouislider/nouislider.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/node-waves/waves.js"></script>

<!-- Custom Js -->
<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/admin/js/pages/forms/advanced-form-elements.js"></script>

<!-- Demo Js -->
<script src="<%=request.getContextPath()%>/admin/js/demo.js"></script>
</body>

</html>


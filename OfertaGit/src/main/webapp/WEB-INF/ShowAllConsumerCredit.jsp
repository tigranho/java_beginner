<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Օֆերդա - Հիմնական վարիչ </title>
    <head><meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

        <!-- Favicon-->
        <link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon">

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
            <ul class="nav navbar-nav navbar-right">
                <form class="form-inline" action="SearchCC" method="get">
                    <input class="form-control mr-sm-2" type="text" name="search" placeholder="Search" aria-label="Search" required>
                    <input class="btn green" type="submit"/>
                </form>
                <!-- #END# Call Search -->
            </ul>
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
        <div class="row clearfix">
            <c:if test="${requestScope.consumerCreditList != null}">
                <c:forEach items="${requestScope.consumerCreditList}" var="ConsumerCredit"
                           varStatus="loop">

                            <div class="col-xs-12 col-sm-4">
                                <div class="card profile-card">
                                    <div class="profile-header">&nbsp;</div>

                                    <div class="profile-body">

                                        <div class="content-area">
                                            <h3>Ավանդ անվանումը : <c:out value="${ConsumerCredit.productName}"/></h3>

                                        </div>
                                        <div class="content-area">
                                            <h4>
                                                Ավանդ կոդը : <c:out value="${ConsumerCredit.productCode}"/></h4>
                                        </div>
                                    </div>
                                    <div class="profile-footer">
                                        <ul>
                                            <li>
                                                <span>Բանկ</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.bankName}"/></span>
                                            </li>

                                            <li>
                                                <span>Արժույթ</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.currancy}"/></span>
                                            </li>
                                            <li>
                                                <span>Նվազագույն գումար</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMinAmount}"/></span>
                                            </li>

                                            <li>
                                                <span>Առավելագույն գումար</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMaxAmount}"/></span>
                                            </li>

                                            <li>
                                                <span>Նվազագույն Ավանդ</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMinLoan}"/></span>
                                            </li>

                                            <li>
                                                <span>Առավելագույն Ավանդ</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMaxLoan}"/></span>
                                            </li>

                                            <li>
                                                <span>Նվազագույն ժամանակաշրջան</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMinPeriodMonth}"/> Ամիս</span>
                                            </li>


                                            <li>
                                                <span>Առավելագույն ժամանակաշրջան</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMaxPeriodMonth}"/> Ամիս</span>
                                            </li>


                                            <li>
                                                <span>Նվազագույն ծառայության վճար</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMinServiceFee}"/> </span>
                                            </li>

                                            <li>
                                                <span>Առավելագույն ծառայության վճար</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCMaxServiceFee}"/> </span>
                                            </li>

                                            <li>
                                                <span>Փաստացի</span>
                                                <span><c:out
                                                        value="${ConsumerCredit.CCFactual}"/> </span>
                                            </li>
                                        </ul>
                                        <form action="UpdateConsumerCredit" method="post">
                                            <button class="btn btn-primary btn-lg waves-effect btn-block">
                                                Թարմացնել Ավանդ տվյալները
                                            </button>
                                            <input type="hidden" name="CLId" value="${ConsumerCredit.CLId}">
                                        </form>
                                       <%-- <form action="UpdateProductName" method="post">
                                            <button class="btn btn-primary btn-lg waves-effect btn-block">
                                                Թարմացնել անվանումը
                                            </button>
                                            <input type="hidden" name="productCode" value="${ConsumerCredit.productCode}">
                                        </form>--%>

                                        <form action="UpdateComments" method="post">
                                            <button class="btn btn-primary btn-lg waves-effect btn-block">
                                                Թարմացնել մեկնաբանությունները
                                            </button>
                                            <input type="hidden" name="productCode" value="${ConsumerCredit.productCode}">
                                        </form>

                                        <form action="DeleteConsumerCredit" method="post">
                                            <button class="btn bg-orange btn-block btn-lg waves-effect">
                                                Ջնջել
                                            </button>
                                            <input type="hidden" name="CLId" value="${ConsumerCredit.CLId}">
                                        </form>
                                    </div>
                                </div>

                            </div>

                </c:forEach>
            </c:if>
        </div>
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

<!-- Custom Js -->
<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>

<!-- Demo Js -->

<script src="<%=request.getContextPath()%>/admin/js/demo.js"></script>
</body>

</html>


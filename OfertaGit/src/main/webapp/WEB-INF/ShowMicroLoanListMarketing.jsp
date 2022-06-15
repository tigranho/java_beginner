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
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="header">
                        <h2>
                            Քարդի Տեսողական դասավորում
                        </h2>
                        <ul class="header-dropdown m-r--5">
                        </ul>
                    </div>
                    <div class="body">
                        <table id="mainTable" class="table table-striped">
                            <thead>
                            <tr>
                                <th>Կոդը</th>
                                <th>Անվանումը</th>
                                <th>Բանկը</th>
                                <th>Արժույթ</th>
                                <th>Դիրք</th>
                                <th>Թարմացնել</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:if test="${requestScope.ListMarketing != null}">
                                <c:forEach items="${requestScope.ListMarketing}" var="ListMarketing" varStatus="loop">
                                    <form action="UpdateMicroLoanApperanceInData" method="get">
                                        <tr>
                                            <td><c:out value="${ListMarketing.productCode}"/></td>
                                            <td><c:out value="${ListMarketing.productName}"/></td>
                                            <td><c:out value="${ListMarketing.bankName}"/></td>
                                            <td><c:out value="${ListMarketing.currancy}"/></td>
                                            <td><input type="number" name="AppearanceNumber" class="form-control"
                                                       value="${ListMarketing.orderOfAppearance}"/></td></td>

                                            <input type="hidden" name="productCode" class="form-control"
                                                   value="${ListMarketing.productCode}"/>
                                            <input type="hidden" name="bankId" class="form-control"
                                                   value="${ListMarketing.bankId}"/>

                                            <input type="hidden" name="Id" value="${ListMarketing.MLId}"/>
                                            <td>
                                                <button type="submit"
                                                        class="btn bg-purple btn-block btn-lg waves-effect">
                                                    Թարմացնել
                                                </button>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </c:if>
                            </tbody>

                            <tfoot>
                            <tr>
                                <th>Կոդը</th>
                                <th>Անվանումը</th>
                                <th>Բանկը</th>
                                <th>Արժույթ</th>
                                <th>Դիրք</th>
                                <th>Թարմացնել</th>
                            </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div>
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

<!-- Editable Table Plugin Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/editable-table/mindmup-editabletable.js"></script>

<!-- Custom Js -->
<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/admin/js/pages/tables/editable-table.js"></script>

<!-- Demo Js -->
<script src="<%=request.getContextPath()%>/admin/js/demo.js"></script>
</body>

</html>


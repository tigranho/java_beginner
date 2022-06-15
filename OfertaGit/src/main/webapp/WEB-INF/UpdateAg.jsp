<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Օֆերդա - Հիմնական վարիչ </title>
    <head><meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Colorpicker Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" />

        <!-- Dropzone Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/dropzone/dropzone.css" rel="stylesheet">

        <!-- Multi Select Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/multi-select/css/multi-select.css" rel="stylesheet">

        <!-- Bootstrap Spinner Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/jquery-spinner/css/bootstrap-spinner.css" rel="stylesheet">

        <!-- Bootstrap Tagsinput Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

        <!-- Bootstrap Select Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />

        <!-- noUISlider Css -->
        <link href="<%=request.getContextPath()%>/admin/plugins/nouislider/nouislider.min.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="<%=request.getContextPath()%>/admin/css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="<%=request.getContextPath()%>/admin/css/themes/all-themes.css" rel="stylesheet" />
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
        <!-- Input -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <form action="UpdateAgDetailsToData" method="post">
                        <div class="header">
                            <div class="alert-info ">
                                <%
                                    if (request.getAttribute("message") != null) {
                                %>
                                <%=request.getAttribute("message")%>
                                <%
                                    }
                                %>
                            </div>

                            <br>
                            <c:if test="${requestScope.AgriculturalCreditList != null}">
                                <c:forEach items="${requestScope.AgriculturalCreditList}" var="AgFullList" varStatus="page">
                                    <div class="row clearfix">
                                        <div class="col-sm-12">
                                            <div class="content-area">
                                                <h4> Վարկի կոդը : <c:out value="${AgFullList.productCode}"/></h4>
                                            </div>
                                            <input type="hidden" name="ProductCode" value="${AgFullList.productCode}">

                                        <div class="col-sm-12">
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="text" name="ProductName" class="form-control"
                                                           value="${AgFullList.productName}"/>
                                                    <label class="form-label">Վարկի անվանումը</label>
                                                </div>
                                            </div>



                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" id="BankName" name="BankName">
                                                        <option name="bankName" value="${AgFullList.bankName}" selected>${AgFullList.bankName}</option>
                                                        <c:if test="${requestScope.BankFullList != null}">
                                                            <c:forEach items="${requestScope.BankFullList}" var="bankList">

                                                                <option name="bankName" value="${bankList.bankName}">${bankList.bankName}</option>

                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMinAmount" class="form-control"
                                                           value="${AgFullList.ACMinAmount}"/>
                                                    <label class=" form-label">Վարկի նվազագույն գումարը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMaxAmount" class="form-control"
                                                           value="${AgFullList.ACMaxAmount}"/>
                                                    <label class=" form-label">Վարկի առավելագույն գումարը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMinLoan" class="form-control" value="${AgFullList.ACMinLoan}"/>
                                                    <label class="form-label">Վարկի Նվազագույն վարկ</label>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMacLoan" class="form-control" value="${AgFullList.ACMaxLoan}"/>
                                                    <label class="form-label">Վարկի առավելագույն վարկ</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACPerFactual" class="form-control" value="${AgFullList.ACFactual}"/>
                                                    <label class="form-label">Փաստացի</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMinPeriodMonth" class="form-control" value="${AgFullList.ACMinPeriodMonth}"/>
                                                    <label class="form-label">Նվազագույն ժամկետը ամիսով</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMaxPeriodMonth" class="form-control" value="${AgFullList.ACMaxPeriodMonth}"/>
                                                    <label class="form-label">Առավելագույն ժամկետը ամիսով</label>
                                                </div>
                                            </div>



                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" id="cardType" name="currancy" >
                                                        <option name="currancy" value="${AgFullList.currancy}" selected>${AgFullList.currancy}</option>
                                                        <c:if test="${requestScope.CurrancyListFullList != null}">
                                                            <c:forEach items="${requestScope.CurrancyListFullList}" var="CurrancyList">
                                                                <option name="currancy" value="${CurrancyList.currancy}">${CurrancyList.currancy}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMinServiceFee" class="form-control" value="${AgFullList.ACMinServiceFee}"/>
                                                    <label class="form-label">Վարկի նվազագույն սպասարկման վճարը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMaxServiceFee" class="form-control" value="${AgFullList.ACMaxServiceFee}"/>
                                                    <label class="form-label">Վարկի առավելագույն սպասարկման վճար</label>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMinAge" class="form-control" value="${AgFullList.minAge}"/>
                                                    <label class="form-label">Վարկի նվազագույն թույլատրված տարիքը</label>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="ACMaxAge" class="form-control" value="${AgFullList.maxAge}"/>
                                                    <label class="form-label">Վարկի առավելագույն թույլատրված տարիքը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="text" name="BankLink" class="form-control" value="${AgFullList.bankLink}"/>
                                                    <label class="form-label">Բանկի հղում</label>
                                                </div>
                                            </div>

                                            <div class="col-xs-3">
                                                <input type="hidden" name="ACId" value="${AgFullList.ACId}"/>
                                                <button class=" btn bg-orange btn-block btn-lg waves-effect"
                                                        type="submit">
                                                    Թարմացնել
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- #END# Input -->
    </div>
</section>

<section class="content">
    <div class="container-fluid" style="height: 600px">

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

<!-- Bootstrap Colorpicker Js -->
<script src="<%=request.getContextPath()%>/admin/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>

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


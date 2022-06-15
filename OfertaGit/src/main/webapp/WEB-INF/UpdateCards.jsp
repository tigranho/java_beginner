<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>Օֆերդա - Հիմնական վարիչ </title>
    <head><meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
                    <form action="UpdateCardDetailsToData" method="post">
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
                            <h2 class="card-inside-title">Նոր քարտի տեղեկատվության ավելացում</h2>
                            <br>
                            <c:if test="${requestScope.CardFullListById != null}">
                                <c:forEach items="${requestScope.CardFullListById}" var="cardFullList" varStatus="page">
                                    <div class="row clearfix">
                                        <div class="col-sm-12">
                                            <div class="content-area">
                                                <h4>
                                                    Վարկի կոդը : <c:out value="${cardFullList.productCode}"/></h4>
                                            </div>
                                            <input type="hidden" name="ProductCode" value="${cardFullList.productCode}">

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" id="BankName"
                                                            name="BankName">
                                                        <option name="bankName" value="${cardFullList.bankName}"
                                                                selected>${cardFullList.bankName}</option>
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
                                                    <input type="text" name="cardName" class="form-control"
                                                           value="${cardFullList.cardName}"/>
                                                    <label class=" form-label">Քարտի անվանում</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick"  name="Cashback">
                                                        <c:set  value="${cardFullList.cashback}" var="cashback"/>
                                                        <c:choose>
                                                            <c:when test="${cashback.equals('0') ||cashback==null}">
                                                                <option value="0" selected>Ոչ - Cashback</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="Cashback" selected>Cashback</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <option value="0">Ոչ - Cashback</option>
                                                        <option value="Cashback">Cashback</option>
                                                    </select>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" name="Timer" required>
                                                        <c:set  value="${cardFullList.timer}" var="Timer"/>
                                                        <c:choose>
                                                            <c:when test="${Timer.equals('0') ||Timer==null}">
                                                                <option value="0" selected>Ոչ - Արտոնյալ ժամանակաշրջան</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="Grace period" selected>Արտոնյալ ժամանակաշրջան</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <option value="0">Ոչ - Արտոնյալ ժամանակաշրջան</option>
                                                        <option value="Grace period">Արտոնյալ ժամանակաշրջան</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" name="Free" required>
                                                        <c:set  value="${cardFullList.free}" var="Free"/>
                                                        <c:choose>
                                                            <c:when test="${Free.equals('0') ||Free==null}">
                                                                <option value="0" selected >Ոչ - Անվճար</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="Free" selected>Անվճար</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <option value="0">Ոչ - Անվճար</option>
                                                        <option value="Free">Անվճար</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" name="Debit" required>
                                                        <c:set  value="${cardFullList.debit}" var="Debit"/>
                                                        <c:choose>
                                                            <c:when test="${Debit.equals('0') ||Debit==null}">
                                                                <option value="0" selected >Ոչ - Դեբիտ</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="Debit" selected>Դեբիտ</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <option value="0">Ոչ - Դեբիտ</option>
                                                        <option value="Debit">Դեբիտ</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" name="Credit" required>
                                                        <c:set value="${cardFullList.crediting}" var="creditsBank"/>
                                                        <c:choose>
                                                            <c:when test="${creditsBank.equals('0') || creditsBank==null }">
                                                                <option value="0" selected >Ոչ - Քրեդիտ</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="Credit" selected>Քրեդիտ</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <option value="0">Ոչ - Քրեդիտ</option>
                                                        <option value="Credit">Քրեդիտ</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardMinServiceFee" class="form-control"
                                                           value="${cardFullList.cardMinServiceFee}"/>
                                                    <label class="form-label">Քարտի նվազագույն սպասարկման վճարը</label>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardMaxServiceFee" class="form-control"
                                                           value="${cardFullList.cardMaxServiceFee}"/>
                                                    <label class="form-label">Քարտի առավելագույն սպասարկման վճար</label>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerMinCashBack" class="form-control"
                                                           value="${cardFullList.cardPerMinCashBack}"/>
                                                    <label class="form-label">Քարտի նվազագույն կանխիկ գումար</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerMaxCashBack" class="form-control"
                                                           value="${cardFullList.cardPerMaxCashBack}"/>
                                                    <label class="form-label">Քարտի առավելագույն կանխիկ գումար</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerMinDiscount" class="form-control"
                                                           value="${cardFullList.cardPerMinDiscount}" step="0.01"
                                                           min="0" max="200"/>
                                                    <label class="form-label">Քարտի նվազագույն զեղչը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerMaxDiscount" class="form-control"
                                                           value="${cardFullList.cardPerMaxDiscount}" step="0.01"
                                                           min="0" max="200"/>
                                                    <label class="form-label">Քարտի առավելագույն զեղչը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="MinCashBack" class="form-control"
                                                           value="${cardFullList.minCashBack}" step="0.01" min="0"
                                                           max="200"/>
                                                    <label class="form-label">Նվազագույն Քեշ բեք</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="MaxCashBack" class="form-control"
                                                           value="${cardFullList.maxCashBack}" step="0.01" min="0"
                                                           max="200"/>
                                                    <label class="form-label">Առավելագույն Քեշ բեք</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardMinCreditLimit" class="form-control"
                                                           value="${cardFullList.cardMinCreditLimit}"/>
                                                    <label class="form-label">Քարտի նվազագույն վարկային
                                                        սահմանաչափը</label>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardMaxCreditLimit" class="form-control"
                                                           value="${cardFullList.cardMaxCreditLimit}"/>
                                                    <label class="form-label">Քարտի առավելագույն վարկի սահմանը</label>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerCreditLimit" class="form-control"
                                                           value="${cardFullList.cardPerCreditLimit}" step="0.01"
                                                           min="0" max="200"/>
                                                    <label class="form-label">Քարտի վարկի սահմանաչափը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerFactual" class="form-control"
                                                           value="${cardFullList.cardPerFactual}" step="0.01" min="0"
                                                           max="200"/>
                                                    <label class="form-label">Փաստացի</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <select class="form-control show-tick" id="currancy"
                                                            name="currancy">
                                                        <option name="bankName" value="${cardFullList.currancy}"
                                                                selected>${cardFullList.currancy}</option>
                                                        <c:if test="${requestScope.CurrancyListFullList != null}">
                                                            <c:forEach items="${requestScope.CurrancyListFullList}"
                                                                       var="CurrancyList">
                                                                <option value="${CurrancyList.currancy}">${CurrancyList.currancy}</option>
                                                            </c:forEach>
                                                        </c:if>

                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="minAge" class="form-control"
                                                           value="${cardFullList.minAge}"/>
                                                    <label class="form-label">Նվազագույն տարիքը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="maxAge" class="form-control"
                                                           value="${cardFullList.maxAge}"/>
                                                    <label class="form-label">Առավելագույն տարիքը</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardGracePeriod" class="form-control"
                                                           value="${cardFullList.cardGracePeriod}"/>
                                                    <label class="form-label">Արտոնյալ ժամկետ</label>
                                                </div>
                                            </div>


                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="number" name="cardPerOnCreditStanding"
                                                           class="form-control"
                                                           value="${cardFullList.cardPerOnCreditStanding}"/>
                                                    <label class="form-label">Վարկը չմարված</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="text" name="cardDetails"
                                                           class="form-control"
                                                           value="${cardFullList.details}"/>
                                                    <label class="form-label">Մանրամասնություններ</label>
                                                </div>
                                            </div>

                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="text" name="BankLink" class="form-control" value="${cardFullList.bankLink}"/>
                                                    <label class="form-label">Բանկի հղում</label>
                                                </div>
                                            </div>
                                            <div class="form-group form-float">
                                                <div class="form-line">
                                                    <input type="text" name="cardInfo" class="form-control" value="${cardFullList.cardInfo}" />
                                                     <label class="form-label">Տեղեկատվական բաժին</label>
                                                </div>
                                            </div>

                                            <div class="col-xs-3">
                                                <input type="hidden" name="cardId" value="${cardFullList.cardId}"/>
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


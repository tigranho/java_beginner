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
        <div class="menu">
            <ul class="list">
                <%--//////////////////////////////////////////////////////////////////////////////////////////Հիմնական նավիգացիան--%>
                <li class="header">Հիմնական նավիգացիան</li>
                <li>
                    <a href="AdminSignInCheck">
                        <i class="material-icons">home</i>
                        <span>Հիմնական էջ</span>
                    </a>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Հիմնական նավիգացիան--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Ադմին վերահսկիչ--%>
                <li>
                    <a href="ToAdminUpdatPage">
                        <i class="material-icons">face</i>
                        <span>Թարմացնել Ադմինի տվյալները</span>
                    </a>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Ադմին վերահսկիչ--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">account_balance_wallet</i>
                        <span>ֆինանսական էջերի կարգավորիչ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="MainPageRangeController">ՀԻՄՆԱԿԱՆ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="DepositRangeController">ԱՎԱՆԴՆԵՐ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="MortgageRangeController">ՀԻՓՈԹԵՔ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="ConsumerRangeController">ՍՊԱՌՈՂԱԿԱՆ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="CarLoanRangeController">ԱՎՏՈՎԱՐԿ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="MicroRangeController">ՄԻԿՐՈՎԱՐԿ էջի կարգավորիչ
                            </a>
                        </li>
                        <li>
                            <a href="AGRangeController">ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ էջի կարգավորիչ
                            </a>
                        </li>

                    </ul>

                    <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>

                    <%--//////////////////////////////////////////////////////////////////////////////////////////Տեսնելության բաժինը Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">find_replace</i>
                        <span>Տեսնելության կարգի բաժին</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Ավտովարկեր</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankCarLoanAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>

                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Միգչո վարկ</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankMicroLoanAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Ավանդ</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankDepositAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>

                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Հիփոթեքային վարկ</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankMortgageLoanAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>

                        </li>

                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>ٍՍպարողական վարկ</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankConsumerLoanAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>

                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Գյուղատնտեսական վարկ</span>
                            </a>
                            <ul class="ml-menu">
                                <c:if test="${requestScope.banksList != null}">
                                    <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                        <li>
                                            <a href="GetBankAgricultureLoanAppearanceOrder?bankId=${BankList.bankId}">
                                                <span><c:out value="${BankList.bankName}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>

                        </li>

                    </ul>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Տեսնելության բաժինը Section--%>

                    <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">graphic_eq</i>
                        <span>Շուկայավարում</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="MarketingCarLoan">Ավտովարկեր
                            </a>
                        </li>
                        <li>
                            <a href="MarketingMicro">Միգչո վարկ
                            </a>
                        </li>
                        <li>
                            <a href="MarketingDeposite">Ավանդ
                            </a>
                        </li>
                        <li>
                            <a href="MarketingMortgage">Հիփոթեքային վարկ
                            </a>
                        </li>
                        <li>
                            <a href="MarketingConsumer">ٍՍպարողական վարկ
                            </a>
                        </li>
                        <li>
                            <a href="MarketingAG">Գյուղատնտեսական վարկ
                            </a>
                        </li>

                    </ul>

                    <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>

                    <%--//////////////////////////////////////////////////////////////////////////////////////////Web control Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">control_point_duplicate</i>
                        <span>Կայքի ղեկավարում</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Մեր մասին</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="AboutUsArm">
                                        <span>Մեր մասին Հայ</span>
                                    </a>
                                </li>
                                <%--<li>
                                    <a href="AboutUsEng">
                                        <span>Մեր մասին Անգ</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="AboutUsRus">
                                        <span>Մեր մասին Ռուս</span>
                                    </a>
                                </li>--%>

                            </ul>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Պայմանները</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="TermsArm">
                                        <span>Պայմանները Հայ</span>
                                    </a>
                                </li>
                                <%--<li>
                                    <a href="TermsEng">
                                        <span>Պայմանները Անգ</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="TermsRus">
                                        <span>Պայմանները Ռուս</span>
                                    </a>
                                </li>--%>

                            </ul>
                        </li>

                        </li>
                    </ul>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////web control Section--%>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Բլոգի բաժինը Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">assignment</i>
                        <span>Բլոգի բաժինը</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>նոր բլոգ</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="AddNewArticleArmenian">
                                        <span>նոր բլոգ</span>
                                    </a>
                                </li>

                            </ul>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Բոլոր բլոգները</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="ShowAllArmenianBlogs">
                                        <span>Բոլոր Հայկական բլոգները</span>
                                    </a>
                                </li>
                                <%--<li>
                                    <a href="ShowAllRussianBlogs">
                                        <span>Բոլոր Ռուսական բլոգները</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="ShowAllEnglishBlogs">
                                        <span>Բոլոր Անգլերեն բլոգները</span>
                                    </a>
                                </li>--%>

                            </ul>
                        </li>

                        </li>
                    </ul>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Բլոգի բաժինը Section--%>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Bank Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">account_balance</i>
                        <span>Բանկերի բաժին</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="ShowAllBanks">Բոլոր Բանկերը</a>
                        </li>
                        <li>
                            <a href="AddNewBank">Ավելացնել նոր բանկ</a>
                        </li>
                        </li>
                    </ul>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Bank Section--%>
                    <%--//////////////////////////////////////////////////////////////////////////////////////////Card Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">card_membership</i>
                        <span>Քարտեր</span>
                    </a>

                    <ul class="ml-menu">
                        <li>
                            <a href="AllCards">Բոլոր քարտերը</a>
                        </li>
                        <li>
                            <a href="NewCard">Ավելացնել նոր քարտ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Card Section--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Car loans Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">directions_car</i>
                        <span>Ավտոմեքենայի վարկերը</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllCarLoans">Բոլոր Ավտոմեքենայի վարկերը</a>
                        </li>
                        <li>
                            <a href="NewCarLoan">Ավելացնել նոր Ավտոմեքենայի վարկ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Car loans Section--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Micro loan Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">class</i>
                        <span>Միկրո վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllMicro">Բոլոր Միկրո վարկերը</a>
                        </li>
                        <li>
                            <a href="NewMicro">Ավելացնել նոր Միկրո վարկ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Micro loan Section--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Deposit Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">looks</i>
                        <span>Ավանդ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllDeposit">Բոլոր Ավանդները</a>
                        </li>
                        <li>
                            <a href="NewDeposit">Ավելացնել նոր Ավանդ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Deposit Section--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Mortgage Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">multiline_chart</i>
                        <span>Հիփոթեքային վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllMortgage">Բոլոր Հիփոթեքային վարկերը</a>
                        </li>
                        <li>
                            <a href="NewMortgage">Ավելացնել նոր Հիփոթեքային վարկ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Mortgage crediting Section--%>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Cosumer crediting Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">face</i>
                        <span>Սպարողական վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllConsumerCredit">Բոլոր Սպարողական վարկերը</a>
                        </li>
                        <li>
                            <a href="NewConsumerCredit">Ավելացնել նոր Սպարողական վարկ</a>
                        </li>
                    </ul>
                </li>
                <%--//////////////////////////////////////////////////////////////////////////////////////////Cosumer crediting Section--%>
                <%--////////////////////////////////////////////////////////////////////////////////////////// Գյուղատնտեսական Section--%>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <i class="material-icons">local_florist</i>
                        <span>Գյուղատնտեսական վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AllAgricultureCredits">Բոլոր Գյուղատնտեսական վարկ</a>
                        </li>
                        <li>
                            <a href="NewAgricultureCredit">Ավելացնել նոր Գյուղատնտեսական վարկ</a>
                        </li>
                    </ul>
            </ul>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Գյուղատնտեսական Section--%>

        </div>
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
                    <form action="AddNewCardToData" method="post">
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
                            <div class="row clearfix">

                                <div class="col-sm-12">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="BankName" name="BankName" required>
                                                <c:if test="${requestScope.BankFullList != null}">
                                                    <c:forEach items="${requestScope.BankFullList}" var="bankList">
                                                        <option name="bankName">${bankList.bankName}</option>

                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="cardName" class="form-control" required/>
                                            <label class="form-label" required>Քարտի անվանում</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="ProductNameEng" class="form-control" />
                                            <label class="form-label" required>Վարկի անվանում Անգլերեն</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="ProductNameRus" class="form-control" />
                                            <label class="form-label" required>Վարկի անվանում Ռուսերեն</label>
                                        </div>
                                    </div>

                                   <%-- <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="cardType" name="cardType" required>
                                                <c:if test="${requestScope.CardTypeFullList != null}">
                                                    <c:forEach items="${requestScope.CardTypeFullList}" var="CardList">
                                                        <option name="bankName">${CardList.cardType}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>--%>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick"  name="Cashback" required>
                                                <option value="0">Ոչ - Cashback</option>
                                                <option value="Cashback">Cashback</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick"  name="Timer" required>
                                                <option value="0">Ոչ - Արտոնյալ ժամանակաշրջան</option>
                                                <option value="Grace period">Արտոնյալ ժամանակաշրջան</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick"  name="Free" required>
                                                <option value="0">Ոչ - Անվճար</option>
                                                <option value="Free">Անվճար</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick"  name="Debit" required>
                                                <option value="0">Ոչ - Դեբիտ</option>
                                                <option value="Debit">Դեբիտ</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick"  name="Credit" required>
                                                <option value="0">Ոչ - Քրեդիտ</option>
                                                <option value="Credit">Քրեդիտ</option>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardMinServiceFee" class="form-control" required />
                                            <label class="form-label" required>Քարտի նվազագույն սպասարկման վճարը</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardMaxServiceFee" class="form-control" required/>
                                            <label class="form-label" required>Քարտի առավելագույն սպասարկման վճար</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerMinCashBack" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label" required>Քարտի նվազագույն կանխիկ գումար</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerMaxCashBack" class="form-control"  required/>
                                            <label class="form-label" required>Քարտի առավելագույն կանխիկ գումար</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerMinDiscount" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label" required>Քարտի նվազագույն զեղչը</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerMaxDiscount" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label" required>Քարտի առավելագույն զեղչը</label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardMinCreditLimit" class="form-control" required/>
                                            <label class="form-label" required>Քարտի նվազագույն վարկային սահմանաչափը</label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardMaxCreditLimit" class="form-control" required/>
                                            <label class="form-label" required>Քարտի առավելագույն վարկի սահմանը</label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerCreditLimit" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label" required>Քարտի վարկի սահմանաչափը</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerFactual" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label">Փաստացի</label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="MinCashBack" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label">Մինիմալ Քեշ բեք</label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="MaxCashBack" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label">Մաքսիմալ Քեշ բեք </label>
                                        </div>
                                    </div>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="currancy" name="currancy" required>
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
                                            <input type="number" name="minAge" class="form-control" required/>
                                            <label class="form-label" required>Նվազագույն տարիքը</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="maxAge" class="form-control" required/>
                                            <label class="form-label" required>Առավելագույն տարիքը</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardGracePeriod" class="form-control" required/>
                                            <label class="form-label" required>Արտոնյալ ժամկետ</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="cardPerOnCreditStanding" class="form-control" required/>
                                            <label class="form-label" required>Վարկը չմարված</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="cardDetails" class="form-control" required/>
                                            <label class="form-label" required>Մանրամասնություններ</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="cardInfo" class="form-control" required/>
                                            <label class="form-label" required>Տեղեկատվական բաժին</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="lastLink" class="form-control" required/>
                                            <label class="form-label">Հղում Զեղչ խանութներում </label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="BankLink" class="form-control" required/>
                                            <label class="form-label">Բանկի հղում</label>
                                        </div>
                                    </div>


                                    <div class="col-xs-3">
                                        <button class=" btn bg-orange btn-block btn-lg waves-effect" type="submit">Դեպի
                                            հաջորդ քայլ >>>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- #END# Input -->
    </div>
</section><section class="content">
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


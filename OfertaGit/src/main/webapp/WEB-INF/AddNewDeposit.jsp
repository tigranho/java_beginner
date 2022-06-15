<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Օֆերդա - Հիմնական վարիչ </title>

        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        !-- Google Fonts -->
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
                    <form action="AddNewDepositToData" method="post">
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
                            <h2 class="card-inside-title">Նոր ավանդների վարկեր տեղեկատվության ավելացում</h2>
                            <br>
                            <div class="row clearfix">
                               <%-- <div class="col-sm-12">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="ProductCode" class="form-control"/>
                                            <label class="form-label" required>Վարկի կոտը</label>
                                        </div>
                                    </div>
                                </div>--%>
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
                                            <input type="text" name="ProductName" class="form-control" required/>
                                            <label class="form-label" required>Վարկի անվանում</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="ProductNameEng" class="form-control"/>
                                            <label class="form-label" required>Վարկի անվանում Անգլերեն</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="ProductNameRus" class="form-control"/>
                                            <label class="form-label" required>Վարկի անվանում Ռուսերեն</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="DMinAmount" class="form-control" required/>
                                            <label class="form-label" required>Վարկի նվազագույն գումարը</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="DMaxAmount" class="form-control" required/>
                                            <label class="form-label" required>Վարկի առավելագույն գումարը</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" name="DPercentage" class="form-control" step="0.01" min="0" max="200" required/>
                                            <label class="form-label" required>Ավանդի %</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="minAge" class="form-control" required/>
                                            <label class="form-label" required>Վարկի նվազագույն թույլատրված տարիքը</label>
                                        </div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="maxAge" class="form-control" required/>
                                            <label class="form-label" required>Վարկի առավելագույն թույլատրված տարիքը</label>
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
                                            <select class="form-control show-tick" id="timeline" name="timeline" required>
                                            <c:if test="${requestScope.timeLineList != null}">
                                                <c:forEach items="${requestScope.timeLineList}"
                                                           var="timelineList">
                                                    <option value="${timelineList.time}">${timelineList.time}</option>
                                                </c:forEach>
                                            </c:if>
                                            </select>
                                        </div>
                                    </div>
                                   <%-- <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="timeline" name="timeline" required>
                                                <c:if test="${requestScope.timeLineList != null}">
                                                    <c:forEach items="${requestScope.timeLineList}"
                                                               var="timelineList">
                                                        <option value="${timelineList.time}">${timelineList.time}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>--%>


                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="DEquippingPossibilities" name="DEquippingPossibilities" required>
                                                        <option value="Այո">Այո - Համալրման հնարաորություն</option>
                                                        <option value="Ոչ">Ոչ</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="DEarlierWithdrawalAmount" name="DEarlierWithdrawalAmount" required>
                                                <option value="Այո">Այո - ժամկետից շուտ քումարի դուր բերում</option>
                                                <option value="Ոչ">Ոչ</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="DAutoExtendPeriod" name="DAutoExtendPeriod" required>
                                                <option value="Այո">Այո - Աֆտոմատ երկարացում</option>
                                                <option value="Ոչ">Ոչ</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control show-tick" id="DCapitalizationPercent" name="DCapitalizationPercent" required>
                                                <option value="Այո">Այո - Տոկոսների քաբիդալիզացիա</option>
                                                <option value="Ոչ">Ոչ</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" name="BankLink" class="form-control" required/>
                                            <label class="form-label">Բանկի հղում</label>
                                        </div>
                                    </div>

                                    <div class="col-xs-3">
                                        <button class=" btn bg-orange btn-block btn-lg waves-effect" type="submit">
                                            Ավելացրեք

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
    <!-- #END# Input -->
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


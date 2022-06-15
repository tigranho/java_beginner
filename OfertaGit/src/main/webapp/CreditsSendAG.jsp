<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
   <meta property="og:title" content="Oferta.am">
    <meta property="og:type" content="website" />
    <meta property="og:image" content="https://oferta.am/images/oferta1.jpg">
    <meta property="og:description" content="Բոլոր բանկային առաջարկները մեկ հարթակում"><meta property="og:url" content="https://oferta.am">
    <meta name="twitter:card" content="summary_large_image">
    <%
        if(request.getSession().getAttribute("session")!= null){
            request.getAttribute("session");
        }else{
            String sessionId = session.getId();
            request.setAttribute("session",sessionId);
        }
    %>
    <jsp:include page="include/google.jsp"/><meta charset="UTF-8">


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Oferta.am</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="css/global.css" type="text/css">
    <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
<link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/materialize.css">

    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/Compare.js"></script>
    <script>
        (function ($) {
            $.fn.countTo = function (options) {
                options = options || {};

                return $(this).each(function () {
                    // set options for current element
                    var settings = $.extend({}, $.fn.countTo.defaults, {
                        from: $(this).data('from'),
                        to: $(this).data('to'),
                        speed: $(this).data('speed'),
                        refreshInterval: $(this).data('refresh-interval'),
                        decimals: $(this).data('decimals')
                    }, options);

                    // how many times to update the value, and how much to increment the value on each update
                    var loops = Math.ceil(settings.speed / settings.refreshInterval),
                        increment = (settings.to - settings.from) / loops;

                    // references & variables that will change with each update
                    var self = this,
                        $self = $(this),
                        loopCount = 0,
                        value = settings.from,
                        data = $self.data('countTo') || {};

                    $self.data('countTo', data);

                    // if an existing interval can be found, clear it first
                    if (data.interval) {
                        clearInterval(data.interval);
                    }
                    data.interval = setInterval(updateTimer, settings.refreshInterval);

                    // initialize the element with the starting value
                    render(value);

                    function updateTimer() {
                        value += increment;
                        loopCount++;

                        render(value);

                        if (typeof (settings.onUpdate) == 'function') {
                            settings.onUpdate.call(self, value);
                        }

                        if (loopCount >= loops) {
                            // remove the interval
                            $self.removeData('countTo');
                            clearInterval(data.interval);
                            value = settings.to;

                            if (typeof (settings.onComplete) == 'function') {
                                settings.onComplete.call(self, value);
                            }
                        }
                    }

                    function render(value) {
                        var formattedValue = settings.formatter.call(self, value, settings);
                        $self.html(formattedValue);
                    }
                });
            };

            $.fn.countTo.defaults = {
                from: 0,               // the number the element should start at
                to: 0,                 // the number the element should end at
                speed: 700,           // how long it should take to count between the target numbers
                refreshInterval: 70,  // how often the element should be updated
                decimals: 0,           // the number of decimal places to show
                formatter: formatter,  // handler for formatting the value before rendering
                onUpdate: null,        // callback method for every time the element is updated
                onComplete: null       // callback method for when the element finishes updating
            };

            function formatter(value, settings) {
                return value.toFixed(settings.decimals);
            }
        }(jQuery));

        jQuery(function ($) {
            // custom formatting example
            $('.count-number').data('countToOptions', {
                formatter: function (value, options) {
                    return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ',');
                }
            });

            // start all the timers
            $('.timer').each(count);

            function count(options) {
                var $this = $(this);
                options = $.extend({}, options || {}, $this.data('countToOptions') || {});
                $this.countTo(options);
            }
        });

    </script>

</head>
<style>
    .html-content {
        position: absolute;
        top: 999999px;
    }
</style>

<body id="body">
<div class="overlay" id="overlay"></div>
<div class="overlay-box" id="overlay-box"></div>
<div class="page-container">
    <div class="fixed-header">
        <header>

            <div class="inner-container large">
                <div class="flex space-between">
                    <div class="left flex align-items-center">
               <span class="logo">
                   <a href="App?PageLanguage=${requestScope.Pagelanguage}"> <img src="${requestScope.getContextPath}/images/oferta.png" alt=""/></a>
               </span>
                        <span class="hide-for-tablet">
                   <ul>
                     	<li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a
                                            href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
									<%--<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}">Մեր Գործընկերները</a></li>--%>
									<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}&&City=<%=request.getAttribute("City")%>">Մեր Գործընկերները</a></li>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
                   </ul>
               </span>
                    </div>
                    <div class="right flex align-items-center">
                        <span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i>${requestScope.City}   </span>
                        <span class="language-box">
                   <%-- <span class="selected-item" onclick="toggleBoxes('langList')">Հայ</span>
                   &lt;%&ndash; <div class="list" id="langList">
                         <ul>
                            <li class="active">Հայ</li>
                            <li>Eng</li>
                            <li>Рус</li>
                        </ul>
                    </div>&ndash;%&gt;
                </span>--%>
                       <%-- <span class="compere-box show-for-tablet relative">
                     <span class="compere-icon" onclick="toggleBoxes('compareTooltipMb')">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipMb">
                        <div class="tooltip"> <!--Todo add 'tp-blue' class when there is no item -->
                            <span class="tooltip-title">Համեմատության</span>
                            <ul>
                                <li>
                                    <span>Սպառողական վարկ</span>
                                    <span class="bold font-14">4</span>
                                    <i class="icon-delete"></i>
                                </li>
                            </ul>
                            <!--Todo add when there is no item -->
                            <!--                            <span>Համեմատության էջում տեղ չկա</span>-->
                        </div>
                    </div>--%>
                </span>
                        <span class="i-menu show-for-tablet" id="mbNavBtn"><i class="icon-menu font-20"></i></span>
                    </div>
                </div>
            </div>

        </header>
        <div class="navigation" id="mbNav">
            <div class="inner-container large">
                <div class="flex space-between align-items-center inner-content flex-wrap">
                    <jsp:include page="include/Search.jsp"/>
                    <div class="nav-content">
                        <c:if test="${requestScope.dropDownsListWithCurrancy!=null}">
                            <c:forEach items="${requestScope.dropDownsListWithCurrancy}" var="dropDownList">
                                <ul>
                                    <li>
                                        <a href="App?Currancy=${requestScope.PageCurrancy}">ԳԼԽԱՎՈՐ</a></li>
                                    <li>
                                        <a href="DepositClient?PageToGo=Deposits&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎԱՆԴՆԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="MortgageClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՀԻՓՈԹԵՔ</a>
                                    </li>
                                    <li>
                                        <a href="ConsumerClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՍՊԱՌՈՂԱԿԱՆ
                                            ՎԱՐԿԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="AutoClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎՏՈՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="MicroClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՄԻԿՐՈՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="AClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ
                                            ՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=on&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=credit">ՔԱՐՏԵՐ</a>
                                    </li>
                                </ul>
                            </c:forEach>
                        </c:if>
                    </div>
                    <!-- starting compare Section -->
                    <%--
                                        <%
                                            if (request.getAttribute("WorningMessage") != null) {
                                        %>
                                        <%=request.getAttribute("WorningMessage")%>
                                        <%
                                            }
                                        %>--%>
                    <span class="right hide-for-tablet">

                <span><i class="icon-search" id="searchBtn"></i></span>
                <span class="compere-box">

                    <span class="compere-icon">
                        <c:if test="${requestScope.comparListDeposit != null}">
                            <c:forEach var="size" items="${requestScope.comparListDeposit}" varStatus="TheCount">
                                <span class="count"><c:out value="${TheCount.count}"/></span>
                            </c:forEach>
                        </c:if>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipWeb">
                        <div class="tooltip">
                            <span class="tooltip-title">Համեմատության</span>
                            <form action="CompareDeposit" method="post" name="DepositCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListDeposit != null}">
                            <ul>
                                <li >
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                      <span onclick="document.DepositCompare.submit();">Ավանդ</span>
                                    <span class="bold font-14">${counterDeposit}</span>
                                     <i class="icon-delete" onclick="document.Delete.submit();"></i>
                                    <form action="DepositClient" method="post" name="Delete">
                                         <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                                </li>
                            </ul>
                            </c:if>
                             <form action="CompareMortgage" method="post" name="MortgageCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListMortgage != null}">
                            <ul>
                               <li onclick="document.MortgageCompare.submit();">
                                    <c:forEach var="MortgagCompare" items="${requestScope.comparListMortgage}"
                                               varStatus="TheCount">
                                        <c:set var="counterMortgag" value="${TheCount.count}" scope="request"/>


                                    </c:forEach>
                                     <span onclick="document.MortgageCompare.submit();">Հիփոթեք</span>
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteHipotek.submit();"></i>
                                </li>
                             <form action="DepositClient" method="post" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                            </ul>
                            </c:if>
                           <form action="CompareConsumer" method="post" name="ConsumerCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                             <c:if test="${requestScope.comparListConsumer != null}">
                            <ul>
                                <li>
                                    <c:forEach var="ConsumerCompare" items="${requestScope.comparListConsumer}"
                                               varStatus="TheCount">
                                        <c:set var="counterCompare" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span onclick="document.ConsumerCompare.submit();">Սպառողական</span>
                                     <span class="bold font-14">${counterCompare}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteConsumer.submit();"></i>
                                </li>
                             <form action="DepositClient" method="post" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="post" name="CarLoanCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListCarLoan != null}">
                            <ul>
                               <li>
                                    <c:forEach var="CarLoanCompare" items="${requestScope.comparListCarLoan}"
                                               varStatus="TheCount">
                                        <c:set var="counterCarLoan" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                    <span onclick="document.CarLoanCompare.submit();">Ավտովարկ</span>
                                      <span class="bold font-14">${counterCarLoan}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteCar.submit();"></i>
                                </li>
                            </ul>
                                <form action="DepositClient" method="post" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                            </c:if>
                           <%--  <form action="CompareMicro" method="post" name="MicroCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListMicro != null}">
                            <ul>
                               <li>
                                    <c:forEach var="MicroCompare" items="${requestScope.comparListMicro}"
                                               varStatus="TheCount">
                                        <c:set var="counterMicro" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span onclick="document.MicroCompare.submit();">Միկրովարկ</span>
                                     <span class="bold font-14">${counterMicro}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteMicro.submit();"></i>
                                </li>
                           <form action="DepositClient" method="post" name="DeleteMicro">
                                        <input type="hidden" name="pageNameToDelete" value="ՄԻԿՐՈՎԱՐԿ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">

                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                            </ul>
                            </c:if>--%>
                            <form action="CompareAg" method="post" name="AgCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListAg != null}">
                            <ul>
                                 <li>
                                    <c:forEach var="AgCompare" items="${requestScope.comparListAg}"
                                               varStatus="TheCount">
                                        <c:set var="counterAg" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span>Գյուղատնտեսական</span>
                                      <span class="bold font-14">${counterAg}</span>
                                         <i type="submit" class="icon-delete" onclick="document.DeleteAG.submit();"></i>
                                </li>
                            <form action="DepositClient" method="post" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                    </form>
                            </ul>
                            </c:if>
                            <%--</c:if>--%>

                             <%--<c:if test="${requestScope.comparListCard != null}">
                            <ul>
                                <li>
                                    <c:forEach var="CardCompare" items="${requestScope.comparListCard}"
                                               varStatus="TheCount">
                                        <c:set var="counterCard" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span>Քարտեր</span>
                                      <span class="bold font-14">${counterCard}</span>
                                        <i class="icon-delete"></i>
                                </li>
                            </ul>
                             </c:if>--%>
                            <!--Todo add when there is no item -->
                            <!--                            <span>Համեմատության էջում տեղ չկա</span>-->

                        </div>
                    </div>
                </span>
            </span>
                    <!-- Ending compare Section -->
                </div>
            </div>
        </div>

    </div>
    <c:if test="${requestScope.TheListAg != null}">
    <c:forEach items="${requestScope.TheListAg}" var="details">
    <div class="bg-white-dark padding-bottom-60 bank-item-page">
        <div class="inner-container">
            <div class="bank-header">
                <div class="bg" style="background-image: url('../../../images/10.svg')">
                    <div class="top">
                        <div class="item-logo">
                            <a href="">
                                <c:if test="${requestScope.bankList != null}">
                                    <c:forEach items="${requestScope.bankList}" var="bankImage"
                                               varStatus="page">
                                        <c:if test="${details.bankId == bankImage.bankId}">
                                            <img src="${bankImage.bankBigLogo}"/>

                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </a>
                        </div>
                        <p class="font-36">${details.productName}</p>

                        <c:set var="sendRequest" value="${details.sendRequest}"/>
                        <c:set var="gotoPage" value="${details.gotoPage}"/>
                        <c:set var="lastLogic" value="${details.lastLogic}"/>
                        <c:choose>
                            <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic== 0}">
                                <a href="${details.bankLink}"
                                   class="def-button btn-green with-shadow margin-top-15 max-width-160"
                                   target="_blank">Անցնել
                                    բանկի
                                    էջ</a>
                            </c:when>
                            <c:when test="${gotoPage == 0 || sendRequest== 0 && lastLogic== 1}">

                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>

            <div class="row row-flex flex flex-wrap credit-info">
                <div class="col-4 flex align-items-center item">
                    <span>Տոկոսադրույքը</span><span>${details.ACFactual}%</span>

                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Սպասարկման վճար</span><span><fmt:formatNumber type="number" maxFractionDigits="3" value="${details.ACMinServiceFee}"/> -
                    <fmt:formatNumber type="number" maxFractionDigits="3" value="${details.ACMaxServiceFee}"/> </span>
                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Փաստացի տոկոսադրույք </span><span>${details.ACMinLoan}% - ${details.ACMaxLoan}%</span>
                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Վարկի գումարը</span><span>
                    <fmt:formatNumber type="number" maxFractionDigits="3" value="${details.ACMinAmount}"/>  -  <fmt:formatNumber
                        type="number" maxFractionDigits="3"
                        value="${details.ACMaxAmount}"/></span>

                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Արժույթ</span><span>${requestScope.PageCurrancy}</span>
                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Վարկառուի տարիքը</span><span>${details.minAge}-${details.maxAge}</span>
                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Տեւողությունը</span><span>${details.ACMinPeriodMonth} Տարի - ${details.ACMaxPeriodMonth} Տարի</span>
                </div>
                <div class="col-4 flex align-items-center item">
                    <span>Նախնական որոշում</span><span>1 ժամ</span>
                </div>
            </div>

        </div>
    </div>

    <div class="padding-bt-60">
        <div class="inner-container">
            <form class="credit-form" action="CreditSend" name="main">
                <div class="row-lg row-flex flex align-items-stretch">

                    <div class="col-4 left-side mobile-left__sides">

                        <div class="row-md clearfix  form-row">
                            <div class="col-12">
                                <span class="label">Մուտքագրեք վարկի գումարը</span>
                                <div class="range-group">
                                    <div class="def-range int-outline">
                                        <script language="javascript" type="text/javascript">
                                            var delayTimer;
                                            function doSearch(text) {
                                                clearTimeout(delayTimer);
                                                delayTimer = setTimeout(function () {
                                                    document.main.submit()
                                                }, 2000); // Will do the ajax stuff after 1000 ms, or 1 s
                                            }

                                        </script>
                                        <c:if test="${requestScope.range!=null}">
                                            <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                       var="dropDownList2"
                                                       varStatus="loop">
                                                <%!
                                                    int amount;
                                                %>
                                                <%
                                                    if (request.getParameter("range") != null) {
                                                        amount = Integer.parseInt(String.valueOf(request.getParameter("range")));
                                                    } else if (request.getAttribute("range") != null) {
                                                        amount = Integer.parseInt(String.valueOf(request.getAttribute("range")));
                                                    } else if (request.getParameter("Amount") != null) {
                                                        amount = Integer.parseInt(String.valueOf(request.getParameter("Amount")));
                                                    } else {
                                                        amount = Integer.parseInt(String.valueOf(request.getAttribute("Amount")));
                                                    }
                                                %>
                                                <output style="display: none;" id="outputer"></output>

                                                <input type="text" name="value" min="${dropDownList2.minAmount}"
                                                       max="${dropDownList2.maxAmount}"
                                                       step="${dropDownList2.steps}"
                                                       value='<%=request.getAttribute("range")%>'
                                                       oninput="showVal(this.value)"
                                                       onchange="showVal(this.value)" id="amount">

                                                <input type="range" name="range"
                                                       min="${dropDownList2.minAmount}"
                                                       max="${dropDownList2.maxAmount}"
                                                       step="${dropDownList2.steps}"
                                                       value='<%=request.getAttribute("range")%>'
                                                       data-rangeslider=""
                                                       id="amount_range"
                                                       style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                       onchange="doSearch();">

                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${requestScope.Amount==null}">
                                            <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                       var="dropDownList3"
                                                       varStatus="loop">

                                                <output style="display: none;" id="outputer"></output>

                                                <input type="text" name="Amount"
                                                       min="${dropDownList3.minAmount}"
                                                       max="${dropDownList3.maxAmount}"
                                                       step="${dropDownList3.steps}"
                                                       value="${requestScope.minAmount}"
                                                       oninput="showVal(this.value)"
                                                       onchange="showVal(this.value)" id="amount">

                                                <input type="range" name="range"
                                                       min="${dropDownList3.minAmount}"
                                                       max="${dropDownList3.maxAmount}"
                                                       step="${dropDownList3.steps}"
                                                       value="${requestScope.minAmount}" data-rangeslider
                                                       id="amount_range"  onchange="doSearch();">


                                                <input type="hidden" name="value_url" id="value_amount_url">
                                                <c:set value="${dropDownList3.steps}" var="step"
                                                       scope="request"/>

                                            </c:forEach>
                                        </c:if>
                                    </div>
                                    <div class="def-select-box st-outline">
                                        <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                        <c:choose>
                                            <c:when test="${AMD == 'AMD'}">
                                                <select name="Currancy" >
                                                    <option value="AMD" selected>֏</option>
                                                </select>
                                            </c:when>
                                        </c:choose>
                                        <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                        <c:choose>
                                            <c:when test="${AMD == 'USD'}">
                                                <select name="Currancy" >
                                                    <option value="USD" selected>$</option>
                                                </select>
                                            </c:when>
                                        </c:choose>


                                        <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                        <c:choose>
                                            <c:when test="${AMD == 'EUR'}">
                                                <select name="Currancy" >
                                                    <option value="EUR" selected>€</option>
                                                </select>
                                            </c:when>
                                        </c:choose>


                                        <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                        <c:choose>
                                            <c:when test="${AMD == 'RUB'}">
                                                <select name="Currancy" >
                                                    <option value="RUB" selected>₽</option>
                                                </select>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row-md clearfix  form-row">
                            <div class="col-12">
                                <span class="label">Ժամկետը</span>
                                <div class="def-select-box">
                                    <%
                                    if(request.getAttribute("months")!=null){%>
                                    <c:set var="monthsSection" value='<%=request.getAttribute("months")%>'/>
                                    <%}else{ %>
                                    <c:set var="monthsSection" value='<%=request.getParameter("months")%>'/>
                                    <%}%>
                                    <c:choose>
                                        <c:when test="${monthsSection == '1' ||  monthsSection == '0'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" selected>1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '2'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2" selected>2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '3'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3" selected>3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '4'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4" selected>4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '5'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5" selected>5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '6'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6" selected>6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '7'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7" selected>7 Տարի</option>
                                                <option value="8">8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '8'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8" selected>8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '9'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8" >8 Տարի</option>
                                                <option value="9" selected>9 Տարի</option>
                                                <option value="10">10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${monthsSection == '10'}">
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8" >8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10" selected>10 Տարի</option>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="months" id="select_month"
                                                    onchange="document.main.submit();">
                                                <option value="1" >1 Տարի</option>
                                                <option value="2">2 Տարի</option>
                                                <option value="3">3 Տարի</option>
                                                <option value="4">4 Տարի</option>
                                                <option value="5">5 Տարի</option>
                                                <option value="6">6 Տարի</option>
                                                <option value="7">7 Տարի</option>
                                                <option value="8" >8 Տարի</option>
                                                <option value="9">9 Տարի</option>
                                                <option value="10" >10 Տարի</option>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="select_value" id="select_month_value" value="1">
                                    <input type="hidden" name="City" value='<%=request.getAttribute("city")%>'>
                                    <input type="hidden" name="MaxAmounr"
                                           value='<%=request.getParameter("MaxAmounr")%>'>
                                    <input type="hidden" name="PageToGo" value='AG'>
                                    <input value="${details.productCode}" name="id" type="hidden">

                                </div>
                            </div>
                        </div>
                        <div class="row-md clearfix  form-row">
                            <div class="col-12">
                                <div class="total-info">
                                    <div class="flex  margin-bottom-15">
                                        <div>
                                            <p>Տոկոսը սկսած</p>
                                            <p class="font-28 bold">${details.ACFactual}%</p>
                                            <%!
                                                static long AmountSecond;
                                                static long MonthsSet;
                                                static double percent;
                                                static long percentageSecond;
                                            %>
                                            <%
                                                percent = Double.parseDouble(String.valueOf(request.getAttribute("percent")));
                                                if (request.getParameter("months") != null && !request.getParameter("months").equals("0")) {
                                                    MonthsSet = Integer.parseInt(request.getParameter("months"));
                                                } else {
                                                    MonthsSet = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                                                }
                                                if (request.getParameter("Amount") != null) {
                                                    AmountSecond = Integer.parseInt(request.getParameter("Amount"));
                                                } else if (request.getParameter("range") != null) {
                                                    AmountSecond = Integer.parseInt(request.getParameter("range"));
                                                } else if (request.getAttribute("range") != null) {
                                                    AmountSecond = Integer.parseInt(String.valueOf(request.getAttribute("range")));
                                                } else {
                                                    AmountSecond = Integer.parseInt(String.valueOf(request.getAttribute("Amount")));
                                                }

                                            %>

                                            <%!
                                                long calculateMonthly(long  AmountOfLoan,long MonthsSet,double percent) {
                                                    percent /= 100.0;
                                                    double monthlyRate = percent /12;
                                                    long termInMonths = MonthsSet*12 ;
                                                    double monthlyPayment = (AmountOfLoan * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
                                                    return (long) monthlyPayment;
                                                }
                                            %>
                                        </div>
                                        <div>
                                            <p>Ամսեկան վճար</p>
                                            <h2 class="timer count-title count-number font-28 bold"
                                                data-to="<%=calculateMonthly(AmountSecond,MonthsSet,percent)%>"
                                                data-speed="1739"></h2>
                                        </div>
                                    </div>
                                    <a class="green-link width-icon" id="myBtn">
                                        <i class="icon-graphic"></i>
                                        <span>Վճարման գրաֆիկ</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
            </form>
            <c:set value='${details.gotoPage}' var="isAvilable"/>
            <c:choose>
                <c:when test="${isAvilable == 1}" >
                    <form action="CreditSend" method="post">
                        <input type="hidden" value="sendingEmail" name="Email">
                        <div class="col-8 right-side mobile-right__sides">
                            <div class="row-md clearfix  form-row">
                                <div class="col-6">
                                    <span class="label">Անուն</span>
                                    <div class="def-input int-outline">
                                        <input type="text" name="userName" placeholder="" required/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <span class="label">Ազգանուն</span>
                                    <div class="def-input int-outline">
                                        <input type="text" name="serName" placeholder="" required/>
                                    </div>
                                </div>
                            </div>

                            <div class="row-md clearfix  form-row">
                                <div class="col-6">
                                    <span class="label">Հեռախոս</span>
                                    <div class="def-input int-outline">
                                        <input type="text" name="phone" placeholder="098 889 898" required
                                               pattern="[0-9]{3}[0-9]{3}[0-9]{3}"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <span class="label">Էլ. հասցե</span>
                                    <div class="def-input int-outline">
                                        <input type="text" name="email" placeholder="" required
                                               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-md clearfix  form-row">
                                <div class="col-12">
                                    <div class="def-textarea txt-outline">
                                        <span class="label">Հաղորդագրություն</span>
                                        <textarea name="body" required></textarea>
                                    </div>
                                </div>
                            </div>
                            <script>
                                function isChecked(checkbox, sub1) {
                                    document.getElementById(sub1).disabled = !checkbox.checked;
                                }
                            </script>

                            <div class="row-md clearfix  form-row">
                                <div class="col-12">
                                    <label class="def-checkbox">
                                        <input type="checkbox" id="termsChkbx " onchange="isChecked(this, 'sub1')"/>
                                        <span><i class="icon-check"></i></span>
                                        <span class="item-text">Ես համաձայն եմ անձնական տվյալների պահպանման և մշակման <a target="_blank" href="/Policy" class="blue-dark-link"> պայմանների </a> հետ</span>
                                        <%
                                            if (request.getAttribute("message") != null) {
                                        %>
                                        <%=request.getAttribute("message")%>
                                        <%
                                            }
                                        %>
                                    </label>
                                </div>
                            </div>
                            <div class="row-md clearfix  form-row button-row">
                                <div class="col-12">
                                    <input type="submit" name="submit"
                                           class="def-button btn-green with-shadow max-width-160 margin-top-5" id="sub1"
                                           value="Ուղարկել հայտ" disabled="disabled"/>
                                    <input type="hidden" name="Bankid" value="${details.bankId}">
                                    <input type="hidden" name="FromPageName" value="AG">
                                    <input type="hidden" name="mfatualpage" value="${details.ACFactual}">


                                </div>
                            </div>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>

        </div>

        </form>
    </div>
</div>
</c:forEach>
</c:if>
<div id="myModal" class="modal modal-payment-graphic">

    <!-- Modal content -->
    <div class="modal-content">

        <div class="modal-header flex align-items-center space-between flex-wrap margin-bottom-20">
            <span class="close-btn" id="closeModal"><i class="icon-close font-16"></i></span>
            <div>
                <p class="title font-16">Վճարման գրաֆիկ
                    <a href="javascript:CreatePDFfromHTML()" class="green align-middle margin-left-15"><i
                            class="icon-download"></i></a>
                </p>
            </div>
            <div class="switch">
                <label>
                    <span>Հավասար մարում</span>
                    <input type="checkbox" id="checkbox">
                    <span class="lever"></span>
                    <span class="green_texts">Նվազող մարում</span>
                </label>
            </div>
        </div>

        <div class="modal-body scroll" id="menu2">
            <div class="clone-pdf">
                <div class="payment-graphic-table table table-style-two menu2-payment__graphic">
                    <div class="table-row head">
                        <div class="table-cell small"><span>Ամիս</span></div>
                        <div class="table-cell"><span>Վարկի մնացորդ</span></div>
                        <div class="table-cell large"><span>Վճարվող տոկոսագումար</span></div>
                        <div class="table-cell"><span>Մարում վարկից</span></div>
                        <div class="table-cell"><span>Ընդամենը վճարում</span></div>
                    </div>

                    <c:set value='<%=request.getParameter("Amount")%>' var="AmountStart" scope="request"/>
                    <c:set value='<%=request.getParameter("range_two")%>' var="AmountStartrange_two"
                           scope="request"/>

                    <c:forEach var="calc" items="${requestScope.LongList}" varStatus="TheCount">
                        <div class="table-row">
                            <div class="table-cell small"><span>${TheCount.count}</span></div>
                            <div class="table-cell">
                                <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                         value='${calc.first}'/></span>

                            </div>
                            <div class="table-cell large">
                                <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                         value='${calc.second}'/></span>
                            </div>
                            <div class="table-cell">

                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value='${calc.third}'/></span>

                            </div>
                            <div class="table-cell">

                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value='${calc.forth}'/></span>

                            </div>

                        </div>
                    </c:forEach>

                    <div class="table-row bottom">

                        <div class="table-cell small"><span></span></div>
                        <div class="table-cell"><span> <fmt:formatNumber type="number"
                                                                         maxFractionDigits="3"
                                                                         value=''/></span>
                        </div>
                        <div class="table-cell large"><span><fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value='${requestScope.SecondSecond}'/></span>
                        </div>
                        <div class="table-cell"><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='${requestScope.ThirdThird}'/></span>
                        </div>

                        <div class="table-cell"><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='${requestScope.ForthForth}'/></span>

                        </div>

                    </div>
                    <div class="table-row head">
                        <div class="table-cell small"><span>Ամիս</span></div>
                        <div class="table-cell"><span>Վարկի մնացորդ</span></div>
                        <div class="table-cell large"><span>Վճարվող տոկոսագումար2</span></div>
                        <div class="table-cell"><span>Մարում վարկից</span></div>
                        <div class="table-cell"><span>Ընդամենը վճարում</span></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal-body scroll" id="menu1">
            <div class="clone-pdf">
                <div class="payment-graphic-table table table-style-two menu1-payment__graphic">
                    <div class="table-row head">
                        <div class="table-cell small"><span>Ամիս</span></div>
                        <div class="table-cell"><span>Վարկի մնացորդ</span></div>
                        <div class="table-cell large"><span>Վճարվող տոկոսագումար</span></div>
                        <div class="table-cell"><span>Մարում վարկից</span></div>
                        <div class="table-cell"><span>Ընդամենը վճարում</span></div>
                    </div>

                    <%!

                        long sameResult(long Am, long Rs, long Mo) {
                            return (Am - Rs) / (Mo);
                        }
                    %>

                    <c:forEach items="${requestScope.RepaymentList}" var="repayment" varStatus="TheCount" begin="0" step="1" end="${requestScope.RepaymentList.size()-1}">
                        <div class="table-row">
                            <div class="table-cell small"><span>${TheCount.count}</span></div>
                            <div class="table-cell">

                                    <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                             value='${repayment.creditBalance}'/></span>

                            </div>
                            <div class="table-cell large">

                                <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                         value='${repayment.percentageOfAmount}'/></span>

                            </div>
                            <div class="table-cell">

                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value='${repayment.loanRepayment}'/></span>

                            </div>
                            <div class="table-cell">

                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value='${repayment.totalPayment}'/></span>

                            </div>

                        </div>
                    </c:forEach>
                    <%-- <% for (int i = 0; i <= MonthsToPay * 12; i++) {%>
                     <div class="table-row">
                         <div class="table-cell small"><span><%=i + 1%></span></div>
                         <div class="table-cell">
                             &lt;%&ndash; <%if(i==0){
                              %>
                              <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                       value='<%=AmountOfLoan-rangeSecond%>'/></span>

                              <%}else{%>&ndash;%&gt;

                             <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                      value='<%=VargiMnatsortHavasar(AmountOfLoan,rangeSecond,MonthsToPay*12,i-1)-sameResult(AmountOfLoan,rangeSecond,MonthsToPay*12)%>'/></span>
                             &lt;%&ndash; <%}%>&ndash;%&gt;
                         </div>
                         <div class="table-cell large">

                             <span> <fmt:formatNumber type="number" maxFractionDigits="3"
                                                      value='<%=dogosakumarHavasar(AmountOfLoan-AmountSecond,MonthsToPay*12,(percentageOfLoan/100))%>'/></span>

                         </div>
                         <div class="table-cell">

                             <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                     value='<%=sameResult(AmountOfLoan,rangeSecond,MonthsToPay*12) %>'/></span>

                         </div>
                         <div class="table-cell">

                             <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                     value='<%=entamenVjarumHavasar(AmountOfLoan-rangeSecond,MonthsToPay*12,(percentageOfLoan/100),NewAmountCalculatedHavasar) %>'/></span>

                         </div>

                     </div>
                     <% }%>--%>
                    <div class="table-row bottom">

                        <div class="table-cell small"><span></span></div>
                        <div class="table-cell"><span> <fmt:formatNumber type="number"
                                                                         maxFractionDigits="3"
                                                                         value=''/></span>
                        </div>
                        <div class="table-cell large"><span><fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value='${requestScope.Secound}'/></span>
                        </div>
                        <div class="table-cell"><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='${requestScope.Third}'/></span>
                        </div>
                        <div class="table-cell"><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='${requestScope.Four}'/></span>
                        </div>

                    </div>
                    <div class="table-row head">
                        <div class="table-cell small"><span>Ամիս</span></div>
                        <div class="table-cell"><span>Վարկի մնացորդ</span></div>
                        <div class="table-cell large"><span>Վճարվող տոկոսագումար</span></div>
                        <div class="table-cell"><span>Մարում վարկից</span></div>
                        <div class="table-cell"><span>Ընդամենը վճարում</span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="html-content"></div>
</div>

<footer>
    <div class="inner-container large">
        <div class="top flex space-between">
            <div class="flex align-items-center">
                <span class="logo">
                   <img src="${requestScope.getContextPath}/images/oferta.png" alt=""/>
                </span>
            </div>
            <div class="flex align-items-center">
                <span class="margin-right-15 hide-for-mb">Միացեք մեզ սոցիալական ցանցերում</span>
                <span>
                    <ul class="social-box">
                       <jsp:include page="include/Social.jsp"/>
                    </ul>
                </span>
            </div>
        </div>
        <div class="bottom flex space-between align-items-center">
            <span>
                <ul>
                    <li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
						<li><a href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
						<li><a href="Policy?Currancy=${requestScope.PageCurrancy}">Գաղտնիության քաղաքականություն</a></li>
                 <%--   <li class="show-for-tablet"><a href="index.php?page=banks">Մեր Գործընկերները</a></li>
                    <li class="show-for-tablet"><a href="index.php?page=usefull-links">Օգտակար հոդվածներ</a></li>--%>
                </ul>
            </span>
            <span class="text-right">
                2020 Oferta.am: Նյութերն օգտագործելիս՝, հղում դեպի oferta.am պարտադիր է:
            </span>
        </div>
    </div>
</footer>
<script>

    $(document).ready(function () {
        $("#checkbox").change(function () {
            if ($(this).is(":checked")) {
                $(".menu2-payment__graphic").css("display", "none");
                $(".menu1-payment__graphic").css("display", "table");
                $("#menu1").show();
                $("#menu2").hide();
            }
            else {
                $(".menu2-payment__graphic").css("display", "table");
                $(".menu1-payment__graphic").css("display", "none");
                $("#menu1").hide();
                $("#menu2").show();
            }
        })
    });
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type="text/javascript" src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>

<script>
    $(".clone-pdf").clone().appendTo(".html-content");

    function CreatePDFfromHTML() {
        var HTML_Width = $(".html-content").width();
        var HTML_Height = $(".html-content").height();
        var top_left_margin = 15;
        var PDF_Width = HTML_Width + (top_left_margin * 2);
        var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
        var canvas_image_width = HTML_Width;
        var canvas_image_height = HTML_Height;

        var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;

        html2canvas($(".html-content")[0]).then(function (canvas) {
            var imgData = canvas.toDataURL("image/jpeg", 1.0);
            var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);
            for (var i = 1; i <= totalPDFPages; i++) {
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height * i) + (top_left_margin * 4), canvas_image_width, canvas_image_height);
            }
            pdf.save("Your_PDF_Name.pdf");
        });
    }

</script>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/rangeslider.js/2.3.0/rangeslider.min.js'></script>
<script src="js/main.js"></script>
<script src="js/search.js"></script>
<script src="js/range.js"></script>
<script src="js/range_two.js"></script>
<script src="js/modal.js"></script>
<script src="libs/owl/owl.carousel.min.js"></script>
<script src="js/carousel.js"></script>
</body>
</html>

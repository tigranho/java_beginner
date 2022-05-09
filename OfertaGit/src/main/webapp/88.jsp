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
    <jsp:include page="include/google.jsp"/><title>Oferta.am</title>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../images/favicon.ico" rel="shortcut icon">

    <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="css/global.css" type="text/css">
    <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
   <link rel="stylesheet" href="css/style.css">
    <style>
        #test span {
            animation: fadein 5s;
            -moz-animation: fadein 5s; /* Firefox */
            -webkit-animation: fadein 5s; /* Safari and Chrome */
            -o-animation: fadein 5s; /* Opera */
        }

        @keyframes fadein {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @-moz-keyframes fadein { /* Firefox */
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @-webkit-keyframes fadein { /* Safari and Chrome */
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @-o-keyframes fadein { /* Opera */
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
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
                   <a href="App?PageLanguage=${requestScope.Pagelanguage}"><img src="../images/oferta1.jpg" alt=""/></a>
               </span>

                        <span class="hide-for-tablet">
                   <ul>
                     	<li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a
                                            href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
									<%--<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}">Մեր Գործընկերները</a></li>--%>
								<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}&&City=<%=request.getAttribute("city")%>">Մեր Գործընկերները</a></li>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
                   </ul>
               </span>
                    </div>
                    <div class="right flex align-items-center">
                        <span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i> ${requestScope.City}    </span>
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
                       <span class="compere-box show-for-tablet relative">
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
                    </div>
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
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=Cash&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=OFF">ՔԱՐՏԵՐ</a>
                                    </li>
                                </ul>
                            </c:forEach>
                        </c:if>
                    </div>
                    <!-- starting compare Section -->

                    <%--    <%
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
                            <form action="CompareDeposit" method="get" name="DepositCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                           <c:if test="${requestScope.comparListDeposit != null}">
                            <ul>
                                <li>
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span onclick="document.DepositCompare.submit();">Ավանդ</span>
                                    <span class="bold font-14">${counterDeposit}</span>
                                     <i class="icon-delete" onclick="document.Delete.submit();"></i>

                                </li>
                                 <form action="DepositClient" method="get" name="Delete">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits" type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                        <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                      <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                             type="hidden">
                                 <%--    <input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>
                                    </form>
                            </ul>
                           </c:if>
                            <form action="CompareMortgage" method="get" name="MortgageCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">

                            </form>
                            <c:if test="${requestScope.comparListMortgage != null}">
                            <ul>
                                <li>
                                    <c:forEach var="MortgagCompare" items="${requestScope.comparListMortgage}"
                                               varStatus="TheCount">
                                        <c:set var="counterMortgag" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                      <span onclick="document.MortgageCompare.submit();">Հիփոթեք</span>
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteHipotek.submit();"></i>
                                </li>
                                 <form action="DepositClient" method="get" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                      <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                      <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                             type="hidden">
                                 <%--    <input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>
                                    </form>
                            </ul>
                            </c:if>
                                    <form action="CompareConsumer" method="get" name="ConsumerCompare">
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
                                <form action="DepositClient" method="get" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                     <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                            type="hidden">
                                    <%-- <input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>
                                    </form>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="get" name="CarLoanCompare">
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
                                <form action="DepositClient" method="get" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                     <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                            type="hidden">
                                    <%-- <input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>
                                    </form>
                            </c:if>
                            <form action="CompareMicro" method="get" name="MicroCompare">
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
                                <form action="DepositClient" method="get" name="DeleteMicro">
                                        <input type="hidden" name="pageNameToDelete" value="ՄԻԿՐՈՎԱՐԿ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                     <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                            type="hidden">
                                  <%--   <input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>

                                    </form>
                            </ul>
                            </c:if>
                            <form action="CompareAg" method="get" name="AgCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListAg != null}">
                            <ul>
                                <li>
                                    <c:forEach var="AgCompare" items="${requestScope.comparListAg}"
                                               varStatus="TheCount">
                                        <c:set var="counterAg" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                      <span onclick="document.AgCompare.submit();">Գյուղատնտեսական</span>
                                      <span class="bold font-14">${counterAg}</span>
                                        <i type="submit" class="icon-delete" onclick="document.DeleteAG.submit();"></i>
                                </li>
                                <form action="DepositClient" method="get" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Credits"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                     <input name="ProductCode" value="<%=request.getParameter("ProductCode")%>"
                                            type="hidden">
                                     <%--<input name="value_two" value="<%=request.getParameter("value_two")%>"
                                            type="hidden">--%>
                                    </form>
                            </ul>
                            </c:if>

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

    <c:if test="${requestScope.TheList != null}">
        <c:forEach items="${requestScope.TheList}" var="details">
            <div class="bg-white-dark padding-bottom-60 bank-item-page">
                <div class="inner-container">
                    <div class="bank-header">
                        <div class="bg" style="background-image: url('../../../images/10.svg')">
                            <div class="top">
                                <p class="back-link">

                                        <%--<a href="javascript:history.back()"
                                           class="green-link width-icon">


                                            <i class="icon-arrow-left font-9 margin-right-5"></i>
                                            <span>Վերադառնալ ցուցակին </span>
                                        </a>--%>
                                </p>
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
                                <p class="font-36">${details.bankName}</p>
                                <c:if test="${requestScope.productNameList != null}">
                                <c:forEach items="${requestScope.productNameList}" var="productName" varStatus="page">
                                <c:if test="${details.productCode == productName.productCode}">
                                <p class="font-16">${productName.productNameAm}</p>
                            </div>
                            </c:if>
                            </c:forEach>
                            </c:if>
                            <c:set var="sendRequest" value="${details.sendRequest}"/>
                            <c:set var="gotoPage" value="${details.gotoPage}"/>
                            <c:set var="lastLogic" value="${details.lastLogic}"/>

                            <c:choose>
                                <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic== 0}">
                                    <a href="${details.bankLink}"
                                       class="def-button btn-green with-shadow margin-top-15 max-width-160"
                                       target="_blank">Անցնել բանկի
                                        էջ</a>
                                </c:when>
                                <c:when test="${gotoPage == 0 || sendRequest== 0 && lastLogic== 1}">

                                </c:when>
                            </c:choose>


                        </div>
                    </div>
                </div>


                <div class="row row-flex flex flex-wrap crediting-info width-percent-80">
                    <div class="col-4 flex align-items-center item">
                        <c:set var="monthsSectionMain" value='<%=request.getParameter("months")%>'/>
                        <c:set var="Perc" value='<%=request.getAttribute("Per")%>' scope="request"/>
                        <c:set var="Per" value="${details.timeLine}" scope="request"/>
                        <%!
                            static double percentageTopper;
                            double percentageToStart;
                            static int monthsMain2;
                        %>

                        <%
                            if(request.getAttribute("monthsSectionMain")!= null){
                                monthsMain2 = Integer.parseInt(String.valueOf(request.getAttribute("monthsSectionMain")));
                                if (monthsMain2 == 30 || monthsMain2 == 90 || monthsMain2 == 180 || monthsMain2 == 360 || monthsMain2 == 540 || monthsMain2 == 720 || monthsMain2 == 1080) {
                                    monthsMain2 /= 30;
                                }
                            }else{
                                monthsMain2 = Integer.parseInt(String.valueOf(request.getParameter("months")));
                                if (monthsMain2 == 30 || monthsMain2 == 90 || monthsMain2 == 180 || monthsMain2 == 360 || monthsMain2 == 540 || monthsMain2 == 720 || monthsMain2 == 1080) {
                                    monthsMain2 /= 30;
                                }
                            }

                            if (request.getAttribute("Perc") != null) {
                                percentageToStart = Double.parseDouble(String.valueOf(request.getAttribute("Perc")));
                            } else {
                                percentageToStart = Double.parseDouble(String.valueOf(request.getAttribute("Per")));
                            }
                        %>
                        <%
                            if (percentageToStart != 0) {
                                percentageTopper = percentageToStart;
                            } else {
                                percentageTopper = 0;
                            }
                        %>
                        <c:set var="currancy" value='<%=request.getParameter("Currancy")%>'/>
                        <c:choose>
                            <c:when test="${currancy == 'AMD' && Perc!=0}">

                                <span>Տոկոսադրույքը</span><span><%=percentageTopper%></span>
                                <c:set value='<%=percentageTopper%>' var="PercentageDFromTop" scope="request"/>
                                <%
                                    percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("Per")));
                                %>

                            </c:when>
                            <c:when test="${currancy == 'AMD'}">
                                <c:choose>
                                    <c:when test="<%=monthsMain2== 1 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth1}%</span>
                                        <c:set value="${details.amdMonth1}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 3  %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth3}%</span>
                                        <c:set value="${details.amdMonth3}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 6 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth6}%</span>
                                        <c:set value="${details.amdMonth6}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 9 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth9}%</span>
                                        <c:set value="${details.amdMonth9}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 12 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth12}%</span>
                                        <c:set value="${details.amdMonth12}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 18 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth18}%</span>
                                        <c:set value="${details.amdMonth18}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 24 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth24}%</span>
                                        <c:set value="${details.amdMonth24}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 36 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.amdMonth36}%</span>
                                        <c:set value="${details.amdMonth36}" var="PercentageD" scope="request"/>
                                        <%
                                            float checkValue = Float.parseFloat(String.valueOf(request.getAttribute("PercentageD")));
                                            if (checkValue == 0) {
                                                percentageTopper = 0.0;
                                            } else {
                                                percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                            }

                                        %>
                                    </c:when>
                                </c:choose>
                            </c:when>

                            <c:when test="${currancy == 'USD'}">
                                <c:choose>
                                    <c:when test="<%=monthsMain2== 1 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth1}%</span>
                                        <c:set value="${details.usdMonth1}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 3 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth3}%</span>
                                        <c:set value="${details.usdMonth3}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 6 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth6}%</span>
                                        <c:set value="${details.usdMonth6}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 9 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth9}%</span>
                                        <c:set value="${details.usdMonth9}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 12 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth12}%</span>
                                        <c:set value="${details.usdMonth12}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 18 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth18}%</span>
                                        <c:set value="${details.usdMonth18}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 24 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth24}%</span>
                                        <c:set value="${details.usdMonth24}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 36 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.usdMonth36}%</span>
                                        <c:set value="${details.usdMonth36}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${currancy == 'EUR'}">
                                <c:choose>
                                    <c:when test="<%=monthsMain2== 1 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth1}%</span>
                                        <c:set value="${details.eurMonth1}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 3 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth3}%</span>
                                        <c:set value="${details.eurMonth3}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 6 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth6}%</span>
                                        <c:set value="${details.eurMonth6}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 9 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth9}%</span>
                                        <c:set value="${details.eurMonth9}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 12 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth12}%</span>
                                        <c:set value="${details.eurMonth12}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 18 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth18}%</span>
                                        <c:set value="${details.eurMonth18}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 24 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth24}%</span>
                                        <c:set value="${details.eurMonth24}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 36 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.eurMonth36}%</span>
                                        <c:set value="${details.eurMonth36}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                </c:choose>

                            </c:when>
                            <c:when test="${currancy == 'RUB'}">
                                <c:choose>
                                    <c:when test="<%=monthsMain2== 1 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth1}%</span>
                                        <c:set value="${details.rubMonth1}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 3 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth3}%</span>
                                        <c:set value="${details.rubMonth3}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 6 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth6}%</span>
                                        <c:set value="${details.rubMonth6}" var="PercentageDFromTop" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 9 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth9}%</span>
                                        <c:set value="${details.rubMonth9}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 12 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth12}%</span>
                                        <c:set value="${details.rubMonth12}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 18 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth18}%</span>
                                        <c:set value="${details.rubMonth18}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 24 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth24}%</span>
                                        <c:set value="${details.rubMonth24}" var="PercentageD" scope="request"/>
                                        <%
                                            percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                        %>
                                    </c:when>
                                    <c:when test="<%=monthsMain2== 36 %>">
                                        <span>Տոկոսադրույքը</span><span>${details.rubMonth36}%</span>
                                        <c:set value="${details.rubMonth36}" var="PercentageD" scope="request"/>
                                        <%
                                            int checkValue = (int) request.getAttribute("PercentageD");
                                            if (checkValue == 0) {

                                            } else {
                                                percentageTopper = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                            }

                                        %>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>

                    </div>

                    <!-- comment first -->

                    <div class="col-8 flex align-items-center item">
                        <c:if test="${requestScope.CommentsList != null}">
                            <c:forEach items="${requestScope.CommentsList}" var="comment">
                                <span>${comment.comment1_Am}</span>
                            </c:forEach>
                        </c:if>
                    </div>

                    <%!
                        int monthsMain;
                    %>
                    <%
                        if (request.getParameter("months") != null) {
                            monthsMain = Integer.parseInt(request.getParameter("months"));
                            if (monthsMain == 30 || monthsMain == 90 || monthsMain == 180 || monthsMain == 360 || monthsMain == 540 || monthsMain == 720 || monthsMain == 1080) {
                                monthsMain /= 30;
                            }
                        } else {
                            monthsMain = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                            if (monthsMain == 30 || monthsMain == 90 || monthsMain == 180 || monthsMain == 360 || monthsMain == 540 || monthsMain == 720 || monthsMain == 1080) {
                                monthsMain /= 30;
                            }
                        }
                        System.out.println(monthsMain);
                    %>
                    <div class="col-4 flex align-items-center item">
                        <span>Ժամկետ</span><span> <%=monthsMain%> Ամիս</span>
                    </div>
                    <div class="col-8 flex align-items-center item">
                        <c:if test="${requestScope.CommentsList != null}">
                            <c:forEach items="${requestScope.CommentsList}" var="comment">
                                <span>${comment.comment2_Am}</span>
                            </c:forEach>
                        </c:if>
                            <%--<c:set value="${requestScope.PageCurrancy}" var="pageCurrancy"/>
                            <c:choose>
                                <c:when test="${pageCurrancy == 'AMD'}">
                                    <span>Արժույթ</span><span>֏</span>
                                </c:when>
                                <c:when test="${pageCurrancy == 'USD'}">
                                    <span>Արժույթ</span><span>$</span>
                                </c:when>
                                <c:when test="${pageCurrancy == 'EUR'}">
                                    <span>Արժույթ</span><span>€</span>
                                </c:when>
                                <c:when test="${pageCurrancy == 'RUB'}">
                                    <span>Արժույթ</span><span>‎₽</span>
                                </c:when>
                            </c:choose>--%>

                    </div>


                    <c:set value="${requestScope.PageCurrancy}" var="currancy"/>
                    <c:choose>
                        <c:when test="${currancy == 'AMD'}">
                            <div class="col-4 flex align-items-center item">
                                <span>Կանխավճար</span><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                              value="${details.DMinAmount}"/>  - <fmt:formatNumber
                                    type="number" maxFractionDigits="3"
                                    value="${details.DMaxAmount}"/> </span>
                            </div>
                        </c:when>
                        <c:when test="${currancy == 'USD'}">
                            <div class="col-8 flex align-items-center item">
                                <span>Կանխավճար</span><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                              value="${details.DMinAmount}"/>  -  <fmt:formatNumber
                                    type="number" maxFractionDigits="3"
                                    value="${details.DMaxAmount}"/> </span>
                            </div>
                        </c:when>
                        <c:when test="${currancy == 'EUR'}">
                            <div class="col-4 flex align-items-center item">
                                <span>Կանխավճար</span><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                              value="${details.DMinAmount}"/>  -  <fmt:formatNumber
                                    type="number" maxFractionDigits="3"
                                    value="${details.DMaxAmount}"/> </span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-4 flex align-items-center item">
                                <span>Կանխավճար</span><span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                              value="${details.DMinAmount}"/>  -  <fmt:formatNumber
                                    type="number" maxFractionDigits="3"
                                    value="${details.DMaxAmount}"/> </span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-8 flex align-items-center item">
                        <c:if test="${requestScope.CommentsList != null}">
                            <c:forEach items="${requestScope.CommentsList}" var="comment">
                                <span>${comment.comment3_Am}</span>
                            </c:forEach>
                        </c:if>
                    </div>
                        <%--<c:set value="${details.timeLine}" var="monthsInMain" scope="request"/>--%>

                </div>
            </div>
        </c:forEach>
    </c:if>


</div>

<div class="padding-bt-60">
    <div class="inner-container">
        <form class="Cred-form" action="Credits" name="main">
            <div class="row-lg row-flex flex align-items-stretch main-left-right__side">
                <div class="col-4 left-side left-side__mobile">
                    <div class="row-md clearfix form-row">
                        <div class="col-12">
                            <span class="label">Մուտքագրեք ավանդի գումարը</span>
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
                                                   onchange="showVal(this.value)" id="amount" inputmode="numeric">

                                            <input type="range" name="range" min="${dropDownList2.minAmount}"
                                                   max="${dropDownList2.maxAmount}"
                                                   step="${dropDownList2.steps}"
                                                   value='<%=request.getAttribute("range")%>' data-rangeslider=""
                                                   id="amount_range"
                                                   style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                   onchange="doSearch();">

                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${requestScope.range == null}">
                                        <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                   var="dropDownList3"
                                                   varStatus="loop">

                                            <output style="display: none;" id="outputer"></output>

                                            <input type="text" name="Amount" min="${dropDownList3.minAmount}"
                                                   max="${dropDownList3.maxAmount}"
                                                   step="${dropDownList3.steps}"
                                                   value="${dropDownList3.minAmount}"
                                                   oninput="showVal(this.value)"
                                                   onchange="showVal(this.value)" id="amount" inputmode="numeric">

                                            <input type="range" name="range" min="${dropDownList3.minAmount}"
                                                   max="${dropDownList3.maxAmount}"
                                                   step="${dropDownList3.steps}"
                                                   value="${dropDownList3.minAmount}" data-rangeslider
                                                   id="amount_range" onchange="doSearch();">


                                            <input type="hidden" name="value_url" id="value_amount_url">
                                            <c:set value="${dropDownList3.steps}" var="step" scope="request"/>

                                        </c:forEach>
                                    </c:if>
                                </div>
                                <div class="def-select-box st-outline">

                                    <c:set var="AMD" value="${requestScope.PageCurrancy}"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'AMD'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD" selected>֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'USD'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD" selected>$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'EUR'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR" selected>€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'RUB'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB" selected>₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-md clearfix form-row">
                        <div class="col-12">
                            <span class="label">Ժամկետը</span>
                            <div class="def-select-box">
                                <c:set var="curranyc" value="${requestScope.PageCurrancy }"/>
                                <c:choose>
                                    <c:when test="${curranyc == 'AMD'}">
                                        <c:if test="${requestScope.TheList != null}">
                                            <c:forEach items="${requestScope.TheList}" var="amountCheck">
                                                <c:set var="DisableMonth1" value="${amountCheck.amdMonth1}"/>
                                                <c:set var="DisableMonth2" value="${amountCheck.amdMonth3}"/>
                                                <c:set var="DisableMonth3" value="${amountCheck.amdMonth6}"/>
                                                <c:set var="DisableMonth4" value="${amountCheck.amdMonth9}"/>
                                                <c:set var="DisableMonth5" value="${amountCheck.amdMonth12}"/>
                                                <c:set var="DisableMonth6" value="${amountCheck.amdMonth18}"/>
                                                <c:set var="DisableMonth7" value="${amountCheck.amdMonth24}"/>
                                                <c:set var="DisableMonth8" value="${amountCheck.amdMonth36}"/>
                                                <%--    <c:out value="${DisableMonth1}"/>
                                                    <c:out value="${DisableMonth2}"/>
                                                    <c:out value="${DisableMonth3}"/>
                                                    <c:out value="${DisableMonth4}"/>
                                                    <c:out value="${DisableMonth5}"/>
                                                    <c:out value="${DisableMonth6}"/>
                                                    <c:out value="${DisableMonth7}"/>
                                                    <c:out value="${DisableMonth8}"/>--%>


                                            </c:forEach>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${curranyc == 'USD'}">
                                        <c:if test="${requestScope.TheList != null}">
                                            <c:forEach items="${requestScope.TheList}" var="amountCheck">
                                                <c:set var="DisableMonth1" value="${amountCheck.usdMonth1}"/>
                                                <c:set var="DisableMonth2" value="${amountCheck.usdMonth3}"/>
                                                <c:set var="DisableMonth3" value="${amountCheck.usdMonth6}"/>
                                                <c:set var="DisableMonth4" value="${amountCheck.usdMonth9}"/>
                                                <c:set var="DisableMonth5" value="${amountCheck.usdMonth12}"/>
                                                <c:set var="DisableMonth6" value="${amountCheck.usdMonth18}"/>
                                                <c:set var="DisableMonth7" value="${amountCheck.usdMonth24}"/>
                                                <c:set var="DisableMonth8" value="${amountCheck.usdMonth36}"/>
                                                <%-- <c:out value="${DisableMonth1}"/>
                                                  <c:out value="${DisableMonth2}"/>
                                                  <c:out value="${DisableMonth3}"/>
                                                  <c:out value="${DisableMonth4}"/>
                                                  <c:out value="${DisableMonth5}"/>
                                                  <c:out value="${DisableMonth6}"/>
                                                  <c:out value="${DisableMonth7}"/>
                                                  <c:out value="${DisableMonth8}"/>--%>
                                            </c:forEach>
                                        </c:if>
                                    </c:when>
                                    <c:when test="${curranyc == 'EUR'}">
                                        <c:if test="${requestScope.TheList != null}">
                                            <c:forEach items="${requestScope.TheList}" var="amountCheck">
                                                <c:set var="DisableMonth1" value="${amountCheck.eurMonth1}"/>
                                                <c:set var="DisableMonth2" value="${amountCheck.eurMonth3}"/>
                                                <c:set var="DisableMonth3" value="${amountCheck.eurMonth6}"/>
                                                <c:set var="DisableMonth4" value="${amountCheck.eurMonth9}"/>
                                                <c:set var="DisableMonth5" value="${amountCheck.eurMonth12}"/>
                                                <c:set var="DisableMonth6" value="${amountCheck.eurMonth18}"/>
                                                <c:set var="DisableMonth7" value="${amountCheck.eurMonth24}"/>
                                                <c:set var="DisableMonth8" value="${amountCheck.eurMonth36}"/>
                                                <%--<c:out value="${DisableMonth1}"/>
                                                 <c:out value="${DisableMonth2}"/>
                                                 <c:out value="${DisableMonth3}"/>
                                                 <c:out value="${DisableMonth4}"/>
                                                 <c:out value="${DisableMonth5}"/>
                                                 <c:out value="${DisableMonth6}"/>
                                                 <c:out value="${DisableMonth7}"/>
                                                 <c:out value="${DisableMonth8}"/>--%>
                                            </c:forEach>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${curranyc == 'RUB'}">
                                        <c:if test="${requestScope.TheList != null}">
                                            <c:forEach items="${requestScope.TheList}" var="amountCheck">
                                                <c:set var="DisableMonth1" value="${amountCheck.rubMonth1}"/>
                                                <c:set var="DisableMonth2" value="${amountCheck.rubMonth3}"/>
                                                <c:set var="DisableMonth3" value="${amountCheck.rubMonth6}"/>
                                                <c:set var="DisableMonth4" value="${amountCheck.rubMonth9}"/>
                                                <c:set var="DisableMonth5" value="${amountCheck.rubMonth12}"/>
                                                <c:set var="DisableMonth6" value="${amountCheck.rubMonth18}"/>
                                                <c:set var="DisableMonth7" value="${amountCheck.rubMonth24}"/>
                                                <c:set var="DisableMonth8" value="${amountCheck.rubMonth36}"/>
                                                <%--  <c:out value="${DisableMonth1}"/>
                                                   <c:out value="${DisableMonth2}"/>
                                                   <c:out value="${DisableMonth3}"/>
                                                   <c:out value="${DisableMonth4}"/>
                                                   <c:out value="${DisableMonth5}"/>
                                                   <c:out value="${DisableMonth6}"/>
                                                   <c:out value="${DisableMonth7}"/>
                                                   <c:out value="${DisableMonth8}"/>--%>
                                            </c:forEach>
                                        </c:if>
                                    </c:when>

                                </c:choose>


                                <%--<c:set var="monthsSection2" value='<%=request.getParameter("months")%>'/>--%>
                                <%!
                                    static int monthsFinal;
                                %>

                                <%
                                    monthsFinal =Integer.parseInt(String.valueOf(request.getParameter("months")));
                                    if (monthsFinal == 30 || monthsFinal == 90 || monthsFinal == 180 || monthsFinal == 360 || monthsFinal == 540 || monthsFinal == 720 || monthsFinal == 1080) {
                                        monthsFinal /= 30;
                                    }
                                %>
                                <c:set value="<%=monthsFinal%>" var="monthsSection" scope="request"/>
                                <c:choose>

                                    <c:when test="${monthsSection == '1' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '1' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1'&& DisableMonth5 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1' && DisableMonth4 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1' && DisableMonth3 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1' &&  DisableMonth2 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '1' && DisableMonth1 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '3' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '3' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '3' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '3'&& DisableMonth5 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '3' && DisableMonth4 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '3' && DisableMonth3 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '3' &&  DisableMonth2 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3" selected>3 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '6' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '6' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '6' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '6'&& DisableMonth5 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '6' && DisableMonth4 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '6' && DisableMonth3 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6" selected>6 ամիս</option>
                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '9' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9" selected>9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '9' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9" selected>9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '9' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9" selected>9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '9'&& DisableMonth5 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9" selected>9 ամիս</option>
                                            <option value="12">12 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '9' && DisableMonth4 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9" selected>9 ամիս</option>
                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '12' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12" selected>12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '12' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12" selected>12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '12' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12" selected>12 ամիս</option>
                                            <option value="18">18 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '12'&& DisableMonth5 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12" selected>12 ամիս</option>

                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '18' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18" selected>18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '18' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18" selected>18 ամիս</option>
                                            <option value="24">24 ամիս</option>

                                        </select>
                                    </c:when>
                                    <c:when test="${monthsSection == '18' && DisableMonth6 > 0 }">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18" selected>18 ամիս</option>

                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '24' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24" selected>24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:when>

                                    <c:when test="${monthsSection == '24' && DisableMonth7 > 0}">

                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24" selected>24 ամիս</option>

                                        </select>
                                    </c:when>


                                    <c:when test="${monthsSection == '36' && DisableMonth8 > 0}">
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">

                                            <option value="1">1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36" selected>36 ամիս</option>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="months" id="select_month"
                                                onchange="document.main.submit();">
                                            <option value="1" selected>1 ամիս</option>
                                            <option value="3">3 ամիս</option>
                                            <option value="6">6 ամիս</option>
                                            <option value="9">9 ամիս</option>
                                            <option value="12">12 ամիս</option>
                                            <option value="18">18 ամիս</option>
                                            <option value="24">24 ամիս</option>
                                            <option value="36">36 ամիս</option>
                                        </select>
                                    </c:otherwise>

                                </c:choose>


                                <%-- <input type="hidden" name="select_value" id="select_month_value" value="1">--%>
                                <input type="hidden" name="City" value='<%=request.getAttribute("city")%>'>
                                <input type="hidden" name="MaxAmounr" value='<%=request.getParameter("MaxAmounr")%>'>

                            </div>
                        </div>
                    </div>
                    <div class="row-md clearfix form-row">
                        <div class="col-12">

                            <c:forEach items='${requestScope.TheList}'
                                       var="checker"
                                       varStatus="loop">
                                <c:set var="DEquippingPossibilities" value="${checker.DEquippingPossibilities}"/>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${DEquippingPossibilities=='Այո'}">
                                    <span class="label">Ամսեկան լրացումներ</span>
                                    <div class="def-range int-outline">
                                        <script language="javascript" type="text/javascript">
                                            var delayTimer;

                                            function doSearch2(text) {
                                                clearTimeout(delayTimer);
                                                delayTimer = setTimeout(function () {
                                                    document.main.submit()
                                                }, 2000); // Will do the ajax stuff after 1000 ms, or 1 s
                                            }

                                        </script>

                                        <c:if test='<%=request.getParameter("range_two")!=null %>'>
                                            <output style="display: none;" id="outputer"></output>

                                            <c:forEach items='${requestScope.dropDownsListWithCurrancy}'
                                                       var="dropDownList2"
                                                       varStatus="loop">
                                                <output style="display: show;" id="outputer"></output>
                                                <!-- esdexna -->
                                                <input type="text" name="value_two" min="0"
                                                       max="${dropDownList2.maxAmount}"
                                                       step="${dropDownList2.steps}"
                                                       value="<%=request.getAttribute("value_url")%>"
                                                       oninput="showVal(this.value)"
                                                       onchange="showVal(this.value)" id="amount_two" inputmode="numeric">

                                                <input type="range" name="range_two" min="0"
                                                       max="${dropDownList2.maxAmount}"
                                                       step="${dropDownList2.steps}"
                                                       value="<%=request.getAttribute("value_url")%>"
                                                       data-rangeslider=""
                                                       id="amount_range_two"
                                                       style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                       oninput="doSearch2();">


                                                <%-- <input type="text" name="value" min="${dropDownList2.minAmount}"
                                                        max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                        value='<%=request.getAttribute("range")%>'
                                                        oninput="showVal(this.value)"
                                                        onchange="showVal(this.value)" id="amount">

                                                 <input type="range" name="range" min="${dropDownList2.minAmount}"
                                                        max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                        value='<%=request.getAttribute("range")%>' data-rangeslider=""
                                                        id="amount_range"
                                                        style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                        onchange="document.main.submit();">--%>
                                            </c:forEach>
                                        </c:if>
                                        <c:set value='<%=request.getAttribute("value_url")%>' var="valueFromslider"
                                               scope="request"/>
                                        <%!
                                            static int AmountRanger;
                                        %>
                                        <%
                                            if (request.getAttribute("valueFromslider") != null) {
                                                AmountRanger = Integer.parseInt(String.valueOf(request.getAttribute("valueFromslider")));
                                            } else if (request.getAttribute("Amount") != null) {
                                                AmountRanger = Integer.parseInt(String.valueOf(request.getAttribute("Amount")));
                                            }
                                        %>
                                        <c:if test='<%=request.getParameter("range_two")== null %>'>
                                            <c:forEach items='${requestScope.dropDownsListWithCurrancy}'
                                                       var="dropDownList"
                                                       varStatus="loop">
                                                <output style="display: none;" id="outputer"></output>
                                                <!-- esdexna -->
                                                <input type="text" name="value_two" min="0"
                                                       max="${dropDownList.maxAmount}"
                                                       step="<%=request.getAttribute("step")%>"
                                                       value="0"
                                                       oninput="showVal(this.value)"
                                                       onchange="showVal(this.value)" id="amount_two" inputmode="numeric">

                                                <input type="range" name="range_two" min="0"
                                                       max="${dropDownList.maxAmount}"
                                                       step="<%=request.getAttribute("step")%>"
                                                       value="0" data-rangeslider=""
                                                       id="amount_range_two"
                                                       style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                       oninput="doSearch2();">

                                                <%-- <input type="text" name="value" min="${dropDownList2.minAmount}"
                                                        max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                        value='<%=request.getAttribute("range")%>'
                                                        oninput="showVal(this.value)"
                                                        onchange="showVal(this.value)" id="amount">

                                                 <input type="range" name="range" min="${dropDownList2.minAmount}"
                                                        max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                        value='<%=request.getAttribute("range")%>' data-rangeslider=""
                                                        id="amount_range"
                                                        style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                        onchange="document.main.submit();">--%>
                                            </c:forEach>
                                        </c:if>

                                            <%--<c:if test='<%=request.getParameter("range")==null && request.getParameter("range_two")==null %>'>
                                                <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                           var="dropDownList3"
                                                           varStatus="loop">



                                                    <input type="text" name="value" min="0"
                                                           max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                                           value="0"
                                                           oninput="showVal(this.value)"
                                                           onchange="showVal(this.value)" id="amount">

                                                    <input type="range" name="range" min="0"
                                                           max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                                           value="0" data-rangeslider=""
                                                           id="amount_range"
                                                           oninput="doSearch();">


                                                    <input type="hidden" name="value_url" id="value_amount_url">



                                                </c:forEach>
                                            </c:if>--%>
                                    </div>
                                </c:when>
                                <c:when test="${DEquippingPossibilities=='Ոչ'}">

                                </c:when>
                            </c:choose>


                        </div>
                    </div>
                </div>
                <div class="col-8 right-side right-side__mobile">
                    <div class="row-lg">
                        <div class="col-4 col rigthtwo-one">
                            <span class="label">Ավանդի շահութաբերությունը</span>
                            <%!
                                long blueResult;
                                float bluePreResult;
                                long ligthGreenResult;
                                long DarkGreenResult;
                                float DarkGreenPreResult;
                                long totalResult;

                                long amountMainForRange;
                                long amountSecondForRange;
                                float percentageForRange;
                                float monthForRange;
                            %>
                            <c:set var="rangeSet" value="${requestScope.range}" scope="request"/>
                            <%
                                if (request.getParameter("range") != null) {
                                    amountMainForRange = Integer.parseInt(String.valueOf(request.getParameter("range")));
                                } else {
                                    amountMainForRange = Integer.parseInt(String.valueOf(request.getAttribute("rangeSet")));
                                }
                                if (request.getParameter("range_two") != null) {
                                    amountSecondForRange = Integer.parseInt(String.valueOf(request.getParameter("range_two")));
                                } else {
                                    amountSecondForRange = 0;
                                }
                                monthForRange = Integer.parseInt(String.valueOf(request.getParameter("months")));
                                percentageForRange = Float.parseFloat(String.valueOf(request.getAttribute("PercentageDFromTop")));

                            %>
                            <%
                                //double percentage = (percentageForRange / 100) / 12;
                                double percentage = (percentageForRange / 100);
                                bluePreResult = (long) ((amountSecondForRange) * (monthForRange - 1));
                                long amountDarkGreeWithPercentage = (long) ((amountMainForRange * percentage) * monthForRange);
                                long amountBlueWithPercentage = (long) ((bluePreResult * percentage) * (monthForRange - 1));
                                //DarkGreenPreResult = amountDarkGreeWithPercentage * monthForRange;
                                DarkGreenResult = amountDarkGreeWithPercentage + amountBlueWithPercentage;
                                System.out.println("DarkGreenResult " + DarkGreenResult);
                                totalResult = (long) (bluePreResult + amountMainForRange + DarkGreenResult);
                                System.out.println("totalResult " + totalResult);

                                double Green = (double) amountMainForRange / totalResult;
                                ligthGreenResult = (long) (Green * 100);
                                System.out.println("ligthGreenResult " + ligthGreenResult);
                                double Blue = bluePreResult / totalResult;
                                blueResult = (long) (Blue * 100);
                                System.out.println("blueResult " + blueResult);
                                double GreenLigth = (double) DarkGreenResult / totalResult;
                                DarkGreenResult = (long) (GreenLigth * 100);
                                System.out.println("DarkGreenResult " + DarkGreenResult);
                            %>

                            <div class="deposit-progress-bar">
                                <div class="progress-line flex align-items-stretch" id="test">
                                    <span class="bg-green-light width-percent-<%=ligthGreenResult%>"></span>
                                    <span class="bg-blue width-percent-<%=blueResult%>"></span>
                                    <span class="bg-green width-percent-<%=DarkGreenResult%>"></span>
                                </div>
                                <ul>
                                    <li class="flex align-items-center">
                                        <span class="cube bg-green-light"></span>
                                        <c:set var="checkName" value='<%=request.getParameter("range")%>'/>
                                        <c:set var="checkName2" value='<%=request.getAttribute("rangeTwoData")%>'/>

                                        <c:choose>
                                            <c:when test="${checkName != null && checkName2 == null}">
                                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='<%=request.getAttribute("range")%>'/> ավանդի գումար</span>
                                                <c:set value='<%=request.getAttribute("range")%>'
                                                       var="AmountToCalculate" scope="request"/>
                                            </c:when>

                                            <c:when test="${checkName == null && checkName2 == null}">
                                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='<%=request.getAttribute("range")%>'/> ավանդի գումար</span>
                                                <c:set value='<%=AmountRanger%>'
                                                       var="AmountToCalculate" scope="request"/>

                                                <c:set var="rangeTwoData" value="${requestScope.Range}"
                                                       scope="request"/>
                                                <%!
                                                    long sumOfSecondMonthsWithMonth1(int month, long baseAmount) {
                                                        long result = 0;
                                                        long sum = 0;
                                                        if (month > 0 && baseAmount != 0) {
                                                            sum += (baseAmount * (month - 1));
                                                        } else {
                                                            sum += baseAmount;
                                                        }
                                                        return sum;
                                                    }
                                                %>
                                                <%!
                                                    static long BaseAmountSet;
                                                    static int MonthsSet;
                                                    static long AmountToCalculateSet;
                                                %>

                                                <%
                                                    if (request.getParameter("months") != null) {
                                                        MonthsSet = Integer.parseInt(request.getParameter("months"));
                                                    } else {
                                                        MonthsSet = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                                                    }

                                                    if (request.getParameter("range") != null) {
                                                        AmountToCalculateSet = Long.parseLong(request.getParameter("valueFromslider"));
                                                    } else if (request.getAttribute("valueFromslider") != null) {
                                                        AmountToCalculateSet = Long.parseLong(String.valueOf(request.getAttribute("valueFromslider")));
                                                    } else {
                                                        AmountToCalculateSet = 0;
                                                    }
                                                    if (request.getAttribute("rangeTwoData") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("rangeTwoData")));
                                                    } else if (request.getAttribute("range_two") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("range_two")));
                                                    } else if (request.getParameter("range_two") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getParameter("range_two")));
                                                    } else {
                                                        BaseAmountSet = 0;
                                                    }

                                                %>
                                                <%-- <span><fmt:formatNumber type="number" maxFractionDigits="3" value='<%=sumOfSecondMonthsWithMonth(MonthsSet,BaseAmountSet)+AmountToCalculateSet %>'/> ավանդի գումար</span>--%>
                                                <%-- <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                         value='<%=request.getParameter("range")%>'/> ավանդի գումար</span>--%>
                                                <c:set value='<%=request.getAttribute("range")%>'
                                                       var="AmountToCalculate" scope="request"/>
                                                <c:set var="AmountToCalculate"
                                                       value='<%=sumOfSecondMonthsWithMonth1(MonthsSet,BaseAmountSet)+AmountToCalculateSet %>'
                                                       scope="request"/>
                                            </c:when>

                                            <c:when test="${checkName != null && checkName2 != null}">
                                                <c:set value='<%=request.getParameter("range")%>' var="sumOfAccounts"
                                                       scope="request"/>
                                                <%!
                                                    static long gabudiHashiv;
                                                %>
                                                <%!
                                                    long sumOfSecondMonthsWithMonth(int month, long baseAmount) {
                                                        gabudiHashiv = 0;
                                                        long sum = 0;
                                                        if (month > 0) {
                                                            sum += (baseAmount * (month - 1));
                                                        } else {
                                                            sum += baseAmount;
                                                        }
                                                        gabudiHashiv = sum;
                                                        return sum;
                                                    }
                                                %>


                                                <%
                                                    MonthsSet = Integer.parseInt(request.getParameter("months"));
                                                    //AmountToCalculateSet = Integer.parseInt(request.getParameter("range"));
                                                    if (request.getAttribute("rangeTwoData") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("rangeTwoData")));
                                                    } else if (request.getAttribute("range_two") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("range_two")));
                                                    } else if (request.getParameter("range_two") != null) {
                                                        BaseAmountSet = Integer.parseInt(String.valueOf(request.getParameter("range_two")));
                                                    } else {
                                                        BaseAmountSet = 0;
                                                    }
                                                %>
                                                <%-- <span><fmt:formatNumber type="number" maxFractionDigits="3" value='<%=sumOfSecondMonthsWithMonth(MonthsSet,BaseAmountSet)+AmountToCalculateSet %>'/> ավանդի գումար</span>--%>
                                                <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='<%=request.getAttribute("range")%>'/> ավանդի գումար</span>
                                                <c:set value='<%=request.getAttribute("range")%>'
                                                       var="AmountToCalculate" scope="request"/>
                                                <c:set var="AmountToCalculate"
                                                       value='<%=sumOfSecondMonthsWithMonth(MonthsSet,BaseAmountSet)+AmountToCalculateSet %>'
                                                       scope="request"/>

                                            </c:when>
                                        </c:choose>


                                    </li>
                                    <li class="flex align-items-center">
                                        <span class="cube bg-blue"></span>

                                        <%!
                                            long calculatePercentagetop(float Amount, double Percentage, int month) {
                                                double firstStep = (Amount / 100 * Percentage);
                                                System.out.println("firstStep " + firstStep);
                                                float secondStep = (float) (firstStep / 365);
                                                System.out.println("secondStep " + secondStep);
                                                float finalStep = (secondStep * 30);
                                                System.out.println("finalStep " + finalStep);
                                                float TheTen = (float) (finalStep * 0.10);
                                                System.out.println("TheTen " + TheTen);
                                                float result = (finalStep - TheTen) * month;
                                                System.out.println("result " + result);
                                                return (int) result;
                                              /*  long result=0;
                                                double percentage = (Percentage / 100);
                                                result = (long) (Amount * percentage * month);
                                                return result;*/
                                            }
                                        %>
                                        <%!
                                            static double percenttop;
                                            long Amounttop;
                                            int monthtop;
                                            int MonthsToDays;

                                        %>

                                        <%
                                            percenttop = Double.parseDouble(String.valueOf(request.getAttribute("PercentageDFromTop")));
                                            Amounttop = Long.parseLong(String.valueOf(request.getAttribute("range")));//100000
                                            if (request.getParameter("months") != null) {
                                                monthtop = Integer.parseInt(request.getParameter("months"));//1
                                                if (monthtop == 30 || monthtop == 90 || monthtop == 180 || monthtop == 360 || monthtop == 540 || monthtop == 720 || monthtop == 1080) {
                                                    monthtop /= 30;
                                                }
                                            } else {
                                                if (monthtop == 30 || monthtop == 90 || monthtop == 180 || monthtop == 360 || monthtop == 540 || monthtop == 720 || monthtop == 1080) {
                                                    monthtop /= 30;
                                                }
                                                monthtop = Integer.parseInt(String.valueOf(request.getAttribute("months")));//1
                                            }
                                            MonthsToDays = monthtop * 30;
                                            if (request.getAttribute("value_url") != null) {
                                                BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("value_url")));
                                            } else if (request.getAttribute("range_two") != null) {
                                                BaseAmountSet = Integer.parseInt(String.valueOf(request.getAttribute("range_two")));
                                            } else if (request.getParameter("range_two") != null) {
                                                BaseAmountSet = Integer.parseInt(String.valueOf(request.getParameter("range_two")));
                                            } else {
                                                BaseAmountSet = 0;
                                            }


                                        %>
                                        <%!
                                            long CalculateSecondAMountPercentage(long AmountSecond, double Percentage, int Months) {
                                                double firstStep = (AmountSecond / 100 * Percentage);
                                                System.out.println("firstStep " + firstStep);
                                                float secondStep = (float) (firstStep / 365);
                                                System.out.println("secondStep " + secondStep);
                                                float finalStep = (secondStep * 30);
                                                System.out.println("finalStep " + finalStep);
                                                float TheTen = (float) (finalStep * 0.10);
                                                System.out.println("TheTen " + TheTen);
                                                float result = (finalStep - TheTen) * (Months - 1);
                                                System.out.println("result " + result);
                                                return (int) result;
                                               /* int dogos=0;
                                                double percentage = (Percentage / 100);
                                                dogos = (int) (((((AmountSecond /100) * percentage)/365) * 30)* (Months - 1));
                                                return dogos;*/
                                            }
                                        %>

                                        <%!
                                            long getFinalCalculation(long firstPercentageCalculation, long secondPercentageCalculation) {
                                                return firstPercentageCalculation + secondPercentageCalculation;
                                            }
                                        %>
                                        <%!
                                            long calculatesecondAmountWithMonths() {

                                                long result = 0;
                                                result = BaseAmountSet * (monthtop - 1);
                                                return result;
                                            }
                                        %>
                                        <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                value='<%=calculatesecondAmountWithMonths() %>'/> լրացում  <%=monthtop%> Ամիս </span>
                                        <c:set var="incomeInMonths"
                                               value='<%=calculatePercentagetop(Amounttop, percenttop, monthtop) %>'
                                               scope="request"/>
                                    </li>
                                    <li class="flex align-items-center">
                                        <span class="cube bg-green"></span>
                                        <span><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                value='<%=getFinalCalculation(CalculateSecondAMountPercentage(BaseAmountSet,percenttop, monthtop),calculatePercentagetop(Amounttop, percenttop, monthtop)) %>'/> հաշվեգրված տոկոսներ</span>
                                        <c:set var="TotalDeposiIncome"
                                               value='<%=getFinalCalculation(CalculateSecondAMountPercentage(BaseAmountSet,percenttop, monthtop),calculatePercentagetop(Amounttop, percenttop, monthtop)) %>'
                                               scope="request"/>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-8 col rigthtwo-two">
                            <div class="total-info margin-top-0">
                                <div class="flex  margin-bottom-15">
                                    <div>
                                        <p>Կապիտալացված տոկոսադրույք</p>
                                        <c:forEach var="percentageCapital" items="${requestScope.TheList}">
                                            <p class="font-28 bold green"><%=percenttop%>
                                                %</p>
                                            <input type="hidden" name="ProductCode"
                                                   value='${percentageCapital.productCode}'>
                                        </c:forEach>
                                    </div>
                                    <div>

                                        <p>Ժամկետի վերջում դուք կստանաք</p>
                                        <%!
                                            long CalculatAll(long income, long firstAmount, long secondAmount) {
                                                long result = income + firstAmount + secondAmount;
                                                return result;
                                            }
                                        %>
                                        <%!
                                            long FirstAmount;
                                            long SecondAmount;
                                        %>

                                        <%
                                            long deposiIncome = (long) request.getAttribute("incomeInMonths");
                                            // int deposiIncome = (int) request.getAttribute("allAMounts");
                                            if (request.getParameter("range") != null) {
                                                FirstAmount = Long.parseLong(String.valueOf(request.getParameter("range")));
                                            } else if (request.getAttribute("range") != null) {
                                                FirstAmount = Long.parseLong(String.valueOf(request.getAttribute("range")));
                                            } else {
                                                FirstAmount = AmountToCalculateSet;
                                            }
                                            SecondAmount = BaseAmountSet * monthtop;
                                        %>

                                        <%!
                                            long finalRsult() {
                                                long resultInox = getFinalCalculation(CalculateSecondAMountPercentage(BaseAmountSet, percenttop, monthtop), calculatePercentagetop(Amounttop, percenttop, monthtop) + Amounttop + (BaseAmountSet * (monthtop - 1)));
                                                return resultInox;
                                            }
                                        %>
                                        <%--<c:set value="${requestScope.PageCurrancy}" var="Currancy"/>
                                        <c:choose>

                                            <c:when test="${Currancy == 'AMD'}">--%>
                                        <h2 class="timer count-title count-number font-28 bold"
                                            data-to="<%=finalRsult()%>"
                                            data-speed="1739"></h2>
                                        <%--  <p class="font-28 bold"><fmt:formatNumber type="number"
                                                                                    maxFractionDigits="3"
                                   <%--                                                 value='<%=finalRsult()%>' />&ndash;%&gt;


                                      </c:when>
                                      <c:when test="${Currancy == 'USD'}">
                                          <p class="font-28 bold"><fmt:formatNumber type="number"
                                                                                    maxFractionDigits="3"
                                                                                    value='<%=CalculatAll(deposiIncome, FirstAmount, SecondAmount)%>'/>
                                              $</p>
                                      </c:when>
                                      <c:when test="${Currancy == 'RUS'}">
                                          <p class="font-28 bold"><fmt:formatNumber type="number"
                                                                                    maxFractionDigits="3"
                                                                                    value='<%=CalculatAll(deposiIncome, FirstAmount, SecondAmount)%>'/>
                                              ₽</p>
                                      </c:when>
                                      <c:otherwise>
                                          <p class="font-28 bold"><fmt:formatNumber type="number"
                                                                                    maxFractionDigits="3"
                                                                                    value='<%=CalculatAll(deposiIncome, FirstAmount, SecondAmount)%>'/></p>
                                      </c:otherwise>
                                  </c:choose>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>


</div>
<footer>
    <div class="inner-container large">
        <div class="top flex space-between">
            <div class="flex align-items-center">
                <span class="logo">
                   <img src="../images/oferta1.jpg" alt=""/>
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
                </ul>
            </span>
            <span class="text-right">
                2020 Oferta.am: Նյութերն օգտագործելիս՝, հղում դեպի oferta.am պարտադիր է:
            </span>
        </div>
    </div>
</footer>
<script>
    $(function () {
        $("#datepicker").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>

<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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

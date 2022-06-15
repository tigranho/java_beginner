<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta property="og:title" content="Oferta.am">
    <meta property="og:type" content="website" />
    <meta property="og:image" content="https://www.oferta.am/images/oferta1.jpg">
    <meta property="og:description" content="Բոլոր բանկային առաջարկները մեկ հարթակում"><meta property="og:url" content="https://www.oferta.am">
    <meta name="twitter:card" content="summary_large_image">
    <%
        if(request.getSession().getAttribute("session")!= null){
            request.getAttribute("session");
        }else{
            String sessionId = session.getId();
            request.setAttribute("session",sessionId);
        }
    %>
    <meta name="twitter:card" content="summary_large_image">
    <jsp:include page="include/google.jsp"/><meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oferta.am</title>
    <link href="${requestScope.getContextPath}/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${requestScope.getContextPath}/fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="${requestScope.getContextPath}/css/global.css" type="text/css">
    <link rel="stylesheet" href="${requestScope.getContextPath}/libs/owl/owl.carousel.min.css"/>
    <link rel="stylesheet" href="${requestScope.getContextPath}/css/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<style>
    #deposit {
        display: block;
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
							<span class="logo"> <a href="App">  <img src="https://www.oferta.am/images/oferta1.jpg" alt=""/></a>
							</span> <span class="hide-for-tablet">
								<ul>
									<li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a
                                            href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
								<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}&&City=<%=request.getAttribute("City")%>">Մեր Գործընկերները</a></li>
									<%--<li><a href="#">Մեր Գործընկերները</a></li>--%>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
								</ul>
							</span>
                    </div>
                    <div class="right flex align-items-center">
                        <span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i> ${requestScope.City}  </span>

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
                                        <a href="DepositClient?PageToGo=Deposits&&PageToGo=Deposits&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎԱՆԴՆԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="MortgageClient?PageToGo=MortgageClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՀԻՓՈԹԵՔ</a>
                                    </li>
                                    <li>
                                        <a href="ConsumerClient?PageToGo=ConsumerClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՍՊԱՌՈՂԱԿԱՆ
                                            ՎԱՐԿԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="AutoClient?PageToGo=AutoClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎՏՈՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="MicroClient?PageToGo=MicroClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՄԻԿՐՈՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="AClient?PageToGo=AClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ
                                            ՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=OFF&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=OFF">ՔԱՐՏԵՐ</a>
                                    </li>
                                </ul>
                                <c:set value="${dropDownList.minAmount}" var="MaxAMountToDelete" scope="request"/>
                            </c:forEach>
                        </c:if>
                    </div>

                    <span class="right hide-for-tablet">

                <span><i class="icon-search" id="searchBtn"></i></span>
                <span class="compere-box">

                    <span class="compere-icon">
                        <c:if test="${requestScope.comparListDeposit != null}">
                            <c:forEach var="size" items="${requestScope.comparListCarLoan}" varStatus="TheCount">
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
                                <input name="PageToGo" value="Deposits"  type="hidden">
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
                                     <i type="submit" class="icon-delete" onclick="document.Delete.submit();"></i>

                                </li>
                                 <form action="DepositClient" method="get" name="Delete">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                            </c:if>
                             <form action="CompareMortgage" method="get" name="MortgageCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">

                            </form>
                            <c:if test="${requestScope.comparListMortgage != null}">
                            <ul>
                                <li >
                                    <c:forEach var="MortgagCompare" items="${requestScope.comparListMortgage}"
                                               varStatus="TheCount">
                                        <c:set var="counterMortgag" value="${TheCount.count}" scope="request"/>


                                    </c:forEach>
                                     <span onclick="document.MortgageCompare.submit();">Հիփոթեք</span>
                                     <input name="PageToGo" value="Mortgage"  type="hidden">
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete" onclick="document.DeleteHipotek.submit();"></i>
                                </li>
                                 <form action="DepositClient" method="get" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

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
                                     <span  onclick="document.ConsumerCompare.submit();">Սպառողական</span>
                                     <input name="PageToGo" value="Consumer" type="hidden">
                                     <span class="bold font-14">${counterCompare}</span>
                                       <i type="submit" class="icon-delete" onclick="document.DeleteConsumer.submit();"></i>
                                </li>
                                  <form action="DepositClient" method="get" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="get" name="CarLoanCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListCarLoan != null}">
                            <ul>
                                <li >
                                    <c:forEach var="CarLoanCompare" items="${requestScope.comparListCarLoan}"
                                               varStatus="TheCount">
                                        <c:set var="counterCarLoan" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                    <span onclick="document.CarLoanCompare.submit();">Ավտովարկ</span>
                                      <span class="bold font-14">${counterCarLoan}</span>
                                       <i type="submit" class="icon-delete" onclick="document.DeleteCar.submit();"></i>
                                </li>
                                 <form action="DepositClient" method="get" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                            </c:if>

                            <form action="CompareAg" method="get" name="AgCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListAg != null}">
                            <ul>
                                <li >
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
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                            </c:if>

                        </div>
                    </div>
                </span>
            </span>
                    <!-- Ending compare Section -->
                </div>
            </div>
        </div>

    </div>


    <div class="top-calculator bg-blue">
        <div class="bg" style="background-image: url('../images/0.jpg')">
            <div class="inner-container relative">
                <div class="sticky-bg"
                     style="background-image: url('../images/girls.png')"></div>
                <div class="info-container">
                    <div class="row-md clearfix margin-bottom-0">
                        <div class="text-box col-5">
                            <p class="font-32 line-height-36">Վարկերի օնլայն հարթակ</p>
                            <p>Այստեղ Դուք կարող եք գտնել բանկերի և վարկային կազմակերպությունների բոլոր առաջարկները, որոնք կարող եք համեմատել և ընտրել լավագույն առաջարկը</p>
                        </div>
                    </div>
                    <form action="DepositClient" method="get">


                        <div class="row-md clearfix">

                            <div class="col-5 large col">

                                <span class="label">Մուտքագրեք գումարը</span>
                                <div class="range-group">
                                    <div class="def-range">


                                        <c:if test="${requestScope.dropDownsListWithCurrancy!=null}">
                                        <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                   var="dropDownList"
                                                   varStatus="loop">
                                        <output style="display: none;" id="outputer">${dropDownList.minAmount}</output>

                                        <input type="text" name="value" min="${dropDownList.minAmount}"
                                               max="${dropDownList.maxAmount}" step="${dropDownList.steps}"
                                               value="${dropDownList.minAmount}"
                                               oninput="showVal(this.value)"
                                               onchange="showVal(this.value)" id="amount" inputmode="numeric">

                                        <input type="range" name="range" min="${dropDownList.minAmount}"
                                               max="${dropDownList.maxAmount}" step="${dropDownList.steps}"
                                               value="${dropDownList.minAmount}" data-rangeslider
                                               id="amount_range">


                                        <input type="hidden" name="MaxAmounr" value="${dropDownList.maxAmount}">
                                        <input type="hidden" name="Amount" value="${dropDownList.minAmount}">
                                            <c:set var="MaxAmounr" value="${dropDownList.maxAmount}" scope="request"/>
                                            <c:set var="range" value="${dropDownList.minAmount}" scope="request"/>

                                                <%!
                                                    static String maxAmountSend;
                                                    static String minAamountSend;
                                                %>
                                                <%
                                                    maxAmountSend = String.valueOf(request.getAttribute("MaxAmounr"));
                                                    minAamountSend = String.valueOf(request.getAttribute("Amount"));
                                                %>
                                        </c:forEach>
                                        </c:if>
                    </form>
                    <div id="slider-range-min"></div>
                </div>
                <div class="def-select-box">

                    <form name="form1" action="App" method="post">

                        <input type="hidden" name="PageLanguage"
                               value="${requestScope.Pagelanguage}">
                        <input type="hidden" name="City" value='<%=request.getAttribute("City")%>'>
                        <c:set var="AMD" value="${requestScope.PageCurrancy }"/>

                                <select name="Currancy" onchange="document.form1.submit();">
                                    <option value="AMD" <c:if test="${AMD == 'AMD'}">selected</c:if> >֏</option>
                                    <option value="USD" <c:if test="${AMD == 'USD'}">selected</c:if>>$</option>
                                    <option value="EUR" <c:if test="${AMD == 'EUR'}">selected</c:if>>€</option>
                                    <option value="RUB" <c:if test="${AMD == 'RUB'}">selected</c:if>>₽</option>
                                </select>

                    </form>
                </div>
            </div>
        </div>


        <div class="col-2-3 middle col">
            <span class="label">Վարկի տեսակը</span>
            <div class="def-select-box">
                <select name="PageToGo">
                    <c:if test="${requestScope.dropDownsListWithCurrancy!=null}">
                        <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                   var="dropDownList">
                            <option value="DepositClient"> Ավանդներ</option>
                            <option value="MortgageClient">Հիփոթեքային վարկ</option>
                            <option value="ConsumerClient">Սպառողական վարկ</option>
                            <option value="AutoClient">Ավտովարկ</option>
                            <option value="MicroClient">Միկրովարկ</option>
                            <option value="AClient">Գյուղատնտեսական վարկ</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="col-1-2 small col">
            <input type="submit" value="Հաշվել" class="def-button btn-green"/>
            <input type="hidden" name="Currancy" value="${requestScope.PageCurrancy}"/>
            <input type="hidden" name="Amount" value='<%=minAamountSend%>'/>
            <input type="hidden" name="MaxAmounr" value='<%=request.getAttribute("MaxAmounr")%>'/>


        </div>
    </div>
    </form>
</div>
</div>
</div>
</div>
<div class="inner-container rate-box-container">
    <div class="flex align-items-center space-between">
        <div>
           <%-- <div class="rate-box flex align-items-center">
                <!-- STARTS exchange rates ********************************************************************************** -->
                <div>
                    <span class="bold">USD</span><span>${requestScope.usd}</span>
                    <c:set value="${requestScope.usdPosition}" var="position"/>
                    <c:choose>
                        <c:when test="${position == -1.0}">
                            <i class="icon-polygon-down red"></i>
                        </c:when>
                        <c:when test="${position == 1.0}">
                            <i class="icon-polygon-up green-dark"></i>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <span class="bold">EUR</span><span>${requestScope.eur}</span>
                    <c:set value="${requestScope.eurPosition}" var="position"/>
                    <c:choose>
                        <c:when test="${position == -1.0}">
                            <i class="icon-polygon-down red"></i>
                        </c:when>
                        <c:when test="${position == 1.0}">
                            <i class="icon-polygon-up green-dark"></i>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <span class="bold">RUR</span><span>${requestScope.rub}</span>
                    <c:set value="${requestScope.rubPosition}" var="position"/>
                    <c:choose>
                        <c:when test="${position == -1.0}">
                            <i class="icon-polygon-down red"></i>
                        </c:when>
                        <c:when test="${position == 1.0}">
                            <i class="icon-polygon-up green-dark"></i>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <span class="bold">GBP</span><span>${requestScope.gpb}</span> <c:set
                        value="${requestScope.gpbPosition}" var="position"/>
                    <c:choose>
                        <c:when test="${position == -1.0}">
                            <i class="icon-polygon-down red"></i>
                        </c:when>
                        <c:when test="${position == 1.0}">
                            <i class="icon-polygon-up green-dark"></i>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
            </div>--%>
            <!-- ENDS exchange rates ********************************************************************************** -->
        </div>
        <%-- <div class="text-right">
             <a href="index.php?page=rate" class="green-link"><fmt:message
                     key="All exchange rates in banks" bundle="${Bundles}"/></a>
         </div>--%>
    </div>
</div>
<div class="bg-white-dark padding-bt-60">
    <div class="inner-container loan-types">
        <div class="row-flex row-md flex flex-wrap align-items-stretch">
            <c:if test="${requestScope.dropDownsListWithCurrancy!=null}">
            <c:forEach items="${requestScope.dropDownsListWithCurrancy}" var="dropDownList">
            <div class="col-4">

                <div class="block-container item">
                    <a href="DepositClient?PageToGo=Deposits&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center">
                        <span class="icon"><img src="../images/icons/1.svg"/></span> <span>Ավանդներ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="MortgageClient?PageToGo=MortgageClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center">
                        <span class="icon"><img src="../images/icons/2.svg"/></span> <span>Հիփոթեքային վարկ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="ConsumerClient?PageToGo=ConsumerClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center"> <span class="icon"><img
                            src="../images/icons/3.svg"/></span> <span>Սպառողական վարկ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="AutoClient?PageToGo=AutoClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center"><img src="../images/icons/4.svg"/></span>
                        <span>Ավտովարկ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="MicroClient?PageToGo=MicroClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center"> <span class="icon"><img
                            src="../images/icons/5.svg"/></span> <span>Միկրոկրեդիտ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="AClient?PageToGo=AClient&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}"
                       class="flex align-items-center"> <span class="icon"><img
                            src="../images/icons/6.svg"/></span> <span>Գյուղատնտեսական վարկ</span>
                    </a>
                </div>
            </div>
            <div class="col-4">
                <div class="block-container item">
                    <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=on&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=credit"
                       class="flex align-items-center">
                        <span class="icon"><img src="../images/icons/7.svg"/></span> <span>Քարտեր</span>
                    </a>
                </div>
            </div>
        </div>
        </c:forEach>
        </c:if>


        <div class="padding-bt-60">
            <div class="inner-container">
                <p class="font-24 title margin-bottom-35">
                    Հատուկ առաջարկներ
                </p>
                <div class="tab-container">
                    <div class="tab-nav flex align-items-center flex-wrap">
						<span class="tab-link active" onclick="openTabItem(event, 'deposit')" id="defaultOpen">
                           Ավանդներ</span>
                        <span class="tab-link" onclick="openTabItem(event, 'mortgage')">
                        Հիպոթեկային վարկ</span>
                        <span class="tab-link" onclick="openTabItem(event, 'consumer-loan')">
                   Սպառողական վարկ</span>
                        <span class="tab-link" onclick="openTabItem(event, 'car-loan')">
                    Ավտովարկ</span>
                        <span class="tab-link" onclick="openTabItem(event, 'agricultural')">
                    Գյուղատնտեսական</span>
                        <span class="tab-link" onclick="openTabItem(event, 'card')">
                   Քարտեր</span>
                    </div>
                    <!-- ############################################################ Deposit START -->
                    <div class="tab-content" id="deposit">
                        <div class="top">

                            <a href="DepositClient?PageToGo=Deposits&&Currancy=${requestScope.PageCurrancy}&&Amount=<%=depositMinAmount%>&&MaxAmounr=<%=depositMaxAmount%>"
                               class="green-link width-icon ellipsis width-percent-70 block">
                                <i class="icon-grid"></i>
                                <span>Ավանդների բոլոր առաջարկները</span>
                            </a>

                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel">
                                    <c:if test="${requestScope.depositListOffer!=null}">
                                        <c:forEach items="${requestScope.depositListOffer}" var="SpecialDeposit">
                                            <c:set var="minAmountDeposit" scope="request"
                                                   value='${SpecialDeposit.DMinAmount}'/>
                                            <c:set var="maxAmountDeposit" scope="request"
                                                   value='${SpecialDeposit.DMaxAmount}'/>
                                            <%!
                                                static int depositMinAmount;
                                                static int depositMaxAmount;
                                                static int convertMonths;
                                            %>
                                            <%
                                                depositMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountDeposit")));
                                                depositMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountDeposit")));
                                            %>


                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialDeposit.DPercentage}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <c:set var="ToMonth" scope="request"
                                                               value="${SpecialDeposit.timeLine}"/>
                                                        <%
                                                            int getMonths = Integer.parseInt(String.valueOf(request.getAttribute("ToMonth")));
                                                            convertMonths = getMonths / 30;
                                                        %>
                                                        <span class="bold font-20"><%=convertMonths%> Ամիս</span>
                                                        <span>Ավանդի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <form action="Credits" method="get">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialDeposit.productCode}"/>
                                                        <input type="hidden" name="PageToGo" value="Deposit"/>
                                                        <input type="hidden" name="Currancy"
                                                               value="${SpecialDeposit.currancy}"/>
                                                        <input type="hidden" name="SpecialAMount"
                                                               value="${SpecialDeposit.DMinAmount}"/>
                                                        <input type="hidden" name="MaxAmounr"  value="${SpecialDeposit.DMaxAmount}"/>
                                                            <%--<input type="hidden" name="MaxAmounr"  value="${SpecialDeposit.DMaxAmount}"/>--%>
                                                        <input type="hidden" name="value_two" value="0"/>
                                                        <input type="hidden" name="months" value='<%=convertMonths%>'/>
                                                    </form>
                                                </div>
                                                <div>
                                                    <div class="item-img" style="background-image: url('${SpecialDeposit.banksList}')">
                                                    </div>
                                                    <div>
                                                        <span>${SpecialDeposit.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############################################################ Deposit ENDS -->
                    <!-- ############################################################ Mortgage STARTS -->
                    <div class="tab-content" id="mortgage">
                        <div class="top">

                            <a href="MortgageClient?Currancy=${requestScope.PageCurrancy}&&Amount=<%=mortgageMinAmount%>&&MaxAmounr=<%=mortgageMaxAmount%>"
                               class="green-link width-icon ellipsis width-percent-70 block">
                                <i class="icon-grid"></i> <span>Հիփոթեքի բոլոր առաջարկները</span>
                            </a>

                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel2">

                                    <c:if test="${requestScope.MortgageListOffer!=null}">
                                        <c:forEach items="${requestScope.MortgageListOffer}"
                                                   var="SpecialMortgage">
                                            <c:set var="minAmountMort" scope="request"
                                                   value='${SpecialMortgage.MMinAmount}'/>
                                            <c:set var="maxAmountMort" scope="request"
                                                   value='${SpecialMortgage.MMaxAmount}'/>
                                            <c:set var="maxMonthMort" scope="request"
                                                   value='${SpecialMortgage.MMaxPeriodMonth}'/>
                                            <%!
                                                static int mortgageMinAmount;
                                                static int mortgageMaxAmount;
                                                static int convertMonthsMortgage;
                                            %>
                                            <%--  <%
                                                  mortgageMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountMort")));
                                                  mortgageMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountMort")));
                                                  convertMonthsMortgage = Integer.parseInt(String.valueOf(request.getAttribute("maxMonthMort")));
                                              %>--%>
                                            <%!
                                                int calculateRangeTwo(int MaxAmount) {
                                                    int resulat = MaxAmount * 10 / 100;
                                                    return resulat;
                                                }
                                            %>
                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialMortgage.MFatual}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <span class="bold font-20">${SpecialMortgage.MMinPeriodMonth} Ամիս</span>
                                                        <span>վարկի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <c:set value="${SpecialMortgage.MMinPeriodMonth}" var="monthS" scope="request"/>
                                                    <%
                                                        long monthsChanging = Long.parseLong(String.valueOf(request.getAttribute("monthS")));
                                                        long monthsing=0;
                                                        if(monthsChanging==12  || monthsChanging==24 || monthsChanging==36 || monthsChanging==48 || monthsChanging==60 || monthsChanging==72 || monthsChanging==84 || monthsChanging==96 || monthsChanging==108 || monthsChanging==120  || monthsChanging==144 || monthsChanging==180 || monthsChanging==240 || monthsChanging==360){
                                                            monthsing = monthsChanging/12;
                                                        }else{
                                                            monthsing = monthsChanging;
                                                        }
                                                    %>
                                                    <form action="CreditSend" method="post">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="more" class="def-button btn-blue"
                                                               value="more"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialMortgage.productCode}"/>
                                                        <input type="hidden" name="Amount"
                                                               value="${SpecialMortgage.MMinAmount}"/>
                                                        <input type="hidden" name="PageToGo" value="Mortgage"/>
                                                        <input type="hidden" name="Currancy"
                                                               value="${SpecialMortgage.currancy}"/>
                                                        <input type="hidden" name="SpecialAMount"
                                                               value="${SpecialMortgage.MMinAmount}"/>
                                                        <input type="hidden" name="MaxAmounr"
                                                               value="${SpecialMortgage.MMaxAmount}"/>
                                                        <input type="hidden" name="value_two" value="0"/>
                                                            <%--<input type="hidden" name="percentageSecond" value="10"/>--%>
                                                        <input type="hidden" name="options" value="1"/>
                                                            <%--   <input type="hidden" name="range_two"
                                                                      value='<%=calculateRangeTwo(mortgageMinAmount)%>'/>--%>

                                                        <input type="hidden" name="months"
                                                               value='<%=monthsing%>'/>

                                                    </form>
                                                </div>
                                                <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                    <div class="item-img">
                                                        <img src="${SpecialMortgage.banksList}"/>
                                                    </div>
                                                    <div>
                                                        <span>${SpecialMortgage.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############################################################ Mortgage ENDS -->
                    <!-- ############################################################ Consumer STARTS -->
                    <div class="tab-content" id="consumer-loan">
                        <div class="top">

                            <a href="ConsumerClient?Currancy=${requestScope.PageCurrancy}&&Amount=<%=conMinAmount%>&&MaxAmounr=<%=contgageMaxAmount%>"
                               class="green-link width-icon"> <i
                                    class="icon-grid"></i> <span>Սպառողական բոլոր առաջարկները</span>
                            </a>

                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel3">
                                    <c:if test="${requestScope.consumerCreditListoffer!=null}">
                                        <c:forEach
                                                items="${requestScope.consumerCreditListoffer}"
                                                var="SpecialconsumerCreditListoffer">
                                            <c:set var="minAmountCon" scope="request"
                                                   value='${SpecialconsumerCreditListoffer.CCMinAmount}'/>
                                            <c:set var="maxAmountCon" scope="request"
                                                   value='${SpecialconsumerCreditListoffer.CCMaxAmount}'/>
                                            <c:set var="maxMonthCon" scope="request"
                                                   value='${SpecialconsumerCreditListoffer.CCMaxPeriodMonth}'/>
                                            <%!
                                                static int conMinAmount;
                                                static int contgageMaxAmount;
                                                static int convertMonthscon;
                                            %>
                                            <%-- <%
                                                 conMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountMort")));
                                                 contgageMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountMort")));
                                                 convertMonthscon = Integer.parseInt(String.valueOf(request.getAttribute("maxMonthCon")));
                                             %>--%>
                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialconsumerCreditListoffer.CCFactual}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <span class="bold font-20">${SpecialconsumerCreditListoffer.CCMaxPeriodMonth} Ամիս</span>
                                                        <span>վարկի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <form action="CreditSend" method="get">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialconsumerCreditListoffer.productCode}"/>
                                                        <input type="hidden" name="PageToGo" value="Consumer"/>
                                                        <input type="hidden" name="Currancy"
                                                               value="${SpecialconsumerCreditListoffer.currancy}"/>
                                                        <input type="hidden" name="Amount"
                                                               value="${SpecialconsumerCreditListoffer.CCMinAmount}"/>
                                                        <input type="hidden" name="MaxAmounr"
                                                               value="${SpecialconsumerCreditListoffer.CCMaxAmount}"/>
                                                        <input type="hidden" name="value_two" value="1000000"/>
                                                        <input type="hidden" name="options" value="1"/>
                                                            <%-- <input type="hidden" name="range_two" value='30'/>--%>

                                                        <input type="hidden" name="months"
                                                               value='<%=convertMonthsMortgage%>'/>

                                                    </form>
                                                </div>
                                                <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                    <div class="item-img">
                                                        <img src="${SpecialconsumerCreditListoffer.banksList}"/>
                                                    </div>
                                                    <div>
                                                        <span>${SpecialconsumerCreditListoffer.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############################################################ Consumer ENDS -->
                    <!-- ############################################################ Car loan STARTS -->
                    <div class="tab-content" id="car-loan">
                        <div class="top">
                            <a href="AutoClient?Currancy=${requestScope.PageCurrancy}&&Amount=<%=carMinAmount%>&&MaxAmounr=<%=cartgageMaxAmount%>"
                               class="green-link width-icon"> <i
                                    class="icon-grid"></i> <span>Ավտոմեքենայի վարկի բոլոր առաջարկները</span>
                            </a>
                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel4">
                                    <c:if test="${requestScope.carLoansListoffer!=null}">
                                        <c:forEach items="${requestScope.carLoansListoffer}"
                                                   var="SpecialcarLoansListoffer">
                                            <c:set var="minAmountcar" scope="request"
                                                   value='${SpecialcarLoansListoffer.CLMinAmount}'/>
                                            <c:set var="maxAmountcar" scope="request"
                                                   value='${SpecialcarLoansListoffer.CLMaxAmount}'/>
                                            <c:set var="maxMonthcar" scope="request"
                                                   value='${SpecialcarLoansListoffer.CLMaxPeriodMonths}'/>
                                            <%!
                                                static int carMinAmount;
                                                static int cartgageMaxAmount;
                                                static int carvertMonthscon;
                                            %>
                                            <%--  <%
                                                  carMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountcar")));
                                                  cartgageMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountcar")));
                                                  carvertMonthscon = Integer.parseInt(String.valueOf(request.getAttribute("maxMonthcar")));
                                              %>--%>
                                            <%!
                                                int calculateRangeTwoCar(int MaxAmount) {
                                                    int resulat = MaxAmount * 10 / 100;
                                                    return resulat;
                                                }
                                            %>
                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialcarLoansListoffer.CLFatual}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <span class="bold font-20">${SpecialcarLoansListoffer.CLMaxPeriodMonths} Ամիս</span>
                                                        <span>վարկի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>

                                                    <form action="CreditSend" method="get">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialcarLoansListoffer.productCode}"/>
                                                        <input type="hidden" name="PageToGo" value="Cars"/>
                                                        <input type="hidden" name="Currancy"
                                                               value="${SpecialcarLoansListoffer.currancy}"/>
                                                        <input type="hidden" name="Amount"
                                                               value="${SpecialcarLoansListoffer.CLMinAmount}"/>
                                                        <input type="hidden" name="MaxAmount"
                                                               value="${SpecialcarLoansListoffer.CLMaxAmount}"/>
                                                        <input type="hidden" name="value_two" value="1000000"/>
                                                        <input type="hidden" name="options" value="1"/>
                                                            <%-- <input type="hidden" name="range_two"
                                                                    value="<%=calculateRangeTwoCar(carMinAmount)%>"/>--%>
                                                        <input type="hidden" name="percentageSecond" value="10"/>

                                                        <input type="hidden" name="months"
                                                               value='<%=convertMonthsMortgage%>'/>

                                                    </form>
                                                </div>
                                                <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                    <div class="item-img">
                                                        <img src="${SpecialcarLoansListoffer.banksList}"/>
                                                    </div>
                                                    <div>
                                                        <span>${SpecialcarLoansListoffer.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############################################################ Car loan ENDS -->
                    <!-- ############################################################ agricultural STARTS -->
                    <div class="tab-content" id="agricultural">
                        <div class="top">
                            <a href="AClient?Currancy=${requestScope.PageCurrancy}&&Amount=<%=AGMinAmount%>&&MaxAmounr=<%=AGtgageMaxAmount%>"
                               class="green-link width-icon"> <i
                                    class="icon-grid"></i> <span>Գյուղատնտեսության բոլոր առաջարկները</span>
                            </a>
                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel5">
                                    <c:if test="${requestScope.agriculturalCreditListoffer!=null}">
                                        <c:forEach
                                                items="${requestScope.agriculturalCreditListoffer}"
                                                var="SpecialagriculturalCreditListoffer">
                                            <c:set var="minAmountAG" scope="request"
                                                   value='${SpecialagriculturalCreditListoffer.ACMinAmount}'/>
                                            <c:set var="maxAmountAG" scope="request"
                                                   value='${SpecialagriculturalCreditListoffer.ACMaxAmount}'/>
                                            <c:set var="maxMonthsAG" scope="request"
                                                   value='${SpecialagriculturalCreditListoffer.ACMaxPeriodMonth}'/>
                                            <%!
                                                static int AGMinAmount;
                                                static int AGtgageMaxAmount;
                                                static int carvertMonthsAG;
                                            %>
                                            <%--  <%
                                                  AGMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountAG")));
                                                  AGtgageMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountAG")));
                                                  carvertMonthsAG = Integer.parseInt(String.valueOf(request.getAttribute("maxMonthsAG")));
                                              %>--%>


                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialagriculturalCreditListoffer.ACFactual}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <span class="bold font-20">${SpecialagriculturalCreditListoffer.ACMaxPeriodMonth} Ամիս</span>
                                                        <span>վարկի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <form action="CreditSend" method="get">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialagriculturalCreditListoffer.productCode}"/>
                                                        <input type="hidden" name="PageToGo" value="AG"/>
                                                        <input type="hidden" name="Currancy"
                                                               value="${SpecialagriculturalCreditListoffer.currancy}"/>
                                                        <input type="hidden" name="Amount"
                                                               value="${SpecialagriculturalCreditListoffer.ACMinAmount}"/>
                                                        <input type="hidden" name="MaxAmount"
                                                               value="${SpecialagriculturalCreditListoffer.ACMaxAmount}"/>
                                                        <input type="hidden" name="value_two" value="1000000"/>
                                                        <input type="hidden" name="options" value="1"/>
                                                            <%-- <input type="hidden" name="range_two" value="30"/>--%>
                                                        <input type="hidden" name="Per" value="10"/>

                                                        <input type="hidden" name="months"
                                                               value='<%=convertMonthsMortgage%>'/>

                                                    </form>
                                                </div>
                                                <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                    <div class="item-img">
                                                        <img src="${SpecialagriculturalCreditListoffer.banksList}"/>
                                                    </div>
                                                    <div>
                                                        <span>${SpecialagriculturalCreditListoffer.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ############################################################ agricultural ENDS -->
                    <!-- ############################################################ card STARTS -->
                    <div class="tab-content" id="card">
                        <div class="top">
                            <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=Cash&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=OFF"
                               class="green-link width-icon"> <i
                                    class="icon-grid"></i> <span>Բոլոր Քարտեր առաջարկները</span>
                            </a>
                        </div>
                        <div>
                            <div class="simple-carousel margin-top-30">
                                <div class="owl-carousel owl-theme" id="simpleCarousel6">
                                    <c:if test="${requestScope.cardsListOffer!=null}">
                                        <c:forEach items="${requestScope.cardsListOffer}" var="SpecialcardsListOffer">

                                            <div class="special-offer-item">
                                                <div class="item-top flex align-items-center margin-bottom-20">
                                                    <div>
                                                        <span class="bold font-20">${SpecialcardsListOffer.cardPerFactual}</span>
                                                        <span>Տարեկան</span>
                                                    </div>
                                                    <div>
                                                        <span class="bold font-20">${SpecialcardsListOffer.cardGracePeriod}</span>
                                                        <span>Քարտի ժամկետը</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <form action="CreditSend" method="get">
                                                        <input type="submit" class="def-button btn-blue"
                                                               value="Իմանալ ավելին"/>
                                                        <input type="hidden" name="ProductCode"
                                                               value="${SpecialcardsListOffer.productCode}"/>
                                                        <input type="hidden" name="PageToGo" value="Cards"/>
                                                    </form>
                                                </div>
                                                <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                    <div class="item-img">
                                                        <img src="${SpecialcardsListOffer.banksList}"/>
                                                    </div>
                                                    <div>
                                                        <span>${SpecialcardsListOffer.productName}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ############################################################ card ENDS -->
        <!-- ############################################################ Partners START -->
        <div class="padding-bt-60 bg-white-dark">
            <div class="inner-container">

                <p class="font-24 title margin-bottom-40">
                    Մեր Գործընկերները
                </p>


                <div class="top">
                    <a href="Ofbanks?Currancy=${requestScope.PageCurrancy}"
                       class="green-link width-icon"> <i
                            class="icon-grid"></i> <span>Մեր Գործընկերները</span>
                    </a>
                </div>


                <div class="partners-container">
                    <div class="owl-carousel owl-theme" id="partnersCarousel">
                        <c:if test="${requestScope.BanksList != null}">
                            <c:forEach items="${requestScope.BanksList}"
                                       varStatus="loop" var="Banks">
                                <div>
                                    <div class="item block-container">
                                        <a href="AllBankServices?bankid=${Banks.bankId}&&Pagelanguage=${requestScope.PageLanguage}&&Currancy=${requestScope.PageCurrancy}"
                                           class="flex align-items-center justify-center">
                                            <div class="item-block__image" style="background-image: url('${Banks.bankSmallLogo}')"></div>
                                        </a>
                                    </div>

                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="padding-bt-60">
            <div class="inner-container">
                <div class="flex align-items-center space-between margin-bottom-35">
                    <div>
                        <p class="font-24 title">
                            Օրենքներ և կանոնակարգեր
                        </p>
                    </div>
                </div>
                <div class="faq-container">
                    <div class="row-md clearfix downloads-row">
                        <div class="col-3">
                            <div class="download-file-item">
                                <a href="<%=request.getContextPath()%>/pdf/ABP.pdf"
                                   target="_blank"
                                   class="flex align-items-center"> <span>
                                            <img src="../images/pdf.svg"/></span> <span>ՀՀ Օրենքը Բանկային գաղտնիքի մասին</span>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="download-file-item">
                                <a href="<%=request.getContextPath()%>/pdf/ABBP.pdf"
                                   target="_blank"
                                   class="flex align-items-center"> <span><img
                                        src="../images/pdf.svg"/></span>
                                    <span>ՀՀ Օրենքը Բանկերի և Բանկային գործունեության</span>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="download-file-item">
                                <a href="<%=request.getContextPath()%>/pdf/SK.pdf"
                                   target="_blank"
                                   class="flex align-items-center"> <span><img
                                        src="../images/pdf.svg"/></span>
                                    <span>ՀՀ օրենքը Սպառողական կրեդիտավորման մասին</span>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="download-file-item">
                                <a href="<%=request.getContextPath()%>/pdf/FH.pdf"
                                   target="_blank"
                                   class="flex align-items-center"> <span><img
                                        src="../images/pdf.svg"/></span>
                                    <span>Ֆինանսական հաշտարար</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ////////////////////////////////////////////////////////////////////////////////////////////////////// Blog START  -->
                <hr class="line margin-top-30 margin-bottom-30">
                <div class="custom-title flex align-items-center space-between margin-bottom-35 flex-wrap">
                    <div>
                        <p class="font-24 title">Օգտակար հոդվածներ</p>
                    </div>
                    <div>
                        <a href="Blog"
                           class="green-link">Բոլոր հոդվածները</a>
                    </div>
                </div>

                <div class="row-md clearfix faq-row main-faq__el">
                    <c:if test="${requestScope.usefulArticlesList != null}">
                        <c:forEach items="${requestScope.usefulArticlesList}" var="blog">
                            <div class="col-3">
                                <div class="faq-item">
                                    <a href="BlogsDetail?Blogid=${blog.UAId}">
                                        <div class="item-img">
                                            <img width="250px" src="${blog.UAImageLink}">
                                        </div>
                                        <div class="item-desc">
                                            <span class="item-title font-16">${blog.UATitleAm}</span>
                                                <%--  <c:set var="languages" value="${requestScope.Pagelanguage}"/>

                                                  <c:choose>
                                                      <c:when test="${languages == 'en_US'}">
                                                          <span class="item-title font-16">${blog.UATitleEn}</span>
                                                      </c:when>
                                                      <c:when test="${languages == 'hy_AM'}">
                                                          <span class="item-title font-16">${blog.UATitleAm}</span>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <span class="item-title font-16">${blog.UATitleRu}</span>
                                                      </c:otherwise>
                                                  </c:choose>--%>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>


    <!-- ////////////////////////////////////////////////////////////////////////////////////////////////////// Blog ENDS -->
    <footer>
        <div class="inner-container large">
            <div class="top flex space-between">
                <div class="flex align-items-center">
                  <span class="logo">
     <img src="https://www.oferta.am/images/oferta.png" alt=""/>
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
						<%--<li class="show-for-tablet"><a
                                href="Ofbanks?Currancy=${requestScope.PageCurrancy}">Գաղտնիության քաղաքականություն</a></li>--%>
						<%--<li class="show-for-tablet"><a
                                href="Blog?Currancy=${requestScope.PageCurrancy}"><fmt:message
                                key="Useful Articles" bundle="${Bundles}"/></a></li>--%>
					</ul>
				</span> <span class="text-right"> 2020 Oferta.am: Նյութերն օգտագործելիս՝ հղումը դեպի Oferta.am պարտադիր է </span>
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
    <%--<script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>--%>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/rangeslider.js/2.3.0/rangeslider.min.js'></script>

    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/js/search.js"></script>
    <script src="<%=request.getContextPath()%>/js/range.js"></script>
    <script src="<%=request.getContextPath()%>/js/modal.js"></script>
    <script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>

    <script type="text/javascript">

    </script>

</body>
</html>


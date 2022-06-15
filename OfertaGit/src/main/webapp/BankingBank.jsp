<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl" %>
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


    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oferta.am</title>
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="css/global.css" type="text/css">
    <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
   <link rel="stylesheet" href="css/style.css">
   <%--   <script src="js/Interval.js"></script>
     <script src="js/jquery-1.9.1.min.js"></script>
     <script src="js/Compare.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
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
							<span class="logo"> <a href="App"><img src="${requestScope.getContextPath}/images/oferta.png" alt=""/></a>
							</span> <span class="hide-for-tablet">
								<ul>
									<li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a
                                            href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
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
                     <span class="compere-icon">
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
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=on&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=credit">ՔԱՐՏԵՐ</a>
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
                            <form action="CompareDeposit" method="post" name="DepositCompare">
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
                                 <form action="DepositBanks" method="post" name="Delete">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                            </c:if>
                             <form action="CompareMortgage" method="post" name="MortgageCompare">
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
                                 <form action="DepositBanks" method="post" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

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
                                     <span  onclick="document.ConsumerCompare.submit();">Սպառողական</span>
                                     <input name="PageToGo" value="Consumer" type="hidden">
                                     <span class="bold font-14">${counterCompare}</span>
                                       <i type="submit" class="icon-delete" onclick="document.DeleteConsumer.submit();"></i>
                                </li>
                                  <form action="DepositBanks" method="post" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="post" name="CarLoanCompare">
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
                                 <form action="DepositBanks" method="post" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

                                    </form>
                            </ul>
                            </c:if>
                           <%-- <form action="CompareMicro" method="post" name="MicroCompare">
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
                                       <i type="submit" class="icon-delete" onclick="document.DeleteMicro.submit();"></i>
                                </li>
                                   <form action="DepositClient" method="post" name="DeleteMicro">
                                        <input type="hidden" name="pageNameToDelete" value="ՄԻԿՐՈՎԱՐԿ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                    </form>
                            </ul>
                            </c:if>--%>
                            <form action="CompareAg" method="post" name="AgCompare">
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
                                 <form action="DepositBanks" method="post" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                        <input name="PageToGo" value="App"  type="hidden">

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


    <div class="padding-bt-60 bg-white-dark banks-page">
        <div class="inner-container">
            <div class="row--md clearfix">
                <div class="col-12 left-side">

                    <!-- /////////////////////////////////////////////////////////////////////////////////////////Bank section Start -->
                    <div class="bg-white-dark padding-bottom-60">
                        <div class="loan-items-container">
                            <div class="table hide-for-mb">
                                <div class="table-row head sorting">
                                    <div class="table-cell"><span>Բանկ</span></div>
                                    <%--  <div class="table-cell"><span>Ավանդի անունը</span></div>--%>
                                    <div class="table-cell">
                                        <span>Տոկոս</span>
                                        <c:set value="${requestScope.arrow}" var="arrow"/>
                                        <c:choose>
                                            <c:when test="${arrow == 1}">
                                                <i class="icon-polygon-down active"></i>
                                                <i class="icon-polygon-up "
                                                   onclick="document.SortingPercentageAsec.submit();"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="icon-polygon-down "
                                                   onclick="document.SortingPercentageDesc.submit();"></i>
                                                <i class="icon-polygon-up active "></i>
                                            </c:otherwise>
                                        </c:choose>
                                        <form action="DepositBanks" method="post" name="SortingPercentageDesc">
                                            <input type="hidden" name="bankId" value='<%=request.getParameter("bankId")%>'/>

                                            <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                            <input name="sorting" value="DescPercent" type="hidden">
                                            <%--  <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                              type="hidden">
                                               <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                                     type="hidden">
                                              <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                                     type="hidden">
                                              <input name="Amount" value="<%=request.getAttribute("range")%>"
                                                     type="hidden">
                                              <input name="months" value="<%=request.getParameter("months")%>"
                                                     type="hidden">
                                             --%>

                                        </form>
                                        <form action="DepositBanks" method="post" name="SortingPercentageAsec">
                                            <input type="hidden" name="bankId" value='<%=request.getParameter("bankId")%>'/>
                                            <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                                   type="hidden">
                                            <input name="sorting" value="AsecPercent" type="hidden">
                                            <%--<input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                                   type="hidden">
                                            <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                            <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                                   type="hidden">
                                            <input name="Amount" value="<%=request.getAttribute("range")%>"
                                                   type="hidden">
                                            <input name="months" value="<%=request.getParameter("months")%>"
                                                   type="hidden">
                                            --%>
                                        </form>

                                    </div>
                                    <div class="table-cell">
                                        <%--<c:set value="${requestScope.arrow2}" var="arrow2"/>
                                        <c:choose>
                                            <c:when test="${arrow2 == 0}">

                                                <i class="icon-polygon-down active"></i>
                                                <i class="icon-polygon-up "
                                                   onclick="document.SortingTimeAsecAmount.submit();"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="icon-polygon-down "
                                                   onclick="document.SortingTimeDescAmount.submit();"></i>
                                                <i class="icon-polygon-up active "></i>
                                            </c:otherwise>
                                        </c:choose>--%>
                                        <span>Ձեր եկամուտը</span>
                                        <%--<form action="Calculate" method="post" name="SortingTimeDescAmount">

                                            <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                            <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                            <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                            <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                            <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                            <input name="months" value="<%=request.getParameter("months")%>" type="hidden">
                                            <input name="sorting" value="DescAmount" type="hidden">

                                        </form>
                                        <form action="Calculate" method="post" name="SortingTimeAsecAmount">

                                            <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                            <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                            <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                            <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                            <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                            <input name="months" value="<%=request.getParameter("months")%>" type="hidden">
                                            <input name="sorting" value="AsecAmount" type="hidden">
                                        </form>--%>
                                    </div>
                                    <div class="table-cell"><span>Մեկնաբանություն</span></div>
                                    <div class="table-cell"></div>
                                </div>
                            </div>
                            <div class="table">
                                <div class="table-row ">
                                    <div class="table-cell item-img">

                                        <c:if test="${requestScope.depositList != null}">
                                            <c:forEach var="firstDepo" items="${requestScope.depositList}"
                                                       varStatus="loop">
                                                <c:set var="percentageMain" value="${firstDepo.DPercentage}"
                                                       scope="request"/>
                                                <c:set value="${firstDepo.bankId}" var="bank"/>
                                                <c:set value="${firstDepo.orderOfAppearance}" var="apperance"/>
                                                <c:set value="${firstDepo.DMinAmount}" var="minAmount" scope="request"/>
                                                <c:set value="${firstDepo.timeLine}" var="months" scope="request"/>

                                                <table class="fold-table">

                                                    <tbody>
                                                    <tr class="view">
                                                        <td>
                                                            <!-- first section -->
                                                            <div class="table">
                                                                <div class="table-row">

                                                                    <div class="table-cell item-img">
                                                                        <img src="${firstDepo.banksList}"/>
                                                                            <%-- <p class="font-12 bold">${firstDepo.productCode}</p>--%>
                                                                        <p class="margin-top-20 hide-for-mb">
                                                                            <a class="green-link font-12 width-icon right-icon ellipsis">
                                                                                    <%-- <span>+ առաջարկ </span> <i class="icon-arrow-down"></i>--%>
                                                                            </a>
                                                                        </p>
                                                                    </div>
                                                                    <div class="table-cell">
                                                                        <p>${firstDepo.productName}</p>
                                                                        <p class="font-24 bold">${firstDepo.DPercentage}%</p>
                                                                        <small>№ ${firstDepo.productCode} </small>

                                                                    </div>
                                                                        <%-- <div class="table-cell">
                                                                                 &lt;%&ndash; <p>Տարեկան ավանդական տոկոսը</p>&ndash;%&gt;
                                                                             <p class="font-24 bold">${firstDepo.DPercentage}%</p>
                                                                         </div>--%>
                                                                        <%-- <div class="table-cell">
                                                                                 &lt;%&ndash;   <p>Ավանդի ժամկետը</p>&ndash;%&gt;
                                                                                     <c:set value="${firstDepo.timeLine}" var="fistDepoMonth" scope="request"/>
                                                                                     <%
                                                                                         int firstDepoMonth = Integer.parseInt(String.valueOf(request.getAttribute("fistDepoMonth")));
                                                                                         int convertfirstDepoMonth = firstDepoMonth/30;
                                                                                     %>
                                                                             <p class="font-24 bold"><%=convertfirstDepoMonth%> Ամիս</p>
                                                                         </div>--%>
                                                                        <%-- <div class="table-cell">
                                                                             <p>Առավելագույն գումարը</p>
                                                                             <p class="font-24 bold">${firstDepo.DMaxAmount}</p>
                                                                         </div>--%>

                                                                    <div class="table-cell">
                                                                            <%-- <p>Ձեր եկամուտը</p>--%>
                                                                        <p >

                                                                                    <%!
                                                                                     int calculatePercentage(float Amount, double Percentage, int month) {
                                                                                         double firstStep = (Amount / 100 * Percentage);
                                                                                         System.out.println("firstStep " + firstStep);
                                                                                         float secondStep = (float) (firstStep / 365);
                                                                                         System.out.println("secondStep " + secondStep);
                                                                                         float finalStep = (float) (secondStep * 30 );
                                                                                         System.out.println("finalStep " + finalStep);
                                                                                         float TheTen = (float) (finalStep * 0.10);
                                                                                         System.out.println("TheTen " + TheTen);
                                                                                         float result = (finalStep - TheTen)* month;
                                                                                         System.out.println("result " + result);
                                                                                         return (int) result;
                                                                                     }
                                                                                     %>

                                                                                    <%
                                                                                    double percent = (double) request.getAttribute("percentageMain");
                                                                                    int Amount = Integer.parseInt(String.valueOf(request.getAttribute("minAmount")));
                                                                                    int mnthsForConvert = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                                                                                    int month = mnthsForConvert / 30;
                                                                                    %>

                                                                                <c:set value="${requestScope.PageCurrancy}"
                                                                                       var="curancy"/>
                                                                            <c:choose>
                                                                            <c:when test="${curancy == 'AMD'}">
                                                                        <h2 class="timer count-title count-number font-28 bold"
                                                                            data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                            data-speed="1739"></h2>
                                                                            <%--  <fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value='<%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                        </c:when>
                                                                        <c:when test="${curancy == 'USD'}">
                                                                            <h2 class="timer count-title count-number font-28 bold"
                                                                                data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                data-speed="1739"></h2>
                                                                            <%--  <fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value=' <%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                        </c:when>
                                                                        <c:when test="${curancy == 'RUB'}">
                                                                            <h2 class="timer count-title count-number font-28 bold"
                                                                                data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                data-speed="1739"></h2>
                                                                            <%-- <fmt:formatNumber type="number"
                                                                                               maxFractionDigits="3"
                                                                                               value=' <%=calculatePercentage(Amount, percent, month) %>'/> ‎--%>
                                                                        </c:when>
                                                                        <c:when test="${curancy == 'EUR'}">
                                                                            <h2 class="timer count-title count-number font-28 bold"
                                                                                data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                data-speed="1739"></h2>
                                                                            <%--<fmt:formatNumber type="number"
                                                                                              maxFractionDigits="3"
                                                                                              value=' <%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                        </c:when>
                                                                        </c:choose>


                                                                        </p>
                                                                    </div>
                                                                    <div class="table-cell">
                                                                            <%--   <p>Ավանդի ժամկետը</p>--%>
                                                                            <%--<sql:setDataSource var="db"
                                                                                               driver="com.mysql.jdbc.Driver"
                                                                                               url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                               user="root" password="YLf{}cJ,Kvd%"/>--%>
                                                                        <sql:setDataSource var="db"
                                                                                           driver="com.mysql.jdbc.Driver"
                                                                                           url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                           user="oferta_root"
                                                                                           password="mnbvcxz00A!"/>
                                                                        <sql:query dataSource="${db}" var="comment">
                                                                            SELECT * FROM commentscontroller ;
                                                                        </sql:query>
                                                                        <c:forEach var="CommentsDao"
                                                                                   items="${comment.rows}"
                                                                                   varStatus="loop">
                                                                            <c:set value="${CommentsDao.productCode}"
                                                                                   var="productCodeSql"/>
                                                                            <c:set value="${firstDepo.productCode}"
                                                                                   var="productCode"/>
                                                                            <c:if test="${productCode == productCodeSql }">
                                                                                <c:out value="${CommentsDao.Comment1_Am}"/>
                                                                                <%--  <c:out value="${CommentsDao.Comment2_Am}"/>
                                                                                  <c:out value="${CommentsDao.Comment3_Am}"/>--%>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div class="table-cell text-center last">
                                                                        <p class="margin-bottom-20 show-for-mb text-left">
                                                                            <a class="green-link font-12 width-icon right-icon ellipsis"
                                                                               onclick="toggleSubSuggestions()">
                                                                                <span><%--+5 առաջարկ --%></span> <i
                                                                                    class="icon-arrow-down"></i>
                                                                            </a>
                                                                        </p>
                                                                        <c:set var="sendRequest"
                                                                               value="${firstDepo.sendRequest}"/>
                                                                        <c:set var="gotoPage"
                                                                               value="${firstDepo.gotoPage}"/>
                                                                        <c:set var="lastLogic"
                                                                               value="${firstDepo.lastLogic}"/>
                                                                        <c:choose>
                                                                            <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic == 0}">
                                                                                <a href="Credits?ProductCode=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=Mortgage"
                                                                                   class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                                                                    ավելին</a>
                                                                                <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getParameter("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&months=${firstDepo.timeLine}&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                   class="blue-link font-12">Համեմատել</a>
                                                                                <%--  <p><a href="index.php?page=compare/deposit"
                                                                                        class="blue-link font-12">Համեմատել</a></p>--%>

                                                                            </c:when>

                                                                            <c:when test="${gotoPage == 0 && sendRequest==0  && lastLogic == 1}">
                                                                                <a href="${firstDepo.bankLink}"
                                                                                   class="def-button btn-green with-shadow  margin-bottom-15"
                                                                                   target="_blank">Անցնել
                                                                                    էջ</a>
                                                                                <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getParameter("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&months=${firstDepo.timeLine}&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                   class="blue-link font-12">Համեմատել</a>
                                                                                <%--<p><a href="index.php?page=compare/deposit"
                                                                                      class="blue-link font-12">Համեմատել</a></p>--%>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <c:set var="sendRequest" value="${firstDepo.sendRequest}"/>
                                                                                <c:set var="gotoPage" value="${firstDepo.gotoPage}"/>
                                                                                <c:set var="lastLogic" value="${firstDepo.lastLogic}"/>
                                                                                <c:choose>
                                                                                    <c:when test="${gotoPage == 1 || sendRequest== 1}">
                                                                                        <a href="Credits?ProductCode=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=Mortgage"
                                                                                           class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                                                                            ավելին</a>
                                                                                        <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getParameter("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&months=${firstDepo.timeLine}&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                           class="blue-link font-12">Համեմատել</a>

                                                                                    </c:when>
                                                                                </c:choose>
                                                                                <c:choose>
                                                                                    <c:when test="${gotoPage == 0 && sendRequest==0 && lastLogic==1}">
                                                                                        <a href="${firstDepo.bankLink}"
                                                                                           class="def-button btn-green with-shadow  margin-bottom-15"
                                                                                           target="_blank">Անցնել
                                                                                            էջ</a>
                                                                                        <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getParameter("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&months=${firstDepo.timeLine}&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                           class="blue-link font-12">Համեմատել</a>

                                                                                    </c:when>
                                                                                </c:choose>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="padding-bt-60">
                            <div class="inner-container">
                                <p class="font-24 title margin-bottom-35">Այլ բանկերի առաջարկներ</p>
                                <div class="loan-items-container border-top">
                                    <div class="table">
                                        <div class="table-row ">
                                            <div class="table-cell item-img">

                                                <c:if test="${requestScope.deposiOposit != null}">
                                                    <c:forEach var="firstDepo" items="${requestScope.deposiOposit}"
                                                               varStatus="loop">
                                                        <c:set var="percentageMain" value="${firstDepo.DPercentage}"
                                                               scope="request"/>
                                                        <c:set value="${firstDepo.bankId}" var="bank"/>
                                                        <c:set value="${firstDepo.orderOfAppearance}" var="apperance"/>

                                                        <table class="fold-table">

                                                            <tbody>
                                                            <tr class="view">
                                                                <td>
                                                                    <!-- first section -->
                                                                    <div class="table">
                                                                        <div class="table-row">

                                                                            <div class="table-cell item-img">
                                                                                <img src="${firstDepo.banksList}"
                                                                                />
                                                                                    <%-- <p class="font-12 bold">${firstDepo.productCode}</p>--%>
                                                                                <p class="margin-top-20 hide-for-mb">
                                                                                    <a class="green-link font-12 width-icon right-icon ellipsis">
                                                                                            <%-- <span>+ առաջարկ </span> <i class="icon-arrow-down"></i>--%>
                                                                                    </a>
                                                                                </p>
                                                                            </div>
                                                                            <div class="table-cell">
                                                                                <p>${firstDepo.productName}</p>
                                                                                <p class="font-24 bold">${firstDepo.DPercentage}%</p>
                                                                                <small>
                                                                                    № ${firstDepo.productCode} </small>

                                                                            </div>
                                                                                <%-- <div class="table-cell">
                                                                                         &lt;%&ndash; <p>Տարեկան ավանդական տոկոսը</p>&ndash;%&gt;
                                                                                     <p class="font-24 bold">${firstDepo.DPercentage}%</p>
                                                                                 </div>--%>
                                                                                <%-- <div class="table-cell">
                                                                                         &lt;%&ndash;   <p>Ավանդի ժամկետը</p>&ndash;%&gt;
                                                                                             <c:set value="${firstDepo.timeLine}" var="fistDepoMonth" scope="request"/>
                                                                                             <%
                                                                                                 int firstDepoMonth = Integer.parseInt(String.valueOf(request.getAttribute("fistDepoMonth")));
                                                                                                 int convertfirstDepoMonth = firstDepoMonth/30;
                                                                                             %>
                                                                                     <p class="font-24 bold"><%=convertfirstDepoMonth%> Ամիս</p>
                                                                                 </div>--%>
                                                                                <%-- <div class="table-cell">
                                                                                     <p>Առավելագույն գումարը</p>
                                                                                     <p class="font-24 bold">${firstDepo.DMaxAmount}</p>
                                                                                 </div>--%>

                                                                            <div class="table-cell">
                                                                                    <%-- <p>Ձեր եկամուտը</p>--%>
                                                                                <p class="font-24 bold">

                                                                                            <%!
                                                                                               static double percent;
                                                                                               static int Amount;
                                                                                               static int month;
                                                                                        %>


                                                                                            <%
                                                                                      percent= (double) request.getAttribute("percentageMain");
                                                                                     Amount= Integer.parseInt(String.valueOf(request.getAttribute("minAmount")));
                                                                                    int mnthsForConvert = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                                                                                     month = mnthsForConvert / 30;
                                                                                    %>

                                                                                        <c:set value="${requestScope.PageCurrancy}"
                                                                                               var="curancy"/>
                                                                                    <c:choose>
                                                                                    <c:when test="${curancy == 'AMD'}">
                                                                                <h2 class="timer count-title count-number font-28 bold"
                                                                                    data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                    data-speed="1739"></h2>
                                                                                    <%--  <fmt:formatNumber type="number"
                                                                                                        maxFractionDigits="3"
                                                                                                        value='<%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                                </c:when>
                                                                                <c:when test="${curancy == 'USD'}">
                                                                                    <h2 class="timer count-title count-number font-28 bold"
                                                                                        data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                        data-speed="1739"></h2>
                                                                                    <%--  <fmt:formatNumber type="number"
                                                                                                        maxFractionDigits="3"
                                                                                                        value=' <%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                                </c:when>
                                                                                <c:when test="${curancy == 'RUB'}">
                                                                                    <h2 class="timer count-title count-number font-28 bold"
                                                                                        data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                        data-speed="1739"></h2>
                                                                                    <%-- <fmt:formatNumber type="number"
                                                                                                       maxFractionDigits="3"
                                                                                                       value=' <%=calculatePercentage(Amount, percent, month) %>'/> ‎--%>
                                                                                </c:when>
                                                                                <c:when test="${curancy == 'EUR'}">
                                                                                    <h2 class="timer count-title count-number font-28 bold"
                                                                                        data-to="<%=calculatePercentage(Amount, percent, month) %>"
                                                                                        data-speed="1739"></h2>
                                                                                    <%--<fmt:formatNumber type="number"
                                                                                                      maxFractionDigits="3"
                                                                                                      value=' <%=calculatePercentage(Amount, percent, month) %>'/>--%>
                                                                                </c:when>
                                                                                </c:choose>


                                                                                </p>
                                                                            </div>
                                                                            <div class="table-cell">
                                                                                    <%--   <p>Ավանդի ժամկետը</p>--%>
                                                                                    <%--<sql:setDataSource var="db"
                                                                                                       driver="com.mysql.jdbc.Driver"
                                                                                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                                       user="root" password="YLf{}cJ,Kvd%"/>--%>
                                                                                <sql:setDataSource var="db"
                                                                                                   driver="com.mysql.jdbc.Driver"
                                                                                                   url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                                   user="oferta_root"
                                                                                                   password="mnbvcxz00A!"/>
                                                                                <sql:query dataSource="${db}"
                                                                                           var="comment">
                                                                                    SELECT * FROM commentscontroller ;
                                                                                </sql:query>
                                                                                <c:forEach var="CommentsDao"
                                                                                           items="${comment.rows}"
                                                                                           varStatus="loop">
                                                                                    <c:set value="${CommentsDao.productCode}"
                                                                                           var="productCodeSql"/>
                                                                                    <c:set value="${firstDepo.productCode}"
                                                                                           var="productCode"/>
                                                                                    <c:if test="${productCode == productCodeSql }">
                                                                                        <c:out value="${CommentsDao.Comment1_Am}"/>
                                                                                        <%--  <c:out value="${CommentsDao.Comment2_Am}"/>
                                                                                          <c:out value="${CommentsDao.Comment3_Am}"/>--%>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </div>
                                                                            <div class="table-cell text-center last">
                                                                                <p class="margin-bottom-20 show-for-mb text-left">
                                                                                    <a class="green-link font-12 width-icon right-icon ellipsis"
                                                                                       onclick="toggleSubSuggestions()">
                                                                                        <span><%--+5 առաջարկ --%></span>
                                                                                        <i
                                                                                                class="icon-arrow-down"></i>
                                                                                    </a>
                                                                                </p>
                                                                                <c:set var="sendRequest"
                                                                                       value="${firstDepo.sendRequest}"/>
                                                                                <c:set var="gotoPage"
                                                                                       value="${firstDepo.gotoPage}"/>
                                                                                <c:set var="lastLogic"
                                                                                       value="${firstDepo.lastLogic}"/>
                                                                                <c:choose>
                                                                                    <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic == 0}">
                                                                                        <a href="Credits?ProductCode=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=Mortgage"
                                                                                           class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                                                                            ավելին</a>
                                                                                        <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                           class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                        <%--  <p><a href="index.php?page=compare/deposit"
                                                                                                class="blue-link font-12">Համեմատել</a></p>--%>

                                                                                    </c:when>
                                                                                    <c:when test="${gotoPage == 0 && sendRequest==0  && lastLogic == 1}">
                                                                                        <a href="${firstDepo.bankLink}"
                                                                                           class="def-button btn-green with-shadow  margin-bottom-15"
                                                                                           target="_blank">Անցնել
                                                                                            էջ</a>
                                                                                        <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                           class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                        <%--<p><a href="index.php?page=compare/deposit"
                                                                                              class="blue-link font-12">Համեմատել</a></p>--%>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <a href="DepositClient?bankId=${firstDepo.bankId}&&id=${firstDepo.productCode}&&months=${firstDepo.timeLine}&&MaxAmounr=${firstDepo.DMaxAmount}&&City=<%=request.getAttribute("City")%>&&Currancy=${firstDepo.currancy}&&PageToGo=BankingDepo&&Amount=${firstDepo.DMinAmount}&&PageName=BankingDepo"
                                                                                           class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                        <%-- <p><a href="index.php?page=compare/deposit"
                                                                                               class="blue-link font-12">Համեմատել</a></p>--%>
                                                                                    </c:otherwise>
                                                                                </c:choose>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                            </tbody>
                                                        </table>
                                                    </c:forEach>
                                                </c:if>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="see-all flex align-items-center justify-center">
                                        <span class="green-link width-icon right-icon">
                                            <span>Տեսնել ավելին</span>
                                            <i class="icon-arrow-down font-9"></i>
                                        </span>
                                </div>
                            </div>
                        </div>

                    </div>
                    <footer>
                        <div class="inner-container large">
                            <div class="top flex space-between">
                                <div class="flex align-items-center">
                                        <span class="logo">  <img src="${requestScope.getContextPath}/images/oferta.png" alt=""/>
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
        </span> <span class="text-right"> 2009-2019 domain.am : նյութերը
        օգտագործելիս, հղում դեպի oferta.am պարտադիր է: </span>
                            </div>
                        </div>

                    </footer>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    $(function () {
        $("#datepicker").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>

<script src="<%=request.getContextPath()%>/js/main.js"></script>
<script src="<%=request.getContextPath()%>/js/search.js"></script>
<script src="<%=request.getContextPath()%>/js/range.js"></script>
<script src="<%=request.getContextPath()%>/js/modal.js"></script>
<script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
<script src="<%=request.getContextPath()%>/js/carousel.js"></script>

<script>
    $(document).ready(function () {
        sessionStorage.clear();
        $(".linkScrollDown").click(function () {
            let sizeScrollDown = $(window).scrollTop();
            sessionStorage.setItem("sizeScrollDown", sizeScrollDown);
        });
    });
    if (sessionStorage['sizeScrollDown']) {
        $("html, body").animate({scrollTop: sessionStorage.getItem("sizeScrollDown")}, "slow");
    } else {
        console.log("empty session storage");
    }
</script>
<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        });
    }
</script>
<script>
    var obj;
    obj = jQuery.parseJSON('${jsonArray}');
    if (obj.WorningMessage === "Դուք արդեն ունեք նույն ID-ին") {
        alert("Դուք արդեն ունեք նույն ID-ին");
        console.log(obj.WorningMessage);

    } else if (obj.WorningMessage === "Դուք չեք կարող 4 ից ավել համեմատել։") {
        alert("Դուք չեք կարող 4 ից ավել համեմատել");
        console.log(obj.WorningMessage);
    } else {
        console.log(obj.WorningMessage);
    }
</script>

</body>
</html>


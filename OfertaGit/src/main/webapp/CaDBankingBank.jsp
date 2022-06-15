<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
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

   <%--   <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/Compare.js"></script>--%>

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
									<%--<li><a href="#">Մեր Գործընկերները</a></li>--%>
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
                            <c:forEach var="size" items="${requestScope.comparListCarLoan}" varStatus="TheCount">
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
                                 <form action="CardBanks" method="post" name="Delete">
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
                                 <form action="CardBanks" method="post" name="DeleteHipotek">
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
                                  <form action="CardBanks" method="post" name="DeleteConsumer">
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
                                 <form action="CardBanks" method="post" name="DeleteCar">
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
                                 <form action="CardBanks" method="post" name="DeleteAG">
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
                        <div class="inner-container">
                            <div class="bank-header">
                                <div class="bg" style="background-image: url('../../../images/10.svg')">
                                    <div class="top">
                                        <div class="item-logo">
                                            <a href="">
                                                <img src="<%=request.getAttribute("BankImageSmall")%>" alt=""
                                                     style="width: 100px">
                                            </a>
                                        </div>
                                        <p class="font-36"><%=request.getAttribute("BankName")%>
                                        </p>
                                    </div>
                                </div>
                            </div>

                            <div class="loan-items-container">

                                <div class="table hide-for-mb">
                                    <div class="table-row head sorting">
                                        <div class="table-cell"><span>Բանկ</span></div>
                                        <%-- <div class="table-cell"><span>Քարդի անունը</span></div>--%>
                                        <div class="table-cell">

                                            <span>Տոկոս</span>
                                            <c:set value="${requestScope.arrow}" var="arrow"/>
                                            <c:choose>
                                                <c:when test="${arrow == 0}">
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

                                            <c:forEach items="${requestScope.depositList}" var="first"
                                                       varStatus="page">
                                                <c:set var="MaxAMount" value="${first.cardMaxCreditLimit}" scope="request"/>
                                                <c:set var="MinAMount" value="${first.cardMinCreditLimit}" scope="request"/>
                                                <c:set var="Time" value="${first.cardGracePeriod}" scope="request"/>

                                            </c:forEach>
                                            <form action="CardBanks" method="post"
                                                  name="SortingPercentageDesc">

                                                <input name="MaxAmounr"
                                                       value='<%=request.getAttribute("MaxAMount")%>'
                                                       type="hidden">
                                                <input name="City" value="<%=request.getParameter("City")%>"
                                                       type="hidden">
                                                <input name="Currancy"
                                                       value="<%=request.getParameter("Currancy")%>"
                                                       type="hidden">
                                                <input name="PageToGo"
                                                       value="<%=request.getParameter("PageToGo")%>"
                                                       type="hidden">
                                                <input name="Amount" value='<%=request.getAttribute("MinAMount")%>'
                                                       type="hidden">
                                                <input name="months" value='<%=request.getAttribute("Time")%>'
                                                       type="hidden">
                                                <input name="bankId" value='<%=request.getAttribute("Bankid")%>'
                                                       type="hidden">
                                                <input name="sorting" value="DescPercent" type="hidden">

                                            </form>
                                            <form action="CardBanks" method="post"
                                                  name="SortingPercentageAsec">

                                                <input name="MaxAmounr"
                                                       value='<%=request.getAttribute("MaxAMount")%>'
                                                       type="hidden">
                                                <input name="City" value="<%=request.getParameter("City")%>"
                                                       type="hidden">
                                                <input name="Currancy"
                                                       value="<%=request.getParameter("Currancy")%>"
                                                       type="hidden">
                                                <input name="PageToGo"
                                                       value="<%=request.getParameter("PageToGo")%>"
                                                       type="hidden">
                                                <input name="Amount" value='<%=request.getAttribute("MinAMount")%>'
                                                       type="hidden">
                                                <input name="months" value='<%=request.getAttribute("Time")%>'
                                                       type="hidden">
                                                <input name="bankId" value='<%=request.getAttribute("Bankid")%>'
                                                       type="hidden">
                                                <input name="sorting" value="AsecPercent" type="hidden">
                                            </form>

                                        </div>
                                        <div class="table-cell">
                                            <span>Մեկնաբանություն</span>
                                            <%-- <c:set value="${requestScope.arrow2}" var="arrow2"/>
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
                                             </c:choose>
                                             <span>ժամկետը</span>

                                                     <form action="CardBanks" method="post"
                                                           name="SortingTimeDescAmount">
                                                         <input name="MaxAmounr"
                                                                value='<%=request.getAttribute("MaxAMount")%>'
                                                                type="hidden">
                                                         <input name="City" value="<%=request.getParameter("City")%>"
                                                                type="hidden">
                                                         <input name="Currancy"
                                                                value="<%=request.getParameter("Currancy")%>"
                                                                type="hidden">
                                                         <input name="PageToGo"
                                                                value="<%=request.getParameter("PageToGo")%>"
                                                                type="hidden">
                                                         <input name="Amount" value='<%=request.getAttribute("MinAMount")%>'
                                                                type="hidden">
                                                         <input name="months" value='<%=request.getAttribute("Time")%>'
                                                                type="hidden">
                                                         <input name="bankId" value='<%=request.getAttribute("Bankid")%>'
                                                                type="hidden">
                                                         <input name="sorting" value="DescAmount" type="hidden">

                                                     </form>
                                                     <form action="CardBanks" method="post"
                                                           name="SortingTimeAsecAmount">

                                                         <input name="MaxAmounr"
                                                                value='<%=request.getAttribute("MaxAMount")%>'
                                                                type="hidden">
                                                         <input name="City" value="<%=request.getParameter("City")%>"
                                                                type="hidden">
                                                         <input name="Currancy"
                                                                value="<%=request.getParameter("Currancy")%>"
                                                                type="hidden">
                                                         <input name="PageToGo"
                                                                value="<%=request.getParameter("PageToGo")%>"
                                                                type="hidden">
                                                         <input name="Amount" value='<%=request.getAttribute("MinAMount")%>'
                                                                type="hidden">
                                                         <input name="months" value='<%=request.getAttribute("Time")%>'
                                                                type="hidden">
                                                         <input name="bankId" value='<%=request.getAttribute("Bankid")%>'
                                                                type="hidden">
                                                         <input name="sorting" value="AsecAmount" type="hidden">
                                                     </form>

 --%>
                                        </div>
                                        <%-- <div class="table-cell"><span>Եկամուտ</span></div>--%>
                                        <div class="table-cell"></div>
                                    </div>
                                </div>


                                <div class="table">
                                    <div class="table-row">
                                        <div class="table-cell item-img">

                                            <c:if test="${requestScope.depositList != null}">
                                                <c:forEach var="firstDepo"
                                                           items="${requestScope.depositList}"
                                                           varStatus="loop">
                                                    <c:set var="percentageMain" value="${firstDepo.cardPerFactual}"
                                                           scope="request"/>
                                                    <c:set value="${firstDepo.bankId}" var="bank"/>
                                                    <c:set value="${firstDepo.orderOfAppearance}"
                                                           var="apperance"/>
                                                    <table class="fold-table">
                                                        <tbody>
                                                        <tr class="view">
                                                            <td>
                                                                <!-- first section -->
                                                                <div class="table">
                                                                    <div class="table-row">

                                                                        <div class="table-cell item-img">
                                                                            <img src="${firstDepo.banksList}"/>
                                                                            <p class="margin-top-20 hide-for-mb">
                                                                                <a class="green-link font-12 width-icon right-icon ellipsis">
                                                                                        <%-- <span>+ առաջարկ </span> <i class="icon-arrow-down"></i>--%>
                                                                                </a>
                                                                            </p>
                                                                        </div>
                                                                        <div class="table-cell">
                                                                            <p>${firstDepo.cardName}</p>
                                                                            <p class="font-24 bold">${firstDepo.cardPerFactual}%</p>
                                                                            <small>№ ${firstDepo.productCode} </small>

                                                                        </div>
                                                                            <%-- <div class="table-cell">
                                                                                 <p>Տարեկան ավանդական տոկոսը</p>
                                                                                 <p class="font-24 bold">${firstDepo.cardPerFactual}%</p>
                                                                             </div>
                                                                             <div class="table-cell">
                                                                                 <p>Ավանդի ժամկետը</p>
                                                                                 <p class="font-24 bold">${firstDepo.cardGracePeriod}
                                                                                     Ամիս</p>
                                                                             </div>--%>
                                                                            <%-- <div class="table-cell">
                                                                                 <p>Առավելագույն գումարը</p>
                                                                                 <p class="font-24 bold">${firstDepo.cardMaxCreditLimit}</p>
                                                                             </div>--%>

                                                                            <%--<div class="table-cell">
                                                                                <p>Ձեր եկամուտը</p>
                                                                                <p class="font-24 bold">
                                                                                    <c:set var="minAmount"
                                                                                           value='${firstDepo.cardMinCreditLimit}'
                                                                                           scope="request"/>
                                                                                    <c:set var="percentageMain"
                                                                                           value='${firstDepo.cardPerFactual}'
                                                                                           scope="request"/>
                                                                                    <c:set var="monthsss"
                                                                                           value='${firstDepo.cardGracePeriod}'
                                                                                           scope="request"/>
                                                                                    <%!
                                                                                        int calculatePercentage(float Amount, double Percentage, int month) {
                                                                                            double percentage = (Percentage / 100) / 12;
                                                                                            int result = (int) (((Amount * percentage)) * month);
                                                                                            return result;
                                                                                        }
                                                                                    %>

                                                                                    <%
                                                                                        double percent = Double.parseDouble(String.valueOf(request.getAttribute("percentageMain")));
                                                                                        int Amount = Integer.parseInt(String.valueOf(request.getAttribute("minAmount")));//100000
                                                                                        int month = Integer.parseInt(String.valueOf(request.getAttribute("monthsss")));//1
                                                                                    %>

                                                                                    <%=calculatePercentage(Amount, percent, month) %>
                                                                                </p>
                                                                            </div>--%>
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
                                                                            <button onclick = "createPopupWin('SendEmailToBank.jsp?ProductCode=${firstDepo.productCode}',
                                                                                    'oferta.am', 800, 650);" class="def-button btn-green with-shadow  margin-bottom-15">Ուղարկել հայտ
                                                                            </button>
                                                                            <input type="hidden" name="BankId" value="${firstDepo.bankId}"/>
                                                                            <input type="hidden" name="productCode" value="${firstDepo.productCode}"/>
                                                                                <%-- </form>--%>
                                                                            <p><a href="CreditSend?PageToGo=Cards&&ProductCode=${firstDepo.productCode}"
                                                                                  class="green-link font-12">Իմանալ
                                                                                ավելին</a></p>
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
                                            <c:if test="${requestScope.deposiOposit != null}">
                                                <c:forEach var="oposit" items="${requestScope.deposiOposit}">
                                                    <div class="table-row">
                                                        <div class="table-cell item-img">
                                                            <c:forEach items="${oposit.banksList}" var="image">
                                                                <img src="${oposit.banksList}"/>
                                                            </c:forEach>
                                                            <p class="margin-top-20 hide-for-mb">
                                                                    <%-- <a class="green-link font-12 width-icon right-icon ellipsis"
                                                                        onclick="toggleSubSuggestions()">
                                                                         <span>+5 առաջարկ </span> <i
                                                                             class="icon-arrow-down"></i>
                                                                     </a>--%>
                                                            </p>
                                                        </div>
                                                        <div class="table-cell">
                                                            <p>${oposit.cardName}</p>
                                                            <p class="font-24 bold">${oposit.cardPerFactual}%</p>
                                                            <small>№ ${oposit.productCode} </small>

                                                        </div>
                                                            <%--<div class="table-cell">
                                                                <c:set var="minAmount2"
                                                                       value='${oposit.cardMinCreditLimit}'
                                                                       scope="request"/>
                                                                <c:set var="percentageMain2"
                                                                       value='${oposit.cardPerFactual}'
                                                                       scope="request"/>
                                                                <c:set var="monthsss2"
                                                                       value='${oposit.cardGracePeriod}'
                                                                       scope="request"/>
                                                                <p>Ձեր եկամուտը</p>
                                                                <%!
                                                                    int calculatePercentage2(float Amount, double Percentage, int month) {
                                                                        double percentage = (Percentage / 100) / 12;
                                                                        int result = (int) (((Amount * percentage)) * month);
                                                                        return result;
                                                                    }
                                                                %>

                                                                <%
                                                                    double percent2 = Double.parseDouble(String.valueOf(request.getAttribute("percentageMain2")));
                                                                    int Amount2 = Integer.parseInt(String.valueOf(request.getAttribute("minAmount2")));//100000
                                                                    int month2 = Integer.parseInt(String.valueOf(request.getAttribute("monthsss2")));//1
                                                                %>


                                                                <p class="font-28 bold"><%=calculatePercentage2(Amount2, percent2, month2) %>
                                                                </p>
                                                            </div>--%>
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
                                                                <c:set value="${oposit.productCode}"
                                                                       var="productCode"/>
                                                                <c:if test="${productCode == productCodeSql }">
                                                                    <c:out value="${CommentsDao.Comment1_Am}"/>
                                                                    <%--  <c:out value="${CommentsDao.Comment2_Am}"/>
                                                                      <c:out value="${CommentsDao.Comment3_Am}"/>--%>
                                                                </c:if>
                                                            </c:forEach>
                                                        </div>
                                                        <div class="table-cell text-center last">
                                                            <button onclick = "createPopupWin('SendEmailToBank.jsp?ProductCode=${oposit.productCode}',
                                                                    'oferta.am', 800, 650);" class="def-button btn-green with-shadow  margin-bottom-15">Ուղարկել հայտ
                                                            </button>
                                                            <input type="hidden" name="BankId" value="${oposit.bankId}"/>
                                                            <input type="hidden" name="productCode" value="${oposit.productCode}"/>
                                                                <%-- </form>--%>
                                                            <p><a href="CreditSend?PageToGo=Cards&&ProductCode=${oposit.productCode}"
                                                                  class="green-link font-12">Իմանալ
                                                                ավելին</a></p>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
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
                            function createPopupWin(pageURL, pageTitle,
                                                    popupWinWidth, popupWinHeight) {
                                var left = (screen.width - popupWinWidth) / 2;
                                var top = (screen.height - popupWinHeight) / 4;

                                var myWindow = window.open(pageURL, pageTitle,
                                    'resizable=yes, width=' + popupWinWidth
                                    + ', height=' + popupWinHeight + ', top='
                                    + top + ', left=' + left);
                            }
                        </script>


</body>
</html>


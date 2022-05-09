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
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/Compare.js"></script>

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
                       <c:if test="${requestScope.comparListDeposit != null}">
                           <c:forEach var="size" items="${requestScope.comparListCarLoan}" varStatus="TheCount">
                               <span class="count"><c:out value="${TheCount.count}"/></span>
                           </c:forEach>
                       </c:if>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipMb">
                        <div class="tooltip">
                            <span class="tooltip-title">Համեմատության</span>
                            <form action="CompareDeposit" method="post" name="DepositComparesmall">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                                <input name="PageToGo" value="Deposits" type="hidden">
                            </form>

                            <c:if test="${requestScope.comparListDeposit != null}">
                            <ul>
                                <li>
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span onclick="document.DepositComparesmall.submit();">Ավանդ</span>

                                    <span class="bold font-14">${counterDeposit}</span>
                                     <i type="submit" class="icon-delete" onclick="document.Deletesmall.submit();"></i>

                                </li>
                                 <form action="CardClient" method="post" name="Deletesmall">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
                                    </form>
                            </ul>
                            </c:if>
                             <form action="CompareMortgage" method="post" name="MortgageComparesmall">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">

                            </form>
                            <c:if test="${requestScope.comparListMortgage != null}">
                            <ul>
                                <li>
                                    <c:forEach var="MortgagCompare" items="${requestScope.comparListMortgage}"
                                               varStatus="TheCount">
                                        <c:set var="counterMortgag" value="${TheCount.count}" scope="request"/>


                                    </c:forEach>
                                     <span onclick="document.MortgageComparesmall.submit();">Հիփոթեք</span>
                                     <input name="PageToGo" value="Mortgage" type="hidden">
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteHipoteksmall.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteHipoteksmall">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
                                    </form>
                            </ul>
                            </c:if>
                            <form action="CompareConsumer" method="post" name="ConsumerComparesmall">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                             <c:if test="${requestScope.comparListConsumer != null}">
                            <ul>
                                <li>
                                    <c:forEach var="ConsumerCompare" items="${requestScope.comparListConsumer}"
                                               varStatus="TheCount">
                                        <c:set var="counterCompare" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span onclick="document.ConsumerComparesmall.submit();">Սպառողական</span>
                                     <input name="PageToGo" value="Consumer" type="hidden">
                                     <span class="bold font-14">${counterCompare}</span>
                                       <i type="submit" class="icon-delete"
                                          onclick="document.DeleteConsumersmall.submit();"></i>
                                </li>
                                  <form action="CardClient" method="post" name="DeleteConsumersmall">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
                                    </form>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="post" name="CarLoanComparesmall">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListCarLoan != null}">
                            <ul>
                                <li>
                                    <c:forEach var="CarLoanCompare" items="${requestScope.comparListCarLoan}"
                                               varStatus="TheCount">
                                        <c:set var="counterCarLoan" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                    <span onclick="document.CarLoanComparesmall.submit();">Ավտովարկ</span>
                                      <span class="bold font-14">${counterCarLoan}</span>
                                       <i type="submit" class="icon-delete"
                                          onclick="document.DeleteCarsmall.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteCarsmall">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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
                            <form action="CompareAg" method="post" name="AgComparesmall">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListAg != null}">
                            <ul>
                                <li>
                                    <c:forEach var="AgCompare" items="${requestScope.comparListAg}"
                                               varStatus="TheCount">
                                        <c:set var="counterAg" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span onclick="document.AgComparesmall.submit();">Գյուղատնտեսական</span>
                                      <span class="bold font-14">${counterAg}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteAGsmall.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteAGsmall">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Dram" value=<%=request.getAttribute("Dram")%> type="hidden">
                                       <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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

                    <%--     <%
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
                               <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>
                               <%!
                                   int countCompare;
                               %>
                               <%
                                   countCompare++;
                               %>
                               <span class="count"><%=countCompare%></span>
                           </c:forEach>
                       </c:if>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipWeb">
                        <div class="tooltip">
                            <span class="tooltip-title">Համեմատության</span>
                            <form action="CompareDeposit" method="post" name="DepositCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                                <input name="PageToGo" value="Deposits" type="hidden">
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
                                     <i type="submit" class="icon-delete" onclick="document.Delete.submit();"></i>

                                </li>
                                 <form action="CardClient" method="post" name="Delete">

                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                         <input name="Dram" value=<%=request.getParameter("Dram")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                        <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
                                    </form>
                                </form>
                            </ul>
                            </c:if>
                             <form action="CompareMortgage" method="post" name="MortgageCompare">
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
                                     <input name="PageToGo" value="Mortgage" type="hidden">
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteHipotek.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">

                                         <input name="Dram" value=<%=request.getParameter("Dram")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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
                                     <input name="PageToGo" value="Consumer" type="hidden">
                                     <span class="bold font-14">${counterCompare}</span>
                                       <i type="submit" class="icon-delete"
                                          onclick="document.DeleteConsumer.submit();"></i>
                                </li>
                                  <form action="CardClient" method="post" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                         <input name="Dram" value=<%=request.getParameter("Dram")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                        <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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
                                       <i type="submit" class="icon-delete" onclick="document.DeleteCar.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Dram" value=<%=request.getParameter("Dram")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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
                                <li>
                                    <c:forEach var="AgCompare" items="${requestScope.comparListAg}"
                                               varStatus="TheCount">
                                        <c:set var="counterAg" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span onclick="document.AgCompare.submit();">Գյուղատնտեսական</span>
                                      <span class="bold font-14">${counterAg}</span>
                                        <i type="submit" class="icon-delete" onclick="document.DeleteAG.submit();"></i>
                                </li>
                                 <form action="CardClient" method="post" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="Cards" type="hidden">
                                        <input name="Amount" value=<%=request.getAttribute("range")%> type="hidden">
                                       <input name="Dram" value=<%=request.getParameter("Dram")%> type="hidden">
                                        <input name="Ruble" value=<%=request.getParameter("Ruble")%> type="hidden">
                                        <input name="Dollar" value=<%=request.getParameter("Dollar")%> type="hidden">
                                        <input name="Euro" value=<%=request.getParameter("Euro")%> type="hidden">
                                        <input name="Cashback"
                                               value=<%=request.getParameter("Cashback")%> type="hidden">
                                        <input name="Depit" value=<%=request.getParameter("Depit")%> type="hidden">
                                        <input name="Free" value=<%=request.getParameter("Free")%> type="hidden">
                                        <input name="Credit" value=<%=request.getParameter("Credit")%> type="hidden">
                                       <input name="Period" value=<%=request.getParameter("Period")%> type="hidden">
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


    <div class="top-calculator top-search">
        <div class="bg" style="background-image: url('../images/7.jpg')">
            <div class="inner-container info-container">
                <div class="row-md clearfix margin-bottom-0">
                    <div class="text-box col-5">
                        <p class="font-32">Քարտեր</p>
                        <p>Մենք կօգնենք Ձեզ գտնել <br/>ամենաշահավետ տարբերակը</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="padding-bt-60 bg-white-dark">
        <div class="inner-container">
            <div class="card-types-top flex align-items-center flex-wrap">
                <form action="CardClient" method="post" name="DramTurnOff">
                    <input type="hidden" name="Dram" value="AMD">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="DramTurnOn">
                    <input type="hidden" name="Dram" value="OFF">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>

                <form action="CardClient" method="post" name="RubleTurnOn">
                    <input type="hidden" name="Ruble" value="RUB">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="RubleTurnOff">
                    <input type="hidden" name="Ruble" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>

                <form action="CardClient" method="post" name="DollarTurnOn">
                    <input type="hidden" name="Dollar" value="USD">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="DollarTurnOff">
                    <input type="hidden" name="Dollar" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>

                <form action="CardClient" method="post" name="EuroTurnOn">
                    <input type="hidden" name="Euro" value="EUR">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="EuroTurnOff">
                    <input type="hidden" name="Euro" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <div class="first">
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Dram").equals("AMD")%>' var="dram"/>
                        <c:choose>
                            <c:when test="${dram == 'ON' || dram == 'on'}">
                                <input type="checkbox" name="typeDram"/>
                                <span><i class="icon-dram" onclick="document.DramTurnOff.submit();"></i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeDram" checked/>
                                <span><i class="icon-dram" onclick="document.DramTurnOn.submit();"></i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>

                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Ruble").equals("RUB")%>' var="ruble"/>
                        <c:choose>
                            <c:when test="${ruble == 'ON' || ruble == 'on'}">
                                <input type="checkbox" name="typeRuble"/>
                                <span><i class="icon-ruble" onclick="document.RubleTurnOn.submit();"></i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeRuble" checked/>
                                <span><i class="icon-ruble" onclick="document.RubleTurnOff.submit();"></i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>

                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Dollar").equals("USD")%>' var="dollar"/>
                        <c:choose>
                            <c:when test="${dollar == 'ON' || dollar == 'on'}">
                                <input type="checkbox" name="typeDollar"/>
                                <span><i class="icon-dollar" onclick="document.DollarTurnOn.submit();"></i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeDollar" checked/>
                                <span><i class="icon-dollar" onclick="document.DollarTurnOff.submit();"></i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Euro").equals("EUR")%>' var="euro"/>
                        <c:choose>
                            <c:when test="${euro == 'ON' || euro == 'on'}">
                                <input type="checkbox" name="typeEuro"/>
                                <span><i class="icon-euro" onclick="document.EuroTurnOn.submit();"></i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeEuro" checked/>
                                <span><i class="icon-euro" onclick="document.EuroTurnOff.submit();"></i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                </div>
                <form action="CardClient" method="post" name="CashCashTurnOn">
                    <input type="hidden" name="Cashback" value="Cash">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="CashCashTurnOff">
                    <input type="hidden" name="Cashback" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">

                </form>

                <form action="CardClient" method="post" name="PeriodTurnOn">
                    <input type="hidden" name="Period" value="Periods">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="PeriodTurnOff">
                    <input type="hidden" name="Period" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>

                <form action="CardClient" method="post" name="FreeTurnOn">
                    <input type="hidden" name="Free" value="free">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>
                <form action="CardClient" method="post" name="FreeTurnOff">
                    <input type="hidden" name="Free" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                </form>


                <form action="CardClient" method="post" name="DepitTurnOn">
                    <input type="hidden" name="Depit" value="Depit">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                </form>
                <form action="CardClient" method="post" name="DepitTurnOff">
                    <input type="hidden" name="Depit" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Credit" value="<%=request.getParameter("Credit")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                </form>

                <form action="CardClient" method="post" name="CreditTurnOn">
                    <input type="hidden" name="Credit" value="crediting">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                </form>
                <form action="CardClient" method="post" name="CreditTurnOff">
                    <input type="hidden" name="Credit" value="OFF">
                    <input type="hidden" name="Dram" value="<%=request.getParameter("Dram")%>">
                    <input type="hidden" name="Ruble" value="<%=request.getParameter("Ruble")%>">
                    <input type="hidden" name="Dollar" value="<%=request.getParameter("Dollar")%>">
                    <input type="hidden" name="Euro" value="<%=request.getParameter("Euro")%>">
                    <input type="hidden" name="Cashback" value="<%=request.getParameter("Cashback")%>">
                    <input type="hidden" name="Period" value="<%=request.getParameter("Period")%>">
                    <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                    <input type="hidden" name="Depit" value="<%=request.getParameter("Depit")%>">
                    <input type="hidden" name="Free" value="<%=request.getParameter("Free")%>">
                </form>

                <div class="last">
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Cashback").equals("Cash")%>' var="CashCash"/>
                        <c:choose>
                            <c:when test="${CashCash == 'ON' || CashCash == 'on'}">
                                <input type="checkbox" name="typeChashBack"/>
                                <span><i onclick="document.CashCashTurnOn.submit();">Cashback</i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeChashBack" checked/>
                                <span><i onclick="document.CashCashTurnOff.submit();">Cashback</i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <%-- <label class="def-checkbox custom-checkbox">
                         <input type="checkbox" name="typeChashBack"/>
                         <span>Cashback</span>
                     </label>--%>
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Period").equals("Periods")%>' var="PeriodPeriod"/>
                        <c:choose>
                            <c:when test="${PeriodPeriod == 'ON' || PeriodPeriod == 'on'}">
                                <input type="checkbox" name="typePeriod"/>
                                <span><i onclick="document.PeriodTurnOn.submit();">Արտոնյալ ժամանակաշրջան</i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typePeriod" checked/>
                                <span><i onclick="document.PeriodTurnOff.submit();">Արտոնյալ ժամանակաշրջան</i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <%-- <label class="def-checkbox custom-checkbox">
                         <input type="checkbox" name="type"/>
                         <span>Արտոնյալ ժամանակաշրջան</span>
                     </label>--%>
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Free").equals("free")%>' var="freefree"/>
                        <c:choose>
                            <c:when test="${freefree == 'ON' || freefree == 'on'}">
                                <input type="checkbox" name="typeFree"/>
                                <span><i onclick="document.FreeTurnOn.submit();">Անվճար</i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeFree" checked/>
                                <span><i onclick="document.FreeTurnOff.submit();">Անվճար</i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <%--
                                        <label class="def-checkbox custom-checkbox">
                                            <input type="checkbox" name="type" />
                                            <span>Անվճար</span>
                                        </label>--%>
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Depit").equals("Depit")%>' var="DepitDepit"/>
                        <c:choose>
                            <c:when test="${DepitDepit == 'ON' || DepitDepit == 'on'}">
                                <input type="checkbox" name="typeDepit"/>
                                <span><i onclick="document.DepitTurnOn.submit();">Դեբիտ</i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeDepit" checked/>
                                <span><i onclick="document.DepitTurnOff.submit();">Դեբիտ</i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <%-- <label class="def-checkbox custom-checkbox">
                         <input type="checkbox" name="type"/>
                         <span>Դեբիտ</span>
                     </label>--%>
                    <label class="def-checkbox custom-checkbox">
                        <c:set value='<%=request.getParameter("Credit").equals("crediting")%>' var="CreditCredit"/>
                        <c:choose>
                            <c:when test="${CreditCredit == 'ON' || CreditCredit == 'on'}">
                                <input type="checkbox" name="typeCredit"/>
                                <span><i onclick="document.CreditTurnOn.submit();">Քրեդիտ</i></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="typeCredit" checked/>
                                <span><i onclick="document.CreditTurnOff.submit();">Քրեդիտ</i></span>
                            </c:otherwise>
                        </c:choose>
                    </label>

                    <%--<label class="def-checkbox custom-checkbox">
                        <input type="checkbox" name="type"/>
                        <span>Քրեդիտ</span>
                    </label>--%>
                </div>
            </div>


            <div class="cards-container">
                <c:if test="${requestScope.filterByCurancy != null}">
                    <c:forEach items="${requestScope.filterByCurancy}" var="cardList">
                        <div class="card-item-row">
                            <div class="item-title">
                                <p class="font-18 bold">${cardList.cardName}</p>
                            </div>
                            <div class="flex align-items-center flex-wrap">
                                <div class="col">
                                    <div class="item-img" style="background-image: url('${cardList.cardImage}')"></div>
                                </div>
                                <div class="item-info col">
                                    <ul>
                                        <li>
                                            <span>Սպասարկման վճար</span>
                                            <span class="bold"><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                                 value='${cardList.cardMaxServiceFee}'/></span>
                                        </li>
                                        <li>

                                    <span>
                                        <c:set var="CashBack" value="${cardList.cashback}"/>
                                        <c:choose>
                                            <c:when test="${CashBack!='0'}">
                                                 <a href="${cardList.PDF}" class="blue-link width-icon right-icon"
                                                    target="_blank">
                                            <span>Քեշ բեք</span><img src="../images/pdf.svg">
                                        </a>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>

                                    </span>
                                            <c:set var="CashBack" value="${cardList.cashback}"/>
                                            <c:choose>
                                                <c:when test="${CashBack != '0'}">
                                                    <span class="bold">${cardList.minCashBack}-${cardList.maxCashBack} %</span>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                        <c:set var="Credit" value="${cardList.crediting}"/>
                                        <c:choose>
                                            <c:when test="${Credit != '0'}">
                                                <li>
                                                    <span>Վարկային գիծ</span>
                                                    <span class="bold"><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                                         value='${cardList.cardMaxCreditLimit}'/></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>

                                        <li>
                                            <span>Տոկոս</span>
                                            <span class="bold">${cardList.cardPerCreditLimit} %</span>
                                        </li>

                                        <c:set var="Timering" value="${cardList.timer}"/>
                                        <c:choose>
                                            <c:when test="${Timering != '0'}">
                                                <li>
                                                    <span>Արտոնյալ ժամանակաշրջան</span>
                                                    <span class="bold">${cardList.cardGracePeriod} օր</span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                        <li>
                                            <c:set var="credits" value="${cardList.crediting}"/>
                                            <c:choose>
                                                <c:when test="${credits != '0'}">
                                                    <span>Տոկոսը դրական մնացորդի վրա</span>
                                                    <span class="bold">${cardList.cardPerFactual} %</span>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                </div>
                                <div class="item-desc col">
                                    <p>${cardList.cardInfo}</p>
                                    <c:set var="CashBack" value="${cardList.cashback}"/>
                                    <c:choose>
                                        <c:when test="${CashBack !='0' }">
                                            <p><a href="${cardList.PDFSale}"
                                                  class="blue-link width-icon right-icon" target="_blank"><span>Զեղչ խանութներում</span>
                                                <img src="../images/pdf.svg"></a></p>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="flex-item col">
                                    <c:set value="${cardList.lastLink}" var="link"/>
                                    <c:choose>
                                        <c:when test="${link != '0'}">
                                            <p><a href="${cardList.bankLink}"
                                                  class="def-button btn-green with-shadow  margin-bottom-15" target="_blank">Անցնել
                                                էջ</a>
                                               </p>
                                        </c:when>
                                        <c:otherwise>
                                            <button onclick="createPopupWin('SendEmailToBank.jsp?ProductCode=${cardList.productCode}', 'oferta.am', 800, 650);"
                                                    class="def-button btn-green with-shadow  margin-bottom-15">Ուղարկել
                                                հայտ
                                            </button>
                                            <input type="hidden" name="BankId" value="${cardList.bankId}"/>
                                            <input type="hidden" name="productCode" value="${cardList.productCode}"/>
                                        </c:otherwise>
                                        <%-- <form action="<%=request.getContextPath()%>/SendEmailForCards" method="post">--%>

                                        <%-- </form>--%>
                                        <%-- <p><a href="CreditSend?PageToGo=Cards&&ProductCode=${cardList.productCode}"
                                               class="green-link font-12">Իմանալ
                                             ավելին</a></p>--%>
                                    </c:choose>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </c:if>


            </div>
            <div class="see-all flex align-items-center justify-center">
    <span class="green-link width-icon right-icon" id="loadMore">
    <span>Տեսնել ավելին</span>
    <i class="icon-arrow-down font-9"></i>
    </span>
            </div>
        </div>
    </div>

   <%-- <jsp:include page="include/Offers.jsp"/>--%>
    <jsp:include page="include/Footer.jsp"/>

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
    <script src="js/modal.js"></script>
    <script src="libs/owl/owl.carousel.min.js"></script>
    <script src="js/carousel.js"></script>


</body>
</html>

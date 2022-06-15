<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="include/google.jsp"/><meta charset="UTF-8">
    <meta property="og:title" content="Oferta.am">
    <meta property="og:type" content="website" />
    <meta property="og:image" content="http://oferta.am/images/oferta1.jpg">
    <meta property="og:description" content="Բոլոր բանկային առաջարկները մեկ հարթակում"><meta property="og:url" content="http://oferta.am">
    <meta name="twitter:card" content="summary_large_image">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oferta.am</title>
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/DriopDownMain/dropdownstyle.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.css"/>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>

</head>
<body id="body">
<%--<fmt:setLocale value="${requestScope.Pagelanguage}"/>

<fmt:setBundle basename="com.ithome.web.Resources.Content" var="Bundles"/>--%>
<div class="overlay" id="overlay"></div>
<div class="overlay-box" id="overlay-box"></div>
<div class="page-container">
    <div class="fixed-header">
        <header>
            <div class="inner-container large">
                <div class="flex space-between">
                    <div class="left flex align-items-center">
              <span class="logo">
                   <a href="App?PageLanguage=${requestScope.Pagelanguage}"><img src="${requestScope.getContextPath}/images/oferta.png" alt=""/></a>
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
                                class="icon-state"></i>${requestScope.City}  </span>
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

                    <%--  <%
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
                                <li onclick="document.DepositCompare.submit();">
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span>Ավանդ</span>
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
                                     <span>Հիփոթեք</span>
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i class="icon-delete"></i>
                                </li>
                            </ul>
                            </c:if>
                            <form action="CompareConsumer" method="post" name="ConsumerCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                             <c:if test="${requestScope.comparListConsumer != null}">
                            <ul>
                                <li onclick="document.ConsumerCompare.submit();">
                                    <c:forEach var="ConsumerCompare" items="${requestScope.comparListConsumer}"
                                               varStatus="TheCount">
                                        <c:set var="counterCompare" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span>Սպառողական</span>
                                     <span class="bold font-14">${counterCompare}</span>
                                        <i class="icon-delete"></i>
                                </li>
                            </ul>
                             </c:if>
                            <form action="CompareCarLoan" method="post" name="CarLoanCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListCarLoan != null}">
                            <ul>
                                <li onclick="document.CarLoanCompare.submit();">
                                    <c:forEach var="CarLoanCompare" items="${requestScope.comparListCarLoan}"
                                               varStatus="TheCount">
                                        <c:set var="counterCarLoan" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                    <span>Ավտովարկ</span>
                                      <span class="bold font-14">${counterCarLoan}</span>
                                        <i class="icon-delete"></i>
                                </li>
                            </ul>
                            </c:if>
                          <%--  <form action="CompareMicro" method="post" name="MicroCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListMicro != null}">
                            <ul>
                                <li onclick="document.MicroCompare.submit();">
                                    <c:forEach var="MicroCompare" items="${requestScope.comparListMicro}"
                                               varStatus="TheCount">
                                        <c:set var="counterMicro" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                    <span>Միկրովարկ</span>
                                     <span class="bold font-14">${counterMicro}</span>
                                        <i class="icon-delete"></i>
                                </li>
                            </ul>
                            </c:if>--%>
                            <form action="CompareAg" method="post" name="AgCompare">
                                <input type="hidden" name="Currancy" value="<%=request.getParameter("Currancy")%>">
                            </form>
                            <c:if test="${requestScope.comparListAg != null}">
                            <ul>
                                <li onclick="document.AgCompare.submit();">
                                    <c:forEach var="AgCompare" items="${requestScope.comparListAg}"
                                               varStatus="TheCount">
                                        <c:set var="counterAg" value="${TheCount.count}" scope="request"/>
                                    </c:forEach>
                                     <span>Գյուղատնտեսական</span>
                                      <span class="bold font-14">${counterAg}</span>
                                        <i class="icon-delete"></i>
                                </li>
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

    <div class="padding-bt-60 bg-white-dark">
        <div class="inner-container">
            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Բանկեր</h3>
                    <br>
                </div>

                <c:if test="${requestScope.BSearchResult != null}">
                    <c:forEach items="${requestScope.BSearchResult}" var="BankSearchResult">
                        <div class="table">
                            <div class="table-row">
                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${BankSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>ՀասցԷ</p>
                                    <p class="font-18 bold">${BankSearchResult.bankAddress}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Հեռախոս</p>
                                    <p class="font-18 bold">${BankSearchResult.bankPhoneNumber}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Գրասենյակ</p>
                                    <p class="font-18 bold">${BankSearchResult.bankHQ}</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a target="_blank" href="http://${BankSearchResult.bankWebSite}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Քարդեր</h3>
                    <br>
                </div>
                <c:if test="${requestScope.CardsSearchResult != null}">
                    <c:forEach items="${requestScope.CardsSearchResult}" var="CardSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${CardSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Քարդի անվանում</p>
                                    <p class="font-18 bold">${CardSearchResult.cardName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Քարդի տեսակ</p>
                                    <p class="font-18 bold">${CardSearchResult.cardType}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ծարայության մինիմալ վճառ</p>
                                    <p class="font-18 bold">${CardSearchResult.cardMinServiceFee}</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardid=${CardSearchResult.cardId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Գյուղանտնտեսական</h3>
                    <br>
                </div>
                <c:if test="${requestScope.AGSearchResult != null}">
                    <c:forEach items="${requestScope.AGSearchResult}" var="AGSearchResult">
                        <div class="table">
                            <div class="table-row">
                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${AGSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Անվանում</p>
                                    <p class="font-18 bold">${AGSearchResult.ProductName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${AGSearchResult.ACMinLoan}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Առավելագույն գումարը</p>
                                    <p class="font-18 bold">${AGSearchResult.ACMinLoan}</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardid=${AGSearchResult.cardId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Ավտո վարկ</h3>
                    <br>
                </div>
                <c:if test="${requestScope.carLoansList != null}">
                    <c:forEach items="${requestScope.carLoansList}" var="CarLoanSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${CarLoanSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>անվանում</p>
                                    <p class="font-18 bold">${CarLoanSearchResult.productName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${CarLoanSearchResult.CLMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ծարայության մինիմալ վճառ</p>
                                    <p class="font-18 bold">${CarLoanSearchResult.CLMinServiceFee}</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardLoanid=${CarLoanSearchResult.CLId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Սպառողական վարկ</h3>
                    <br>
                </div>
                <c:if test="${requestScope.consumerCreditList != null}">
                    <c:forEach items="${requestScope.consumerCreditList}" var="ConsumerSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${ConsumerSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>անվանում</p>
                                    <p class="font-18 bold">${ConsumerSearchResult.productName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${ConsumerSearchResult.CCMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ծարայության մինիմալ ժամկետ </p>
                                    <p class="font-18 bold">${ConsumerSearchResult.CCMinPeriodMonth} Ամիս</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardLoanid=${ConsumerSearchResult.CLId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Ավանդ</h3>
                    <br>
                </div>
                <c:if test="${requestScope.depositList != null}">
                    <c:forEach items="${requestScope.depositList}" var="DeposiSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${DeposiSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>անվանում</p>
                                    <p class="font-18 bold">${DeposiSearchResult.productName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${DeposiSearchResult.DMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Տոկոսադրույք </p>
                                    <p class="font-18 bold">${DeposiSearchResult.DPercentage}</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardLoanid=${DeposiSearchResult.DId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Միկրո վարկ</h3>
                    <br>
                </div>
                <c:if test="${requestScope.microLoansList != null}">
                    <c:forEach items="${requestScope.microLoansList}" var="MicroSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${MicroSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>անվանում</p>
                                    <p class="font-18 bold">${MicroSearchResult.productName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${MicroSearchResult.MLMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ծարայության մինիմալ ժամկետ </p>
                                    <p class="font-18 bold">${MicroSearchResult.MMinPeriodDays} Օր</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="#?cardLoanid=${MicroSearchResult.MLId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <div class="head">
                    <h3>Հիպոթեկային վարկ</h3>
                    <br>
                </div>
                <c:if test="${requestScope.mortgageList != null}">
                    <c:forEach items="${requestScope.mortgageList}" var="MortgageSearchResult">
                        <div class="table">
                            <div class="table-row">

                                <div class="table-cell">
                                    <p>Բանկ</p>
                                    <p class="font-18 bold">${MortgageSearchResult.bankName}</p>
                                </div>
                                <div class="table-cell">
                                    <p>անվանում</p>
                                    <p class="font-18 bold">${MortgageSearchResult.productName}</p>

                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումար</p>
                                    <p class="font-18 bold">${MortgageSearchResult.MMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ծարայության մինիմալ ժամկետ </p>
                                    <p class="font-18 bold">${MortgageSearchResult.MMinPeriodMonth} Ամիս</p>
                                </div>
                                <div class="table-cell text-center">
                                    <a href="MortgageClient?cardLoanid=${MortgageSearchResult.MId}Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>


        </div>
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
                    <span class="margin-right-15 hide-for-mb">Միացեք մեզ սոցիալական ցանցերում</span> <span>
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
    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/js/search.js"></script>
    <script src="<%=request.getContextPath()%>/js/range.js"></script>
    <script src="<%=request.getContextPath()%>/js/modal.js"></script>
    <script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>


</body>
</html>


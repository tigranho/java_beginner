<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
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
                      <%--  <span class="compere-box hide-for-tablet relative">
                     <span class="compere-icon">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>
                    </span>--%>
                    <%--<div class="tooltip-container bottom right" id="compareTooltipMb">
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

                    <%-- <%
                         if (request.getAttribute("WorningMessage") != null) {
                     %>
                     <%=request.getAttribute("WorningMessage")%>
                     <%
                         }
                     %>--%>
                    <span class="right hide-for-tablet">

                <span><i class="icon-search" id="searchBtn"></i></span>
                <span class="compere-box">

                    <span class="compere-icon" >
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
                                 <form action="Ofbanks" method="post" name="Delete">
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
                                 <form action="Ofbanks" method="post" name="DeleteHipotek">
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
                                  <form action="Ofbanks" method="post" name="DeleteConsumer">
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
                                 <form action="Ofbanks" method="post" name="DeleteCar">
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
                                 <form action="Ofbanks" method="post" name="DeleteAG">
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
            <div class="row-md clearfix">
                <div class="col-9 left-side">
                    <%--<div class="top-calculator top-search bg-blue margin-bottom-30">
                        <div class="inner-container info-container">
                            <div class="row-md clearfix">
                                &lt;%&ndash;<form action="searchBanks" method="post">
                                    <div class="col-2 small col">
                                        <span class="label">Քաղաք</span>
                                        &lt;%&ndash;<div class="def-select-box">
                                            <c:set var="languages" value="${requestScope.Pagelanguage}"/>
                                            <c:choose>
                                                <c:when test="${languages == 'en_US'}">
                                                    <select name="city">
                                                        <option value="0">Yerevan</option>
                                                        <option value="1">Gyumri</option>
                                                        <option value="2">Vanadzor</option>
                                                        <option value="3">Vagharshapat</option>
                                                        <option value="4">Abovyan</option>
                                                        <option value="5">Kapan</option>
                                                        <option value="6">Hrazdan</option>
                                                        <option value="7">Armavir</option>
                                                        <option value="8">Dilijan</option>
                                                        <option value="9">Artashat</option>
                                                        <option value="10">Ijevan</option>
                                                        <option value="11">Sevan</option>
                                                        <option value="12">Masis</option>
                                                        <option value="13">Ararat</option>
                                                        <option value="14">Goris</option>
                                                        <option value="15">Gavar</option>
                                                        <option value="16">Ashtarak</option>
                                                    </select>
                                                </c:when>
                                                <c:when test="${languages == 'hy_AM'}">
                                                    <select name="city">
                                                        <option value="0">Երևան</option>
                                                        <option value="1">Գյումրի</option>
                                                        <option value="2">Վանաձոր</option>
                                                        <option value="3">Վաղարշապատ</option>
                                                        <option value="4">Աբովյան</option>
                                                        <option value="5">Կապան</option>
                                                        <option value="6">Հրազդան</option>
                                                        <option value="7">Արմավիր</option>
                                                        <option value="8">Դիլիջան</option>
                                                        <option value="9">Արտաշատ</option>
                                                        <option value="10">Իջևան</option>
                                                        <option value="11">Սևան</option>
                                                        <option value="12">Մասիս</option>
                                                        <option value="13">Արարատ</option>
                                                        <option value="14">Գորիս</option>
                                                        <option value="15">Գավառ</option>
                                                        <option value="16">Աշտարակ</option>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <select name="city">
                                                        <option value="0">Ереван</option>
                                                        <option value="1">Гюмри</option>
                                                        <option value="2">Ванадзор</option>
                                                        <option value="3">Вагаршапат</option>
                                                        <option value="4">Абовян</option>
                                                        <option value="5">Капан</option>
                                                        <option value="6">Раздан</option>
                                                        <option value="7">Армавир</option>
                                                        <option value="8">Дилижан</option>
                                                        <option value="9">Арташат</option>
                                                        <option value="10">Иджеван</option>
                                                        <option value="11">Севан</option>
                                                        <option value="12">Масис</option>
                                                        <option value="13">Арарат</option>
                                                        <option value="14">Горис</option>
                                                        <option value="15">Гавар</option>
                                                        <option value="16">Аштарак</option>
                                                    </select>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>&ndash;%&gt;
                                    </div>
                                    &lt;%&ndash;<div class="col-4 large col">
                                        <span class="label">Կազմակերպություն</span>
                                        <div class="def-select-box">
                                            <c:set var="languages" value="${requestScope.Pagelanguage}"/>
                                            <c:choose>
                                                <c:when test="${languages == 'en_US'}">
                                                    <select name="Company">
                                                        <option value="Բանկեր">Banks</option>
                                                    </select>
                                                </c:when>
                                                <c:when test="${languages == 'hy_AM'}">
                                                    <select name="Company">
                                                        <option value="Բանկեր">Բանկեր</option>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <select name="Company">
                                                        <option value="Բանկեր">банки</option>
                                                    </select>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>&ndash;%&gt;
                                    <div class="col-4 large col">
                                        <span class="label">Փնտրել</span>
                                        <div class="def-input">
                                            <input type="text" placeholder="<fmt:message key="Enter bank name ..." bundle="${Bundles}"/>" required/>
                                        </div>
                                    </div>
                                    <div class="col-2 small col">
                                        <button type="submit" class="def-button btn-green">
                                            <span><fmt:message  key="Search" bundle="${Bundles}"/></span>
                                        </button>
                                    </div>
                                    <input type="hidden" name="Currancy" value="${requestScope.PageCurrancy}">
                                    <input type="hidden" name="Pagelanguage" value="${requestScope.Pagelanguage}">
                                </form>&ndash;%&gt;
                            </div>

                        </div>
                    </div>--%>

                    <!-- /////////////////////////////////////////////////////////////////////////////////////////Bank section Start -->
                    <div class="banks-table">
                        <div class="item-row flex head align-items-center">
                            <div class="flex-item"><span>Բանկերը</span></div>
                            <div class="flex-item"><span>Հեռախոսներ եւ հասցեներ</span></div>
                            <div class="flex-item"><span>Աշխատանքային ժամերը</span></div>
                        </div>
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="banks">
                                <a href="AllBankServices?bankid=${banks.bankId}&&Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"
                                   class="item-row flex align-items-center">
                            <span class="flex-item">
                                <div class="flex align-items-center flex-wrap">
                                    <div class="item-img ">
                                        <img style="width: 50px" src="${banks.bankBigLogo}"/>
                                    </div>
                                    <div class="item-info">
                                        <p class="font-16 bold margin-bottom-15">${banks.bankName}</p>
                                        <p class="green">Բաժանմունքներ: ${banks.bankBranches}</p>
                                        <p class="green">Բանկոմատ: ${banks.bankATMNumbers}</p>
                                    </div>
                                </div>
                            </span>
                                    <span class="flex-item">
                                <p class="green">${banks.bankAddress}</p>
                                <p>${banks.bankPhoneNumber}</p>
                            </span>
                                    <span class="flex-item">
                                <p>Երկ - Շաբ: 09:00-20:00</p>
                                <p>Կիր: Փակ է</p>
                            </span>
                                </a>
                            </c:forEach>
                        </c:if>
                        <!-- /////////////////////////////////////////////////////////////////////////////////////////Bank section END -->

                    </div>
                    <%-- <div class="see-all flex align-items-center justify-center">
                     <span class="green-link width-icon right-icon">
                         <span>Տեսնել ավելին</span>
                         <i class="icon-arrow-down font-9"></i>
                     </span>
                     </div>--%>
                </div>
                <div class="col-3 right-side hide-for-tablet">
                    <div class="banner-box no-bg">
                        <img src="../images/us-loan-debt.png" alt=""/>
                        <div class="inner">
                            <div class="text-box">
                                <p class="font-32 margin-bottom-10 line-height-44">Վարկերի օնլայն հարթակ</p>
                                <p>Այստեղ Դուք կարող եք գտնել բանկերի և վարկային կազմակերպությունների բոլոր առաջարկները, որոնք կարող եք համեմատել և ընտրել լավագույն առաջարկը</p>
                                <button class="def-button btn-green max-width-110 margin-top-20">
                                    <span>Հաշվել</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="banner-box no-bg">
                        <img src="../images/Inecobank_Webpage_Cards.png" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="padding-bt-60">
        <div class="inner-container">
            <p class="font-24 title margin-bottom-35">Հատուկ առաջարկներ</p>
            <div class="tab-container">
                <div class="tab-nav flex align-items-center flex-wrap">
                    <span class="tab-link" onclick="openTabItem(event, 'deposit')" id="defaultOpen">Ավանդներ </span>
                    <span class="tab-link" onclick="openTabItem(event, 'hyperpox')">Հիփոթեք</span>
                    <span class="tab-link" onclick="openTabItem(event, 'consumer-loan')">Սպառողական վարկեր</span>
                    <span class="tab-link" onclick="openTabItem(event, 'car-loan')">Ավտովարկ</span>
                    <span class="tab-link" onclick="openTabItem(event, 'agricultural')">Գյուղատնտեսական վարկ</span>
                    <span class="tab-link" onclick="openTabItem(event, 'card')">Քարտեր</span>
                </div>


                <!-- ############################################################ Deposit START -->
                <div class="tab-content" id="deposit">
                    <div class="top">
                        <a href="DepositClient?PageToGo=Deposits&&Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}" class="green-link width-icon ellipsis width-percent-70 block">
                            <i class="icon-grid"></i>
                            <span>Ավանդների բոլոր առաջարկները</span>
                        </a>
                    </div>
                    <div>
                        <div class="simple-carousel margin-top-30">
                            <div class="owl-carousel owl-theme" id="simpleCarousel">
                                <c:if test="${requestScope.depositListOffer!=null}">
                                    <c:forEach items="${requestScope.depositListOffer}" var="SpecialDeposit">
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
                                                        int convertMonths = getMonths / 30;
                                                    %>
                                                    <span class="bold font-20">${SpecialDeposit.timeLine} օր | <%=convertMonths%> Ամիս</span>
                                                    <span>Ավանդի ժամկետը</span>
                                                </div>
                                            </div>
                                            <div>
                                                <a class="def-button btn-blue" href="">Իմանալ ավելին</a>
                                            </div>
                                            <div class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                                <div class="item-img">
                                                    <img src="${SpecialDeposit.banksList}"/>
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
                                        <%
                                            mortgageMinAmount = Integer.parseInt(String.valueOf(request.getAttribute("minAmountMort")));
                                            mortgageMaxAmount = Integer.parseInt(String.valueOf(request.getAttribute("maxAmountMort")));
                                            convertMonthsMortgage = Integer.parseInt(String.valueOf(request.getAttribute("maxMonthMort")));
                                        %>
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
                        <a href="ConsumerClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}" class="green-link width-icon"> <i
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
                                                <a class="def-button btn-blue" href="">Իմանալ ավելին</a>
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
                        <a href="AutoClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}" class="green-link width-icon"> <i
                                class="icon-grid"></i> <span>Ավտոմեքենայի վարկի բոլոր առաջարկները</span>
                        </a>
                    </div>
                    <div>
                        <div class="simple-carousel margin-top-30">
                            <div class="owl-carousel owl-theme" id="simpleCarousel4">
                                <c:if test="${requestScope.carLoansListoffer!=null}">
                                    <c:forEach items="${requestScope.carLoansListoffer}"
                                               var="SpecialcarLoansListoffer">
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
                                                <a class="def-button btn-blue" href="">Իմանալ ավելին</a>
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
                        <a href="AClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}" class="green-link width-icon"> <i
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
                                                <a class="def-button btn-blue" href="">Իմանալ ավելին</a>
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
                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=Cash&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=OFF" class="green-link width-icon"> <i
                                class="icon-grid"></i> <span>Բոլոր Քարտեր առաջարկները</span>
                        </a>
                    </div>
                    <div>
                        <div class="simple-carousel margin-top-30">
                            <div class="owl-carousel owl-theme" id="simpleCarousel6">
                                <c:if test="${requestScope.cardsListOffer!=null}">
                                    <c:forEach items="${requestScope.cardsListOffer}"
                                               var="SpecialcardsListOffer">
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
                                                <a class="def-button btn-blue" href="">Իմանալ ավելին</a>
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
    <footer>
        <div class="inner-container large">
            <div class="top flex space-between">
                <div class="flex align-items-center">
					<span class="logo"> <img src="${requestScope.getContextPath}/images/oferta.png" alt=""/>
					</span>
                </div>
                <div class="flex align-items-center">
                    <span class="margin-right-15 hide-for-mb">Միացեք մեզ սոցիալական ցանցերում</span> <span>
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
    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/js/search.js"></script>
    <script src="<%=request.getContextPath()%>/js/range.js"></script>
    <script src="<%=request.getContextPath()%>/js/modal.js"></script>
    <script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/carousel.js"></script>


</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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

<%--    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/Compare.js"></script>--%>

</head>
<body id="body">
<%--<fmt:setLocale value="${requestScope.Pagelanguage}"/>

<fmt:setBundle basename="com.ithome.web.Resources.Content" var="Bundles"/>--%>
<div class="overlay" id="overlay"></div>
<div class="page-container">
    <div class="fixed-header">
        <header>
            <div class="inner-container large">
                <div class="flex space-between">
                    <div class="left flex align-items-center">
                        <span class="logo"> <a href="App"><img src="${requestScope.getContextPath}/images/oferta.png"
                                                               alt=""/></a>
							</span> <span class="hide-for-tablet">
								<ul>
									<li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a
                                            href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
								<li><li><a
                                        href="Ofbanks?Currancy=${requestScope.PageCurrancy}&&City=<%=request.getAttribute("city")%>">Մեր Գործընկերները</a></li>
									<%--<li><a href="#">Մեր Գործընկերները</a></li>--%>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
								</ul>
							</span>
                    </div>
                    <div class="right flex align-items-center">
                        <span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i> ${requestScope.City}   </span>
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
                     <span class="compere-icon" onclick="toggleBoxes('compareTooltipMb')">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>--%>
                    </span>
                        <%-- <div class="tooltip-container bottom right" id="compareTooltipMb">
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
                        <c:if test="${requestScope.comparListCarLoan != null}">
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
                                 <form action="AllBankServices" method="post" name="Delete">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="App" type="hidden">

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
                                 <form action="AllBankServices" method="post" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="App" type="hidden">

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
                                  <form action="AllBankServices" method="post" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="App" type="hidden">

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
                                 <form action="AllBankServices" method="post" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="App" type="hidden">

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
                                 <form action="AllBankServices" method="post" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="App" type="hidden">

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

    <c:if test="${requestScope.banksList != null}">
        <c:forEach items="${requestScope.banksList}" var="bankDetail">
            <div class="bg-white-dark padding-bottom-60 bank-item-page">
                <div class="inner-container">
                    <div class="bank-header">
                        <div class="bg" style="background-image: url('../../../images/10.svg')">
                            <div class="top">
                                <div class="item-logo">

                                    <a href="">
                                        <img src="${bankDetail.bankBigLogo}"
                                             style="width: 100px;" alt="">
                                    </a>

                                </div>

                                <p class="font-36"> ${bankDetail.bankName}</p>
                            </div>
                        </div>
                    </div>
                    <div class="loan-types">
                        <div class="flex flex-wrap align-items-stretch">
                            <div class="block-container item">
                                <a href="DepositBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/1.svg"/></span>
                                    <span>Ավանդներ</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="MortgageBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/2.svg"/></span>
                                    <span>Հիփոթեք</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="ConsumerBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/3.svg"/></span>
                                    <span>Սպառողական վարկեր</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="AutoBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/4.svg"/></span>
                                    <span>Ավտովարկ</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="MicroBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/5.svg"/></span>
                                    <span>Միկրովարկ</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="AgBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/6.svg"/></span>
                                    <span>Գյուղատնտեսական վարկ</span>
                                </a>
                            </div>
                            <div class="block-container item">
                                <a href="CardBanks?Currancy=${requestScope.PageCurrancy}&&bankId=${bankDetail.bankId}">
                                    <span class="icon"><img src="../images/icons/7.svg"/></span>
                                    <span>Քարտեր</span>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="about-bank padding-top-60">
                        <p class="title font-24">Բանկի մասին</p>
                        <p class="item-desc"> ${bankDetail.bankName}</p>
                        <div class="bank-info row-md row-flex flex flex-wrap">
                            <div class="col-6">
                                <p class="blue-dark">Իրավաբանական հասցե։</p>
                                <p class="font-16 bold">${bankDetail.bankAddress}</p>
                            </div>
                            <div class="col-3">
                                <p class="blue-dark">Էլ. հասցե</p>
                                <p class="font-16 bold">${bankDetail.bankEmailAddress}</p>
                            </div>
                            <div class="col-3">
                                <p class="blue-dark">Աշխ. ժամերը</p>
                                <p class="font-16 bold">Երկ - Շաբ: 09:00-20:00 Կիր: Փակ է</p>
                            </div>
                            <div class="col-6">
                                <p class="blue-dark">Հեռախոսահամար</p>
                                <p class="font-16 bold">${bankDetail.bankPhoneNumber}</p>
                            </div>
                            <div class="col-3">
                                <p class="blue-dark">Կայք</p>
                                <p class="font-16 bold">${bankDetail.bankWebSite}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="padding-bt-60">
                <div class="inner-container bank-branches">
                    <div class="flex space-between align-items-center flex-wrap">
                        <p class="font-24 title">Մասնաճյուղեր և բանկոմատներ <a href=""
                                                                               class="green-link">Երևանում</a></p>
                        <div class="right-part">
                            <a href="" class="def-button btn-outline">${bankDetail.bankHQ}</a>
                            <a href="" class="def-button btn-outline">${bankDetail.bankBranches}</a>
                        </div>
                    </div>
                    <div class="map">

                            <%--<iframe src="https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d24380.81480803617!2d44.48420823987601!3d40.195673287729065!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sInecobank%2C!5e0!3m2!1sru!2s!4v1557868287819!5m2!1sru!2s"
                                    frameborder="0" style="border:0" allowfullscreen></iframe>--%>

                        <c:set value="${bankDetail.bankName}" var="bankName"/>
                        <c:choose>
                            <c:when test="${bankName eq 'AMERIABANK'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3048.499186105274!2d44.50756241564362!3d40.17570297818636!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abc57a46ad91b%3A0x15dd5a253c1b4e3f!2z1LHVtNWl1oDVq9Wh1aLVodW21a8gfCBBbWVyaWFiYW5r!5e0!3m2!1sen!2s!4v1569648791284!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;"
                                        allowfullscreen=""></iframe>
                            </c:when>
                            <c:when test="${bankName eq 'HSBC Bank Armenia cjsc'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3047.9447869054447!2d44.518739215643876!3d40.18804217743944!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abce0612cc619%3A0x20a359da7b3dde40!2sHSBC%20Bank%20Head%20Office!5e0!3m2!1sen!2s!4v1569648739000!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;"
                                        allowfullscreen=""></iframe>
                            </c:when>
                            <c:when test="${bankName eq 'ՎՏԲ Բանկ Հայաստան'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1523.9746746068126!2d44.5165572577249!3d40.18794064750932!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abd1e2afde517%3A0x47700db9569b7305!2sAmeriabank%20CJSC%20-%20Moskovyan%20Branch!5e0!3m2!1sen!2s!4v1569620826568!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;"
                                        allowfullscreen=""></iframe>
                            </c:when>
                            <c:when test="${bankName eq 'ACBA-CREDIT AGRICOLE BANK'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3048.1268450289726!2d44.50533571564377!3d40.18399047768475!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abde7a80b5e67%3A0x479d7ba42dc522fb!2sACBA-CREDIT%20AGRICOLE%20BANK!5e0!3m2!1sen!2s!4v1569648874121!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;"
                                        allowfullscreen=""></iframe>
                            </c:when>
                            <c:when test="${bankName  eq 'Byblos Bank Armenia'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3048.243143529614!2d44.50514461582832!3d40.18140207939317!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcfdc62f037d%3A0xc9fd80486ff46df1!2sByblos%20Bank!5e0!3m2!1sen!2s!4v1586631058838!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>
                            <c:when test="${bankName  eq 'INECOBANK'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12192.972637076746!2d44.49857854392913!3d40.181401729075915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abce4e919068d%3A0x58722000679e9488!2sInecobank%20CJSC!5e0!3m2!1sen!2s!4v1586631120862!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>

                            <c:when test="${bankName  eq 'ConverseBank'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12192.972755586483!2d44.49857854285864!3d40.181401069654825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb2c7b71cf8f999cc!2sConverse%20Bank!5e0!3m2!1sen!2s!4v1586631167110!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>

                            <c:when test="${bankName  eq 'EVOCABANK'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48788.2668498227!2d44.49933703286577!3d40.15861580050708!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcf0c9703ad1%3A0x1e1290b816cbfa39!2sEVOCABANK%20Head%20Office!5e0!3m2!1sen!2s!4v1588103778614!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>

                            <c:when test="${bankName  eq 'EVOCABANK'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48788.2668498227!2d44.49933703286577!3d40.15861580050708!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcf0c9703ad1%3A0x1e1290b816cbfa39!2sEVOCABANK%20Head%20Office!5e0!3m2!1sen!2s!4v1588103778614!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>
                            <c:when test="${bankName  eq 'ՀԱՅԲԻԶՆԵՍԲԱՆԿ'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d48788.274429981204!2d44.499337015731534!3d40.15860525101485!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcfaedc76c81%3A0x457abc022e31e3f!2sArmBusinessBank!5e0!3m2!1sen!2s!4v1588103884500!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>
                            <c:when test="${bankName  eq 'ՀԱՅԷԿՈՆՈՄԲԱՆԿ'}">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5126.745998487368!2d44.50840215489949!3d40.178324590559754!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcfde8bbd181%3A0x8716869a49e037fd!2sArmeconombank%20Head%20Office!5e0!3m2!1sen!2s!4v1588104787404!5m2!1sen!2s"
                                        width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""
                                        aria-hidden="false" tabindex="0"></iframe>
                            </c:when>




                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>

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


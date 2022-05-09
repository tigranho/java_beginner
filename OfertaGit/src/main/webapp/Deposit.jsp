<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta property="og:title" content="Oferta.am">
    <meta property="og:type" content="website" />
    <meta property="og:image" content="http://oferta.am/images/oferta1.jpg">
    <meta property="og:description" content="Բոլոր բանկային առաջարկները մեկ հարթակում"><meta property="og:url" content="http://oferta.am">
    <meta name="twitter:card" content="summary_large_image">
    <jsp:include page="include/google.jsp"/><meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Varking.am</title>
        <link href="${requestScope.getContextPath}images/favicon.png" rel="shortcut icon">

        <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
        <link rel="stylesheet" href="css/global.css" type="text/css">
        <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
       <link rel="stylesheet" href="css/style.css">
        <style>
            .accordion {
                background-color: #eee;

                width: 100%;
                border: none;
                text-align: left;
                outline: none;

                transition: 0.4s;
            }

            .active, .accordion:hover {
                background-color: #ccc;
            }

            .panel {

                display: none;
                background-color: white;
                overflow: hidden;
            }
        </style>
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
               <span class="logo">
                   <a href="App?PageLanguage=${requestScope.Pagelanguage}"><img src="../images/logo.svg" alt=""/></a>
               </span>
                        <span class="hide-for-tablet">
                   <ul>
                      <li><a href="About?Currancy=${requestScope.PageCurrancy}">Պայմաններ</a></li>
									<li><a href="ContactUs?Currancy=${requestScope.PageCurrancy}">Կապ մեզ հետ</a></li>
									<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}">Մեր Գործընկերները</a></li>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
                   </ul>
               </span>
                    </div>
                    <div class="right flex align-items-center">
                       	<span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i> <%=request.getAttribute("city")%> </span>
                        <span class="language-box">
                            <div class="dropdown">
  <button class="dropbtn">${requestScope.pageLanguageName}</button>
  <div class="dropdown-content">
    <a href="<%=request.getAttribute("PageName")%>?Pagelanguage=hy_AM&&Amount=${requestScope.minAmount}&&MaxAmounr=${requestScope.maxAmount}&&PageToGo=Deposits">
        <fmt:message key="Armenian" bundle="${Bundles}"/></a>
   <a href="<%=request.getAttribute("PageName")%>?Pagelanguage=en_US&&Amount=${requestScope.minAmount}&&MaxAmounr=${requestScope.maxAmount}&&PageToGo=Deposits">
       <fmt:message key="English" bundle="${Bundles}"/></a>
   <a href="<%=request.getAttribute("PageName")%>?Pagelanguage=ru_RU&&Amount=${requestScope.minAmount}&&MaxAmounr=${requestScope.maxAmount}&&PageToGo=Deposits">
       <fmt:message key="Russian" bundle="${Bundles}"/></a>
  </div>
</div>
                </span>
                        <span class="compere-box show-for-tablet relative">
                     <span class="compere-icon" onclick="toggleBoxes('compareTooltipMb')">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipMb">
                        <div class="tooltip"> <!--Todo add 'tp-blue' class when there is no item -->
                            <span class="tooltip-title"><fmt:message key="comparison" bundle="${Bundles}"/></span>
                            <ul>
                                <li>
                                    <span><fmt:message key="Consumer crediting" bundle="${Bundles}"/></span>
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
                    <span class="close-btn" id="navCloseBtn"><i class="icon-close font-16"></i></span>
                    <div class="search-popup" id="searchPopup">
                        <span class="close-btn hide-for-tablet" id="closeBtn"><i class="icon-close font-16"></i></span>
                        <span class="state ellipsis show-for-tablet"><i class="icon-state"></i> Երևան </span>
                        <div class="inner">
                            <p class="font-30 text-center uppercase hide-for-tablet">Որոնում</p>
                            <div class="def-input int-right-icon int-outline margin-top-20">
                                <form action="SearchWeb" method="post">
                                    <i class="icon-search" type="submit"></i>
                                    <input type="text" name="Search" placeholder="Search"/>
                                    <input type="hidden" name="PageLanguage" value="${requestScope.Pagelanguage}">
                                    <input type="hidden" name="Currancy" value="${requestScope.PageCurrancy}">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="nav-content">
                        <c:if test="${requestScope.dropDownsListWithCurrancy!=null}">
                            <c:forEach items="${requestScope.dropDownsListWithCurrancy}" var="dropDownList">
                                <ul>
                                    <li>
                                        <a href="App?Currancy=${requestScope.PageCurrancy}">ԳԼԽԱՎՈՐ</a></li>
                                    <li>
                                        <a href="DepositClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎԱՆԴՆԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="MortgageClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՀԻՓՈԹԵՔ</a></li>
                                    <li>
                                        <a href="ConsumerClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՍՊԱՌՈՂԱԿԱՆ ՎԱՐԿԵՐ</a>
                                    </li>
                                    <li>
                                        <a href="AutoClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԱՎՏՈՎԱՐԿ</a></li>
                                    <li>
                                        <a href="MicroClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ՄԻԿՐՈՎԱՐԿ</a></li>
                                    <li>
                                        <a href="AClient?Currancy=${requestScope.PageCurrancy}&&Amount=${dropDownList.minAmount}&&MaxAmounr=${dropDownList.maxAmount}">ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ
                                            ՎԱՐԿ</a>
                                    </li>
                                    <li>
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=Cash&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=OFF">ՔԱՐՏԵՐ</a></li>
                                </ul>
                            </c:forEach>
                        </c:if>
                    </div>
                    <span class="right hide-for-tablet">
                <span><i class="icon-search" id="searchBtn"></i></span>
                <span class="compere-box">
                    <span class="compere-icon" onclick="toggleBoxes('compareTooltipWeb')">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipWeb">
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
            </span>
                </div>
            </div>
        </div>

    </div>


    <div class="top-calculator top-search">
        <div class="bg" style="background-image: url('../images/2.png')">
            <div class="inner-container info-container">
                <div class="row-md clearfix margin-bottom-0">
                    <div class="text-box col-5">
                        <p class="font-32">Ավանդներ</p>
                        <p>Մենք կօգնենք Ձեզ գտնել <br/>շահավետ ավանդ</p>
                    </div>
                </div>
                <form action="calculatingDeposit" method="get">
                    <div class="row-md clearfix">
                        <div class="col-6 large col">
                            <span class="label">Մուտքագրեք ավանդի գումարը</span>
                            <div class="range-group">
                                <div class="def-range">

                                </div>
                                <!-- the range section -->
                                <c:if test="${requestScope.Amount!=null}">
                                    <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                               var="dropDownList2"
                                               varStatus="loop">
                                        <output style="display: none;" id="outputer"></output>
                                        <input type="text" name="value" min="${dropDownList2.minAmount}"
                                               max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                               value="${requestScope.Amountfiltered}" oninput="showVal(this.value)"
                                               onchange="showVal(this.value)" id="amount">
                                        <input type="range" name="range" min="${dropDownList2.minAmount}"
                                               max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                               value="${requestScope.Amountfiltered}" data-rangeslider
                                               id="amount_range">
                                        <input type="hidden" name="value_url" id="value_amount_url">
                                    </c:forEach>
                                </c:if>
                                <c:if test="${requestScope.Amount==null}">
                                    <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                               var="dropDownList3"
                                               varStatus="loop">
                                        <output style="display: none;" id="outputer"></output>
                                        <input type="text" name="value" min="${dropDownList3.minAmount}"
                                               max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                               value="${requestScope.Amountfiltered}" oninput="showVal(this.value)"
                                               onchange="showVal(this.value)" id="amount">
                                        <input type="range" name="range" min="${dropDownList3.minAmount}"
                                               max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                               value="${requestScope.Amountfiltered}" data-rangeslider
                                               id="amount_range">
                                        <input type="hidden" name="value_url" id="value_amount_url">

                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="def-select-box">
                                <form name="form1" action="DepositClient" method="get">

                                    <input type="hidden" name="PageLanguage"
                                           value="${requestScope.Pagelanguage}">
                                    <input type="hidden" name="City" value='<%=request.getAttribute("city")%>'>
                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'AMD'}">
                                            <select name="Currancy" onchange="document.form1.submit();">
                                                <option value="AMD" selected>֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                </form>
                                <form name="form2" action="DepositClient" method="get">
                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'USD'}">

                                            <input type="hidden" name="PageLanguage"
                                                   value="${requestScope.Pagelanguage}">

                                            <select name="Currancy" onchange="document.form2.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD" selected>$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                </form>
                                <form name="form3" action="DepositClient" method="get">
                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'EUR'}">
                                            <input type="hidden" name="PageLanguage"
                                                   value="${requestScope.Pagelanguage}">
                                            <select name="Currancy" onchange="document.form3.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR" selected>€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                </form>
                                <form name="form4" action="DepositClient" method="get">
                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'RUB'}">

                                            <input type="hidden" name="PageLanguage"
                                                   value="${requestScope.Pagelanguage}">
                                            <select name="Currancy" onchange="document.form4.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB" selected>₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>

                                </form>
                            </div>


                        </div>
                    </div>
                    <div class="col-2-3 middle col">
                        <span class="label">Ժամկետը</span>
                        <div class="def-select-box">
                            <select name="months">
                                <option value="1">1 ամիս</option>
                                <option value="3">3 ամիս</option>
                                <option value="6">6 ամիս</option>
                                <option value="9">9 ամիս</option>
                                <option value="12">12 ամիս</option>
                                <option value="18">18 ամիս</option>
                                <option value="24">24 ամիս</option>
                                <option value="36">36 ամիս</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-2 small col">
                        <input type="button" class="def-button btn-green">
                        <span>Գտնել</span>
                        </input>
                        <!-- the range section -->
                    </div>
            </div>
            </form>
        </div>
    </div>
</div>


<div class="padding-bt-60 bg-white-dark">
    <div class="inner-container">
        <div class="bookmarked-items loan-items-container  margin-bottom-40">
            <c:if test="${requestScope.depositeAseList != null}">
                <c:forEach items="${requestScope.depositeAseList}" var="SpecialDeposit">
                    <div class="table">
                        <div class="table-row">
                            <div class="table-cell item-img">
                                <c:forEach items="${SpecialDeposit.banksList}" var="image">
                                    <img src="<%=request.getContextPath()%>/${image}">
                                </c:forEach>
                            </div>
                            <c:if test="${requestScope.productNameList != null}">
                                <c:forEach items="${requestScope.productNameList}"
                                           var="productName" varStatus="page">
                                    <c:if test="${SpecialDeposit.productCode == productName.productCode}">
                                        <div class="table-cell">
                                            <c:out value="${productName.productNameAm}"/>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <div class="table-cell">
                                <p>Տոկոս</p>
                                <p class="font-28 bold">${SpecialDeposit.DPercentage}%</p>
                            </div>
                            <div class="table-cell">
                                <p>Գումար</p>
                                <p class="font-28 bold">${SpecialDeposit.DMinAmount}</p>
                            </div>
                            <div class="table-cell">
                                <p>Եկամուտ</p>
                                <p class="font-28 bold">${SpecialDeposit.DMaxAmount}</p>
                            </div>
                            <div class="table-cell text-center">
                                <c:set value="${SpecialDeposit.sendRequest}" var="isThere"/>
                                <c:choose>
                                    <c:when test="${isThere == 1}">
                                        <a href="index.php?page=bank/credits/deposit"
                                           class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                            ավելին</a>
                                        <p><a href="Compare?id=${SpecialDeposit.DId}"
                                        class="blue-link font-12">Համեմատել</a>
                                    </c:when>
                                    <c:otherwise>
                                        <%--  <a href="index.php?page=bank/credits/deposit"
                                             class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>--%>
                                        <p><a href="Compare?id=${SpecialDeposit.DId}"
                                              class="blue-link font-12">Համեմատել</a>

                                        </p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <div class="loan-items-container">
            <div class="table hide-for-mb">
                <div class="table-row head sorting">
                    <div class="table-cell"><span>Բանկ</span></div>
                    <div class="table-cell"><span>Ավանդի անունը</span></div>
                    <div class="table-cell">
                        <span>Տոկոս</span>
                        <i class="icon-polygon-down"></i>
                        <i class="icon-polygon-up"></i>
                    </div>
                    <div class="table-cell">
                        <span>Նվազագույն գումարը</span>
                        <i class="icon-polygon-down active"></i>
                        <i class="icon-polygon-up"></i>
                    </div>
                    <div class="table-cell"><span>Առավելագույն գումարը</span></div>
                    <div class="table-cell"></div>
                </div>
            </div>

            <div class="table">
                <c:if test="${requestScope.depositAllInRage != null}">
                    <c:forEach var="firstDepo" items="${requestScope.depositAllInRage}" varStatus="theCount">
                        <div class="accordion">
                        <c:set var="languages" value="${requestScope.Pagelanguage}"/>
                        <c:choose>
                            <c:when test="${languages == 'hy_AM'}">
                                <div class="table-cell item-img">
                                    <img src="<%=request.getContextPath()%>/${firstDepo.banksList}"/>
                                    <p class="margin-top-20 hide-for-mb">
                                        <a class="green-link font-12 width-icon right-icon ellipsis">
                                            <span>+5 առաջարկ ${theCount.index}</span>
                                            <i class="icon-arrow-down"></i>
                                        </a>
                                    </p>
                                </div>

                                <div class="table-cell">
                                    <p><a href="" class="blue-dark-link">${firstDepo.productName}</a></p>
                                </div>
                                <div class="table-cell">
                                    <p>Տարեկան ավանդական տոկոսը</p>
                                    <p class="font-28 bold">${firstDepo.DPercentage}%</p>
                                </div>
                                <div class="table-cell">
                                    <p>Նվազագույն գումարը</p>
                                    <p class="font-28 bold">${firstDepo.DMinAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Առավելագույն գումարը</p>
                                    <p class="font-28 bold">${firstDepo.DMaxAmount}</p>
                                </div>
                                <div class="table-cell">
                                    <p>Ձեր եկամուտը</p>
                                    <p class="font-28 bold">367 օր</p>
                                </div>

                                <div class="table-cell text-center last">
                                    <p class="margin-bottom-20 show-for-mb text-left">
                                        <a class="green-link font-12 width-icon right-icon ellipsis">
                                            <span>+5 առաջարկ ${theCount.index}</span> <i
                                                class="icon-arrow-down"></i>
                                        </a>
                                    </p>
                                    <a href="" class="def-button btn-green with-shadow  margin-bottom-15">Անցնել
                                        էջ</a>
                                    <p><a href="index.php?page=compare/deposit"
                                          class="blue-link font-12">Համեմատել</a>
                                    </p>
                                </div>
                                </div>


                                <div class="panel">

                                <c:forEach items="${requestScope.depositAllInSubRage}" var="semiDeposit"
                                           varStatus="theCount">

                                    <c:set var="getDetail" value="${semiDeposit.bankId}"/>
                                    <c:choose>
                                        <c:when test="${semiDeposit.bankId == firstDepo.bankId}">
                                            <div class="table-cell item-img">
                                                <img src="<%=request.getContextPath()%>/${semiDeposit.banksList}"/>
                                            </div>
                                            <div class="table-cell">
                                                <p><a href="" class="blue-dark-link">${semiDeposit.productName}</a>
                                                </p>
                                            </div>
                                            <div class="table-cell">
                                                <p>Տարեկան ավանդական տոկոսը</p>
                                                <p class="font-28 bold">${semiDeposit.DPercentage}%</p>
                                            </div>
                                            <div class="table-cell">
                                                <p>Ավանդի ժամկետը</p>
                                                <p class="font-28 bold">${semiDeposit.DMinAmount}</p>
                                            </div>
                                            <div class="table-cell">
                                                <p>Ձեր եկամուտը</p>
                                                <p class="font-28 bold">${semiDeposit.DMaxAmount}</p>
                                            </div>
                                            <div class="table-cell text-center">
                                                <a href=""
                                                   class="def-button btn-green with-shadow  margin-bottom-15">Անցնել
                                                    էջ</a>
                                                <p><a href="index.php?page=compare/deposit"
                                                      class="blue-link font-12">Համեմատել</a>
                                                </p>
                                            </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>

                            </c:when>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </div>

        </div>


        <%--            <div class="table">

                <div class="table-row">
                    <c:if test="${requestScope.depositByMaxMinAmountAsc != null}">
                    <c:forEach var="appirance1" items="${requestScope.depositByMaxMinAmountAsc}" begin="1">
                    <div class="table-cell item-img">
                        <img src="../images/banks/logo/vtb.svg"/>
                        <p class="margin-top-20 hide-for-mb">
                            <a class="green-link font-12 width-icon right-icon ellipsis"
                               onclick="toggleSubSuggestions()">
                                <span>+5 առաջարկ </span> <i class="icon-arrow-down"></i>
                            </a>
                        </p>
                    </div>


                    <div class="table-cell">
                        <p><a href="" class="blue-dark-link">Ավանդ «Ընտանեկան»</a></p>
                    </div>
                    <div class="table-cell">
                        <p>Տարեկան ավանդական տոկոսը</p>
                        <p class="font-28 bold">16,00%</p>
                    </div>
                    <div class="table-cell">
                        <p>Ավանդի ժամկետը</p>
                        <p class="font-28 bold">367 օր</p>
                    </div>
                    <div class="table-cell">
                        <p>Ձեր եկամուտը</p>
                        <p class="font-28 bold">367 օր</p>
                    </div>
                    <div class="table-cell text-center last">
                        <p class="margin-bottom-20 show-for-mb text-left">
                            <a class="green-link font-12 width-icon right-icon ellipsis"
                               onclick="toggleSubSuggestions()">
                                <span>+5 առաջարկ </span> <i class="icon-arrow-down"></i>
                            </a>
                        </p>
                        <a href="" class="def-button btn-green with-shadow margin-bottom-15">Անցնել էջ</a>
                        <p><a href="index.php?page=compare/deposit" class="blue-link font-12">Համեմատել</a></p>
                    </div>
                </div>

            <c:if test="${requestScope.depositByMaxMinAmountAsc != null}">
            <c:forEach var="appirance1" items="${requestScope.depositByMaxMinAmountAsc}" begin="2">
            <div class="table">
                <div class="table-row">
                    <div class="table-cell item-img">
                        <img src="../images/banks/logo/vtb.svg"/>
                    </div>
                    <div class="table-cell">
                        <p><a href="" class="blue-dark-link">Ավանդ «Ընտանեկան»</a></p>
                    </div>
                    <div class="table-cell">
                        <p>Տարեկան ավանդական տոկոսը</p>
                        <p class="font-28 bold">16,00%</p>
                    </div>
                    <div class="table-cell">
                        <p>Ավանդի ժամկետը</p>
                        <p class="font-28 bold">367 օր</p>
                    </div>
                    <div class="table-cell">
                        <p>Ձեր եկամուտը</p>
                        <p class="font-28 bold">367 օր</p>
                    </div>
                    <div class="table-cell text-center">
                        <a href="" class="def-button btn-green with-shadow margin-bottom-15">Անցնել էջ</a>
                        <p><a href="index.php?page=compare/deposit" class="blue-link font-12">Համեմատել</a></p>
                    </div>
                    </c:forEach>
                    </c:if>
                    </c:forEach>
                    </c:if>

                </div>
            </div>
            </div>--%>


        <div class="see-all flex align-items-center justify-center">
            <span class="green-link width-icon right-icon">
                <span>Տեսնել ավելին</span>
                <i class="icon-arrow-down font-9"></i>
            </span>
        </div>
    </div>
</div>


<div class="padding-bt-60">
    <div class="inner-container">
        <p class="font-24 title margin-bottom-35">
            <fmt:message key="Special Offers" bundle="${Bundles}"/>
        </p>
        <div class="tab-container">
            <div class="tab-nav flex align-items-center flex-wrap">
    <span class="tab-link" onclick="openTabItem(event, 'deposit')" id="defaultOpen">
    <fmt:message key="Deposits" bundle="${Bundles}"/> </span>
                <span class="tab-link" onclick="openTabItem(event, 'mortgage')">
    <fmt:message key="Mortgage" bundle="${Bundles}"/></span>
                <span class="tab-link" onclick="openTabItem(event, 'consumer-loan')">
    <fmt:message key="Consumer crediting" bundle="${Bundles}"/></span>
                <span class="tab-link" onclick="openTabItem(event, 'car-loan')">
    <fmt:message key="Auto loan" bundle="${Bundles}"/></span>
                <span class="tab-link" onclick="openTabItem(event, 'agricultural')">
    <fmt:message key="Agricultural crediting" bundle="${Bundles}"/></span>
                <span class="tab-link" onclick="openTabItem(event, 'card')">
    <fmt:message key="Cards" bundle="${Bundles}"/></span>
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

<footer>
    <div class="inner-container large">
        <div class="top flex space-between">
            <div class="flex align-items-center">
    <span class="logo">
    <img src="${requestScope.getContextPath}/images/oferta.png" alt=""/>
    </span>
            </div>
            <div class="flex align-items-center">
    <span class="margin-right-15 hide-for-mb"><fmt:message key="Join us on social networks!"
                                                           bundle="${Bundles}"/></span>
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
    <li><a href="About?Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"><fmt:message
            key="About us" bundle="${Bundles}"/></a></li>
    <li><a href="ContactUs?Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"><fmt:message
            key="Contact us" bundle="${Bundles}"/></a></li>
    <li><a href="policy?Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"><fmt:message
            key="Privacy Policy" bundle="${Bundles}"/></a></li>
    <li class="show-for-tablet"><a
            href="banks?Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"><fmt:message
            key="Our Partners"
            bundle="${Bundles}"/></a></li>
    <li class="show-for-tablet"><a
            href="Blog?Pagelanguage=${requestScope.Pagelanguage}&&Currancy=${requestScope.PageCurrancy}"><fmt:message
            key="Useful Articles"
            bundle="${Bundles}"/></a></li>
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

</body>
</html>


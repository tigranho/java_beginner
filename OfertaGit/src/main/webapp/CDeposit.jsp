<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Oferta.am</title>
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/Interval.js"></script>
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
                    <%--    <span class="compere-box show-for-tablet relative">
                     <span class="compere-icon">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>--%>
                    </span>
                        <div class="tooltip-container bottom right" id="compareTooltipMb">
                            <div class="tooltip"> <!--Todo add 'tp-blue' class when there is no item -->
                                <%-- <span class="tooltip-title">Համեմատության</span>
                                 <ul>
                                     <li>
                                         <span>Սպառողական վարկ</span>
                                         <span class="bold font-14">4</span>
                                         <i class="icon-delete"></i>
                                     </li>
                                 </ul>
                                 <!--Todo add when there is no item -->
                                 <!--                            <span>Համեմատության էջում տեղ չկա</span>-->--%>
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

                </div>
            </div>
        </div>

    </div>


    <div class="padding-bt-60 bg-white-dark">
        <div class="inner-container">
            <div class="compare-table flex-table">
                <div class="flex align-items-center head">
                    <div class="flex-item">
                    <span class="flex align-items-center">
                        <span class="icon"><img src="../../images/icons/1.svg"/></span>
                        <span class="font-16 bold padding-left-15">Ավանդներ</span>
                    </span>
                    </div>

                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">
                            <c:set value="${compare.bankId}" var="BankId" scope="request"/>
                            <%--<sql:setDataSource var="db"
                                               driver="com.mysql.jdbc.Driver"
                                               url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                               user="root" password="YLf{}cJ,Kvd%"/>--%>
                            <sql:setDataSource var="db"
                                               driver="com.mysql.jdbc.Driver"
                                               url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                               user="oferta_root" password="mnbvcxz00A!"/>
                            <sql:query dataSource="${db}" var="comment">
                                SELECT * FROM bankscontroller <%--WHERE bankId=BankId--%> ;
                            </sql:query>
                            <div class="flex-item">
                                <div class="flex space-between">
                         <span>

                             <span class="item-img flex align-items-center">
                             <c:forEach var="CommentsDao" items="${comment.rows}">
                                 <c:if test="${CommentsDao.bankId == BankId}">
                                     <img src="${CommentsDao.bankSmallLogo}"/>
                                 </c:if>
                             </c:forEach>

                                   </span>

                            <p class="margin-top-20"><a href="#"
                                                        class="green-link font-12">${compare.productName}</a></p>
                        </span>
                                    <span class="delete">
                                        <a class="icon-trash"
                                           href="CompareDeposit?IDToRemove=${compare.idCheck}&&Currancy=<%= request.getParameter("Currancy")%>&&clientCode=${compare.SID}"></a>

                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>

                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Տոկոսադրույք</p>
                    </div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">
                            <div class="flex-item">
                                <span>${compare.percentage}%</span>
                                <c:set var="percent" value="${compare.percentage}" scope="request"/>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Ավանդի գումարը</p>
                    </div>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script>
                        $(document).ready(function () {
                            var timer = null;
                            $("#start1").keydown(function () {
                                console.log("start1");
                                clearTimeout(timer);
                                timer = setTimeout(SendInfoToBack1, 1800)
                            });
                            $("#start2").keydown(function () {
                                console.log("start2");
                                clearTimeout(timer);
                                timer = setTimeout(SendInfoToBack2, 2800)
                            });
                            $("#start3").keydown(function () {
                                console.log("start3");
                                clearTimeout(timer);
                                timer = setTimeout(SendInfoToBack3, 3800)
                            });
                            $("#start4").keydown(function () {
                                console.log("start4");
                                clearTimeout(timer);
                                timer = setTimeout(SendInfoToBack4, 4800)
                            });

                            function SendInfoToBack1() {
                                var x = $("#start1").val();
                                x = x.replace(/,/g, '');
                                console.log("x = " + x);
                                if (!$.isNumeric(x)) {
                                    alert("Խնդրում ենք մուտքագրել թվեր")
                                } else if(x < 0 ){
                                    alert("Խնդրում ենք մուտքագրել Դրական թվեր")
                                } else {
                                    console.log("Սխալների մուտքագրում չկա");
                                    $("#startApp1").submit();
                                    console.log("Ներկայացվեց հաջողությամբ1")
                                }
                            };

                            function SendInfoToBack2() {
                                var x = $("#start2").val();
                                x = x.replace(/,/g, '');
                                console.log("x = " + x);
                                if (!$.isNumeric(x)) {
                                    alert("Խնդրում ենք մուտքագրել թվեր")
                                }else if(x < 0 ){
                                    alert("Խնդրում ենք մուտքագրել Դրական թվեր")
                                } else {
                                    console.log("Սխալների մուտքագրում չկա");
                                    $("#startApp2").submit();
                                    console.log("Ներկայացվեց հաջողությամբ2")
                                }
                            };

                            function SendInfoToBack3() {
                                var x = $("#start3").val();
                                x = x.replace(/,/g, '');
                                console.log("x = " + x);
                                if (!$.isNumeric(x)) {
                                    alert("Խնդրում ենք մուտքագրել թվեր")
                                }else if(x < 0 ){
                                    alert("Խնդրում ենք մուտքագրել Դրական թվեր")
                                } else {
                                    console.log("Սխալների մուտքագրում չկա");
                                    $("#startApp3").submit();
                                    console.log("Ներկայացվեց հաջողությամբ3")
                                }
                            };

                            function SendInfoToBack4() {
                                var x = $("#start4").val();
                                x = x.replace(/,/g, '');
                                console.log("x = " + x);
                                if (!$.isNumeric(x)) {
                                    alert("Խնդրում ենք մուտքագրել թվեր")
                                }else if(x < 0 ){
                                    alert("Խնդրում ենք մուտքագրել Դրական թվեր")
                                } else {
                                    console.log("Սխալների մուտքագրում չկա");
                                    $("#startApp4").submit();
                                    console.log("Ներկայացվեց հաջողությամբ4")
                                }
                            };
                        });

                    </script>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}" varStatus="TheCount">
                            <c:set value='<%=request.getParameter("Currancy")%>' var="currancy"/>

                            <div class="flex-item">
                                <div class="def-input int-dashed">


                                    <c:choose>
                                        <c:when test="${currancy == 'AMD'}">
                                            <form action="CompareDeposit" name="CompareNewAmount" type="get"
                                                  id="startApp${TheCount.count}">
                                                <input name="NewAmount" type="text" id="start${TheCount.count}"
                                                       value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${compare.amount}"/>"
                                                       onchange="doSearch();" maxlength="9"/>
                                                <input type="hidden" name="idNew" value='${compare.idCheck}'>
                                                <input type="hidden" name="Currancy"
                                                       value='<%=request.getParameter("Currancy")%>'>
                                                <input type="hidden" name="clientCode" value='${compare.SID}'>

                                            </form>
                                        </c:when>
                                        <c:when test="${currancy == 'USD'}">
                                            <form action="CompareDeposit" name="CompareNewAmount" type="get">
                                                <input type="text" name="NewAmount" id="start${TheCount.count}"
                                                       value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${compare.amount}"/>"
                                                       onchange="doSearch();" maxlength="9"/>
                                                <input type="hidden" name="idNew" value='${compare.idCheck}'>
                                                <input type="hidden" name="Currancy"
                                                       value='<%=request.getParameter("Currancy")%>'>
                                                <input type="hidden" name="clientCode" value='${compare.SID}'>

                                            </form>
                                        </c:when>
                                        <c:when test="${currancy == 'EUR'}">
                                            <form action="CompareDeposit" name="CompareNewAmount" type="get">
                                                <input type="text" name="NewAmount" id="start${TheCount.count}"
                                                       value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${compare.amount}"/>"
                                                       onchange="doSearch();"/>
                                                <input type="hidden" name="idNew" value='${compare.idCheck}'>
                                                <input type="hidden" name="Currancy"
                                                       value='<%=request.getParameter("Currancy")%>'>
                                                <input type="hidden" name="clientCode" value='${compare.SID}'>

                                            </form>
                                        </c:when>
                                        <c:when test="${currancy == 'RUB'}">
                                            <form action="CompareDeposit" name="CompareNewAmount" type="get">
                                                <input type="text" name="NewAmount" id="start${TheCount.count}"
                                                       value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${compare.amount}"/>"
                                                       onchange="doSearch();"/>
                                                <input type="hidden" name="idNew" value='${compare.idCheck}'>
                                                <input type="hidden" name="Currancy"
                                                       value='<%=request.getParameter("Currancy")%>'>
                                                <input type="hidden" name="clientCode" value='${compare.SID}'>

                                            </form>
                                        </c:when>

                                    </c:choose>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>


                </div>
                <form action="CompareDeposit" name="CompareNewAmount" type="get">
                    <div class="flex align-items-center">
                        <div class="flex-item">
                            <span class="title blue">Ամսեկան վճարում</span>
                            <span class="info-tooltip">
                        <i class="icon-info"></i>
                        <div class="tooltip-container top left">
                             <div class="tooltip tp-blue">
                                <span class="arrow"><i class="icon-polygon-down"></i></span>
                               Հաշվարկված է հավասարաչափ մարումով
                            </div>
                        </div>
                    </span>
                        </div>
                        <%!
                            int calculatePercentage(float Amount, double Percentage, int month) {
                                double per= Percentage/100;
                                int monthConvert = month / 30;
                                double firstStep = (Amount * per);
                                float secondStep = (float) (firstStep / 365);
                                float finalStep2 = (secondStep * 31 );
                                float result = (finalStep2 * monthConvert) ;
                                float finalResult = (float) (Amount + (result - (result * 0.10)));
                                return (int) Math.round(finalResult);
                            }
                        %>
                        <c:if test="${requestScope.DepositComapreList != null}">
                            <c:forEach var="compare" items="${requestScope.DepositComapreList}">
                                <c:set value='${compare.amount}' var="minAmount1" scope="request"/>
                                <c:set value='${compare.percentage}' var="Percntage1" scope="request"/>

                                <c:set value='${compare.months}' var="months1" scope="request"/>
                                <div class="flex-item">
                                    <%!
                                        /* double percent;
                                         int Amount;*/
                                        int month;
                                        int monthConvert;
                                        static float amount;
                                        static float percentages;
                                    %>

                                    <%
                                        amount = Float.parseFloat(String.valueOf(request.getAttribute("minAmount1")));
                                        percentages = Float.parseFloat(String.valueOf(request.getAttribute("Percntage1")));
                                        month = (int) request.getAttribute("months1");//1
                                        // monthConvert = month / 31;
                                    %>

                                    <h2 class="timer count-title count-number bold blue"
                                        data-to="<%=calculatePercentage(amount, percentages, (month)) %>"
                                        data-speed="1739"></h2>
                                        <%-- <p class="bold blue"><fmt:formatNumber type="number" maxFractionDigits="3"
                                                                                value='<%=calculatePercentage(amount, percentages, (monthConvert)) %>'/>
                                         </p>--%>
                                </div>

                            </c:forEach>
                        </c:if>
                    </div>
                </form>
                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Ժամկետը</p>
                    </div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">
                            <c:set value='<%=request.getParameter("Currancy")%>' var="currancy"/>

                            <div class="flex-item">
                                <c:set value="${compare.months}" var="monthsLast" scope="request"/>
                                <%
                                    int monthslast = Integer.parseInt(String.valueOf(request.getAttribute("monthsLast")));
                                    int monthslastconverted = monthslast / 30;
                                %>
                                <c:choose>
                                    <c:when test="${currancy == 'AMD'}">
                                        <p><%=monthslastconverted%> Ամիս</p>
                                    </c:when>
                                    <c:when test="${currancy == 'USD'}">
                                        <p><%=monthslastconverted%>  Ամիս</p>
                                    </c:when>
                                    <c:when test="${currancy == 'EUR'}">
                                        <p><%=monthslastconverted%>  Ամիս</p>
                                    </c:when>
                                    <c:when test="${currancy == 'RUB'}">
                                        <p><%=monthslastconverted%>  Ամիս</p>
                                    </c:when>

                                </c:choose>

                            </div>
                        </c:forEach>
                    </c:if>

                </div>

                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Համալրման հնարավորություն</p>
                    </div>

                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">

                            <c:set value="${compare.DEquippingPossibilitiesid}" var="IsTrue"/>
                            <c:choose>

                                <c:when test="${IsTrue == 1}">
                                    <div class="flex-item">
                                        <p class="grey font-18">Ոչ</p>
                                    </div>
                                </c:when>
                                <c:when test="${IsTrue != 1}">
                                    <div class="flex-item">
                                        <p class="green font-18">Այո</p>
                                    </div>
                                </c:when>

                            </c:choose>

                        </c:forEach>
                    </c:if>

                </div>
                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Ժամկետից շուտ գումարի դուրս բերում</p>
                    </div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">

                            <c:set value="${compare.DEarlierWithdrawalAmountid}" var="IsTrue"/>
                            <c:choose>

                                <c:when test="${IsTrue == 1}">
                                    <div class="flex-item">
                                        <p class="grey font-18">Ոչ</p>
                                    </div>
                                </c:when>
                                <c:when test="${IsTrue != 1}">
                                    <div class="flex-item">
                                        <p class="green font-18">Այո</p>
                                    </div>
                                </c:when>
                            </c:choose>

                        </c:forEach>
                    </c:if>

                </div>
                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Ավտոմատ երկրարացում</p>
                    </div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">

                            <c:set value="${compare.DAutoExtendPeriodid}" var="IsTrue"/>
                            <c:choose>

                                <c:when test="${IsTrue == 1}">
                                    <div class="flex-item">
                                        <p class="grey font-18">Ոչ</p>
                                    </div>
                                </c:when>
                                <c:when test="${IsTrue != 1}">
                                    <div class="flex-item">
                                        <p class="green font-18">Այո</p>
                                    </div>
                                </c:when>
                            </c:choose>

                        </c:forEach>
                    </c:if>

                </div>
                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Տոկոսների կապիտալիզացիա</p>
                    </div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">

                            <c:set value="${compare.DCapitalizationPercentid}" var="IsTrue"/>
                            <c:choose>

                                <c:when test="${IsTrue == 1}">
                                    <div class="flex-item">
                                        <p class="grey font-18">Ոչ</p>
                                    </div>
                                </c:when>
                                <c:when test="${IsTrue != 1}">
                                    <div class="flex-item">
                                        <p class="green font-18">Այո</p>
                                    </div>
                                </c:when>

                            </c:choose>

                        </c:forEach>
                    </c:if>
                </div>


                <div class="flex align-items-center bottom">
                    <div class="flex-item"></div>
                    <c:if test="${requestScope.DepositComapreList != null}">
                        <c:forEach var="compare" items="${requestScope.DepositComapreList}">

                            <div class="flex-item">
                                <form action="Credits" method="post">
                                    <button class="def-button btn-blue">Իմանալ ավելին</button>
                                    <input type="hidden" name="ProductCode" value="${compare.idCheck}">
                                    <input type="hidden" name="Percentage" value="${compare.percentage}">
                                    <input type="hidden" name="GoToPage" value="Deposit">
                                    <input type="hidden" name="range" value="${compare.amount}">
                                    <input type="hidden" name="Currancy" value='<%=request.getParameter("Currancy")%>'>
                                    <c:set value="${compare.months}" var="months" scope="request"/>
                                    <%
                                        int months = Integer.parseInt(String.valueOf(request.getAttribute("months")));
                                        int MonthsToDays = months / 30;
                                    %>
                                    <input type="hidden" name="months" value="<%=MonthsToDays%>">
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/rangeslider.js/2.3.0/rangeslider.min.js'></script>

    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    <script src="<%=request.getContextPath()%>/js/search.js"></script>

    <%--    <script src="<%=request.getContextPath()%>/js/range.js"></script>
        <script src="<%=request.getContextPath()%>/js/modal.js"></script>
        <script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/carousel.js"></script>--%>


</body>
</html>


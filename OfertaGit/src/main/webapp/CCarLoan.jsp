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
                                class="icon-state"></i> ${requestScope.City}  </span>
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
                        <span class="icon"><img src="../../images/icons/4.svg"/></span>
                        <span class="font-16 bold padding-left-15">Ավտովարկեր</span>
                    </span>
                    </div>

                    <c:if test="${requestScope.consumerCreditsCompareList != null}">
                        <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                            <c:set value="${compare.bankId}" var="BankId" scope="request"/>

                            <div class="flex-item">
                                <div class="flex space-between">
                         <span>

                             <span class="item-img flex align-items-center">
                            <c:if test="${requestScope.BanksList != null}">
                                <c:forEach items="${requestScope.BanksList}" var="CommentsDao">
                                    <c:if test="${CommentsDao.bankId == BankId}">
                                        <img src="${CommentsDao.bankSmallLogo}"/>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                                   </span>

                            <p class="margin-top-20"><a href="#"
                                                        class="green-link font-12">${compare.productName}</a></p>
                        </span>
                                    <span class="delete">
                                        <a class="icon-trash"
                                           href="CompareCarLoan?IDToRemove=${compare.idCheck}&&Currancy=<%= request.getParameter("Currancy")%>&&clientCode=${compare.SID}"></a>

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
                    <c:if test="${requestScope.consumerCreditsCompareList != null}">
                        <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}" varStatus="TheCount">
                            <div class="flex-item">
                                <span>${compare.percentage}%</span>
                                <c:set var="percent" value="${compare.percentage}" scope="request"/>
                                <%!
                                    static double percentageFromUp;
                                %>
                                <%
                                    percentageFromUp = Double.parseDouble(String.valueOf(request.getAttribute("percent")));
                                %>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="flex align-items-center">
                    <div class="flex-item">
                        <p class="title">Վարկի գումարը</p>
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
                    <c:if test="${requestScope.consumerCreditsCompareList != null}">
                        <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}"
                                   varStatus="TheCount">
                            <c:set value='<%=request.getParameter("Currancy")%>' var="currancy"/>

                            <div class="flex-item">
                                <div class="def-input int-dashed">


                                            <form action="CompareCarLoan" method="post" name="amountSectionform"
                                                  id="startApp${TheCount.count}">
                                                <input type="text" name="NewAmount" id="start${TheCount.count}"
                                                       value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${compare.amount}"/>"
                                                       onchange="doSearch();" maxlength="9"/>
                                                <input type="hidden" name="idNew"
                                                       value='${compare.idCheck}'>
                                                <input type="hidden" name="Currancy"
                                                       value='<%=request.getParameter("Currancy")%>'>
                                                <input type="hidden" name="clientCode"
                                                       value='${compare.SID}'>
                                            </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>


                </div>

                <form action="CompareCarLoan" name="compareNewAmount" type="get">
                    <div class="flex align-items-center">
                        <div class="flex-item">
                            <p class="title">Դուք կստանաք </p>
                        </div>

                        <c:if test="${requestScope.consumerCreditsCompareList != null}">
                        <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}"
                                   varStatus="TheCount">

                        <div class="flex-item">
                            <div class="def-select-box select-dashed">
                                <c:set var="monthsSection" value="${compare.months}" scope="request"/>

                                <%!
                                    int monthsFromData;
                                    static int convertToMonths;

                                %>

                                <%
                                    monthsFromData = Integer.parseInt(String.valueOf(request.getAttribute("monthsSection")));
                                    convertToMonths = monthsFromData;

                                %>

                                        <select name="months${TheCount.count}" id="select_month"
                                                onchange="document.compareNewAmount.submit();">
                                            <option value="12" <c:if test="<%=convertToMonths == 12%>">selected</c:if> >12 ամիս</option>
                                            <option value="18" <c:if test="<%=convertToMonths == 18%>">selected</c:if>>18 ամիս</option>
                                            <option value="24" <c:if test="<%=convertToMonths == 24%>">selected</c:if>>24 ամիս</option>
                                            <option value="36" <c:if test="<%=convertToMonths == 36%>">selected</c:if>>36 ամիս</option>
                                            <option value="48" <c:if test="<%=convertToMonths == 48%>">selected</c:if>>48 ամիս</option>
                                            <option value="60" <c:if test="<%=convertToMonths == 60%>">selected</c:if>>60 ամիս</option>
                                            <option value="120" <c:if test="<%=convertToMonths == 120%>">selected</c:if>>120 ամիս</option>
                                        </select>
                                        <input type="hidden" name="Currancy"
                                               value='<%=request.getParameter("Currancy")%>'>
                                        <input type="hidden" name="clientCode" value='${compare.SID}'>
                                        <input type="hidden" name="idCheck${TheCount.count}" value='${compare.idCheck}'>

                            </div>
                        </div>
                            <%-- </c:if>--%>
                        </c:forEach>
                        </c:if>
                </form>
            </div>


            <div class="flex align-items-center">
                <div class="flex-item">
                    <p class="title">Արժույթ</p>
                </div>
                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <div class="flex-item">
                            <p><%= request.getParameter("Currancy")%>
                            </p>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
            <div class="flex align-items-center">
                <div class="flex-item">
                    <p class="title">Սպասարկման վճար</p>
                </div>

                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <div class="flex-item">
                            <h4 class="timer count-title count-number bold blue" data-to="${compare.service}"
                                data-speed="1739"></h4>
                                <%--<p>${compare.service}</p>--%>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
            <div class="flex align-items-center">
                <div class="flex-item">
                    <span class="title blue">Ժամկետի վերջում դու կստանաք</span>
                    <span class="info-tooltip">
                        <i class="icon-info"></i>
                        <div class="tooltip-container top left">
                             <div class="tooltip tp-blue">
                                <span class="arrow"><i class="icon-polygon-down"></i></span>
                                <span>Հարկերի նվազեցումից հետո</span>
                            </div>
                        </div>
                    </span>
                </div>

                <%!
                    long calculatePercentage(float Amount, double Percentage, int month) {
                        Percentage /= 100.0;
                        double monthlyRate = Percentage /12;
                        long termInMonths = month ;
                        double monthlyPayment = (Amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
                        return (long) monthlyPayment;
                    }
                %>

                <%!
                    long calculatePercentage2(float Amount, double Percentage, int month) {
                        Percentage /= 100.0;
                        double monthlyRate = Percentage /12;
                        long termInMonths = month ;
                        double monthlyPayment = (Amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
                        return (long) monthlyPayment * month;
                    }
                %>


                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <%-- <c:set value="${compare.percentage}" var="percentages"/>--%>
                        <c:set value='${compare.amount}' var="minAmount1" scope="request"/>
                        <c:set value='${compare.months}' var="months1" scope="request"/>
                        <c:set value='${compare.percentage}' var="percente" scope="request"/>
                        <div class="flex-item">
                            <%!
                                double percent;
                                int Amount;
                                int month;
                                int service;
                            %>

                            <%
                                percent = Double.parseDouble(String.valueOf(request.getAttribute("percente")));
                                Amount = Integer.parseInt(String.valueOf(request.getAttribute("minAmount1")));//100000
                                month = Integer.parseInt(String.valueOf(request.getAttribute("months1")));//1
                            %>
                            <h3 class="timer count-title count-number bold blue"
                                data-to="<%=calculatePercentage(Amount, percent, (month)) %>"
                                data-speed="1739"></h3>

                        </div>
                    </c:forEach>
                </c:if>
            </div>


            <div class="flex align-items-center">
                <div class="flex-item">
                    <p class="title">Ընդհանուր վճարված գումար</p>
                </div>
                <%!
                    double percent2;
                    int Amount2;
                    int month2;
                    int service2;
                %>
                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <c:set var="percent1" value="${compare.percentage}" scope="request"/>
                        <c:set var="minAmount1" value="${compare.amount}" scope="request"/>
                        <c:set var="months1" value="${compare.months}" scope="request"/>
                        <c:set var="serviceFee" value="${compare.service}" scope="request" />
                        <%
                            percent2 = Double.parseDouble(String.valueOf(request.getAttribute("percent1")));
                            Amount2 = Integer.parseInt(String.valueOf(request.getAttribute("minAmount1")));//100000
                            month2 = Integer.parseInt(String.valueOf(request.getAttribute("months1")));//1
                            service2 = Integer.parseInt(String.valueOf(request.getAttribute("serviceFee")));
                        %>
                        <div class="flex-item">
                            <h3 class="timer count-title count-number bold blue"
                                data-to="<%=calculatePercentage2(Amount2, percent2, month2) + (service2) %>"
                                data-speed="1739"></h3>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
            <div class="flex align-items-center padding-top-30">
                <div class="flex-item"></div>
                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <div class="flex-item">
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="flex align-items-center bottom">
                <div class="flex-item"></div>
                <c:if test="${requestScope.consumerCreditsCompareList != null}">
                    <c:forEach var="compare" items="${requestScope.consumerCreditsCompareList}">
                        <div class="flex-item">
                            <form action="CreditSend" method="post">
                                <button class="def-button btn-blue">Իմանալ ավելին</button>
                                <input type="hidden" name="ProductCode" value="${compare.idCheck}">
                                <input type="hidden" name="PageToGo" value="Cars">
                                <input type="hidden" name="range" value="${compare.amount}">
                                <input type="hidden" name="Amount" value="${compare.amount}">
                                <input type="hidden" name="Currancy" value='<%=request.getParameter("Currancy")%>'>
                                <input type="hidden" name="months" value='${compare.months}'>
                                <input type="hidden" name="City" value='<%=request.getAttribute("city")%>'>
                                <input type="hidden" name="months" value="<%=convertToMonths%>">
                                <input type="hidden" name="range_two" value="${compare.perpaiment}">
                                <input type="hidden" name="percentageSecond" value="${compare.percentage}">
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
<script src="<%=request.getContextPath()%>/js/range.js"></script>
<script src="<%=request.getContextPath()%>/js/modal.js"></script>
<script src="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.js"></script>
<script src="<%=request.getContextPath()%>/js/carousel.js"></script>


</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/23/2019
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Oferta.am</title>
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="css/global.css" type="text/css">
    <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript"
            src="//cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>

    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oferta.am</title>
    <link href="../images/favicon.ico" rel="shortcut icon">

   <link rel="stylesheet" href="css/style.css">

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
								<%--<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}">Մեր Գործընկերները</a></li>--%>
								<li><a href="Ofbanks?Currancy=${requestScope.PageCurrancy}&&City=<%=request.getAttribute("city")%>">Մեր Գործընկերները</a></li>
									<li><a href="Blog">Օգտակար հոդվածներ</a></li>
								</ul>
							</span>
                    </div>
                    <div class="right flex align-items-center">
                        <span class="state ellipsis hide-for-tablet"><i
                                class="icon-state"></i>${requestScope.City}    </span>
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
                                        <a href="CardClient?Currancy=${requestScope.PageCurrancy}&&Dram=AMD&&Ruble=OFF&&Dollar=OFF&&Euro=OFF&&Cashback=on&&Depit=OFF&&Free=OFF&&Period=OFF&&Credit=credit">ՔԱՐՏԵՐ</a></li>
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
                                 <form action="CardMoreDetail" method="post" name="Delete">
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
                                 <form action="CardMoreDetail" method="post" name="DeleteHipotek">
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
                                  <form action="CardMoreDetail" method="post" name="DeleteConsumer">
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
                                 <form action="CardMoreDetail" method="post" name="DeleteCar">
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
                                 <form action="CardMoreDetail" method="post" name="DeleteAG">
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


    <div class="bg-white-dark padding-bottom-60 bank-item-page">
        <div class="inner-container">
            <c:if test="${requestScope.cardsList != null}">
                <c:forEach items="${requestScope.cardsList}" var="Details">
                    <div class="bank-header">
                        <c:set var="bankId" value="${Details.bankId}"/>
                            <%--  <sql:setDataSource var="db"
                                                                                                                     driver="com.mysql.jdbc.Driver"
                                                                                                                     url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                                                     user="root" password="YLf{}cJ,Kvd%"/>--%>
                        <sql:setDataSource var="db"
                                           driver="com.mysql.jdbc.Driver"
                                           url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                           user="oferta_root" password="mnbvcxz00A!"/>
                        <sql:query dataSource="${db}" var="comment">
                            SELECT * FROM bankscontroller ;
                        </sql:query>
                        <c:forEach var="CommentsDao" items="${comment.rows}" varStatus="loop">
                        <c:set value="${CommentsDao.bankId}" var="bankIdSql"/>
                        <c:if test="${bankIdSql == bankId}">
                        <div class="bg" style="background-image: url('../../../uploadCard/${CommentsDao.bankBigLogo}')">
                            </c:if>
                            </c:forEach>
                            <div class="top">
                                <div class="row-md row-flex flex align-items-center">
                                    <div class="col-9">
                                        <div class="item-logo">
                                            <a href="">
                                                <img src="<%=request.getContextPath()%>/uploadCard/${Details.cardImage}"
                                                     alt="">
                                            </a>
                                        </div>
                                        <p class="font-36">${Details.cardName}</p>

                                        <c:set var="productCode" value="${Details.productCode}"/>
                                            <%--  <sql:setDataSource var="db"
                                                                                                                      driver="com.mysql.jdbc.Driver"
                                                                                                                      url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                                                      user="root" password="YLf{}cJ,Kvd%"/>--%>
                                        <sql:setDataSource var="db2"
                                                           driver="com.mysql.jdbc.Driver"
                                                           url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                           user="oferta_root" password="mnbvcxz00A!"/>
                                        <sql:query dataSource="${db2}" var="comment2">
                                            SELECT * FROM productnamecontroller ;
                                        </sql:query>
                                        <c:forEach var="CommentsDao2" items="${comment.rows}" varStatus="loop">
                                            <c:set value="${CommentsDao2.productCode}" var="productCodeSql"/>
                                            <c:if test="${productCodeSql == productCode}">
                                                <p class="font-16">${CommentsDao2.productNameAm}</p>
                                            </c:if>
                                        </c:forEach>
                                        <a href="${Details.bankLink}"
                                           class="def-button btn-green with-shadow margin-top-15 max-width-160" target="_blank">Անցնել
                                            բանկի էջ</a>
                                    </div>
                                    <div class="col-3 col">
                                        <div class="card-img"
                                             style="background-image: url('<%=request.getContextPath()%>/uploadCard/${Details.cardImage}')"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row-md row-flex flex flex-wrap credit-info">
                        <div class="col-4 flex align-items-center item">
                            <span>Սպասարկման վճար</span><span>${Details.cardMaxServiceFee} </span>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <span>Արտոնյալ ժամանակաշրջան</span><span>${Details.cardGracePeriod} օր</span>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <a href="" class="blue-link width-icon right-icon"><span>Զեղչ խանութներում</span> <img
                                    src="../images/pdf.svg"></a>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <span><a href="" class="blue-link">Քեշ բեք</a></span>
                            <span>1-10% <img src="../images/pdf.svg"></span>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <span>Տոկոսը դրական մնացորդի վրա</span><span>${Details.cardPerMaxDiscount} %</span>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <span>Վարկառուի տարիքը</span><span>${Details.minAge} +</span>
                        </div>
                        <div class="col-4 flex align-items-center item">
                            <span>Վարկային գիծ</span><span>${Details.cardMaxCreditLimit} </span>
                        </div>
                        <div class="col-4 flex align-items-center item"></div>
                        <div class="col-4 flex align-items-center item">
                            <span>Նախնական որոշում</span><span>1 ժամ</span>
                        </div>
                    </div>
                    <div class="row-md clearfix margin-top-30">
                    <div class="col-12">
                    <c:set var="commentscontroller" value="${Details.productCode}"/>
                    <%--  <sql:setDataSource var="db"
                                                                                                                  driver="com.mysql.jdbc.Driver"
                                                                                                                  url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                                                                                                  user="root" password="YLf{}cJ,Kvd%"/>--%>
                    <sql:setDataSource var="db3"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="oferta_root" password="mnbvcxz00A!"/>
                    <sql:query dataSource="${db3}" var="comment3">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <c:forEach var="CommentsDao3" items="${comment.rows}" varStatus="loop">
                        <c:set value="${CommentsDao3.productCode}" var="productCodeSql"/>
                        <c:if test="${productCodeSql == productCode}">
                            <p class="font-16 line-height-24">${comment3.Comment1_Am}</p>
                            <%-- <p class="font-16 line-height-24">${comment3.Comment2_Am}</p>
                             <p class="font-16 line-height-24">${comment3.Comment3_Am}</p>--%>
                        </c:if>
                        </div>
                        </div>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </div>
    </div>


</div>
<jsp:include page="include/Footer.jsp"/>
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


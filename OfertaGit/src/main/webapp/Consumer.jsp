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
<%--
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/Compare.js"></script>--%>

<script>/// <reference path="jquery-1.12.3.js" />
(function ($) {
    var list = [];
    /* function to be executed when product is selected for comparision*/
    $(document).on('click', '.addToCompare', function () {
        $(".comparePanle").show();
        $(this).toggleClass("rotateBtn");
        $(this).parents(".selectProduct").toggleClass("selected");
        var productID = $(this).parents('.selectProduct').attr('data-title');
        var inArray = $.inArray(productID, list);
        if (inArray < 0) {
            if (list.length > 2) {
                $("#WarningModal").show();
                $("#warningModalClose").click(function () {
                    $("#WarningModal").hide();
                });
                $(this).toggleClass("rotateBtn");
                $(this).parents(".selectProduct").toggleClass("selected");
                return;
            }
            if (list.length < 3) {
                list.push(productID);
                var displayTitle = $(this).parents('.selectProduct').attr('data-id');
                var image = $(this).siblings(".productImg").attr('src');
                $(".comparePan").append('<div id="' + productID + '" class="relPos titleMargin w3-margin-bottom   w3-col l3 m4 s4"><div class="w3-white titleMargin"><a class="selectedItemCloseBtn w3-closebtn cursor">&times</a><img src="' + image + '" alt="image" style="height:100px;"/><p id="' + productID + '" class="titleMargin1">' + displayTitle + '</p></div></div>');
            }
        } else {
            list.splice($.inArray(productID, list), 1);
            var prod = productID.replace(" ", "");
            $('#' + prod).remove();
            hideComparePanel();
        }
        if (list.length > 1) {
            $(".cmprBtn").addClass("active");
            $(".cmprBtn").removeAttr('disabled');
        } else {
            $(".cmprBtn").removeClass("active");
            $(".cmprBtn").attr('disabled', '');
        }
    });
    /*function to be executed when compare button is clicked*/
    $(document).on('click', '.cmprBtn', function () {
        if ($(".cmprBtn").hasClass("active")) {
            /* this is to print the  features list statically*/
            $(".contentPop").append('<div class="w3-col s3 m3 l3 compareItemParent relPos">' + '<ul class="product">' + '<li class=" relPos compHeader"><p class="w3-display-middle">Features</p></li>' + '<li>Title</li>' + '<li>Size</li>' + '<li>Weight</li>' + '<li class="cpu">Processor</li>' + '<li>Battery</li></ul>' + '</div>');
            for (var i = 0; i < list.length; i++) {
                /* this is to add the items to popup which are selected for comparision */
                product = $('.selectProduct[data-title="' + list[i] + '"]');
                var image = $('[data-title=' + list[i] + ']').find(".productImg").attr('src');
                var title = $('[data-title=' + list[i] + ']').attr('data-id');
                /*appending to div*/
                $(".contentPop").append('<div class="w3-col s3 m3 l3 compareItemParent relPos">' + '<ul class="product">' + '<li class="compHeader"><img src="' + image + '" class="compareThumb"></li>' + '<li>' + title + '</li>' + '<li>' + $(product).data('size') + '</li>' + '<li>' + $(product).data('weight') + '<li class="cpu">' + $(product).data('processor') + '</li>' + '<li>' + $(product).data('battery') + '</ul>' + '</div>');
            }
        }
        $(".modPos").show();
    });
    /* function to close the comparision popup */
    $(document).on('click', '.closeBtn', function () {
        $(".contentPop").empty();
        $(".comparePan").empty();
        $(".comparePanle").hide();
        $(".modPos").hide();
        $(".selectProduct").removeClass("selected");
        $(".cmprBtn").attr('disabled', '');
        list.length = 0;
        $(".rotateBtn").toggleClass("rotateBtn");
    });
    /*function to remove item from preview panel*/
    $(document).on('click', '.selectedItemCloseBtn', function () {
        var test = $(this).siblings("p").attr('id');
        $('[data-title=' + test + ']').find(".addToCompare").click();
        hideComparePanel();
    });
    function hideComparePanel() {
        if (!list.length) {
            $(".comparePan").empty();
            $(".comparePanle").hide();
        }
    }
})(jQuery);
</script>
<script>
    $(function () {
        $(".green-link").on("click", function () {
            $(this).parents('tr.view').toggleClass("open").next(".fold").toggleClass("open");
        });
    });
</script>

<style>
    @import url("https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css");
    .pcs:after {
    }
    .cur:before {
    }
    .per:after {
    }
    /** {
        box-sizing: border-box;
    }*/
    /* body {
         padding: .2em 2em;
     }*/
    table {
        /* width: 100%;*/
    }
    table th {
        /*text-align: left;*/
        /* border-bottom: 1px solid #ccc;*/
    }
    table th, table td {
        /* padding: .4em;*/
    }
    table.fold-table > tbody > tr.view td, table.fold-table > tbody > tr.view th {
        cursor: pointer;
    }
    table.fold-table > tbody > tr.view td:first-child,
    table.fold-table > tbody > tr.view th:first-child {
        /*  position: relative;*/
        /* padding-left: 20px;*/
    }
    table.fold-table > tbody > tr.view td:first-child:before,
    table.fold-table > tbody > tr.view th:first-child:before {
        /* position: absolute;
         top: 50%;
         left: 5px;
         width: 9px;
         height: 16px;
         margin-top: -8px;
         font: 16px fontawesome;
         color: #999;
         content: "\f0d7";
         transition: all .3s ease;*/
    }
    table.fold-table > tbody > tr.view:nth-child(4n-1) {
        /* background: #eee;*/
    }
    table.fold-table > tbody > tr.view:hover {
        /* background: #000;*/
    }
    table.fold-table > tbody > tr.view.open {
        /*  background: tomato;
          color: white;*/
    }
    table.fold-table > tbody > tr.view.open td:first-child:before, table.fold-table > tbody > tr.view.open th:first-child:before {
        transform: rotate(-180deg);
        /*  color: #333;*/
    }
    table.fold-table > tbody > tr.fold {
        display: none;
    }
    table.fold-table > tbody > tr.fold.open {
        display: table-row;
    }
    .fold-content {
        /* padding: .5em;*/
    }
    .fold-content h3 {
        margin-top: 0;
    }
    .fold-content > table {
        /* border: 2px solid #ccc;*/
    }
    .fold-content > table > tbody tr:nth-child(even) {
        /* background: #eee;*/
    }
</style>

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
                                class="icon-state"></i> ${requestScope.City}  </span>
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
                        <c:if test="${requestScope.comparListConsumer != null}">
                            <c:forEach var="size" items="${requestScope.comparListConsumer}" varStatus="TheCount">
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
                            </form>
                            <c:if test="${requestScope.comparListDeposit != null}">
                            <ul>
                                <li >
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span  onclick="document.DepositComparesmall.submit();">Ավանդ</span>
                                    <span class="bold font-14">${counterDeposit}</span>
                                     <i class="icon-delete" onclick="document.Deletesmall.submit();"></i>

                                    <form action="DepositClient" method="post" name="Deletesmall">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                         <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteHipoteksmall.submit();"></i>
                                </li>
                            <form action="DepositClient" method="post" name="DeleteHipoteksmall">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                 <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                                     <span class="bold font-14">${counterCompare}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteConsumersmall.submit();"></i>
                                </li>
                             <form action="DepositClient" method="post" name="DeleteConsumersmall">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                        <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                            </ul>
                                <form action="DepositClient" method="post" name="DeleteCarsmall">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                    </form>
                            </c:if>
                            <%--<form action="CompareMicro" method="post" name="MicroComparesmall">
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
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteMicro.submit();"></i>
                                </li>
                           <form action="DepositClient" method="post" name="DeleteMicro">
                                        <input type="hidden" name="pageNameToDelete" value="ՄԻԿՐՈՎԱՐԿ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
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
                                         <i type="submit" class="icon-delete" onclick="document.DeleteAGsmall.submit();"></i>
                                </li>
                            <form action="DepositClient" method="post" name="DeleteAGsmall">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                 <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                    </div>
                    <!-- starting compare Section -->

                    <%--   <%
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
                        <c:if test="${requestScope.comparListConsumer != null}">
                            <c:forEach var="size" items="${requestScope.comparListConsumer}" varStatus="TheCount">
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
                                <li >
                                    <c:forEach var="DepositCompare" items="${requestScope.comparListDeposit}"
                                               varStatus="TheCount">
                                        <c:set var="counterDeposit" value="${TheCount.count}" scope="request"/>

                                    </c:forEach>
                                     <span  onclick="document.DepositCompare.submit();">Ավանդ</span>
                                    <span class="bold font-14">${counterDeposit}</span>
                                     <i class="icon-delete" onclick="document.Delete.submit();"></i>

                                    <form action="DepositClient" method="post" name="Delete">
                                        <input type="hidden" name="pageNameToDelete" value="Ավանդ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                         <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                                     <span class="bold font-14">${counterMortgag}</span>
                                        <i type="submit" class="icon-delete" onclick="document.DeleteHipotek.submit();"></i>
                                </li>
                            <form action="DepositClient" method="post" name="DeleteHipotek">
                                        <input type="hidden" name="pageNameToDelete" value="Հիփոթեք">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                 <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                                     <span class="bold font-14">${counterCompare}</span>
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteConsumer.submit();"></i>
                                </li>
                             <form action="DepositClient" method="post" name="DeleteConsumer">
                                        <input type="hidden" name="pageNameToDelete" value="Սպարողական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                  <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteCar.submit();"></i>
                                </li>
                            </ul>
                                <form action="DepositClient" method="post" name="DeleteCar">
                                        <input type="hidden" name="pageNameToDelete" value="Ավտովարկ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                     <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
                                    </form>
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
                                        <i type="submit" class="icon-delete"
                                           onclick="document.DeleteMicro.submit();"></i>
                                </li>
                           <form action="DepositClient" method="post" name="DeleteMicro">
                                        <input type="hidden" name="pageNameToDelete" value="ՄԻԿՐՈՎԱՐԿ">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
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
                            <form action="DepositClient" method="post" name="DeleteAG">
                                        <input type="hidden" name="pageNameToDelete" value="Գյուղատնտեսական">
                                        <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>"
                                               type="hidden">
                                        <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                        <input name="Currancy" value="<%=request.getParameter("Currancy")%>"
                                               type="hidden">
                                        <input name="PageToGo" value="ConsumerClient"
                                               type="hidden">
                                        <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                 <input name="months" value="<%=request.getAttribute("months")%>" type="hidden">
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
        <div class="bg" style="background-image: url('../images/1.jpg')">
            <div class="inner-container info-container">
                <div class="row-md clearfix margin-bottom-0">
                    <div class="text-box col-5">
                        <p class="font-32">Սպառողական վարկեր</p>
                        <p>Մենք կօգնենք Ձեզ գտնել <br/>ամենաշահավետ տարբերակը</p>
                    </div>
                </div>
                <div>

                </div>
                <div class="row-md clearfix">
                    <form name="main" action="ConsumerCalculate" method="post">
                        <div class="col-6 large col">
                            <span class="label">Մուտքագրեք վարկի գումարը</span>
                            <div class="range-group">
                                <div class="def-range">
                                    <script language="javascript" type="text/javascript">
                                        var delayTimer;
                                        function doSearch(text) {
                                            clearTimeout(delayTimer);
                                            delayTimer = setTimeout(function () {
                                                document.main.submit()
                                            }, 2000); // Will do the ajax stuff after 1000 ms, or 1 s
                                        }
                                    </script>

                                    <c:if test="${requestScope.Amount!=null}">
                                        <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                   var="dropDownList2"
                                                   varStatus="loop">

                                            <output style="display: none;" id="outputer"></output>

                                            <input type="text" name="value" min="${dropDownList2.minAmount}"
                                                   max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                   value="${requestScope.Amountfiltered}"
                                                   oninput="showVal(this.value)"
                                                   onchange="showVal(this.value)" id="amount" inputmode="numeric">

                                            <input type="range" name="range" min="${dropDownList2.minAmount}"
                                                   max="${dropDownList2.maxAmount}" step="${dropDownList2.steps}"
                                                   value="${requestScope.Amountfiltered}" data-rangeslider=""
                                                   id="amount_range"
                                                   style="position: absolute; width: 1px; height: 1px; overflow: hidden; opacity: 0;"
                                                   onchange="doSearch();">

                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${requestScope.Amount==null}">
                                        <c:forEach items="${requestScope.dropDownsListWithCurrancy}"
                                                   var="dropDownList3"
                                                   varStatus="loop">

                                            <output style="display: none;" id="outputer"></output>

                                            <input type="text" name="value" min="${dropDownList3.minAmount}"
                                                   max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                                   value="${requestScope.Amountfiltered}"
                                                   oninput="showVal(this.value)"
                                                   onchange="showVal(this.value)" id="amount" inputmode="numeric">

                                            <input type="range" name="range" min="${dropDownList3.minAmount}"
                                                   max="${dropDownList3.maxAmount}" step="${dropDownList3.steps}"
                                                   value="${requestScope.Amountfiltered}" data-rangeslider
                                                   id="amount_range" onchange="doSearch();">


                                            <input type="hidden" name="value_url" id="value_amount_url">

                                        </c:forEach>
                                    </c:if>

                                </div>
                                <div class="def-select-box">

                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'AMD'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD" selected>֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'USD'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD" selected>$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'EUR'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR" selected>€</option>
                                                <option value="RUB">₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                    <c:set var="AMD" value="${requestScope.PageCurrancy }"/>
                                    <c:choose>
                                        <c:when test="${AMD == 'RUB'}">
                                            <select name="Currancy" onchange="document.main.submit();">
                                                <option value="AMD">֏</option>
                                                <option value="USD">$</option>
                                                <option value="EUR">€</option>
                                                <option value="RUB" selected>₽</option>
                                            </select>
                                        </c:when>
                                    </c:choose>


                                </div>
                            </div>
                        </div>
                        <div class="col-4 middle col">
                            <span class="label">Ժամկետը</span>
                            <div class="def-select-box">

                                <select name="months" id="select_month" onchange="document.main.submit();">
                                    <option value="0">Բոլորը</option>
                                    <option value="1">1 ամիս</option>
                                    <option value="3">3 ամիս</option>
                                    <option value="6">6 ամիս</option>
                                    <option value="9">9 ամիս</option>
                                    <option value="12">12 ամիս</option>
                                    <option value="18">18 ամիս</option>
                                    <option value="24">24 ամիս</option>
                                    <option value="36">36 ամիս</option>
                                    <option value="48">48 ամիս</option>
                                    <option value="60">60 ամիս</option>
                                </select>

                                <%-- <input type="hidden" name="select_value" id="select_month_value" value="1">--%>
                                <input type="hidden" name="City" value='<%=request.getAttribute("city")%>'>


                            </div>
                        </div>
                        <div class="col-2 small col">
                            <button class="def-button btn-green">
                                <span>Գտնել  </span><input type="hidden" name="MaxAmounr"
                                                           value="<%=request.getParameter("MaxAmounr")%>">
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="padding-bt-60 bg-white-dark">
        <div class="inner-container">
            <div class="bookmarked-items loan-items-container  margin-bottom-40">
                <c:if test="${requestScope.depositeAseList != null}">
                    <c:forEach items="${requestScope.depositeAseList}" var="SpecialConsumer">

                        <div class="table">
                            <div class="table-row">
                                <div class="table-cell item-img">

                                    <img src="${SpecialConsumer.banksList}"/>
                                </div>
                                <div class="table-cell">
                                    <c:out value="${SpecialConsumer.productName}"/><br>
                                    <p class="font-28 bold">${SpecialConsumer.CCFactual}%</p>
                                    <small>№ ${SpecialConsumer.productCode}</small>

                                </div>
                                <div class="table-cell">
                                    <p>Գումար</p>
                                        <%-- Մինչեւ ${SpecialConsumer.CCMaxPeriodMonth} Ամիս--%>
                                    <c:set value="${requestScope.PageCurrancy}" var="curancy"/>
                                    <c:choose>
                                        <c:when test="${curancy == 'AMD'}">
                                            <p class="font-24 bold"> <fmt:formatNumber type="number"
                                                                                       maxFractionDigits="3"
                                                                                       value="${SpecialConsumer.CCMinAmount}"/>
                                                - <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                    value="${SpecialConsumer.CCMaxAmount}"/></p>

                                        </c:when>
                                        <c:when test="${curancy == 'USD'}">
                                            <p class="font-24 bold"> <fmt:formatNumber type="number"
                                                                                       maxFractionDigits="3"
                                                                                       value="${SpecialConsumer.CCMinLoan}"/>
                                                - <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                    value="${SpecialConsumer.CCMaxLoan}"/></p>
                                        </c:when>
                                        <c:when test="${curancy == 'RUB'}">
                                            <p class="font-24 bold"> <fmt:formatNumber type="number"
                                                                                       maxFractionDigits="3"
                                                                                       value="${SpecialConsumer.CCMinLoan}"/>
                                                - <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                    value="${SpecialConsumer.CCMaxLoan}"/></p>
                                        </c:when>
                                        <c:when test="${curancy == 'EUR'}">
                                            <p class="font-24 bold"> <fmt:formatNumber type="number"
                                                                                       maxFractionDigits="3"
                                                                                       value="${SpecialConsumer.CCMinLoan}"/>
                                                - <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                    value="${SpecialConsumer.CCMaxLoan}"/></p>
                                        </c:when>
                                    </c:choose>


                                </div>
                                <div class="table-cell">
                                    <c:out value="${CommentsDao.comment1_Am}"/>
                                </div>
                                <div class="table-cell text-center">
                                    <c:set value="${SpecialConsumer.sendRequest}" var="isThere"/>
                                    <c:choose>
                                    <c:when test="${isThere == 1}">
                                    <c:set var="sendRequest" value="${SpecialConsumer.sendRequest}"/>
                                    <c:set var="gotoPage" value="${SpecialConsumer.gotoPage}"/>
                                    <c:set var="lastLogic" value="${SpecialConsumer.lastlogic}"/>
                                    <c:choose>
                                    <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic== 0}">
                                    <a href="CreditSend?ProductCode=${SpecialConsumer.productCode}&&Per=${SpecialConsumer.CCFactual}&&months=0&&Amount=<%=request.getAttribute("range")%>&&PageToGo=Consumer&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&value_two=<%=request.getAttribute("minAmount")%>&&range_two=30"
                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ ավելին</a>
                                    <p>
                                        <a href="ConsumerClient?id=${SpecialConsumer.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                           class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                        </c:when>

                                        <c:when test="${gotoPage == 0 && sendRequest== 0 && lastLogic==1}">
                                        <a href="${SpecialConsumer.bankLink}"
                                           class="def-button btn-green with-shadow  margin-bottom-15" target="_blank" >Անցնել էջ</a>
                                    <p><a href="ConsumerClient?id=${SpecialConsumer.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                          class="blue-link font-12 linkScrollDown">Համեմատել</a>

                                        </c:when>
                                        </c:choose>

                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="sendRequest" value="${SpecialConsumer.sendRequest}"/>
                                            <c:set var="gotoPage" value="${SpecialConsumer.gotoPage}"/>
                                            <c:set var="lastLogic" value="${SpecialConsumer.lastlogic}"/>
                                        <c:choose>
                                        <c:when test="${gotoPage == 1 || sendRequest== 1}">
                                        <a href="CreditSend?ProductCode=${SpecialConsumer.productCode}&&Per=${SpecialConsumer.CCFactual}&&months=0&&Amount=<%=request.getAttribute("range")%>&&PageToGo=Consumer&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&value_two=<%=request.getAttribute("minAmount")%>&&range_two=30"
                                           class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                            ավելին</a>
                                    <p><a href="ConsumerClient?id=${SpecialConsumer.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                          class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                        </c:when>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${gotoPage == 0 && sendRequest==0 && lastLogic==1}">
                                        <a href="${SpecialConsumer.bankLink}"
                                           class="def-button btn-green with-shadow  margin-bottom-15" target="_blank">Անցնել էջ</a>
                                    <p><a href="ConsumerClient?id=${SpecialConsumer.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                          class="blue-link font-12 linkScrollDown">Համեմատել</a>

                                        </c:when>
                                        </c:choose>
                                        </c:otherwise>
                                        </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>


            <!-- Testing section Start -->
            <div class="loan-items-container">
                <div class="table hide-for-mb">
                    <div class="table-row head sorting">
                        <div class="table-cell"><span>Բանկ</span></div>
                        <%--  <div class="table-cell"><span>Ավանդի անունը</span></div>--%>
                        <div class="table-cell">
                            <span>Տոկոս</span>
                            <c:set value="${requestScope.arrow}" var="arrow"/>
                            <c:choose>
                                <c:when test="${arrow == 0}">
                                    <i class="icon-polygon-down "
                                       onclick="document.SortingPercentageDesc.submit();"></i>
                                    <i class="icon-polygon-up active "></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="icon-polygon-down active"></i>
                                    <i class="icon-polygon-up " onclick="document.SortingPercentageAsec.submit();"></i>

                                </c:otherwise>
                            </c:choose>
                            <form action="ConsumerClient" method="post" name="SortingPercentageDesc">

                                <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                <input name="sorting" value="DescPercent" type="hidden">

                            </form>
                            <form action="ConsumerClient" method="post" name="SortingPercentageAsec">

                                <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                <input name="sorting" value="AsecPercent" type="hidden">
                            </form>

                        </div>
                        <div class="table-cell">
                            <c:set value="${requestScope.arrow2}" var="arrow2"/>
                            <c:choose>
                                <c:when test="${arrow2 == 0}">
                                    <i class="icon-polygon-down "
                                       onclick="document.SortingPercentageDescAmount.submit();"></i>
                                    <i class="icon-polygon-up active "></i>
                                </c:when>
                                <c:otherwise>

                                    <i class="icon-polygon-down active"></i>
                                    <i class="icon-polygon-up "
                                       onclick="document.SortingPercentageAsecAmount.submit();"></i>

                                </c:otherwise>
                            </c:choose>
                            <span>Գումար</span>
                            <form action="ConsumerClient" method="post" name="SortingPercentageDescAmount">

                                <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                <input name="sorting" value="DescAmount" type="hidden">

                            </form>
                            <form action="ConsumerClient" method="post" name="SortingPercentageAsecAmount">

                                <input name="MaxAmounr" value="<%=request.getParameter("MaxAmounr")%>" type="hidden">
                                <input name="City" value="<%=request.getParameter("City")%>" type="hidden">
                                <input name="Currancy" value="<%=request.getParameter("Currancy")%>" type="hidden">
                                <input name="PageToGo" value="<%=request.getParameter("PageToGo")%>" type="hidden">
                                <input name="Amount" value="<%=request.getAttribute("range")%>" type="hidden">
                                <input name="sorting" value="AsecAmount" type="hidden">
                            </form>
                        </div>
                        <div class="table-cell"><span>Պահանջներ</span></div>
                        <div class="table-cell"></div>
                    </div>
                </div>

                <div class="table">
                    <div class="table-row ">
                        <div class="table-cell item-img">


                            <c:if test="${requestScope.depositAllInRage != null}">

                                <c:forEach var="firstDepo" items="${requestScope.depositAllInRage}" varStatus="loop">

                                    <table class="fold-table">
                                        <tbody>
                                        <tr class="view">
                                            <td>
                                                <!-- first section -->
                                                <div class="table">
                                                    <div class="table-row">

                                                        <div class="table-cell item-img">
                                                            <img src='${firstDepo.bankBigLogo}'/>
                                                            <p class="font-12 bold"></p>
                                                            <p class="margin-top-20 hide-for-mb">
                                                                <a class="green-link font-12 width-icon right-icon ellipsis">
                                                                    <%! int count = 0; %>
                                                                    <c:forEach items="${requestScope.depositAllInSubRage}" var="seconDepoCount">

                                                                        <c:if test="${seconDepoCount.bankId == firstDepo.bankId}">
                                                                            <c:set var="TheCounter" value="${TheCount.count}" scope="request"/>
                                                                            <% count++; %>
                                                                        </c:if>

                                                                    </c:forEach>
                                                                    <% if (count > 0) {%>
                                                                    <span>ևս <%= Math.round(count) %> առաջարկ</span>
                                                                    <%  count = 0;  } else if(count == 0) {%>
                                                                    <span></span>
                                                                    <% }%>
                                                                    <% count = 0; %>
                                                                </a>
                                                            </p>
                                                        </div>
                                                        <div class="table-cell">
                                                            <c:out value="${firstDepo.productName}"/><br>
                                                            <p class="font-28 bold">${firstDepo.CCFactual}%</p>
                                                            <small>№ ${firstDepo.productCode}</small>
                                                        </div>

                                                        <div class="table-cell">
                                                            <p class="font-22 bold">

                                                            <p class="font-24 bold"><fmt:formatNumber type="number"
                                                                                                      maxFractionDigits="3"
                                                                                                      value="${firstDepo.CCMinAmount}"/>
                                                                - <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                                    value="${firstDepo.CCMaxAmount}"/></p>

                                                        </div>


                                                        <div class="table-cell">
                                                            <p>
                                                                <c:out value="${firstDepo.comment1_Am}"/>

                                                            </p>
                                                        </div>
                                                        <div class="table-cell text-center last">
                                                            <p class="margin-bottom-20 show-for-mb text-left">
                                                                <a class="green-link font-12 width-icon right-icon ellipsis">
                                                                    <span>Ավելի Առաջարկներ</span> <i
                                                                        class="icon-arrow-down"></i>
                                                                </a>
                                                            </p>
                                                            <c:set var="sendRequest" value="${firstDepo.sendRequest}"/>
                                                            <c:set var="gotoPage" value="${firstDepo.gotoPage}"/>
                                                            <c:set var="lastLogic" value="${firstDepo.lastlogic}"/>
                                                            <c:choose>
                                                                <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic == 0}">
                                                                    <a href="CreditSend?ProductCode=${firstDepo.productCode}&&Per=${SpecialConsumer.CCFactual}&&months=0&&Amount=<%=request.getAttribute("range")%>&&PageToGo=Consumer&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&value_two=<%=request.getAttribute("minAmount")%>&&range_two=30"
                                                                       class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                                                        ավելին</a>
                                                                    <a href="ConsumerClient?id=${firstDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                                                       class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                    <%--  <p><a href="index.php?page=compare/deposit"
                                                                            class="blue-link font-12">Համեմատել</a></p>--%>

                                                                </c:when>
                                                                <c:when test="${gotoPage == 0 && sendRequest==0  && lastLogic == 1}">
                                                                    <a href="${firstDepo.bankLink}"
                                                                       class="def-button btn-green with-shadow  margin-bottom-15" target="_blank">Անցնել
                                                                        էջ</a>
                                                                    <a href="ConsumerClient?id=${firstDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
                                                                       class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                    <%--<p><a href="index.php?page=compare/deposit"
                                                                          class="blue-link font-12">Համեմատել</a></p>--%>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="ConsumerClient?id=${firstDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Deposit"
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


                                        <tr class="fold">
                                            <td colspan="7">
                                                <div class="fold-content">
                                                    <table>
                                                        <tbody>
                                                        <tr>
                                                            <td>
                                                                <c:forEach items="${requestScope.depositAllInSubRage}" var="seconDepo">

                                                                    <c:if test="${seconDepo.bankId == firstDepo.bankId }">
                                                                        <div class="table">
                                                                            <div class="table-row">
                                                                                <div class="table-cell item-img">
                                                                                    <img src="${seconDepo.bankBigLogo}"/>
                                                                                </div>
                                                                                <div class="table-cell">
                                                                                    <c:out value="${seconDepo.productName}"/><br>
                                                                                    <p class="font-28 bold">${seconDepo.CCFactual}%</p>
                                                                                    <small>№ ${seconDepo.productCode}</small>
                                                                                </div>
                                                                                <div class="table-cell">
                                                                                        <%-- <p>Մինչեւ ${seconDepo.CCMaxPeriodMonths} Ամիս</p>--%>
                                                                                    <p class="font-24 bold">
                                                                                        <fmt:formatNumber
                                                                                                type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${seconDepo.CCMinAmount}"/>
                                                                                        - <fmt:formatNumber
                                                                                            type="number"
                                                                                            maxFractionDigits="3"
                                                                                            value="${seconDepo.CCMaxAmount}"/></p>
                                                                                </div>
                                                                                <div class="table-cell">
                                                                                    <c:out value="${seconDepo.comment1_Am}"/>
                                                                                </div>
                                                                                <div class="table-cell text-center">
                                                                                    <c:set var="sendRequest"
                                                                                           value="${seconDepo.sendRequest}"/>
                                                                                    <c:set var="gotoPage"
                                                                                           value="${seconDepo.gotoPage}"/>
                                                                                    <c:set var="lastLogic"
                                                                                           value="${seconDepo.lastlogic}"/>
                                                                                    <c:choose>
                                                                                        <c:when test="${gotoPage == 1 || sendRequest== 1 && lastLogic == 0}">
                                                                                            <a href="CreditSend?ProductCode=${seconDepo.productCode}&&Per=${SpecialConsumer.CCFactual}&&months=0&&Amount=<%=request.getAttribute("range")%>&&PageToGo=Consumer&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&value_two=<%=request.getAttribute("minAmount")%>&&range_two=30"
                                                                                               class="def-button btn-green with-shadow  margin-bottom-15">Իմանալ
                                                                                                ավելին</a>

                                                                                            <a href="ConsumerClient?id=${seconDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Mortgage"
                                                                                               class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                            <%--<p>
                                                                                                <a href="index.php?page=compare/deposit"
                                                                                                   class="blue-link font-12">Համեմատել</a>
                                                                                            </p>--%>
                                                                                        </c:when>

                                                                                        <c:when test="${gotoPage == 0 && sendRequest==0  && lastLogic == 1}">
                                                                                            <a href="${seconDepo.bankLink}"
                                                                                               class="def-button btn-green with-shadow  margin-bottom-15" target="_blank">Անցնել
                                                                                                էջ</a>

                                                                                            <a href="ConsumerClient?id=${seconDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Mortgage"
                                                                                               class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                            <%-- <p>
                                                                                                 <a href="index.php?page=compare/deposit"
                                                                                                    class="blue-link font-12">Համեմատել</a>
                                                                                             </p>--%>
                                                                                        </c:when>
                                                                                        <c:otherwise>

                                                                                            <a href="ConsumerClient?id=${seconDepo.productCode}&&months=0&&MaxAmounr=<%=request.getParameter("MaxAmounr")%>&&City=<%=request.getParameter("City")%>&&Currancy=<%=request.getParameter("Currancy")%>&&PageToGo=<%=request.getParameter("PageToGo")%>&&Amount=<%=request.getAttribute("range")%>&&PageName=Mortgage"
                                                                                               class="blue-link font-12 linkScrollDown">Համեմատել</a>
                                                                                            <%-- <p>
                                                                                                 <a href="index.php?page=compare/deposit"
                                                                                                    class="blue-link font-12">Համեմատել</a>
                                                                                             </p>--%>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>


                                                                </c:forEach>

                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
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
                <!-- Testing section Ending -->

            </div>
            <div class="padding-bt-60">
                <div class="inner-container">
                    <p class="font-24 title margin-bottom-35">
                        Հատուկ առաջարկներ
                    </p>
                    <div class="tab-container">
                        <div class="tab-nav flex align-items-center flex-wrap">
						<span class="tab-link" onclick="openTabItem(event, 'deposit')" >
                           Ավանդներ</span>
                            <span class="tab-link" onclick="openTabItem(event, 'mortgage')">
                        Հիպոթեկային վարկ</span>
                            <span class="tab-link active" onclick="openTabItem(event, 'consumer-loan')" id="defaultOpen">
                   Սպառողական վարկ</span>
                            <span class="tab-link" onclick="openTabItem(event, 'car-loan')">
                    Ավտովարկ</span>
                            <span class="tab-link" onclick="openTabItem(event, 'agricultural')">
                    Գյուղատնտեսական</span>
                            <span class="tab-link" onclick="openTabItem(event, 'card')">
                   Քարտեր</span>
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
                var obj;
                obj = jQuery.parseJSON('${jsonArray}');
                if(obj.WorningMessage==="Դուք արդեն ունեք նույն ID-ին"){
                    alert("Դուք արդեն ունեք նույն ID-ին");
                    console.log(obj.WorningMessage);
                }else if(obj.WorningMessage==="Դուք չեք կարող 4 ից ավել համեմատել։"){
                    alert("Դուք չեք կարող 4 ից ավել համեմատել");
                    console.log(obj.WorningMessage);
                }else {
                    console.log(obj.WorningMessage);
                }
            </script>
</body>
</html>
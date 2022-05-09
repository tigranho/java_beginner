<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>

<head>
   <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
/*
            $('#search').autocomplete({
                source: '${pageContext.request.contextPath}/ajax'
            });*/

        });
    </script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/demos/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oferta.am</title>
    <link href="<%=request.getContextPath()%>/images/favicon.ico" rel="shortcut icon">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/global.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/libs/owl/owl.carousel.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

   <%-- <script src="../../js/jquery-1.9.1.min.js"></script>
    <script src="../../../js/Compare.js"></script>--%>
    <script>function validate() {
        $(document).on('keyup', 'input', function (e) {
            e.preventDefault();

            var search_text = $(this).val();

            var _data = {
                search_text: search_text

            };

            $.ajax({
                url: show_more_distributor_info,
                type: 'POST',
                data: _data,
                dataType: 'text',
                beforeSend: function () {
                },
                success: function (data) {
                    console.log(data);
                },
                error: function (data) {
                }
            });
        });
    </script>
</head>
<body>
<span class="close-btn" id="navCloseBtn"><i class="icon-close font-16"></i></span>
<div class="search-popup" id="searchPopup">
    <span class="close-btn hide-for-tablet" id="closeBtn"><i class="icon-close font-16"></i></span>
    <%-- <span class="state ellipsis show-for-tablet"><i class="icon-state"></i> </span>--%>

    <div class="inner">
        <p class="font-30 text-center uppercase hide-for-tablet">Որոնում</p>
        <form action="SearchPageApp" method="get" onsubmit="return validate();">
            <div class="def-input int-right-icon int-outline margin-top-20">
                <i class="icon-search"></i>
                <div class="col-10 middle col">

                    <input type="text" name="searchText" id="search" class="font-14" required>
                    <p id="demo"></p>
                    <input type="hidden" name="Pagelanguage" value="${requestScope.PageLanguage}">
                    <input type="hidden" name="Currancy" value="${requestScope.PageCurrancy}"/>

                </div>
                <div class="col-1-2 small col">
                    <input type="submit" value="Անցնել էջ" class="def-button btn-green" onclick="return validate();"/><br/><br/><br/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
</html>
<%-- --%>

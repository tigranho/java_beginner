<%--
  Created by IntelliJ IDEA.
  User: Asus / BeautyIt +374 98 22 98 98
  Date: 10/30/2019
  Time: 10:38 PM
  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:include page="include/google.jsp"/><title>Send Email To Bank</title>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Oferta.am</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${requestScope.getContextPath}/images/favicon.png" rel="shortcut icon">

    <link rel="stylesheet" href="fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="css/global.css" type="text/css">
    <link rel="stylesheet" href="libs/owl/owl.carousel.min.css"/>
    <link rel="stylesheet" href="css/style.css">

   <%-- <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/Compare.js"></script>--%>

</head>
<body>
<form action="CreditSend">
    <input type="hidden" value="sendingEmail" name="Email">
    <div class="col-8 right-side">
        <div class="row-md clearfix  form-row">
            <div class="col-6">
                <span class="label">Անուն</span>
                <div class="def-input int-outline">
                    <input type="text" name="userName" placeholder="" required/>
                </div>
            </div>
            <div class="col-6">
                <span class="label">Ազգանուն</span>
                <div class="def-input int-outline">
                    <input type="text" name="serName" placeholder="" required/>
                </div>
            </div>
        </div>
        <div class="row-md clearfix  form-row">
            <div class="col-6">
                <span class="label">Հեռախոս</span>
                <div class="def-input int-outline">
                    <input type="text" name="phone" placeholder="098 889 898" required
                           pattern="[0-9]{3}[0-9]{3}[0-9]{3}"/>
                </div>
            </div>
            <div class="col-6">
                <span class="label">Էլ. հասցե</span>
                <div class="def-input int-outline">
                    <input type="text" name="email" placeholder="" required
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
                </div>
            </div>
        </div>
        <div class="row-md clearfix  form-row">
            <div class="col-12">
                <div class="def-textarea txt-outline">
                    <span class="label">Հաղորդագրություն</span>
                    <textarea name="body" required></textarea>
                </div>
            </div>
        </div>
        <script>
            function isChecked(checkbox, sub1) {
                document.getElementById(sub1).disabled = !checkbox.checked;
            }
        </script>

        <div class="row-md clearfix  form-row">
            <div class="col-12">
                <label class="def-checkbox">
                    <input type="checkbox" id="termsChkbx " onchange="isChecked(this, 'sub1')"/>
                    <span><i class="icon-check"></i></span>
                    <span class="item-text">Ես համաձայն եմ անձնական տվյալների պահպանման և մշակման <a target="_blank" href="/Policy" class="blue-dark-link"> պայմանների </a> հետ</span>
                    <%
                        if (request.getAttribute("message") != null) {
                    %>
                    <%=request.getAttribute("message")%>
                    <%
                        }
                    %>
                </label>
            </div>
        </div>
        <div class="row-md clearfix  form-row button-row">
            <div class="col-12">
                <input type="submit" name="submit"
                       class="def-button btn-green with-shadow max-width-160 margin-top-5" id="sub1"
                       value="Ուղարկել հայտ" disabled="disabled"/>
                <input type="hidden" name="Bankid" value="${details.bankId}">
            </div>
        </div>
    </div>
</form>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/rangeslider.js/2.3.0/rangeslider.min.js'></script>
<script src="js/main.js"></script>
<script src="js/search.js"></script>
<script src="js/range.js"></script>
<script src="js/range_two.js"></script>
<script src="js/modal.js"></script>
<script src="libs/owl/owl.carousel.min.js"></script>
<script src="js/carousel.js"></script>
</html>

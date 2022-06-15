<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/13/2019
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:include page="../../include/google.jsp"/><meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Varking.am</title>
    <link href="../images/favicon.ico" rel="shortcut icon" >

    <link rel="stylesheet" href="../fonts/icomoon/style.css" type="text/css">
    <link rel="stylesheet" href="../css/global.css" type="text/css">
    <link rel="stylesheet" href="../libs/owl/owl.carousel.min.css" />
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>

</head>
<body id="body">
<div class="overlay" id="overlay"></div>
<div class="page-container">
    <div class="fixed-header">
        <jsp:include page="../inc/header.jsp"/>
        <jsp:include page="../inc/footer.jsp"/>

</div>

<script>
    $( function() {
        $( "#datepicker" ).datepicker({
            changeMonth: true,
            changeYear: true
        });
    } );
</script>
<script src="../js/main.js"></script>
<script src="../js/search.js"></script>
<script src="../js/range.js"></script>
<script src="../js/modal.js"></script>
<script src="../libs/owl/owl.carousel.min.js"></script>
<script src="../js/carousel.js"></script>


</body>
</html>

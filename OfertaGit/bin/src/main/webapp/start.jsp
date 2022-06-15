<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/13/2019
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
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
    <jsp:include page="include/google.jsp"/><meta http-equiv="refresh"
          content="0; URL='<%=request.getContextPath() %>/Oferia'" />
    <title>Insert title here</title>
</head>
<body>

</body>
</html>

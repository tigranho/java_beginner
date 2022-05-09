<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/23/2019
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:set value='<%=request.getParameter("Dram")%>' var="currancyCheck"/>
<c:set value='<%=request.getParameter("Cashback")%>' var="ChasBackTypeCheck"/>
<c:set value='<%=request.getParameter("Depit")%>' var="DepitTypeCheck"/>
<c:set value='<%=request.getParameter("Free")%>' var="FreeTypeCheck"/>
<c:set value='<%=request.getParameter("Period")%>' var="PeriodTypeCheck"/>
<c:set value='<%=request.getParameter("Credit")%>' var="CreditTypeCheck"/>

<c:choose>
    <c:when test="${currancyCheck == 'USD' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'depit' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdbdb" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdbdb}" var="rsrsrs">
            SELECT * FROM cardcontroller WHERE currancy = 'USD' AND cardtype ='Դեբետ';
        </sql:query>
        <c:forEach var="Carding" items="${rsrsrs.rows}">

            <div class="card-item-row">
                <div class="item-title">
                    <p class="font-18 bold">${Carding.cardname}</p>
                </div>
                <div class="flex align-items-center flex-wrap">
                    <div class="col">
                        <div class="item-img"
                             style="background-image: url('../uploadCard/${Carding.cardimage}')"></div>
                    </div>
                    <div class="item-info col">
                        <ul>
                            <li>
                                <span>Սպասարկման վճար</span>
                                <span class="bold">${Carding.cardmaxservicefee} դր.</span>
                            </li>
                            <li> <span>
                                                 <a href="" class="blue-link width-icon right-icon">
                                                <span>Քեշ բեք</span><img src="../images/pdf.svg">
                                                </a>
                                                </span>
                                <span class="bold">${Carding.mincashback}-${Carding.maxcashback} %</span>
                            </li>
                            <li>
                                <span>Քրեդիտային գիծ</span>
                                <span class="bold">${Carding.cardmaxcreditlimit} դր.</span>
                            </li>
                            <li>
                                <span>Տոկոս</span>
                                <span class="bold">${Carding.cardperfactual} %</span>
                            </li>
                            <li>
                                <span>Արտոնյալ ժամանակաշրջան</span>
                                <span class="bold">${Carding.cardgraceperiod} օր</span>
                            </li>
                            <li>
                                <span>Տոկոսը դրական մնացորդի վրա</span>
                                <span class="bold">${Carding.cardpermaxdiscount} %</span>
                            </li>
                        </ul>
                    </div>
                    <sql:setDataSource var="dbdbdb"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdbdb}" var="commentcommentcomment">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcommentcomment.rows}"
                                   varStatus="loop">
                            <c:set value="${CommentsDao.productCode}"
                                   var="productCodeSql"/>
                            <c:set value="${Carding.productCode}"
                                   var="productCode"/>
                            <c:if test="${productCode == productCodeSql }">
                                <p>${CommentsDao.Comment1_Am}</p>
                                <p>${CommentsDao.Comment2_Am}</p>
                                <p>${CommentsDao.Comment3_Am}</p>
                            </c:if>
                        </c:forEach>
                        <p><a href=""
                              class="blue-link width-icon right-icon"><span>Զեղչ խանութներում</span>
                            <img
                                    src="../images/pdf.svg"></a></p>
                    </div>
                    <div class="flex-item col">
                        <button class="def-button btn-green with-shadow margin-bottom-15">
                            Ուղարկել
                            հայտ
                        </button>
                        <p><a href="CardMoreDetailCardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
                            class="green-link font-12">Իմանալ
                            ավելին</a>

                            name="<%=request.getParameter("Currancy")%>"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:when>
</c:choose>


<c:set value='<%=request.getParameter("Dram")%>' var="currancyCheck"/>
<c:set value='<%=request.getParameter("Cashback")%>' var="ChasBackTypeCheck"/>
<c:set value='<%=request.getParameter("Depit")%>' var="DepitTypeCheck"/>
<c:set value='<%=request.getParameter("Free")%>' var="FreeTypeCheck"/>
<c:set value='<%=request.getParameter("Period")%>' var="PeriodTypeCheck"/>
<c:set value='<%=request.getParameter("Credit")%>' var="CreditTypeCheck"/>

<c:choose>
    <c:when test="${currancyCheck == 'USD' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'free' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdbdb2" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdbdb2}" var="rsrsrs2">
            SELECT * FROM cardcontroller WHERE currancy = 'USD' AND cardtype ='Անվճար';
        </sql:query>
        <c:forEach var="Carding" items="${rsrsrs2.rows}">

            <div class="card-item-row">
                <div class="item-title">
                    <p class="font-18 bold">${Carding.cardname}</p>
                </div>
                <div class="flex align-items-center flex-wrap">
                    <div class="col">
                        <div class="item-img"
                             style="background-image: url('../uploadCard/${Carding.cardimage}')"></div>
                    </div>
                    <div class="item-info col">
                        <ul>
                            <li>
                                <span>Սպասարկման վճար</span>
                                <span class="bold">${Carding.cardmaxservicefee} դր.</span>
                            </li>
                            <li> <span>
                                                 <a href="" class="blue-link width-icon right-icon">
                                                <span>Քեշ բեք</span><img src="../images/pdf.svg">
                                                </a>
                                                </span>
                                <span class="bold">${Carding.mincashback}-${Carding.maxcashback} %</span>
                            </li>
                            <li>
                                <span>Քրեդիտային գիծ</span>
                                <span class="bold">${Carding.cardmaxcreditlimit} դր.</span>
                            </li>
                            <li>
                                <span>Տոկոս</span>
                                <span class="bold">${Carding.cardperfactual} %</span>
                            </li>
                            <li>
                                <span>Արտոնյալ ժամանակաշրջան</span>
                                <span class="bold">${Carding.cardgraceperiod} օր</span>
                            </li>
                            <li>
                                <span>Տոկոսը դրական մնացորդի վրա</span>
                                <span class="bold">${Carding.cardpermaxdiscount} %</span>
                            </li>
                        </ul>
                    </div>
                    <sql:setDataSource var="dbdbdb2"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdbdb2}" var="commentcommentcomment2">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcommentcomment2.rows}"
                                   varStatus="loop">
                            <c:set value="${CommentsDao.productCode}"
                                   var="productCodeSql"/>
                            <c:set value="${Carding.productCode}"
                                   var="productCode"/>
                            <c:if test="${productCode == productCodeSql }">
                                <p>${CommentsDao.Comment1_Am}</p>
                                <p>${CommentsDao.Comment2_Am}</p>
                                <p>${CommentsDao.Comment3_Am}</p>
                            </c:if>
                        </c:forEach>
                        <p><a href=""
                              class="blue-link width-icon right-icon"><span>Զեղչ խանութներում</span>
                            <img
                                    src="../images/pdf.svg"></a></p>
                    </div>
                    <div class="flex-item col">
                        <button class="def-button btn-green with-shadow margin-bottom-15">
                            Ուղարկել
                            հայտ
                        </button>
                        <p><a href="CardMoreDetailCardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
                            class="green-link font-12">Իմանալ
                            ավելին</a>

                            name="<%=request.getParameter("Currancy")%>"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:when>
</c:choose>

<c:set value='<%=request.getParameter("Dram")%>' var="currancyCheck"/>
<c:set value='<%=request.getParameter("Cashback")%>' var="ChasBackTypeCheck"/>
<c:set value='<%=request.getParameter("Depit")%>' var="DepitTypeCheck"/>
<c:set value='<%=request.getParameter("Free")%>' var="FreeTypeCheck"/>
<c:set value='<%=request.getParameter("Period")%>' var="PeriodTypeCheck"/>
<c:set value='<%=request.getParameter("Credit")%>' var="CreditTypeCheck"/>

<c:choose>
    <c:when test="${currancyCheck == 'USD' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'Periods' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdbdb3" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdbdb3}" var="rsrsrs3">
            SELECT * FROM cardcontroller WHERE currancy = 'USD' AND cardtype ='Արտոնյալ ժամանակաշրջան';
        </sql:query>
        <c:forEach var="Carding" items="${rsrsrs3.rows}">

            <div class="card-item-row">
                <div class="item-title">
                    <p class="font-18 bold">${Carding.cardname}</p>
                </div>
                <div class="flex align-items-center flex-wrap">
                    <div class="col">
                        <div class="item-img"
                             style="background-image: url('../uploadCard/${Carding.cardimage}')"></div>
                    </div>
                    <div class="item-info col">
                        <ul>
                            <li>
                                <span>Սպասարկման վճար</span>
                                <span class="bold">${Carding.cardmaxservicefee} դր.</span>
                            </li>
                            <li> <span>
                                                 <a href="" class="blue-link width-icon right-icon">
                                                <span>Քեշ բեք</span><img src="../images/pdf.svg">
                                                </a>
                                                </span>
                                <span class="bold">${Carding.mincashback}-${Carding.maxcashback} %</span>
                            </li>
                            <li>
                                <span>Քրեդիտային գիծ</span>
                                <span class="bold">${Carding.cardmaxcreditlimit} դր.</span>
                            </li>
                            <li>
                                <span>Տոկոս</span>
                                <span class="bold">${Carding.cardperfactual} %</span>
                            </li>
                            <li>
                                <span>Արտոնյալ ժամանակաշրջան</span>
                                <span class="bold">${Carding.cardgraceperiod} օր</span>
                            </li>
                            <li>
                                <span>Տոկոսը դրական մնացորդի վրա</span>
                                <span class="bold">${Carding.cardpermaxdiscount} %</span>
                            </li>
                        </ul>
                    </div>
                    <sql:setDataSource var="dbdbdb3"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdbdb3}" var="commentcommentcomment3">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcommentcomment3.rows}"
                                   varStatus="loop">
                            <c:set value="${CommentsDao.productCode}"
                                   var="productCodeSql"/>
                            <c:set value="${Carding.productCode}"
                                   var="productCode"/>
                            <c:if test="${productCode == productCodeSql }">
                                <p>${CommentsDao.Comment1_Am}</p>
                                <p>${CommentsDao.Comment2_Am}</p>
                                <p>${CommentsDao.Comment3_Am}</p>
                            </c:if>
                        </c:forEach>
                        <p><a href=""
                              class="blue-link width-icon right-icon"><span>Զեղչ խանութներում</span>
                            <img
                                    src="../images/pdf.svg"></a></p>
                    </div>
                    <div class="flex-item col">
                        <button class="def-button btn-green with-shadow margin-bottom-15">
                            Ուղարկել
                            հայտ
                        </button>
                        <p><a href="CardMoreDetailCardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
                            class="green-link font-12">Իմանալ
                            ավելին</a>

                            name="<%=request.getParameter("Currancy")%>"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:when>
</c:choose>

<c:set value='<%=request.getParameter("Dram")%>' var="currancyCheck"/>
<c:set value='<%=request.getParameter("Cashback")%>' var="ChasBackTypeCheck"/>
<c:set value='<%=request.getParameter("Depit")%>' var="DepitTypeCheck"/>
<c:set value='<%=request.getParameter("Free")%>' var="FreeTypeCheck"/>
<c:set value='<%=request.getParameter("Period")%>' var="PeriodTypeCheck"/>
<c:set value='<%=request.getParameter("Credit")%>' var="CreditTypeCheck"/>

<c:choose>
    <c:when test="${currancyCheck == 'USD' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'crediting'}">
        <sql:setDataSource var="dbdbdb4" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdbdb4}" var="rsrsrs4">
            SELECT * FROM cardcontroller WHERE currancy = 'USD' AND cardtype ='Քրեդիտ';
        </sql:query>
        <c:forEach var="Carding" items="${rsrsrs4.rows}">

            <div class="card-item-row">
                <div class="item-title">
                    <p class="font-18 bold">${Carding.cardname}</p>
                </div>
                <div class="flex align-items-center flex-wrap">
                    <div class="col">
                        <div class="item-img"
                             style="background-image: url('../uploadCard/${Carding.cardimage}')"></div>
                    </div>
                    <div class="item-info col">
                        <ul>
                            <li>
                                <span>Սպասարկման վճար</span>
                                <span class="bold">${Carding.cardmaxservicefee} դր.</span>
                            </li>
                            <li> <span>
                                                 <a href="" class="blue-link width-icon right-icon">
                                                <span>Քեշ բեք</span><img src="../images/pdf.svg">
                                                </a>
                                                </span>
                                <span class="bold">${Carding.mincashback}-${Carding.maxcashback} %</span>
                            </li>
                            <li>
                                <span>Քրեդիտային գիծ</span>
                                <span class="bold">${Carding.cardmaxcreditlimit} դր.</span>
                            </li>
                            <li>
                                <span>Տոկոս</span>
                                <span class="bold">${Carding.cardperfactual} %</span>
                            </li>
                            <li>
                                <span>Արտոնյալ ժամանակաշրջան</span>
                                <span class="bold">${Carding.cardgraceperiod} օր</span>
                            </li>
                            <li>
                                <span>Տոկոսը դրական մնացորդի վրա</span>
                                <span class="bold">${Carding.cardpermaxdiscount} %</span>
                            </li>
                        </ul>
                    </div>
                    <sql:setDataSource var="dbdbdb4"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdbdb4}" var="commentcommentcomment4">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcommentcomment4.rows}"
                                   varStatus="loop">
                            <c:set value="${CommentsDao.productCode}"
                                   var="productCodeSql"/>
                            <c:set value="${Carding.productCode}"
                                   var="productCode"/>
                            <c:if test="${productCode == productCodeSql }">
                                <p>${CommentsDao.Comment1_Am}</p>
                                <p>${CommentsDao.Comment2_Am}</p>
                                <p>${CommentsDao.Comment3_Am}</p>
                            </c:if>
                        </c:forEach>
                        <p><a href=""
                              class="blue-link width-icon right-icon"><span>Զեղչ խանութներում</span>
                            <img
                                    src="../images/pdf.svg"></a></p>
                    </div>
                    <div class="flex-item col">
                        <button class="def-button btn-green with-shadow margin-bottom-15">
                            Ուղարկել
                            հայտ
                        </button>
                        <p><a href="CardMoreDetailCardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>"
                              class="green-link font-12">Իմանալ
                            ավելին</a>

                            name="<%=request.getParameter("Currancy")%>"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:when>
</c:choose>

<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/23/2019
  Time: 9:46 PM
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
    <c:when test="${currancyCheck == 'RUB' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'depit' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdb1" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdb1}" var="rsrs1">
            SELECT * FROM cardcontroller WHERE currancy = 'RUB' AND cardtype ='Դեբետ';
        </sql:query>
        <c:forEach var="Carding" items="${rsrs1.rows}">

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
                    <sql:setDataSource var="dbdb1"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdb1}" var="commentcomment1">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcomment1.rows}"
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
                        <p><a href="CardMoreDetail?CardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
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
    <c:when test="${currancyCheck == 'RUB' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'free' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdb2" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdb2}" var="rsrs2">
            SELECT * FROM cardcontroller WHERE currancy = 'RUB' AND cardtype ='Անվճար';
        </sql:query>
        <c:forEach var="Carding" items="${rsrs2.rows}">

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
                    <sql:setDataSource var="dbdb2"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdb2}" var="commentcomment2">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcomment2.rows}"
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
                        <p><a href="CardMoreDetail?CardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
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
    <c:when test="${currancyCheck == 'RUB' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'Periods' && CreditTypeCheck == 'OFF'}">
        <sql:setDataSource var="dbdb3" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdb3}" var="rsrs3">
            SELECT * FROM cardcontroller WHERE currancy = 'RUB' AND cardtype ='Արտոնյալ ժամանակաշրջան';
        </sql:query>
        <c:forEach var="Carding" items="${rsrs3.rows}">

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
                    <sql:setDataSource var="dbdb3"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdb3}" var="commentcomment3">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcomment.rows}"
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
                        <p><a href="CardMoreDetail?CardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
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
    <c:when test="${currancyCheck == 'RUB' && ChasBackTypeCheck == 'OFF' && DepitTypeCheck == 'OFF' && FreeTypeCheck == 'OFF' && PeriodTypeCheck == 'OFF' && CreditTypeCheck == 'crediting'}">
        <sql:setDataSource var="dbdb4" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                           user="root" password="YLf{}cJ,Kvd%"/>

        <%--<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                         url="jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                         user="oferta_root" password="mnbvcxz00A!"/>--%>

        <sql:query dataSource="${dbdb4}" var="rsrs4">
            SELECT * FROM cardcontroller WHERE currancy = 'RUB' AND cardtype ='Քրեդիտ';
        </sql:query>
        <c:forEach var="Carding" items="${rsrs4.rows}">

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
                    <sql:setDataSource var="dbdb4"
                                       driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8"
                                       user="root" password="YLf{}cJ,Kvd%"/>
                    <sql:query dataSource="${dbdb4}" var="commentcomment4">
                        SELECT * FROM commentscontroller ;
                    </sql:query>
                    <div class="item-desc col">
                        <c:forEach var="CommentsDao" items="${commentcomment4.rows}"
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
                        <p><a href="CardMoreDetail?CardId=${Carding.id}&&Currancy=<%=request.getParameter("Currancy")%>""
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

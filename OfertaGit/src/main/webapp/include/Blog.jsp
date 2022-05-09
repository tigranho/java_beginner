<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/26/2019
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////// Blog START  -->
<hr class="line margin-top-30 margin-bottom-30">
<div class="custom-title flex align-items-center space-between margin-bottom-35 flex-wrap">
    <div>
        <p class="font-24 title">Օգտակար հոդվածներ</p>
    </div>
    <div>
        <a href="Blog?Currancy=${requestScope.PageCurrancy}"
           class="green-link">Բոլոր հոդվածները</a>
    </div>
</div>

<div class="row-md clearfix faq-row">
    <c:if test="${requestScope.usefulArticlesList != null}">
        <c:forEach items="${requestScope.usefulArticlesList}" var="blog">
            <div class="col-3">
                <div class="faq-item">
                    <a href="BlogsDetail?Blogid=${blog.UAId}&&Currancy=${requestScope.PageCurrancy}">
                        <div class="item-img">
                            <img width="250px"
                                 src="<%=request.getContextPath()%>/${blog.UAImageLink}">
                        </div>
                        <div class="item-desc">
                            <span class="item-title font-16">${blog.UATitleAm}</span>
                                <%--  <c:set var="languages" value="${requestScope.Pagelanguage}"/>

                                  <c:choose>
                                      <c:when test="${languages == 'en_US'}">
                                          <span class="item-title font-16">${blog.UATitleEn}</span>
                                      </c:when>
                                      <c:when test="${languages == 'hy_AM'}">
                                          <span class="item-title font-16">${blog.UATitleAm}</span>
                                      </c:when>
                                      <c:otherwise>
                                          <span class="item-title font-16">${blog.UATitleRu}</span>
                                      </c:otherwise>
                                  </c:choose>--%>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>


<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////// Blog ENDS -->
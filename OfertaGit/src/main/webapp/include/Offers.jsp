<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/23/2019
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="tab-nav flex align-items-center flex-wrap">
						<span class="tab-link" onclick="openTabItem(event, 'deposit')" id="defaultOpen">
                           Ավանդներ</span>
    <span class="tab-link" onclick="openTabItem(event, 'mortgage')">
                        Հիպոթեկային վարկ</span>
    <span class="tab-link" onclick="openTabItem(event, 'consumer-loan')">
                   Սպառողական վարկ</span>
    <span class="tab-link" onclick="openTabItem(event, 'car-loan')">
                    Ավտովարկ</span>
    <span class="tab-link" onclick="openTabItem(event, 'agricultural')">
                    Միկրոկրեդիտ</span>
    <span class="tab-link" onclick="openTabItem(event, 'card')">
                   Քարտեր</span>
</div>
<!-- ############################################################ Deposit START -->
<div class="tab-content" id="deposit">
    <div class="top">
        <a href=""
           class="green-link width-icon ellipsis width-percent-70 block">
            <i class="icon-grid"></i> <span>Ավանդների բոլոր առաջարկները</span>
        </a>
    </div>
    <div>
        <div class="simple-carousel margin-top-30">
            <div class="owl-carousel owl-theme" id="simpleCarousel">

                <c:if test="${requestScope.depositListOffer!=null}">
                    <c:forEach items="${requestScope.depositListOffer}" var="SpecialDeposit">
                        <div class="special-offer-item">
                            <div class="item-top flex align-items-center margin-bottom-20">
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialDeposit.DPercentage}"/></span> <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialDeposit.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">
                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialDeposit.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%--<c:set var="languages"
                                                       value="${requestScope.Pagelanguage}"/>
                                                <c:choose>
                                                    <c:when test="${languages == 'en_US'}">
                                                        <c:out value="${productName.prodeuctNameEn}"/>
                                                    </c:when>
                                                    <c:when test="${languages == 'hy_AM'}">
                                                        <c:out value="${productName.productNameAm}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${productName.productNameRu}"/>
                                                    </c:otherwise>
                                                </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialDeposit.productName}"/>Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialDeposit.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialDeposit.bankName}</span>
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
        <a href="" class="green-link width-icon"> <i
                class="icon-grid"></i> <span>All Mortgage offers</span>
        </a>
    </div>
    <div>
        <div class="simple-carousel margin-top-30">
            <div class="owl-carousel owl-theme" id="simpleCarousel2">

                <c:if test="${requestScope.MortgageListOffer!=null}">
                    <c:forEach items="${requestScope.MortgageListOffer}" var="SpecialMortgage">
                        <div class="special-offer-item">
                            <div class="item-top flex align-items-center margin-bottom-20">
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialMortgage.productCode}"/></span> <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialMortgage.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">

                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialMortgage.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%-- <c:set var="languages"
                                                        value="${requestScope.Pagelanguage}"/>
                                                 <c:choose>
                                                     <c:when test="${languages == 'en_US'}">
                                                         <c:out value="${productName.prodeuctNameEn}"/>
                                                     </c:when>
                                                     <c:when test="${languages == 'hy_AM'}">
                                                         <c:out value="${productName.productNameAm}"/>
                                                     </c:when>
                                                     <c:otherwise>
                                                         <c:out value="${productName.productNameRu}"/>
                                                     </c:otherwise>
                                                 </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialMortgage.productName}"/>
                                                       Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialMortgage.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialMortgage.bankName}</span>
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
        <a href="" class="green-link width-icon"> <i
                class="icon-grid"></i> <span>All Consumer Offers</span>
        </a>
    </div>
    <div>
        <div class="simple-carousel margin-top-30">
            <div class="owl-carousel owl-theme" id="simpleCarousel3">
                <c:if test="${requestScope.consumerCreditListoffer!=null}">
                    <c:forEach items="${requestScope.consumerCreditListoffer}"
                               var="SpecialconsumerCreditListoffer">
                        <div class="special-offer-item">
                            <div class="item-top flex align-items-center margin-bottom-20">
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialconsumerCreditListoffer.productCode}"/></span>
                                    <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialconsumerCreditListoffer.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">
                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialconsumerCreditListoffer.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%-- <c:set var="languages"
                                                        value="${requestScope.Pagelanguage}"/>
                                                 <c:choose>
                                                     <c:when test="${languages == 'en_US'}">
                                                         <c:out value="${productName.prodeuctNameEn}"/>
                                                     </c:when>
                                                     <c:when test="${languages == 'hy_AM'}">
                                                         <c:out value="${productName.productNameAm}"/>
                                                     </c:when>
                                                     <c:otherwise>
                                                         <c:out value="${productName.productNameRu}"/>
                                                     </c:otherwise>
                                                 </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialconsumerCreditListoffer.productName}"/>
                                                       Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialconsumerCreditListoffer.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialconsumerCreditListoffer.bankName}</span>
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
        <a href="" class="green-link width-icon"> <i
                class="icon-grid"></i> <span>All Car Loan Offers</span>
        </a>
    </div>
    <div>
        <div class="simple-carousel margin-top-30">
            <div class="owl-carousel owl-theme" id="simpleCarousel4">
                <c:if test="${requestScope.carLoansListoffer!=null}">
                    <c:forEach items="${requestScope.carLoansListoffer}" var="SpecialcarLoansListoffer">
                        <div class="special-offer-item">
                            <div class="item-top flex align-items-center margin-bottom-20">
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialcarLoansListoffer.productCode}"/></span>
                                    <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialcarLoansListoffer.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">
                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialcarLoansListoffer.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%-- <c:set var="languages"
                                                        value="${requestScope.Pagelanguage}"/>
                                                 <c:choose>
                                                     <c:when test="${languages == 'en_US'}">
                                                         <c:out value="${productName.prodeuctNameEn}"/>
                                                     </c:when>
                                                     <c:when test="${languages == 'hy_AM'}">
                                                         <c:out value="${productName.productNameAm}"/>
                                                     </c:when>
                                                     <c:otherwise>
                                                         <c:out value="${productName.productNameRu}"/>
                                                     </c:otherwise>
                                                 </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialcarLoansListoffer.productName}"/>
                                                       Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialcarLoansListoffer.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialcarLoansListoffer.bankName}</span>
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
        <a href="" class="green-link width-icon"> <i
                class="icon-grid"></i> <span>All Agriculture offers</span>
        </a>
    </div>
    <div>
        <div class="simple-carousel margin-top-30">
            <div class="owl-carousel owl-theme" id="simpleCarousel5">
                <c:if test="${requestScope.agriculturalCreditListoffer!=null}">
                    <c:forEach items="${requestScope.agriculturalCreditListoffer}"
                               var="SpecialagriculturalCreditListoffer">
                        <div class="special-offer-item">
                            <div class="item-top flex align-items-center margin-bottom-20">
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialagriculturalCreditListoffer.productCode}"/></span>
                                    <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialagriculturalCreditListoffer.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">
                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialagriculturalCreditListoffer.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%--  <c:set var="languages"
                                                         value="${requestScope.Pagelanguage}"/>
                                                  <c:choose>
                                                      <c:when test="${languages == 'en_US'}">
                                                          <c:out value="${productName.prodeuctNameEn}"/>
                                                      </c:when>
                                                      <c:when test="${languages == 'hy_AM'}">
                                                          <c:out value="${productName.productNameAm}"/>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <c:out value="${productName.productNameRu}"/>
                                                      </c:otherwise>
                                                  </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialagriculturalCreditListoffer.productName}"/>
                                                        Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialagriculturalCreditListoffer.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialagriculturalCreditListoffer.bankName}</span>
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
        <a href="" class="green-link width-icon"> <i
                class="icon-grid"></i> <span>All Cards offers</span>
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
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialcardsListOffer.productCode}"/></span>
                                    <span>Annual</span>
                                </div>
                                <div>
                                            <span class="bold font-20"><c:out
                                                    value="${SpecialcardsListOffer.productCode}"/> productCode</span>
                                    <br>
                                    <c:if test="${requestScope.productNameList != null}">
                                        <c:forEach items="${requestScope.productNameList}"
                                                   var="productName" varStatus="page">
                                            <c:if test="${SpecialcardsListOffer.productCode == productName.productCode}">
                                                <c:out value="${productName.productNameAm}"/>
                                                <%--  <c:set var="languages"
                                                         value="${requestScope.Pagelanguage}"/>
                                                  <c:choose>
                                                      <c:when test="${languages == 'en_US'}">
                                                          <c:out value="${productName.prodeuctNameEn}"/>
                                                      </c:when>
                                                      <c:when test="${languages == 'hy_AM'}">
                                                          <c:out value="${productName.productNameAm}"/>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <c:out value="${productName.productNameRu}"/>
                                                      </c:otherwise>
                                                  </c:choose>--%>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <span class="bold font-20"><c:out
                                            value="${SpecialcardsListOffer.cardName}"/>
                                                        Annual</span>
                                    <span>Ավանդի ժամկետը</span>
                                </div>
                            </div>
                            <div>
                                <a href="" class="def-button btn-blue">Իմանալ ավելին</a>
                            </div>
                            <div
                                    class="item-bottom flex align-items-center margin-top-30 margin-bottom-15">
                                <div class="item-img">

                                    <img src="<%=request.getContextPath()%>/${SpecialcardsListOffer.banksList}"/>

                                </div>
                                <div>
                                    <span>Deposit Family</span>
                                    <span class="notoBold uppercase font-16">${SpecialcardsListOffer.bankName}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!-- ############################################################ card ENDS -->
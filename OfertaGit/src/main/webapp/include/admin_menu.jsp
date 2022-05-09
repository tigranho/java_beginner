<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="menu">
    <ul class="list">
        <%--//////////////////////////////////////////////////////////////////////////////////////////Հիմնական նավիգացիան--%>
        <li class="header">Հիմնական նավիգացիան</li>
        <li>
            <a href="AdminSignInCheck">
                <i class="material-icons">home</i>
                <span>Հիմնական էջ</span>
            </a>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Հիմնական նավիգացիան--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Ադմին վերահսկիչ--%>
        <li>
            <a href="ToAdminUpdatPage">
                <i class="material-icons">face</i>
                <span>Թարմացնել Ադմինի տվյալները</span>
            </a>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Ադմին վերահսկիչ--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">account_balance_wallet</i>
                <span>ֆինանսական էջերի կարգավորիչ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="MainPageRangeController">ՀԻՄՆԱԿԱՆ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="MortgageRangeController">ԱՎԱՆԴՆԵՐ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="DepositRangeController">ՀԻՓՈԹԵՔ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="ConsumerRangeController">ՍՊԱՌՈՂԱԿԱՆ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="CarLoanRangeController">ԱՎՏՈՎԱՐԿ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="MicroRangeController">ՄԻԿՐՈՎԱՐԿ էջի կարգավորիչ
                    </a>
                </li>
                <li>
                    <a href="AGRangeController">ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ էջի կարգավորիչ
                    </a>
                </li>

            </ul>

            <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>

            <%--//////////////////////////////////////////////////////////////////////////////////////////Տեսնելության բաժինը Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">find_replace</i>
                <span>Տեսնելության կարգի բաժին</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Ավտովարկեր</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankCarLoanAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Միգչո վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankMicroLoanAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Ավանդ</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankDepositAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                </li>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Հիփոթեքային վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankMortgageLoanAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                </li>

                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>ٍՍպարողական վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankConsumerLoanAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                </li>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Գյուղատնտեսական վարկ</span>
                    </a>
                    <ul class="ml-menu">
                        <c:if test="${requestScope.banksList != null}">
                            <c:forEach items="${requestScope.banksList}" var="BankList" varStatus="loop">
                                <li>
                                    <a href="GetBankAgricultureLoanAppearanceOrder?bankId=${BankList.bankId}">
                                        <span><c:out value="${BankList.bankName}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>

                </li>

            </ul>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Տեսնելության բաժինը Section--%>

            <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">graphic_eq</i>
                <span>Շուկայավարում</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="MarketingCarLoan">Ավտովարկեր
                    </a>
                </li>
                <li>
                    <a href="MarketingMicro">Միգչո վարկ
                    </a>
                </li>
                <li>
                    <a href="MarketingDeposite">Ավանդ
                    </a>
                </li>
                <li>
                    <a href="MarketingMortgage">Հիփոթեքային վարկ
                    </a>
                </li>
                <li>
                    <a href="MarketingConsumer">ٍՍպարողական վարկ
                    </a>
                </li>
                <li>
                    <a href="MarketingAG">Գյուղատնտեսական վարկ
                    </a>
                </li>

            </ul>

            <%--//////////////////////////////////////////////////////////////////////////////////////////Հատուկ առաջարկի բաժին Section--%>

            <%--//////////////////////////////////////////////////////////////////////////////////////////Web control Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">control_point_duplicate</i>
                <span>Կայքի ղեկավարում</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Մեր մասին</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AboutUsArm">
                                <span>Մեր մասին Հայ</span>
                            </a>
                        </li>
                        <%--<li>
                            <a href="AboutUsEng">
                                <span>Մեր մասին Անգ</span>
                            </a>
                        </li>
                        <li>
                            <a href="AboutUsRus">
                                <span>Մեր մասին Ռուս</span>
                            </a>
                        </li>--%>

                    </ul>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Պայմանները</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="TermsArm">
                                <span>Պայմանները Հայ</span>
                            </a>
                        </li>
                        <%--<li>
                            <a href="TermsEng">
                                <span>Պայմանները Անգ</span>
                            </a>
                        </li>
                        <li>
                            <a href="TermsRus">
                                <span>Պայմանները Ռուս</span>
                            </a>
                        </li>--%>

                    </ul>
                </li>

                </li>
            </ul>
            <%--//////////////////////////////////////////////////////////////////////////////////////////web control Section--%>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Բլոգի բաժինը Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">assignment</i>
                <span>Բլոգի բաժինը</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>նոր բլոգ</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="AddNewArticleArmenian">
                                <span>նոր բլոգ</span>
                            </a>
                        </li>

                    </ul>
                <li>
                    <a href="javascript:void(0);" class="menu-toggle">
                        <span>Բոլոր բլոգները</span>
                    </a>
                    <ul class="ml-menu">
                        <li>
                            <a href="ShowAllArmenianBlogs">
                                <span>Բոլոր Հայկական բլոգները</span>
                            </a>
                        </li>
                        <%--<li>
                            <a href="ShowAllRussianBlogs">
                                <span>Բոլոր Ռուսական բլոգները</span>
                            </a>
                        </li>
                        <li>
                            <a href="ShowAllEnglishBlogs">
                                <span>Բոլոր Անգլերեն բլոգները</span>
                            </a>
                        </li>--%>

                    </ul>
                </li>

                </li>
            </ul>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Բլոգի բաժինը Section--%>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Bank Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">account_balance</i>
                <span>Բանկերի բաժին</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="ShowAllBanks">Բոլոր Բանկերը</a>
                </li>
                <li>
                    <a href="AddNewBank">Ավելացնել նոր բանկ</a>
                </li>
                </li>
            </ul>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Bank Section--%>
            <%--//////////////////////////////////////////////////////////////////////////////////////////Card Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">card_membership</i>
                <span>Քարտեր</span>
            </a>

            <ul class="ml-menu">
                <li>
                    <a href="AllCards">Բոլոր քարտերը</a>
                </li>
                <li>
                    <a href="NewCard">Ավելացնել նոր քարտ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Card Section--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Car loans Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">directions_car</i>
                <span>Ավտոմեքենայի վարկերը</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllCarLoans">Բոլոր Ավտոմեքենայի վարկերը</a>
                </li>
                <li>
                    <a href="NewCarLoan">Ավելացնել նոր Ավտոմեքենայի վարկ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Car loans Section--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Micro loan Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">class</i>
                <span>Միկրո վարկ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllMicro">Բոլոր Միկրո վարկերը</a>
                </li>
                <li>
                    <a href="NewMicro">Ավելացնել նոր Միկրո վարկ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Micro loan Section--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Deposit Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">looks</i>
                <span>Ավանդ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllDeposit">Բոլոր Ավանդները</a>
                </li>
                <li>
                    <a href="NewDeposit">Ավելացնել նոր Ավանդ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Deposit Section--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Mortgage Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">multiline_chart</i>
                <span>Հիփոթեքային վարկ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllMortgage">Բոլոր Հիփոթեքային վարկերը</a>
                </li>
                <li>
                    <a href="NewMortgage">Ավելացնել նոր Հիփոթեքային վարկ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Mortgage crediting Section--%>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Cosumer crediting Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">face</i>
                <span>Սպարողական վարկ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllConsumerCredit">Բոլոր Սպարողական վարկերը</a>
                </li>
                <li>
                    <a href="NewConsumerCredit">Ավելացնել նոր Սպարողական վարկ</a>
                </li>
            </ul>
        </li>
        <%--//////////////////////////////////////////////////////////////////////////////////////////Cosumer crediting Section--%>
        <%--////////////////////////////////////////////////////////////////////////////////////////// Գյուղատնտեսական Section--%>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons">local_florist</i>
                <span>Գյուղատնտեսական վարկ</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="AllAgricultureCredits">Բոլոր Գյուղատնտեսական վարկ</a>
                </li>
                <li>
                    <a href="NewAgricultureCredit">Ավելացնել նոր Գյուղատնտեսական վարկ</a>
                </li>
            </ul>
    </ul>
    <%--//////////////////////////////////////////////////////////////////////////////////////////Գյուղատնտեսական Section--%>

</div>

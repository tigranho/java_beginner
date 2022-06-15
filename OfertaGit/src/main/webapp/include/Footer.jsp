<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/23/2019
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<li><a href="https://www.facebook.com/Ofertaam-102543797781234/" target="_blank" class="facebook"><i class="icon-facebook"></i></a></li>
<li><a href="https://t.me/Ofertaam" class="telegram" target="_blank"><i class="icon-telegram"></i></a></li>
<li><a href="https://www.linkedin.com/company/ofertaam" class="linkedin" target="_blank"><i class="icon-linkedin"></i></a></li>
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

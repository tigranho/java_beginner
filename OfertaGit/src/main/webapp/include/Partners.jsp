<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/26/2019
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="flex align-items-center space-between margin-bottom-35">
    <div>
        <p class="font-24 title">
            Օրենքներ և կանոնակարգեր
        </p>
    </div>
</div>
<div class="faq-container">
    <div class="row-md clearfix downloads-row">
        <div class="col-3">
            <div class="download-file-item">
                <a href="<%=request.getContextPath()%>/pdf/ABP.pdf" target="_blank"
                   class="flex align-items-center"> <span>
                                            <img src="../images/pdf.svg"/></span> <span>ՀՀ Օրենքը Բանկային գաղտնիքի մասին</span>
                </a>
            </div>
        </div>
        <div class="col-3">
            <div class="download-file-item">
                <a href="<%=request.getContextPath()%>/pdf/ABBP.pdf" target="_blank"
                   class="flex align-items-center"> <span><img
                        src="../images/pdf.svg"/></span> <span>ՀՀ Օրենքը Բանկերի և Բանկային գործունեության</span>
                </a>
            </div>
        </div>
        <div class="col-3">
            <div class="download-file-item">
                <a href="<%=request.getContextPath()%>/pdf/SK.pdf" target="_blank"
                   class="flex align-items-center"> <span><img
                        src="../images/pdf.svg"/></span>
                    <span>ՀՀ օրենքը Սպառողական կրեդիտավորման մասին</span>
                </a>
            </div>
        </div>
        <div class="col-3">
            <div class="download-file-item">
                <a href="<%=request.getContextPath()%>/pdf/FH.pdf" target="_blank"
                   class="flex align-items-center"> <span><img
                        src="../images/pdf.svg"/></span> <span>Ֆինանսական հաշտարար</span>
                </a>
            </div>
        </div>
    </div>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<span class="close-btn" id="navCloseBtn"><i class="icon-close font-16"></i></span>
<div class="search-popup" id="searchPopup">
    <span class="close-btn hide-for-tablet" id="closeBtn"><i class="icon-close font-16"></i></span>
    <%-- <span class="state ellipsis show-for-tablet"><i class="icon-state"></i> </span>--%>

    <div class="inner">
        <p class="font-30 text-center uppercase hide-for-tablet">Որոնում</p>
        <form id="searchForm" action="SearchPageApp" method="post">
            <div class="def-input int-right-icon int-outline margin-top-20">
                <i class="icon-search"></i>
                <div class="col-10 middle col relativeBlock">

                    <input type="text" name="searchText" id="search-element" class="font-14" required>
                    <p id="demo"></p>
                    <div id="results" class="search-results"></div>
                    <input type="hidden" name="Pagelanguage" value="${requestScope.PageLanguage}">
                    <input type="hidden" name="Currancy" value="${requestScope.PageCurrancy}"/>

                </div>
                <div class="col-1-2 small col">
                    <input type="button" id="searchformsubmit" value="Անցնել էջ" class="def-button btn-green" /><br/><br/><br/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(function () {
        $('#search-element').keyup( function (e) {
            e.preventDefault()
            console.log('first' + $(this).val())
            console.log( 'second' + e.target.value)

            var search_text = $(this).val();
            console.log('search_text' + search_text)
            var _data = {
                search_text: search_text
            };

            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                type: 'POST',
                data: _data,
                dataType: 'text',
                beforeSend: function () {
                    $('#results').html('')
                },
                success: function (data) {
                    var dataParse =  $.parseJSON(data);
                    $('#results').html(dataParse)
                    console.log('data parse' + dataParse);
                },
                error: function (data) {
                }
            });
        });
        $(document).on('click', '.search-results span', function (e) {
            var toFiled = $(this).text();
            console.log('toFiled', toFiled)
            $('#search-element').val(toFiled);
            $('#results').html('');
            console.log('$(document).on(click, .search-results span, function (e)')
        });

        $('#searchformsubmit').on('click',function () {
             $(this).preventDefault();
            console.log(" $(this).preventDefault();" );
            var value;
            value = document.getElementById('search-element').value;
            if (value.length < 3) {
                document.getElementById('demo').innerHTML = "Խնդրում ենք լրացնել դաշտը ավելի քան 3 տեքստ";
                return false;
                //check if the input contains dropdown data
            }else if(!value.includes('ID') || !value.includes('-ում')){
                document.getElementById('demo').innerHTML = "Խնդրում ենք ընտրել բացվող ցուցակից";
                return false;
            }else {
                $('#searchForm').submit();
                console.log(" $('#searchForm').submit();" );
            }
        });
    });
</script>
<%-- --%>

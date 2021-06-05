<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="courseAPI" value="/api/course"/>
<c:url var="courseURL" value="/training-staff/course/list"/>
<c:url var="requestCourseAPI" value="/api/trainee/course"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your course</title>
</head>

<body>
<div class="main-content">
    <%--<form action="<c:url value='/trainee/course/list'/>" id="formSubmit" method="get">--%>
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <%--GET trainee ID--%>
                                <form:form class="form-horizontal" role="form" id="formSubmit1"
                                           modelAttribute="traineeDTO">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="name">Trainee name:</label>
                                        <div class="col-sm-9">
                                            <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                                        </div>
                                        <form:hidden path="id" id="traineeId" value="${traineeDTO.id}"/>
                                    </div>
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th><label for="checkAll"></label><input type="checkbox" id="checkAll"></th>
                                            <th>Course name</th>
                                            <th>Short description</th>
                                            <th>Course category</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><label for="checkbox_${item.id}"></label><input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                <td>${item.courseName}</td>
                                                <td>${item.shortDescription}</td>
                                                <td>${item.courseCategoryNameCode}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <!--  Phan trang 1 2 3 4 5 6 -->
                                    <div class="container">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination" id="pagination"></ul>
                                            <input type="hidden" value="" id="page" name="page">
                                            <input type="hidden" value="" id="limit" name="limit">
                                        </nav>
                                    </div>
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button class="btn btn-info" type="button"
                                                    id="btnAddOrUpdateNew">
                                                <i class="ace-icon fa fa-check bigger-110"></i> Send request
                                            </button>

                                            &nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i> Cancel
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <%--</form>--%>
</div>
<script>
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                // currentPage là page đang đứng hiện tại, page là page muốn chuyển tiếp
                if (currentPage != page) {
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        // var data = {};
        // var formData = $('#formSubmit').serializeArray();
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        var arr = [];
        var data = {};
        var formData = $('#formSubmit1').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        // Doi object thanh array
        arr = Object.values(data);
        // get id trainee gan cho traineeId
        var traineeId = new Array(arr[1]);
        // gop 2 mang lai
        var data2 = traineeId.concat(ids);
        console.log(data2)
        // truyen ve mang ma index 0 la id cua trainee, 1,2,3... la id cua course
        requestCourse(data2);
    });
    function requestCourse(data2) {
        $.ajax({
            url: '${requestCourseAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data2),
            dataType: 'json',
            success: function (result) {
                location.reload();
            },
            error: function (error) {
                location.reload();
            }
        });
    }
</script>
</body>
</html>
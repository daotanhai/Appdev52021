<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%-- <c:url var="listUserURL" value="/quan-tri/user/list"/> --%>
<c:url var="editTraineeURL" value="/training-staff/trainee/edit"/>
<c:url var="trainingStaffAPI" value="/api/trainee"/>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Create trainee account</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">${message}</div>
                    </c:if>
                    <form:form class="form-horizontal" role="form" id="formSubmit"
                               modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="userName">Username:</label>
                            <div class="col-sm-9">
                                <form:input path="userName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="password">Password:</label>
                            <div class="col-sm-9">
                                <form:input path="password" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="name">Name:</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="ge">Age:</label>
                            <div class="col-sm-9">
                                <form:input path="age" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="dOB">DOB:</label>
                            <div class="col-sm-9">
                                <form:input path="dOB" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="department">Department:</label>
                            <div class="col-sm-9">
                                <form:input path="department" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="education">Education:</label>
                            <div class="col-sm-9">
                                <form:input path="education" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="experienceDetails">Experience Details:</label>
                            <div class="col-sm-9">
                                <form:input path="experienceDetails"
                                            cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="location">Location:</label>
                            <div class="col-sm-9">
                                <form:input path="location" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="mainProgrammingLangugage">Main Programming Language:</label>
                            <div class="col-sm-9">
                                <form:input path="mainProgrammingLangugage"
                                            cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="toeicScore">toeicScore:</label>
                            <div class="col-sm-9">
                                <form:input path="toeicScore" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="status">Status 0 or 1:</label>
                            <div class="col-sm-9">
                                <form:input path="status" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="roleCode">Role Code:</label>
                            <div class="col-sm-9">
                                <form:input path="roleCode" value="TRAINEE"
                                            cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>

                        <!-- List course for trainee -->
                        <%--<table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>Tên khóa học</th>
                                <th>Mô tả ngắn</th>
                                <th>Thể loại</th>
                                <th>The loai id</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${courseList.listResult}">
                                <tr>
                                    <!-- value = id cua bai viet  -->
                                    <!-- nó lặp, nên cần phân biệt id checkbox nào của bài viết nào do đó, dùng checkbox+id của bài viết -->
                                    <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}" ></td>
                                    <td>${item.courseName}</td>
                                    <td>${item.shortDescription}</td>
                                    <td>${item.courseCategoryNameCode}</td>
                                    <td>${item.courseCategoryId}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>--%>
                        <form:hidden path="id" id="traineeId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat trainer -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button"
                                            id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i> Cập nhật trainee
                                    </button>
                                </c:if>
                                <!-- ID = null thi them trainer -->
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button"
                                            id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i> Thêm mới trainee
                                    </button>
                                </c:if>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i> Hủy
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();

        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        var id = $('#traineeId').val();

        if (id == "") {
            addTrainee(data);
        } else {
            updateTrainee(data);
        }
    });

    function addTrainee(data) {
        $.ajax({
            url: '${trainingStaffAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editTraineeURL}?id=" + result.id + "&message=insert_success";
                successPopup();
            },
            error: function (error) {
                errorPopup();
            }
        });
    }

    function updateTrainee(data) {
        $.ajax({
            url: '${trainingStaffAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editTraineeURL}?id=" + result.id
                    + "&message=update_success";
                successPopup();
            },
            error: function (error) {
                errorPopup();
            }
        });
    }

    function successPopup() {
        alert("Thành công!");
    }

    function errorPopup() {
        alert("Lỗi");
    }
</script>
</body>
</html>
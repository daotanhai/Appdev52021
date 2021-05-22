<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="updateTraineeAPI" value="/api/trainee/role"/>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>View profile</title>
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
                                   for="age">Age:</label>
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
                        <form:hidden path="roleCode" value="TRAINEE"/>
                        <form:hidden path="id" id="traineeId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat trainer -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button"
                                            id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i> Cập nhật profile của bạn!
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
        updateTrainee(data);
    });

    function updateTrainee(data) {
        $.ajax({
            url: '${updateTraineeAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                <%--window.location.href = "${editTraineeURL}?id=" + result.id--%>
                <%--    + "&message=update_success";--%>
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
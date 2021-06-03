<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="updateTrainerAPI" value="/api/trainer/role"/>

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
                                   for="passWord">Password:</label>
                            <div class="col-sm-9">
                                <form:input path="passWord" cssClass="col-xs-10 col-sm-5"/>
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
                                   for="workingPlace">Working Place:</label>
                            <div class="col-sm-9">
                                <form:input path="workingPlace" cssClass="col-xs-10 col-sm-5"/>
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
                                   for="telephone">Telephone:</label>
                            <div class="col-sm-9">
                                <form:input path="telephone" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="email">email:</label>
                            <div class="col-sm-9">
                                <form:input path="email" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="externalOrInternal">External Or Internal:</label>
                            <div class="col-sm-9">
                                <form:input path="externalOrInternal"
                                            cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="roleCode" value="TRAINER"/>
                        <form:hidden path="id" id="trainerId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat trainer -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button"
                                            id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i> Update your profile!
                                    </button>
                                </c:if>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i> Cancel
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
        updateTrainer(data);
    });

    function updateTrainer(data) {
        $.ajax({
            url: '${updateTrainerAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                successPopup();
                location.reload();
            },
            error: function (error) {
                errorPopup();
                location.reload();
            }
        });
    }

    function successPopup() {
        alert("Successful!");
    }

    function errorPopup() {
        alert("Error");
    }
</script>
</body>
</html>
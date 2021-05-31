<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listUserURL" value="/quan-tri/user/list"/>
<c:url var="editUserURL" value="/quan-tri/user/edit"/>
<c:url var="userAPI" value="/api/user"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Create trainer/training staff</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="models">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="userName">UserName:</label>
                            <div class="col-sm-9">
                                <form:input path="userName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="password">Password:</label>
                            <div class="col-sm-9">
                                <form:input path="password" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="role" class="col-sm-3 control-label no-padding-right">Role:</label>
                            <div class="col-sm-9">
                                <form:select path="role" id="role">
                                    <form:option value="" label="-- Choose role --"/>
                                    <form:options items="${roles}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="status">Status 0 or
                                1:</label>
                            <div class="col-sm-9">
                                <form:input path="status" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="id" id="userId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat trainer -->
                                <c:if test="${not empty models.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Update trainer/training staff
                                    </button>
                                </c:if>
                                <!-- ID = null thi them trainer -->
                                <c:if test="${empty models.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Add new trainer/training staff
                                    </button>
                                </c:if>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Cancel
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
        var id = $('#userId').val();
        if (id == "") {
            addUser(data);
        } else {
            updateUser(data);
        }
    });

    function addUser(data) {
        $.ajax({
            url: '${userAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                // thành công thì đứng yên trang edit và thông báo qua link
                window.location.href = "${editUserURL}?id=" + result.id + "&message=insert_success";
                successPopup();
            },
            error: function (error) {
                // Lỗi thì trả về trang user list
                window.location.href = "${listUserURL}?page=1&limit=2&message=error_system";
                errorPopup();
            }
        });
    }

    function updateUser(data) {
        $.ajax({
            url: '${userAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editUserURL}?id=" + result.id + "&message=update_success";
                successPopup();
            },
            error: function (error) {
                window.location.href = "${editUserURL}?id=" + result.id + "&message=error_system";
                successPopup();
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
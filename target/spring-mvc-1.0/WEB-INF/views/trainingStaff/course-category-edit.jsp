<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="courseCategoryListURL" value="/training-staff/coursecategory/list"/>
<c:url var="courseCategoryEditURL" value="/training-staff/coursecategory/edit"/>
<c:url var="courseCategoryAPI" value="/api/courseCategory"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Edit course category</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="nameCourseCategory">Course category name</label>
                            <div class="col-sm-9">
                                <form:input path="nameCourseCategory" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="courseCategoryNameCode">Course category name code</label>
                            <div class="col-sm-9">
                                <form:input path="courseCategoryNameCode" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="shortDescription" class="col-sm-3 control-label no-padding-right">Course category short description</label>
                            <div class="col-sm-9">
                                <form:textarea path="courseCategoryDescription" rows="5" cols="10"
                                               cssClass="form-control" id="shortDescription" name="shortDescription"/>
                            </div>
                        </div>
                        <form:hidden path="id" id="courseCategoryId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat  -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Update course category
                                    </button>
                                </c:if>
                                <!-- ID = null thi them mới -->
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Add new course category
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
        var id = $('#courseCategoryId').val();
        if (id == "") {
            addCategory(data);
        } else {
            updateCategory(data);
        }
    });

    function addCategory(data) {
        $.ajax({
            url: '${courseCategoryAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${courseCategoryEditURL}?id=" + result.id + "&message=insert_success";
                successPopup();
            },
            error: function (error) {
                window.location.href = "${courseCategoryListURL}?page=1&limit=5&message=error_system";
            }
        });
    }

    function updateCategory(data) {
        $.ajax({
            url: '${courseCategoryAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${courseCategoryEditURL}?id=" + result.id + "&message=update_success";
                successPopup()
            },
            error: function (error) {
                window.location.href = "${courseCategoryListURL}?page=1&limit=5&message=error_system";
                successPopup()
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
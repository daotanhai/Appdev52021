<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="courseURL" value="/training-staff/course/list"/>
<c:url var="courseEditURL" value="/training-staff/course/edit"/>
<c:url var="courseAPI" value="/api/course"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Chỉnh sửa khóa học</title>
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
            <!-- /.breadcrumb -->
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
                            <label for="courseCategoryNameCode" class="col-sm-3 control-label no-padding-right">Thể loại
                                khóa học:</label>
                            <div class="col-sm-9">
                                <form:select path="courseCategoryNameCode" id="courseCategoryNameCode">
                                    <form:option value="" label="-- Chọn thể loại --"/>
                                    <form:options items="${categories}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="courseName">Tên khóa
                                học</label>
                            <div class="col-sm-9">
                                <form:input path="courseName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="shortDescription">Mô tả ngắn loại
                                khóa học</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="shortDescription" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="id" id="courseId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat bai viet -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật khóa học
                                    </button>
                                </c:if>
                                <!-- ID = null thi them bai viet -->
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Thêm khóa học
                                    </button>
                                </c:if>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Hủy
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
        var id = $('#courseId').val();
        if (id == "") {
            addCourse(data);
        } else {
            updateCourse(data);
        }
    });

    function addCourse(data) {
        $.ajax({
            url: '${courseAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${courseEditURL}?id=" + result.id + "&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${courseURL}?page=1&limit=2&message=error_system";
            }
        });
    }

    function updateCourse(data) {
        $.ajax({
            url: '${courseAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${courseEditURL}?id=" + result.id + "&message=update_success";
            },
            error: function (error) {
                window.location.href = "${courseEditURL}?page=1&limit=2&message=error_system";
            }
        });
    }
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="topicURL" value="/training-staff/topic/list"/>
<c:url var="topicEditURL" value="/training-staff/topic/edit"/>
<c:url var="topicAPI" value="/api/topic"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Chỉnh sửa Topics</title>
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
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên topic</label>
                            <div class="col-sm-9">
                                <form:input path="topicName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Mô tả ngắn
                                topic</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="topicDescription" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-3 control-label no-padding-right">Thuộc khóa
                                học:</label>
                            <div class="col-sm-9">
                                <form:select path="courseName" id="courseCategoryNameCode">
                                    <form:option value="" label="-- Chọn khóa học --"/>
                                    <form:options items="${courses}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="trainerName" class="col-sm-3 control-label no-padding-right">Trainer sẽ phụ
                                trách topic này:</label>
                            <div class="col-sm-9">
                                <form:select path="trainerName" id="courseCategoryNameCode">
                                    <form:option value="" label="-- Chọn trainer --"/>
                                    <form:options items="${trainers}"/>
                                </form:select>
                            </div>
                        </div>
                        <form:hidden path="id" id="topicId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật topic
                                    </button>
                                </c:if>
                                <!-- ID = null thi them-->
                                <c:if test="${empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Thêm topic
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
        var id = $('#topicId').val();
        if (id == "") {
            addTopic(data);
        } else {
            updateTopic(data);
        }
    });

    function addTopic(data) {
        $.ajax({
            url: '${topicAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${topicEditURL}?id=" + result.id + "&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${topicURL}?page=1&limit=5&message=error_system";
            }
        });
    }

    function updateTopic(data) {
        $.ajax({
            url: '${topicAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${topicEditURL}?id=" + result.id + "&message=update_success";
            },
            error: function (error) {
                window.location.href = "${topicEditURL}?id=" + result.id + "&message=error_system";
            }
        });
    }
</script>
</body>
</html>
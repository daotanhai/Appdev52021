<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="trainerURL" value="/training-staff/trainer/list"/>
<c:url var="trainerEditURL" value="/training-staff/trainer/edit"/>
<c:url var="trainerAPI" value="/api/trainer"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Chỉnh sửa trainer</title>
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
                            <label class="col-sm-3 control-label no-padding-right" for="name">Tên trainer</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="workingPlace">Nơi làm
                                việc</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="workingPlace" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="education">Education</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="education" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="telephone">SDT</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="telephone" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="email">Email</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="email" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="externalOrInternal">External Or
                                Internal</label>
                            <div class="col-sm-9">
                                <!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
                                <form:input path="externalOrInternal" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <form:hidden path="id" id="trainerId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật trainer
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
        var id = $('#trainerId').val();
        updateTrainer(data);
    });

    function updateTrainer(data) {
        $.ajax({
            url: '${trainerAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${trainerEditURL}?id=" + result.id + "&message=update_success";
                successPopup();
            },
            error: function (error) {
                window.location.href = "${trainerURL}?page=1&limit=5&message=error_system";
                errorPopup()
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
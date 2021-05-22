<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="assignTopicURL" value="/training-staff/trainer/edit/assign-topic"/>
<c:url var="assignTopicAPI" value="/api/trainer/topic"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Gán topics cho trainer</title>
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
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên
                                trainer</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <%--LIST TOPIC--%>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>Tên topic</th>
                                <th>Mô tả ngắn</th>
                                <th>Thuộc khóa học</th>
                                <th>Trainer phụ trách</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="topicList" items="${topicList.listResult}">
                                <tr>
                                    <!-- value = id cua bai viet  -->
                                    <!-- nó lặp, nên cần phân biệt id checkbox nào của bài viết nào do đó, dùng checkbox+id của bài viết -->
                                    <td><label for="checkbox_${topicList.id}"></label><input type="checkbox"
                                                                                             id="checkbox_${topicList.id}"
                                                                                             value="${topicList.id}">
                                    </td>
                                    <td>${topicList.topicName}</td>
                                    <td>${topicList.topicDescription}</td>
                                    <td>${topicList.courseName}</td>
                                    <td>${topicList.trainerName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form:hidden path="id" id="trainerId"/>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <!-- ID khac null thi cap nhat -->
                                <c:if test="${not empty model.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Cập nhật ngay
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
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        addTopic(ids);
    });

    function addTopic(ids) {
        $.ajax({
            url: '${assignTopicAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(ids),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${assignTopicURL}?id=" + result.id + "&message=insert_success";
                successPopup();
            },
            error: function (error) {
                <%--window.location.href = "${assignTopicURL}?page=1&limit=5&message=error_system";--%>
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
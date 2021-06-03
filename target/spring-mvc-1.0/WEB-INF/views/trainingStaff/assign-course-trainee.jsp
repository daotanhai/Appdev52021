<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%-- <c:url var="listUserURL" value="/quan-tri/user/list"/> --%>
<c:url var="assignTraineeURL" value="/training-staff/trainee/assign-course"/>
<c:url var="assignCourseAPI" value="/api/assign"/>
<c:url var="deleteCourseAPI" value="/api/assign"/>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Assign courses for trainee</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form class="form-horizontal" role="form" id="formSubmit"
                               modelAttribute="model">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="name">Trainee name:</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                            <form:hidden path="id" id="traineeId" value="${model.id}"/>
                        </div>
                    </form:form>
                    <!-- List course for trainee -->
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="checkAll"></th>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Short Description</th>
                            <th>Course Category</th>
                            <th>Course category ID</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${courseList.listResult}">
                            <tr>
                                <!-- value = id cua bai viet  -->
                                <!-- nó lặp, nên cần phân biệt id checkbox nào của bài viết nào do đó, dùng checkbox+id của bài viết -->
                                    <%--                                    <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}" ></td>--%>
                                <td><label for="checkbox_${item.id}"></label><input type="checkbox"
                                                                                    id="checkbox_${item.id}"
                                                                                    value="${item.id}"></td>
                                <td>${item.id}</td>
                                <td>${item.courseName}</td>
                                <td>${item.shortDescription}</td>
                                <td>${item.courseCategoryNameCode}</td>
                                <td>${item.courseCategoryId}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h4>Danh sách khóa học của trainee này</h4>
                    <%--TABLE cho khóa học đã đăng ký của học viên --%>
                    <div class="widget-box table-filter">
                        <div class="table-btn-controls">
                            <div class="pull-right tableTools-container">
                                <div class="dt-buttons btn-overlap btn-group">
                                    <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                            data-toggle="tooltip" title='Xóa trainees'>
															<span>
																<i class="fa fa-trash-o bigger-110 pink"></i>
															</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="checkAll2"></th>
                            <th>ID</th>
                            <th>Course ID</th>
                            <th>Trainee ID</th>
                            <th>Assigned by</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${traineeCourseList.listResult}">
                            <tr>
                                <!-- value = id cua bai viet  -->
                                <!-- nó lặp, nên cần phân biệt id checkbox nào của bài viết nào do đó, dùng checkbox+id của bài viết -->
                                    <%--                                    <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}" ></td>--%>
                                <td><label for="checkbox_${item.id}"></label><input type="checkbox"
                                                                                    id="checkbox_${item.id}"
                                                                                    value="${item.id}"></td>
                                <td>${item.id}</td>
                                <td>${item.courseId}</td>
                                <td>${item.traineeId}</td>
                                <td>${item.createdBy}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="button"
                                    id="btnAddOrUpdateNew">
                                <i class="ace-icon fa fa-check bigger-110"></i> Assign course
                            </button>

                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="ace-icon fa fa-undo bigger-110"></i> Cancel
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        // var data = {};
        // var formData = $('#formSubmit').serializeArray();
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        var arr = [];
        var data = {};
        var formData = $('#formSubmit').serializeArray();
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
        addCourse(data2);
    });

    function warningBeforeDelete() {
        swal({
            title: "Confirm delete",
            text: "Are you sure to delete?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Confirm",
            cancelButtonText: "Cancel",
        }).then(function (isConfirm) {
            if (isConfirm) {
                // get tất cả ids, là 1 mảng và put vào biến var ids
                // check box nằm trong tbody, checked là những cái nào đc check rồi thì get
                var ids = $('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();
                // put mang ids vao
                deleteTraineeCourse(ids);
            }
        });
    }

    function deleteTraineeCourse(data) {
        $.ajax({
            url: '${deleteCourseAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
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

    function addCourse(data2) {
        $.ajax({
            url: '${assignCourseAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data2),
            dataType: 'json',
            success: function (result) {
                // thành công thì đứng yên trang edit và thông báo qua link
                successPopup();
                location.reload();
            },
            error: function (error) {
                // Lỗi thì trả về trang user list
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

<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="trainerAPI" value="/api/trainer"/>
<c:url var="trainerURL" value="/training-staff/trainer/list"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách trainer</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/training-staff/trainer/list'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <!-- Training Staff khong them duoc trainer -->
                                        <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa trainer'>
															<span>
																<i class="fa fa-trash-o bigger-110 pink"></i>
															</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="checkAll"></th>
                                            <th>Tên trainer</th>
                                            <th>Username</th>
                                            <th>Nơi làm việc</th>
                                            <th>Education</th>
                                            <th>SDT</th>
                                            <th>Email</th>
                                            <th>External Or Internal</th>
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <!-- value = id cua bai viet  -->
                                                <!-- nó lặp, nên cần phân biệt id checkbox nào của bài viết nào do đó, dùng checkbox+id của bài viết -->
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                </td>
                                                <td>${item.name}</td>
                                                <td>${item.userName}</td>
                                                <td>${item.workingPlace}</td>
                                                <td>${item.education}</td>
                                                <td>${item.telephone}</td>
                                                <td>${item.email}</td>
                                                <td>${item.externalOrInternal}</td>
                                                <td>
                                                    <c:url var="updateTrainerURL2" value="/training-staff/trainer/edit">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật trainer" href='${updateTrainerURL2}'><i
                                                            class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                    <c:url var="assignCourseTrainerURL"
                                                           value="/training-staff/trainer/assign-course">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Gán khóa học cho trainer này"
                                                       href='${assignCourseTrainerURL}'><i class="fa fa-pencil-square-o"
                                                                                           aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <!--  Phan trang 1 2 3 4 5 6 -->
                                    <div class="container">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination" id="pagination"></ul>
                                            <input type="hidden" value="" id="page" name="page">
                                            <input type="hidden" value="" id="limit" name="limit">
                                        </nav>
                                    </div>
                                    <!-- Ket thuc phan trang -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->
<script>
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                // currentPage là page đang đứng hiện tại, page là page muốn chuyển tiếp
                if (currentPage !== page) {
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });

    // jquery
    function warningBeforeDelete() {
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa hay không",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
        }).then(function (isConfirm) {
            if (isConfirm) {
                // get tất cả ids, là 1 mảng và put vào biến var ids
                // check box nằm trong tbody, checked là những cái nào đc check rồi thì get
                var ids = $('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();
                // put mang ids vao
                deleteTrainer(ids);
            }
        });
    }

    function deleteTrainer(data) {
        $.ajax({
            url: '${trainerAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            //data: data,
            success: function (result) {
                window.location.href = "${trainerURL}?page=1&limit=5&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${trainerURL}?page=1&limit=5&message=error_system";
            }
        });
    }
</script>

</body>
</html>
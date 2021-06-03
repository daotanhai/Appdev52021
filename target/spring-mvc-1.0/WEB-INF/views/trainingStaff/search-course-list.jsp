<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="trainingStaffListURL" value="/training-staff/trainee/list"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Results of searching course</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/training-staff/course/list'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>created by</th>
                                            <th>courseName</th>
                                            <th>shortDescription</th>
                                            <th>courseCategoryId</th>
                                            <th>courseCategoryNameCode</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${courseDTOListResult.listResult}">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td>${item.createdBy}</td>
                                                <td>${item.courseName}</td>
                                                <td>${item.shortDescription}</td>
                                                <td>${item.courseCategoryId}</td>
                                                <td>${item.courseCategoryNameCode}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
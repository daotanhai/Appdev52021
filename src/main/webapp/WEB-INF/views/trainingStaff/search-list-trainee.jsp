<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="trainingStaffListURL" value="/training-staff/trainee/list"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kết quả tìm kiếm trainee</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/training-staff/trainee/search/list'/>" id="formSubmit" method="get">
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
                                            <th>Name</th>
                                            <th>Age</th>
                                            <th>DOB</th>
                                            <th>Education</th>
                                            <th>Main Programming Language</th>
                                            <th>Toeic Score</th>
                                            <th>Experience Details</th>
                                            <th>Department</th>
                                            <th>Location</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${traineeDTOListResult.listResult}">
                                            <tr>
                                                <td>${item.name}</td>
                                                <td>${item.age}</td>
                                                <td>${item.dOB}</td>
                                                <td>${item.education}</td>
                                                <td>${item.mainProgrammingLangugage}</td>
                                                <td>${item.toeicScore}</td>
                                                <td>${item.experienceDetails}</td>
                                                <td>${item.department}</td>
                                                <td>${item.location}</td>
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
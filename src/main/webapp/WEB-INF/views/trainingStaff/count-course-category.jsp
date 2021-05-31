<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="courseCategoryEditURL" value="/training-staff/coursecategory/edit"/>
<c:url var="courseCategoryListURL" value="/training-staff/coursecategory/list"/>
<c:url var="courseCategoryAPI" value="/api/courseCategory"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Count course category</title>
</head>

<body>
<div class="main-content">
    <form action="<c:url value='/training-staff/count'/>" id="formSubmit" method="get">
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
                                            <th>Course Category Name Code</th>
                                            <th>Number of courses in category</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${countCourseCategories.listResult}">
                                            <tr>
                                                <td>${item.courseCategoryNameCode}</td>
                                                <td>${item.numberOfCourses}</td>
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
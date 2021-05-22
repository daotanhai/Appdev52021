<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%-- <c:url var="listUserURL" value="/quan-tri/user/list"/> --%>
<c:url var="searchTraineeURL" value="/training-staff/trainee/search"/>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<html>
<head>
    <title>Search trainee</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form method="POST"
                               action="/training-staff/trainee/search/list" modelAttribute="trainee">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="userName">Username:</label>
                            <div class="col-sm-9">
                                <form:input path="userName" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="mainProgrammingLangugage">Main Programming Language:</label>
                            <div class="col-sm-9">
                                <form:input path="mainProgrammingLangugage" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="toeicScore">toeicScore:</label>
                            <div class="col-sm-9">
                                <form:input path="toeicScore" cssClass="col-xs-10 col-sm-5"/>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                    <%--NUT SUBMIT--%>
                                <input class="btn btn-info" type="submit" value="Submit"/>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110">Hủy</i>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar')
        } catch (e) {
        }
    </script>
    <ul class="nav nav-list">
        <li>
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Training Staff Role
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href="<c:url value='/training-staff/trainer/list?page=1&limit=5'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Trainer list
                    </a>

                    <a href="<c:url value='/training-staff/trainee/list?page=1&limit=5'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Trainee list
                    </a>

                    <a href="<c:url value='/training-staff/course/list?page=1&limit=5'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Course list
                    </a>

                    <a href="<c:url value='/training-staff/coursecategory/list?page=1&limit=5'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Course category list
                    </a>

                    <a href="<c:url value='/training-staff/trainee/edit'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Add new trainee
                    </a>
                    <a href="<c:url value='/training-staff/coursecategory/edit'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Add new course category
                    </a>

                    <a href="<c:url value='/training-staff/course/edit'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Add new course
                    </a>

                    <a href="<c:url value='/training-staff/trainee/search'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Search Trainee
                    </a>

                    <a href="<c:url value='/training-staff/course-category/search'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Search Course category
                    </a>

                    <a href="<c:url value='/training-staff/course/search'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Search Courses
                    </a>

                    <a href="<c:url value='/training-staff/count'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        Number of courses in category
                    </a>

                    <a href="<c:url value='/training-staff/request/course?page=1&limit=5'/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        View request courses from trainee
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>
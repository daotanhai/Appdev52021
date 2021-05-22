package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraineeDTO extends AbstractDTO<TraineeDTO> {

    private String name;
    private Integer age;
    private String dOB;
    private String education;
    private String mainProgrammingLangugage;
    private String toeicScore;
    private String experienceDetails;
    private String department;
    private String location;
    private String roleCode;
    private String userName;
    private String password;
    private Integer status;
    //private Set<String> courses = new HashSet<>();
    private List<CourseDTO> courseList = new ArrayList<CourseDTO>();
    // Map courseDTO key,value
    private Map<String, String> courseDTOs = new HashMap<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMainProgrammingLangugage() {
        return mainProgrammingLangugage;
    }

    public void setMainProgrammingLangugage(String mainProgrammingLangugage) {
        this.mainProgrammingLangugage = mainProgrammingLangugage;
    }

    public String getToeicScore() {
        return toeicScore;
    }

    public void setToeicScore(String toeicScore) {
        this.toeicScore = toeicScore;
    }

    public String getExperienceDetails() {
        return experienceDetails;
    }

    public void setExperienceDetails(String experienceDetails) {
        this.experienceDetails = experienceDetails;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public Map<String, String> getCourseDTOs() {
        return courseDTOs;
    }

    public void setCourseDTOs(Map<String, String> courseDTOs) {
        this.courseDTOs = courseDTOs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

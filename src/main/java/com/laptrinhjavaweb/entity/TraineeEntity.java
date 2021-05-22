package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Trainee")
public class TraineeEntity extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "DOB")
    private String dOB;

    @Column(name = "education")
    private String education;

    @Column(name = "mainprogramminglangugage")
    private String mainProgrammingLangugage;

    @Column(name = "toeicscore")
    private String toeicScore;

    @Column(name = "experiencedetails")
    private String experienceDetails;

    @Column(name = "department")
    private String department;

    @Column(name = "location")
    private String location;

	/*// map trainee vs course n trainee - n course
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "course_trainee", joinColumns = @JoinColumn(name = "trainee_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<CourseEntity> courses = new ArrayList<>();*/

    // 1 TraineeEntity - N TraineeCourseEntity
    @OneToMany(mappedBy = "traineeEntity")
    private List<TraineeCourseEntity> traineeCourseEntities = new ArrayList<>();

    // map trainee vs role n trainee - 1 role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleTrainee;

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

    public List<TraineeCourseEntity> getTraineeCourseEntities() {
        return traineeCourseEntities;
    }

    public void setTraineeCourseEntities(List<TraineeCourseEntity> traineeCourseEntities) {
        this.traineeCourseEntities = traineeCourseEntities;
    }

    public RoleEntity getRoleTrainee() {
        return roleTrainee;
    }

    public void setRoleTrainee(RoleEntity roleTrainee) {
        this.roleTrainee = roleTrainee;
    }
}

package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainer")
public class TrainerEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "workingplace")
    private String workingPlace;

    @Column(name = "education")
    private String education;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "externalorinternal")
    private String externalOrInternal;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    // 1 TrainerEntity - N TrainerCourseEntity
    @OneToMany(mappedBy = "trainerEntity")
    private List<TrainerCourseEntity> trainerCourseEntities = new ArrayList<>();

    // Map Trainer vs Role | n trainer - 1 role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleTrainer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExternalOrInternal() {
        return externalOrInternal;
    }

    public void setExternalOrInternal(String externalOrInternal) {
        this.externalOrInternal = externalOrInternal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TrainerCourseEntity> getTrainerCourseEntities() {
        return trainerCourseEntities;
    }

    public void setTrainerCourseEntities(List<TrainerCourseEntity> trainerCourseEntities) {
        this.trainerCourseEntities = trainerCourseEntities;
    }

    public RoleEntity getRoleTrainer() {
        return roleTrainer;
    }

    public void setRoleTrainer(RoleEntity roleTrainer) {
        this.roleTrainer = roleTrainer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

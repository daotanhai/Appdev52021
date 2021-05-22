package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "trainingstaff")
public class TrainingStaffEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "workingplace")
    private String workingPlace;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    // Map TrainingStaff vs Role | n trainingstaff - 1 role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleTrainingStaff;

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEntity getRoleTrainingStaff() {
        return roleTrainingStaff;
    }

    public void setRoleTrainingStaff(RoleEntity roleTrainingStaff) {
        this.roleTrainingStaff = roleTrainingStaff;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

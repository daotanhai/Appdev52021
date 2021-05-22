package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code") // Biet duoc code admin va user
    private String code;

    // Map role vs user n role - n user
    /*
     * @ManyToMany(mappedBy = "roles") private List<UserEntity> users = new
     * ArrayList<>();
     */

    // Map role vs user 1 role - n user
    @OneToMany(mappedBy = "roleEntity")
    private List<UserEntity> users = new ArrayList<>();

    // Map role vs trainee 1 role - n trainee
    @OneToMany(mappedBy = "roleTrainee")
    private List<TraineeEntity> trainees = new ArrayList<>();

    // Map role vs trainee 1 role - n trainer
    @OneToMany(mappedBy = "roleTrainer")
    private List<TrainerEntity> trainers = new ArrayList<>();

    // Map role vs trainee 1 role - n training staff
    @OneToMany(mappedBy = "roleTrainingStaff")
    private List<TrainingStaffEntity> trainingStaffs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<TraineeEntity> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<TraineeEntity> trainees) {
        this.trainees = trainees;
    }

    public List<TrainerEntity> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TrainerEntity> trainers) {
        this.trainers = trainers;
    }

    public List<TrainingStaffEntity> getTrainingStaffs() {
        return trainingStaffs;
    }

    public void setTrainingStaffs(List<TrainingStaffEntity> trainingStaffs) {
        this.trainingStaffs = trainingStaffs;
    }


}

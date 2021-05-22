package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "trainee_course")
public class TraineeCourseEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traineeid")
    private TraineeEntity traineeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid")
    private CourseEntity courseEntityForTrainee;

    public TraineeEntity getTraineeEntity() {
        return traineeEntity;
    }

    public void setTraineeEntity(TraineeEntity traineeEntity) {
        this.traineeEntity = traineeEntity;
    }

    public CourseEntity getCourseEntityForTrainee() {
        return courseEntityForTrainee;
    }

    public void setCourseEntityForTrainee(CourseEntity courseEntityForTrainee) {
        this.courseEntityForTrainee = courseEntityForTrainee;
    }
}

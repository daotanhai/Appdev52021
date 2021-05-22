package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "trainer_course")
public class TrainerCourseEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerid")
    private TrainerEntity trainerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid")
    private CourseEntity courseEntityForTrainer;

    public TrainerEntity getTrainerEntity() {
        return trainerEntity;
    }

    public void setTrainerEntity(TrainerEntity trainerEntity) {
        this.trainerEntity = trainerEntity;
    }

    public CourseEntity getCourseEntityForTrainer() {
        return courseEntityForTrainer;
    }

    public void setCourseEntityForTrainer(CourseEntity courseEntityForTrainer) {
        this.courseEntityForTrainer = courseEntityForTrainer;
    }
}

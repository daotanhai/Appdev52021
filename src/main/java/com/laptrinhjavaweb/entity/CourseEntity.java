package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")

public class CourseEntity extends BaseEntity {
    @Column(name = "coursename")
    private String courseName;

    @Column(name = "shortdescription")
    private String shortDescription;

    // 1 courseEntity - N TrainerCourseEntity
    @OneToMany(mappedBy = "courseEntityForTrainer")
    private List<TrainerCourseEntity> trainerCourseEntitiesForTrainer = new ArrayList<>();

    // 1 courseEntity - N TraineeCourseEntity
    @OneToMany(mappedBy = "courseEntityForTrainee")
    private List<TraineeCourseEntity> traineeCourseEntitiesForTrainee = new ArrayList<>();

    // map CourseEntity vs CategoryCourse 1 course - 1 category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorycourse_id")
    private CourseCategoryEntity courseCategory;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<TrainerCourseEntity> getTrainerCourseEntitiesForTrainer() {
        return trainerCourseEntitiesForTrainer;
    }

    public void setTrainerCourseEntitiesForTrainer(List<TrainerCourseEntity> trainerCourseEntitiesForTrainer) {
        this.trainerCourseEntitiesForTrainer = trainerCourseEntitiesForTrainer;
    }

    public List<TraineeCourseEntity> getTraineeCourseEntitiesForTrainee() {
        return traineeCourseEntitiesForTrainee;
    }

    public void setTraineeCourseEntitiesForTrainee(List<TraineeCourseEntity> traineeCourseEntitiesForTrainee) {
        this.traineeCourseEntitiesForTrainee = traineeCourseEntitiesForTrainee;
    }

    public CourseCategoryEntity getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategoryEntity courseCategory) {
        this.courseCategory = courseCategory;
    }
}

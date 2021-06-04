package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "request_course")

public class RequestCourseEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeRequest;

    public CourseEntity getCourseRequest() {
        return courseRequest;
    }

    public void setCourseRequest(CourseEntity courseRequest) {
        this.courseRequest = courseRequest;
    }

    public TraineeEntity getTraineeRequest() {
        return traineeRequest;
    }

    public void setTraineeRequest(TraineeEntity traineeRequest) {
        this.traineeRequest = traineeRequest;
    }
}

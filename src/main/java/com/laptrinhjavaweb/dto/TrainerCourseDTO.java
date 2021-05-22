package com.laptrinhjavaweb.dto;

public class TrainerCourseDTO extends AbstractDTO<TrainerCourseDTO> {
    private Long courseId;
    private Long trainerId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
}

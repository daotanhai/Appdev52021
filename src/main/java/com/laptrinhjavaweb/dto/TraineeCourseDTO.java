package com.laptrinhjavaweb.dto;

public class TraineeCourseDTO extends AbstractDTO<TraineeCourseDTO> {
    private Long courseId;
    private Long traineeId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Long traineeId) {
        this.traineeId = traineeId;
    }
}

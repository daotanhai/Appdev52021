package com.laptrinhjavaweb.dto;

public class RequestCourseDTO extends AbstractDTO<RequestCourseDTO>{
    private Long courseId;
    private Long traineeId;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

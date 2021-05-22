package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO extends AbstractDTO<CourseDTO> {
    List<TraineeDTO> traineeList = new ArrayList<>();
    private String courseName;
    private String shortDescription;
    private Long courseCategoryId;
    private String courseCategoryNameCode;

    public List<TraineeDTO> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<TraineeDTO> traineeList) {
        this.traineeList = traineeList;
    }

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

    public Long getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Long courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public String getCourseCategoryNameCode() {
        return courseCategoryNameCode;
    }

    public void setCourseCategoryNameCode(String courseCategoryNameCode) {
        this.courseCategoryNameCode = courseCategoryNameCode;
    }


}

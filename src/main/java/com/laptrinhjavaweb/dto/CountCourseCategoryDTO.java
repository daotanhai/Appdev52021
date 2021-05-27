package com.laptrinhjavaweb.dto;

public class CountCourseCategoryDTO extends AbstractDTO<CountCourseCategoryDTO>{
    private String courseCategoryNameCode;
    private long numberOfCourses;

    public String getCourseCategoryNameCode() {
        return courseCategoryNameCode;
    }

    public void setCourseCategoryNameCode(String courseCategoryNameCode) {
        this.courseCategoryNameCode = courseCategoryNameCode;
    }

    public long getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(long numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }
}

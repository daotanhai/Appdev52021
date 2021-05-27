package com.laptrinhjavaweb.dto;

public class CourseCategoryDTO extends AbstractDTO<CourseCategoryDTO> {
    //name
    private String nameCourseCategory;
    // code
    private String courseCategoryNameCode;
    // description
    private String courseCategoryDescription;

    public String getNameCourseCategory() {
        return nameCourseCategory;
    }

    public void setNameCourseCategory(String nameCourseCategory) {
        this.nameCourseCategory = nameCourseCategory;
    }

    public String getCourseCategoryNameCode() {
        return courseCategoryNameCode;
    }

    public void setCourseCategoryNameCode(String courseCategoryNameCode) {
        this.courseCategoryNameCode = courseCategoryNameCode;
    }

    public String getCourseCategoryDescription() {
        return courseCategoryDescription;
    }

    public void setCourseCategoryDescription(String courseCategoryDescription) {
        this.courseCategoryDescription = courseCategoryDescription;
    }
}

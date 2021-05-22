package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorycourse")
public class CourseCategoryEntity extends BaseEntity {
    @Column(name = "namecoursecategory")
    private String nameCourseCategory;

    @Column(name = "coursecategorynamecode")
    private String courseCategoryNameCode;

    @Column(name = "coursecategorydescription")
    private String courseCategoryDescription;

    // map CategoryCourseEntity vs CourseEntity 1 category - n course

    @OneToMany(mappedBy = "courseCategory")
    private List<CourseEntity> courseEntity = new ArrayList<>();

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

    public List<CourseEntity> getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(List<CourseEntity> courseEntity) {
        this.courseEntity = courseEntity;
    }


}

package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.RequestCourseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRequestCourseService {
    void requestCourse(long[] ids);
    // get tong so cac request course duoi database
    int getTotalRequestCourse();
    // find all base pageable
    List<RequestCourseDTO> findAll(Pageable pageable);
}

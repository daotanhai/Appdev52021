package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CourseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICourseService {
    // Hiển thị theo page phân trang
    List<CourseDTO> findAll(Pageable pageable);

    List<CourseDTO> findAllCourse();

    int getTotalCourse();

    // Thêm + sửa
    CourseDTO findById(long id);

    CourseDTO save(CourseDTO courseDTO);

    // Xóa
    void delete(long[] ids);

    Map<String, String> findAll();

    List<CourseDTO> findCourses(CourseDTO courseDTO);

    List<CourseDTO> findCoursesByTraineeId(long id);
    List<CourseDTO> findCoursesByTrainerId(long id);
}

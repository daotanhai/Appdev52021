package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CourseCategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICourseCategoryService {
    // Hien thi ALL course category - LIST
    List<CourseCategoryDTO> findAll(Pageable pageable);

    // Tim course category theo id - Để get ra data ->update
    CourseCategoryDTO findById(Long id);

    // Luu course category
    CourseCategoryDTO saveCourseCategory(CourseCategoryDTO courseCategoryDTO);

    // delete course category
    void deleteCourseCategory(long[] ids);

    // get số lượng course category trong danh sách - Để phân trang
    int getTotalCourseCategory();

    // Có map để trans từ code name qua id
    // hiển thị thể loại ra view, nhưng khi chọn sẽ gửi về CODE.
    // name code chỉ để hiển thị, làm việc là CODE
    // vd: CODE = GIAO-DUC | NAME = Giáo dục
    Map<String, String> findAll();

    CourseCategoryDTO findCourseCategory(CourseCategoryDTO courseCategoryDTO);
}

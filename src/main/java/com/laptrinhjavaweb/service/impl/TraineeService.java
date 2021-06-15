package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.controller.trainingStaff.AssignCourseForTraineeController;
import com.laptrinhjavaweb.converter.CourseConverter;
import com.laptrinhjavaweb.converter.TraineeConverter;
import com.laptrinhjavaweb.converter.TraineeCourseConverter;
import com.laptrinhjavaweb.dto.TraineeCourseDTO;
import com.laptrinhjavaweb.dto.TraineeDTO;
import com.laptrinhjavaweb.entity.*;
import com.laptrinhjavaweb.repository.*;
import com.laptrinhjavaweb.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TraineeService implements ITraineeService {
    @Autowired
    AssignCourseForTraineeController assignCourseForTraineeController;
    @Autowired
    ITraineeRepository ITraineeRepository;
    @Autowired
    TraineeConverter traineeConverter;
    @Autowired
    IRoleRepository IRoleRepository;
    @Autowired
    ICourseRepository ICourseRepository;
    @Autowired
    CourseConverter courseConverter;
    @Autowired
    ITraineeCourseRepository ITraineeCourseRepository;
    @Autowired
    TraineeCourseConverter traineeCourseConverter;
    @Autowired
    IUserRepository IUserRepository;

    private long traineeId;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(long traineeId) {
        this.traineeId = traineeId;
    }

    @Override
    public List<TraineeDTO> findAll(Pageable pageable) {
        List<TraineeDTO> models = new ArrayList<TraineeDTO>();
        List<TraineeEntity> entities = ITraineeRepository.findAll(pageable).getContent();
        for (TraineeEntity items : entities) {
            TraineeDTO traineeDTO = traineeConverter.toDTO(items);
            models.add(traineeDTO);
        }
        return models;
    }

    @Override
    public TraineeDTO saveTrainee(TraineeDTO traineeDTO) {
        TraineeEntity traineeEntity = new TraineeEntity();
        UserEntity userEntity = new UserEntity();

        RoleEntity role = IRoleRepository.findOneByCode(traineeDTO.getRoleCode());
        // update neu id tu DTO khac null
        if (traineeDTO.getId() != null) {
            TraineeEntity oldTrainee = ITraineeRepository.findOne(traineeDTO.getId());
            UserEntity oldUser = IUserRepository.findUserEntityByUserName(oldTrainee.getUserName());
            // update trong bang User
            oldUser.setPassword(bCryptPasswordEncoder.encode(traineeDTO.getPassword()));
            oldUser.setFullName(traineeDTO.getName());
            IUserRepository.save(oldUser);
            // update trong bang trainee
            oldTrainee.setRoleTrainee(role);
            oldTrainee.setPassword(oldUser.getPassword());

            traineeEntity = traineeConverter.toEntity(oldTrainee, traineeDTO);
        }
        // them moi trainee
        if (traineeDTO.getId() == null) {
            traineeEntity = traineeConverter.toEntity(traineeDTO);
            // set username + password cho table user -> co the dang nhap duoc
            userEntity.setUserName(traineeEntity.getUserName());
            userEntity.setPassword(bCryptPasswordEncoder.encode(traineeDTO.getPassword()));
            userEntity.setRoleEntity(role);
            userEntity.setFullName(traineeDTO.getName());
            userEntity.setStatus(traineeDTO.getStatus());

            IUserRepository.save(userEntity);
            traineeEntity.setRoleTrainee(role);
            // SET riêng password cho trainee từ User Entity. Nếu không, thì sẽ không đăng nhập được vì password set cho user
            // bị set trước. Điều này sẽ làm password không còn khớp nữa.
            // Nên mình sẽ set riêng password cho user sau đó get password từ user và set cho trainee
            traineeEntity.setPassword(userEntity.getPassword());
        }
        return traineeConverter.toDTO(ITraineeRepository.save(traineeEntity));
    }

    @Override
    public List<TraineeCourseDTO> findCourseOnTraineeId(long id) {
        List<TraineeCourseEntity> traineeCourseEntities = ITraineeCourseRepository.findTraineeCourseEntitiesByTraineeEntity_Id(id);
        TraineeCourseDTO traineeCourseDTO = new TraineeCourseDTO();
        List<TraineeCourseDTO> models = new ArrayList<>();
        for (TraineeCourseEntity item : traineeCourseEntities) {
            traineeCourseDTO = traineeCourseConverter.toDTO(item);
            models.add(traineeCourseDTO);
        }
        return models;
    }

    @Override
    public void deleteTraineeCourse(long[] ids) {
        for (long id : ids) {
            TraineeCourseEntity traineeCourseEntity = ITraineeCourseRepository.findOne(id);
            ITraineeCourseRepository.delete(traineeCourseEntity);
        }
    }

    // truyen vao user id, tim ra trainee DTO
    @Override
    public TraineeDTO findTraineeByUserId(long id) {
        UserEntity userEntity = IUserRepository.findOne(id);
        TraineeEntity traineeEntity = ITraineeRepository.findTraineeEntityByUserName(userEntity.getUserName());
        return traineeConverter.toDTO(traineeEntity);
    }

    @Override
    @Transactional
    public void deleteTrainee(long[] ids) {
        for (long id : ids) {
            ITraineeRepository.delete(id);
        }
    }

    @Override
    public int getTotalTrainees() {
        // count() do JpaRepository cung cấp
        return (int) ITraineeRepository.count();
    }

    @Override
    public void saveCourseAssign(long[] ids) {
        // Trong mảng gửi về, thì phần tử đầu tiên của ids là id của trainee
        long traineeId = ids[0];
        // i bắt đầu = 1 vì phải bỏ qua giá trị của traineeId trong mảng
        for (int i = 1; i < ids.length; i++) {
            TraineeCourseEntity traineeCourseEntity = new TraineeCourseEntity();
            TraineeEntity traineeEntity = ITraineeRepository.findOne(traineeId);
            long courseIdOld = ids[i];
            // Tìm courseEntity dựa trên courseIdOle
            CourseEntity courseEntity = ICourseRepository.findOne(courseIdOld);
            // set trainee
            traineeCourseEntity.setTraineeEntity(traineeEntity);
            // set course
            traineeCourseEntity.setCourseEntityForTrainee(courseEntity);
            // set xong thì lưu
            ITraineeCourseRepository.save(traineeCourseEntity);
        }
        //traineeRepository.save(traineeEntity);
    }

    @Override
    public TraineeDTO findById(Long id) {
        TraineeEntity traineeEntity = ITraineeRepository.findOne(id);
        return traineeConverter.toDTO(traineeEntity);
    }

    @Override
    public List<TraineeDTO> findAll() {
        List<TraineeEntity> entities = ITraineeRepository.findAll();
        List<TraineeDTO> models = new ArrayList<>();
        for (TraineeEntity item : entities) {
            models.add(traineeConverter.toDTO(item));
        }
        return models;
    }

    @Override
    public void saveTraineeEntity(TraineeEntity traineeEntity) {
        this.ITraineeRepository.save(traineeEntity);
    }


}

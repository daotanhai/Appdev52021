package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TrainerConverter;
import com.laptrinhjavaweb.converter.TrainingStaffConverter;
import com.laptrinhjavaweb.converter.UserForAdminConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.TrainerEntity;
import com.laptrinhjavaweb.entity.TrainingStaffEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.TrainerRepository;
import com.laptrinhjavaweb.repository.TrainingStaffRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserForAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

// UserService dùng cho admin
@Service
public class UserForAdminService implements IUserForAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserForAdminConverter userForAdminConverter;

    @Autowired
    private TrainerConverter trainerConverter;

    @Autowired
    private TrainingStaffConverter trainingStaffConverter;

    @Autowired
    private TrainingStaffRepository trainingStaffRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> models = new ArrayList<UserDTO>();
        // SELECT * thông tin của table new dựa theo pageable
        // nếu k dựa theo pageable nó sẽ lấy hết danh sách và hiển thị ra ngoài
        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        // do data tu userEntity(item) qua UserDTO(models)
        for (UserEntity item : entities) {
            UserDTO userDTO = userForAdminConverter.toDTO(item);
            // put userDTO vao models
            models.add(userDTO);
        }
        return models;
    }

    // Lưu user + cập nhật user
    @Override
    public UserDTO save(UserDTO userDTO) {
        // Lấy ra đối tượng RoleEntity cần phải update dựa trên role(string) gửi về
        // từ client trong DTO
        RoleEntity role = roleRepository.findOneByCode(userDTO.getRole());

        UserEntity userEntity = new UserEntity();
        // Có id trả về từ DTO -> UPDATE user (id khác NULL)
        if (userDTO.getId() != null) {
            // get ra id của user cũ dựa trên hàm findOne(framework) từ RoleRepository
            UserEntity oldUser = userRepository.findOne(userDTO.getId());
            // get role cu cua no, truong hop no update role
            RoleEntity oldRole = oldUser.getRoleEntity();
            // update cho trainer
            if (userDTO.getRole().equalsIgnoreCase("TRAINER")) {
                // hiện tại là TRAINER nhưng cũ là TRAINING-STAFF
                if (oldRole.getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                    // XÓA TRAINING STAFF ENTITY CŨ ĐI
                    TrainingStaffEntity oldTrainingStaffEntity = trainingStaffRepository.findTrainingStaffEntityByUserName(oldUser.getUserName());
                    trainingStaffRepository.delete(oldTrainingStaffEntity);
                    // CHANGE QUA TRAINER ĐỂ SET MỚI 1 TRAINER ENTITY
                    TrainerEntity trainerEntity = new TrainerEntity();
                    trainerEntity.setUserName(userDTO.getUserName());
                    trainerEntity.setRoleTrainer(role);
                    trainerRepository.save(trainerEntity);
                } else if (oldRole.getCode().equalsIgnoreCase("TRAINER")) {
                    // Đầu tiên là lấy ra oldTrainer
                    TrainerEntity oldTrainerEntity = trainerRepository.findTrainerEntityByUserName(oldUser.getUserName());
                    oldTrainerEntity.setUserName(userDTO.getUserName()); // set userName moi
                    trainerRepository.save(oldTrainerEntity); // save lai
                }
            }
            // update cho training staff
            else if (userDTO.getRole().equalsIgnoreCase("TRAINING-STAFF")) {
                // HIỆN TẠI LÀ TRAINING STAFF NHƯNG CŨ LÀ TRAINER
                if (oldRole.getCode().equalsIgnoreCase("TRAINER")) {
                    // XÓA TRAINER ENTITY CŨ
                    TrainerEntity oldTrainerEntity = trainerRepository.findTrainerEntityByUserName(oldUser.getUserName());
                    trainerRepository.delete(oldTrainerEntity);
                    // QUA TRAINING STAFF ENTITY ĐỂ SET
                    TrainingStaffEntity trainingStaffEntity = new TrainingStaffEntity();
                    trainingStaffEntity.setUserName(userDTO.getUserName());
                    trainingStaffEntity.setRoleTrainingStaff(role);
                    trainingStaffRepository.save(trainingStaffEntity);
                } else if (oldRole.getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                    TrainingStaffEntity oldTrainingStaffEntity = trainingStaffRepository.findTrainingStaffEntityByUserName(oldUser.getUserName());
                    oldTrainingStaffEntity.setUserName(userDTO.getUserName());
                    trainingStaffRepository.save(oldTrainingStaffEntity);
                }
            }
            // Role là 1 field từ table khác. K có trong UserEntity nên phải thêm riêng
            // update role của user cũ
            oldUser.setRoleEntity(role);
            userEntity = userForAdminConverter.toEntity(oldUser, userDTO);
        }
        // id = null se them moi user
        if (userDTO.getId() == null) {
            userEntity = userForAdminConverter.toEntity(userDTO);
            userEntity.setRoleEntity(role);
        }
        // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
        // va xử lý cần sang DTO
        // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn save, ở đây
        // là user entity va trainer entity
        return userForAdminConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public void saveTrainerRoleAndUserName(UserDTO userDTO) {
        TrainerEntity trainerEntity = new TrainerEntity();
        RoleEntity role = roleRepository.findOneByCode(userDTO.getRole());
        if (userDTO.getId() == null) {
            trainerEntity.setUserName(userDTO.getUserName());
            trainerEntity.setRoleTrainer(role);
            trainerEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        }
        // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
        // va xử lý cần sang DTO
        // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn save, ở đây
        // trainer entity
        // cần return vì sau này sẽ để hiển thị lại các fields đã lưu
        trainerConverter.toDTO(trainerRepository.save(trainerEntity));
    }

    @Override
    public UserDTO findById(long id) {
        UserEntity userEntity = userRepository.findOne(id);
        return userForAdminConverter.toDTO(userEntity);
    }

    @Override
    public int getTotalItem() {
        return (int) userRepository.count();
    }

    @Override
    public void saveTrainingStaffRoleAndUserNameAndPassword(UserDTO userDTO) {
        TrainingStaffEntity trainingStaffEntity = new TrainingStaffEntity();
        // Tìm ra đối tượng role(bao gồm các fields trong table mySQL)
        // dựa theo code(ADMIN, TRAINING-STAFF, TRAINER) gửi về từ DTO
        // trùng cái nào thì get ra đối tượng đó, chứa các thông tin như trong field
        RoleEntity role = roleRepository.findOneByCode(userDTO.getRole());
        // id trong userDTO = rỗng nghĩa là đang thêm mới. Otherwise, update
        if (userDTO.getId() == null) {
            trainingStaffEntity.setUserName(userDTO.getUserName());
            // Set 1 đối tượng mới vào table trainingstaff
            // đối tượng này mình chỉ thêm chủ động role vào table trainingstaff
            trainingStaffEntity.setRoleTrainingStaff(role);
        }
        // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
        // va xử lý cần sang DTO
        // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn save, ở đây
        // trainer entity
        trainingStaffConverter.toDTO(trainingStaffRepository.save(trainingStaffEntity));
    }

    // Xoa user
    @Override
    @Transactional // quản lý commit / rollback - tựa như JDBC.
    public void delete(long[] ids) {
        // ids là 1 mảng, dùng forEach duyệt mảng ids và xóa lần lượt
        for (long id : ids) {
            String userName;
            // tìm được role code + username trong bảng user
            UserEntity userEntity = userRepository.findOne(id);
            // get username by id trong bang user
            userName = userEntity.getUserName();
            if (userEntity.getRoleEntity().getCode().equalsIgnoreCase("TRAINER")) {
                // get trainerEntity dua theo userName
                TrainerEntity trainerEntity = trainerRepository.findTrainerEntityByUserName(userName);
                trainerRepository.delete(trainerEntity);
            } else if (userEntity.getRoleEntity().getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                TrainingStaffEntity trainingStaffEntity = trainingStaffRepository.findTrainingStaffEntityByUserName(userName);
                trainingStaffRepository.delete(trainingStaffEntity);
            }
            if (!userEntity.getRoleEntity().getCode().equalsIgnoreCase("ADMIN")) {
                userRepository.delete(id);
            }
			/*// delete từ framework support cho
			userRepository.delete(id);*/
        }
    }
}

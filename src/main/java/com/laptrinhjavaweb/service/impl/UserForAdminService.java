package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TrainerConverter;
import com.laptrinhjavaweb.converter.TrainingStaffConverter;
import com.laptrinhjavaweb.converter.UserForAdminConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.TrainerEntity;
import com.laptrinhjavaweb.entity.TrainingStaffEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IRoleRepository;
import com.laptrinhjavaweb.repository.ITrainerRepository;
import com.laptrinhjavaweb.repository.ITrainingStaffRepository;
import com.laptrinhjavaweb.repository.IUserRepository;
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
    private IUserRepository IUserRepository;

    @Autowired
    private IRoleRepository IRoleRepository;

    @Autowired
    private ITrainerRepository ITrainerRepository;

    @Autowired
    private UserForAdminConverter userForAdminConverter;

    @Autowired
    private TrainerConverter trainerConverter;

    @Autowired
    private TrainingStaffConverter trainingStaffConverter;

    @Autowired
    private ITrainingStaffRepository ITrainingStaffRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> models = new ArrayList<UserDTO>();
        // SELECT * thông tin của table new dựa theo pageable
        // nếu k dựa theo pageable nó sẽ lấy hết danh sách và hiển thị ra ngoài
        List<UserEntity> entities = IUserRepository.findAll(pageable).getContent();
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
    public UserDTO saveUser(UserDTO userDTO) {
        // Lấy ra đối tượng RoleEntity cần phải update dựa trên role(string) gửi về
        // từ client trong DTO
        RoleEntity role = IRoleRepository.findOneByCode(userDTO.getRole());

        UserEntity userEntity = new UserEntity();
        // KHONG UPDATE ADMIN, NEU GUI VE USERNAME LA ADMIN THI KHONG SAVE GI
        if (!userDTO.getUserName().equalsIgnoreCase("admin")){
            // Có id trả về từ DTO -> UPDATE user (id khác NULL)
            if (userDTO.getId() != null) {
                // get ra id của user cũ dựa trên hàm findOne(framework) từ RoleRepository
                UserEntity oldUser = IUserRepository.findOne(userDTO.getId());
                // get role cu cua no, truong hop no update role
                RoleEntity oldRole = oldUser.getRoleEntity();
                // update cho trainer
                if (userDTO.getRole().equalsIgnoreCase("TRAINER")) {
                    // hiện tại là TRAINER nhưng cũ là TRAINING-STAFF
                    if (oldRole.getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                        // XÓA TRAINING STAFF ENTITY CŨ ĐI
                        TrainingStaffEntity oldTrainingStaffEntity = ITrainingStaffRepository.findTrainingStaffEntityByUserName(oldUser.getUserName());
                        ITrainingStaffRepository.delete(oldTrainingStaffEntity);
                        // CHANGE QUA TRAINER ĐỂ SET MỚI 1 TRAINER ENTITY
                        TrainerEntity trainerEntity = new TrainerEntity();
                        trainerEntity.setUserName(userDTO.getUserName());
                        trainerEntity.setRoleTrainer(role);
                        ITrainerRepository.save(trainerEntity);
                    } else if (oldRole.getCode().equalsIgnoreCase("TRAINER")) {
                        // Đầu tiên là lấy ra oldTrainer
                        TrainerEntity oldTrainerEntity = ITrainerRepository.findTrainerEntityByUserName(oldUser.getUserName());
                        oldTrainerEntity.setUserName(userDTO.getUserName()); // set userName moi
                        ITrainerRepository.save(oldTrainerEntity); // saveUser lai
                    }
                }
                // update cho training staff
                else if (userDTO.getRole().equalsIgnoreCase("TRAINING-STAFF")) {
                    // HIỆN TẠI LÀ TRAINING STAFF NHƯNG CŨ LÀ TRAINER
                    if (oldRole.getCode().equalsIgnoreCase("TRAINER")) {
                        // XÓA TRAINER ENTITY CŨ
                        TrainerEntity oldTrainerEntity = ITrainerRepository.findTrainerEntityByUserName(oldUser.getUserName());
                        ITrainerRepository.delete(oldTrainerEntity);
                        // QUA TRAINING STAFF ENTITY ĐỂ SET
                        TrainingStaffEntity trainingStaffEntity = new TrainingStaffEntity();
                        trainingStaffEntity.setUserName(userDTO.getUserName());
                        trainingStaffEntity.setRoleTrainingStaff(role);
                        ITrainingStaffRepository.save(trainingStaffEntity);
                    } else if (oldRole.getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                        TrainingStaffEntity oldTrainingStaffEntity = ITrainingStaffRepository.findTrainingStaffEntityByUserName(oldUser.getUserName());
                        oldTrainingStaffEntity.setUserName(userDTO.getUserName());
                        ITrainingStaffRepository.save(oldTrainingStaffEntity);
                    }
                }
                // Role là 1 field từ table khác. K có trong UserEntity nên phải thêm riêng
                // update role của user cũ
                // Làm với bảng USER, những logic ở trên là thao tác với trainer/training staff table
                oldUser.setRoleEntity(role);
                userEntity = userForAdminConverter.toEntity(oldUser, userDTO);
                return userForAdminConverter.toDTO(IUserRepository.save(userEntity));
            }
            // id = null se them moi user
            if (userDTO.getId() == null && IUserRepository.findUserEntityByUserName(userDTO.getUserName())==null) {
                userEntity = userForAdminConverter.toEntity(userDTO);
                userEntity.setRoleEntity(role);
                return userForAdminConverter.toDTO(IUserRepository.save(userEntity));
            }

        }
        // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
        // va xử lý cần sang DTO
        // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn saveUser, ở đây
        // là user entity va trainer entity
        return null;
    }

    @Override
    public void saveTrainerRoleAndUserName(UserDTO userDTO) {
        if (IUserRepository.findUserEntityByUserName(userDTO.getUserName())==null){
            TrainerEntity trainerEntity = new TrainerEntity();
            RoleEntity role = IRoleRepository.findOneByCode(userDTO.getRole());
            if (userDTO.getId() == null) {
                trainerEntity.setUserName(userDTO.getUserName());
                trainerEntity.setRoleTrainer(role);
                trainerEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            }
            // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
            // va xử lý cần sang DTO
            // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn saveUser, ở đây
            // trainer entity
            // cần return vì sau này sẽ để hiển thị lại các fields đã lưu
            trainerConverter.toDTO(ITrainerRepository.save(trainerEntity));
        }

    }

    @Override
    public UserDTO findById(long id) {
        UserEntity userEntity = IUserRepository.findOne(id);
        return userForAdminConverter.toDTO(userEntity);
    }

    @Override
    public int getTotalItem() {
        return (int) IUserRepository.count();
    }

    @Override
    public void saveTrainingStaffRoleAndUserNameAndPassword(UserDTO userDTO) {
        if (IUserRepository.findUserEntityByUserName(userDTO.getUserName())==null){
            TrainingStaffEntity trainingStaffEntity = new TrainingStaffEntity();
            // Tìm ra đối tượng role(bao gồm các fields trong table mySQL)
            // dựa theo code(ADMIN, TRAINING-STAFF, TRAINER) gửi về từ DTO
            // trùng cái nào thì get ra đối tượng đó, chứa các thông tin như trong field
            RoleEntity role = IRoleRepository.findOneByCode(userDTO.getRole());
            // id trong userDTO = rỗng nghĩa là đang thêm mới. Otherwise, update
            if (userDTO.getId() == null) {
                trainingStaffEntity.setUserName(userDTO.getUserName());
                // Set 1 đối tượng mới vào table trainingstaff
                // đối tượng này mình chỉ thêm chủ động role vào table trainingstaff
                trainingStaffEntity.setRoleTrainingStaff(role);
            }
            // khi cập nhật hay thêm mới là làm với entity. Nhưng để đưa đối tượng ra view
            // va xử lý cần sang DTO
            // dùng convert và gọi hàm SAVE ( framework), truyền vào entity muốn saveUser, ở đây
            // trainer entity
            trainingStaffConverter.toDTO(ITrainingStaffRepository.save(trainingStaffEntity));
        }
    }

    // Xoa user
    @Override
    @Transactional // quản lý commit / rollback - tựa như JDBC.
    public void delete(long[] ids) {
        // ids là 1 mảng, dùng forEach duyệt mảng ids và xóa lần lượt
        for (long id : ids) {
            String userName;
            // tìm được role code + username trong bảng user
            UserEntity userEntity = IUserRepository.findOne(id);
            // get username by id trong bang user
            userName = userEntity.getUserName();
            if (userEntity.getRoleEntity().getCode().equalsIgnoreCase("TRAINER")) {
                // get trainerEntity dua theo userName
                TrainerEntity trainerEntity = ITrainerRepository.findTrainerEntityByUserName(userName);
                ITrainerRepository.delete(trainerEntity);
            } else if (userEntity.getRoleEntity().getCode().equalsIgnoreCase("TRAINING-STAFF")) {
                TrainingStaffEntity trainingStaffEntity = ITrainingStaffRepository.findTrainingStaffEntityByUserName(userName);
                ITrainingStaffRepository.delete(trainingStaffEntity);
            }
            if (!userEntity.getRoleEntity().getCode().equalsIgnoreCase("ADMIN")) {
                IUserRepository.delete(id);
            }
			/*// delete từ framework support cho
			userRepository.delete(id);*/
        }
    }
    /*// tạo ra chuỗi string OTP với 8 ký tự, sau đó set vào user + expired time
    @Override
    public void generateOneTimePassword(UserEntity userEntity) throws UnsupportedEncodingException, MessagingException {
        String OTP = RandomStringUtils.random(8);
        String encodedOTP = bCryptPasswordEncoder.encode(OTP);
        userEntity.setOneTimePassword(encodedOTP);
        userEntity.setOtpRequestedTime(new Date());
        userRepository.saveUser(userEntity);
        sendOTPEmail(userEntity,OTP);
    }

    @Override
    public void sendOTPEmail(UserEntity userEntity, String OTP) throws UnsupportedEncodingException, MessagingException{
        MimeMessage message = mailSender.mailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("daotanhai123@gmail.com","Helper");
        helper.setTo(userEntity.getEmail());
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello " + userEntity.getUserName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.mailSender().send(message);
    }

    @Override
    public void clearOTP(UserEntity userEntity) {
        userEntity.setOneTimePassword(null);
        userEntity.setOtpRequestedTime(null);
        userRepository.saveUser(userEntity);
    }*/
}

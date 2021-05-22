package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TrainerDTO;
import com.laptrinhjavaweb.entity.TrainerEntity;
import org.springframework.stereotype.Component;

// Dùng cho Training Staff và Trainer
@Component
public class TrainerConverter {
    // convert TrainerEntity to DTO
    // lay tu table len phai lay het cac thu trong table chinh + table lien quan nhu la topic va role
    public TrainerDTO toDTO(TrainerEntity trainerEntity) {
        TrainerDTO result = new TrainerDTO();
        result.setId(trainerEntity.getId());
        result.setUserName(trainerEntity.getUserName());
        result.setPassWord(trainerEntity.getPassword());
        result.setName(trainerEntity.getName());
        result.setWorkingPlace(trainerEntity.getWorkingPlace());
        result.setEducation(trainerEntity.getEducation());
        result.setTelephone(trainerEntity.getTelephone());
        result.setEmail(trainerEntity.getEmail());
        result.setExternalOrInternal(trainerEntity.getExternalOrInternal());
        //result.setTopicName(trainerEntity.getTopics());
        // vi set Role cua DTO la String, phai chuyen tu Interger (role_id la so) sang code(string)
        // table trung gian trainer vs role - Không cần role nữa vì training staff với trainer chỉ
        // update các fields thông tin
        //result.setRole(trainerEntity.getRoleTrainer().getCode());
        return result;
    }

    // convert TrainerDTO to TrainerEntity
    // gui data xuong server, thi chi can set cac field dang co, con field thuoc table khac
    // xu ly trong service sau
    public TrainerEntity toEntity(TrainerDTO trainerDTO) {
        TrainerEntity result = new TrainerEntity();
        result.setName(trainerDTO.getName());
        result.setUserName(trainerDTO.getUserName());
        result.setWorkingPlace(trainerDTO.getWorkingPlace());
        result.setEducation(trainerDTO.getEducation());
        result.setTelephone(trainerDTO.getTelephone());
        result.setEmail(trainerDTO.getEmail());
        result.setExternalOrInternal(trainerDTO.getExternalOrInternal());
        //result.setTopics(trainerDTO.getTopicName());
        return result;
    }

    // Update Trainer
    public TrainerEntity toEntity(TrainerEntity result, TrainerDTO trainerDTO) {
        result.setName(trainerDTO.getName());
        result.setUserName(trainerDTO.getUserName());
        result.setWorkingPlace(trainerDTO.getWorkingPlace());
        result.setEducation(trainerDTO.getEducation());
        result.setTelephone(trainerDTO.getTelephone());
        result.setEmail(trainerDTO.getEmail());
        result.setExternalOrInternal(trainerDTO.getExternalOrInternal());
        //result.setTopics(trainerDTO.getTopicName());
        return result;
    }
}

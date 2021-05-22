package com.laptrinhjavaweb.dto;

public class TrainerForTrainingStaffDTO extends AbstractDTO<TrainerForTrainingStaffDTO>{
    private String name;
    private String workingPlace;
    private String education;
    private String telephone;
    private String email;
    private String externalOrInternal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExternalOrInternal() {
        return externalOrInternal;
    }

    public void setExternalOrInternal(String externalOrInternal) {
        this.externalOrInternal = externalOrInternal;
    }
}

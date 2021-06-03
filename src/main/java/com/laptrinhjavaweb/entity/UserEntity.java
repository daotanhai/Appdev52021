package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
// Nếu làm với postgre, phải đặt tên lại cho bảng user thế này, nếu không sẽ lỗi ko tạo đc bảng USER
@Table(name = "`Appuser`")
public class UserEntity extends BaseEntity {
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 phut'

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column
    private Integer status;

    /*@Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;

    @Column(name = "email")
    private String email;*/

    /*//check OTP expired or not
    public boolean isOTPRequired() {
        if (this.getOneTimePassword() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }

        return true;
    }*/

    /*
     * @ManyToMany(fetch = FetchType.LAZY) // Tạo table trung gian giữa user và
     * role. Sau đó join column userID và roleID để làm khóa ngoại
     *
     * @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"),
     * inverseJoinColumns = @JoinColumn(name = "roleid")) private List<RoleEntity>
     * roles = new ArrayList<>();
     */
// RoleEntity trả về chuỗi các role user đó. 

    // Map UserEntity vs RoleEntity n user - 1 role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    /*public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Date getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Date otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/
}

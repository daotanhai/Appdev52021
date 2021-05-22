package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column
    private Integer status;

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

}

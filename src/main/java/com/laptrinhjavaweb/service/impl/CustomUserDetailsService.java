package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    public long userId;
    // Authentication
    @Autowired
    private UserRepository userRepository;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    // Tu dong tim theo password, k can them nua. No hoat dong ngam

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
        if (userEntity != null) {
            setUserId(userEntity.getId());
        }
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Vai trò user - 1 user có nhiều role - get role của user
        // Khi mà nhận được username và tìm xem có username khớp hay k, thì nếu nó khớp thì bài toán
        // đặt ra ở đây là làm sao mình có thể get được Code của username đó ?
        // thì mình nhìn vào DB, bảng user k có field code. Chỉ có bảng role mới có
        // cho nên, vòng lặp for ở dưới mình phải sử dụng tới đối tượng RoleEntity mới getCode() được
        // userEntity.getRoles() là nó sẽ get hết các role trong bản user, từng role sẽ ứng với 1 code
        // bởi vì user có thể có nhiều role do đó có thể có nhiều code riêng. Nên phải get hết
        // userEntity.getRoles() là nó dùng cái username đó, lấy ra cái list role của username đó trong
        // bảng role, vì mình đã dùng JPA để tạo user_role trong bảng user, nên list đó chỉ return
        // về role. Và bây giờ mình sẽ cho lặp từng role của user đó; Mỗi lần lặp
        // mình sẽ getCode() trong cái bảng role ra.
        List<GrantedAuthority> authorities = new ArrayList<>();
        // vi 1 user nhieu role nen dung for
        /*
         * for (RoleEntity role : userEntity.getRoles()) {
         * authorities.add(new SimpleGrantedAuthority(role.getCode())); }
         */
        // nhung bay gio 1 user chi co 1 role, nen minh chi can get luon role ra
        RoleEntity role = userEntity.getRoleEntity();
        authorities.add(new SimpleGrantedAuthority(role.getCode()));
        // Put thong tin user vào list gồm username, pass, role code. true true true true là constructor, enable tất cả
        // các constructor của spring security mà nó cung cấp
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true, authorities);
        myUser.setFullName(userEntity.getFullName());
        return myUser;
    }

}

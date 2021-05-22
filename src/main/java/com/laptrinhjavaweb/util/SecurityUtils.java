package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.dto.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    public static MyUser getPrincipal() {
        MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }

    @SuppressWarnings("unchecked")
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        // get thông tin đã được put từ customUserDetailsService: list GrantedAuthority
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
            // getAuthority() là 1 hàm có sẵn của spring security, nó đi cùng với GrantedAuthority List
            // getAutority -> get được ADMIN or USER hoặc cả 2. 
            // là 1 list role của username đó
            // 1 list role của username đó mình đã put vào từ class customUserDetailsService
        }
        return results;
    }
}
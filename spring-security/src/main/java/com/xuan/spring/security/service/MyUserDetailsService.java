package com.xuan.spring.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuan.spring.security.entity.Users;
import com.xuan.spring.security.mapper.UsersMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/5/5 10:49
 * @description
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final UsersMapper usersMapper;

    public MyUserDetailsService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username",username);

        Users users = usersMapper.selectOne(queryWrapper);

        if (users == null){
            throw new  UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");

        return new User(username,new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}

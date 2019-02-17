package com.su.subike.user.service;

import com.su.subike.user.dao.UserMapper;
import com.su.subike.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login() {
        User user = new User();
        user.setId(1L);
        userMapper.insertSelective(user);
        return null;
    }
}

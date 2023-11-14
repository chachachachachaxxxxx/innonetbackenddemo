package com.example.innonetbackenddemo.controller;

import com.example.innonetbackenddemo.entity.User;
import com.example.innonetbackenddemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users/{uid}")
    public User queryByUid(@PathVariable("uid") long uid) {
        return userMapper.selectByUid(uid);
    }

    @PostMapping("/users/")
    public boolean createUser(@RequestBody User user){
        return userMapper.createUser(user.getUid(), user.getAuthority(), user.getUname(), user.getEmail());
    }

    @PutMapping("/users/{uid}")
    public User updateUser(@PathVariable("uid") long uid, @RequestBody User user) {
        userMapper.updateUser(uid, user.getAuthority(), user.getUname(), user.getEmail());
        return userMapper.selectByUid(uid);
    }
//    @PostMapping("/sessions/")
//    public void login() {
//
//    }
}

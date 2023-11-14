package com.example.innonetbackenddemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.innonetbackenddemo.entity.ProjectData;
import com.example.innonetbackenddemo.entity.User;
import com.example.innonetbackenddemo.mapper.ProjectDataMapper;
import com.example.innonetbackenddemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectDataMapper projectDataMapper;

    @GetMapping("/projects/{pid}")
    public String queryByPid(@PathVariable("pid") long pid) {
        // ProjectData projectData = projectDataMapper.selectByPid(pid);
        return JSON.toJSONString( projectDataMapper.selectByPid(pid));
    }

    @GetMapping("/projects")
    public List<ProjectData> selectAll() {
        return projectDataMapper.selectAll();
    }
    @PostMapping("/projects")
    public boolean createProject(@RequestBody ProjectData projectData){

//        return userMapper.createUser(user.getUid(), user.getAuthority(), user.getUname(), user.getEmail());
       //  projectDataMapper.createProject(projectData.getName(), projectData.getProjectDataAbstract(), projectData.getCategory(), projectData.getAuthor(), projectData.getContent());
        if (projectDataMapper.selectByPid(projectData.getid()) != null)
            return false;
        return projectDataMapper.createProject(projectData.getid(),projectData.getName(), projectData.getProjectDataAbstract(),JSON.toJSONString(projectData.getCategory()), JSON.toJSONString(projectData.getAuthor()), projectData.getContent());
    }

//    @PutMapping("/users/{uid}")
//    public User updateUser(@PathVariable("uid") long uid, @RequestBody User user) {
//        userMapper.updateUser(uid, user.getAuthority(), user.getUname(), user.getEmail());
//        return userMapper.selectByUid(uid);
//    }
}

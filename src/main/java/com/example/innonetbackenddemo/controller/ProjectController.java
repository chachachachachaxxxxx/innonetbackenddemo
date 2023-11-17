package com.example.innonetbackenddemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.innonetbackenddemo.entity.ProjectData;
import com.example.innonetbackenddemo.entity.User;
import com.example.innonetbackenddemo.mapper.ProjectDataMapper;
import com.example.innonetbackenddemo.mapper.UserMapper;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.bccr.v1_0_0.request.AddHashregisterRequest;
import cn.com.antcloud.api.bccr.v1_0_0.request.GetUploadurlRequest;
import cn.com.antcloud.api.bccr.v1_0_0.response.AddHashregisterResponse;
import cn.com.antcloud.api.bccr.v1_0_0.response.GetUploadurlResponse;

@RestController
public class ProjectController {
    @Autowired
    private ProjectDataMapper projectDataMapper;
    /**
     * 网关地址，每个环境的网关地址都不同，建议做成配置项
     */
    private static final String ENDPOINT = "";

    /**
     * 调用方租户AK，每个环境的AK都不同，建议做成配置项
     */
    private static final String ACCESS_KEY = "";

    /**
     * 调用方租户SK，每个环境的AK都不同，建议做成配置项
     */
    private static final String SECRET_KEY = "";

    private static String getSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Convert byte array to hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
    public boolean uploadToChain(ProjectData projectData) throws InterruptedException, NoSuchAlgorithmException {
        AntFinTechProfile antFinTechProfile = AntFinTechProfile.getProfile(
                ENDPOINT,
                ACCESS_KEY,
                SECRET_KEY
        );
        AntFinTechApiClient antFinTechApiClient = new AntFinTechApiClient(antFinTechProfile);

        AddHashregisterRequest addHashregisterRequest = new AddHashregisterRequest();
        addHashregisterRequest.setHash(getSHA256Hash(projectData.getContent()));
        addHashregisterRequest.setHashType("SHA256");

        addHashregisterRequest.setLength(ClassLayout.parseInstance(projectData).instanceSize());
        addHashregisterRequest.setType("TEXT");
        addHashregisterRequest.setName(projectData.getName());
        addHashregisterRequest.setCertName(projectData.getAuthor()[0].getUname());
        addHashregisterRequest.setCertNo("614870198001014479");
        addHashregisterRequest.setCertType("IDENTITY_CARD");
        // 发送请求，并获取结果
        AddHashregisterResponse response = antFinTechApiClient.execute(addHashregisterRequest);
        if (response.isSuccess()) {
            System.out.println("success registerId:" + response.getRegisterId() + " reqMsgId:"+response.getReqMsgId());
            projectDataMapper.uploadProject(projectData.getid(), response.getRegisterId());
            return true;
        } else {
            // 如果调用失败，打印结果码
            System.out.println("failed:" + response.getResultCode() + " " + response.getResultMsg());
            return false;
        }
    }

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

    @PutMapping("/projects/{pid}")
    public boolean updateUser(@PathVariable("pid") long pid, @RequestBody ProjectData projectData) {
        return projectDataMapper.updateProject(pid, projectData.getName(), projectData.getProjectDataAbstract(),  JSON.toJSONString(projectData.getCategory()), JSON.toJSONString(projectData.getAuthor()),projectData.getContent());
    }
}

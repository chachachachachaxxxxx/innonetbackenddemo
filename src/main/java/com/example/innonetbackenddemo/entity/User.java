package com.example.innonetbackenddemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value="users", autoResultMap = true)
public class User {
    /**
     * 用户分类
     */
    private String authority;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户账号
     */
    private long uid;
    /**
     * 用户姓名
     */
    private String uname;

    public String getAuthority() { return authority; }
    public void setAuthority(String value) { this.authority = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public long getUid() { return uid; }
    public void setUid(long value) { this.uid = value; }

    public String getUname() { return uname; }
    public void setUname(String value) { this.uname = value; }
}
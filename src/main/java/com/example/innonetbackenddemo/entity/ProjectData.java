package com.example.innonetbackenddemo.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

@TableName(value="projectData", autoResultMap = true)
/**
 * ProjectData
 */
public class ProjectData {
    /**
     * 项目摘要
     */
    private String projectDataAbstract;
    /**
     * 项目作者
     */
    @TableField(value="Author",typeHandler = JacksonTypeHandler.class)
    private User[] author;
    /**
     * 项目类型
     */
    @TableField(value="category",typeHandler = JacksonTypeHandler.class)
    private Category category;
    /**
     * 内容
     */
    private String content;
    /**
     * 项目ID编号
     */
    private long id;
    /**
     * 项目名称
     */
    private String name;

    public String getProjectDataAbstract() { return projectDataAbstract; }
    public void setProjectDataAbstract(String value) { this.projectDataAbstract = value; }

    public User[] getAuthor() { return author; }
    public void setAuthor(User[] value) { this.author = value; }

    public Category getCategory() { return category; }
    public void setCategory(Category value) { this.category = value; }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}

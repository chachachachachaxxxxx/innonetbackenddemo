package com.example.innonetbackenddemo.entity;

/**
 * category
 */
public class Category {
    /**
     * 分类ID
     */
    private long id;
    /**
     * 分类名称
     */
    private String name;

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}

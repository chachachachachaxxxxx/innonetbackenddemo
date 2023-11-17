package com.example.innonetbackenddemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.innonetbackenddemo.entity.ProjectData;
import com.example.innonetbackenddemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectDataMapper extends BaseMapper<ProjectData> {

    //@Select("select * from ProjectData")
    List<ProjectData> selectAll();

    // @Select("select * from ProjectData where id = #{pid}")
    ProjectData selectByPid(long pid);

    // @Insert("insert into ProjectData values(#{name}, #{abstract2}, #{category}, #{author}, #{content})")
    boolean createProject(long id, String name, String abstract2, String category, String author, String content);

    @Update("update projectData set registerId = #{registerId} where id = #{pid}")
    boolean uploadProject(long pid, String registerId);
    @Update("update Users set name=#{name},abstract=#{abstract2},category=#{category}, Author=#{author}, Content=#{content} where id =#{pid}")
    boolean updateProject(long pid, String name, String abstract2, String category, String author, String content);

    @Delete("delete from ProjectData where id = #{pid}")
    boolean deleteProject(long pid);

}

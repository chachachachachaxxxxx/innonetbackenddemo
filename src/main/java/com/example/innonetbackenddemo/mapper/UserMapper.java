package com.example.innonetbackenddemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.innonetbackenddemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from Users where uid=#{uid}")
    User selectByUid(long uid);

    @Insert("insert into Users(uid, authority, uname, email) values(#{uid}, #{authority}, #{uname}, #{email})")
    boolean createUser(long uid, String authority, String uname, String email);

    @Update("update Users set authority=#{authority},uname=#{uname}, email=#{email} where uid =#{uid}")
    boolean updateUser(long uid, String authority, String uname, String email);
}

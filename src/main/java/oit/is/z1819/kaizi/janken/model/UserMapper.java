package oit.is.z1819.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * from users where id = #{id}")
    User selectById(String id);

    @Select("SELECT * from users where name = #{name}")
    User selectByName(String name);

    @Select("SELECT * from users")
    ArrayList<User> selectAllUsers();
}
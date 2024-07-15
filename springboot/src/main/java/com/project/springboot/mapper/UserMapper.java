package com.project.springboot.mapper;

import com.project.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO User (UserName, UserFirstName, UserLastName, UserRole, UserPassword, UserEmail, UserStatus) "
            + " VALUES (#{userName}, #{userFirstName}, #{userLastName}, #{userRole}, #{userPassword}, #{userEmail}, #{userStatus})")
    void insert(User user);

    @Update("UPDATE User SET UserName = #{userName}, UserFirstName = #{userFirstName}, UserLastName = #{userLastName}, "
            + "UserRole = #{userRole}, UserPassword = #{userPassword}, UserEmail = #{userEmail}, UserStatus = #{userStatus} "
            + "WHERE UserID = #{userID}")
    void update(User user);

    @Delete ("DELETE FROM User WHERE UserID = #{userID}")
    void delete(Integer id);

    @Select("SELECT * FROM USER ORDER BY UserID DESC")
    List<User> selectAll();

    @Select("SELECT * FROM USER WHERE UserName = #{userName}")
    User selectByUserName(String userName);

    @Select("SELECT * FROM USER WHERE UserEmail = #{userEmail}")
    User selectByUserEmail(String userEmail);

}

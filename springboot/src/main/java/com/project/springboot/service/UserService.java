/**
 * Author: Bintong
 * Date: 2024/7/6 11:26
 */

package com.project.springboot.service;

import com.project.springboot.Utils.TokenUtils;
import com.project.springboot.entity.User;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }

    public void batchDeleteUser(List<Integer> ids) {
        for (Integer id : ids){
            userMapper.delete(id);
        }
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    public User login(User user){
        User databaseUser = userMapper.selectByUserName(user.getUserName());
        if (databaseUser == null){
            throw new ServiceException("Username doesn't exist");
        }
        if (!user.getUserPassword().equals(databaseUser.getUserPassword())){
            throw new ServiceException("Username or Password incorrect");
        }
        // generate token
        String token = TokenUtils.generateToken(databaseUser.getUserName(),databaseUser.getUserPassword());
        databaseUser.setToken(token);
        return databaseUser;
    }


    public User register(User user) {
        // to check if the userName already exists
        User databaseUser = userMapper.selectByUserName(user.getUserName());
        if (databaseUser != null){
            throw new ServiceException("Username already exists. ");
        }
        // to check if the user email already exists
        User databaseUserEmail = userMapper.selectByUserEmail(user.getUserEmail());
        if (databaseUserEmail != null){
            throw new ServiceException("User Email already exists. ");
        }
        userMapper.insert(user);
        return null;
    }

    public void updateUser(User user) {
        // to check if the user email already exists
        User databaseUserEmail = userMapper.selectByUserEmail(user.getUserEmail());
        if (databaseUserEmail != null && !databaseUserEmail.getUserID().equals(user.getUserID())){
            throw new ServiceException("User Email already exists. ");
        }
        userMapper.update(user);
    }
}

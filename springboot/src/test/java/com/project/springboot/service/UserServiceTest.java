/**
 * Author: Bintong
 * Date: 2024/7/29
 */

package com.project.springboot.service;

import com.project.springboot.entity.User;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserEmail("testUser@example.com");

        when(userMapper.selectByUserName("testUser")).thenReturn(null);
        when(userMapper.selectByUserEmail("testUser@example.com")).thenReturn(null);

        userService.register(user);

        verify(userMapper, times(1)).insert(user);
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserEmail("testUser@example.com");

        when(userMapper.selectByUserName("testUser")).thenReturn(user);

        assertThrows(ServiceException.class, () -> userService.register(user));
        verify(userMapper, never()).insert(any(User.class));
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserEmail("testUser@example.com");

        when(userMapper.selectByUserName("testUser")).thenReturn(null);
        when(userMapper.selectByUserEmail("testUser@example.com")).thenReturn(user);

        assertThrows(ServiceException.class, () -> userService.register(user));
        verify(userMapper, never()).insert(any(User.class));
    }

    @Test
    public void testLoginUser() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserPassword("password");

        User databaseUser = new User();
        databaseUser.setUserName("testUser");
        databaseUser.setUserPassword("password");

        when(userMapper.selectByUserName("testUser")).thenReturn(databaseUser);

        User result = userService.login(user);

        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        assertNotNull(result.getToken());
    }

    @Test
    public void testLoginUserWithWrongPassword() {
        User user = new User();
        user.setUserName("testUser");
        user.setUserPassword("wrongPassword");

        User databaseUser = new User();
        databaseUser.setUserName("testUser");
        databaseUser.setUserPassword("password");

        when(userMapper.selectByUserName("testUser")).thenReturn(databaseUser);

        assertThrows(ServiceException.class, () -> userService.login(user));
    }

    @Test
    public void testLoginUserWithNonExistingUsername() {
        User user = new User();
        user.setUserName("nonExistingUser");
        user.setUserPassword("password");

        when(userMapper.selectByUserName("nonExistingUser")).thenReturn(null);

        assertThrows(ServiceException.class, () -> userService.login(user));
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUserID(1);
        user.setUserName("testUser");
        user.setUserEmail("testUser@example.com");

        when(userMapper.selectByUserEmail("testUser@example.com")).thenReturn(null);

        userService.updateUser(user);

        verify(userMapper, times(1)).update(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1);
        verify(userMapper, times(1)).delete(1);
    }

    @Test
    public void testSelectAllUsers() {
        userService.selectAll();
        verify(userMapper, times(1)).selectAll();
    }
}

package com.exercise.registration.service;

import com.exercise.registration.dto.User;
import com.exercise.registration.entity.UserEntity;
import com.exercise.registration.mapper.UserMapper;
import com.exercise.registration.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String TEST_USERNAME = "TestUsername";
    private static final String TEST_PASSWORD = "TestPass123";

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    private UserService objectUnderTests;

    @Before
    public void setUp() {
        objectUnderTests = new UserService(mapper, repository);
        UserEntity userEntity = new UserEntity(TEST_USERNAME, TEST_PASSWORD);
        when(mapper.convertToEntity(any(User.class))).thenReturn(userEntity);
        User user = new User();
        user.setUsername(TEST_USERNAME);
        when(mapper.convertToDTO(any(UserEntity.class))).thenReturn(user);
    }

    @Test
    public void testRegisterUser() {
        when(repository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        ResponseEntity responseEntity = objectUnderTests.registerUser(new User());
        User createdUser = (User) responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(TEST_USERNAME, createdUser.getUsername());
    }
}
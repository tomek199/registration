package com.exercise.registration.mapper;

import com.exercise.registration.dto.User;
import com.exercise.registration.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserMapperTest {

    private static final String TEST_USERNAME = "TestUsername";
    private static final String TEST_PASSWORD = "TestPass123";

    private Mapper objectUnderTests;

    @Before
    public void setUp() {
        objectUnderTests = new UserMapper();
    }

    @Test
    public void testConvertToEntity() {
        User user = new User();
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
        UserEntity userEntity = (UserEntity) objectUnderTests.convertToEntity(user);
        assertEquals(user.getUsername(), userEntity.getUsername());
        assertEquals(user.getPassword(), userEntity.getPassword());
    }

    @Test
    public void testConvertToDTO() {
        UserEntity userEntity = new UserEntity(TEST_USERNAME, TEST_PASSWORD);
        User user = (User) objectUnderTests.convertToDTO(userEntity);
        assertEquals(userEntity.getUsername(), user.getUsername());
        assertNull(user.getPassword());
    }
}
package com.exercise.registration.repository;

import com.exercise.registration.RegistrationApplication;
import com.exercise.registration.entity.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
public class UserRepositoryTest {

    private static final String TEST_USERNAME = "TestUsername";
    private static final String TEST_PASSWORD = "testPass123";

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        UserEntity user = new UserEntity(TEST_USERNAME, TEST_PASSWORD);
        userRepository.save(user);
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindByUsername() {
        UserEntity user = userRepository.findByUsername(TEST_USERNAME);
        assertNotNull(user);
    }

    @Test
    public void testFindByUsernameNotFound() {
        UserEntity user = userRepository.findByUsername("OtherUsername");
        assertNull(user);
    }
}
package com.exercise.registration.validator;

import com.exercise.registration.entity.UserEntity;
import com.exercise.registration.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueUsernameValidatorTest {

    private static final String TEST_USERNAME = "TestUsername";

    @Mock
    private UserRepository userRepository;

    private UniqueUsernameValidator objectUnderTests;

    @Before
    public void setUp() {
        objectUnderTests = new UniqueUsernameValidator(userRepository);
    }

    @Test
    public void testIsValidTrue() {
        when(userRepository.findByUsername(any())).thenReturn(null);
        boolean result = objectUnderTests.isValid(TEST_USERNAME, null);
        Assert.assertTrue(result);
    }

    @Test
    public void testIsValidFalse() {
        when(userRepository.findByUsername(any())).thenReturn(new UserEntity());
        boolean result = objectUnderTests.isValid(TEST_USERNAME, null);
        Assert.assertFalse(result);
    }
}
package com.exercise.registration.controller;

import com.exercise.registration.RegistrationApplication;
import com.exercise.registration.dto.User;
import com.exercise.registration.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
@WebAppConfiguration
public class UserControllerIntegrationTest {

    private static final String API_URL = "/api/users";
    private static final String CORRECT_USERNAME = "TestUsername123";
    private static final String CORRECT_PASSWORD = "Passwd1345";

    private MediaType contentType;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
        mockMvc = webAppContextSetup(webApplicationContext).build();
        userRepository.deleteAll();
    }

    @Test
    public void testCreateSuccess() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, CORRECT_PASSWORD);
        mockMvc.perform(post(API_URL)
                .contentType(contentType)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", Matchers.is(CORRECT_USERNAME)));
    }

    @Test
    public void testValidateUsernameEmpty() throws Exception {
        String requestBody = generateRequestBody("", CORRECT_PASSWORD);
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidateUsernameLengthLessThan5() throws Exception {
        String requestBody = generateRequestBody("a1b2", CORRECT_PASSWORD);
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidateUsernameNotAlphanumeric() throws Exception {
        String requestBody = generateRequestBody("*#3#", CORRECT_PASSWORD);
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidatePasswordEmpty() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, "");
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidatePasswordLengthLessThan8() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, "ABcd123");
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidatePasswordNotContainsNumber() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, "aBcDeFgHiJ");
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidatePasswordNotContainsUppercaseChar() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, "1a2b3c4d5e");
        assertHasErrors(requestBody);
    }

    @Test
    public void testValidatePasswordNotContainsLowercaseChar() throws Exception {
        String requestBody = generateRequestBody(CORRECT_USERNAME, "1A2B3C4D5E");
        assertHasErrors(requestBody);
    }

    private String generateRequestBody(String username, String password) throws JsonProcessingException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    private void assertHasErrors(String requestBody) throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(contentType)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
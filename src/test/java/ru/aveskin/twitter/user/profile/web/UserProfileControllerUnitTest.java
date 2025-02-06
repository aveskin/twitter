package ru.aveskin.twitter.user.profile.web;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.aveskin.twitter.common.exception.TwitterException;
import ru.aveskin.twitter.security.web.dto.LoginRequest;
import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileControllerUnitTest {

    @Autowired
    private MockMvc restMockMvc;

    @Test
    void shouldReturnStatusCreated() throws Exception {
        String token = getAccessToken();  // Получаем реальный токен

        UserProfileRegisterRequest userProfileRegisterRequest = new UserProfileRegisterRequest("nickname", "some info");

        restMockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/user-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)  // Добавляем токен в заголовок
                        .content(new ObjectMapper()
                                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsBytes(userProfileRegisterRequest))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnStatus4xxStatusWhenUserProfileAlreadyExists() throws Exception {
        String token = getAccessToken();

        UserProfileRegisterRequest userProfileRegisterRequest = new UserProfileRegisterRequest("nickname", "some info");

        restMockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/user-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(new ObjectMapper()
                                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsBytes(userProfileRegisterRequest))
                )
                .andExpect(status().isCreated());

        MvcResult mvcResult = restMockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/user-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(new ObjectMapper()
                                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsBytes(userProfileRegisterRequest))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();

        Exception actualException = mvcResult.getResolvedException();
        assertNotNull(actualException);
        assertInstanceOf(TwitterException.class, actualException);
    }


    private String getAccessToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest("alejandro_mclaughlin@gmail.com", "password");

        return new ObjectMapper()
                .readTree(
                        restMockMvc.perform(
                                        MockMvcRequestBuilders
                                                .post("/api/v1/authentication/access_token")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(new ObjectMapper()
                                                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                                        .writeValueAsBytes(loginRequest))
                                )
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.idToken").isNotEmpty())
                                .andExpect(jsonPath("$.idToken").isString())
                                .andReturn()
                                .getResponse()
                                .getContentAsString()
                )
                .get("idToken")
                .asText();
    }

}
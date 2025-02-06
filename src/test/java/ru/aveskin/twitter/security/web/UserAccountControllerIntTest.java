package ru.aveskin.twitter.security.web;

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
import ru.aveskin.twitter.security.repository.UserAccountRepository;
import ru.aveskin.twitter.security.web.dto.RegisterRequest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserAccountControllerIntTest {

    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    void shouldCreateUserAccount() throws Exception {
        String[] uuidParts = UUID.randomUUID().toString().split("-");
        String username = String.format("%s-%s@gmail.com", uuidParts[0], uuidParts[1]);

        boolean existBefore = userAccountRepository.existsByUsername(username);
        assertFalse(existBefore);

        RegisterRequest registerRequest = new RegisterRequest(username, "password");

        restMockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                        .writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isCreated());

        boolean existAfter = userAccountRepository.existsByUsername(username);
        assertTrue(existAfter);

    }

    @Test
    void shouldNotCreateUserAccountWithExistingUsername() throws Exception {
        String[] uuidParts = UUID.randomUUID().toString().split("-");
        String username = String.format("%s-%s@gmail.com", uuidParts[0], uuidParts[1]);

        boolean existBefore = userAccountRepository.existsByUsername(username);
        assertFalse(existBefore);

        RegisterRequest registerRequest = new RegisterRequest(username, "password");

        restMockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper()
                                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                        .writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isCreated());

        boolean existAfter = userAccountRepository.existsByUsername(username);
        assertTrue(existAfter);

        MvcResult mvcResult = restMockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts/register")
                                .contentType(MediaType.APPLICATION_JSON)

                                .content(new ObjectMapper()
                                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                        .writeValueAsBytes(registerRequest))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        Exception resolvedException = mvcResult.getResolvedException();
        assertNotNull(resolvedException);
        assertThat(resolvedException).isExactlyInstanceOf(TwitterException.class);
        assertEquals("Account with this username has already existed", resolvedException.getMessage());

    }

}

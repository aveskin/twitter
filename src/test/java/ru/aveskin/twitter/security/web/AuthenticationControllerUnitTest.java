package ru.aveskin.twitter.security.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.aveskin.twitter.security.usecase.AuthenticationUseCase;
import ru.aveskin.twitter.security.web.dto.AccessToken;
import ru.aveskin.twitter.security.web.dto.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerUnitTest {
    MockMvc mockMvc;

    @Mock
    private AuthenticationUseCase authenticationUseCase;

    @InjectMocks
    private AuthenticationController controller;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnAccessToken() throws Exception {
        LoginRequest request = new LoginRequest("test@gmail.com", "test_password");
        Mockito.when(authenticationUseCase.authenticate(request))
                .thenReturn(new AccessToken("test_token_string"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/authentication/access_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idToken").value("test_token_string"));

        Mockito.verify(authenticationUseCase, Mockito.times(1)).authenticate(request);
    }

    @Test
    void shouldReturnStatus4xxStatusWhenInvalidLoginRequest() throws Exception {
        LoginRequest request = new LoginRequest("invalid_email", "test_password");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/authentication/access_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();

        Exception actualException = mvcResult.getResolvedException();
        assertNotNull(actualException);
        assertInstanceOf(MethodArgumentNotValidException.class, actualException);
        Mockito.verify(authenticationUseCase, Mockito.never()).authenticate(any());

    }

}
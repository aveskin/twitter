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
import ru.aveskin.twitter.security.usecase.RegisterUserAccountUseCase;
import ru.aveskin.twitter.security.web.dto.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserAccountControllerUnitTest {
    MockMvc mockMvc;

    @Mock
    private RegisterUserAccountUseCase registerUserAccountUseCase;

    @InjectMocks
    private UserAccountController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnStatusCreated() throws Exception {
        RegisterRequest request = new RegisterRequest("test@gmail.com", "test_password");

        Mockito.doNothing().when(registerUserAccountUseCase).register(request);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/accounts/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request))
                )
                .andExpect(status().isCreated());

        Mockito.verify(registerUserAccountUseCase, Mockito.times(1)).register(request);
    }

    @Test
    void shouldReturnStatus4xxStatusWhenInvalidRegisterRequest() throws Exception {
        RegisterRequest request = new RegisterRequest("invalid_email", "test_password");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/accounts/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();

        Exception actualException = mvcResult.getResolvedException();
        assertNotNull(actualException);
        assertInstanceOf(MethodArgumentNotValidException.class, actualException);
        Mockito.verify(registerUserAccountUseCase, Mockito.never()).register(any());
    }

}
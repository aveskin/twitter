package ru.aveskin.twitter.sequrity.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.sequrity.web.dto.RegisterRequest;
import ru.aveskin.twitter.usecase.RegisterUserAccountUseCase;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class UserAccountController {
    private final RegisterUserAccountUseCase registerUserAccountUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody RegisterRequest request) {
        log.info("register request: {}", request);
        registerUserAccountUseCase.register(request);
    }
}

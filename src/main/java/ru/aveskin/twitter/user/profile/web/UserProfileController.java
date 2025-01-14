package ru.aveskin.twitter.user.profile.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.user.profile.usecase.UserProfileRegisterUseCase;
import ru.aveskin.twitter.user.profile.web.dto.UserProfileRegisterRequest;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileRegisterUseCase registerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserProfile(@Valid @RequestBody UserProfileRegisterRequest request) {
        registerUseCase.registerUserProfile(request);
    }
}

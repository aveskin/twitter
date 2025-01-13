package ru.aveskin.twitter.sequrity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.aveskin.twitter.sequrity.model.UserAccount;
import ru.aveskin.twitter.sequrity.model.UserRole;
import ru.aveskin.twitter.sequrity.service.UserAccountService;
import ru.aveskin.twitter.sequrity.service.UserRoleService;
import ru.aveskin.twitter.sequrity.web.dto.RegisterRequest;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@RequestBody RegisterRequest request) {
        log.info("register request: {}", request);
        Assert.hasLength(request.username(), "username should not be empty or null");
        Assert.hasLength(request.password(), "password should not be empty or null");

        UserRole userRole = userRoleService
                .findUserRole()
                .orElseThrow(() -> new RuntimeException("User role not found"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(request.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(passwordEncoder.encode(request.password()));
        userAccount.setAuthorities(List.of(userRole));

        userAccountService.createUserAccount(userAccount);

    }
}

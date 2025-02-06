package ru.aveskin.twitter.security.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank @Email
        String username,
        @NotBlank
        String password) {
}

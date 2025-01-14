package ru.aveskin.twitter.user.profile.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileRegisterRequest(
        @NotBlank
        String nickname,

        @NotBlank
        String imageLink) {

}

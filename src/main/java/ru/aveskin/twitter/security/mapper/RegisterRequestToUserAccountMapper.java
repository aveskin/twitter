package ru.aveskin.twitter.security.mapper;

import ru.aveskin.twitter.security.model.UserAccount;
import ru.aveskin.twitter.security.web.dto.RegisterRequest;

public interface RegisterRequestToUserAccountMapper extends Mapper<UserAccount, RegisterRequest> {
}

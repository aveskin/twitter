package ru.aveskin.twitter.sequrity.mapper;

import ru.aveskin.twitter.sequrity.model.UserAccount;
import ru.aveskin.twitter.sequrity.web.dto.RegisterRequest;

public interface RegisterRequestToUserAccountMapper extends Mapper<UserAccount, RegisterRequest> {
}

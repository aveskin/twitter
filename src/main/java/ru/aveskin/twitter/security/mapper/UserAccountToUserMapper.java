package ru.aveskin.twitter.security.mapper;

import org.springframework.security.core.userdetails.User;
import ru.aveskin.twitter.common.mapper.Mapper;
import ru.aveskin.twitter.security.model.UserAccount;

public interface UserAccountToUserMapper extends Mapper<User, UserAccount> {
}

package ru.aveskin.twitter.security.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aveskin.twitter.security.mapper.UserAccountToUserMapper;
import ru.aveskin.twitter.security.service.UserAccountService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccountService userAccountService;
    private final UserAccountToUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountService.findUserByUsername(username)
                .map(mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("неверные учетные данные пользователя"));
    }
}

package ru.aveskin.twitter.security.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aveskin.twitter.security.model.UserRole;
import ru.aveskin.twitter.security.repository.UserRoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplUnitTest {
    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Test
    void shouldReturnUserRoleWhenAuthorityIsRoleUser() {
        UserRole expectedResult = new UserRole();
        expectedResult.setAuthority("ROLE_USER");
        expectedResult.setId(1L);

        Mockito.when(userRoleRepository.findByAuthority(expectedResult.getAuthority()))
                .thenReturn(Optional.of(expectedResult));

        Optional<UserRole> actualUserRole = userRoleService
                .findUserRole();

        assertTrue(actualUserRole.isPresent());
        assertEquals(Optional.of(expectedResult), actualUserRole);

        Mockito.verify(userRoleRepository, Mockito.times(1))
                .findByAuthority(any());

    }

    @Test
    void shouldReturnEmptyOptionalWhenUserRoleNotFound() {
        Mockito.when(userRoleRepository.findByAuthority("ROLE_USER"))
                .thenReturn(Optional.empty());

        Optional<UserRole> actualUserRole = userRoleService
                .findUserRole();

        assertTrue(actualUserRole.isEmpty());
    }

}
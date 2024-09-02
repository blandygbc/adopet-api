package com.blandygbc.adopet.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blandygbc.adopet.TestsEnvProperties;
import com.blandygbc.adopet.domain.exception.EmailAlreadyInUseException;
import com.blandygbc.adopet.domain.role.RoleRepository;
import com.blandygbc.adopet.utils.RoleUtils;
import com.blandygbc.adopet.utils.UserUtils;

@SpringBootTest
@DisplayName("Unit tests for AuthService")
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository repositoryMock;

    @Mock
    private RoleRepository roleRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    public static void setup() {
        TestsEnvProperties.load();
    }

    @Nested
    @DisplayName("Tests for createUser")
    class CreateUser {
        @Test
        @DisplayName("Criar deve retornar EmailAlreadyInUseException quando já existir o e-mail no banco")
        void create_MustThrowEmailAlreadyInUseException_WhenAlreadyExistsEmailInTheDatabase() {
            var sqlEx = new SQLIntegrityConstraintViolationException(
                    "Duplicate entry 'johndoe@email.com' for key 'users.email'");
            var dataEx = new DataIntegrityViolationException("unique_email constraint", sqlEx);

            when(roleRepositoryMock.getReferenceById(anyLong())).thenReturn(RoleUtils.getTutorRole());
            when(repositoryMock.save(any())).thenThrow(dataEx);

            Assertions.assertThatExceptionOfType(EmailAlreadyInUseException.class)
                    .isThrownBy(() -> authService.createUser("email", "password", 1L));

        }
    }

    // @Test
    // void testGetUserDetails() {

    // }

    // @Test
    // void testLoadUserByUsername() {

    // }

    @Nested
    @DisplayName("Tests for updateUser")
    class UpdateUser {
        @Test
        @DisplayName("Atualizar deve retornar EmailAlreadyInUseException quando já existir o e-mail no banco")
        void update_MustThrowEmailAlreadyInUseException_WhenAlreadyExistsEmailInTheDatabase() {
            var sqlEx = new SQLIntegrityConstraintViolationException(
                    "Duplicate entry 'johndoe@email.com' for key 'users.email'");
            var dataEx = new DataIntegrityViolationException("unique_email constraint", sqlEx);

            when(repositoryMock.save(any())).thenThrow(dataEx);

            User user = UserUtils.getUser();
            Assertions.assertThatExceptionOfType(EmailAlreadyInUseException.class)
                    .isThrownBy(() -> authService.updateUser(user, "email", "password"));

        }
    }
}

package com.blandygbc.adopet.domain.tutor;

import static com.blandygbc.adopet.utils.TutorUtils.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.blandygbc.adopet.TestsEnvProperties;
import com.blandygbc.adopet.domain.exception.EmailAlreadyInUseException;
import com.blandygbc.adopet.domain.user.AuthService;

@SpringBootTest
@DisplayName("Unit tests for TutorService")
public class TutorServiceTest {

    @MockBean
    private TutorRepository repositoryMock;

    @MockBean
    private TutorService serviceMock;

    @MockBean
    private AuthService authService;

    @BeforeAll
    public static void setup() {
        TestsEnvProperties.load();
    }

    // @Test
    // void testCreateTutor() {

    // }

    // @Test
    // void testDeleteTutor() {

    // }

    // @Test
    // void testFindAll() {

    // }

    // @Test
    // void testGetTutor() {

    // }

    @Nested
    @DisplayName("Tests for updateTutor")
    class UpdateTutor {
        // To implement
    }
    // @Test
    // void testUpdateTutor() {

    // }
}

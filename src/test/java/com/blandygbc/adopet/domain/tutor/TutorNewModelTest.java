package com.blandygbc.adopet.domain.tutor;

import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@DisplayName("Unit tests for TutorNewModelTest")
public class TutorNewModelTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Nested
    @DisplayName("Tests for name validation")
    class NameTests {
        @ParameterizedTest
        @ValueSource(strings = {
                "Lorem Ipsum", "Du",
                "Name With 100 Characters!Name With 100 Characters!Name With 100 Characters!Name With 100 Characters!"
        })
        @DisplayName("Validate name must not return violations when name is valid")
        void validateName_MustNotReturnViolations_WhenNameIsValid(String validName) {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .name(validName)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "name");

            Assertions.assertThat(actualViolations).isEmpty();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = { "   ", "\t", "\n" })
        @DisplayName("Validate name must return violations when name is blank")
        void validateName_MustReturnViolations_WhenNameIsBlank(String blankName) {
            var newTutor = TutorNewModel.builder().name(blankName).build();
            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "name");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("não deve estar em branco");

        }

        @Test
        @DisplayName("Validate name must return violations when name contains less than 2 characters")
        void validateName_MustReturnViolations_WhenNameContainsLessThan2Characters() {
            String nameWithLessThan2Characters = "l";

            Assertions.assertThat(nameWithLessThan2Characters).hasSizeLessThan(2);

            TutorNewModel newTutor = TutorNewModel.builder()
                    .name(nameWithLessThan2Characters)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "name");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("tamanho deve ser entre 2 e 100");
        }

        @Test
        @DisplayName("Validate name must return violations when name contains more than 100 characters")
        void validateName_MustReturnViolations_WhenNameContainsMoreThan100Characters() {
            String nameWithMoreThan100Characters = "loremloremloremloremloremloremloremloremloremlorem" +
                    "loremloremloremloremloremloremloremloremloremloreml";

            Assertions.assertThat(nameWithMoreThan100Characters).hasSizeGreaterThan(100);

            TutorNewModel newTutor = TutorNewModel.builder()
                    .name(nameWithMoreThan100Characters)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "name");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("tamanho deve ser entre 2 e 100");
        }
    }

    @Nested
    @DisplayName("Tests for email validation")
    class EmailTests {

        @Test
        @DisplayName("Validate email must not return violations when email is valid")
        void validateEmail_MustNotReturnViolations_WhenEmailIsValid() {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .email("lorem@email.com")
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "email");

            Assertions.assertThat(actualViolations).isEmpty();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = { "   ", "\t", "\n" })
        @DisplayName("Validate email must return violations when email is blank")
        void validateEmail_MustReturnViolations_WhenEmailIsBlank(String blankEmail) {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .email(blankEmail)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "email");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("não deve estar em branco");
        }

        @Test
        @DisplayName("Validate email must return violations when email contains more than 255 characters")
        void validateEmail_MustReturnViolations_WhenEmailContainsMoreThan100Characters() {
            String emailWithMoreThan255Characters = "loremloremloremloremloremloremloremloremloremlorem" +
                    "loremloremloremloremloremloremloremloremloremlorem" +
                    "loremloremloremloremloremloremloremloremloremlorem" +
                    "loremloremloremloremloremloremloremloremloremlorem" +
                    "loremloremloremloremloremloremloremloremloremlorem" +
                    "@email.com";

            Assertions.assertThat(emailWithMoreThan255Characters).hasSizeGreaterThan(255);

            TutorNewModel newModel = TutorNewModel.builder()
                    .email(emailWithMoreThan255Characters)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newModel, "email");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("O e-mail deve conter no máximo 255 caracteres");
        }

        @ParameterizedTest
        @ValueSource(strings = { "lorememailcom", "lorem.com", "@email.com" })
        @DisplayName("Validate email must return violations when email is not well formed")
        void validateEmail_MustReturnViolations_WhenEmailIsNotWellFormed(String notWellFormedEmail) {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .email(notWellFormedEmail)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "email");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("deve ser um endereço de e-mail bem formado");
        }

    }

    @Nested
    @DisplayName("Tests for password validation")
    class PasswordTests {

        @ParameterizedTest
        @ValueSource(strings = { "12345678", "1234567890@#$%)(*&[]abcdefghij" })
        @DisplayName("Validate password must not return violations when password is valid")
        void validatePassword_MustNotReturnViolations_WhenPasswordIsValid(String validPassword) {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .password(validPassword)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "password");

            Assertions.assertThat(actualViolations).isEmpty();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = { "   ", "\t", "\n" })
        @DisplayName("Validate password must return violations when password is blank")
        void validatePassword_MustReturnViolations_WhenPasswordIsBlank(String blankPassword) {
            TutorNewModel newTutor = TutorNewModel.builder()
                    .password(blankPassword)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "password");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("não deve estar em branco");
        }

        @Test
        @DisplayName("Validate password must return violations when password contains less than 7 characters")
        void validatePassword_MustReturnViolations_WhenPasswordContainsLessThan2Characters() {
            String passwordWithLessThan8Characters = "1234567";

            Assertions.assertThat(passwordWithLessThan8Characters).hasSizeLessThan(8);

            TutorNewModel newTutor = TutorNewModel.builder()
                    .password(passwordWithLessThan8Characters)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "password");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("A senha deve ter entre 8 a 30 caracteres");
        }

        @Test
        @DisplayName("Validate password must return violations when password contains more than 30 characters")
        void validatePassword_MustReturnViolations_WhenPasswordContainsMoreThan100Characters() {
            String passwordWithMoreThan30Characters = "123456789012345678901234567890_";

            Assertions.assertThat(passwordWithMoreThan30Characters).hasSizeGreaterThan(30);

            TutorNewModel newTutor = TutorNewModel.builder()
                    .password(passwordWithMoreThan30Characters)
                    .build();

            Set<ConstraintViolation<TutorNewModel>> actualViolations = validator
                    .validateProperty(newTutor, "password");

            List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

            Assertions.assertThat(actualViolations).isNotEmpty();
            Assertions.assertThat(actualMessages).contains("A senha deve ter entre 8 a 30 caracteres");
        }

    }
}

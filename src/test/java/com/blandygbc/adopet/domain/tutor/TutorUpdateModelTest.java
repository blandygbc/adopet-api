package com.blandygbc.adopet.domain.tutor;

import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static com.blandygbc.adopet.utils.TutorUtils.*;

@DisplayName("Unit tests for TutorUpdateModelTest")
public class TutorUpdateModelTest {
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
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .name(validName)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "name");

                        Assertions.assertThat(actualViolations).isEmpty();
                }

                @ParameterizedTest
                @NullAndEmptySource
                @ValueSource(strings = { " ", "\t", "\n" })
                @DisplayName("Entity should not update name when is blank")
                void validateName_MustNotUpdateName_WhenNameIsBlank(String blankName) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .name(blankName)
                                        .build();

                        // Original name Jessica
                        var tutor = getTutorSavedNew();

                        tutor.updateInfo(updateTutor);
                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "image");

                        Assertions.assertThat(actualViolations).isEmpty();
                        Assertions.assertThat(tutor.getName()).isEqualTo("Jessica");
                }

                @Test
                @DisplayName("Validate name must return violations when name contains less than 2 characters")
                void validateName_MustReturnViolations_WhenNameContainsLessThan2Characters() {
                        String nameWithLessThan2Characters = "l";

                        Assertions.assertThat(nameWithLessThan2Characters).hasSizeLessThan(2);

                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .name(nameWithLessThan2Characters)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "name");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

                        Assertions.assertThat(actualViolations).isNotEmpty();
                        Assertions.assertThat(actualMessages).contains("tamanho deve ser entre 2 e 100");
                }

                @Test
                @DisplayName("Validate name must return violations when name contains more than 100 characters")
                void validateName_MustReturnViolations_WhenNameContainsMoreThan100Characters() {
                        String nameWithMoreThan100Characters = "loremloremloremloremloremloremloremloremloremlorem" +
                                        "loremloremloremloremloremloremloremloremloremloreml";

                        Assertions.assertThat(nameWithMoreThan100Characters).hasSizeGreaterThan(100);

                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .name(nameWithMoreThan100Characters)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "name");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

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
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .email("lorem@email.com")
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "email");

                        Assertions.assertThat(actualViolations).isEmpty();
                }

                @ParameterizedTest
                @NullAndEmptySource
                @ValueSource(strings = { "   ", "\t", "\n" })
                @DisplayName("Validate email must return violations when email is blank")
                void validateEmail_MustReturnViolations_WhenEmailIsBlank(String blankEmail) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .email(blankEmail)
                                        .build();
                        // Original email jessica@email.com
                        var tutor = getTutorSavedNew();

                        tutor.updateInfo(updateTutor);

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "image");

                        Assertions.assertThat(actualViolations).isEmpty();
                        Assertions.assertThat(tutor.getUser().getEmail()).isEqualTo("jessica@email.com");
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

                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .email(emailWithMoreThan255Characters)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "email");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

                        Assertions.assertThat(actualViolations).isNotEmpty();
                        Assertions.assertThat(actualMessages).contains("O e-mail deve conter no máximo 255 caracteres");
                }

                @ParameterizedTest
                @ValueSource(strings = { "lorememailcom", "lorem.com", "@email.com" })
                @DisplayName("Validate email must return violations when email is not well formed")
                void validateEmail_MustReturnViolations_WhenEmailIsNotWellFormed(String notWellFormedEmail) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .email(notWellFormedEmail)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "email");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

                        Assertions.assertThat(actualViolations).isNotEmpty();
                        Assertions.assertThat(actualMessages).contains("deve ser um endereço de e-mail bem formado");
                }

        }

        @Nested
        @DisplayName("Tests for image validation")
        class ImageTests {

                @Test
                @DisplayName("Validate image must not return violations when image url is valid")
                void validateImage_MustNotReturnViolations_WhenImageIsValid() {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .image("http://some.url.com/image")
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "image");

                        Assertions.assertThat(actualViolations).isEmpty();
                }

                @ParameterizedTest
                @NullAndEmptySource
                @DisplayName("Validate image must not update tutor when image is blank")
                void validateImage_MustNotUpdateTutor_WhenImageIsBlank(String blankImage) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .image(blankImage)
                                        .build();
                        // Original http://some.url.com/image
                        var tutor = getTutorComplete();

                        tutor.updateInfo(updateTutor);

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "image");

                        Assertions.assertThat(actualViolations).isEmpty();

                        Assertions.assertThat(tutor.getImage()).isEqualTo("http://some.url.com/image");
                }

                @ParameterizedTest
                @ValueSource(strings = {
                                "   ",
                                "\t",
                                "\n",
                                "some.url.com",
                                "lorem.com",
                                "lorem.com/image",
                })
                @DisplayName("Validate image must return violations when image is not valid")
                void validateImage_MustReturnViolations_WhenImageIsNotValid(String invalidUrl) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .image(invalidUrl)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "image");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

                        Assertions.assertThat(actualViolations).isNotEmpty();
                        Assertions.assertThat(actualMessages).contains("deve ser uma URL válida");
                }
        }

        @Nested
        @DisplayName("Tests for phone validation")
        class PhoneTests {

                @ParameterizedTest
                @ValueSource(strings = {
                                "21999999999",
                                "2133333333",
                                "08009999999",
                                "0800999999",
                })
                @DisplayName("Validate phone must not return violations when phone is valid")
                void validatePhone_MustNotReturnViolations_WhenPhoneIsValid(String validPhone) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .phone(validPhone)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "phone");

                        Assertions.assertThat(actualViolations).isEmpty();
                }

                @Test
                @DisplayName("Validate phone must not update tutor when phone is null")
                void validatePhone_MustNotUpdateTutor_WhenPhoneIsNull() {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .phone(null)
                                        .build();
                        // Original 21999999999
                        var tutor = getTutorComplete();

                        tutor.updateInfo(updateTutor);

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "phone");

                        Assertions.assertThat(actualViolations).isEmpty();

                        Assertions.assertThat(tutor.getPhone()).isEqualTo("21999999999");
                }

                @ParameterizedTest
                @EmptySource
                @ValueSource(strings = { "   ", "\t", "\n" })
                @DisplayName("Validate phone must return violations when phone is blank")
                void validatePhone_MustReturnViolations_WhenPhoneIsBlank(String blankPhone) {
                        TutorUpdateModel updateTutor = TutorUpdateModel.builder()
                                        .phone(blankPhone)
                                        .build();

                        Set<ConstraintViolation<TutorUpdateModel>> actualViolations = validator
                                        .validateProperty(updateTutor, "phone");

                        List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage)
                                        .toList();

                        Assertions.assertThat(actualViolations).isNotEmpty();
                        Assertions.assertThat(actualMessages).contains("tamanho deve ser entre 10 e 11");
                }
        }

}

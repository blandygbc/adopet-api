package com.blandygbc.adopet.controller;

import static com.blandygbc.adopet.utils.TutorUtils.*;
import static com.blandygbc.adopet.utils.ConstantUtils.NOT_FOUND_MESSAGE;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.blandygbc.adopet.TestsEnvProperties;
import com.blandygbc.adopet.domain.exception.EmailAlreadyInUseException;
import com.blandygbc.adopet.domain.exception.EmptyListException;
import com.blandygbc.adopet.domain.exception.InternalErrorDTO;
import com.blandygbc.adopet.domain.exception.ValidationErrorFields;
import com.blandygbc.adopet.domain.role.BasicRoles;
import com.blandygbc.adopet.domain.role.Role;
import com.blandygbc.adopet.domain.tutor.Tutor;
import com.blandygbc.adopet.domain.tutor.TutorModel;
import com.blandygbc.adopet.domain.tutor.TutorNewModel;
import com.blandygbc.adopet.domain.tutor.TutorService;
import com.blandygbc.adopet.domain.tutor.TutorUpdateModel;
import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.util.JsonMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DisplayName("Unit tests for TutorService")
public class TutorControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private JacksonTester<TutorNewModel> newModelToJson;

        @Autowired
        private JacksonTester<TutorUpdateModel> updateModelToJson;

        @Autowired
        private ObjectMapper mapper;

        @MockBean
        private TutorService serviceMock;

        private static final String TUTORS_BASE_MAPPING = "/tutors";

        @BeforeAll
        public static void setup() {
                TestsEnvProperties.load();
        }

        @Nested
        @DisplayName("Tests for add endpoint")
        class AddEndpoint {

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar email em branco")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithNullEmail() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithEmail(null))
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var listErrorFields = mapper.readValue(response.getContentAsString(),
                                        new TypeReference<List<ValidationErrorFields>>() {
                                        });

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(listErrorFields.get(0).field()).isEqualTo("email");
                        Assertions.assertThat(listErrorFields.get(0).message())
                                        .isEqualTo("must not be blank");
                }

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar email inválido")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithInvalidEmail() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithEmail(
                                                        "pessoaemail.com"))
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var listErrorFields = mapper.readValue(response.getContentAsString(),
                                        new TypeReference<List<ValidationErrorFields>>() {
                                        });

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(listErrorFields.get(0).field()).isEqualTo("email");
                        Assertions.assertThat(listErrorFields.get(0).message())
                                        .isEqualTo("must be a well-formed email address");
                }

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar email já está em uso")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithEmailInUse() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithEmail(
                                                        "pessoa@email.com"))
                                        .getJson();

                        when(serviceMock.createTutor(any())).thenThrow(new EmailAlreadyInUseException());

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var responseMessage = mapper.readValue(response.getContentAsString(StandardCharsets.UTF_8),
                                        JsonMessage.class);

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(responseMessage.message())
                                        .isEqualTo("O e-mail já está em uso.");
                }

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar com senha em branco")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithNullPassword() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithPassword(null))
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var listErrorFields = mapper.readValue(response.getContentAsString(),
                                        new TypeReference<List<ValidationErrorFields>>() {
                                        });

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(listErrorFields.get(0).field()).isEqualTo("password");
                        Assertions.assertThat(listErrorFields.get(0).message())
                                        .isEqualTo("must not be blank");
                }

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar com senha em menor que 8")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithSmallPassword() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithPassword("1234"))
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var listErrorFields = mapper.readValue(response.getContentAsString(),
                                        new TypeReference<List<ValidationErrorFields>>() {
                                        });

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(listErrorFields.get(0).field()).isEqualTo("password");
                        Assertions.assertThat(listErrorFields.get(0).message())
                                        .isEqualTo("A senha deve ter entre 8 a 30 caracteres");
                }

                @Test
                @DisplayName("Adicionar deveria gerar erro 400 com ValidationErrorFields ao cadastrar com nome em branco")
                @WithMockUser
                void add_MustReturnBadRequest_WhenTryToCreateTutorWithNullName() throws Exception {
                        String jsonNewModel = newModelToJson
                                        .write(getTutorNewModelWithName(null))
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();

                        var listErrorFields = mapper.readValue(response.getContentAsString(),
                                        new TypeReference<List<ValidationErrorFields>>() {
                                        });

                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
                        Assertions.assertThat(listErrorFields.get(0).field()).isEqualTo("name");
                        Assertions.assertThat(listErrorFields.get(0).message())
                                        .isEqualTo("must not be blank");

                }

                @Test
                @DisplayName("Adicionar deveria devolver 200 com o tutor criado")
                @WithMockUser
                void add_MustReturnOkAndTutorModel_WhenTryToCreateAValidTutor() throws Exception {
                        when(serviceMock.createTutor(any())).thenReturn(getTutorSavedNew());
                        String jsonNewModel = newModelToJson
                                        .write(getCompleteTutorNewModel())
                                        .getJson();

                        var response = mockMvc.perform(
                                        MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonNewModel))
                                        .andReturn().getResponse();


                        var tutorModelResp = mapper.readValue(response.getContentAsString(), TutorModel.class);
                        
                        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                        Assertions.assertThat(tutorModelResp.id()).isEqualTo(getTutorModelSavedNew().id());
                        Assertions.assertThat(tutorModelResp.name()).isEqualTo(getTutorModelSavedNew().name());
                        Assertions.assertThat(tutorModelResp.user().email()).isEqualTo(getTutorModelSavedNew().user().email());
                        Assertions.assertThat(tutorModelResp.user().role().name()).isEqualTo(getTutorModelSavedNew().user().role().name());
                }
        }

        @Nested
        @DisplayName("Tests for delete endpoint")
        class DeleteEndpoint {
                @Test
                @DisplayName("Deletar deveria retornar 200 com mensagem de sucesso")
                @WithMockUser
                void delete_MustReturnOkAndSuccessMessage_WhenDeleteSuccessfully() throws Exception {
                        Mockito.doNothing().when(serviceMock).deleteTutor(1L);

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.delete(TUTORS_BASE_MAPPING + "/1"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn().getResponse().getContentAsString();

                        var responseMessage = mapper.readValue(responseBody,
                                        JsonMessage.class);

                        Assertions.assertThat(responseMessage.message())
                                        .isEqualTo("Removido com sucesso!");
                }

                @Test
                @DisplayName("Deletar deveria retornar 404 com mensagem não encontrado com id inexistente")
                @WithMockUser
                void delete_MustReturn404WithNotFoundMessage_WhenNotFoundTutor() throws Exception {
                        BDDMockito.willThrow(new EntityNotFoundException())
                                        .given(serviceMock).deleteTutor(2L);

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.delete(TUTORS_BASE_MAPPING + "/2"))
                                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);

                        var responseMessage = mapper.readValue(responseBody,
                                        JsonMessage.class);

                        Assertions.assertThat(responseMessage.message())
                                        .isEqualTo(NOT_FOUND_MESSAGE);
                }

                @Test
                @DisplayName("Deletar deveria retornar 500 com InternalErrorDTO com id inválido")
                @WithMockUser
                void delete_MustReturn500WithInternalErrorDTO_WhenTryToDeleteInvalidId() throws Exception {
                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.delete(TUTORS_BASE_MAPPING + "/1o"))
                                        .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);

                        var error = mapper.readValue(responseBody,
                                        InternalErrorDTO.class);

                        Assertions.assertThat(error.date().getDayOfMonth())
                                        .isEqualTo(LocalDateTime.now().getDayOfMonth());
                        Assertions.assertThat(error.date().getMonth()).isEqualTo(LocalDateTime.now().getMonth());
                        Assertions.assertThat(error.date().getYear()).isEqualTo(LocalDateTime.now().getYear());
                        Assertions.assertThat(error.message()).isEqualTo(
                                        "Failed to convert value of type 'java.lang.String'"
                                                        + " to required type 'java.lang.Long'; For input string: \"1o\"");
                }

        }

        @Nested
        @DisplayName("Tests for detail endpoint")
        class DetailEndpoint {

                @Test
                @DisplayName("Detalhar deveria devolver 200 com o tutor modelo ao achar o tutor")
                @WithMockUser
                void detail_MustReturnOkAndTutorModel_WhenSuccessfullyFindTutor() throws Exception {
                        when(serviceMock.getTutor(1L)).thenReturn(getTutorComplete());

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.get(TUTORS_BASE_MAPPING + "/1"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);


                        var tutorModelResp = mapper.readValue(responseBody, TutorModel.class);
                        
                        Assertions.assertThat(tutorModelResp.id()).isEqualTo(getTutorModelComplete().id());
                        Assertions.assertThat(tutorModelResp.name()).isEqualTo(getTutorModelComplete().name());
                        Assertions.assertThat(tutorModelResp.image()).isEqualTo(getTutorModelComplete().image());
                        Assertions.assertThat(tutorModelResp.phone()).isEqualTo(getTutorModelComplete().phone());
                        Assertions.assertThat(tutorModelResp.city()).isEqualTo(getTutorModelComplete().city());
                        Assertions.assertThat(tutorModelResp.state()).isEqualTo(getTutorModelComplete().state());
                        Assertions.assertThat(tutorModelResp.about()).isEqualTo(getTutorModelComplete().about());
                        Assertions.assertThat(tutorModelResp.user().email()).isEqualTo(getTutorModelComplete().user().email());
                        Assertions.assertThat(tutorModelResp.user().role().name()).isEqualTo(getTutorModelComplete().user().role().name());
                }

                @Test
                @DisplayName("Detalhar deveria devolver 404 com mensagem ao não achar o tutor")
                @WithMockUser
                void detail_MustReturn404AndNotFoundMessage_WhenNotFindTutor() throws Exception {
                        when(serviceMock.getTutor(2L)).thenThrow(new EntityNotFoundException());

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.get(TUTORS_BASE_MAPPING + "/2"))
                                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);


                        var tutorModelResp = mapper.readValue(responseBody, JsonMessage.class);
                        
                        Assertions.assertThat(tutorModelResp.message()).isEqualTo(NOT_FOUND_MESSAGE);
                }

                @Test
                @DisplayName("Detalhar deveria retornar 500 com InternalErrorDTO com id inválido")
                @WithMockUser
                void detail_MustReturn500WithInternalErrorDTO_WhenUseInvalidId() throws Exception {
                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.get(TUTORS_BASE_MAPPING + "/1o"))
                                        .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);

                        var error = mapper.readValue(responseBody,
                                        InternalErrorDTO.class);

                        Assertions.assertThat(error.date().getDayOfMonth())
                                        .isEqualTo(LocalDateTime.now().getDayOfMonth());
                        Assertions.assertThat(error.date().getMonth()).isEqualTo(LocalDateTime.now().getMonth());
                        Assertions.assertThat(error.date().getYear()).isEqualTo(LocalDateTime.now().getYear());
                        Assertions.assertThat(error.message()).isEqualTo(
                                        "Failed to convert value of type 'java.lang.String'"
                                                        + " to required type 'java.lang.Long'; For input string: \"1o\"");
                }
        }

        @Nested
        @DisplayName("Tests for getAll endpoint")
        class GetAllEndpoint {
                @Test
                @DisplayName("Pegar todos deveria devolver 200 com List<TutorModel> quando há dados e acessa com sucesso")
                @WithMockUser
                void getAll_MustReturnOkAndTutorModelList_WhenHasDataAndFecthSuccessfully() throws Exception {
                        when(serviceMock.findAll()).thenReturn(List.of(getTutorModelComplete()));

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.get(TUTORS_BASE_MAPPING))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);


                        var tutorModelRespList = mapper.readValue(responseBody, new TypeReference<List<TutorModel>>() {});
                        
                        Assertions.assertThat(tutorModelRespList).isNotNull().isNotEmpty().hasSize(1);
                        Assertions.assertThat(tutorModelRespList.get(0).id()).isEqualTo(getTutorModelComplete().id());
                        Assertions.assertThat(tutorModelRespList.get(0).name()).isEqualTo(getTutorModelComplete().name());
                        Assertions.assertThat(tutorModelRespList.get(0).image()).isEqualTo(getTutorModelComplete().image());
                        Assertions.assertThat(tutorModelRespList.get(0).phone()).isEqualTo(getTutorModelComplete().phone());
                        Assertions.assertThat(tutorModelRespList.get(0).city()).isEqualTo(getTutorModelComplete().city());
                        Assertions.assertThat(tutorModelRespList.get(0).state()).isEqualTo(getTutorModelComplete().state());
                        Assertions.assertThat(tutorModelRespList.get(0).about()).isEqualTo(getTutorModelComplete().about());
                        Assertions.assertThat(tutorModelRespList.get(0).user().email()).isEqualTo(getTutorModelComplete().user().email());
                        Assertions.assertThat(tutorModelRespList.get(0).user().role().name()).isEqualTo(getTutorModelComplete().user().role().name());
                }

                @Test
                @DisplayName("Pegar todos deveria devolver 200 com mensagem ao não achar nenhum tutor")
                @WithMockUser
                void getAll_MustReturn200AndNotFoundMessage_WhenNotFindAnyTutor() throws Exception {
                        when(serviceMock.findAll()).thenThrow(new EmptyListException());

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.get(TUTORS_BASE_MAPPING))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);


                        var tutorModelResp = mapper.readValue(responseBody, JsonMessage.class);
                        
                        Assertions.assertThat(tutorModelResp.message()).isEqualTo(NOT_FOUND_MESSAGE);
                }

        }

        @Nested
        @DisplayName("Tests for update endpoint")
        class UpdateEndpoint {
                @Test
                @DisplayName("Atualizar deveria devolver 200 com TutorModel quando os dados são validos")
                @WithMockUser
                void update_MustReturn200AndTutorModel_WhenUpdateIsSuccessfull() throws Exception {
                        final String name = "Lorem Ipsum";
                        final String email = "lorem@email.com";

                        var tutorUpdate = TutorUpdateModel.builder()
                                        .id(1L)
                                        .name(name)
                                        .email(email)
                                        .build();

                        var roleMock = Role.builder()
                                        .id(BasicRoles.TUTOR.getId())
                                        .name(BasicRoles.TUTOR.name())
                                        .build();

                        var userMock = User.builder()
                                        .email(email)
                                        .role(roleMock)
                                        .build();

                        var tutorMock = Tutor.builder()
                                        .id(1l)
                                        .name(name)
                                        .user(userMock)
                                        .build();

                        when(serviceMock.updateTutor(tutorUpdate)).thenReturn(tutorMock);
                        var tutorUpdateJson = updateModelToJson.write(tutorUpdate).getJson();

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.put(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(tutorUpdateJson))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);

                        var tutorModelResp = mapper.readValue(responseBody, TutorModel.class);

                        Assertions.assertThat(tutorModelResp.id()).isEqualTo(tutorUpdate.id());
                        Assertions.assertThat(tutorModelResp.name()).isEqualTo(tutorUpdate.name());
                        Assertions.assertThat(tutorModelResp.user().email()).isEqualTo(tutorUpdate.email());

                }

                @Test
                @DisplayName("Atualizar deveria devolver 400 com mensagem quando o e-mail já está em uso")
                @WithMockUser
                void update_MustReturn400AndMessage_WhenUpdateEmailAlreadyInUse() throws Exception {
                        final String name = "Lorem Ipsum";
                        final String email = "lorem@email.com";

                        var tutorUpdate = TutorUpdateModel.builder()
                                        .id(1L)
                                        .name(name)
                                        .email(email)
                                        .build();

                        when(serviceMock.updateTutor(tutorUpdate)).thenThrow(new EmailAlreadyInUseException());
                        var tutorUpdateJson = updateModelToJson.write(tutorUpdate).getJson();

                        var responseBody = mockMvc.perform(
                                        MockMvcRequestBuilders.put(TUTORS_BASE_MAPPING)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(tutorUpdateJson))
                                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                        .andReturn()
                                        .getResponse().getContentAsString(StandardCharsets.UTF_8);

                        var messageResponse = mapper.readValue(responseBody, JsonMessage.class);

                        Assertions.assertThat(messageResponse.message()).isEqualTo("O e-mail já está em uso.");

                }

        }

}

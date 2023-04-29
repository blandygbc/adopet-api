package com.blandygbc.adopet.controller;

import static com.blandygbc.adopet.utils.TutorUtils.getCompleteTutorNewModel;
import static com.blandygbc.adopet.utils.TutorUtils.getTutorModelSavedNew;
import static com.blandygbc.adopet.utils.TutorUtils.getTutorNewModelWithEmail;
import static com.blandygbc.adopet.utils.TutorUtils.getTutorNewModelWithName;
import static com.blandygbc.adopet.utils.TutorUtils.getTutorNewModelWithPassword;
import static com.blandygbc.adopet.utils.TutorUtils.getTutorSavedNew;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blandygbc.adopet.TestsEnvProperties;
import com.blandygbc.adopet.domain.tutor.TutorModel;
import com.blandygbc.adopet.domain.tutor.TutorNewModel;
import com.blandygbc.adopet.domain.tutor.TutorService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TutorControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<TutorNewModel> newModelToJson;

        @Autowired
        private JacksonTester<TutorModel> modelToJson;

        @MockBean
        private TutorService service;

        private static final String TUTORS_BASE_MAPPING = "/tutors";

        @BeforeAll
        public static void setup() {
                TestsEnvProperties.load();
        }

        @Test
        @DisplayName("Deveria gerar erro ao cadastrar email em branco")
        void testAddCase1() throws Exception {
                var response = mvc.perform(
                                MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                newModelToJson
                                                                                .write(getTutorNewModelWithEmail(null))
                                                                                .getJson()))
                                .andReturn().getResponse();
                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria gerar erro ao cadastrar email inválido")
        void testAddCase2() throws Exception {
                var response = mvc.perform(
                                MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                newModelToJson
                                                                                .write(getTutorNewModelWithEmail(
                                                                                                "pessoaemail.com"))
                                                                                .getJson()))
                                .andReturn().getResponse();
                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria gerar erro ao cadastrar com senha em branco")
        void testAddCase3() throws Exception {
                var response = mvc.perform(
                                MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                newModelToJson
                                                                                .write(getTutorNewModelWithPassword(
                                                                                                null))
                                                                                .getJson()))
                                .andReturn().getResponse();
                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria gerar erro ao cadastrar com nome em branco")
        void testAddCase4() throws Exception {
                var response = mvc.perform(
                                MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                newModelToJson
                                                                                .write(getTutorNewModelWithName(null))
                                                                                .getJson()))
                                .andReturn().getResponse();
                System.out.println(response.getContentAsString());
                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deveria devolver ok com o tutor criado")
        void testAddCase5() throws Exception {
                when(service.createTutor(any())).thenReturn(getTutorSavedNew());
                
                var response = mvc.perform(
                MockMvcRequestBuilders.post(TUTORS_BASE_MAPPING)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(newModelToJson
                                                                .write(getCompleteTutorNewModel())
                                                                .getJson()))
                                .andReturn().getResponse();


                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                var tutorModelResp = modelToJson.write(getTutorModelSavedNew()).getJson();
                Assertions.assertThat(response.getContentAsString()).isEqualTo(tutorModelResp);
        }

        // @Test
        // void testDelete() {

        // }

        // @Test
        // void testDetail() {

        // }

        // @Test
        // void testGetAll() {

        // }

        // @Test
        // void testUpdate() {

        // }

}

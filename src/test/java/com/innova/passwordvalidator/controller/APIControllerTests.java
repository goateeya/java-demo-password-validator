package com.innova.passwordvalidator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.passwordvalidator.common.BaseRequest;
import com.innova.passwordvalidator.model.ValidatePasswordRequestEntity;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTests {
    @Autowired
    public MockMvc mockMvc;

    private String GetRequestPayload(String password) {
        String payload = null;
        ObjectMapper om = new ObjectMapper();
        BaseRequest<ValidatePasswordRequestEntity> request = new BaseRequest<>();
        ValidatePasswordRequestEntity entity = new ValidatePasswordRequestEntity(password);
        request.setBpId(UUID.randomUUID().toString());
        request.setRequestEntity(entity);
        try {
            payload = om.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }

    @Test
    public void testBadRequest() {
        try {
            String content = GetRequestPayload(null);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/validatePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUnpassablePassword() {
        try {
            String content = GetRequestPayload("12345");
            mockMvc.perform(MockMvcRequestBuilders.post("/api/validatePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$.code", Matchers.equalTo("900")));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPassPassword() {
        try {
            String content = GetRequestPayload("123456abc");
            mockMvc.perform(MockMvcRequestBuilders.post("/api/validatePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$.code", Matchers.equalTo("000")));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

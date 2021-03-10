package ua.mandybur.productmarket.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ua.mandybur.productmarket.model.dto.CustomerRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class AuthenticationControllerTest {
    public static final String REGISTER_ENDPOINT = "/register";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerCustomer_ReturnHttpStatusCreated() throws Exception {
        CustomerRequestDto dto = new CustomerRequestDto();
        dto.setEmail("tartar@gmail.com");
        dto.setPassword("23643746534");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(dto);
        mockMvc.perform(post(REGISTER_ENDPOINT)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }
}
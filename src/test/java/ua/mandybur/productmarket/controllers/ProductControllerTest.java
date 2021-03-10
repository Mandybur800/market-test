package ua.mandybur.productmarket.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ua.mandybur.productmarket.model.dto.ProductRequestDto;
import ua.mandybur.productmarket.model.dto.ProductResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class ProductControllerTest {
    public static final String PRODUCT_ENDPOINT = "/products";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addProduct_Ok() throws Exception {
        ProductRequestDto appleDto = new ProductRequestDto();
        appleDto.setName("apple");
        appleDto.setPrice(5);
        appleDto.setAmount(10);

        ObjectMapper mapper = new ObjectMapper();
        String appleContent = mapper.writeValueAsString(appleDto);
        mockMvc.perform(
                post(PRODUCT_ENDPOINT)
                    .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4")
                    .content(appleContent)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void addTwoSameProduct_Ok() throws Exception {
        ProductRequestDto appleDto = new ProductRequestDto();
        appleDto.setName("apple");
        appleDto.setPrice(5);
        appleDto.setAmount(10);

        ProductRequestDto appleDtoSame = new ProductRequestDto();
        appleDtoSame.setName("apple");
        appleDtoSame.setPrice(5);
        appleDtoSame.setAmount(5);


        ProductRequestDto anotherAppleDto = new ProductRequestDto();
        anotherAppleDto.setName("apple");
        anotherAppleDto.setPrice(10.4);
        anotherAppleDto.setAmount(30);

        ObjectMapper mapper = new ObjectMapper();
        String appleContent = mapper.writeValueAsString(appleDto);
        String appleSameContent = mapper.writeValueAsString(appleDtoSame);
        String anotherAppleContent = mapper.writeValueAsString(anotherAppleDto);
        mockMvc.perform(
                post(PRODUCT_ENDPOINT)
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4")
                        .content(appleContent)
                        .content(appleSameContent)
                        .content(anotherAppleContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        ResultActions perform = mockMvc.perform(
                get(PRODUCT_ENDPOINT)
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4")
                        .contentType(MediaType.APPLICATION_JSON));
        perform.andExpect(status().is2xxSuccessful());
        String response = perform.andReturn().getResponse().getContentAsString();
        List<ProductResponseDto> productResponseDtos = mapper.readValue(response,
                new TypeReference<List<ProductResponseDto>>() {});
        long apples = productResponseDtos.stream().filter(d -> d.getName().equals("apple")).count();
        Assertions.assertEquals(2, apples);
    }
}
package ua.mandybur.productmarket.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ua.mandybur.productmarket.model.dto.OrderResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerTest {
    public static final String ORDER_ENDPOINT = "/orders/complete";
    public static final String SHOPPING_CART_ENDPOINT = "/shopping-carts";
    public static final String INNER_USER_ID = "/1";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void completeOrder_Ok() throws Exception {
        mockMvc.perform(
                get(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=1&amount=5")
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
        ResultActions perform = mockMvc.perform(
                get(ORDER_ENDPOINT + INNER_USER_ID)
                        .header("Authorization", "Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"));
        perform
                .andExpect(status().is2xxSuccessful());
        String contentAsString = perform.andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        OrderResponseDto dto = mapper.readValue(contentAsString, OrderResponseDto.class);
        OrderResponseDto expected = new OrderResponseDto();
        expected.setPrice(81.6);
        expected.setId(1L);
        expected.setProducts(List.of("apple"));
        expected.setCustomerName("Bob");
        assertEquals(expected, dto);
    }
}
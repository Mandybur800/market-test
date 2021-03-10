package ua.mandybur.productmarket.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class ShoppingCartControllerTest {
    public static final String SHOPPING_CART_ENDPOINT = "/shopping-carts";
    public static final String INNER_USER_ID = "/1";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addProductToCart_Ok() throws Exception {
        mockMvc.perform(
                get(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=1&amount=5")
                            .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(
                get(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=2&amount=10")
                            .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void removeFromCart_Ok() throws Exception {
        mockMvc.perform(
                get(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=1&amount=5")
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(
                get(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=2&amount=10")
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(
                delete(SHOPPING_CART_ENDPOINT + INNER_USER_ID + "?productId=2")
                        .header("Authorization","Basic Z2dAZ21haWwuY29tOjEyMzQ1Njc4"))
                .andExpect(status().is2xxSuccessful());
    }
}
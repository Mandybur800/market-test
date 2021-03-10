package ua.mandybur.productmarket.model.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private double price;
    private int amount;
}

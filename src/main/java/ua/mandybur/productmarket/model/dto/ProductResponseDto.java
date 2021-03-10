package ua.mandybur.productmarket.model.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private double price;
    private boolean isAvailable;
}

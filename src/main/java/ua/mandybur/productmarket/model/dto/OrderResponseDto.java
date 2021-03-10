package ua.mandybur.productmarket.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private String customerName;
    private Double price;
    private List<String> products;
}

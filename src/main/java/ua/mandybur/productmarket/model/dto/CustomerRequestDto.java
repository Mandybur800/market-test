package ua.mandybur.productmarket.model.dto;

import lombok.Data;
import ua.mandybur.productmarket.util.EmailValidation;

@Data
public class CustomerRequestDto {
    @EmailValidation
    private String email;
    private String password;
}

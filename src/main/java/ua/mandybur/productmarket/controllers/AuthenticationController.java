package ua.mandybur.productmarket.controllers;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.mandybur.productmarket.model.dto.CustomerRequestDto;
import ua.mandybur.productmarket.security.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void registration(@RequestBody @Valid CustomerRequestDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}

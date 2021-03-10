package ua.mandybur.productmarket.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.dto.ProductRequestDto;
import ua.mandybur.productmarket.model.dto.ProductResponseDto;
import ua.mandybur.productmarket.service.ProductService;
import ua.mandybur.productmarket.service.mapper.ProductMapper;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping
    public void addNewProduct(@RequestBody ProductRequestDto dto) {
        Product product = mapper.toEntity(dto);
        productService.add(product);
    }

    @GetMapping("/")
    public ProductResponseDto getByName(@RequestParam String name) {
        return mapper.toDto(productService.getByName(name));
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts(
            @RequestParam(defaultValue = "POPULAR", required = false) OrderType sort,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction) {
        Sort sortBy = null;
        if (sort == OrderType.POPULAR) {
            sortBy = Sort.by(direction, "amount");
        } else {
            sortBy = Sort.by(direction, sort.name().toLowerCase());
        }
        return productService.getAll(sortBy).stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public enum OrderType {
        POPULAR, PRICE
    }
}

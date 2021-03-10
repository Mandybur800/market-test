package ua.mandybur.productmarket.service.mapper;

import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.dto.ProductRequestDto;
import ua.mandybur.productmarket.model.dto.ProductResponseDto;

@Service
public class ProductMapper {
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setAvailable(product.getAmount() > 0);
        return dto;
    }

    public Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        return product;
    }
}

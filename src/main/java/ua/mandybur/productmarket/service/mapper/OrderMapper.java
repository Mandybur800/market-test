package ua.mandybur.productmarket.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Order;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.dto.OrderResponseDto;

@Service
public class OrderMapper {
    public OrderResponseDto getDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setPrice(order.getPrice());
        List<String> products = order.getOrderedItems().keySet()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        dto.setProducts(products);
        return dto;
    }
}

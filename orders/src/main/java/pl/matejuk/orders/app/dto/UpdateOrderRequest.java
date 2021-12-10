package pl.matejuk.orders.app.dto;

import lombok.*;
import pl.matejuk.orders.app.entity.Order;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateOrderRequest {

    private int price;
    private String description;

    public static BiFunction<Order, UpdateOrderRequest, Order> dtoToEntityMapper(){
        return (order, updateOrderRequest) -> {
          order.setPrice(updateOrderRequest.getPrice());
          order.setDescription(updateOrderRequest.getDescription());
          return order;
        };
    }
}

package pl.matejuk.orders.app.dto;

import lombok.*;
import pl.matejuk.orders.app.entity.Order;

import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOrderResponse {

    private UUID id;
    private UUID clientId;
    private int price;
    private String description;

    public static Function<Order, GetOrderResponse> entityToDtoMapper(){
        return order -> GetOrderResponse.builder()
                .id(order.getId())
                .clientId(order.getClient().getId())
                .price(order.getPrice())
                .description(order.getDescription())
                .build();
    }
}

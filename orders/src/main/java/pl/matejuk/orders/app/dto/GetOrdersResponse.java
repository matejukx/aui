package pl.matejuk.orders.app.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOrdersResponse  {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Order{
        private UUID id;
        private String description;
    }

    @Singular
    private List<Order> orders;

    public static Function<Collection<pl.matejuk.orders.app.entity.Order>, GetOrdersResponse> entityToDtoMapper(){
        return orders -> {
            GetOrdersResponseBuilder response = GetOrdersResponse.builder();
            orders.stream()
                    .map(order -> Order.builder()
                            .id(order.getId())
                            .description(order.getDescription())
                            .build())
                    .forEach(response::order);
            return response.build();
        };
    }
}

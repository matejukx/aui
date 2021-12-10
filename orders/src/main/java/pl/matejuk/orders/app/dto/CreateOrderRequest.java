package pl.matejuk.orders.app.dto;

import lombok.*;
import pl.matejuk.orders.app.entity.Client;
import pl.matejuk.orders.app.entity.Order;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateOrderRequest {
    private UUID clientId;
    private int price;
    private String description;

    public static Function<CreateOrderRequest, Order> dtoToEntityMapper(
            Supplier<Client> clientSupplier){
        return createOrderRequest -> Order.builder()
                .price(createOrderRequest.getPrice())
                .description(createOrderRequest.getDescription())
                .date(LocalDate.now())
                .client(clientSupplier.get())
                .build();
    }
}

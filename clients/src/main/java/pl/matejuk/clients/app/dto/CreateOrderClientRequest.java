package pl.matejuk.clients.app.dto;

import lombok.*;

import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateOrderClientRequest {
    private Long Id;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @ToString
    @EqualsAndHashCode
    public static
    class OrderClient {
        private Long id;
    }
    public static Function<OrderClient, CreateOrderClientRequest> entityToDtoMapper(){
        return entity -> CreateOrderClientRequest.builder()
                .Id(entity.getId())
                .build();
    }
}

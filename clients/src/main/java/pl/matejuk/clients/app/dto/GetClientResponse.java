package pl.matejuk.clients.app.dto;

import lombok.*;
import pl.matejuk.clients.app.entity.Client;

import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetClientResponse {

    private Long id;
    private String name;
    private String surname;

    public static Function<Client, GetClientResponse> entityToDtoMapper(){
        return client -> GetClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .build();
    }
}

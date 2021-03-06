package pl.matejuk.clients.app.dto;

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
public class GetClientsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Client{
        private Long id;
        private String name;
    }

    @Singular
    private List<Client> clients;

    public static Function<Collection<pl.matejuk.clients.app.entity.Client>, GetClientsResponse> entityToDtoMapper(){
        return clients -> {
            GetClientsResponseBuilder response = GetClientsResponse.builder();
            clients.stream()
                    .map(client -> Client.builder()
                            .id(client.getId())
                            .name(client.getName())
                            .build())
                    .forEach(response::client);
            return response.build();
        };
    }
}

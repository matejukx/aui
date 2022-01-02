package pl.matejuk.clients.app.dto;

import lombok.*;
import pl.matejuk.clients.app.entity.ClientFile;

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
public class GetFilesResponse {

    @Singular
    private List<ClientFile> files;

    public static Function<Collection<ClientFile>, GetFilesResponse> entityToDtoMapper(){
        return files -> {
            GetFilesResponseBuilder response = GetFilesResponse.builder();
            files.stream()
                    .map(file -> ClientFile.builder()
                            .id(file.getId())
                            .author(file.getAuthor())
                            .description(file.getDescription())
                            .filename(file.getFilename())
                            .build())
                    .forEach(response::file);
            return response.build();
        };
    }
}

package pl.matejuk.clients.app.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "clientFiles")
public class ClientFile {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String author;
    @Column
    private String description;
    @Column
    private String filename;
}

package pl.matejuk.clients.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matejuk.clients.app.entity.ClientFile;

public interface IClientFileRepository extends JpaRepository<ClientFile, Long> {
}

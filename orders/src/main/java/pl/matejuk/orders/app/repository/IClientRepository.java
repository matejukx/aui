package pl.matejuk.orders.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matejuk.orders.app.entity.Client;

import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<Client, UUID> {

}

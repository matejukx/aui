package pl.matejuk.orders.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matejuk.orders.app.entity.Client;
import pl.matejuk.orders.app.entity.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID> {
    public List<Order> findAllByClient(Client client);
}

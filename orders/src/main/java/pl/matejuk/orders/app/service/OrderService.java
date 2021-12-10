package pl.matejuk.orders.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matejuk.orders.app.entity.Client;
import pl.matejuk.orders.app.entity.Order;
import pl.matejuk.orders.app.repository.IOrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final IOrderRepository repository;

    @Autowired
    public OrderService(IOrderRepository repository) {
        this.repository = repository;
    }

    public Optional<Order> find(UUID id) {
        return repository.findById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findAllByClient(Client client) { return repository.findAllByClient(client); }

    @Transactional
    public Order create(Order entity) {
        return repository.save(entity);
    }

    @Transactional
    public void delete(Order entity) {
        repository.delete(entity);
    }

    @Transactional
    public void deleteAll (List<Order> entities) { repository.deleteAll(entities);}

    @Transactional
    public void update(Order entity) {
        repository.save(entity);
    }
}

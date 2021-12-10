package pl.matejuk.orders.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.matejuk.orders.app.entity.Client;
import pl.matejuk.orders.app.entity.Order;
import pl.matejuk.orders.app.service.ClientService;
import pl.matejuk.orders.app.service.OrderService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public DataInitializer(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @PostConstruct
    void start(){
        new Thread(this::init).start();
    }

    void init() {
        while (clientService.findAll().stream().count() < 3){}
        var clients = clientService.findAll();

        Client client1 = clients.get(0);
        Client client2 = clients.get(1);
        Client client3 = clients.get(2);


        Order order1 = Order.builder()
                .price(120)
                .date(LocalDate.of(2021,5,5))
                .client(client1)
                .description("Paint restoration")
                .build();
        this.orderService.create(order1);

        Order order2 = Order.builder()
                .price(121300)
                .date(LocalDate.of(2021,3,15))
                .client(client1)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order2);

        Order order3 = Order.builder()
                .price(10000)
                .date(LocalDate.of(2021,5,13))
                .client(client1)
                .description("Wheels detailing")
                .build();
        this.orderService.create(order3);

        Order order4 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,4,11))
                .client(client1)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order4);

        Order order5 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,3,2))
                .client(client2)
                .description("Paint restoration")
                .build();
        this.orderService.create(order5);

        Order order6 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,2,5))
                .client(client3)
                .description("Wheels detailing")
                .build();
        this.orderService.create(order6);

        Order order7 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,1,23))
                .client(client3)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order7);
    }

}

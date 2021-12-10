package pl.matejuk.clients.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.matejuk.clients.app.dto.CreateOrderClientRequest;
import pl.matejuk.clients.app.entity.Client;
import pl.matejuk.clients.app.service.ClientService;
import pl.matejuk.clients.app.service.ClientOrderService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ClientService clientService;
    private final ClientOrderService clientOrderService;

    @Autowired
    public DataInitializer(ClientService clientService, ClientOrderService clientOrderService) {
        this.clientService = clientService;
        this.clientOrderService = clientOrderService;
    }

    @PostConstruct
    void init(){
        Client client1 = Client.builder()
                .name("Adam")
                .surname("Sandler")
                .build();
        this.clientService.create(client1);
        this.clientOrderService.create(new CreateOrderClientRequest.OrderClient(client1.getId()));

        Client client2 = Client.builder()
                .name("Max")
                .surname("Kolonko")
                .build();
        this.clientService.create(client2);
        this.clientOrderService.create(new CreateOrderClientRequest.OrderClient(client2.getId()));

        Client client3 = Client.builder()
                .name("Chuck")
                .surname("Norris")
                .build();
        this.clientService.create(client3);
        this.clientOrderService.create(new CreateOrderClientRequest.OrderClient(client3.getId()));
    }

}

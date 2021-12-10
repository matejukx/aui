package pl.matejuk.clients.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.matejuk.clients.app.dto.CreateOrderClientRequest;
import pl.matejuk.clients.app.entity.Client;

import javax.transaction.Transactional;


@Service
public class ClientOrderService {

    private final RestTemplate restTemplate;

    @Autowired
    public ClientOrderService(@Value("${orders.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    @Transactional
    public void create(CreateOrderClientRequest.OrderClient entity) {
        restTemplate.postForLocation("/clients", CreateOrderClientRequest.entityToDtoMapper().apply(entity));
    }

    @Transactional
    public void delete(Client entity) {
        restTemplate.delete("/clients/{username}", entity.getId());
    }

}

package pl.matejuk.orders.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.matejuk.orders.app.dto.CreateClientRequest;
import pl.matejuk.orders.app.dto.GetOrdersResponse;
import pl.matejuk.orders.app.entity.Client;
import pl.matejuk.orders.app.service.ClientService;
import pl.matejuk.orders.app.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("api/clients")
public class ClientOrdersController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}/orders")
    public ResponseEntity<GetOrdersResponse> getClientOrders(@PathVariable("id") UUID id){
        var clientEntity = this.clientService.find(id);
        if (clientEntity.isPresent()){
            var orders = this.orderService.findAllByClient(clientEntity.get());
            return ResponseEntity.ok(GetOrdersResponse.entityToDtoMapper().apply(orders));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CreateClientRequest request, UriComponentsBuilder builder){
        Client client = CreateClientRequest.dtoToEntityMapper().apply(request);
        client = this.clientService.create(client);
        return ResponseEntity.created(builder.pathSegment("api", "clients", "{id}").buildAndExpand(client.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") UUID id){
        var client = this.clientService.find(id);
        if (client.isPresent()){
            var orders = this.orderService.findAllByClient(client.get());
            this.orderService.deleteAll(orders);
            this.clientService.delete(client.get());
            return ResponseEntity.accepted().build();
        }
        else return ResponseEntity.notFound().build();
    }

}

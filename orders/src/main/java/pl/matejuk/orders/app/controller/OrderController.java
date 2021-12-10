package pl.matejuk.orders.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.matejuk.orders.app.dto.*;
import pl.matejuk.orders.app.service.ClientService;
import pl.matejuk.orders.app.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public OrderController(ClientService clientService, OrderService orderService){
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GetOrdersResponse> getOrders(){
        return ResponseEntity.ok(GetOrdersResponse.entityToDtoMapper().apply(orderService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("id") UUID id){
        var orderEntity = this.orderService.find(id);
        return orderEntity.map(value -> ResponseEntity.ok(GetOrderResponse.entityToDtoMapper().apply(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request, UriComponentsBuilder builder){
        var clientId = request.getClientId();
        var order = CreateOrderRequest
                .dtoToEntityMapper(() -> this.clientService.find(clientId).orElse(null))
                .apply(request);
        order = this.orderService.create(order);
        return ResponseEntity.created(builder.pathSegment("api", "orders", "{id}").buildAndExpand(order.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable("id") UUID id, @RequestBody UpdateOrderRequest request){
        var order = this.orderService.find(id);
        if (order.isPresent()){
            UpdateOrderRequest.dtoToEntityMapper().apply(order.get(), request);
            this.orderService.update(order.get());
            return ResponseEntity.accepted().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") UUID id){
        var order = this.orderService.find(id);
        if (order.isPresent()){
            this.orderService.delete(order.get());
            return ResponseEntity.accepted().build();
        }
        else return ResponseEntity.notFound().build();
    }
}

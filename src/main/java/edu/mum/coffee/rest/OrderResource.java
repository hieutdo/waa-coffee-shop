package edu.mum.coffee.rest;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.exception.HttpClientErrorException;
import edu.mum.coffee.exception.HttpNotFoundErrorException;
import edu.mum.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {
    private OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) throws URISyntaxException {
        if (order.getId() != null) {
            throw new HttpClientErrorException("A order to be created cannot have id attribute");
        }
        Order result = orderService.save(order);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId())).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            throw new HttpNotFoundErrorException("Order with id " + id + " does not exist");
        }
        return ResponseEntity.ok(order);
    }
}

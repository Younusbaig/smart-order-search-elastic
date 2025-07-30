package smart_order_system.smart_order_system_elastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart_order_system.smart_order_system_elastic.model.OrderDocument;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;
import smart_order_system.smart_order_system_elastic.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<OrderEntity> save(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping("/search")
    public List<OrderDocument> search(@RequestParam String customer) {
        return service.searchByCustomer(customer);
    }
}
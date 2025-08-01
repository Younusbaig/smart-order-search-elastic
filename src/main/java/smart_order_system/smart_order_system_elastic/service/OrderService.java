package smart_order_system.smart_order_system_elastic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import smart_order_system.smart_order_system_elastic.model.OrderDocument;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;
import smart_order_system.smart_order_system_elastic.repository.OrderESRepository;
import smart_order_system.smart_order_system_elastic.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderESRepository esRepo;

    public OrderEntity createOrder(OrderEntity order) {

        order.setOderDate(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());
        OrderEntity saved = orderRepo.save(order);


        // Index to ES
        OrderDocument doc = new OrderDocument();
        doc.setId(saved.getId());
        doc.setCustomerName(saved.getCustomerName());
        doc.setStatus(saved.getStatus());
        doc.setAmount(saved.getAmount());
        doc.setOderDate(order.getOderDate());
        doc.setCreatedAt(order.getCreatedAt());
        esRepo.save(doc);

        return saved;
    }

    public List<OrderDocument> searchByCustomer(String customer) {
        return esRepo.findByCustomerNameContainingIgnoreCase(customer);
    }

    public OrderEntity getById(String id){
        return orderRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order id not found"));

    }
}
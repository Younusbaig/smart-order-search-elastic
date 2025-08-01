package smart_order_system.smart_order_system_elastic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<OrderEntity> searchOrders(String customerName, String status, int page, int size ){
        Pageable pageable = PageRequest.of(page, size);

        if (customerName != null && status != null){
            return orderRepo.findByCustomerNameContainingIgnoreCaseAndStatus(customerName, status, pageable);
        } else if (customerName != null) {
            return orderRepo.findByCustomerNameContainingIgnoreCase(customerName, pageable);
        } else if (status != null) {
            return orderRepo.findByStatus(status, pageable);
        }
        else {
            return orderRepo.findAll(pageable);
        }

    }

    public OrderEntity getById(String id){
        return orderRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order id not found"));

    }
}
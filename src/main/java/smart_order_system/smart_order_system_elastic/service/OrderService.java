package smart_order_system.smart_order_system_elastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart_order_system.smart_order_system_elastic.model.OrderDocument;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;
import smart_order_system.smart_order_system_elastic.repository.OrderESRepository;
import smart_order_system.smart_order_system_elastic.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderESRepository esRepo;

    public OrderEntity createOrder(OrderEntity order) {
        OrderEntity saved = orderRepo.save(order);

        // Index to ES
        OrderDocument doc = new OrderDocument();
        doc.setId(saved.getId().toString());
        doc.setCustomerName(saved.getCustomerName());
        doc.setStatus(saved.getStatus());
        doc.setAmount(saved.getAmount());
        esRepo.save(doc);

        return saved;
    }

    public List<OrderDocument> searchByCustomer(String customer) {
        return esRepo.findByCustomerNameContainingIgnoreCase(customer);
    }
}
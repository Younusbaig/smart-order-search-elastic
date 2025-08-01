package smart_order_system.smart_order_system_elastic.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import smart_order_system.smart_order_system_elastic.model.OrderDocument;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Page<OrderEntity> findByCustomerNameContainingIgnoreCase(String customerName, Pageable pageable);
    Page<OrderEntity> findByStatus(String status, Pageable pageable);
    Page<OrderEntity> findByCustomerNameContainingIgnoreCaseAndStatus(String customerName, String status,
                                                                      Pageable pageable);

}
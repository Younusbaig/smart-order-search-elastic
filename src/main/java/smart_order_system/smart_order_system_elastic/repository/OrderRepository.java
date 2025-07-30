package smart_order_system.smart_order_system_elastic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}

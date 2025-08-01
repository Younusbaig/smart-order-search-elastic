package smart_order_system.smart_order_system_elastic.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import smart_order_system.smart_order_system_elastic.model.OrderEntity;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String>{}

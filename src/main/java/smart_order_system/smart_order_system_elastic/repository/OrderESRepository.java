package smart_order_system.smart_order_system_elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import smart_order_system.smart_order_system_elastic.model.OrderDocument;

import java.util.List;

public interface OrderESRepository extends ElasticsearchRepository<OrderDocument, String> {
    List<OrderDocument> findByCustomerNameContainingIgnoreCase(String customerName);
    List<OrderDocument> findByStatus(String status);
}

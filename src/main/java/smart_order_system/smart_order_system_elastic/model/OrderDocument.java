package smart_order_system.smart_order_system_elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import smart_order_system.smart_order_system_elastic.model.enums.Priority;
import smart_order_system.smart_order_system_elastic.model.enums.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "orders")
public class OrderDocument {
    @Id
    private String id;
    private String customerName;
    private String customerEmail;
    private Status status;
    private Double amount;
    private LocalDateTime oderDate;
    private Priority priority;
    private LocalDateTime deliveryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}

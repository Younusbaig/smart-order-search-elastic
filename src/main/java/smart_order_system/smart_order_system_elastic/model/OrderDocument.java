package smart_order_system.smart_order_system_elastic.model;

import org.springframework.data.elasticsearch.annotations.Document;
import jakarta.persistence.*;

@Document(indexName = "orders")
public class OrderDocument {
    @Id
    private String id;
    private String customerName;
    private String status;
    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}

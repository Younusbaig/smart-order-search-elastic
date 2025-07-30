package smart_order_system.smart_order_system_elastic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String customerName;
        private String status;
        private Double amount;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
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

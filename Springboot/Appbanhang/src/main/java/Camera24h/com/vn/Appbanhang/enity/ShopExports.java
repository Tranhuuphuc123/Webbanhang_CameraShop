package Camera24h.com.vn.Appbanhang.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_exports")
public class ShopExports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "export_date")
    private LocalDateTime exportDate;

    @Column(name = "description")
    private String description;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}

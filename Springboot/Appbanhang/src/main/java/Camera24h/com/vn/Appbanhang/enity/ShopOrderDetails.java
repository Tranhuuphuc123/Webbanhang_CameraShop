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
@IdClass(ShopOrderDetailsId.class) // cach import khóa chính kép (composite primary key)
@Table(name = "shop_order_details")
public class ShopOrderDetails {
    @Id
    private int orderId;

    @Id
    private int productId;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "discount")
    private float discount;

    @Column(name = "order_detail_status")
    private String orderDetailStatus;

    @Column(name = "date_allocated")
    private LocalDateTime dateAllocated;

}

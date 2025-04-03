package Camera24h.com.vn.Appbanhang.enity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop_product_discounts")
public class ShopProductDiscounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "discount_name")
    private String discountName;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}

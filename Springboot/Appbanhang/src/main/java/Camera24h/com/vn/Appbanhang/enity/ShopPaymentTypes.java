package Camera24h.com.vn.Appbanhang.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "shop_payment_types")
public class ShopPaymentTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "payment_name")
    private String paymentName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime creatAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

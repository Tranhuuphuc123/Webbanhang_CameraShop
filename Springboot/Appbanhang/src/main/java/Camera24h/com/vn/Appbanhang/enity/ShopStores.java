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
@Table(name = "shop_stores")
public class ShopStores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "store_code")
    private String storeCode;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}

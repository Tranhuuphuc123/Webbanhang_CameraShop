package Camera24h.com.vn.Appbanhang.enity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_import_details")
public class ShopImportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "import_id")
    private int importId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "unit_price")
    private float unitPrice;
}

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
@Table(name = "shop_products")
public class ShopProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "image")
    private String image;

    //short description nay tao thong bao ngan tren card giao dien
    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "standard_cost")
    private long standardCode;

    @Column(name = "list_price")
    private long listPrice;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    //xet che do boolean cho sanpham de neu sp qua han co he xet false nham an di khoi giao dien
    @Column(name = "discontinued")
    private boolean discontinued;

    //nhom is_new va is_featured quan ly viec sp do co phai la sp  moi hay ting nang moi hay khong
    @Column(name = "is_featured")
    private boolean isFeatured;

    @Column(name = "is_new")
    private boolean isNew;

    @Column(name="category_id")
    private int categoryId;

    @Column(name="supplier_id")
    private int supplierId;

    @Column(name = "created_at")
    private LocalDateTime creatAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}

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
@Table(name = "shop_customers")
public class ShopCustomers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "passWord")
    private String passWord;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "company")
    private String company;

    @Column(name = "phone")
    private String phone;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "active_code")
    private String activeCode;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdDt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

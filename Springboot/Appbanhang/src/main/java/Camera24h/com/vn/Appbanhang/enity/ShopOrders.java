package Camera24h.com.vn.Appbanhang.enity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_orders")
public class ShopOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "ship_address1")
    private String shipAddress1;

    @Column(name = "ship_address2")
    private String shipAddress2;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_state")
    private String shipState;

    @Column(name = "ship_postal_code")
    private String shipPostalCode;

    @Column(name = "ship_country")
    private String shipCountry;

    @Column(name = "shipping_fee")
    private float shippingFee;

    @Column(name = "payment_type")
    private int paymentType;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "shop_payment_type_id")
    private int shopPaymentTypeId;
}

package Camera24h.com.vn.Appbanhang.DTO.ShopOrdersDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrdersCreateRequestDTO {

    @NotNull(message = "khong duoc bo trong orderdate")
    private LocalDateTime orderDate;

    private LocalDateTime shippedDate;

    @NotBlank(message = "khong duoc bo trong ship name")
    @Length(min = 4, max = 30, message = "shipname tu 4-30 la hop le")
    private String shipName;

    @NotBlank(message = "Khong duoc bo trong address1")
    private String shipAddress1;

    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipPostalCode;

    @NotBlank(message = "khong duoc bo trong ship country")
    private String shipCountry;

    @NotNull(message = "khong duoc bo trong shippingFee")
    private float shippingFee;

    @NotNull(message = "khong duoc bo trong pamentTYpe")
    private int paymentType;

    private LocalDateTime paidDate;

    @NotBlank(message = "khong duoc bo trong order status")
    private String orderStatus;

    @NotNull(message = "khong duoc bo trong shopayment id")
    private int shopPaymentTypeId;

}

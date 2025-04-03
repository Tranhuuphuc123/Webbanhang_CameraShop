package Camera24h.com.vn.Appbanhang.DTO.ShopProductDiscountsDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopProductDiscountCreateRequestDTO {

    @NotNull(message = "productid khong duoc bo trong ")
    private int productId;

    @NotBlank(message = "discount name khong duoc bo trong ")
    private String discountName;

    @NotNull(message = "discount amount khong duoc bo trong")
    private double discountAmount;

    @NotBlank(message = "isFixed ")
    private boolean isFixed;

    @NotBlank(message = "start_date khong duoc bo trong")
    private LocalDateTime startDate;

    @NotBlank(message = "start_date khong duoc bo trong")
    private LocalDateTime endDate;
}

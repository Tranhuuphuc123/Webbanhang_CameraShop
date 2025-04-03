package Camera24h.com.vn.Appbanhang.DTO.ShopProductsReviewsDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductsReviewsCreateRequestDTO {

    @NotNull(message = "productid khong duoc bo trong ")
    private int productId;

    @NotNull(message = "rating khong duoc bo trong")
    private float rating;

    @NotNull(message = "khong duoc bo trong comment 🤬")
    private String comment;
}

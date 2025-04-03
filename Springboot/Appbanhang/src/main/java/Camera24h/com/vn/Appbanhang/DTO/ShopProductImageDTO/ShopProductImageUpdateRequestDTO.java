package Camera24h.com.vn.Appbanhang.DTO.ShopProductImageDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductImageUpdateRequestDTO {

    @NotNull(message = "productid khong duoc bo trong ")
    private int productId;

    @NotBlank(message = "Image khong duoc bo trong")
    private String image;
}

package Camera24h.com.vn.Appbanhang.DTO.ShopStoresDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopStoresCreateRequestDTO {
    @NotBlank(message = "khong duong bo trong ma store")
    @Length(min = 3, max = 64, message = "ma store chi dc dat ten trong pham vi 3 - 64 ky tu")
    private String storeCode;

    @NotBlank(message = "khong duong bo trong ten store")
    @Length(min = 1, max = 500, message = "ten store dat ten trong pham vi 1 - 500 ky tu")
    private String storeName;

    private String description;
    private String image;
}

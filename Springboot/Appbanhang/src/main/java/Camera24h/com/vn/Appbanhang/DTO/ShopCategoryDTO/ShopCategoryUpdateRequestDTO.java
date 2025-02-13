package Camera24h.com.vn.Appbanhang.DTO.ShopCategoryDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCategoryUpdateRequestDTO {
    @NotBlank(message = "khong duong bo trong ma category")
    @Length(min = 3, max = 64, message = "ma loai chi dc dat ten trong pham vi 3 - 64 ky tu")
    private String categoryCode;

    @NotBlank(message = "khong duong bo trong ten category")
    @Length(min = 1, max = 500, message = "ten loai dat ten trong pham vi 1 - 500 ky tu")
    private String categoryName;

    private String description;
    private String image;
}

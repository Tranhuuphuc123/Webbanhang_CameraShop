package Camera24h.com.vn.Appbanhang.DTO.ShopProductDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductUpdateRequestDTO {
    @NotBlank(message = "khong duong bo trong ma product")
    @Length(min = 3, max = 64, message = "ma product chi dc dat ten trong pham vi 3 - 64 ky tu")
    private String productCode;

    @NotBlank(message = "khong duong bo trong ten product")
    @Length(min = 1, max = 500, message = "ten product dat ten trong pham vi 1 - 500 ky tu")
    private String productName;

    private String image;
    private String shortDescription;
    private String description;

    @NotNull(message = "gia nhap khong dc bo trong")
    private long standardCode;

    @NotNull(message = "gia niem yet khong dc bo trong")
    private long listPrice;

    @NotNull(message = "so luong khong dc bo trong")
    private String quantityPerUnit;


    private boolean discontinued;
    private boolean isFeatured;
    private boolean isNew;

    @NotNull(message = "khong dc bo trong categoryId")
    private int categoryId;

    @NotNull(message = "khong duoc bo trong supplierId")
    private int supplierId;
}

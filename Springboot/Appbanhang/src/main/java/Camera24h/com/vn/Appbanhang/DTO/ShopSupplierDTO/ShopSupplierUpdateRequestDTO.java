package Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopSupplierUpdateRequestDTO {
    @NotBlank(message = "khong duong bo trong ma supplier")
    @Length(min = 3, max = 64, message = "ma loai chi dc dat ten trong pham vi 3 - 64 ky tu")
    private String supplierCode;

    @NotBlank(message = "khong duong bo trong ten supplier")
    @Length(min = 1, max = 500, message = "ten loai dat ten trong pham vi 1 - 500 ky tu")
    private String supplierName;

    private String description;
    private String image;
}

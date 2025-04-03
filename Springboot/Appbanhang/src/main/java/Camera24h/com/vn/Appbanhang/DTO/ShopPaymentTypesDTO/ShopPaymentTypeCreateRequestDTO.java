package Camera24h.com.vn.Appbanhang.DTO.ShopPaymentTypesDTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopPaymentTypeCreateRequestDTO {

    @NotBlank(message = "Khong duoc bo trong ma payment")
    @Length(min = 3, max = 64, message = "ma paymet chi dc dat ten trong pham vi 3 - 64 ky tu")
    private String paymentCode;

    @NotBlank(message = "khong duong bo trong ten payment")
    @Length(min = 1, max = 500, message = "ten payment dat ten trong pham vi 1 - 500 ky tu")
    private String paymentName;

    private String description;

    private String image;
}

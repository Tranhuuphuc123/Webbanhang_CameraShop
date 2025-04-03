package Camera24h.com.vn.Appbanhang.DTO.ShopCustomersDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCustomersUpdateRequestDTO {
    @NotBlank(message = "khong duoc bo trong username")
    @Length(min = 3, max = 50, message = "ten  dat ten trong pham vi 3 - 50 ky tu")
    private String userName;

    @NotBlank(message = "khong duoc bo trong pass word")
    @Length(min = 6, max = 50, message = "pass word dat ten trong pham vi 6 - 50 ky tu")
    private String passWord;

    @NotBlank(message = "Khong duoc bo trong ten")
    private String firstName;

    @NotBlank(message = "Khong duoc bo trong ho")
    private String lastName;

    @NotBlank(message = "Khong duoc bo trong email")
    private String email;

    private String avatar;
    private String company;

    @NotBlank(message = "Khong duoc bo trong phone")
    private String phone;

    private String billingAddress;
    private String shippingAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String rememberToken;
    private String activeCode;

    @NotNull(message = "khong duoc bo trong status")
    private int status;
}

package Camera24h.com.vn.Appbanhang.DTO.ShopExportsDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopExportsUpdateRequestDTO {
    @NotNull(message = "Khong duoc bo trong store id")
    private int storeId;

    @NotNull(message = "Khong duoc bo trong employee id")
    private int employeeId;

    @NotNull(message = "Khong duoc bo trong export date")
    private LocalDateTime exportDate;

    private String description;
    private int orderId;

}

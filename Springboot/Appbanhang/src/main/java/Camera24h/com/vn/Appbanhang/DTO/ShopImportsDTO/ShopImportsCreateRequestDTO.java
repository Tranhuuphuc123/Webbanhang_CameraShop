package Camera24h.com.vn.Appbanhang.DTO.ShopImportsDTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopImportsCreateRequestDTO {

    @NotNull(message = "Khong duoc bo trong store id")
    private int storeId;

    @NotNull(message = "Khong duoc bo trong employee id")
    private int employeeId;

    @NotNull(message = "Khong duoc bo trong import date")
    private LocalDateTime importDate;

}

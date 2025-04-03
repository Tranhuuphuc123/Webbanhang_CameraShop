package Camera24h.com.vn.Appbanhang.DTO.ShopOrderDetailsDTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderDetailsCreateRequestDTO {

    @NotNull(message = "Khong duoc bo trong orderId")
    private int orderId;

    @NotNull(message = "Khong duoc bo trong productId")
    private int productId;

    @NotNull(message = "Khong duoc bo trong quantity")
    private float quantity;

    @NotNull(message = "Khong duoc bo trong unit price")
    private float unitPrice;

    @NotNull(message = "khong duoc bo trong discount")
    private float discount;

    private String orderDetailStatus;

    private LocalDateTime dateAllocated;
}

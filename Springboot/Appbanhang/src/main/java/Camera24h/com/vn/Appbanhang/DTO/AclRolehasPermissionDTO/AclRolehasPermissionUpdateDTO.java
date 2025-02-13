package Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AclRolehasPermissionUpdateDTO {
    @NotNull(message = "phai chon ten quyen can cap nhat")
    private int permissionId;

    @NotNull(message = "phai chon vai tro can cap nhat")
    private int roleId;
}

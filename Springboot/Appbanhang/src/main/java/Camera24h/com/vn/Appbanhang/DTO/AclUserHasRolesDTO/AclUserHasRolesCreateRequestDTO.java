package Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AclUserHasRolesCreateRequestDTO {

    @NotNull(message = "phai chon ten user can cap vai tro")
    private int userId;

    @NotNull(message = "phai chon vai tro can cap cho user")
    private int roleId;
}

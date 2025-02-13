package Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

//batcch create ly do: role thi co te nhieu vai tro co the create nhieu permision co mot roleva nc lai
public class AclRolehasPermissionCreateDTO {
    @NotNull(message = "phai chon quyen can xet cho vai  tro  tren")
    private List<Integer> listPermission;


    @NotNull(message = "phai chon roleid can cap quyen")
    private int roleId;
}

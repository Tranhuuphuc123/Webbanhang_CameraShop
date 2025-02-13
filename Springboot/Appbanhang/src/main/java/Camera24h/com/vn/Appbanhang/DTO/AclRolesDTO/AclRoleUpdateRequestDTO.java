package Camera24h.com.vn.Appbanhang.DTO.AclRolesDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AclRoleUpdateRequestDTO {

    @NotBlank(message = "name khong dc bo trong")
    private String name;

    @NotBlank(message = "displayName khong dc bo trong")
    private String displayName;

    @NotBlank(message = "guardName khong dc bo trong")
    private String guardName;
}

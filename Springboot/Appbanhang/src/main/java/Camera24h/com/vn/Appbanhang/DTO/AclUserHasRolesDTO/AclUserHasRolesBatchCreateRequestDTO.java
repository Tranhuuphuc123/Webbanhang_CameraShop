package Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

//Batch: hàng loạt, ý nghìa là diến dãi chứa đc nhiều thuôc tính cùng lucs
public class AclUserHasRolesBatchCreateRequestDTO {

    @NotNull(message = "phai chon ten user can cap vai tro")
    private int userId;

    /*nếu chỉ là kiểu int thì nó chi la mot bien luu chu lan dc mot value
    * => ta chuyen no thanh kieu list de chua nhieu value tanh mot mang luon*/
    @NotNull(message = "phai chon vai tro can cap cho user")
    private List<Integer> listRoleId;
}

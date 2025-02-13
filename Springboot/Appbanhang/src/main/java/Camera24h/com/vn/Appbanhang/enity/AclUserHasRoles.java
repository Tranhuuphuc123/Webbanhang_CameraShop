package Camera24h.com.vn.Appbanhang.enity;

//xay dung class enity choa table AclUserHasRoles

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acl_user_has_roles")
public class AclUserHasRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;

    //get set dung loombook

}

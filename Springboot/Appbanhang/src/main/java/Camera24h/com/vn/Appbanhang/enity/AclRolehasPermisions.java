package Camera24h.com.vn.Appbanhang.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acl_rol_has_permissions")
public class AclRolehasPermisions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /****NOTE: KHI SỬ DỤNG ANNOTATION THI ĐÃ CÓ LK CÁC CỘT HÀNG NEN KHÔNG CẦN KHAI BÁO RA*****/
//    @Column(name = "role_id")
//    private int roleId;
//
//    @Column(name = "permission_id")
//    private int permissionId;

    //lien ket khoa ngoai quan he N -1 vói table Acl_Role
    @ManyToOne
    private AclRole role;

    //lien ket khoa ngoai quan he N-1 với table Acl_permissions
    @ManyToOne
    private AclPermissions permissions;
}

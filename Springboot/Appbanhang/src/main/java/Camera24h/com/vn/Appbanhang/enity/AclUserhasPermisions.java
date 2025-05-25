package Camera24h.com.vn.Appbanhang.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acl_user_has_permissions")
public class AclUserhasPermisions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /****NOTE: KHI SỬ DỤNG ANNOTATION THI ĐÃ CÓ LK CÁC CỘT HÀNG NEN KHÔNG CẦN KHAI BÁO RA*****/
//    @Column(name = "user_id")
//    private int userId;
//
//    @Column(name = "permission_id")
//    private int permissionId;


    /******tạo liên kết khóa ngoại N -1 với table User*********/
    @ManyToOne
    private AclUser user;

    //lien ket khoa ngoai quan he N-1 với table Acl_permissions
    @ManyToOne
    private AclPermissions permissions;
}

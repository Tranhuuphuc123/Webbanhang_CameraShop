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

    /****NOTE: KHI SỬ DỤNG ANNOTATION THI ĐÃ CÓ LK CÁC CỘT HÀNG NEN KHÔNG CẦN KHAI BÁO RA*****/
//    @Column(name = "user_id")
//    private int userId;
//
//    @Column(name = "role_id")
//    private int roleId;

    //get set dung loombook

    /******khóa ngoại liên kết******
     * '@ManyToOne': annotation chỉ liên kết giưã hai enity với nhau
     * quan hệ userhasroles với user là quan hệ N-1
     * */
    @ManyToOne
    private AclUser user;

    //lien ket khoa ngoai quan he  N - 1 vơi table role
    @ManyToOne
    private AclRole role;

}

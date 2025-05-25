package Camera24h.com.vn.Appbanhang.enity;
//các enity của table AclRole trong database MySQL

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //lib loombook
@Table(name="acl_roles") // ánh xạ table bên mysql
public class AclRole {
    //khai bao cac properties anh xa csdl tuong ung
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //danh dau id tu tang
    private int id;

    @Column(name = "name") //anh xa den ten dung cua no ben csdl mysql
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "guard_name")
    private String guardName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //get/set dung loombook lib

    //tạo khóa ngoai lien ket vơi table con quan hệ 1- N vs table acl_role_has_permissions
    @OneToMany(mappedBy = "role")
    private List<AclRolehasPermisions> permisions;

}

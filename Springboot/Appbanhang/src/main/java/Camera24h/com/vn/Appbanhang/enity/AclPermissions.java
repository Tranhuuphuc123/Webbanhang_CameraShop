package Camera24h.com.vn.Appbanhang.enity;

//class entity của table acl_permissions

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "acl_permissions")
public class AclPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displyName;

    @Column(name = "guard_name")
    private String guardName;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;


//    //tao lien ket khao ngoai quan he 1 -N vơi table alc_role_has_permission
//    @OneToMany(mappedBy = "permissions")
//    private List<AclRolehasPermisions> roles;
//
//    //tao lien ket khoa ngoai quan he 1 - N vơi table acl_user_has_permissions
//    @OneToMany(mappedBy = "permissions")
//    private List<AclUserhasPermisions> users;


}

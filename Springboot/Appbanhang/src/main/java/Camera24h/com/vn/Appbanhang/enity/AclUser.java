    package Camera24h.com.vn.Appbanhang.enity;
/* class Enity này dùng mapping đến co sở dữ liệu bên MySQl*/

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

    // class Enity AclUser ánh xạ mapping đến bảng table acl_sers bên MySQL
//@Enity danh dau cho code biet day khong phai class thong thuong ma class Enity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acl_users")
public class AclUser {

    //@Id danh dau la khoa chinh tu tang
    // @GeneratedValue(strategy = GenerationType.IDENTITY) giai thua tang theo Identity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String last_name;
    private String first_name;
    private String email;

    //@Nullable cho phép đc null vì trong csdl mình cho notnull
    @Nullable
    private String avatar;
    @Nullable
    private String job_title;
    @Nullable
    private String department;
    @Nullable
    private Integer manager_id;

    private String phone;
    private String address1;

    @Nullable
    private String address2;
    @Nullable
    private String city;
    @Nullable
    private String state;
    @Nullable
    private String postal_code;
    @Nullable
    private String country;
    @Nullable
    private String remember_token;
    @Nullable
    private String active_code;

    private Long status;

    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private Date updated_at;




    /****>>>>liên kết khóa ngoại với table user_has_roles***<<<<
     * => giải nghĩa:
     *  + @OneTomany: Là một annotation được dùng để đánh dấu mối quan
     *  hệ "một-nhiều" (one-to-many) giữa hai thực thể (entity).
     *  => quan hệ giữa user và userhasroles là quan hệ 1-N
     *
     *  +mappedBy="key": liên kết voi khóa ngoại của thèn con bằng mappedBy, user là key
     * tên mà minh tạo khóa ngoại liên kết ở thằng con giống như ở t/h này
     * */
    @OneToMany(mappedBy = "user")
    private List<AclUserHasRoles> roles;


    /*****liên kết khóa ng oại với table acl_role_has_permission******/
    @OneToMany(mappedBy = "user")
    private List<AclUserhasPermisions> permisions;

}

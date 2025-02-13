package Camera24h.com.vn.Appbanhang.DTO.AclUserDTO;
/*lớp DTo update cho table acl_user*/

import lombok.Data;

//@Data -> lib loombook tổng hợp cả getter và setter trong DTO
@Data
public class AclUserUpdateRequestDTO {
    private String password;
    private String last_name;
    private String first_name;
    private String email;
    private String avatar;
    private String job_title;
    private String department;
    private Integer manager_id;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String remember_token;
    private String active_code;
    private Long status;

    //getter and setter -> sử dụng lib loombook thay vì viết tay kiểu truyền thống
}

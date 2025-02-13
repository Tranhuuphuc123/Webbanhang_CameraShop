package Camera24h.com.vn.Appbanhang.DTO.AclUserDTO;
/*
* DTO là gì?
*  => lớp lữu trữ thông tin người dùng
* <=> thực ra DTO cũng tuong tự Entity xây dưng y như thế
* tuy nhiên vd table acl-users nó có tới 22 trường value thoong tin
* mà mình chỉ cần tao trang đk user để client nhập có 8 thông tin
* thì không thể controller gọi đến service roi service án ánh xạ
* đủ 22 trường thông tin của entity từ csdl đc (co nhiều trường nó cho null)
* ===> vậy vây DTO là lớp dùng để lwuu trữ dữ liệu người dùng mà ở đó nó chỉ
* cần các thông tin càn thiết mà mình cần chèn thui
*
*****KẾT LUẬN****
* => DTO (Data Transfer Object) là một lớp dùng để truyền dữ liệu giữa các tầng trong
* mô hình ba lớp (Three-Tier Architecture):
* => DTO giúp tối ưu hóa và chuẩn hóa việc truyền dữ liệu giữa các tầng mà không cần
* phải gửi trực tiếp các thực thể từ cơ sở dữ liệu (Entity).
*
* ****Tóm lại:*****
 + Entity: Tương tác với cơ sở dữ liệu.
 + DTO: Truyền dữ liệu giữa các tầng.
 + Service: Chuyển đổi giữa Entity và DTO để thực hiện logic nghiệp vụ.
=====> DTO làm cho ứng dụng rõ ràng, bảo mật và dễ bảo trì hơn, đặc biệt là trong các dự án lớn.
* */


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

//xây dựng dto ánh xạ các value cần thiết truyến cho service xử lý creat class acl_user
//@Data -> lib loombook tổng hợp cả getter và setter trong DTO
@Data
public class AclUserCreateRequestDTO {
    /*khi  tạo các properties nên xác thực validation với các anotation bên duois (xem thêm bài
     xử lý service validation trong nó có mục anotation của validation)
     => có thể thiết lập thêm nội dung báo lỗi thông qua thuộc tính message trong cacs anotation validation
    * */

    @NotBlank(message = "không đc để username không đc để trống")
    @Length(min = 3, max = 70, message = "tên usernam phái có ít nhất là 3 ký tự trở lên")
    private String username;

    @NotBlank(message = "mật khẩu không đc để trống")
    @Length(min = 6, max = 128, message = "mk ít nhât phai có ít nhất 6 - 128 ký tự")
    private String password;

    @NotBlank(message = "không đc để trống cột này")
    @Length(max = 200)
    private String last_name;

    @NotBlank(message = "không đc để trống cột này")
    @Length(max = 200)
    private String first_name;

    @NotBlank(message = "không đc để trống cột này")
    // nó cũng là mot RegEx đc spring boot hỗ trợ sẵn thay vì dùng RegEx tự viết
    @Email(message = "phải đúng cấu trúc <name>@gmail.com")
    //@Pattern(regexp = "^(.+)@(\\\\S+)$") //-> có thể dùng RegEx đặt quy chuẩn biểu thức chinh quy cho email
    private String email;

    @NotBlank(message = "không được để trống")
    @Pattern(message = "phải đúng cấu trúc xxxx-xxx-xxx",regexp = "^\\d{4}\\d{3}\\d{3}$")
    private String phone;

    @NotBlank(message = "không được để trống")
    @Length(max = 500)
    private String address1;

    @NotNull(message = "không được để trống")
    private Long status;

    //getter and setter -> dùng loombook lib @Data cho gon
}

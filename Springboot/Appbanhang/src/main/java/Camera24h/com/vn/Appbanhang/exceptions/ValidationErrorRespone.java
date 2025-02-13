package Camera24h.com.vn.Appbanhang.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.apache.el.util.Validation;

import java.util.ArrayList;
import java.util.List;

/*Lớp này sinh ra nhăm mục đich ghi nhận các constraint validaton
*  -> constraint validation: chính là các ràng buộc thiết lặp bắt lỗi neu vi phạm ở lớp DTO
* (@Notnull, @Email, @NotBlank.... chính là các thiết lặp này ở DTO)
*
* => lớp này sinh ra để ghi nhân các thiết lặp ở đây và tiến hành đc gọi vào GlobalException để ghi
* nhận xử lý lỗi hé và trả ve ds các lỗi ghi nhạn ánh xạ đc từ lớp Violation với hai trường là
* filename và message  */


@Getter
@Setter
public class ValidationErrorRespone {
    // tạo ra list chứ các lỗi ghi nhận các message lỗi và filename cụ thể của các file từ class Violations vào đây
    private List<Violations> violations = new ArrayList<>();


    //getter và setter sài loombook nha
}

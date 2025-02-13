package Camera24h.com.vn.Appbanhang.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/* lớp này tạo ra 2 properties de ghi nhận 2 cai thong tin vi pham
*  + filename
*  + message
*  ==> nghĩa là ghi nhận 2 trường mà sẽ thông báo ra cho người dùng biết là fielname của cái nào
* đang vi phàm và message thng báo tương ứng mà mình đã khi báo annotation validation trong \DTO
* <=> tức là ánh xạ các value của filename tương ứng và messsage thông tin báo loi ben DTO qua
* exception na
* ==> lưu ý là có thể viết truc tiếp trong file ValidationsErroResponse luôn cũng đc tuy nhiên
* sau này nếu xu lý cho nhiều truường thông tin ở nhiều table khác nhau việc tách riêng thế này
* khiến code gọn ràng dễ hiễu và dễ quản lý hơn với lập trình viên -> tinh gọn code hơn là viet
* một file duy nhất
* */

//@AllArgsConstructor: là mot loombook sinh ra contructor có tham số
@Data
@AllArgsConstructor
public class Violations {
  private String fielname;
  private String message;

  //contructor và get/set sẽ đc khai báo vơi lib loombook: @Data
}

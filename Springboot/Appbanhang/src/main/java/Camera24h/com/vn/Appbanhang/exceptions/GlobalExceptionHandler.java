package Camera24h.com.vn.Appbanhang.exceptions;
/*đây là file chính xử lý bắt lỗi exception toàn cục cho cả project web này
*  ==> đây là file exception chủ lực và chính của mục exception này
*  ==> xử lý logic nghiệp vụ exception global cho project này nằm ở đây
* */


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

/*1/ @ControllerAdvice; annotation này đánh dấu cho spring bít đây là class xử lý lỗi exception chính
và nó tự ánh xạ cái exception này vào controller biết thông qua cai annotation @Valid đã khai báo ở controller
trc đó.
* 2/ @Order(Ordered.HIGHEST_PRECEDENCE):  cho spring boot đay là class exception có dộ ưu tiên cao nhất
* sẽ đc ưu tiên xư lý đâu tiên
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /*****chú thích code - chú thích các annotation ****
     a/ @ExceptionHandler(ConstraintViolationException.class): anotation đanh dấu ghi nhận đay là lớp
     thực thi xử ly exception
     b/ @ResponseStatus(HttpStatus.BAD_REQUEST): chỉ định method xử lý là bad resquest từ client guire
     -.> lưu ý: trong method của ConstraintViolationException co rat nhieu method xử lý khác nhau vì vậy
     việc chỉ định cụ thể sẽ giúp spring dễ dàng thao tác hơn
     c/ @ResponseBody: chỉ định chỗ xử lý dl lấy từ request body vì nếu liên quan đên form thì sẽ liên quan
      đến method post nến value thg sẽ lưu ở body

     d/ ConstraintViolationException: annottion này được sử dụng để báo hiệu rằng một hoặc nhiều ràng buộc
     (constraints) của một đối tượng đã bị vi phạm khi thực hiện kiểm tra tính hợp lệ--> nó sẽ trả về dánh sách
     các lỗi về đây
     e/ MethodArgumentNotValidException: là một exception được ném ra khi một đối tượng request chứa các tham
     số hoặc trường không hợp lệ dựa trên các ràng buộc được định nghĩa bằng annotations validation
     (ví dụ: @NotNull, @Size, @Email,...). Nó thường xảy ra khi sử dụng Spring Boot và các Bean Validation (JSR-380).
    */


    /****************NHOM EXCEPTION 1 - XU LY SERVICE VALIDATION TU NEM THROW*******************/


    /*****1-0 phần này là xử lý lỗi quăng ra từ viec kiểm tra ràng buộc ở mức class (xu ly
     exception ơ phia service validation - ma loi la 500 Interal server error)
     ==>luu y: bat loi nay se tu quang ra boi throw(throw quang loi xu ly o luan ly logic code
     chinh la function nay)*****/
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // ma loi 500
    @ResponseBody
    ValidationErrorRespone onConstraintValidatioExcepttion(ConstraintViolationException e){
        //gọi và khởi tạo lớp ghi nhận trả về lỗi ValidattionsErrorResponse
        ValidationErrorRespone error = new ValidationErrorRespone();
        //trả về ds các loi
        for(ConstraintViolation violation :  e.getConstraintViolations()){
            error.getViolations().add(
                    //violation.getPropertyPath().toString(); trả về tt tên message(filename cụ thể )
                    //violation.getMessage(): lấy nd trong message đó ra thong báo lõi dựa trên nd message đó
                    new Violations(violation.getPropertyPath().toString(), violation.getMessage())
            );
        }
        return error;
    }





/********************************************************DAY PHAN CACH 2 NHOM*************************************************************************/






    /****************NHOM EXCEPTION 2 - XU LY DTO VALIDATION*******************/
    /****2 - tao function bắt lối theo method
     * => xử lý lỗi quăng ra từ tham số trong action(function method) của controller
     * => mức này xử lý exception ở phía DTO Controller validation
     * ******/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //kieu ma loij 400
    @ResponseBody
    ValidationErrorRespone onMethodArgumentValidException(MethodArgumentNotValidException e){
        //gọi và khởi tạo lớp ghi nhận trả về lỗi ValidattionsErrorResponse
        ValidationErrorRespone error = new ValidationErrorRespone();
        for(FieldError fileError : e.getBindingResult().getFieldErrors()){
            error.getViolations().add(
                    new Violations(fileError.getField(), fileError.getDefaultMessage())
            );
        }
        return  error;
    }

}

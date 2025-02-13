package Camera24h.com.vn.Appbanhang.controller;
/*đây là controller trong mô hình mvc nó dùng để thực hiện chức năng cụ thể la thiết
lặp đường dẫn mapping urlPattern để thực thi truy cập yêu cầu theo moo hình client server
*  <=> nghĩa la ta đã viết service luận lý crud rồi vậy ta sẽ gọi đến service ở đây
* đê tến hành thực thi theo yểu cầu từ client khi mà người truy cap và gửi yêu cầu
* từ phía client trình duyệt lên theo urlPattern mà mình thiết lặp ở ngay controller
* này
==>thi theo đg dẫn mapping urlPattern đó nó trỏ đung với controller đã thiết kế thì nó mởi gọi đc
đến service và repository để thực thi đúng yếu cầu crud đã dc tk trc đó
<=> nếu không có controller thì client không thể truy cập đến server đúng chứ nói chi
đến viêc gọi đến các mehtod luan lý từ service để thuc hiện theo yêu cầu người dùng

*****tóm lại****
 => controller đúng nghĩa là một lớp dùng điều phối
  + điều phối urlPattern mapping để client truy cập đúng thì mới thực thi
  + điều phối lớp luận lý service để thực thi crud
  ..
  vậy là code chi tiếl luận lý logic là ở services còn controller là trung tâm
  diều phối các lớp chức năng khác nhau chứ nó không trực tiếp thực hiện luận lý
  logic chưc năng cụ thể mà chỉ đóng vai trò điều phối
* */

import Camera24h.com.vn.Appbanhang.DTO.AclUserDTO.AclUserCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserDTO.AclUserUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.AclUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//@RestController đánh dấu là controller theo chuẩn RestfullApi
//RequestMapping(): thiết lặp urlPattern
@RestController
@RequestMapping("/api/v1/acl-users")
public class AclUserController {

    /*tiến hành ghi log nhật ký hoạt động - lôi trong dự án
    => có nhiều lib loggger: đay ta sài import org.slf4j.Logger;
    */
    Logger logger = LoggerFactory.getLogger(AclUserController.class);

    /*khởi tạo trỏ đến service
    *  @Autowired giúp ánh xạ bean phù hơp và inject các phụ thuộc từ bean đó vào
    * một cách auto
    * */
    @Autowired
    private AclUserService aclUserService;



    /******************************************I/ RENDER GET****************************************************/
    /******1 - viết function hành động điều phối render lấy hiển thị dữ liệu theo chuẩn ReponseEntity
     * ==> lưu ý: có get xử lý phân trang
     * ****/
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> index(){
//        //gọi đến service thực hiện truy vấn CRUD - cụ thể là getAll dữ liệu mà mình viết logic bên đó
//        return  aclUserService.getAllUsers();
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> index(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                     @RequestParam(defaultValue = "3") Integer pageSize,
                                                     @RequestParam(defaultValue = "id")  String sortBy){
        //gọi đến service thực hiện truy vấn CRUD - cụ thể là getAll dữ liệu mà mình viết logic bên đó
        return  aclUserService.getAllUserPagination(pageNumber,pageSize, sortBy);
    }



    /********************************************II - PUT**************************************************/
    /******2 - viết function hành động điều phối create theo chuẩn ReponseEntity (creater thì nên sài method post)****/
    //@PostMapping("/create"): thiết lập mapping theo chuẩn method post
    /*@RequestBody AclUserCreateRequestDTO res:
    * -> giúp nhận dữ liệu từ client dưới dạng JSON/XML, chuyển đổi nó thành một đối tượng Java
    *  (AclUserCreateRequestDTO), và đưa vào xử lý trong phương thức create.
    * <=> nghĩa là DTO nó luu dữ liệu người dùng do mình viết để ánh xạ vlaue cần thiết
    * <=> ở phía giao diện nó hiện thì như một form thông thin cho client điền và -. gửi lại dưới dạng
    * xml/json
    * ===> cúi cùng @RequestBody này nó chuyển đổi thành đối tượng java và đưa vào controller mapping creat
    * xử lý và lưu lại vào csdl
    *
    *  @Valid: annotation kiểm tra tính hợp lẹ của các validation thiết lap ở DTO và ánh xạ các excption xử lý
    * bắt  lỗi  thông qua annotation @ControllerAdvice đc khai báo ở file exception GlobalExceptionHandler
    * */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid AclUserCreateRequestDTO res){
        // xu ly bat loi nem ra throw tu service voi try catch
        try{
            //ghi log nhật ks loi
            logger.info("ghi log nhật ký lỗi hệ thống chức năng create controller");

            // gọi đến service lưu csdl từ dto -> đc gửi lê từ client thông qua form điền các value dto
            return aclUserService.createUser(res);
        }catch (Exception ex){
            logger.error("error", ex);

            //khoi tao bien luu response tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statuscode", 501);
            response.put("msg", "dữ liệu đã tồn tại tên username trong csdl, yêu cau nhập lại");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    /*******************************************III - DELETE***************************************************/
    /******3 - viết function hành động điều phối chức delete theo chuẩn ReponseEntity ****/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
       return aclUserService.deleteUser(id);
    }





    /*******************************************IV - PUT**********************************************/
    /******4 - viết function hành động điều phối chức update theo chuẩn ReponseEntity ****/

    /* @Putmapping là annotation dung de dánh dau chuc nang put trong spring boot theo id
    * @PutMapping("/update/{id)"): cách làm truyền id vào url patter -> trong function thi them @PathVariable Integer id
    * --> trong bài nay ta khong truyền theo cách đó
    * */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id,  @RequestBody AclUserUpdateRequestDTO res ){
        // cách truyền id  vào url: return aclUserService.upateUser(id, res);
        return aclUserService.upateUser(id, res);
    }
}

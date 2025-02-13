package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.AclUserDTO.AclUserCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserDTO.AclUserUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.AclUser;
import Camera24h.com.vn.Appbanhang.exceptions.ValidationErrorRespone;
import Camera24h.com.vn.Appbanhang.exceptions.Violations;
import Camera24h.com.vn.Appbanhang.repository.AclUserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****QUY TRÌNH 3 - XÂY DỰNG LỚP XỬ LÝ LOGIC/LUẬN LÝ SERVICE
 * => đây là lớp luận lý logic tức là viết code luận lý đẻ thao tác đến CRUD trực tiếp trong bằng code java
 * => bằng cách là viết code luận lý truy vấn và gọi đến Repository để sử dụng đến các function
 * đã đc xây dựng sẵn trong class CrudRepository của spring boot đã xd sẵn hé (CrudRepository đc khai báo
 * trong qui trình 2 là Repository)
 * ===> thì trong class này ta đi code luận lý và gọi đến các method crud đã đc xd trng class repository
*/

//@Service đánh dấu là class Service trong spring boot
@Service
public class AclUserService {

    /* @Autowired nó tự động tìm kiếm và tiêm (inject) các bean phụ thuộc vào class AclUserService
    * hiểu rõ hơn thì vơi việc thêm thuộc tính annotation này
    *  + Khi Spring tìm thấy bean phù hợp (bean AclUserRepository), nó sẽ tiêm (inject) bean này vào
    * trường aclUserRepo của lớp AclUserService.
    *  => Kết quả: Bạn không cần tự khởi tạo hoặc quản lý instance của AclUserRepository.
    *  => Nó đảm bảo rằng tất cả các phụ thuộc được cung cấp bởi Spring Container, giúp giảm sự phụ
    * thuộc cứng và tăng tính linh hoạt, dễ bảo trì của mã nguồn.
    * ====> tới đây thì chỉ cần gọi đén method thực thi crud motj cách dễ dàng là xong:
    *   + vd:
    *
    *              aclUserRepo.create/findALl/delete/edit,,
    *  */
    @Autowired
    private AclUserRepository aclUserRepo;  //ánh xạ Repository của AclUser qua hé


    /*********************************************=I - GET***********************************************************/
    /***01 -0 (lấy dữ lieu mà không phân trang) - xây dựng method getAll để render dữ liệu ra
     * ----> theo chuẩn EntityResponse****/
    public ResponseEntity<Map<String, Object>> getAllUsers(){
        //a - khởi tạo biến response lưu trữ kết quả trả về
        /**giai thich code
         * Map: Interjace tong java giup luu tru value theo cap key-value
         * Object: la kieu dl cho phép giá trị trong Map có thể là bất kỳ loại dữ liệu nào, từ chuỗi, số, danh sách..
         * HashMap:
         *     + Đây là một lớp thực thi của interface Map
         *     + lưu trữ các cặp key-value trong một bảng băm (hash table),
         *     giúp việc truy xuất dữ liệu nhanh chóng dựa trên key.
         *
         * <>: Đây là cú pháp sử dụng "diamond operator" trong Java.
         * Nó cho phép trình biên dịch suy diễn kiểu dữ liệu, nên bạn không cần phải
         * lặp lại kiểu trong phần khởi tạo (HashMap<String, Object>).
         * ==>  tom lai:
         *              + muc dich doan nay la Tạo một đối tượng HashMap rỗng.
         *              + Cho phép bạn thêm dữ liệu vào Map này dưới dạng cặp key-value
         *              va luu tru no trong bien respone
         * **/
        Map<String, Object> response = new HashMap();

        //b - Yêu câu repository lấy dữ liệu -> gọi đén Repository.mehthod trong crudRepository
        List<AclUser> lsUsers = (List<AclUser>) aclUserRepo.findAll();

        //c - trả về kết quả cho người dùng -> trả theo chuẩn restFullApi
        response.put("data", lsUsers);
        response.put("statuscode", 200);
        response.put("msg", "get dữ liệu thành công");

        return new ResponseEntity(response, HttpStatus.OK);
    }


    /*-------------------------------------------------------------------------------------------*/
    /***01 -2 (lấy dữ lieu có phân trang) - xây dựng method getAll để render dữ liệu ra
     * ----> theo chuẩn EntityResponse****/
    public ResponseEntity<Map<String, Object>> getAllUserPagination(int pageNumber, int pageSize, String sortBy ){
        //a - khởi tạo biến response lưu trữ kết quả trả về
        Map<String, Object> response = new HashMap();

        //b - Yêu câu repository lấy dữ liệu -> gọi đén Repository.mehthod trong crudRepository
        /*
         Pageable: là một giao diện trong Spring Data được sử dụng để hỗ trợ phân trang (pagination)
         và sắp xếp dữ liệu (sorting) trong các truy vấn cơ sở dữ liệu
          + pageNumber: trang so may muon xem
          + pageSize: so luong mau tin trong moi trang muon xem
          + sortBy: sap xep theo cot nao: id, name...
        * */
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<AclUser> pageResult =  aclUserRepo.findAll(pageable);
        if(pageResult.hasContent()){
            //trả về kết quả cho người dùng -> trả theo chuẩn restFullApi
            response.put("data", pageResult.getContent());
            response.put("statuscode", 200);
            response.put("msg", "get dữ liệu thành công");

            response.put("curentPage", pageNumber );
            response.put("isFirst", pageResult.isFirst() );
            response.put("isLast", pageResult.isLast());
            response.put("hasNext", pageResult.hasNext() );
            response.put("hasPrevious", pageResult.hasPrevious() );
            response.put("totalPage", pageResult.getTotalPages() );
            response.put("totalElement", pageResult.getTotalElements());

            return new ResponseEntity(response, HttpStatus.OK);
        }else{
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "get dữ liệu không thành công");

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }





    /*********************************************II - POST********************************************************/
    /***02 - xây dựng method create  liệu từ DTO user - theo chuẩn EntityResponse****/
    public ResponseEntity<Map<String, Object>> createUser(AclUserCreateRequestDTO objCreate){
        //a - khởi tạo biến response lưu trữ kết quả trả về
        Map<String, Object> response = new HashMap<>();

        /****************xử lý service validations*************************/
        /*** xử lý exception kiểm tra ràng buộc(trc khi nhập create thì can kiem tra coi nó đúng vs y/c khong?)**/
        //a-1: kiểm tra đk tránh trùng tên chức vụ admin username
        ValidationErrorRespone responeError = new ValidationErrorRespone();
        if(objCreate.getUsername().equalsIgnoreCase("Admin") || objCreate.getUsername().equalsIgnoreCase("quan tri vien")){
            responeError.getViolations().add(new Violations("username", " không đượt đặt trùng tền với tên admin"));
        }
        //a-2: xu lý password vứi service validation -. theo bieu thuc chinh quy regex
        String regExpn = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(objCreate.getPassword());
        // tao bien kiem tra mk co du manh nhu regex chua
        boolean isPasswordIstrong = matcher.matches();
        if(isPasswordIstrong == false){
            responeError.getViolations().add(new Violations("password", "mt khau phai co ky u in hoa in thuong (a-Z) va it nhat mot ky tu db"));
        }
        //.. co the them nhieu luan ly logic code cacs rang buoc kiem tra he....

        /****************end - xử lý service validations*************************/

        /*neu nguoi dung khong vi pham bat ke service validation nao thi cho luu <=> thi bao loi nhu logic tren*/
        if(responeError.getViolations().size() ==0){
            // b- Khởi tọa UserEntity
            AclUser newEntity = new AclUser();
            newEntity.setUsername(objCreate.getUsername());
            newEntity.setPassword(objCreate.getPassword());
            newEntity.setFirst_name(objCreate.getFirst_name());
            newEntity.setLast_name(objCreate.getLast_name());
            newEntity.setEmail(objCreate.getEmail());
            newEntity.setPhone(objCreate.getPhone());
            newEntity.setAddress1(objCreate.getAddress1());
            newEntity.setStatus((long) 1); //set mac dinh

            // c- yeu cau repository lua lai khoi tao tren
            /****************xử lý service validations phía sql*************************/
            List<AclUser> listUsers = aclUserRepo.findByUsername(objCreate.getUsername());
            //thực hien kiem tra ds data trong mysql co trung ten username hay khong
            if(listUsers.size() > 0){
                //nem loi exception xu ly service validation tao o file exception GlobalExceptionHandler
                throw new ConstraintViolationException("Error bi trung ten nay da ton tai trong usernme",null);
            }else{
                AclUser createEntity =  aclUserRepo.save(newEntity);

                //d.1 - trả về kết quả cho người dùng theo chuẩn restfullAPi
                response.put("data", createEntity);
                response.put("statuscode", 201);
                response.put("msg", "crete thành công");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            /****************end -xử lý service validations phía sql********************/

        }else{
            //neu vi pham validation thi thong bao loi bat nhap lai
            //d.2 - trả ve thong bao loi
            response.put("data", responeError);
            response.put("statuscode", 501);
            response.put("msg", "dữ liệu chưa đạt yêu cầu cần xem lại");

            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }



    /*********************************************III - DELETE********************************************************/
    /***03 - xây dựng method Delete liệu từ DTO user - theo chuẩn EntityResponse****/
    public  ResponseEntity<Map<String, Object>> deleteUser(Integer id){
        /**a - khởi tạo biến response lưu trữ kết quả trả về**/
        Map<String, Object> response = new HashMap();

         //kiểm trang ràng buột validation service

        /**b - nhờ reponsitory goij đê method tìm đến id cần xóa**/
        /*
        * Optional:
            + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
            + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
            + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị có thể là null.
        * */
        Optional<AclUser> optFound =  aclUserRepo.findById(id);
        if(optFound.isPresent()){
            //nếu ton tai id can tim thi lay no ra ghi nhan vao enity
            AclUser delEnity = optFound.get();
            //nho repository xoa
            aclUserRepo.delete((delEnity));

            //tra ve ket qua nguoi dung
            response.put("data", null);
            response.put("statuscode", 200);
            response.put("msg", "delete thành công");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            //tra ve ket qua nguoi dung
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "tài khoản xóa không tồn tại");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }







    /*********************************************IV - PUT********************************************************/
    /***04 - xây dựng method update liệu từ DTO update user - theo chuẩn EntityResponse****/
    public ResponseEntity<Map<String, Object>> upateUser(Integer id, AclUserUpdateRequestDTO objEdit){
        //a - khởi tạo biến response lưu trữ kết quả trả về
        Map<String, Object> response = new HashMap<>();

        /*b - nhờ repository tìm kiếm enity muốn sữa thông qua key id*/
        Optional<AclUser> optFound =  aclUserRepo.findById(id);


        //kiem tra trc coi thực thể đó có tồn tại khong thì mới tính  tới việc thuc thi update
        if (optFound.isPresent()) {
           //nhận đc id vùa tìm kiếm hé
            AclUser enityEdit = optFound.get();

            // Kiểm tra và cập nhật các trường String (có thể là null hoặc empty)
            if (objEdit.getPassword() != null && !objEdit.getPassword().isEmpty()) {
                enityEdit.setPassword(objEdit.getPassword());
            }
            if (objEdit.getLast_name() != null && !objEdit.getLast_name().isEmpty()) {
                enityEdit.setLast_name(objEdit.getLast_name());
            }
            if (objEdit.getFirst_name() != null && !objEdit.getFirst_name().isEmpty()) {
                enityEdit.setFirst_name(objEdit.getFirst_name());
            }
            if (objEdit.getEmail() != null && !objEdit.getEmail().isEmpty()) {
                enityEdit.setEmail(objEdit.getEmail());
            }
            if (objEdit.getAvatar() != null && !objEdit.getAvatar().isEmpty()) {
                enityEdit.setAvatar(objEdit.getAvatar());
            }
            if (objEdit.getJob_title() != null && !objEdit.getJob_title().isEmpty()) {
                enityEdit.setJob_title(objEdit.getJob_title());
            }
            if (objEdit.getDepartment() != null && !objEdit.getDepartment().isEmpty()) {
                enityEdit.setDepartment(objEdit.getDepartment());
            }
            if (objEdit.getPhone() != null && !objEdit.getPhone().isEmpty()) {
                enityEdit.setPhone(objEdit.getPhone());
            }
            if (objEdit.getAddress1() != null && !objEdit.getAddress1().isEmpty()) {
                enityEdit.setAddress1(objEdit.getAddress1());
            }
            if (objEdit.getAddress2() != null && !objEdit.getAddress2().isEmpty()) {
                enityEdit.setAddress2(objEdit.getAddress2());
            }
            if (objEdit.getCity() != null && !objEdit.getCity().isEmpty()) {
                enityEdit.setCity(objEdit.getCity());
            }
            if (objEdit.getState() != null && !objEdit.getState().isEmpty()) {
                enityEdit.setState(objEdit.getState());
            }
            if (objEdit.getPostal_code() != null && !objEdit.getPostal_code().isEmpty()) {
                enityEdit.setPostal_code(objEdit.getPostal_code());
            }
            if (objEdit.getCountry() != null && !objEdit.getCountry().isEmpty()) {
                enityEdit.setCountry(objEdit.getCountry());
            }
            if (objEdit.getRemember_token() != null && !objEdit.getRemember_token().isEmpty()) {
                enityEdit.setRemember_token(objEdit.getRemember_token());
            }
            if (objEdit.getActive_code() != null && !objEdit.getActive_code().isEmpty()) {
                enityEdit.setActive_code(objEdit.getActive_code());
            }

            // Kiểm tra và cập nhật các trường Integer và Long
            if (objEdit.getManager_id() != null) {
                enityEdit.setManager_id(objEdit.getManager_id());
            }
            if (objEdit.getStatus() != null) {
                enityEdit.setStatus(objEdit.getStatus());
            }


            //yêu cau repository update
            aclUserRepo.save(enityEdit);

            // trả về kết quả thông báo thành công
            response.put("data", enityEdit);
            response.put("statuscode", 200);
            response.put("msg", "update thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "không tìm thấy dữ liệu");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}//end clas AclUserService

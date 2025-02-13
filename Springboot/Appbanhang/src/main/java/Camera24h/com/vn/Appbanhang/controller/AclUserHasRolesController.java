package Camera24h.com.vn.Appbanhang.controller;

import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesBatchCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.AclUserHasRolesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/acl-userhasroles")
@Tag(name = "AclUserHasRolesController", description = "API phân vai trò cho user")
public class AclUserHasRolesController {

    //ghi log nhat ky loi
    Logger logger = LoggerFactory.getLogger(AclUserHasRolesController.class);

    //goi khoi tao bien service
    @Autowired
    private AclUserHasRolesService aclUserHasRolesService;

    /*1 - get*/
    @GetMapping
    public ResponseEntity<Map<String, Object>> index(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                     @RequestParam(defaultValue = "3") Integer pageSize,
                                                     @RequestParam(defaultValue = "id")  String sortBy){
        return aclUserHasRolesService.getAllAclUserHasRoles_Pagination(pageNumber, pageSize, sortBy);
    }


    /*02- put create*/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid AclUserHasRolesCreateRequestDTO res) {
        try {
            //ghi lo nhat ky
            logger.info("ghi log nhat ky lo cho trang acl_user_has_roles");
            return aclUserHasRolesService.createUserHasRoles(res);
        } catch (Exception ex) {
            logger.error("error", ex);

            //khoi tao bien luu response tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statusCode", 500);
            response.put("msg", "dữ liệu đầu vào chưa đúng yêu cau nhập lại");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*02- put create*/
    @PostMapping("/batch-create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid AclUserHasRolesBatchCreateRequestDTO res) {
        try {
            //ghi lo nhat ky
            logger.info("ghi log nhat ky lo cho trang acl_user_has_roles");
            return aclUserHasRolesService.batchCreateUserHasRoles(res);
        } catch (Exception ex) {
            logger.error("error", ex);

            //khoi tao bien luu response tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statusCode", 501);
            response.put("msg", "dữ liệu đầu vào chưa đúng yêu cau nhập lại");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*03 - delete */
    /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return aclUserHasRolesService.deleteUserHasRoles(id);
    }


    /*04 - put update method */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody AclUserHasRolesUpdateRequestDTO res){
      return aclUserHasRolesService.updateUserHasRoles(id, res);
    }


}//end class

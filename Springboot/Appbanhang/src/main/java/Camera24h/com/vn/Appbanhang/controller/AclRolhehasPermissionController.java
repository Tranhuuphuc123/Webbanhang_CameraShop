package Camera24h.com.vn.Appbanhang.controller;

import Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO.AclRolehasPermissionCreateDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO.AclRolehasPermissionUpdateDTO;
import Camera24h.com.vn.Appbanhang.service.AclRolehasPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/acl-rolehaspermission")
@Tag(name = "AclRolhehasPermissionController", description = "APi cho trang AclRolhehasPermission")
public class AclRolhehasPermissionController {

    //ghi log nhat ky loi
    Logger logger = LoggerFactory.getLogger(AclPermisionController.class);

    //khoi tao service
    @Autowired
    private AclRolehasPermissionService aclRolehasPermissionService;


    /******1- get all phan trng******/
    @GetMapping
     public ResponseEntity<Map<String, Object>> index(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                      @RequestParam(defaultValue = "3") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy){
        return aclRolehasPermissionService.getAllRolehasPermissionPagination(pageNumber, pageSize, sortBy);
    }



    /********2 - create*************/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid AclRolehasPermissionCreateDTO res){
        try{
            return aclRolehasPermissionService.batchCreateRolehasPermission(res);
        }catch (Exception ex){
            logger.error("error", ex);

            //khoi tao bien luu response tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statusCode", 500);
            response.put("msg", "dữ liệu đầu vào chưa đúng yêu cau nhập lại");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*********2 - delete************/
    /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Integer id){
        return aclRolehasPermissionService.deleteRolehasPermission(id);
    }



    /**********4 - update***********/
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody AclRolehasPermissionUpdateDTO res){
        return aclRolehasPermissionService.updateRolehasPermission(id, res);
    }

}//end class

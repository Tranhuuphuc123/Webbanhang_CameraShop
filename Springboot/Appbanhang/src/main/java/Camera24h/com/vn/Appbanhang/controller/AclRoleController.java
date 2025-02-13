package Camera24h.com.vn.Appbanhang.controller;
//trung tam dieu phoi mapping url vaf thuc thi service

import Camera24h.com.vn.Appbanhang.DTO.AclRolesDTO.AclRoleCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclRolesDTO.AclRoleUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.AclRole;
import Camera24h.com.vn.Appbanhang.service.AclRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/acl-roles")
public class AclRoleController {
    /*khởi tạo trỏ đến service
     *  @Autowired giúp ánh xạ bean phù hơp và inject các phụ thuộc từ bean đó vào
     * một cách auto
     * */
    @Autowired
    private AclRoleService aclRoleService;


    /*01-get co xu ly phan trang*/
    @GetMapping
    public ResponseEntity<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                                        @RequestParam(defaultValue = "id")  String sortBy){
        //goi AclRoleService xu ly
        return aclRoleService.getAllRolePagination(pageNumber, pageSize, sortBy);
    }



    /*02-put(diu poi mapping va service thuc thi method create)
    *>>@RequestBody/: giup chuyen doi cac value tuw client dua vao <=> thanh object dua
    vao trong cac method xu ly cuar service de create vao csdl mysql
    *>>@Valid/: kiem tra tinh hop le cua cac validation dto
    */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create (@RequestBody @Valid AclRoleCreateRequestDTO res){
        try{
            //goi den AclRoleService create csdl
            return aclRoleService.createRoles(res);
        }catch (Exception ex){
            //khoi tao bien luu response tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statuscode", 501);
            response.put("msg", "dữ liệu đã tồn tại tên username trong csdl, yêu cau nhập lại");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*03 - delete*/
    /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return aclRoleService.deleteRole(id);    }





    /*04 - pute update*/
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody AclRoleUpdateRequestDTO res){

        return aclRoleService.updateRole(id, res);

    }


}

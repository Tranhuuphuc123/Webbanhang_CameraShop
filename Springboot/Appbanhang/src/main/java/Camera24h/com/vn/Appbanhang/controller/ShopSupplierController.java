package Camera24h.com.vn.Appbanhang.controller;

import Camera24h.com.vn.Appbanhang.DTO.ShopCategoryDTO.ShopCategoryCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopCategoryDTO.ShopCategoryUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.ShopCategoryService;
import Camera24h.com.vn.Appbanhang.service.ShopSupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/shop-suppliers")
@Tag(name = "ShopSupplierController", description = " api cua phan ShopSuppliers")
public class ShopSupplierController {

    @Autowired
    private ShopSupplierService shopSupplierService;

    /************1- get*******************/
    /*  @CrossOrigin(origins = "http://localhost:3000": cho phép localhost 8080 chấp nhận
    chay localhost 3000 khi localhost 8080 đang chay
    * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy){
        //gi serrvice xu ly getall co phan trang
        return shopSupplierService.getAllPagination(pageNumber,pageSize,sortBy);
    }


    /************2 - create******************/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create (@RequestBody @Valid ShopSupplierCreateRequestDTO res){
        try {
            return shopSupplierService.createrShopSupplier(res);
        }catch (Exception ex){
            //khoi tao bien luu tru tra ve
            Map<String, Object> response = new HashMap<>();
            response.put("data", ex.getMessage());
            response.put("statuscode", 501);
            response.put("msg", "du lieu khong thanh vui long xem lai cac truong value");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /************3- delete******************/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return shopSupplierService.deleteShopSupplier(id);
    }



    /****************4 - update***************************/
    @PutMapping("/update/{id}")
     public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody ShopSupplierUpdateRequestDTO res){
         return shopSupplierService.updateShopCategory(id, res);
     }



}//end class

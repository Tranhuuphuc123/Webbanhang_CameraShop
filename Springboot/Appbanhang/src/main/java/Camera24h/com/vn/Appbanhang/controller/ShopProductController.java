package Camera24h.com.vn.Appbanhang.controller;

import Camera24h.com.vn.Appbanhang.DTO.ShopProductDTO.ShopProductCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopProductDTO.ShopProductUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.ShopProductService;
import Camera24h.com.vn.Appbanhang.service.ShopSupplierService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/shop-products")
@Tag(name = "ShopProductController", description = " api cua phan ShopProduct")
public class ShopProductController {

    @Autowired
    private ShopProductService shopProductService;

    /************1- get*******************/
    /*  @CrossOrigin(origins = "http://localhost:3000": cho phép localhost 8080 chấp nhận
    chay localhost 3000 khi localhost 8080 đang chay
    * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestParam(required = false) String search){
        //gi serrvice xu ly getall co phan trang
        return shopProductService.getAllPagination(pageNumber,pageSize,sortBy, search);
    }

    /***************** 1-1api getById *******************/
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id){
        //yeu cau service tra  ve id
        return shopProductService.getById(id);
    }



    /************2 - create******************/
    /**
     a/ goi multipartFile xu li lay file image va luu img ca ten lan ruot vao folder da thiet lap
     b/ String jsonData: anh da duoc xu ly voi MultipartFile, thi cac value con lai van gui o dang
     json -> parse vao DTO thong qua lop ObjectMapper
     **/
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create (@RequestParam("file") MultipartFile file,
                                                       @RequestParam("data") String jsonData){
        //gọi class ObjectMapper đẻ mapp json (param: data ) gửi lên -> parse(chuyển) json đó thành value trong ShopCategoryCreateRequestDTO
        ObjectMapper objMapper = new ObjectMapper();

        //goi khoi tao lop dto của category
        ShopProductCreateRequestDTO objDTO = null;

        //tien hanh cho DTO doc va ghi nhan value tu json gui len da dc map thong qua lop ObjectMapper
        try {
            objDTO = objMapper.readValue(jsonData, ShopProductCreateRequestDTO.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return shopProductService.createrShopProduct(objDTO, file);
    }



    /************3- delete******************/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-*/
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return shopProductService.deleteShopProduct(id);
    }

    /*****3- deleteAll nhiều id****/
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/batch-delete")
    public ResponseEntity<Map<String, Object>> deleteMultiple(@RequestBody List<Integer> ids){
        return shopProductService.deleteShopProductMultiple(ids);
    }

    /****************4 - update***************************/
    //@RequestParam(value = "file", required = false: chấp nhận img null khong bat loi
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update/{id}")
     public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id,
                                                       @RequestParam(value = "file", required = false) MultipartFile file,
                                                       @RequestParam("data") String jsonData){
        /*goi class ObjectMapper de mapp json(param: data) gui len -> parse(chuyen) json do thanh value va xu ly
        * luu torng shopProducts*/
        ObjectMapper objMapper = new ObjectMapper();

        //goi khoi  tao lop dto cua shopProduct
        ShopProductUpdateRequestDTO objDTO = null;

        // tien hanh cho DTO doc va ghi nhan value tu json gui len da dc map thong qua lop ObjectMapper
        try {
            objDTO = objMapper.readValue(jsonData, ShopProductUpdateRequestDTO.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

         return shopProductService.updateShopProduct(id, objDTO, file);
     }



}//end class

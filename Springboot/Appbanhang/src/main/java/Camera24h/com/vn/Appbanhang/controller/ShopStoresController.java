package Camera24h.com.vn.Appbanhang.controller;


import Camera24h.com.vn.Appbanhang.DTO.ShopStoresDTO.ShopStoresCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopStoresDTO.ShopStoresUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.ShopStoresService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/shop-stores")
@Tag(name = "ShopStoresController", description = "api cua phan Shop Stores")
public class ShopStoresController {

    @Autowired
    ShopStoresService shopStoresService;

    /************1- get*******************/
      /*  @CrossOrigin(origins = "http://localhost:3000": cho phép localhost 8080 chấp nhận
    chay localhost 3000 khi localhost 8080 đang chay
    * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Map<String,Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(defaultValue = "3") Integer pageSize,
                                                       @RequestParam(defaultValue = "id") String sortBy){
        //goi service xu ly getall co phan trang
        return shopStoresService.getAllPagination(pageNumber, pageSize, sortBy);
    }

    /************2 - create******************/
    /**
     a/ goi multipartFile xu li lay file image va luu img ca ten lan ruot vao folder da thiet lap
     b/ String jsonData: anh da duoc xu ly voi MultipartFile, thi cac value con lai van gui o dang
     json -> parse vao DTO thong qua lop ObjpubPaectMapper
     **/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>>  create(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("data") String jsonData){
        //goi class ObjectMapper để map json (param: data) gửi lên -> parse(chuyển) json đó thành value trong ShopProductImageCreateDTO
        ObjectMapper objMapper = new ObjectMapper();

        //goi khoi tao lop dto cua shop stores
        ShopStoresCreateRequestDTO objDTO = null;

        //tien hanh cho DTO doc va ghi nhan value tu json gui len da dc map thong qua lop ObjectMapper
        try{
            objDTO = objMapper.readValue(jsonData, ShopStoresCreateRequestDTO.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return shopStoresService.createShopStores(objDTO, file);
    }

    /************3- delete******************/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>>  delete(@PathVariable("id") Integer id){
        return shopStoresService.deleteShopStores(id);
    }

    /****************4 - update***************************/
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id,
                                                      @RequestParam("file") MultipartFile file,
                                                      @RequestParam("data") String jsonData){
        //gọi class ObjectMapper đẻ mapp json (param: data ) gửi lên -> parse(chuyển) json đó thành value trong ShopCategoryCreateRequestDTO
        ObjectMapper objMapper = new ObjectMapper();

        //goi khoi tao lo dto cua product iamges
        ShopStoresUpdateRequestDTO objDTO = null;

        //tien hanh cho DTO doc va ghi nhan value tu json gui len da dc map thong qua lop ObjectMapper
        try{
            objDTO = objMapper.readValue(jsonData, ShopStoresUpdateRequestDTO.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return shopStoresService.updateShopStores(id, objDTO, file);
    }
}//end class

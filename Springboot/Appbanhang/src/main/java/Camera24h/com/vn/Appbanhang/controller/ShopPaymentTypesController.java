package Camera24h.com.vn.Appbanhang.controller;


import Camera24h.com.vn.Appbanhang.DTO.ShopPaymentTypesDTO.ShopPaymentTypeCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopPaymentTypesDTO.ShopPaymentTypeUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.ShopPaymentTypesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/v1/shop-payment-types")
@Tag(name = "ShopPaymentTypesController", description = "api cua phan ShopPaymentTypes")
public class ShopPaymentTypesController {

    @Autowired
    private ShopPaymentTypesService shopPaymentTypesService;

    /************1- get*******************/
      /*  @CrossOrigin(origins = "http://localhost:3000": cho phép localhost 8080 chấp nhận
    chay localhost 3000 khi localhost 8080 đang chay
    * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy){
        //gi service xu ly getall co phan trang
        return shopPaymentTypesService.getAllPagination(pageNumber, pageSize, sortBy);
    }

    /************2 - create******************/
    /**
     a/ goi multipartFile xu li lay file image va luu img ca ten lan ruot vao folder da thiet lap
     b/ String jsonData: anh da duoc xu ly voi MultipartFile, thi cac value con lai van gui o dang
     json -> parse vao DTO thong qua lop ObjpubPaectMapper
     **/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestParam("file")MultipartFile file,
                                                      @RequestParam("data") String jsonData){
        //goi class ObjectMapper để map (param: data) gửi lên -> parse(chuyển) json thành value trong SHopPaymentTypes
        ObjectMapper objMapper = new ObjectMapper();

        //goi khởi tạo lớp dto của  images
        ShopPaymentTypeCreateRequestDTO objDTO = null;

        //tiến hành cho DTO đọc và ghi nhận value từ json giử lên đa đc map thông qua lớp ObjectMapper
        try{
            objDTO = objMapper.readValue(jsonData, ShopPaymentTypeCreateRequestDTO.class);
        }catch(Exception e){
            e.printStackTrace();
        }

        return shopPaymentTypesService.createShopPaymentType(objDTO, file);
    }

    /************3- delete******************/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return shopPaymentTypesService.deleteShopPaymentType(id);
    }

    /****************4 - update***************************/
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody ShopPaymentTypeUpdateRequestDTO objUpdate,
                                                      @RequestParam("file") MultipartFile file,
                                                      @RequestParam("data") String jsonData){
        //gọi class ObjectMapper đẻ mapp json (param: data ) gửi lên -> parse(chuyển) json đó thành value trong ShopCategoryCreateRequestDTO
        ObjectMapper objMapper = new ObjectMapper();

        //gọi khởi tạo lớp DTO của payment
        ShopPaymentTypeUpdateRequestDTO objDTO = null;

        //tiến hành cho DTO đọc và ghi nhận value từ json gọi lên đã đc map thông qua lớp ObjectMapper
        try {
            objDTO = objMapper.readValue(jsonData, ShopPaymentTypeUpdateRequestDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return shopPaymentTypesService.updateShopPaymentType(id, objDTO, file);
    }
}

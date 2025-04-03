package Camera24h.com.vn.Appbanhang.controller;


import Camera24h.com.vn.Appbanhang.DTO.ShopProductsReviewsDTO.ShopProductsReviewsCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopProductsReviewsDTO.ShopProductsReviewsUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.service.ShopProductsReviewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/shop-products-reviews")
@Tag(name = "ShopProductsReviews", description = "api cua phan ShopProductsReviews")
public class ShopProductsReviewsController {

    @Autowired
    private ShopProductsReviewsService shopProductsReviewsService;

    /************1- get*******************/
      /*  @CrossOrigin(origins = "http://localhost:3000": cho phép localhost 8080 chấp nhận
    chay localhost 3000 khi localhost 8080 đang chay
    * */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                                        @RequestParam(required = false) String sortBy){
        //gi service xu ly getall co phan trang
        return shopProductsReviewsService.getAllPagination(pageNumber,pageSize,sortBy);
    }

    /************2 - create******************/
    /**
     a/ goi multipartFile xu li lay file image va luu img ca ten lan ruot vao folder da thiet lap
     b/ String jsonData: anh da duoc xu ly voi MultipartFile, thi cac value con lai van gui o dang
     json -> parse vao DTO thong qua lop ObjpubPaectMapper
     **/
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestParam("date") String jsonData){
        //gọi class ObjectMapper đẻ mapp json (param: data ) gửi lên -> parse(chuyển) json đó thành value trong ShopCategoryCreateRequestDTO
        ObjectMapper mapper = new ObjectMapper();

        //goi khoi tao lop dto cua product reviews
        ShopProductsReviewsCreateRequestDTO objDTO = null;

        //tien hanh cho DTO doc va ghi nhan value tu json gui len da dc map thong qua lop ObjectMapper
        try{
            objDTO = mapper.readValue(jsonData,ShopProductsReviewsCreateRequestDTO.class);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return shopProductsReviewsService.createrShopProductsReviews(objDTO);
    }


    /************3- delete******************/
     /* annotation @PathVariable được sử dụng để trích xuất giá trị từ URL và ánh xạ nó tới tham số của phương thức controller.
      Đây là cách mà giá trị của {id} trong đường dẫn (path) được truyền đến tham số id của phương thức delete. */
    //@DeleteMapping annotation dung danh dau la chuc nang delete trong spring boot theo id-*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        return shopProductsReviewsService.deleteShopProductsReviews(id);
    }

    /****************4 - update***************************/
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody ShopProductsReviewsUpdateRequestDTO res){

        return shopProductsReviewsService.updateShopProductsReviews(id, res);
    }
}

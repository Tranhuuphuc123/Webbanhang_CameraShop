package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.ShopOrderDetailsDTO.ShopOrderDetailsCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopOrderDetailsDTO.ShopOrderDetailsUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopOrderDetails;
import Camera24h.com.vn.Appbanhang.repository.ShopOrderDetailsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ShopOrderDetailsService {

    //khoi tao repository cua shop_product_images o lop service
    //@Autowired: annotation nay giup tim kiem va ịneject cac phu thuoc tu entity qua
   @Autowired
    private ShopOrderDetailsRespository shopOrderDetailsRepo;

    /******I - get co phan trang*******/
   public ResponseEntity<Map<String, Object>> getAllPagination(int pageNumber, int pageSize, String sortBy) {
       //khoi tao bien luu tru tra ve
       Map<String, Object> response = new HashMap<>();

       //yeu cau repo thuc thi get all co phan trang voi pageable
       Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
       Page<ShopOrderDetails> pageResult = shopOrderDetailsRepo.findAll(pageable);
       if(pageResult.hasContent()) {
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
       }else {
           response.put("data", null);
           response.put("statuscode", 404);
           response.put("msg", "get dữ liệu không thành công");

           return new ResponseEntity(response, HttpStatus.NOT_FOUND);
       }
   }


    /**************2 - create *********************/
    /*MultipartFile: Là một interface trong Spring Framework được sử dụng để xử lý các tệp (files) được upload thông qua
    HTTP requests sử dụng multipart/form-data.*/
    public ResponseEntity<Map<String,Object>> createShopOrderDetails(ShopOrderDetailsCreateRequestDTO objCreate) {
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo su ly create
        ShopOrderDetails shopEntity = new ShopOrderDetails();
        shopEntity.setOrderId(objCreate.getOrderId());
        shopEntity.setProductId(objCreate.getProductId());
        shopEntity.setQuantity(objCreate.getQuantity());
        shopEntity.setUnitPrice(objCreate.getUnitPrice());
        shopEntity.setDiscount(objCreate.getDiscount());
        shopEntity.setOrderDetailStatus(objCreate.getOrderDetailStatus());
        shopEntity.setDateAllocated(objCreate.getDateAllocated());

        //yeu cau repository luu lai value vua khoi tao va ghi nhan value tra ve
        ShopOrderDetails createEntity = shopOrderDetailsRepo.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopOrderDetails(Integer id) {
        //khoi tao bien luu kq tra ve
        Map<String, Object> response = new HashMap<>();

        //goi repo xy ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopOrderDetails> optFound = shopOrderDetailsRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tim duoc
            ShopOrderDetails delId = optFound.get();

            //dung repo xoa record trong database
            shopOrderDetailsRepo.delete(delId);

            //tra ve  thong bao thanh cong
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

    /*********************4 - update*************************/
        /*MultipartFile: Là một interface trong Spring Framework được sử dụng để xử lý các tệp (files) được upload thông qua
        HTTP requests sử dụng multipart/form-data.*/
    public ResponseEntity<Map<String, Object>> updateShopOrderDetails(Integer id, ShopOrderDetailsUpdateRequestDTO objUpdate) {
        //khoi tao bien luu tr
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly update tren id tuong ung
        Optional<ShopOrderDetails> optFound = shopOrderDetailsRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim duoc cho entity
            ShopOrderDetails entityUpdate = optFound.get();
            //goi repo xu ly update va kiem tra value co null hay khong
            if(objUpdate.getOrderId() >=0){
                entityUpdate.setOrderId(objUpdate.getOrderId());
            }
            if(objUpdate.getProductId() >=0){
                entityUpdate.setProductId(objUpdate.getProductId());
            }
            if(objUpdate.getQuantity() >=0){
                entityUpdate.setQuantity(objUpdate.getQuantity());
            }
            if(objUpdate.getUnitPrice() >=0){
                entityUpdate.setUnitPrice(objUpdate.getUnitPrice());
            }
            if(objUpdate.getDiscount() >=0){
                entityUpdate.setDiscount(objUpdate.getDiscount());
            }
            if(objUpdate.getOrderDetailStatus() !=null && !objUpdate.getOrderDetailStatus().isEmpty()){
                entityUpdate.setOrderDetailStatus(objUpdate.getOrderDetailStatus());
            }
            if(objUpdate.getDateAllocated() !=null){
                entityUpdate.setDateAllocated(objUpdate.getDateAllocated());
            }

            //repo xu ly update
            shopOrderDetailsRepo.save(entityUpdate);

            // trả về kết quả thông báo thành công
            response.put("data", entityUpdate);
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
}// end class

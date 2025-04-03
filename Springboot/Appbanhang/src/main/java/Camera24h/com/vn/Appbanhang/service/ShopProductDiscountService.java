package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.ShopProductDiscountsDTO.ShopProductDiscountCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopProductDiscountsDTO.ShopProductDiscountUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopProductDiscounts;
import Camera24h.com.vn.Appbanhang.repository.ShopProductDiscountsRepository;
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
public class ShopProductDiscountService {

    @Autowired
    private ShopProductDiscountsRepository shopProductDiscountsRepo;

    /******I - get co phan trang*******/
    public ResponseEntity<Map<String, Object>> getAllPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thuc thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<ShopProductDiscounts> pageResult = shopProductDiscountsRepo.findAll(pageable);
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



    /**************2 - create *********************/
    /*MultipartFile: Là một interface trong Spring Framework được sử dụng để xử lý các tệp (files) được upload thông qua
    HTTP requests sử dụng multipart/form-data.*/
    public ResponseEntity<Map<String, Object>> createrShopProductDiscounts(ShopProductDiscountCreateRequestDTO objCreate){
        /**khoi tao bien luu tru**/
        Map<String, Object> response = new HashMap<>();

        /**yeu cau repo xu ly create***/
        ShopProductDiscounts shopEntity = new ShopProductDiscounts();
        shopEntity.setProductId(objCreate.getProductId());
        shopEntity.setDiscountName(objCreate.getDiscountName());
        shopEntity.setDiscountAmount(objCreate.getDiscountAmount());
        shopEntity.setFixed(objCreate.isFixed());

        /** yeu cau repository luu lai value vua khoi tao va ghi nhan value tra ve***/
        ShopProductDiscounts createEntity = shopProductDiscountsRepo.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopProductDiscounts(Integer id){
        // khoi tao bien luu tr]
        Map<String, Object> response = new HashMap<>();

        //goi repo xy ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopProductDiscounts> optFound = shopProductDiscountsRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tiem kiem dc
            ShopProductDiscounts delId = optFound.get();

           //dung repo xoa record trong database
            shopProductDiscountsRepo.delete(delId);

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
    public ResponseEntity<Map<String, Object>> updateShopProductDiscount(Integer id, ShopProductDiscountUpdateRequestDTO objUpdate) {

        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xy ly update tren id tuong ung
        Optional<ShopProductDiscounts> optFound = shopProductDiscountsRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vau tim kiem cho entity
            ShopProductDiscounts enityUpdate = optFound.get();
            //goi repo xy ly update va kiem tra value co null hay không
            if(objUpdate.getProductId() >=0 ){
                enityUpdate.setProductId(objUpdate.getProductId());
            }
            if(objUpdate.getDiscountName() !=null && !objUpdate.getDiscountName().isEmpty()){
                enityUpdate.setDiscountName(objUpdate.getDiscountName());
            }
            if(objUpdate.getDiscountAmount() >=0 ){
                enityUpdate.setDiscountAmount(objUpdate.getDiscountAmount());
            }
            if(objUpdate.isFixed() ){
                enityUpdate.setFixed(objUpdate.isFixed());
            }
            if(objUpdate.getStartDate() !=null){
                enityUpdate.setStartDate(objUpdate.getStartDate());
            }
            if(objUpdate.getEndDate() !=null){
                enityUpdate.setEndDate(objUpdate.getEndDate());
            }

            //repo xu ly update
            shopProductDiscountsRepo.save(enityUpdate);

            // trả về kết quả thông báo thành công
            response.put("data", enityUpdate);
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

package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.ShopProductsReviewsDTO.ShopProductsReviewsCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopProductsReviewsDTO.ShopProductsReviewsUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopProductsReviews;
import Camera24h.com.vn.Appbanhang.repository.ShopProductsReviewsRepository;
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
public class ShopProductsReviewsService {


    //khoi ta repository cua shop products reviews o lop service
    //@Autowired: annotation nay giup tim kiem va inject cac phu thuoc tu enity qua
    @Autowired
    private ShopProductsReviewsRepository shopProductsReviewsRepo;

    /******I - get co phan trang*******/
    public ResponseEntity<Map<String, Object>> getAllPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thuc thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortBy));
        Page<ShopProductsReviews> pageResult = shopProductsReviewsRepo.findAll(pageable);
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
    public ResponseEntity<Map<String, Object>> createrShopProductsReviews(ShopProductsReviewsCreateRequestDTO objCreate){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo xu ly create
        ShopProductsReviews shopEntity = new ShopProductsReviews();
        shopEntity.setProductId(objCreate.getProductId());
        shopEntity.setRating(objCreate.getRating());
        shopEntity.setComment(objCreate.getComment());

        /** yeu cau repository luu lai value vua khoi tao va ghi nhan value tra ve***/
        ShopProductsReviews createEntity = shopProductsReviewsRepo.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopProductsReviews(Integer id){
        //khoi tao bien uu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopProductsReviews> optFound = shopProductsReviewsRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tiem kiem dc
            ShopProductsReviews delId = optFound.get();

            //dung repo xoa record trong database
            shopProductsReviewsRepo.delete(delId);

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
    public ResponseEntity<Map<String, Object>> updateShopProductsReviews(Integer id, ShopProductsReviewsUpdateRequestDTO objUpdate){

        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly update treen id tuong ung
        Optional<ShopProductsReviews> optFound = shopProductsReviewsRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim kiem cho entiy
            ShopProductsReviews enityUpdate = optFound.get();
            //goi repo xu ly update va kiem tra value co null hay ko
            if(objUpdate.getProductId() >= 0){
                enityUpdate.setProductId(objUpdate.getProductId());
            }
            if(objUpdate.getRating() >= 0){
                enityUpdate.setRating(objUpdate.getRating());
            }
            if(objUpdate.getComment() != null && !objUpdate.getComment().isEmpty()){
                enityUpdate.setComment(objUpdate.getComment());
            }

            //repo xu ly update
            shopProductsReviewsRepo.save(enityUpdate);

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
}//end class

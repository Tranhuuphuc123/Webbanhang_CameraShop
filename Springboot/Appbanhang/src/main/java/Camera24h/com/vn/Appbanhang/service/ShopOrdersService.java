package Camera24h.com.vn.Appbanhang.service;
//class luan ly logic xu ly nghiep vu CRUD tuong tac truc tiep voi csdl MySQL

import Camera24h.com.vn.Appbanhang.DTO.ShopOrdersDTO.ShopOrdersCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopOrdersDTO.ShopOrdersUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopOrders;
import Camera24h.com.vn.Appbanhang.repository.ShopOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ShopOrdersService {

    //khoi tao repository cua shop_product_images o lop service
    //@Autowired: annotation nay giup tim kiem va ịneject cac phu thuoc tu entity qua
    @Autowired
    private ShopOrdersRepository shopOrdersRepo;

    //tao biến string lấy url cấu hình lưu file đã thiết lặp trong application.properties
    //annotation @Value: được sử dụng để gán giá trị cho một biến từ các nguồn như: application.properties/yaml
    @Value("uploads")
    private String uploadDir;

    /******* get co phân trang *******/
    public ResponseEntity<Map<String, Object>> getALLPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu ket qua trả về
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thực thi get all có phân trang vơi pageable
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortBy));
        Page<ShopOrders> pageResult = shopOrdersRepo.findAll(pageable);
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
    public ResponseEntity<Map<String, Object>> createShopOrder(ShopOrdersCreateRequestDTO objCreate) {
        //khởi tạo biến lưu trữ
        Map<String, Object> response = new HashMap<>();



        /**yeu cau repo xu ly create***/
        ShopOrders shopEntity = new ShopOrders();
        shopEntity.setOrderDate(objCreate.getOrderDate());
        shopEntity.setShippedDate(objCreate.getShippedDate());
        shopEntity.setShipName(objCreate.getShipName());
        shopEntity.setShipAddress1(objCreate.getShipAddress1());
        shopEntity.setShipAddress2(objCreate.getShipAddress2());
        shopEntity.setShipCity(objCreate.getShipCity());
        shopEntity.setShipState(objCreate.getShipState());
        shopEntity.setShipPostalCode(objCreate.getShipPostalCode());
        shopEntity.setShipCountry(objCreate.getShipCountry());
        shopEntity.setShippingFee(objCreate.getShippingFee());
        shopEntity.setPaymentType(objCreate.getPaymentType());
        shopEntity.setPaidDate(objCreate.getPaidDate());
        shopEntity.setOrderStatus(objCreate.getOrderStatus());
        shopEntity.setShopPaymentTypeId(objCreate.getShopPaymentTypeId());

        /** yeu cau repository luu lai value vua khoi tao va ghi nhan value tra ve***/
        ShopOrders createEntity = shopOrdersRepo.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>>  deleteShopOrder(Integer id) {
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopOrders> optFound = shopOrdersRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vau tim kiem dc
            ShopOrders delId = optFound.get();

            //dung repo xoa record trong database
            shopOrdersRepo.delete(delId);

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
    public ResponseEntity<Map<String, Object>> updateShopOrders(Integer id, ShopOrdersUpdateRequestDTO objUpdate) {
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly update treen id tuong ung
        Optional<ShopOrders> optFound = shopOrdersRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim kiem cho entity
            ShopOrders entityUpdate = optFound.get();
            //goi repo xu ly update va kiem tra value co null hay ko
            if(objUpdate.getOrderDate() != null){
                entityUpdate.setOrderDate(objUpdate.getOrderDate());
            }
            if(objUpdate.getShippedDate() != null){
                entityUpdate.setShippedDate(objUpdate.getShippedDate());
            }
            if(objUpdate.getShipName() != null && !objUpdate.getShipName().isEmpty()){
                entityUpdate.setShipName(objUpdate.getShipName());
            }
            if(objUpdate.getShipAddress1() != null && !objUpdate.getShipAddress1().isEmpty()){
                entityUpdate.setShipAddress1(objUpdate.getShipAddress1());
            }
            if(objUpdate.getShipAddress2() != null && !objUpdate.getShipAddress2().isEmpty()){
                entityUpdate.setShipAddress2(objUpdate.getShipAddress2());
            }
            if(objUpdate.getShipCity() != null && !objUpdate.getShipCity().isEmpty()){
                entityUpdate.setShipCity(objUpdate.getShipCity());
            }
            if(objUpdate.getShipState() != null && !objUpdate.getShipState().isEmpty()){
                entityUpdate.setShipState(objUpdate.getShipState());
            }
            if(objUpdate.getShipPostalCode() != null && !objUpdate.getShipPostalCode().isEmpty()){
                entityUpdate.setShipPostalCode(objUpdate.getShipPostalCode());
            }
            if(objUpdate.getShipCountry() != null && !objUpdate.getShipCountry().isEmpty()){
                entityUpdate.setShipCountry(objUpdate.getShipCountry());
            }
            if(objUpdate.getShippingFee() >=0){
                entityUpdate.setShippingFee(objUpdate.getShippingFee());
            }
            if(objUpdate.getPaymentType() >=0){
                entityUpdate.setPaymentType(objUpdate.getPaymentType());
            }
            if(objUpdate.getPaidDate() !=null){
                entityUpdate.setPaidDate(objUpdate.getPaidDate());
            }
            if(objUpdate.getOrderStatus() != null && !objUpdate.getOrderStatus().isEmpty()) {
                entityUpdate.setOrderStatus(objUpdate.getOrderStatus());
            }
            if (objUpdate.getShopPaymentTypeId() >=0){
                entityUpdate.setShopPaymentTypeId(objUpdate.getShopPaymentTypeId());
            }

            //repo xu ly update
            shopOrdersRepo.save(entityUpdate);

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
}//end class

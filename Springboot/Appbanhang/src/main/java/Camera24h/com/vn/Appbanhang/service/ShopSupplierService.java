package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.ShopCategoryDTO.ShopCategoryCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopCategoryDTO.ShopCategoryUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopCategories;
import Camera24h.com.vn.Appbanhang.enity.ShopSuppliers;
import Camera24h.com.vn.Appbanhang.repository.ShopCategoryRepository;
import Camera24h.com.vn.Appbanhang.repository.ShopSupplierRepository;
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
public class ShopSupplierService {

    @Autowired
    private ShopSupplierRepository shopSupplierRepo;


    /******I - get co phan trang*******/
    public ResponseEntity<Map<String, Object>> getAllPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu ket qua tra  ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thu thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<ShopSuppliers> pageResult = shopSupplierRepo.findAll(pageable);
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
    public ResponseEntity<Map<String, Object>> createrShopSupplier(ShopSupplierCreateRequestDTO objCreate){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo xu ly create
        ShopSuppliers supplierEnity = new ShopSuppliers();
        supplierEnity.setSupplierCode(objCreate.getSupplierCode());
        supplierEnity.setSupplierName(objCreate.getSupplierName());
        supplierEnity.setDescription(objCreate.getDescription());
        supplierEnity.setImage(objCreate.getImage());

        //luu lai khoi tao
        ShopSuppliers createEntity;
        try{
            createEntity = shopSupplierRepo.save(supplierEnity);
        }catch (Exception ex){
            throw  ex;
        }

        //tra ve ket qua thong bao theo chuan restfull api
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopSupplier(Integer id){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopSuppliers> optFound = shopSupplierRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tiem kiem dc
            ShopSuppliers delId = optFound.get();
            //dung repo xoa no
            shopSupplierRepo.delete(delId);

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
    public ResponseEntity<Map<String, Object>> updateShopCategory(Integer id, ShopSupplierUpdateRequestDTO objUpdate){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();


        //goi repo xu ly update tren id tuong ung
        Optional<ShopSuppliers> optFound = shopSupplierRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim kiem cho entity
            ShopSuppliers enityUpdate = optFound.get();
            //goi repo xu ly update va kiemtra value co null hay khong
            if(objUpdate.getSupplierCode() != null && !objUpdate.getSupplierCode().isEmpty()){
                enityUpdate.setSupplierCode(objUpdate.getSupplierCode());
            }
            if(objUpdate.getSupplierName() != null && !objUpdate.getSupplierName().isEmpty()){
                enityUpdate.setSupplierName(objUpdate.getSupplierName());
            }
            if(objUpdate.getDescription() != null && !objUpdate.getDescription().isEmpty()){
                enityUpdate.setDescription(objUpdate.getDescription());
            }
            if(objUpdate.getImage() != null && !objUpdate.getImage().isEmpty()){
                enityUpdate.setImage(objUpdate.getImage());
            }

            //repo xu ly update
            shopSupplierRepo.save(enityUpdate);

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

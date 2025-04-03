package Camera24h.com.vn.Appbanhang.service;


import Camera24h.com.vn.Appbanhang.DTO.ShopImportsDTO.ShopImportsCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopImportsDTO.ShopImportsUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopImports;
import Camera24h.com.vn.Appbanhang.repository.ShopImportsRepository;
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
public class ShopImportsService {

    @Autowired
    private ShopImportsRepository shopImportsRepo;

    /******I - get co phan trang*******/
    public ResponseEntity<Map<String, Object>> getALLPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu tru kq tra ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thuc thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<ShopImports> pageResult = shopImportsRepo.findAll(pageable);
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
    public ResponseEntity<Map<String, Object>> createShopImports(ShopImportsCreateRequestDTO objCreate){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        /**yeu cau repo xu ly create*/
        ShopImports shopEntity = new ShopImports();
        shopEntity.setStoreId(objCreate.getStoreId());
        shopEntity.setEmployeeId(objCreate.getEmployeeId());
        shopEntity.setImportDate(objCreate.getImportDate());

        /** yeu cau repo luu lai value vua khoi tao va ghi nhan value tra ve*/
        ShopImports createEntity = shopImportsRepo.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopImports(Integer id){
        // khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xy ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopImports> optFound = shopImportsRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tiem kiem dc
            ShopImports delId = optFound.get();

            //dung repo xoa record trong database
            shopImportsRepo.delete(delId);

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
    public ResponseEntity<Map<String, Object>> updateShopImports(Integer id, ShopImportsUpdateRequestDTO objUpdate){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly update tren id tuong ung
        Optional<ShopImports> optFound = shopImportsRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim duoc cho entity
            ShopImports entityUpdate = optFound.get();
            //goi repo xu ly update va kiem tra value co null hay ho
            if(objUpdate.getStoreId() >=0){
                entityUpdate.setStoreId(objUpdate.getStoreId());
            }
            if(objUpdate.getEmployeeId() >=0){
                entityUpdate.setEmployeeId(objUpdate.getEmployeeId());
            }
            if(objUpdate.getImportDate() !=null){
                entityUpdate.setImportDate(objUpdate.getImportDate());
            }

            //repo xu ly update
            shopImportsRepo.save(entityUpdate);

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

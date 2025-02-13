package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO.AclRolehasPermissionCreateDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclRolehasPermissionDTO.AclRolehasPermissionUpdateDTO;
import Camera24h.com.vn.Appbanhang.enity.AclRolehasPermisions;
import Camera24h.com.vn.Appbanhang.repository.AclRolehasPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AclRolehasPermissionService {

    //khoi tao repository
    @Autowired
    private AclRolehasPermissionRepository aclRolehasPermissionRepo;

    /*************************1 - get view (co phan trang)*******************************/
    public ResponseEntity<Map<String, Object>> getAllRolehasPermissionPagination(int pageNumber, int pageSize, String sortBy){
       //tao luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //repository thuc thi phan trnag voi class pageAble
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<AclRolehasPermisions> pageResult = aclRolehasPermissionRepo.findAll(pageable);
        if(pageResult.hasContent()){
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


    /******************************************2 -create*****************************************************/
    public ResponseEntity<Map<String, Object>> batchCreateRolehasPermission(AclRolehasPermissionCreateDTO objCreate){
        //khoi tao bien luu  tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //goi repository xu ly create
        List<AclRolehasPermisions> listCreateEnity = new ArrayList<>();
        try{
            for(Integer permissionID : objCreate.getListPermission()){
                AclRolehasPermisions aclRolehasPerEntity = new AclRolehasPermisions();
                aclRolehasPerEntity.setPermissionId(permissionID);
                aclRolehasPerEntity.setRoleId(objCreate.getRoleId());

                aclRolehasPermissionRepo.save(aclRolehasPerEntity);
                listCreateEnity.add(aclRolehasPerEntity);
            }
        }catch (Exception ex){
            throw  ex;
        }
        //tra ve ketqua cuan restfull API
        response.put("data", listCreateEnity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    /*************************************3 - delete**************************************************/
 public ResponseEntity<Map<String, Object>> deleteRolehasPermission(Integer id){
     //bien luu tru
     Map<String, Object> response = new HashMap<>();

     //goi den repository
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
     Optional<AclRolehasPermisions> optFound = aclRolehasPermissionRepo.findById(id);
     if(optFound.isPresent()){
         //ghi nhan value vua tiem kiem
         AclRolehasPermisions delEnity = optFound.get();
         //dung repository xoa ket vua tim thay id
         aclRolehasPermissionRepo.delete(delEnity);

         //tra ve thong bao thanh cong
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



 /**************************************4 - update********************************************/
public ResponseEntity<Map<String, Object>> updateRolehasPermission(Integer id, AclRolehasPermissionUpdateDTO objUpdate){
    //khoi tao bien luuu tru
    Map<String, Object> response = new HashMap<>();

    //goi repository
    Optional<AclRolehasPermisions> optFound = aclRolehasPermissionRepo.findById(id);
    if(optFound.isPresent()){
        //nhan value id vua tim thay
        AclRolehasPermisions enityEdit = optFound.get();
        //dung repository update - 2 th value nay bat buoc phai nhap do co kho ngoai lien ket
        enityEdit.setPermissionId(objUpdate.getPermissionId());
        enityEdit.setRoleId(objUpdate.getRoleId());

        try{
            aclRolehasPermissionRepo.save(enityEdit);
        }catch(Exception ex){
            throw ex;
        }

        //tra ve ket qua thanh cong
        response.put("data", enityEdit);
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

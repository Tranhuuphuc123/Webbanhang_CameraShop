package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesBatchCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclUserHasRolesDTO.AclUserHasRolesUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.AclUser;
import Camera24h.com.vn.Appbanhang.enity.AclUserHasRoles;
import Camera24h.com.vn.Appbanhang.repository.AclUserHasRolesRepository;
import jakarta.persistence.ManyToOne;
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
public class AclUserHasRolesService {

    //khoi tao va goi repository cuar acl_user_has_roles vao day
    //@Autowired la tiem phu thuoc cac values va tt tu repository va enity mot cach auto
    @Autowired
    private AclUserHasRolesRepository aclUserHasRoleRepo;

    /************************METHOD LUAN LY LOGIC ***************************/
    /*01- metod logic Get khong phan trang*/
    public ResponseEntity<Map<String, Object>> getAllAclUserHasRoles(){
        //khoi tao response luu  tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau resposiory lay value va luu vao mang list
        List<AclUserHasRoles> listAclUserHasRoles = (List<AclUserHasRoles>) aclUserHasRoleRepo.findAll();

        //tra ve ket qua theo chuan API
        response.put("data", listAclUserHasRoles);
        response.put("statusCode", 200);
        response.put("msg", "get du lieu thanh cong");

        return new ResponseEntity(response, HttpStatus.OK);
    }



    /*01-01: get du lieu co phan trang*/
    public ResponseEntity<Map<String, Object>> getAllAclUserHasRoles_Pagination(int pageNumber, int pageSize, String sortBy){
        // khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi den repository vaf khai bao Pageable xu ly phan trang
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<AclUserHasRoles> pageResult = aclUserHasRoleRepo.findAll(pageable);
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



    /*02- method post create*/
    public ResponseEntity<Map<String, Object>> createUserHasRoles(AclUserHasRolesCreateRequestDTO objCreate){
        //tao Map response luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repository
        AclUserHasRoles aclUserRoleEnity = new AclUserHasRoles();
//        aclUserRoleEnity.setUserId(objCreate.getUserId());
//        aclUserRoleEnity.setRoleId(objCreate.getRoleId());

        /*do dung annotation la cac '@ManyToOne' , '@OneToMany' khoa ngoai lien ket Entity
         trong spring boot
         ===> nen khong the get/set bien theo cach thong thuong ma phai viet nhu sau*/
//        AclUser getUserId = aclUserRoleEnity.getId(objCreate.getUserId());
        aclUserRoleEnity.setUser(null);
        aclUserRoleEnity.setRole(null);

        //luu lai cac khoi tao
        try{
            aclUserHasRoleRepo.save(aclUserRoleEnity);
        }catch(Exception ex){
            throw  ex;
        }

        //tra ve ketqua cuan restfull API
        response.put("data", aclUserRoleEnity);
        response.put("statuscode", 201);
        response.put("msg", "create list roleId thành công cho user vua chon");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /*02-01 method post batchRreate -> phân nhiều role cho một user
    * với DTO là mot mang list no se chua nhieu value role cho mot user
    * moi lan call api create len
    * */
    public ResponseEntity<Map<String, Object>> batchCreateUserHasRoles(AclUserHasRolesBatchCreateRequestDTO objCreate){
        //tao Map response luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repository
        List<AclUserHasRoles> listCreateEntity = new ArrayList<>();
        try{
            for(Integer roledId : objCreate.getListRoleId()){
                AclUserHasRoles aclUserRoleEnity = new AclUserHasRoles();
//                aclUserRoleEnity.setUserId(objCreate.getUserId());
//                aclUserRoleEnity.setRoleId(roledId);

                aclUserHasRoleRepo.save(aclUserRoleEnity);
                listCreateEntity.add(aclUserRoleEnity);
            }
        }catch(Exception ex){
            throw  ex;
        }

        //tra ve ketqua cuan restfull API
        response.put("data", listCreateEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    /*03 - deleted method*/
    public ResponseEntity<Map<String, Object>> deleteUserHasRoles(Integer id){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi den repository
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<AclUserHasRoles> optFound = aclUserHasRoleRepo.findById(id);
        if(optFound.isPresent()){
            //neu co  ton taiti lay ghi nhan values tu id vuwa check tren
            AclUserHasRoles delEnity = optFound.get();
            //nho repository xoa ket qua
            aclUserHasRoleRepo.delete(delEnity);

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


    /*04- put update method*/
    public ResponseEntity<Map<String, Object>> updateUserHasRoles(Integer id, AclUserHasRolesUpdateRequestDTO objCreate){
        //khoi tao bien luu ttru
        Map<String, Object> response = new HashMap<>();

        //goi repository
        Optional<AclUserHasRoles> optFound = aclUserHasRoleRepo.findById(id);
        if(optFound.isPresent()){
            //nhan values tu id vua tim dc
            AclUserHasRoles enityEdit = optFound.get();

            //do co hai thuoc tinh gan nhu bat buoc phai co nen no khoi kiem tra cos null hay khong
//            enityEdit.setUserId(objCreate.getUserId());
//            enityEdit.setRoleId(objCreate.getRoleId());

            try{
                //tien hanh luu ket qua vua update
                aclUserHasRoleRepo.save(enityEdit);
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

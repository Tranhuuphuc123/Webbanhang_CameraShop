package Camera24h.com.vn.Appbanhang.service;
//class luan ly logic xu ly nghiep vu CRUD tuong tac truc tiep voi csdl MySQL

import Camera24h.com.vn.Appbanhang.DTO.AclRolesDTO.AclRoleCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.AclRolesDTO.AclRoleUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.AclRole;
import Camera24h.com.vn.Appbanhang.repository.AclRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AclRoleService {

    //khoi tao repository cua acl_role o lop service
    //@Autowired: annotation nay giup tim kiem va inject cac phu thuoc tu enity qua
    @Autowired
    private AclRoleRepository aclRoleRepo;

    /******************xay dung cac function logic code xu ly thao tac crud************************/
    /*01-0 - getAll: render api csdl khong phan trang*/
    public ResponseEntity<Map<String, Object>> getAllRoles(){

        /**giai thich code
         * Map: Interjace tong java giup luu tru value theo cap key-value
         * Object: la kieu dl cho phép giá trị trong Map có thể là bất kỳ loại dữ liệu nào, từ chuỗi, số, danh sách..
         * HashMap:
         *     + Đây là một lớp thực thi của interface Map
         *     + lưu trữ các cặp key-value trong một bảng băm (hash table),
         *     giúp việc truy xuất dữ liệu nhanh chóng dựa trên key.
         *
         * <>: Đây là cú pháp sử dụng "diamond operator" trong Java.
         * Nó cho phép trình biên dịch suy diễn kiểu dữ liệu, nên bạn không cần phải
         * lặp lại kiểu trong phần khởi tạo (HashMap<String, Object>).
         * ==>  tom lai:
         *              + muc dich doan nay la Tạo một đối tượng HashMap rỗng.
         *              + Cho phép bạn thêm dữ liệu vào Map này dưới dạng cặp key-value
         *              va luu tru no trong bien respone
         * **/
        //a- khoi tao bien response luu tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //b- yeu cau repository lay du lieu
        List<AclRole> listRoles = (List<AclRole>) aclRoleRepo.findAll();

        //c - tra ve ket qua cho nguoi dun theo chuan ApiResfull
        response.put("data", listRoles);
        response.put("statuscode", 200);
        response.put("msg", "get dữ liệu thành công");

        return new ResponseEntity(response, HttpStatus.OK);
    }



    /*01-02: get du lieu co phan trang*/
    public ResponseEntity<Map<String, Object>> getAllRolePagination(int pageNumber, int pageSize, String sortBy){
        // khoi tao Map reponse luu tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //goi den AclRoleRepository xu ly method
        //Pageable: xu ly phan  trang
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<AclRole> pageResult =  aclRoleRepo.findAll(pageable);
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




    /*02- post(create method)*/
    public ResponseEntity<Map<String, Object>> createRoles(AclRoleCreateRequestDTO objCreate){
        //Map response luu tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //khoi tao aclRoleEnity
        AclRole aclRoleEnity = new AclRole();
        aclRoleEnity.setName(objCreate.getName());
        aclRoleEnity.setDisplayName(objCreate.getDisplayName());
        aclRoleEnity.setGuardName(objCreate.getGuardName());

        //luu lai cac khoi tao
        AclRole createEntity;
        try{
             createEntity = aclRoleRepo.save(aclRoleEnity);
        }catch(Exception ex){
            throw ex;
        }

        //tra ve ket qua thong bao theo chuan restfull api
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    /*03_ delete*/
    public ResponseEntity<Map<String, Object>> deleteRole(Integer id){
        //khoi tao bien response luu tru ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //goi den AclRoleRepository de thuc hien thao ta delete
        Optional<AclRole> optFound = aclRoleRepo.findById(id);
        if(optFound.isPresent()){
            //neu id co ton tai thi lay no va ghi nhan vao enity
            AclRole delEnity = optFound.get();
            //nho repository xoa di ket qua vua tra ve
            aclRoleRepo.delete((delEnity));

            //tra ve ketqua nguoiw dung
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


    /*04 - update method*/
    public ResponseEntity<Map<String, Object>> updateRole(Integer id, AclRoleUpdateRequestDTO objEdit){
        //khoi tao Map response luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //goi den AclRoleRepository
        Optional<AclRole> optFound = aclRoleRepo.findById(id);
        //kiem t ra coi thu the do co ton tai khong thi moi tien hanh update
        if(optFound.isPresent()){
            //nhan id vua tim kiem
            AclRole enityEdit = optFound.get();

            //keim tra cap nhat xem cac truong value co null hay khong
            if(objEdit.getName()!= null && !objEdit.getName().isEmpty()){
                enityEdit.setName(objEdit.getName());
            }
            if(objEdit.getDisplayName()!= null && !objEdit.getDisplayName().isEmpty()){
                enityEdit.setDisplayName(objEdit.getDisplayName());
            }
            if(objEdit.getGuardName()!= null && !objEdit.getGuardName().isEmpty()){
                enityEdit.setGuardName(objEdit.getGuardName());
            }
            enityEdit.setUpdatedAt(LocalDateTime.now());


                //yeu cau repository update
               aclRoleRepo.save(enityEdit);


            // trả về kết quả thông báo thành công
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

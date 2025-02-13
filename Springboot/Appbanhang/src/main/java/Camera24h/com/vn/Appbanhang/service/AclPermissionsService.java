package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.enity.AclPermissions;
import Camera24h.com.vn.Appbanhang.repository.AclPermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AclPermissionsService {

    //goi va khoi to repository
    @Autowired
    private AclPermissionsRepository aclPermissionsRepo;

    /*1 - method get all khong phan  trang*/
    public ResponseEntity<Map<String, Object>> getALlPermision(){
        //koi tao map reposnse luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repository lay du lieu
        List<AclPermissions> listPermision = (  List<AclPermissions>) aclPermissionsRepo.findAll();

        //ket qua tra ve theo chuan restfull api
        response.put("data", listPermision);
        response.put("statuscode", 200);
        response.put("msg", "get du lieu thanh cong");

        return new ResponseEntity(response, HttpStatus.OK);
    }


 //1-2 get du lieu co phan trang
    public ResponseEntity<Map<String, Object>> gettALLPerPagination(int pageNumber, int pageSize, String sortBy){
         //khoi tao map repsone luu tru
        Map<String, Object> response = new HashMap<>();

        //goi den reposiory xu ly get du lieu vaf phan trang
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy) );
        Page<AclPermissions> pageResult = aclPermissionsRepo.findAll(pageable);
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

}//end class

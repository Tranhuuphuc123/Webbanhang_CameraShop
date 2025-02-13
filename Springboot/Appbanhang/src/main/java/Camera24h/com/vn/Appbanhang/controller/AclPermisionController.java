package Camera24h.com.vn.Appbanhang.controller;

import Camera24h.com.vn.Appbanhang.service.AclPermissionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/acl-permisions")
@Tag(name = "AclPermisions", description = "APi liet ke cac quyen cho Admin/user")
public class AclPermisionController {
    //ghi log nhat ky
    Logger logger = LoggerFactory.getLogger(AclPermisionController.class);

    //khoi tao AclPermisions
    @Autowired
    private AclPermissionsService aclPermissionsService;

    //1- get
    @GetMapping
    public ResponseEntity<Map<String, Object>> index(@RequestParam(defaultValue = "1") Integer pageNUmber,
                                                     @RequestParam(defaultValue = "3") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy){
        return aclPermissionsService.gettALLPerPagination(pageNUmber, pageSize, sortBy);
    }
}

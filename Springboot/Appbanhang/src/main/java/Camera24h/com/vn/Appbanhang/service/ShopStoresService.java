package Camera24h.com.vn.Appbanhang.service;
//class luan ly logic xu ly nghiep vu CRUD tuong tac truc tiep voi csdl MySQL

import Camera24h.com.vn.Appbanhang.DTO.ShopStoresDTO.ShopStoresCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopStoresDTO.ShopStoresUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopStores;
import Camera24h.com.vn.Appbanhang.repository.ShopStoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ShopStoresService {

    //khoi tao repository cua shop_product_images o lop service
    //@Autowired: annotation nay giup tim kiem va ịneject cac phu thuoc tu entity qua
    @Autowired
    private ShopStoresRepository shopStoresRepository;

    //tao biến string lấy url cấu hình lưu file đã thiết lặp trong application.properties
    //annotation @Value: được sử dụng để gán giá trị cho một biến từ các nguồn như: application.properties/yaml
    @Value("${file.upload-dir}")
    private String uploadDir;

    /******* get co phân trang *******/
    public ResponseEntity<Map<String, Object>>  getAllPagination(int pageNumber, int pageSize, String sortBy){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thuc thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<ShopStores> pageResult = shopStoresRepository.findAll(pageable);
        if(pageResult.hasContent()){
            //tra ve ket qua cho nguoi dung -> tra theo chuan restFULLAPI
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
    public ResponseEntity<Map<String, Object>> createShopStores(ShopStoresCreateRequestDTO objCreate, MultipartFile file){
        //khoi tao bien luu kq tra ve
        Map<String, Object> response = new HashMap<>();

        /****xu ly th gap loi khi luu file cung ten:
         *vd image a: anh dong ho rolex -> luu ten la rolex.png
         *       image b: anh do ho rolex pro -> cung luu ten la rolec.png
         *       --> van de gap phai la khi choose file -> create file no se bi trung ten
         *       va thang sau se de len thang trc khuon mat di anh cua nguoi t1 hoac gap loi
         *    ===> vay de khac phuc loi luu trung ten ta co nhieu cach pho bien la hai cach sau:
         *
         *     ++ c1: co the dung phuong thuc UUID de tao ra cac ma ten khac nhau khi create imge ma du
         *     ban dau co cung ten(co the goi method randomUUID de sinh ma ten tu nhien tranh trung)
         *      -> truy cap trang: "https://www.baeldung.com/java-uuid" de tham khao them
         *
         *     ++ c2: la dung phuong thuc DatetimeFormatter: de ghi nhan thoi gian luu anh tranh trung
         *     dua tren moc thoi gian
         *      -> truy cap trang: "https://www.baeldung.com/java-uuid" tham khao them
         *
         *     ... con rat nhieu cach khac nhau co the tim hieu them....
         * *****/
        //tao chuoi randomString de bien doi UUIDm or datetime thanh chuoi string de inset vao csdl
        String randomString = "";
        //c1: UUID
//        UUID uuid = UUID.randomUUID();
//        randomString = uuid.toString();

        //c2: dùng datetime get yymmdd for spring java
        DateTimeFormatter iso_8601_formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        randomString = LocalDateTime.now().format(iso_8601_formatter);

        /** thiet lap file path lay dung ten goc o dia luu folder trong project
         * => co the gang cung root url tren o dia: E:\\PRATICE_CODE\\SPRING_FRAMEWORK\\SPRINGBOOT\\thuchanh\\project_web3lop\\Appbanhang
         * <=> tuy nhien code nay qua may khac se loi nen thiet lap thong minh hon
         * de qua moi may no tu anh xa url tren o dia cua may tinh dang chay project
         * => Paths -> nen dung cua lib  java.nio.file.Paths;
         * ***/
        String rootFolder = Paths.get("").toAbsolutePath().toString();

        /*****tạo đường dan xu ly luu file*****
         * a/ File.separator: co nhiem vu chinh la dùng để chỉ dấu phân cách thư mục (directory separator)
         * phù hợp với hệ điều hành mà ứng dụng đang chạy.(vd: windown thi url thg la dau "\", con linux
         * thi url thuong la dau "\" -> voi File.separator no se tu dieu chinh auto cho phu hoi voi he
         * dieu hanh do.)
         *   vd:   String filePath = rootFolder + File.separator + uploadDir +  File.separator +  newFile;
         *   <=> hieu la: String filePath = E:\\PRATICE_CODE\\SPRING_FRAMEWORK\\SPRINGBOOT\\thuchanh\\project_web3lop\\Appbanhang\\Uploads
         * b/ uploadDir: chinh la ten file lien ket voi url thiet lap trong application.properties ay
         * c/ file.getOriginalFilename(): doan nay xu ly ghi nhan lay cai file ruot anh va tien hanh lay ruot
         * do de ghi nhan va luu vao trong folder chua uploads he
         */
        String newFile = randomString + "_" + file.getOriginalFilename();
        String filePath = rootFolder + File.separator   + uploadDir + File.separator + newFile;

        /**tien hanh xu ly luu file vao thu muc**/
        //tao muc vo ben ngoai de chua ruot file anh
        File destinationFile = new File(filePath);

        /*tien hanh tao folder uploads trong project neu no khong ton tai(th khac phuc hanh vi mac dinh
        tao folder theo cau hinh sẵn có khi cài spring boot _> de neu ma folder uploads cau hinh trong file
        apllication.properties no khong co dan dung url tren project ca nhan thi co the dung lop getParrentFile.mkdirs())
        de ghi nhan project goc dang su dung va tien hanh tu tao folder uploads de luu ruot image khi create method co
        xu ly choose file img*/
        destinationFile.getParentFile().mkdir();

        //tien hanh lay ruot anh(anh goc, kich co anh(nhieu mb...)) ghi nhan va luu vao file
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**yeu cau repo xu ly create***/
        ShopStores shopEntity = new ShopStores();
        shopEntity.setStoreCode(objCreate.getStoreCode());
        shopEntity.setStoreName(objCreate.getStoreName());
        shopEntity.setDescription(objCreate.getDescription());
        //xu ly getimage tu dto luu vao entity
        shopEntity.setImage(newFile);

        /** yeu cau repository luu lai value vua khoi tao va ghi nhan value tra ve***/
        ShopStores createEntity = shopStoresRepository.save(shopEntity);

        /**tra ve ket qua thong bao theo chuan restfull api**/
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopStores(Integer id) {
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopStores> optFound = shopStoresRepository.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tim kiem dc
            ShopStores delId = optFound.get();
            //dung repo xoa no
            shopStoresRepository.delete(delId);

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

    /*********************4 - update*************************/
    public ResponseEntity<Map<String, Object>> updateShopStores(Integer id, ShopStoresUpdateRequestDTO objUpdate, MultipartFile file) {
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly update tren id tuong ung
        Optional<ShopStores> optFound = shopStoresRepository.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim kiem cho entiy
            ShopStores entityUpdate = optFound.get();
            //goi repo xu ly update va kiem tra value co null hay khong
            if(objUpdate.getStoreCode() != null && !objUpdate.getStoreCode().isEmpty()){
                entityUpdate.setStoreCode(objUpdate.getStoreCode());
            }
            if(objUpdate.getStoreName() != null && !objUpdate.getStoreName().isEmpty()){
                entityUpdate.setStoreName(objUpdate.getStoreName());
            }
            if(objUpdate.getDescription() != null && !objUpdate.getDescription().isEmpty()){
                entityUpdate.setDescription(objUpdate.getDescription());
            }

            //xu ly upload file co anh img
            if (file != null){
                /** qui trinh 1 -update file moi(khoi tao create file img moi) vao thu muc mong doi**/
                //tao chuoi randomString de bien doi UUIDm or datetime thanh chuoi string de inset vao csdl
                String randomString = "";

                //dung datetime get yymmdd for spring java
                DateTimeFormatter iso_8601_formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                randomString = LocalDateTime.now().format(iso_8601_formatter);

                String rootFolder = Paths.get("").toAbsolutePath().toString();

                String newFile = randomString + "_" + file.getOriginalFilename();
                String filePath = rootFolder + File.separator + uploadDir + File.separator + newFile;

                //**tien hanh xu ly luu file vao thu muc**/
                //tao muc vo ben ngoai de chua ruot file anh
                File destinationFile = new File(filePath);

                //neu khong ton tai folder nao thi no tu tạo folder chua
                destinationFile.getParentFile().mkdir();

                //tien hanh lay ruot anh(anh goc, kich co anh()nhieu mb...)) ghi nhan va luu vao file
                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /**qui trinh 2 - tien hanh xoa file cu da ton tai trc do de tranh file rac ton dong **/
                Path deletingFilePath = Path.of(rootFolder + File.separator + uploadDir + File.separator + entityUpdate.getImage());
                try{
                    //deleteIfExists co ton tai no moi xoa
                    Files.deleteIfExists(deletingFilePath);
                }catch (IOException e){
                    e.printStackTrace();
                }

                /**qui trinh 3 - cap nhat duong dan file img moi lai vao file trong database**/
                entityUpdate.setImage(newFile);
            }

            //repo xu ly update
            shopStoresRepository.save(entityUpdate);

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

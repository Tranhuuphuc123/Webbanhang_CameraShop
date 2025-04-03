package Camera24h.com.vn.Appbanhang.service;

import Camera24h.com.vn.Appbanhang.DTO.ShopProductDTO.ShopProductCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopProductDTO.ShopProductUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierCreateRequestDTO;
import Camera24h.com.vn.Appbanhang.DTO.ShopSupplierDTO.ShopSupplierUpdateRequestDTO;
import Camera24h.com.vn.Appbanhang.enity.ShopProducts;
import Camera24h.com.vn.Appbanhang.enity.ShopSuppliers;
import Camera24h.com.vn.Appbanhang.repository.ShopProductRepository;
import Camera24h.com.vn.Appbanhang.repository.ShopSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShopProductService {

    @Autowired
    private ShopProductRepository shopProductRepo;

    //tạo biến string lấy url cấu hình lưu file đã thiết lặp trong application.properties
    //annotation @Value: được sử dụng để gán giá trị cho một biến từ các nguồn như: application.properties/yaml
    @Value("${file.upload-dir}")
    private String uploadDir;


    /******I - get co phan trang*******/
    public ResponseEntity<Map<String, Object>> getAllPagination(int pageNumber,
                                                                int pageSize,
                                                                String sortBy,
                                                                String searchTerm){
        //khoi tao bien luu ket qua tra  ve
        Map<String, Object> response = new HashMap<>();

        //yeu cau repo thu thi get all co phan trang voi pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by(sortBy));
        Page<ShopProducts> pageResult = null;

        //neu nhu khogn co thuc thi tiem kiem thi hien thi phang trang binh thuong <=> serarch thi bo phan trang
        if(searchTerm == null || searchTerm.isEmpty()){
            pageResult =  shopProductRepo.findAll(pageable);
        }else{
            //co yeu cau tim kiem thi tien hanh xoa phan trang di
            pageResult =  shopProductRepo.findByProdcutnameContainOrProducCodeContain(searchTerm.toLowerCase(),
                                                                        searchTerm.toLowerCase(),
                                                                        pageable);
        }

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




    /*********I -  1: xậy dựng mehod tiem kiem tra ve ket qua  id***********************/
    /*nhu cach thuc cua getAllPagination no cung tra ve ket qua tuy nhien getallPaginattion
     * la method tra ve ket qua theo recored value hien thi ra tat ca value can co trng csdl
     * -> gio trong method nay ta can tra la tra ve ket qua la id cua record tuong ung thui
     * ==> trong bai nay ta phuc vu chuc nang update tuy nhien trc khi thuc thi method update
     * ta can tra ve ket qua la cac id tuong ung cac record tren csdl */
    public ResponseEntity<Map<String, Object>> getById (Integer id){
        //khoi tao bien luu ket qua tra ve
        Map<String, Object> response = new HashMap<>();

        //nho repo thuc thi tra ve ket qua id => luu trong bien Optional(chap nhan gia tri null)
        Optional<ShopProducts> optFoundById = shopProductRepo.findById(id);
        //neu no co ton tai
        if(optFoundById.isPresent()){
            //nhan id vau tim kiem dc
            ShopProducts shopEntity = optFoundById.get();

            //tra ve thong bao thanh cong
            response.put("data", shopEntity);
            response.put("statuscode", 201);
            response.put("msg", "ket qua tra ve ton tai id vua tim kiem");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            //tra ve ket qua nguoi dung
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "vui long xem lai khong ton tai id vua tim kiem");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    /**************2 - create *********************/
     /*MultipartFile: Là một interface trong Spring Framework được sử dụng để xử lý các tệp (files) được upload thông qua
    HTTP requests sử dụng multipart/form-data.*/
    public ResponseEntity<Map<String, Object>> createrShopProduct(ShopProductCreateRequestDTO objCreate, MultipartFile file){
        //khoi tao bien luu tru
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

        //c2: dung datetime get yymmdd for spring java
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
        String filePath = rootFolder + File.separator + uploadDir +  File.separator +  newFile;

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

        //yeu cau repo xu ly create
        ShopProducts shopProductEnitty = new ShopProducts();
        shopProductEnitty.setProductCode(objCreate.getProductCode());
        shopProductEnitty.setProductName(objCreate.getProductName());

        //xu ly getimage tu dto luu vao entity
        shopProductEnitty.setImage(newFile);

        shopProductEnitty.setShortDescription(objCreate.getShortDescription());
        shopProductEnitty.setDescription(objCreate.getDescription());
        shopProductEnitty.setStandardCode(objCreate.getStandardCode());
        shopProductEnitty.setListPrice(objCreate.getListPrice());
        shopProductEnitty.setQuantityPerUnit(objCreate.getQuantityPerUnit());

        //voi kieu du lieu boolean choose thi phai get theo kieu nay
        shopProductEnitty.setDiscontinued(objCreate.isDiscontinued());
        shopProductEnitty.setFeatured(objCreate.isFeatured());
        shopProductEnitty.setNew(objCreate.isNew());

        shopProductEnitty.setCategoryId(objCreate.getCategoryId());
        shopProductEnitty.setSupplierId(objCreate.getSupplierId());


        //luu lai khoi tao
        ShopProducts createEntity = shopProductRepo.save(shopProductEnitty);

        //tra ve ket qua thong bao theo chuan restfull api
        response.put("data", createEntity);
        response.put("statuscode", 201);
        response.put("msg", "create thành công");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /****************3 - delete**********************/
    public ResponseEntity<Map<String, Object>> deleteShopProduct(Integer id){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();

        //goi repo xu ly delete
         /* Optional:
        + Là một lớp trong Java (java.util.Optional) được giới thiệu từ Java 8.
                + Nó là một container object có thể chứa một giá trị không null hoặc rỗng (empty).
                + Mục tiêu chính của Optional là giúp giảm thiểu lỗi NullPointerException khi xử lý các
            * giá trị co thể là null.*/
        Optional<ShopProducts> optFound = shopProductRepo.findById(id);
        if(optFound.isPresent()){
            //ghi nhan ket qua id vua tiem kiem dc
            ShopProducts delId = optFound.get();

            // xóa ảnh luu trong folder tren project tranh giu file rác khi record trên database đã xóa rồi mà img vần còn
            /**code nay muon cần giải thích có thêm xem ở giải thích của method create
             * => giai thich them ti:
             *  + de xoa anh thi can thiet lap kiem tra coi no co ton tai khong neu co  thi moi xoa
             *  => luu ý can chuyen bien filePath thanh kieu du lieu Path de theo chuan viet code java mơi
             *  + Path là một interface trong Java NIO (New Input/Output), được sử dụng để biểu diễn đường dẫn của một tệp
             *  hoặc thư mục trong hệ thống tập tin.
             * + Paths.get(...) tạo ra một đối tượng Path từ chuỗi đường dẫn được truyền vào.
             * ==> tum lai: Biến filePath chứa đường dẫn tuyệt đối đến tập tin ảnh cần thao tác.
             * **/
            String rootFolder = Paths.get("").toAbsolutePath().toString();
            Path filePath = Path.of(rootFolder + File.separator + uploadDir +  File.separator +  delId.getImage());
            try{
                //deleteIfExists co ton tai no moi xoa
                Files.deleteIfExists(filePath);
            }catch (IOException e){
                e.printStackTrace();
            }
            //dung repo xoa no
            shopProductRepo.delete(delId);

            //tra ve  thong bao thanh cong
            response.put("data", null);
            response.put("statuscode", 200);
            response.put("msg", "delete thành công");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            //tra ve ket qua nguoi dung
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "Có tài khoản xóa không tồn tại vui lòng kiểm tra lại");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    /****3-1- delete all nhiều value cùng lúc ****/
    public ResponseEntity<Map<String, Object>> deleteShopProductMultiple(List<Integer> ids){
        //1 - tao hàm Map khởi tạo lưu trữ kết quả trả về
        Map<String, Object> response = new HashMap<>();

        /*2 - nho repo tim kiem xem co id nao khong ton tai khong neu co thi bao loi
        * >>y nghia:<<
        * => List<ShopProducts>: tra ve list danh sach id, existinProductId(existing tv la
        * co ton tai khong?)
        * => de khac phuc th la neu id khong ton tai thi sao xoa dc phai bao loi de biet
        * id do khong co ton tai nua
        *
        * */
        List<ShopProducts> existingProductId =   (List<ShopProducts>)  shopProductRepo.findAllById(ids);

        //lap dieu kien neu id can xoa lay ma ton tai mot id khog nco thi khog du dk xoa
        if(existingProductId.size() != ids.size()){
           //2 -1 ne phat hien id khogn du hoac khog co trong list id can xoa thi xu ly tra ket qua thong bao
            response.put("data", null);
            response.put("statuscode", 404);
            response.put("msg", "tài khoản xóa không tồn tại");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //2-2: thuc hien xoa file ruot anh
        for(ShopProducts shopPro  : existingProductId){
            String rootFolder = Paths.get("").toAbsolutePath().toString();
            Path filePath = Path.of(rootFolder + File.separator + uploadDir +  File.separator +  shopPro.getImage());
            try{
                //deleteIfExists co ton tai no moi xoa
                Files.deleteIfExists(filePath);
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        // 3 th: tat ca id mun xoa da thoa dieu kien da co ton tai trong database thi thuc hien -> t/h xoa
        shopProductRepo.deleteAllById(ids);

        // tra ve  thong bao thanh cong
        response.put("data", null);
        response.put("statuscode", 200);
        response.put("msg", "delete danh sách các id cần xóa thành công");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    /*********************4 - update*************************/
    public ResponseEntity<Map<String, Object>> updateShopProduct(Integer id, ShopProductUpdateRequestDTO objUpdate,
                                                                MultipartFile file ){
        //khoi tao bien luu tru
        Map<String, Object> response = new HashMap<>();


        //goi repo xu ly update tren id tuong ung
        Optional<ShopProducts> optFound = shopProductRepo.findById(id);
        if(optFound.isPresent()){
            //gan id vua tim kiem cho entity
            ShopProducts enityUpdate = optFound.get();
            //goi repo xu ly update va kiemtra value co null hay khong
            if(objUpdate.getProductCode() != null && !objUpdate.getProductCode().isEmpty()){
                enityUpdate.setProductCode(objUpdate.getProductCode());
            }
            if(objUpdate.getProductName() != null && !objUpdate.getProductName().isEmpty()){
                enityUpdate.setProductName(objUpdate.getProductName());
            }
            //xu ly edit voi file(file thi co lien quan den anh ruot anh chu khong chi la chuoi String)
            if(file != null){
               /**quy trinh 1: update file moi (khoi tao create file img moi) vao thu muc mong doi**/
                //tao chuoi randomString rong de lưu gia tri bien moi vao
                String randomString = "";

                //dung datetime de luu tru img phan loai luu ten theo datetime tranh trung ten bi ghi de img
                DateTimeFormatter iso_8601_formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                randomString = LocalDateTime.now().format(iso_8601_formatter);

                //tao muc lay folder goc chua anh
                String rootFolder = Paths.get("").toAbsolutePath().toString();

                //tao newFile de chua anh(ruot anh chu khong phai chi la ten file)
                String rootFileImg = randomString + "_" + file.getOriginalFilename();

                /*tao bien filePath de luu url lay anh: bao gom co folder goc \\ ruot anh.img\\..
                * +  File.separator: la tuy theo he dieu hang no lay dau url dung voi he dieu hanh do
                * o day la window thi no lay dau \, neu mac no tu hieu thanh dau /
                * + File.separator: folder upload da ding nghia o properties
                * */
                String filePath = rootFolder + File.separator + uploadDir + File.separator + rootFileImg;

                /*tien hanh xu ly luu file vao thu muc*/
                //tao muc de chua ruot file anh sau khi lay dc url lay anh dung muc img da chon
                File destinationFile = new File(filePath);

                //mkdir: kiem tra neu khong ton tai muc folder da thiet lap thi no se tu tao muc folder do
                destinationFile.getParentFile().mkdir();

                //tien hanh lay ruot anh(anh goc) ghi nhan va luu vao file
                try{
                    //transferTo: giup ghi nhan va lay img(ruot anh)
                    file.transferTo(destinationFile);
                }catch(IOException e ){
                    e.printStackTrace();
                }


                /**quy trinh 2: tien hanh xoa file anh cu da ton tai trc do de tranh file rac ton dong**/
                Path deletingFilePath = Path.of(rootFolder + File.separator + uploadDir + File.separator + enityUpdate.getImage());
                try{
                    //tien hanh delete file da ton tai
                    Files.deleteIfExists(deletingFilePath);
                }catch(IOException e){
                    e.printStackTrace();
                }

                /**quy trinh 3: tien hanh cap nhat file img moi lai vao muc img trong database**/
                enityUpdate.setImage(rootFileImg);
            }
            if(objUpdate.getShortDescription() != null && !objUpdate.getShortDescription().isEmpty()){
                enityUpdate.setShortDescription(objUpdate.getShortDescription());
            }
            if(objUpdate.getDescription() != null && !objUpdate.getDescription().isEmpty()){
                enityUpdate.setDescription(objUpdate.getDescription());
            }

            //voi kieu in thi can kiem tra xem no co ton tai hay khong thi kiem tra voi ma 0 1
            if(objUpdate.getStandardCode() >= 0){
                enityUpdate.setStandardCode(objUpdate.getStandardCode());
            }
            if(objUpdate.getListPrice() >= 0){
                enityUpdate.setListPrice(objUpdate.getListPrice());
            }

            if(objUpdate.getQuantityPerUnit() != null && !objUpdate.getQuantityPerUnit().isEmpty()){
                enityUpdate.setQuantityPerUnit(objUpdate.getQuantityPerUnit());
            }

            enityUpdate.setDiscontinued(objUpdate.isDiscontinued());
            enityUpdate.setFeatured(objUpdate.isFeatured());
            enityUpdate.setNew(objUpdate.isNew());

            //repo xu ly update
            shopProductRepo.save(enityUpdate);

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

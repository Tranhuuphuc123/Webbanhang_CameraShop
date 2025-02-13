package Camera24h.com.vn.Appbanhang.repository;
/*
* *** qui trình 2 - lớp xử lý dữ liệu (data access layer) **
* => đay là qui trình lớp xu lý các thao tác CRUD (giao thức http) lên  cơ sở dữ liệu
* thông qua Entity (class mapping ánh xạ dến csdl bên MySQL)
* => trong qui trình này nó kế thừa đến class CrudRepository đã đc xd thẳng ở trong
* spring boot viêc tao ra qui  trình này để sử dụng đến các function có sẵn trong
* CrudRepository này để thực hiện truy vấn Crud mà mình sẽ code trong qui trình kế
* là service hé
* */


import Camera24h.com.vn.Appbanhang.enity.AclUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository đánh dấu đây là class repository
/*CrudRepository< <nameEnity>, <typePrimaryKey> >, Integer> class của spring boot hỗ trợ:
*  + trog đó:
*     ++ <nameEnity>: ánh xạ tới cái Enity cần thao tác
*     ++ <typePrimaryKey>: kiểu dữ liệu khóa chính của table csdl(thể hien thong qua Entity)
* */
/*PagingAndSortingRepository: lớp xử lý phân trang trong spring boot*/
@Repository
public interface AclUserRepository extends CrudRepository<AclUser, Integer>,
        PagingAndSortingRepository<AclUser, Integer> {

    //viết function tiềm kiếm theo tên hàm bổ sung thêm mà bên spring hng có hổ trợ ta tự viết
    List<AclUser> findByUsername(String userName);

}

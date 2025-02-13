package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.AclRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*interface Repository lớp kết thừa các method của spring boot kết nối các enity
* ánh xạ các csdl tu mysql
* PagingAndSortingRepository: class xu ly phan trang
* */
@Repository
public interface AclRoleRepository extends CrudRepository<AclRole, Integer>,
        PagingAndSortingRepository<AclRole, Integer> {
}

package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopPaymentTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*interface Repository lớp kết thừa các method của spring boot kết nối các enity
 * ánh xạ các csdl tu mysql
 * PagingAndSortingRepository: class xu ly phan trang
 * */
@Repository
public interface ShopPaymentTypesRepository extends CrudRepository<ShopPaymentTypes, Integer>,
        PagingAndSortingRepository<ShopPaymentTypes, Integer> {
}

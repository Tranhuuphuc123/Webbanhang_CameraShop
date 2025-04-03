package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopExports;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopExportsRepository extends CrudRepository<ShopExports, Integer>,
        PagingAndSortingRepository<ShopExports, Integer> {
}

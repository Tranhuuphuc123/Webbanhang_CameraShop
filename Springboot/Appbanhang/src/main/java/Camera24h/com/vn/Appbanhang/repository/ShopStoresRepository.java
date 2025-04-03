package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopStores;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopStoresRepository extends CrudRepository<ShopStores, Integer>,
        PagingAndSortingRepository<ShopStores, Integer> {
}

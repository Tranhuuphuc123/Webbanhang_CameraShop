package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends CrudRepository<ShopProducts, Integer>,
        PagingAndSortingRepository<ShopProducts, Integer> {
}

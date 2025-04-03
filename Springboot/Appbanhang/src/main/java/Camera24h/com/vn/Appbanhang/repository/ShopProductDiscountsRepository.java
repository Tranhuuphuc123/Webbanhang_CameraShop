package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopProductDiscounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShopProductDiscountsRepository extends CrudRepository<ShopProductDiscounts, Integer>,
        PagingAndSortingRepository<ShopProductDiscounts, Integer> {
}

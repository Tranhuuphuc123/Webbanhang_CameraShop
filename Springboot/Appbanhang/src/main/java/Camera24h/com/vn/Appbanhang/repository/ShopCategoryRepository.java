package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopCategories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCategoryRepository extends CrudRepository<ShopCategories, Integer>,
        PagingAndSortingRepository<ShopCategories, Integer> {
}

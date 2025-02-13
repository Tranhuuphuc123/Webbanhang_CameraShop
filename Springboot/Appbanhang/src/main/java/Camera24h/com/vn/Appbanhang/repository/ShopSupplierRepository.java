package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopCategories;
import Camera24h.com.vn.Appbanhang.enity.ShopSuppliers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopSupplierRepository extends CrudRepository<ShopSuppliers, Integer>,
        PagingAndSortingRepository<ShopSuppliers, Integer> {
}

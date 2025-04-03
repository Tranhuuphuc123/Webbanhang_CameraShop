package Camera24h.com.vn.Appbanhang.repository;


import Camera24h.com.vn.Appbanhang.enity.ShopImports;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopImportsRepository extends CrudRepository<ShopImports, Integer>,
        PagingAndSortingRepository<ShopImports, Integer> {
}

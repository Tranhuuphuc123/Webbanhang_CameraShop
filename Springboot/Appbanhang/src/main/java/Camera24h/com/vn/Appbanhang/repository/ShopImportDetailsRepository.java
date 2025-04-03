package Camera24h.com.vn.Appbanhang.repository;


import Camera24h.com.vn.Appbanhang.enity.ShopImportDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopImportDetailsRepository extends CrudRepository<ShopImportDetails, Integer>,
        PagingAndSortingRepository<ShopImportDetails, Integer> {
}

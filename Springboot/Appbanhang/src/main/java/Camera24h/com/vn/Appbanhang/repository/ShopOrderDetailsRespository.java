package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopOrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderDetailsRespository extends CrudRepository<ShopOrderDetails, Integer>,
        PagingAndSortingRepository<ShopOrderDetails, Integer> {
}

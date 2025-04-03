package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopOrders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrdersRepository extends CrudRepository<ShopOrders, Integer>,
        PagingAndSortingRepository<ShopOrders, Integer> {
}

package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopCustomers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCustomersRepository extends CrudRepository<ShopCustomers, Integer>,
        PagingAndSortingRepository<ShopCustomers, Integer> {
}

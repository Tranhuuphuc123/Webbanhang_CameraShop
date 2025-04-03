package Camera24h.com.vn.Appbanhang.repository;


import Camera24h.com.vn.Appbanhang.enity.ShopProductImages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShopProductImageRepository extends CrudRepository<ShopProductImages, Integer>,
        PagingAndSortingRepository<ShopProductImages, Integer> {
}

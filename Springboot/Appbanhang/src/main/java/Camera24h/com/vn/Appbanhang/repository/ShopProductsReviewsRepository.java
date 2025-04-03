package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopProductsReviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductsReviewsRepository extends CrudRepository<ShopProductsReviews, Integer> ,
        PagingAndSortingRepository<ShopProductsReviews, Integer>{

}

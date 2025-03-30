package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.ShopProducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductRepository extends CrudRepository<ShopProducts, Integer>,
        PagingAndSortingRepository<ShopProducts, Integer> {
//    //them method tim kiem theo ma code cua product
//    List<ShopProducts> findByProductCode(String productCode);
//
//    //them method tim kiem theo ten cua product va ma code
//   List<ShopProducts> findByProductCodeAndProductName(String productCode, String productName);

    // xay dung method tu viet api spring boot tiem kiem theo ten va co phan trang
    @Query("SELECT p FROM ShopProducts p WHERE "
            + "LOWER(p.productCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
            + "LOWER(p.productName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<ShopProducts> findByProdcutnameContainOrProducCodeContain(
                                                            @Param("searchTerm") String productName,
                                                            @Param("searchTerm") String productCode,
                                                            Pageable pageable);
}

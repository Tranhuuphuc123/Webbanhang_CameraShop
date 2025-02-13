package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.AclUserHasRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclUserHasRolesRepository extends CrudRepository<AclUserHasRoles, Integer>,
        PagingAndSortingRepository<AclUserHasRoles, Integer> {
}

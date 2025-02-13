package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.AclPermissions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AclPermissionsRepository extends CrudRepository<AclPermissions, Integer>,
        PagingAndSortingRepository<AclPermissions, Integer> {
}

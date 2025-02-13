package Camera24h.com.vn.Appbanhang.repository;

import Camera24h.com.vn.Appbanhang.enity.AclRolehasPermisions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclRolehasPermissionRepository  extends CrudRepository<AclRolehasPermisions, Integer>,
        PagingAndSortingRepository<AclRolehasPermisions, Integer> {
}

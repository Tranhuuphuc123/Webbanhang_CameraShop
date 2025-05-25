package Camera24h.com.vn.Appbanhang.JWT;

import Camera24h.com.vn.Appbanhang.enity.AclUser;
import Camera24h.com.vn.Appbanhang.repository.AclUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AclUserRepository alcUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           AclUser user = alcUserRepo.findByUsername(username);
           if(user == null){
               new UsernameNotFoundException("UserName khong tim thay");
           }

           //to do: lay vai tro
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("")));
    }
}

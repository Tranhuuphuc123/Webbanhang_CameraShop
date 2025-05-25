package Camera24h.com.vn.Appbanhang.config;
/*cau hinh lop bao mat security trong spring boot cho muc ma hoa code jwt token
* => muc dich: khi call api thi phai co token nay moi thuc thi call api chu khong phai
* ai cung dc tu do call api - phai co token xac minh la ban co role va permissions gi moi
* cho qua va dung api ma toi viet
*
* ==> moi lan user gui request len server thi lop nay kiem tra dua tren ma hoa jwt token
* lam sao trong header phai co jwt token, dem di giai ma dung thong tin header payload, va chu
* ky signature cuar cau truc jwt token moi cho qua va xu ly
* */

import Camera24h.com.vn.Appbanhang.JWT.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


/*@Configuration: ??????
* @EnableMethodSecurity: ???
* */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    /*tao method xu ly loc xy ly: bat ke request nao di qua cung phai qua filter nay moi dc
    * => tuc no loc kiem tra phai co jwt token moi dc di qua va xu ly tiep khong thi out*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
        return null;
    }
}

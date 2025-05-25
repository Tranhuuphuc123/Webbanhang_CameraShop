package Camera24h.com.vn.Appbanhang.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*lop tien ich xu ly trong Config/SecurityConfig.java
* => lop loc filter nay giup tao mot cong an ninh loc va loai bien nhung
* request tu nguoi dung nao gui len ma khong jwt token, sau khi dat yeu
* cau la co cac jwt token _> viec tiep theo co sang loc xu ly gi
* thi lowp SecurityConfig.java cu the la method SecurityConfig se xu ly
* lop tien ich JwtFilter nay chi co nhiem vu la lop cua loc nhung ai khong
* co jwt token thi khong cho qua thui
*
* => luu y no chi kiem tra mot lan duy nhat: vi neu khong co jwt token
* thi da loai bien ngay tu dau roi lam gi nua mac cong
* */

//OncePerRequestFilter: ???
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    //method xu ly nghiep cu class nay
    protected  void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;

        if(authHeader != null &&  authHeader.startsWith("Bearer ")){
            /*7: la cat bo 7 ky tu chu 'Bearer '.. vi ma jwt token la theo cau truc la nam
            * sau bearer va khoang tran(7 ky tu) -> ta lay la lay jwt token thui con chu
            * bearer chi de nhan dang*/
            jwt = authHeader.substring(7);
            userName = jwtProvider.extractUsername(jwt);
        }

        //kiem tra userName storage luu tru va userName trong token con trung khop khong neu ok thi xu ly
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            //ktra token valid khong
            if(jwtProvider.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}

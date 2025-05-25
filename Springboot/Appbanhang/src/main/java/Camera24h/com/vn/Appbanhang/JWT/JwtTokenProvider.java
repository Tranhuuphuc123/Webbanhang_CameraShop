package Camera24h.com.vn.Appbanhang.JWT;

import Camera24h.com.vn.Appbanhang.enity.AclUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/*lop nay dung de tao ma hoa thong tin duoi dang Jwt Token */
@Component
public class JwtTokenProvider {
    //tao key bao mat do minh quy dinh
    private final String jwtSecrets ="c@m@r@24h";

    /***tao ham  ma hoa  decode jwt(header, payload va signature)  thanh encode jwt token(ma ky tu lon xon)****/
    public String generateToken(AclUser user){
        //tao thoi han song cho token
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 86400000); //1 day ton tai(24*60*60*1000(don vi ms))

        //builder la ham xay dung theo chuan Jwt
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(Keys.hmacShaKeyFor(jwtSecrets.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }



    /*tao ham giai ma encode jwt token(ma ky tu lon xon) ngc lai thanh cac decode jwt token ****/
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecrets.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    /****ham kiem tra jwt co hop le hay khong****
     public boolean isTokenValid(String token, UserDetails userDetails){
     * + UserDetails: giup luu tru thong tin xac thuc -> xac thuc thnh cong tt se dc luu vao
     * */
    public boolean isTokenValid(String token, UserDetails userDetails){
            return extractUsername(token).equals(userDetails.getUsername());
    }
}

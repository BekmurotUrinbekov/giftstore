package uz.mygift.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.mygift.service.UserDetService;
import uz.mygift.service.UserService;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUteils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;

    @Value("${ref.lifetime}")
    private Duration refLifeTime;
    private final UserDetService userDetService;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims=new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles",rolesList);
        Date issuedDate=new Date();
        Date expiredDate=new Date(issuedDate.getTime()+jwtLifeTime.toMillis());
        String compact = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return compact;
    }
    public String generateTokenWithRefreshToken(String refreshToken){
        Claims claims = getAllClaimsFromToken(refreshToken);

        return generateToken(userDetService.loadUserByUsername(claims.get("username").toString()));
    }

    public String getUsername(String token){
        return getAllClaimsFromToken(token).getSubject();
    }


    public List<String > getRoles(String token){
        return getAllClaimsFromToken(token).get("roles",List.class);
    }


    private Claims getAllClaimsFromToken(String  token){
//        System.out.println("claim "+token);
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String refreshToken(String accessToken) {
        String username = getUsername(accessToken);
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + refLifeTime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    //    public boolean checkExpiredDate(String token){
//        Claims claims = getAllClaimsFromToken(token);
//        long expirationTimestamp = claims.getExpiration().getTime();
//
//        // Compare the expiration timestamp with the current time
//        return new Date().getTime() > expirationTimestamp;
//    }

    //    public boolean validateTokens(String accesToken,String refToken){
//        Claims allClaimsFromToken = getAllClaimsFromToken(refToken);
//        String token = allClaimsFromToken.get("token", String.class);
//        return accesToken.equals(token);
//    }
}

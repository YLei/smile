package com.emx.platform.utils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.emx.platform.vo.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private static final String TOKEN_PREFIX="prefix";
	private static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String secret = "w-oasis123456";
    private static final Long expiration = 604800L;

    /*@Value("${jwt.secret}")
    private String secret; //密钥

    @Value("${jwt.expiration}")
    private Long expiration; //过期时间
     */   
    public String getUserNameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        Boolean result= expiration.before(new Date());
        return result;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ACCOUNT, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUserNameFromToken(token);
        Boolean result= (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
        );
        return result;
    }

	/*public UserDTO parseToken(String token){
		try{
			Claims body=Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token.substring(TOKEN_PREFIX.length()))
					.getBody();
			UserDTO u=new UserDTO();
			u.setUsername(body.getSubject());
	        u.setId(1);
	        return u;
		}catch (JwtException e){
			return null;
		}
	}
	
	public String generateToken(HttpServletResponse response,UserDTO user) {
		Claims claims=Jwts.claims().setSubject(user.getUsername());
		claims.put("userId", user.getId() + "");
	    String token=TOKEN_PREFIX+Jwts.builder().setClaims(claims)
					.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
					.compact();
	    response.addHeader(HEADER, token);
		return token;
	}
	public static String generateToken(String userName){
		Claims claims=Jwts.claims().setSubject(userName);
		return TOKEN_PREFIX + Jwts.builder().setClaims(claims).
				signWith(SignatureAlgorithm.HS512, SECRET_KEY).
				compact();
	}
	*/
	
}

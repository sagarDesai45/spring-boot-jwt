package com.example.assignment.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private long jwtExpirationMs;
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
	
	 private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	    }
	
	
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName=getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date exp=getExpirationDateFromToken(token);
        return exp.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}

	public String getUserNameFromToken(String token)
	{
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return getClaimFromToken(token,Claims::getSubject);
	}
}



//@Component
//public class JwtUtils {
//
//	@Value("${jwt.secret}")
//	private String jwtSecret;
//
//	@Value("${app.jwtExpirationMs}")
//	private long jwtExpirationMs;
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .compact();
//    }
//
//    public Claims extractClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractClaims(token).getExpiration().before(new Date());
//    }
//
//    public boolean validateToken(String token, String username) {
//        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
//    }
//}


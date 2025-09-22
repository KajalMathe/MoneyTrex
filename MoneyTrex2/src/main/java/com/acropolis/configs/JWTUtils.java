package com.acropolis.configs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {

	@Autowired
	private UserDetailsService userDetailsService;

  private static final String SECRET_KEY = "14930665748bbc241f255a6b34ae325a1f5b08527152f6f38b36a817a40bca30";
  private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

  public  String generateToken(String username) 
  {
      Map<String, Object> claims = new HashMap<>();
      return Jwts.builder()
              .setClaims(claims)
              .setSubject(username)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
              .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
              .compact();
  }

  public boolean validateToken(String token) 
  {
          final Claims extractedClaims = extractAllClaims(token);

          UserDetails userDetails = userDetailsService.loadUserByUsername(extractedClaims.getSubject());

          if(userDetails!=null && !isTokenExpired(extractedClaims)){
              setAuthentication(userDetails);
              return true;
          } else {
              return false;
          }
  }

  public void setAuthentication(UserDetails userDetails)
  {
      // Set authentication in context
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authToken);
  }

  private Claims extractAllClaims(String token) {
      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(Claims claims) {
      return claims.getExpiration().before(new Date());
  }

}

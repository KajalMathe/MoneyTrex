package com.acropolis.configs;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.acropolis.util.APIResponse;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//JWTAuthFilter will execute for each request to check the authorization
@Component
public class JWTAuthFilter extends OncePerRequestFilter
{
	@Autowired
	private JWTUtils jwtUtils;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String uri = request.getRequestURI();
		return uri.startsWith("/money");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Gson gson = new Gson();
		final String authHeader = request.getHeader("Authorization");
		String jwt = null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) 
		{
			jwt = authHeader.substring(7);
			if(jwtUtils.validateToken(jwt)) 
			{
				filterChain.doFilter(request, response);
			}else 
			{
				APIResponse res = new APIResponse("Invalid or Expire Token", false,null);
				String str = gson.toJson(res);
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write(str);
			}
		}
		else 
		{
			APIResponse res = new APIResponse( "Token Not Found", false,null);
			String str = gson.toJson(res);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(str);
		}
	}
}

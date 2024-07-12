package com.example.assignment.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader=request.getHeader("Authorization");
		String user=null;
		String token=null;
		
		if(requestHeader !=null && requestHeader.startsWith("Bearer ")) {
			token=requestHeader.substring(7);
			 try {

	                user = this.jwtUtils.getUserNameFromToken(token);

	            } catch (IllegalArgumentException e) {
//	                logger.info("Illegal Argument while fetching the username !!");
	                e.printStackTrace();
	            } catch (ExpiredJwtException e) {
//	                logger.info("Given jwt token is expired !!");
	                e.printStackTrace();
	            } catch (MalformedJwtException e) {
//	                logger.info("Some changed has done in token !! Invalid Token");
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();

	            }
		}
		
		if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
            Boolean validateToken = this.jwtUtils.validateToken(token, userDetails);
            if(validateToken)
            {
            	 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authentication);
            }
		}
		
		 filterChain.doFilter(request, response);
		
	}

	
	
}



//
//public class JwtAuthTokenFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//	public  JwtAuthTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }
//
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            String username = jwtUtils.extractUsername(token);
//
//            if (username != null && jwtUtils.validateToken(token, username)) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
//                );
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}


package com.emx.platform.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emx.platform.utils.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	
	@Value("${jwt.header}")
    private String header;
	@Value("${jwt.tokenHead}")
    private String tokenHead;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
			, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader(header);
		if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length());
            String username = jwtUtil.getUserNameFromToken(authToken);
            logger.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication " + username);
            //token校验通过
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            	//根据username（用户名）查询user认证对象
            	UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("JwtAuthenticationTokenFilter[doFilterInternal]  authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
		}
		filterChain.doFilter(request, response);
	}
}

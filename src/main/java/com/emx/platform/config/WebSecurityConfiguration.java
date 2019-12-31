package com.emx.platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emx.platform.security.JwtAuthenticationTokenFilter;
import com.emx.platform.security.MyFilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired MyAuthenticationProvider authenticationProvider;
	@Autowired JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired MyAccessDeniedHandler accessDeniedHandler;
	@Autowired MyFilterSecurityInterceptor filterSecurityInterceptor;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/logout").permitAll()
        // swagger start
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/v2/api-docs").permitAll()
        .antMatchers("/configuration/ui").permitAll()
        .antMatchers("/configuration/security").permitAll()
        // swagger end
        // 除上面外的所有请求全部需要鉴权认证
        .anyRequest().authenticated()
        .and()
        .headers().cacheControl();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(authenticationProvider);
	}

	/**
	 * 装载BCrypt密码编码器
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}*/
	
	 @Bean
	 @Override 
	 public AuthenticationManager authenticationManagerBean() throws
	 Exception { return super.authenticationManagerBean(); }
}

package com.group.booking.Configs;

import com.group.booking.Common.Const;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenEntryPoint();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers().hasAnyAuthority(Const.ROLE_ADMIN); // role admin mới được sử dụng
        http.authorizeRequests().antMatchers().hasAnyAuthority(Const.ROLE_HOTEL); // role hotel mới được sử dụng
        http.authorizeRequests().antMatchers().hasAnyAuthority(Const.ROLE_EMPLOYEE); // role employee mới được sử dụng
        http.authorizeRequests().antMatchers().hasAnyAuthority(Const.ROLE_CUSTOMER); // role customer mới được dùng
        http.authorizeRequests().antMatchers().authenticated(); // tất cả role đếu được sử dụng (page update password)
        http.authorizeRequests().antMatchers(
            "/api/v1/users/signup"
        ).permitAll(); // ai cung duoc su dung

        // authorization exceptions
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        // authentication exceptions
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        // phai verify truoc de add roles for user details manage url
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

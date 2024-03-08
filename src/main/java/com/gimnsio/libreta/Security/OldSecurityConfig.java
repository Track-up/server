package com.gimnsio.libreta.Security;

import com.gimnsio.libreta.Security.filters.JwtAuthenticationFilter;
import com.gimnsio.libreta.Security.filters.JwtAuthorizationFilter;
import com.gimnsio.libreta.Security.jwt.JwtUtils;
import com.gimnsio.libreta.exception.CustomAuthenticationFailureHandler;
import com.gimnsio.libreta.user.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OldSecurityConfig {

    UserDetailsServiceImpl userDetailsService;

    JwtUtils jwtUtils;

    JwtAuthorizationFilter authorizationFilter;


    public OldSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils,
                             JwtAuthorizationFilter authorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.authorizationFilter = authorizationFilter;
    }
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .anyRequest().authenticated().and()
                .httpBasic();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
            throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils, userDetailsService);
        jwtAuthenticationFilter.setUserService(userDetailsService);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    // auth.requestMatchers("/doc/swagger-ui.html").permitAll();
                    auth.anyRequest().permitAll();// .authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
//     @Bean
//     UserDetailsService userDetailsService() {
//     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//     manager.createUser(User.withUsername("admin")
//     .roles()
//     .password("1234").build());
//     return manager;
//     }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();

    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
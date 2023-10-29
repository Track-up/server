package com.gimnsio.libreta.Security;

import com.gimnsio.libreta.Security.filters.JwtAuthenticationFilter;
import com.gimnsio.libreta.Security.filters.JwtAuthorizationFilter;
import com.gimnsio.libreta.Security.jwt.JwtUtils;
import com.gimnsio.libreta.exception.CustomAuthenticationFailureHandler;
import com.gimnsio.libreta.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsServiceImpl userDetailsService;

    JwtUtils jwtUtils;

    JwtAuthorizationFilter authorizationFilter;


    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils,
            JwtAuthorizationFilter authorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.authorizationFilter = authorizationFilter;
    }

    // no esta mal pero dice que mejor otra
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
    // Exception {
    // return httpSecurity
    // //.csrf().disable() // Cross-Site Request Forgery TODO minuto 25
    // https://www.youtube.com/watch?v=pmSJTrOWi7w&ab_channel=UnProgramadorNace sino
    // buscar + info. hace falta?
    // .authorizeHttpRequests()
    // .requestMatchers("/exercises").permitAll()
    // .anyRequest().authenticated()
    // .and()
    // .formLogin().permitAll()
    // .and()
    // .build();
    // }

    // //El numero 2 del mismo video de arriba
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
    // Exception {
    // return httpSecurity
    // .authorizeHttpRequests(auth -> {
    // auth.requestMatchers("/exercises").permitAll();
    // auth.anyRequest().authenticated();
    // })
    // .formLogin().permitAll()
    // .and()
    // .sessionManagement()
    // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    // .invalidSessionUrl("/login")
    // .maximumSessions(1)
    // .expiredUrl("/login")
    // .sessionRegistry(sessionRegistry())
    // .and()
    // .sessionFixation()
    // .migrateSession()
    // .and()
    // .build();
    // }
    //
    // @Bean
    // public SessionRegistry sessionRegistry(){
    // return new SessionRegistryImpl();
    // }


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

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setUserService(userDetailsService);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .cors(cors -> cors.disable())
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

    // @Bean
    // UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // manager.createUser(User.withUsername("admin")
    // .roles()
    // .password("1234").build());
    // return manager;
    // }

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

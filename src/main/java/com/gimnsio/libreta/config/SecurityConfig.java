//package com.gimnsio.libreta.config;
//
//import com.gimnsio.libreta.Security.filters.JwtAuthenticationFilter;
//import com.gimnsio.libreta.Security.filters.JwtAuthorizationFilter;
//import com.gimnsio.libreta.exception.CustomAuthenticationFailureHandler;
//import com.gimnsio.libreta.user.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    private JwtAuthorizationFilter authorizationFilter;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Value("${frontend.url}")
//    private String frontendUrl;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(authorizeRequests -> {
//                    authorizeRequests.requestMatchers("/v3/api-docs/**", "/doc/swagger-ui/**", "/swagger-ui.html").permitAll();
//                    authorizeRequests.anyRequest().authenticated();
////                }).oauth2Login(oauth2 -> {
////                    oauth2.successHandler(oAuth2LoginSuccessHandler);
//                })
//                .sessionManagement(session -> {
//                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//                })
//                .addFilter(jwtAuthenticationFilter)
//                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of(frontendUrl, "http://localhost:3000"));
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return urlBasedCorsConfigurationSource;
//    }
//
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
//            throws Exception {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder)
//                .and().build();
//
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//}

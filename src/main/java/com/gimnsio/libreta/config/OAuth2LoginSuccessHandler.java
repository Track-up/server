//package com.gimnsio.libreta.config;
//
//import com.gimnsio.libreta.persistence.entities.UserRole;
//import com.gimnsio.libreta.user.persistence.Provider;
//import com.gimnsio.libreta.user.persistence.UserEntity;
//import com.gimnsio.libreta.user.service.UserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Component
//public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    private final UserService userService;
//
//    @Value("${frontend.url}")
//    private String frontendUrl;
//
//    public OAuth2LoginSuccessHandler(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//
//        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
//        if ("google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
//            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
//            Map<String, Object> attributes = principal.getAttributes();
//            String email = attributes.getOrDefault("email", "").toString();
//            String name = attributes.getOrDefault("name", "").toString();
//            userService.findByEmail(email)
//                    .ifPresentOrElse(user -> {
//                        DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRoles().toString())),//TODO En teoria no
//                                attributes, "name");
//                        Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(user.getRoles().toString())),//TODO En teoria no
//                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
//                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
//                    }, () -> {
//                        UserEntity userEntity = new UserEntity();
//                        userEntity.setRoles(Set.of(UserRole.USER));
//                        userEntity.setEmail(email);
//                        userEntity.setName(name);
//                        userEntity.setProvider(Provider.GOOGLE);
//                        userEntity.setImage(attributes.getOrDefault("picture", "").toString());
//                        userEntity.setCreatedAt(java.time.LocalDateTime.now());
//                        userService.save(userEntity);
//                        DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(UserRole.USER.toString())),
//                                attributes, "name");
//                        Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(UserRole.USER.toString())),
//                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
//                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
//                    });
//        }
//
//        this.setAlwaysUseDefaultTargetUrl(true);
//        this.setDefaultTargetUrl(frontendUrl);
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}

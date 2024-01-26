package com.gimnsio.libreta.Security.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimnsio.libreta.DTO.users.UserIdDTO;
import com.gimnsio.libreta.Security.jwt.JwtUtils;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import com.gimnsio.libreta.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private JwtUtils jwtUtils;


    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils ){
        this.jwtUtils = jwtUtils;

    }

    public void setUserService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UserEntity userEntity = null;
        String username = "";
        String password = "";
        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();

        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);

        return authentication;
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        //TODO https://www.codejava.net/frameworks/spring-boot/spring-security-before-authentication-filter-examples
        
        // Crear un mensaje de error personalizado
//        String errorMessage = "Autenticación fallida: Usuario o contraseña incorrectos.";

        // Construir la respuesta con ResponseEntity
//        ResponseEntity<Object> responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);

//        // Escribir el mensaje en el cuerpo de la respuesta
//        response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        // Configurar el código de estado HTTP apropiado
//        response.setStatus(responseEntity.getStatusCodeValue());
//        response.getWriter().flush();

        String errorMessage = "Autenticación fallida: Usuario o contraseña incorrectos.";

        if (failed instanceof BadCredentialsException) {
            errorMessage = "Credenciales inválidas. Por favor, verifica tu nombre de usuario y contraseña.";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"message\": \"" + errorMessage + "\"}");
        response.getWriter().flush();


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername());
        UserIdDTO userIdDTO = userDetailsService.getUserIdDTOByUsername(user.getUsername());

        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("id",userIdDTO.getId());
        httpResponse.put("token", token);
        httpResponse.put("message","Autentificación correcta");
        httpResponse.put("username", user.getUsername());
        httpResponse.put("image", userIdDTO.getImage());
        httpResponse.put("lenguage","es");

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}

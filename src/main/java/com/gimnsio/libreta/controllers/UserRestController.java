package com.gimnsio.libreta.controllers;


import com.gimnsio.libreta.DTO.users.UserRegistryDTO;
import com.gimnsio.libreta.DTO.users.UserUpdateDTO;
import com.gimnsio.libreta.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Users API")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAllUsers(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistryDTO userRegistryDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            return new ResponseEntity<>(new Message(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
//        }
//
//        return ResponseEntity.ok(usersService.createUser(userRegistryDTO));
//    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistryDTO userRegistryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            Map<String, Object> httpResponse = new HashMap<>();
            httpResponse.put("message", errors.toString().substring(1, errors.toString().length() - 1));
            return ResponseEntity.badRequest().body(httpResponse);
        }
        return userService.createUser(userRegistryDTO);


    }

    //    private void authenticateUserAndSetSession(String username, String password) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        // Configurar la autenticación
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Configurar la sesión
//        HttpSession session = request.getSession(true);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//    }
    @PutMapping
    public ResponseEntity<?> editUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }

//    @PutMapping("/{id}/change_stats")
//    public ResponseEntity<?> changeStats(@RequestBody MeasuresDTO measuresDTO, @PathVariable Long id) {
//        return ResponseEntity.ok(userService.updateUserStats(id, measuresDTO));
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

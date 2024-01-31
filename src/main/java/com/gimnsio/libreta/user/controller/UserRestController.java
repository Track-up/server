package com.gimnsio.libreta.user.controller;


import com.gimnsio.libreta.user.dto.UserRegistryDTO;
import com.gimnsio.libreta.user.dto.UserUpdateDTO;
import com.gimnsio.libreta.user.service.UserService;
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
@Tag(name = "Usuarios", description = "CRUD de usurarios")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/entities")
    public ResponseEntity<?>getUserEntities(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAllUsersEntities(pageable));

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
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(id,userUpdateDTO));
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

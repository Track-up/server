package com.gimnsio.libreta.controllers;


import com.gimnsio.libreta.services.UsersService;
import com.gimnsio.libreta.users.UserDTO;
import com.gimnsio.libreta.users.UserRegistryDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "CRUD de usurarios")
public class UserRestController {

    private UsersService usersService;

    public UserRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(this.usersService.getAllUsers(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistryDTO userRegistryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {//TODO Devuelve string
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return usersService.createUser(userRegistryDTO);


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
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return ResponseEntity.ok(usersService.updateUser(id, userDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
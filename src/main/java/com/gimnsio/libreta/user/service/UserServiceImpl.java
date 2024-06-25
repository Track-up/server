package com.gimnsio.libreta.user.service;

import com.gimnsio.libreta.authority.persistence.RoleEntity;
import com.gimnsio.libreta.authority.service.RoleService;
import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.services.MeasuresService;
import com.gimnsio.libreta.user.dto.UserBasicsDTO;
import com.gimnsio.libreta.user.dto.UserDTO;
import com.gimnsio.libreta.user.dto.UserRegistryDTO;
import com.gimnsio.libreta.user.dto.UserUpdateDTO;
import com.gimnsio.libreta.user.mapper.UserMapper;
import com.gimnsio.libreta.user.persistence.Provider;
import com.gimnsio.libreta.user.persistence.UserEntity;
import com.gimnsio.libreta.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${url.backend}")
//    @Value("${url.local}")
    private String urlBase;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MeasuresService measuresService;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Map<String, String> tokenRepository = new HashMap<>();


    @Override
    public UserDTO getUserById(long id) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return userMapper.mapUserDTO(userEntityOptional.get());
        } else {
            throw new ApiRequestException("No se encontró el usuario con el ID: " + id);
        }


    }

    @Override
    public UserEntity getUserEntityById(long id) {
        return findUser(id);
    }

    private UserEntity findUser(long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else {
            throw new ApiRequestException("No se encontró el usuario con el ID: " + id);
        }
    }

    @Override
    public Set<UserBasicsDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(userEntity -> {
                    return userMapper.userEntityToUserBasicsDTO(userEntity);
                }).collect(Collectors.toSet());
    }


    public ResponseEntity<?> createUser(UserRegistryDTO userRegistryDTO) {
        UserEntity userEntity = userMapper.userRegistryDTOToUserEntity(userRegistryDTO);

//        userEntity.setDateOfCreation(new Date());
        userEntity.setCreatedAt(java.time.LocalDateTime.now());
        userEntity.setProvider(Provider.LOCAL);
        userEntity.setRoles(new HashSet<>(Collections.singletonList(roleService.findByID(1L))));
        userEntity.setEnabled(true);
        userEntity.setAccountNoExpired(true);
        userEntity.setAccountNoLocked(true);
        userEntity.setCredentialNoExpired(true);


        try {
            UserEntity userCreated = userRepository.save(userEntity);
            measuresService.createMeasures(userCreated);
            return ResponseEntity.ok(userCreated);
        } catch (DataIntegrityViolationException e) {
            String errorMessage;
            if (e.getCause().getCause().getMessage().contains("username")) {
                errorMessage = "El nombre de usuario ya está en uso. Por favor, elige otro.";
            } else if (e.getCause().getCause().getMessage().contains("email")) {
                errorMessage = "El correo electrónico ya está en uso. Por favor, elige otro.";
            } else {
                errorMessage = "Error de duplicación en la base de datos. Por favor, verifica los campos.";
            }

            Map<String, Object> httpResponse = new HashMap<>();
            httpResponse.put("message", errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpResponse);
        }
    }

    private ResponseEntity<String> getLogin(UserRegistryDTO userRegistryDTO) {

        String url = urlBase.concat("/login"); // TODO Cambia la URL y el puerto según tu configuración
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Cambia el tipo de contenido a JSON

        // Crea un objeto para enviar como JSON en el cuerpo de la solicitud
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("username", userRegistryDTO.getUsername());
        requestMap.put("password", userRegistryDTO.getPassword());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestMap, headers);

        // Realiza la solicitud POST para autenticarte
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return response;
    }

    @Override
    public UserUpdateDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        UserEntity userEntity = findUser(id);
        if (userUpdateDTO.getUsername() != null) {
            userEntity.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().isEmpty()) {
            userEntity.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getImage() != null) {
            userEntity.setImage(userUpdateDTO.getImage());
        }

        userRepository.save(userEntity);
        return userUpdateDTO;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(findUser(id));
    }

    @Override
    public List<UserEntity> getAllUsersEntities(Pageable pageable) {
        return userRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void createPasswordResetTokenForUser(String email, String token) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("No se encontró el usuario con el correo electrónico: " + email));
        tokenRepository.put(email, token);
    }

    @Override
    public void updatePassword(String email, String newPassword, String token) {
        if (tokenRepository.containsKey(email) && tokenRepository.get(email).equals(token)) {
            UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("No se encontró el usuario con el correo electrónico: " + email));
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
            tokenRepository.remove(email);
        } else {
            throw new ApiRequestException("El token no es válido o ha expirado.");
        }
    }

//    @Override
//    public MeasuresDTO updateUserStats(Long id, MeasuresDTO measuresDTO) {
//
//        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
//        if (userEntityOptional.isPresent()){
//            UserEntity userEntity = userEntityOptional.get();
//
//            userEntity.setStats(statsService.createStats(measuresDTO));
//            userEntity.getStats().setUser(userEntity);// is this a chapuza en toda regla?
//            userRepository.save(userEntity);
//            return measuresDTO;
//        }else throw new NoSuchElementException("No se encontró el usuario con el ID: " + id);
//
//
//    }

}

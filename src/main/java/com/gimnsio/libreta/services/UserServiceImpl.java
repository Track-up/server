package com.gimnsio.libreta.services;

import com.gimnsio.libreta.DTO.users.UserBasicsDTO;
import com.gimnsio.libreta.DTO.users.UserDTO;
import com.gimnsio.libreta.DTO.users.UserRegistryDTO;
import com.gimnsio.libreta.Mapper.UserMapper;
import com.gimnsio.libreta.exception.ApiRequestException;
import com.gimnsio.libreta.persistence.entities.UserEntity;
import com.gimnsio.libreta.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${url.backend}")
    private String urlBase;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;

//    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RestTemplate restTemplate) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//        this.restTemplate = restTemplate;
//    }


    @Override
    public UserDTO getUserById(long id) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            return userMapper.mapUserDTO(userEntityOptional.get());
        }else {
            throw new ApiRequestException("No se encontró el usuario con el ID: " + id);
        }


    }

    @Override
    public UserEntity getUserEntityById(long id) {
        return findUser(id);
    }
    private UserEntity findUser(long id){
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }else {
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

        userEntity.setDateOfCreation(new Date());
        try {
            userRepository.save(userEntity);
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
        ResponseEntity<String> response = getLogin(userRegistryDTO);

        return response;
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
    public ResponseEntity<?> updateUser(long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(findUser(id));
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


//    @Override
//    public ResponseEntity<?> updateUser(long id, UserE userE) {
//
//        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
//
//        if (userEntityOptional.isPresent()) {
//            UserEntity userEntity = userEntityOptional.get();
//            userEntity = userMapper.mapUserEntity(userE);
//            userEntity.setId(id);//TODO Cambiar chapuza
//            return userMapper.mapUserOld(userRepository.save(userEntity));
//        } else {
//            throw new NoSuchElementException("No se encontró el usuario con el ID: " + id);
//        }
//    }

//    @Override
//    public ResponseEntity<?> deleteUser(long id) {
//        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
//
//        if (userEntityOptional.isPresent()) {
//            userRepository.deleteById(id);
//        } else {
//            throw new NoSuchElementException("No se encontró el usuario con el ID: " + id);
//        }
//    }
}

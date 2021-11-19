package ru.netology.netologydiplombackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.netology.netologydiplombackend.config.jwt.JwtUtils;
import ru.netology.netologydiplombackend.model.User;
import ru.netology.netologydiplombackend.pojo.JwtResponse;
import ru.netology.netologydiplombackend.pojo.Request;
import ru.netology.netologydiplombackend.repository.FilesRepository;
import ru.netology.netologydiplombackend.repository.UserRepository;
import ru.netology.netologydiplombackend.service.UserDetailsImpl;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final FilesRepository filesRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, FilesRepository filesRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.filesRepository = filesRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> registerUser(@RequestBody Request request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //TODO добавить проверку пароля
        if (userRepository.existsByLogin(request.getLogin())) {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getLogin()));
        }
        User user = new User(request.getLogin(),
                passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getLogin()));

    }


}

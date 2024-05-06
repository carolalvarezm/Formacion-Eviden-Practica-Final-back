package es.a926666.proyectofinal.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.a926666.proyectofinal.user.Role;
import es.a926666.proyectofinal.user.User;
import es.a926666.proyectofinal.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public void login(LoginRequest request) {
        
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
        .username(request.getUsername())
        .password(request.getPassword())
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .rol(Role.user)
        .build();
        userRepository.save(user);
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
}

}

package com.getsu.chatop.domain.service;

import com.getsu.chatop.application.auth.models.LoginRequest;
import com.getsu.chatop.application.auth.models.RegisterRequest;
import com.getsu.chatop.application.auth.models.UserResponse;
import com.getsu.chatop.infrastructure.models.User;
import com.getsu.chatop.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());

        return userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Aucun utilisateur existe avec cet email ");
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return user;
        }
        throw new IllegalArgumentException("Mot de passe ou email incorrect");
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Utilisateur introuvable");
        }
        return convertToResponse(user);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated_at(user.getCreatedAt());
        response.setUpdated_at(user.getUpdatedAt());
        return response;
    }

}

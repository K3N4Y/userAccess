package kenayperez.useraccess.Service;

import kenayperez.useraccess.Repository.UserRepository;
import kenayperez.useraccess.Repository.RoleRepository;
import kenayperez.useraccess.dto.UserDTO;
import kenayperez.useraccess.entities.User;
import kenayperez.useraccess.entities.Role;
import kenayperez.useraccess.error.EmailAlreadyExistException;
import kenayperez.useraccess.error.PasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("User with this email already exists");
        }

        if (user.getPasswordHash().length() < 8 || user.getPasswordHash().length() > 20) {
            throw new PasswordInvalidException("Password has to be between 8 and 20 characters");
        }

        // Asignar Rol por defecto (ROLE_USER)
        // Esto ignora cualquier rol que el usuario haya enviado en el JSON (Seguridad)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        user.setRoles(Collections.singleton(userRole));

        // Encriptar contraseña y guardar
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);

        return new UserDTO(
                user.getEmail(),
                user.getUsername());
    }
}

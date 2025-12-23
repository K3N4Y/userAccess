package kenayperez.useraccess.Service;

import kenayperez.useraccess.Repository.UserRepository;
import kenayperez.useraccess.dto.UserDTO;
import kenayperez.useraccess.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return null;
        }
        if (user.getPasswordHash().length() < 8 || user.getPasswordHash().length() > 20) {
            return null;
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
        return new UserDTO(
                user.getEmail(),
                user.getUsername()
        );
    }
}

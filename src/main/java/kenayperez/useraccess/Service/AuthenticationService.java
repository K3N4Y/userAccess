package kenayperez.useraccess.Service;

import kenayperez.useraccess.security.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String username, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);

}

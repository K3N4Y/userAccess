package kenayperez.useraccess.Repository;

import kenayperez.useraccess.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {

    Optional<User> findByEmail(String email);
}

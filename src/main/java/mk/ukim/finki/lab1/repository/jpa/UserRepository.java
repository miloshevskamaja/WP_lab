package mk.ukim.finki.lab1.repository.jpa;

import mk.ukim.finki.lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}

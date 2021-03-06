package mobile.repo;


import mobile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface Userrepo extends JpaRepository<User, Long> {
    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);

    User findUserByEmail(String email);

   User findUserByEmailAndMotdepasse(String email, String password);
}

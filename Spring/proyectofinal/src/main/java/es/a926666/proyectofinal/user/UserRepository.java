package es.a926666.proyectofinal.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(Integer id);
    List<User> findAll();
    Optional<User> findByUsername(String username);
}

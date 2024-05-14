package es.a926666.proyectofinal.serie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SerieRepository extends JpaRepository<Serie,Integer>{
    Optional<Serie> findById(Integer id);
    List<Serie> findAll();
    Optional<Serie> findByName(String name);
}

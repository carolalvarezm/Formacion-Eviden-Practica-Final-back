package es.a926666.proyectofinal.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer>{
    Optional<Brand> findById(Integer id);
    List<Brand> findAll();
    Optional<Brand> findByName(String name);
}

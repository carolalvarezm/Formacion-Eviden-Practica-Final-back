package es.a926666.proyectofinal.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    Optional<Product> findById(Integer id);
    List<Product> findAll();
    Optional<Product> findByName(String name);
}

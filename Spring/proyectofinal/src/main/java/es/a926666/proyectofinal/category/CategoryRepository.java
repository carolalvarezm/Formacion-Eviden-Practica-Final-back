package es.a926666.proyectofinal.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Optional<Category> findById(Integer id);
    List<Category> findCategoriesBy();
    Optional<Category> findByName(String name);
}

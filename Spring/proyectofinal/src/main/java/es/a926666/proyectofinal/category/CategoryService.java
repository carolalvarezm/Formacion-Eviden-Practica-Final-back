package es.a926666.proyectofinal.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> getAllCategories() {
        List<CategoryDTO> categories = categoryRepository.findCategoriesBy();
        if(categories.size()>0){
            return ResponseEntity.ok(categories);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getCategoryById(Integer id) {
        Optional<CategoryDTO> category = categoryRepository.findBy(id);
        if(category.isPresent()){
            return ResponseEntity.ok(category);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createCategory(Category categoryNew) {
        try {
            Optional<CategoryDTO> category = categoryRepository.findByName(categoryNew.getName());
            if(category.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                categoryRepository.save(categoryNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la categoría con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateCategory(Integer id, Category categoryNew) {
        Optional<CategoryDTO> category = categoryRepository.findBy(id);
        if(category.isPresent()){
            categoryNew.setId(id);
            categoryRepository.save(categoryNew);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la categoría a modificar");
        }

    }

    public ResponseEntity<?>  deleteCategory(Integer id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()){
                categoryRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

}
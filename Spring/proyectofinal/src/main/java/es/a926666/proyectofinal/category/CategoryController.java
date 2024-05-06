package es.a926666.proyectofinal.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategoriess() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>  getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>  createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.updateCategory(id,category);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteCategory(@PathVariable Integer id) { 
        return categoryService.deleteCategory(id);
    }
}

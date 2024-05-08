package es.a926666.proyectofinal.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>  getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>  createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id,product);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteProduct(@PathVariable Integer id) { 
        return productService.deleteProduct(id);
    }
}

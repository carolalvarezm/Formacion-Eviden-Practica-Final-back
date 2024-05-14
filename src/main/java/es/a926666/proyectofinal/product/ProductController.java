package es.a926666.proyectofinal.product;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.a926666.proyectofinal.category.Category;
import org.springframework.web.bind.annotation.RequestParam;



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
    @GetMapping("images/{filename}")
    public byte[] getMethodName(@PathVariable String filename) {
        System.out.println(filename);
        return productService.getProductImage(filename);
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createProduct(@RequestPart Product producto ,@RequestPart("file") MultipartFile fichero) {
        return productService.createProduct(producto,fichero);
    }
    @PutMapping("/{id}/categories")
    public ResponseEntity<?>  addCategorytoProduct(@PathVariable Integer id,@RequestBody List<Category> categories) {
        return productService.addListofCategorytoProduct(id,categories);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateProduct(@PathVariable Integer id, @RequestPart Product producto,@RequestPart("file") MultipartFile fichero)  {
        return productService.updateProduct(id,producto,fichero);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteProduct(@PathVariable Integer id) { 
        return productService.deleteProduct(id);
    }
}

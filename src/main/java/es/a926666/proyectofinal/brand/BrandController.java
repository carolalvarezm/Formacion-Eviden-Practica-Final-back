package es.a926666.proyectofinal.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {
    
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getAllBrands() {
        return brandService.getAllBrands();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>  getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }
    @GetMapping("/{id}/series")
    public ResponseEntity<?>  getSeriesByBrandId(@PathVariable Integer id) {
        return brandService.getSeriesByBrandId(id);
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<?>  getProductsByBrandId(@PathVariable Integer id) {
        return brandService.getProductsByBrand(id);
    }
    @PostMapping("")
    public ResponseEntity<?>  createBrand(@RequestPart Brand brand ,@RequestPart("file") MultipartFile fichero) {
        return brandService.createBrand(brand,fichero);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateBrand(@PathVariable Integer id, @RequestPart Brand brand ,@RequestPart("file") MultipartFile fichero) {
        return brandService.updateBrand(id,brand,fichero);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteBrand(@PathVariable Integer id) { 
        return brandService.deleteBrand(id);
    }
    
}

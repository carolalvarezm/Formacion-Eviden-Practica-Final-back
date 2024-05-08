package es.a926666.proyectofinal.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.proyectofinal.brand.BrandDTO;
import es.a926666.proyectofinal.brand.BrandService;
import es.a926666.proyectofinal.category.Category;
import es.a926666.proyectofinal.category.CategoryDTO;
import es.a926666.proyectofinal.category.CategoryService;
import es.a926666.proyectofinal.serie.SerieDTOWB;
import es.a926666.proyectofinal.serie.SerieService;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired 
    private SerieService serieService;
    @Autowired 
    private BrandService brandService;

    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs= this.translateListToDTO(products);
        if(products.size()>0){
            return ResponseEntity.ok(productDTOs);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        
        if(product.isPresent()){
            ProductDTO productDTO=this.translateToDTO(product.get());
            return ResponseEntity.ok(productDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createProduct(Product productNew) {
        try {
            Optional<Product> product = productRepository.findByName(productNew.getName());
            if(product.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                productRepository.save(productNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la marca con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateProduct(Integer id, Product productNew) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productNew.setId(id);
            productRepository.save(productNew);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
        }

    }

    public ResponseEntity<?>  deleteProduct(Integer id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                productRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }
    public ProductDTO translateToDTO(Product product){
        List<CategoryDTO> categories=categoryService.translateListToDTO(product.getCategories());
        SerieDTOWB serie=serieService.translateToDTOwithoutBrand(product.getSerie());
        BrandDTO brand=brandService.translateToDTO(product.getBrand());
        ProductDTO productDTO= new ProductDTO(product.getId(),product.getName(), product.getImage(), product.getDescription(), serie,brand,categories);
        return productDTO;
    }
    public List<ProductDTO> translateListToDTO(List<Product> products){
        List<ProductDTO> productsDtos=new ArrayList<ProductDTO>();
        for (Product product : products) {
            productsDtos.add(this.translateToDTO(product));
        }
        return productsDtos;
    }

    public ResponseEntity<?> addListofCategorytoProduct(Integer id, List<Category> categories) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                product.get().setCategories(categories);
                productRepository.save(product.get());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se ha modificado con éxito");
            }
            else{
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el producto a modificar");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

}

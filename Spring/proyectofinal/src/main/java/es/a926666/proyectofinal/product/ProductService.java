package es.a926666.proyectofinal.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> products = productRepository.findProductsBy();
        if(products.size()>0){
            return ResponseEntity.ok(products);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getProductById(Integer id) {
        Optional<ProductDTO> product = productRepository.findBy(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createProduct(Product productNew) {
        try {
            Optional<ProductDTO> product = productRepository.findByName(productNew.getName());
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
        Optional<ProductDTO> product = productRepository.findBy(id);
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

}

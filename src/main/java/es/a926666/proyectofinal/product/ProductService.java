package es.a926666.proyectofinal.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import es.a926666.proyectofinal.images.ImagesService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.a926666.proyectofinal.category.Category;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private static final String Path="imagenes\\productos";
    @Autowired
    private ImagesService imageService;

    public ResponseEntity<?> getAllProducts() {
        try{
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs= this.translateListToDTO(products);
            if(products.size()>0){
                return ResponseEntity.ok(productDTOs);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }catch(Exception  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde " + e);
        }
    }

    public ResponseEntity<?>  getProductById(Integer id) {
        try{
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                ProductDTO productDTO=this.translateToDTO(product.get());
                return ResponseEntity.ok(productDTO);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }catch(Exception  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde " + e);
        }

    }

    public ResponseEntity<?>  createProduct(Product productNew, MultipartFile fichero) {
        try {
            Optional<Product> product = productRepository.findByName(productNew.getName());
            if(product.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                imageService.saveImage(fichero,Path);
                productNew.setImage(fichero.getOriginalFilename());
                productRepository.save(productNew);
                Optional<Product> producto = productRepository.findByName(productNew.getName());
                System.out.println(producto.get().getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(producto.get().getId());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }
    public ResponseEntity<?>  updateProduct(Integer id, Product productNew,MultipartFile fichero) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                imageService.saveImage(fichero,Path);
                productNew.setImage(fichero.getOriginalFilename());
                productNew.setId(id);
                productRepository.save(productNew);
                return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
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
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde ");
        }
    }
    public ProductDTO translateToDTO(Product product) throws IOException{
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;    
    }
    public List<ProductDTO> translateListToDTO(List<Product> products) throws IOException{
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

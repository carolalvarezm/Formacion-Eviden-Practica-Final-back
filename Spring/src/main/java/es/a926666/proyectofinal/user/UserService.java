package es.a926666.proyectofinal.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.proyectofinal.product.Product;
import es.a926666.proyectofinal.product.ProductDTO;
import es.a926666.proyectofinal.product.ProductRepository;
import es.a926666.proyectofinal.product.ProductService;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.size()>0){
            return ResponseEntity.ok(users);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createUser(User userNew) {
        try {
            Optional<User> user = userRepository.findByUsername(userNew.getUsername());
            if(user.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                userRepository.save(userNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la marca con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateUser(Integer id, User userNew) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userNew.setId(id);
            userRepository.save(userNew);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
        }

    }

    public ResponseEntity<?>  deleteUser(Integer id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                userRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            UserDTO userDto=new UserDTO(user.get().getUsername(),user.get().getFirstname(),user.get().getLastname(),user.get().getEmail());
            return ResponseEntity.ok(userDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?> getProductsByUsername(String username) {
        try{
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isPresent()){
                List<ProductDTO> productsDTO= productService.translateListToDTO(user.get().getProducts());
                return ResponseEntity.ok(productsDTO);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }catch(Exception e){
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde ");

        }

    }
    public ResponseEntity<?> addProductsByUsername(String username,Integer idProducto) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Product> product = productRepository.findById(idProducto);
        if(user.isPresent()&&product.isPresent()){
            user.get().addProduct(product.get());
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body("Se ha añadido correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?> deleteProductsByUsername(String username, Integer idProducto) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Product> product = productRepository.findById(idProducto);
        if(user.isPresent()&&product.isPresent()){
            user.get().removeProduct(product.get());
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body("Se ha añadido correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }
}

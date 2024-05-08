package es.a926666.proyectofinal.user;

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
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
  
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{username}")
    public ResponseEntity<?>  getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    @GetMapping("/{username}/products")
    public ResponseEntity<?>  getProductsByUsername(@PathVariable String username) {
        return userService.getProductsByUsername(username);
    }
    @PostMapping("")
    public ResponseEntity<?>  createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id,user);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteUser(@PathVariable Integer id) { 
        return userService.deleteUser(id);
    }
    
}

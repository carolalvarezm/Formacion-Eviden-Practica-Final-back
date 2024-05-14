package es.a926666.proyectofinal.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.proyectofinal.product.Product;
import es.a926666.proyectofinal.product.ProductRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDtos=this.translateListToDTO(categories);
        if(categories.size()>0){
            return ResponseEntity.ok(categoriesDtos);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            CategoryDTO categoryDTO=this.translateToDTO(category.get());
            return ResponseEntity.ok(categoryDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createCategory(Category categoryNew) {
        try {
            Optional<Category> category = categoryRepository.findByName(categoryNew.getName());
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
        Optional<Category> category = categoryRepository.findById(id);
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
                
                for(Product p:category.get().getProducts()){
                    p.removeCategory(category.get());
                    productRepository.save(p);
                }
                
                categoryRepository.save(category.get());
                System.out.println(category.get().toString());
                categoryRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }
    public CategoryDTO translateToDTO(Category category){
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
    public List<CategoryDTO> translateListToDTO(List<Category> categories){
        List<CategoryDTO> categoriesDtos=new ArrayList<CategoryDTO>();
        for (Category category : categories) {
            categoriesDtos.add(this.translateToDTO(category));
        }
        return categoriesDtos;
    }

}

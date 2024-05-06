package es.a926666.proyectofinal.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public ResponseEntity<?> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if(brands.size()>0){
            return ResponseEntity.ok(brands);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getBrandById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isPresent()){
            return ResponseEntity.ok(brand);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createBrand(Brand brandNew) {
        try {
            Optional<Brand> brand = brandRepository.findByName(brandNew.getName());
            if(brand.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                brandRepository.save(brandNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la marca con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateBrand(Integer id, Brand brandNew) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isPresent()){
            brandNew.setId(id);
            brandRepository.save(brandNew);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
        }

    }

    public ResponseEntity<?>  deleteBrand(Integer id) {
        try {
            Optional<Brand> brand = brandRepository.findById(id);
            if(brand.isPresent()){
                brandRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

}

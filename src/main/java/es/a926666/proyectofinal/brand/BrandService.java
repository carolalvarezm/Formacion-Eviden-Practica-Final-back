package es.a926666.proyectofinal.brand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import es.a926666.proyectofinal.images.ImagesService;
import es.a926666.proyectofinal.product.Product;
import es.a926666.proyectofinal.product.ProductDTO;

import es.a926666.proyectofinal.serie.SerieDTOWB;





@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    private static final String Path="imagenes\\marcas";
    @Autowired
    private ImagesService imageService;
    @Autowired
    private es.a926666.proyectofinal.serie.SerieService SerieService;
    public ResponseEntity<?> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandDTO> brandsDtos=new ArrayList<BrandDTO>();
        for (Brand brand : brands) {
            brandsDtos.add(this.translateToDTO(brand));
        }
        if(brands.size()>0){
            return ResponseEntity.ok(brandsDtos);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getBrandById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isPresent()){
        BrandDTO brandDto=this.translateToDTO(brand.get());
            return ResponseEntity.ok(brandDto);
        }
        if(brand.isPresent()){
            return ResponseEntity.ok(brand);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }
    public ResponseEntity<?>  getSeriesByBrandId(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isPresent()){
        List<SerieDTOWB> seriesDto=SerieService.translateListToDTOWB(brand.get().getSeries());
            return ResponseEntity.ok(seriesDto);
        }
        if(brand.isPresent()){
            return ResponseEntity.ok(brand);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }
    


    public ResponseEntity<?>  createBrand(Brand brandNew, MultipartFile fichero) {
        try {
            Optional<Brand> brand = brandRepository.findByName(brandNew.getName());
            if(brand.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                imageService.saveImage(fichero,Path);
                brandNew.setImage(fichero.getOriginalFilename());
                brandRepository.save(brandNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la marca con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateBrand(Integer id, Brand brandNew, MultipartFile fichero) {
        try{
            Optional<Brand> brand = brandRepository.findById(id);
            if(brand.isPresent()){
                imageService.saveImage(fichero,Path);
                brandNew.setImage(fichero.getOriginalFilename());
                brandNew.setId(id);
                brandRepository.save(brandNew);
                return ResponseEntity.ok("Se ha modificado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
        

    }
    public ResponseEntity<?> getProductsByBrand(Integer id) {
        try{
            Optional<Brand> brand = this.findById(id);
            if(brand.isPresent()){
                List<ProductDTO> productsDTO= this.translateListToDTO(brand.get().getProducts());
                return ResponseEntity.ok(productsDTO);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }catch(Exception e){
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde "+ e);
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

        public Optional<Brand> findById(Integer id){
            return brandRepository.findById(id);
        }
        public BrandDTO translateToDTO(Brand brand){
            ModelMapper modelMapper = new ModelMapper();
            BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
            return brandDTO;}
            
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


}

package es.a926666.proyectofinal.serie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import es.a926666.proyectofinal.product.ProductDTO;
import es.a926666.proyectofinal.product.ProductService;





@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private ProductService productService;

    public ResponseEntity<?> getAllSeries() {
        List<Serie> series = serieRepository.findAll();
        if(series.size()>0){
            List<SerieDTO> seriesDTO = this.translateListToDTO(series);
            return ResponseEntity.ok(seriesDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  getSerieById(Integer id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()){
            SerieDTO serieDTO=this.translateToDTO(serie.get());
            return ResponseEntity.ok(serieDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?>  createSerie(Serie serieNew) {
        try {
            Optional<Serie> serie = serieRepository.findByName(serieNew.getName());
            if(serie.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe el registro en la base de datos");
            }
            else{
                serieRepository.save(serieNew);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado la marca con éxito");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }

    public ResponseEntity<?>  updateSerie(Integer id, Serie serieNew) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()){
            serieNew.setId(id);
            serieRepository.save(serieNew);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha modificado correctamente");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la marca a modificar");
        }

    }

    public ResponseEntity<?>  deleteSerie(Integer id) {
        try {
            Optional<Serie> serie = serieRepository.findById(id);
            if(serie.isPresent()){
                serieRepository.deleteById(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde");
        }
    }
    public ResponseEntity<?> getProductsBySerie(Integer id) {
        try{
            Optional<Serie> serie = this.findById(id);
            if(serie.isPresent()){
                List<ProductDTO> productsDTO= productService.translateListToDTO(serie.get().getProducts());
                return ResponseEntity.ok(productsDTO);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }
        catch(Exception e){
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha habido un error, inténtelo más tarde "+ e);
        }

    }
    public SerieDTO translateToDTO(Serie serie) {
         ModelMapper modelMapper = new ModelMapper();
         SerieDTO serieDTO = modelMapper.map(serie, SerieDTO.class);
        return serieDTO;

    }
    public SerieDTOWB translateToDTOwithoutBrand(Serie serie) {
        ModelMapper modelMapper = new ModelMapper();
        SerieDTOWB serieDTOWB = modelMapper.map(serie, SerieDTOWB.class);
        return serieDTOWB;

    }
    public Optional<Serie> findById(Integer id){
        return serieRepository.findById(id);
    }
    
    public List<SerieDTOWB> translateListToDTOWB(List<Serie> series) {
        List<SerieDTOWB> seriesDtos=new ArrayList<SerieDTOWB>();
        for (Serie serie : series) {
            seriesDtos.add(this.translateToDTOwithoutBrand(serie));
        }
        return seriesDtos;
    }
    public List<SerieDTO> translateListToDTO(List<Serie> series) {
        List<SerieDTO> seriesDtos=new ArrayList<SerieDTO>();
        for (Serie serie : series) {
            seriesDtos.add(this.translateToDTO(serie));
        }
        return seriesDtos;
    }
}

package es.a926666.proyectofinal.serie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.proyectofinal.brand.Brand;
import es.a926666.proyectofinal.brand.BrandDTO;





@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;


    public ResponseEntity<?> getAllSeries() {
        List<Serie> series = serieRepository.findAll();
        if(series.size()>0){
            List<SerieDTOWB> seriesDTO = this.translateListToDTO(series);
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

    public SerieDTO translateToDTO(Serie serie) {
        BrandDTO brandDTO=this.translateToDTO(serie.getBrand());
        return new SerieDTO( serie.getId(), serie.getName(), serie.getImage(),serie.getDescription(),brandDTO);

    }
    public SerieDTOWB translateToDTOwithoutBrand(Serie serie) {
        return new SerieDTOWB( serie.getId(), serie.getName(), serie.getImage(),serie.getDescription());

    }
    public BrandDTO translateToDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO(brand.getId(),brand.getName(),brand.getDescription(),brand.getImage());
        return brandDTO;
    }

    public List<SerieDTOWB> translateListToDTO(List<Serie> series) {
        List<SerieDTOWB> seriesDtos=new ArrayList<SerieDTOWB>();
        for (Serie serie : series) {
            seriesDtos.add(this.translateToDTOwithoutBrand(serie));
        }
        return seriesDtos;
    }
}

package es.a926666.proyectofinal.serie;

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
@RequestMapping("api/v1/series")
@CrossOrigin(origins = "http://localhost:4200")
public class SerieController {
  
    @Autowired
    private SerieService serieService;

    @GetMapping("")
    public ResponseEntity<?> getAllSeries() {
        return serieService.getAllSeries();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>  getSerieById(@PathVariable Integer id) {
        return serieService.getSerieById(id);
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<?>  getProductsBySerie(@PathVariable Integer id) {
        return serieService.getProductsBySerie(id);
    }
    @PostMapping("")
    public ResponseEntity<?>  createSerie(@RequestBody Serie serie) {
        return serieService.createSerie(serie);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateSerie(@PathVariable Integer id, @RequestBody Serie serie) {
        return serieService.updateSerie(id,serie);
    }
    @DeleteMapping
    ("/{id}")
    public ResponseEntity<?>  deleteSerie(@PathVariable Integer id) { 
        return serieService.deleteSerie(id);
    }
    
}

package es.a926666.proyectofinal.images;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/v1/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImagesController {
    @Autowired
    private ImagesService imageService;

    @GetMapping("product/{filename}")
    public byte[] getProductImage(@PathVariable String filename) {

        return imageService.getImage(filename,"imagenes\\productos");
    }
    @GetMapping("marca/{filename}")
    public byte[] getMarcaImage(@PathVariable String filename) {
        return imageService.getImage(filename,"imagenes\\marcas");
    }

}

package es.a926666.proyectofinal.serie;

import es.a926666.proyectofinal.brand.BrandDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerieDTO {
    private String name;
    private String image;
    private String description;
    private BrandDTO brand;
}

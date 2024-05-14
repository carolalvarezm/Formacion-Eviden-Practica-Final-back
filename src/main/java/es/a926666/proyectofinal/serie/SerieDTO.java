package es.a926666.proyectofinal.serie;

import es.a926666.proyectofinal.brand.BrandDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SerieDTO {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private BrandDTO brand;
    public SerieDTO(Integer id, String name, String image,String description,BrandDTO brand){
        this.id=id;
        this.name=name;
        this.image=image;
        this.description=description;
        this.brand=brand;
    }
}

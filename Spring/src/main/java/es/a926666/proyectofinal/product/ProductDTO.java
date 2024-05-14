package es.a926666.proyectofinal.product;

import java.util.List;

import es.a926666.proyectofinal.brand.BrandDTO;
import es.a926666.proyectofinal.category.CategoryDTO;
import es.a926666.proyectofinal.serie.SerieDTOWB;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private SerieDTOWB serie;
    private BrandDTO brand;
    private List<CategoryDTO> categories;
    public ProductDTO(Integer id,String name, String image, String description, SerieDTOWB serie, BrandDTO brand,List<CategoryDTO> categories){
        this.name=name;
        this.image=image;
        this.description=description;
        this.serie=serie;
        this.brand=brand;
        this.categories=categories;
        this.id=id;

    }
}

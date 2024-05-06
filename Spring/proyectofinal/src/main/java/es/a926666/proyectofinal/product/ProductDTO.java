package es.a926666.proyectofinal.product;

import java.util.List;

import es.a926666.proyectofinal.category.CategoryDTO;
import es.a926666.proyectofinal.serie.Serie;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String image;
    private String description;
    private Serie product;
    private List<CategoryDTO> categories;
}

package es.a926666.proyectofinal.brand;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BrandDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;

}

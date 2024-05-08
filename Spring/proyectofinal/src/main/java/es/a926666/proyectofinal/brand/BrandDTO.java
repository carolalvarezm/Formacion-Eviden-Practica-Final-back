package es.a926666.proyectofinal.brand;

import lombok.Data;



@Data
public class BrandDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    public BrandDTO(Integer id,String name,String description,String image){
        this.id=id;
        this.name=name;
        this.description=description;
        this.image=image;
    }
}

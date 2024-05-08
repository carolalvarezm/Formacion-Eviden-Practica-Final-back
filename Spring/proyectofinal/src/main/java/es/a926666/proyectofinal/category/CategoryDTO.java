package es.a926666.proyectofinal.category;


import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
    CategoryDTO(Integer id,String name,String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
}

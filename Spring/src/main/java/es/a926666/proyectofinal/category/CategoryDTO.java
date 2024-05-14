package es.a926666.proyectofinal.category;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
    public CategoryDTO(Integer id,String name,String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
}

package es.a926666.proyectofinal.serie;


import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SerieDTOWB {
    private Integer id;
    private String name;
    private String image;
    private String description;
    public SerieDTOWB(Integer id, String name, String image,String description){
        this.id=id;
        this.name=name;
        this.image=image;
        this.description=description;
    }
}

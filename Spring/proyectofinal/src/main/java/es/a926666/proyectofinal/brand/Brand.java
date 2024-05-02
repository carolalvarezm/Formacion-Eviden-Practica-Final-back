package es.a926666.proyectofinal.brand;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.a926666.proyectofinal.serie.Serie;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "brand",cascade= CascadeType.ALL)
    @JsonBackReference(value = "Brand-Serie")
    private List<Serie> series;

}

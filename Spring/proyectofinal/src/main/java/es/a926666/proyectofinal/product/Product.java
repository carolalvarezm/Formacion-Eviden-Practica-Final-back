package es.a926666.proyectofinal.product;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.a926666.proyectofinal.category.Category;
import es.a926666.proyectofinal.serie.Serie;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;
    private String image;
    private String description;

    @ManyToOne
    @JoinColumn(name="serie_id")
    private Serie serie;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "product_category", 
        joinColumns=@JoinColumn(name="product_id",referencedColumnName = "id"),
        inverseJoinColumns=@JoinColumn(name="category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    
}

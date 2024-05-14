package es.a926666.proyectofinal.product;



import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class ProductRequest {
   private Product product;
   private MultipartFile image;
}

package es.a926666.proyectofinal.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	private String username;
    private String firstname;
    private String lastname;
    private String email;
}

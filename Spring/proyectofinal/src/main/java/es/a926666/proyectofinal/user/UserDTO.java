package es.a926666.proyectofinal.user;

import lombok.Data;

@Data
public class UserDTO {
	private String username;
    private String firstname;
    private String lastname;
    private String email;
    public UserDTO(String username, String firstname, String lastname, String email){
        this.username=username;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        
    }
}

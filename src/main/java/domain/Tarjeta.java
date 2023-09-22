package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tarjeta {
	
	@Id
	int id;
	String contrasena;
	
	
	public Tarjeta(int id, String contrasena) {
		this.contrasena=contrasena;
		this.id=id;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasenna(String contrasena) {
		this.contrasena = contrasena;
	}

}

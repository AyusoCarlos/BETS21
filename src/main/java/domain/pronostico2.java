package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class pronostico2 {
	
	@Id
	String texto;
	String cuota;

	
	public pronostico2(String texto, String cuota) {
		this.cuota=cuota;
		this.texto=texto;
		
	}

	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCuota() {
		return cuota;
	}

	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	public pronostico2 porNombre(String n) {
		if (this.texto==n) {
			return this;
		}else
			return null;
		
	}
	
}

package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	@Id
	private String name;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Boolean Futbol=false;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Boolean Motor=false;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Boolean Tenis=false;
	private Vector<String> categorias= new Vector<String>();
	
	public Categoria() {
		categorias.add(" ");
		categorias.add("Futbol");
		categorias.add("Motor");
		categorias.add("Tenis");
	}
	
	
	public Vector<String> getCategorias() {
		return categorias;
	}


	public void setCategorias(Vector<String> categorias) {
		this.categorias = categorias;
	}


	public Boolean getFutbol() {
		return Futbol;
	}
	public void setFutbol(Boolean futbol) {
		Futbol = futbol;
	}
	public Boolean getMotor() {
		return Motor;
	}
	public void setMotor(Boolean motor) {
		Motor = motor;
	}
	public Boolean getTenis() {
		return Tenis;
	}
	public void setTenis(Boolean tenis) {
		Tenis = tenis;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
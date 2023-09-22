package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Combinada {
	
	public float getInversion() {
		return inversion;
	}

	public void setInversion(float inversion) {
		this.inversion = inversion;
	}

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apuestas> Apuestas= new Vector<Apuestas>();
	@Id
	private int id;
	private float inversion;
	private float ganancia;
	private int ganadas=0;
	private int acabadas=0;
	
	
	public int getGanadas() {
		return ganadas;
	}

	public void setGanadas(int ganadas) {
		this.ganadas = ganadas;
	}

	public int getAcabadas() {
		return acabadas;
	}

	public void setAcabadas(int acabadas) {
		this.acabadas = acabadas;
	}

	public Combinada(int id) {
		this.id=id;
		
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<Apuestas> getApuestas() {
		return Apuestas;
	}
	public void setApuestas(Vector<Apuestas> apuestas) {
		Apuestas = apuestas;
	}
	public void addApuestas(Apuestas apu){
		Apuestas.add(apu);
	}
}

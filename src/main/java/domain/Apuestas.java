package domain;

import javax.persistence.Entity;

@Entity
public class Apuestas {
	


	private pronostico2 pro;
	private float inversion;
	private float ganancia;
	private String evento;
	private String q;
	private boolean ganada;
	private boolean acabada;

	public boolean isAcabada() {
		return acabada;
	}



	public void setAcabada(boolean acabada) {
		this.acabada = acabada;
	}



	public boolean isGanada() {
		return ganada;
	}



	public void setGanada(boolean ganada) {
		this.ganada = ganada;
	}



	public Apuestas(pronostico2 pronostico, float inversion, float ganancia, Event evento, Question q) {
		this.pro=pronostico;
		this.inversion=inversion;
		this.ganancia=ganancia;
		this.evento=evento.getDescription();
		this.q=q.getQuestion();
		
	}

	

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
	public pronostico2 getPro() {
		return pro;
	}



	public void setPro(pronostico2 pro) {
		this.pro = pro;
	}



	public String getEvento() {
		return evento;
	}



	public void setEvento(String evento) {
		this.evento = evento;
	}



	public String getQ() {
		return q;
	}



	public void setQ(String q) {
		this.q = q;
	}

}

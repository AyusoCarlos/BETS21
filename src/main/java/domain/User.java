package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {

	public Vector<Apuestas> getApuesta() {
		return apuesta;
	}

	public void setApuesta(Vector<Apuestas> apuesta) {
		this.apuesta = apuesta;
	}
	@Id
	public 	String user;
	public String contrasena;
	public boolean admin;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	public Vector <Apuestas> apuesta=new Vector<Apuestas>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	public Historial historial=new Historial(0,0,0,0,0);
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	public Vector <Combinada> combinada=new Vector<Combinada>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	public Vector <Tarjeta> Tarjetas=new Vector<Tarjeta>();
	public float monedero;
	
	
	
	public Vector<Tarjeta> getTarjetas() {
		return Tarjetas;
	}

	public void setTarjetas(Tarjeta tarjetas) {
		Tarjetas.add(tarjetas);
	}

	public Historial getHistorial() {
		return historial;
	}
	

	public Vector<Combinada> getCombi() {
		return combinada;
	}

	public void setCombi(Vector<Combinada> combi) {
		this.combinada = combi;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}

	public User (String user, String contrasena, boolean admin) {
		
		this.admin=admin;
		this.contrasena=contrasena;
		this.user=user;
		
		//apuestas = new Vector<pronosticos>();
	}
	
	public boolean iguales(String user, String contrasena) {
		if (this.user.equals(user) && this.contrasena.equals(contrasena)) {
			return true;
			
		}
		return false;
	}

	public boolean igualesUsuario(String user) {
		if (this.user.equals(user)) {
			return true;
			
		}
		return false;}
	
	
	public float getMonedero() {
		return monedero;
	}

	public void setMonedero(float monedero) {
		this.monedero = monedero;
	}

	public String getUser() {
		return user;
	}
	public String getContrasena() {
		return contrasena;
	}
	public boolean getAdmin() {
		return admin;
	}
//	public Vector <pronosticos> getPronosticos(){
//		return this.apuestas;
//	}
//	public void setPronosticos(pronosticos pro) {
//		apuestas.addElement(pro);
//	}
	public Apuestas AddApuestas(Apuestas apu) {
	apuesta.add(apu);
	return apu;
	}
	public Combinada AddCombnadas(Combinada combi) {
		this.combinada.add(combi);
		return combi;
		}
	public int getSizeCombi() {
		return combinada.size();
	}
	
	
	
}

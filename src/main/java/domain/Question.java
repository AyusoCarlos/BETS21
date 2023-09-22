package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private String result;  
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<pronostico2> pronosticos=new Vector<pronostico2>();
	private String resultado;
	

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, Event event, Vector<pronostico2> pronosticos) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.event = event;
		this.pronosticos=pronosticos;
	}
	
	

	public Vector<pronostico2> getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(Vector<pronostico2> pronosticos) {
		this.pronosticos = pronosticos;
	}

	public Question(String query,  Event event) {
		super();
		this.question = query;
		
		
		//this.event = event;
	}
	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}

	



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}




	public String toString(){
		return questionNumber+";"+question+";";
	}
public pronostico2 addPronostico(pronostico2 pronostico)  {
        
        pronostico2 pronostico2= new pronostico2(pronostico.texto,pronostico.cuota);
        pronosticos.add(pronostico2);
        return pronostico2;
	}




	
}
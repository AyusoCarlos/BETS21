package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Tarjeta;
import domain.User;
import domain.pronostico2;
import domain.Apuestas;
import domain.Categoria;
import domain.Combinada;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	
	
	@WebMethod public User createUser(String user, String contrasena);
	
	@WebMethod public boolean init(String user, String cont);
	
	@WebMethod public boolean initUsuario(String user);
	
	@WebMethod	 public User getUser(String user, String cont);
	
	@WebMethod  public void anadirApuesta(Apuestas a, User u);
	
	@WebMethod public void createEvent(Event event) ;
	@WebMethod public void anadirPronostico2(Event ev,Question q, pronostico2 pronostico) ;
	
	@WebMethod public pronostico2 getPro(String n);
	@WebMethod public void cerrarEvento(Event ev);
	@WebMethod public void cerrarPreguntas(Question q,String resultado);
	@WebMethod public void ActualizarHistorial(Apuestas apu, User usuario, boolean Resu );
	@WebMethod public Vector <User> getAllUsers();
	@WebMethod public int getAllEventos();
	@WebMethod public Categoria getCat(String n);
	@WebMethod public void anadirCombinada(Combinada combi, User usuario);
	@WebMethod public int getSizeCombi();
	@WebMethod public void actualizarCombi(Combinada combi, User usuario,Apuestas apu, float Ganancias);
	@WebMethod public void ActualizarHistorial2(Combinada combi, User usuario, boolean Resu );
	@WebMethod public void setPregunta(Combinada combi,boolean resu,int index) ;
	@WebMethod public void anadirTarjeta(User usuario, int id,String contrasena);
	@WebMethod public Vector<Tarjeta> getAllTarjetas(User usuario);
	@WebMethod public void actualizarMonedero(User usuario, float dinero, boolean resu);
	@WebMethod public float sacarMonedero(User usuario);
	@WebMethod public Question getQuestion(String q, Event ev );
}
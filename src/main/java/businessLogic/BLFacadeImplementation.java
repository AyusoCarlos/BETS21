package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
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

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question);		

		dbManager.close();
		
		return qry;
   };
   
   public User createUser(String user, String contrasena){
	   
	  
		dbManager.open(false);
		User qry=null;
		
	     qry=dbManager.registrarUsuarios(user, contrasena);	

		dbManager.close();
		
		return qry;
  };
   
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    public boolean init(String user, String cont) {
    	dbManager.open(false);
    	Vector<User> n =dbManager.getAllUsers();
    	for(User u : n) {
    		System.out.println(u.getContrasena()+ u.getUser());
    		if(u.iguales(user, cont)) {
    			return true;
    		}
    	}
    	
		dbManager.close();
    	return false;
    }
    public boolean initUsuario(String user) {
    	dbManager.open(false);
    	Vector<User> n =dbManager.getAllUsers();
    	for(User u : n) {
    		System.out.println( u.getUser());
    		if(u.igualesUsuario(user)) {
    			return true;
    		}
    	}
    	
		dbManager.close();
    	return false;
    }

    public User getUser(String user, String cont) {
    	dbManager.open(false);
    	Vector<User> n =dbManager.getAllUsers();
    	for(User u : n) {
    		
    		if(u.iguales(user, cont)) {
    			return u;
    		}
    	}
    	
		dbManager.close();
    	return null;
    }
    public void anadirApuesta(Apuestas a,User u) {
    	dbManager.open(false);
    	
    	
    	dbManager.anadirApuesta(a,u);

    	dbManager.close();
    }
    
    public void createEvent(Event event) {
    	dbManager.open(false);
    	
    	
    	dbManager.createEvent(event);

    	dbManager.close();
    }
    public void anadirPronostico2(Event ev,Question q, pronostico2 pronostico) {
    	dbManager.open(false);
    	dbManager.anadirPronostico2(ev, q, pronostico);
    	dbManager.close();
    }

    public pronostico2 getPro(String n) {
    	dbManager.open(false);
    	pronostico2 p= dbManager.getPro(n);
    	dbManager.close();
    	return p;
    }
    public void cerrarEvento(Event ev) {
    	dbManager.open(false);
    	dbManager.cerrarEvento(ev);
    	dbManager.close();
    }
    public void cerrarPreguntas(Question q, String Resultado) {
    	dbManager.open(false);
    	dbManager.cerrarPreguntas(q,Resultado);
    	dbManager.close();
    }
    public void ActualizarHistorial(Apuestas apu, User usuario, boolean Resu ) {
    	dbManager.open(false);
    	dbManager.ActualizarHistorial(apu, usuario, Resu);
    	dbManager.close();
    	
    }
    public void ActualizarHistorial2(Combinada combi, User usuario, boolean Resu ) {
    	dbManager.open(false);
    	dbManager.ActualizarHistorial2(combi, usuario, Resu);
    	dbManager.close();
    	
    }
    public Vector <User> getAllUsers(){
    	dbManager.open(false);
    	 Vector <User> p=dbManager.getAllUsers();
    	dbManager.close();
    	return p;
    }
    public int getAllEventos() {
    	dbManager.open(false);
    	int n= dbManager.getAllEventos();
    			dbManager.close();
    			return n;
    }
    public Categoria getCat(String n) {
    	dbManager.open(false);
    	Categoria p= dbManager.getCat(n);
    	dbManager.close();
    	return p;
    }
    
    public void anadirCombinada(Combinada combi, User usuario) {
    	dbManager.open(false);
    	dbManager.anadirCombinada(combi, usuario);
    	dbManager.close();
    }
    public int getSizeCombi() {
    	dbManager.open(false);
    	int p = dbManager.getSizeCombi();
    	dbManager.close();
		return p;
    }
    public void actualizarCombi(Combinada combi, User usuario,Apuestas apu, float Ganancias) {
    	dbManager.open(false);
    	dbManager.actualizarCombi(combi, usuario, apu,Ganancias);
    	dbManager.close();
    }
    public void setPregunta(Combinada combi,boolean resu,int index) {
    	dbManager.open(false);
    	dbManager.setPregunta(combi, resu, index);
    	dbManager.close();
    }
    public void anadirTarjeta(User usuario, int id,String contrasena) {
    	dbManager.open(false);
    	dbManager.anadirTarjeta(usuario, id, contrasena);
    	dbManager.close();
    }
    public Vector<Tarjeta> getAllTarjetas(User usuario){
    	dbManager.open(false);
    	Vector<Tarjeta> lista;
    	lista =dbManager.getAllTarjetas(usuario);
    	dbManager.close();
    	return lista;
    }
    public void actualizarMonedero(User usuario, float dinero, boolean resu) {
    	dbManager.open(false);
    	dbManager.actualizarMonedero(usuario, dinero, resu);
    	dbManager.close();
    }
    public float sacarMonedero(User usuario) {
    	dbManager.open(false);
    	float resu;
    	resu= dbManager.sacarMonedero(usuario);
    	dbManager.close();
    	return resu;
    }
    public Question getQuestion(String q, Event ev ) {
    	dbManager.open(false);
    	Question resu;
    	resu= dbManager.getQuestion(q, ev);
    	dbManager.close();
    	return resu;
    }
}
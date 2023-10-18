package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Tarjeta;
import domain.User;
import domain.pronostico2;
import domain.Apuestas;
import domain.Categoria;
import domain.Combinada;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		  
		   
		   Categoria Futbol= new Categoria();
		   Futbol.setFutbol(true);
		   Futbol.setName("Futbol");
		   db.persist(Futbol);
		   
		   Categoria Motor= new Categoria();
		   Motor.setMotor(true);
		   Motor.setName("Motor");
		   db.persist(Motor);
		   
		   Categoria Tenis= new Categoria();
		   Tenis.setName("Tenis");
		   Tenis.setTenis(true);
		   db.persist(Tenis);
		   
		   
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17),Futbol);
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17),Futbol);
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17),Futbol);
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17),Futbol);
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17),Futbol);
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17),Futbol);
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17),Futbol);
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17),Futbol);
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17),Futbol);
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17),Futbol);

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1),Futbol);
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1),Futbol);
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1),Futbol);
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1),Futbol);
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1),Futbol);
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1),Futbol);
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28),Futbol);
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28),Futbol);
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28),Futbol);
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28),Futbol);
			Event ev21=new Event(21, "Real Union-Bayern de Munich", UtilDate.newDate(year,month,17),true);
			
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?");
				q2=ev1.addQuestion("¿Quién meterá el primer gol?");
				q3=ev11.addQuestion("¿Quién ganará el partido?");
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?");
				q5=ev17.addQuestion("¿Quién ganará el partido?");
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?");
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?");
				q2=ev1.addQuestion("Who will score first?");
				q3=ev11.addQuestion("Who will win the match?");
				q4=ev11.addQuestion("How many goals will be scored in the match?");
				q5=ev17.addQuestion("Who will win the match?");
				q6=ev17.addQuestion("Will there be goals in the first half?");
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?");
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?");
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?");
				q4=ev11.addQuestion("Zenbat gol sartuko dira?");
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?");
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?");
			
			}
		
			User user1 =new User("Alex","xxx",true);
			User user2 =new User("Carlos","bomba",false);
			
			db.persist(user1);
			db.persist(user2);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev21);
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question) throws QuestionAlreadyExist {
        System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question);
        if (event != null) {
            Event ev = db.find(Event.class, event.getEventNumber());
            if (question != null) {
                if (ev.DoesQuestionExists(question))
                    throw new QuestionAlreadyExist(
                            ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

                db.getTransaction().begin();
                Question q = ev.addQuestion(question);
                // db.persist(q);

                db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
                                // property of Event class
                                // @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
                db.getTransaction().commit();
                return q;
            }
        }
        return null;
    }
	
	
	
	public Event createEvent(Event event)  {
		
		
			
			
			
			db.getTransaction().begin();

			//db.persist(q);
			db.persist(event); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return event;
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	
public User registrarUsuarios( String nombre, String contrasena) {
		db.getTransaction().begin();
		User n = new User(nombre,contrasena,false);
		db.persist(n);
		db.getTransaction().commit();
		return n;
	}

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
public Vector <User> getAllUsers(){
	Vector<User> res = new Vector<User>();	
	TypedQuery<User> query = db.createQuery("SELECT p FROM User p",User.class);
	List<User> usuarios = query.getResultList();
	for (User ev:usuarios){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
}

public void anadirPronostico2(Event eve,Question q, pronostico2 pronostico) {
	db.getTransaction().begin();
	
	Question que = db.find(Question.class, q.getQuestionNumber());
	//Question q = ev.addQuestion(question, betMinimum);
	pronostico2 pronosticos= que.addPronostico(pronostico);
//	db.persist(pronosticos);
	
	db.persist(que);
	
	
	db.getTransaction().commit();
}
public void anadirApuesta(Apuestas a, User u) {
	db.getTransaction().begin();
	
	User que = db.find(User.class, u.getUser());
	//Question q = ev.addQuestion(question, betMinimum);
	Apuestas z=que.AddApuestas(a);
//	db.persist(pronosticos);
	
	db.persist(que);
	
	
	db.getTransaction().commit();
}

public pronostico2 getPro(String n) {
	pronostico2 x=db.find(pronostico2.class, n);
	return x;
	
}
public void cerrarEvento(Event ev) {
	db.getTransaction().begin();
	Event e=db.find(Event.class, ev.getEventNumber());
	e.setAcabado(true);
	db.persist(e);
	db.getTransaction().commit();
}
public void cerrarPreguntas(Question q,String resultado) {
	db.getTransaction().begin();
	Question que=db.find(Question.class, q.getQuestionNumber());
	que.setResultado(resultado);
	db.persist(que);
	db.getTransaction().commit();
}
public void ActualizarHistorial(Apuestas apu, User usuario, boolean Resu ) {
	db.getTransaction().begin();
	
	User u=db.find(User.class, usuario.getUser() );
	if(Resu==true) {
		u.getHistorial().setApuestasGanadas(u.getHistorial().getApuestasGanadas()+1);
		u.getHistorial().setDineroGanado(apu.getGanancia()+u.getHistorial().getDineroGanado());
		
	}else {
		u.getHistorial().setApuestasPerdidas(u.getHistorial().getApuestasPerdidas()+1);
	}
	u.getHistorial().setNumeroApuestas(u.getHistorial().getNumeroApuestas()+1);
	
	db.persist(u);
	db.getTransaction().commit();
}

public void ActualizarHistorial2(Combinada combi, User usuario, boolean Resu ) {
	db.getTransaction().begin();
	
	User u=db.find(User.class, usuario.getUser() );
	if(Resu==true) {
		u.getHistorial().setCombinadaGanadas(u.getHistorial().getCombinadaGanadas()+1);
		u.getHistorial().setDineroGanado(combi.getGanancia()+u.getHistorial().getDineroGanado());
		
	}else {
		u.getHistorial().setCombinadasPerdidas(u.getHistorial().getCombinadasPerdidas()+1);
	}
	u.getHistorial().setNumeroApuestas(u.getHistorial().getNumeroApuestas()+1);
	
	db.persist(u);
	db.getTransaction().commit();
}

public int getAllEventos(){
	Vector<Event> res = new Vector<Event>();	
	int cont=0;
	TypedQuery<Event> query = db.createQuery("SELECT p FROM Event p",Event.class);
	List<Event> usuarios = query.getResultList();
	for (Event ev:usuarios){
	 	   cont++;
		  }
	 	return cont;
}

public Categoria getCat(String n) {
	Categoria x=db.find(Categoria.class, n);
	return x;
	
}
public void anadirCombinada(Combinada combi, User usuario) {
	db.getTransaction().begin();
	
	User que = db.find(User.class, usuario.getUser());
	Combinada C=que.AddCombnadas(combi);
	db.persist(que);
	db.getTransaction().commit();
}
public int getSizeCombi(){
	Vector<Combinada> res = new Vector<Combinada>();	
	int cont=0;
	TypedQuery<Combinada> query = db.createQuery("SELECT c FROM Combinada c",Combinada.class);
	List<Combinada> usuarios = query.getResultList();
	for (Combinada ev:usuarios){
	 	   cont++;
		  }
	 	return cont;
}
public void actualizarCombi(Combinada combi, User usuario,Apuestas apu, float Ganancias) {
	db.getTransaction().begin();
	Combinada C= db.find(Combinada.class, combi.getId());
	C.addApuestas(apu);
	C.setGanancia(C.getGanancia()+Ganancias);
	db.persist(C);
	db.getTransaction().commit();
}
public void setPregunta(Combinada combi, boolean resu, int index) {
	db.getTransaction().begin();
	Combinada C=db.find(Combinada.class, combi.getId());
	if(resu== true) {
		C.setGanadas(C.getGanadas()+1);
		
		
	}else {
		C.setAcabadas(C.getAcabadas()+1);
	}
	
	db.persist(C);
	db.getTransaction().commit();
}
public void anadirTarjeta(User usuario, int id, String contrasena) {
	db.getTransaction().begin();
	
	User que = db.find(User.class, usuario.getUser());
	Tarjeta tar= new Tarjeta(id,contrasena);
	que.setTarjetas(tar);
	db.persist(que);
	db.getTransaction().commit();
}
public Vector<Tarjeta> getAllTarjetas(User usuario){

	User que = db.find(User.class, usuario.getUser());
	
	
	return que.getTarjetas();
}
public void actualizarMonedero(User usuario, float dinero, boolean resu) {
    db.getTransaction().begin();
    if (usuario != null) {
        User U = db.find(User.class, usuario.getUser());
        if (dinero > 0) {
            if (resu) {
                U.setMonedero(U.getMonedero() + dinero);
            } else {
                U.setMonedero(U.getMonedero() - dinero);
            }

            db.persist(U);
            
        }
    }
    db.getTransaction().commit();
}
public float sacarMonedero(User usuario) {
	
	User U= db.find(User.class, usuario.getUser());
	return U.getMonedero();
	
}
public Question getQuestion(String q, Event ev ) {
	Event E= db.find(Event.class, ev.getEventNumber());
	Question resu = null;
	for(Question que: E.getQuestions()) {
		if(que.getQuestion().equals(q)) {
			resu=que;
		}
	}
	return resu;
	
}


	
}



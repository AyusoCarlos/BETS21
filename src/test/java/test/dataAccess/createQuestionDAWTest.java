package test.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Categoria;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;
import javax.persistence.EntityManager;

public class createQuestionDAWTest {
	

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 private Event ev;
	 
	 @Test
	 //1 2(F) 13 END
	 public void Test1() {
		 try {
			 String question1 = "Evento null";
			 assertNull(sut.createQuestion(null, question1));
			 
			 
			 
		 }catch(Exception e){
			 fail();
		 }
	 }
	 
	 @Test
	 //1 2(T) 3 4(F) 13 END
	 public void Test2() {
		 try {
			 assertNull(sut.createQuestion(ev, null));
			 
			 
			 
		 }catch(Exception e){
			 fail();
		 }
	 }
	 
	 @Test
	 //1 2(T) 3 4(T) 5(T) 6 7 END
	 public void Test3() {
		 try {
			 int n=500;
			 String Desc= "prueba";
			 Date fech = UtilDate.newDate(2023, 11, 23);
			 ev = new Event(n,Desc,fech,null);
			 String question1 = "pregunta ya existe";
			 ev.addQuestion(question1);
			 sut.createEvent(ev);
			 sut.createQuestion(ev, question1);
			 fail();
			 
			 
			 
		 }catch(Exception e){
			 assertTrue(true);
		 }
	 }
	 
	 @Test
	 //1 2(T) 3 4(T) 5(F) 8 9 10 11 12 END
	 public void Test4() {
		 try {
			 int n=0;
			 String Desc= "prueba";
			 Date fech = UtilDate.newDate(2023, 11, 22);
			 Categoria Futbol= sut.getCat("Futbol");
			 ev = new Event(n,Desc,fech,Futbol);
			 String question2 = "pregunta no existe";
			 sut.createEvent(ev);
			 sut.createQuestion(ev, question2);
			 assertTrue(ev.DoesQuestionExists(question2));

			
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 fail();
			 
		 }
	 }

	 
	 
	
	

}

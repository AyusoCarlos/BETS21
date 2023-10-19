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

public class createQuestionDABTest {
	

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 private Event ev;
	 
	 @Test
	 //Que el evento no es null
	 public void Test1() {
		 try {
			 String question1 = "Evento null";
			 assertNull(sut.createQuestion(null, question1));
			 
			 
			 
		 }catch(Exception e){
			 fail();
		 }
	 }
	 
	 @Test
	 //Que la pregunta no es null
	 public void Test2() {
		 try {
			 assertNull(sut.createQuestion(ev, null));
			 
			 
			 
		 }catch(Exception e){
			 fail();
		 }
	 }
	 
	 @Test
	 //Que el evento existe en la base de datos
	 public void Test3() {
		 try {
			 int n=500;
			 String Desc= "prueba";
			 Date fech = UtilDate.newDate(2023, 11, 23);
			 Categoria Futbol= sut.getCat("Futbol");
			 ev = new Event(n,Desc,fech,Futbol);
			 String question1 = "pregunta ya existe";
			 ev.addQuestion(question1);
			 sut.createEvent(ev);
			 sut.createEvent(ev);
			 fail();
			 
			 
		 }catch(Exception e){
			 assertTrue(true);
		 }
	 }
	 @Test
	 //Que la pregunta existe en la BD
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
			 fail();
			 
		 }
	 }
	 
	 
	
	

}

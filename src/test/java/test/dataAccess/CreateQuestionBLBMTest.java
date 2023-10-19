package test.dataAccess;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateQuestionBLBMTest {
	
	 DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Event mockedEvent=Mockito.mock(Event.class);
     
     @InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
     @Test
     public void test1() {
			try {
				String question1="pregunta existe";
				Date fech = UtilDate.newDate(2023, 11, 23);
					
				Mockito.doReturn(fech).when(mockedEvent).getEventDate();
				Mockito.doReturn(new Question(question1,mockedEvent)).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class));

				

				//invoke System Under Test (sut) 
				Question q=sut.createQuestion(mockedEvent, question1);
				
				
				ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
				ArgumentCaptor<String> questionStringCaptor = ArgumentCaptor.forClass(String.class);
	
				
				Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(eventCaptor.capture(),questionStringCaptor.capture());

				assertEquals(eventCaptor.getValue(),mockedEvent);
				assertEquals(questionStringCaptor.getValue(),question1);

			   } catch (QuestionAlreadyExist e) {
				// TODO Auto-generated catch block
				assertTrue(true);
				} catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
     
     @Test
 	public void test2() {
 		try {
 			String question1="pregunta existe";
 			Date fech = UtilDate.newDate(2023, 11, 23);
 			
 			Mockito.doReturn(fech).when(mockedEvent).getEventDate();
 			Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class));

 			

 			//invoke System Under Test (sut) 
 			Question q=sut.createQuestion(null, question1);
 			
 			Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class));
 			
 			
 	
 			assertTrue(q==null);
 			

 		   } catch (Exception e) {
 		
 			fail();
 			}
 		   }
 	@Test
 	public void test3() {
 		try {
 			String queryText="pregunta existe";
 			Date fech = UtilDate.newDate(2023, 11, 23);	
 			
 			Mockito.doReturn(fech).when(mockedEvent).getEventDate();
 			Mockito.when(dataAccess.createQuestion(Mockito.any(Event.class),Mockito.any(String.class))).thenThrow(QuestionAlreadyExist.class);
 			

 			//invoke System Under Test (sut) 
 			sut.createQuestion(mockedEvent, queryText);
 			
 			
 		    fail();
 		   } catch (QuestionAlreadyExist e) {
 			// TODO Auto-generated catch block
 			   
 			// if the program goes to this point OK
 			assertTrue(true);
 			} catch (Exception e) {
 			    fail();
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		   }
 	
 	

}

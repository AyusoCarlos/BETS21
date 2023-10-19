package test.dataAccess;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class actualizarMonederoBLBMTest {

	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	User mockedUser = Mockito.mock(User.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);

	@Test
	// usuario no existe en base de datos
	public void test1() {
		try {
			// define parámetros
			float dinero = 1;
			boolean resu = false;
			User usuario = new User("prueba", "prueba2", false);
			// configure Mock para que devuelva un usuario nulo
			Mockito.doReturn("prueba").when(mockedUser).getUser();
			Mockito.doNothing().when(dataAccess).actualizarMonedero(Mockito.any(User.class), Mockito.anyFloat(),
					Mockito.anyBoolean());

			// invoke System Under Test (sut)
			sut.actualizarMonedero(usuario, dinero, resu);

			// verify the results
//			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

//			Mockito.verify(dataAccess, Mockito.times(0)).actualizarMonedero(mockedUser, Mockito.anyFloat(),
//					Mockito.anyBoolean());
			Mockito.verify(mockedUser, Mockito.times(0)).setMonedero(Mockito.anyFloat());

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// usuario es null
	public void test2() {
		try {
			float dinero = 1;
			boolean resu = false;

			// configure Mock para que devuelva un usuario nulo
			Mockito.doNothing().when(dataAccess).actualizarMonedero(ArgumentMatchers.isNull(), Mockito.anyFloat(),
					Mockito.anyBoolean());

			// invoke System Under Test (sut)
			sut.actualizarMonedero(null, dinero, resu);

			// verify the results
//			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

			Mockito.verify(mockedUser, Mockito.times(0)).setMonedero(Mockito.anyFloat());

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// Dinero es negativo
	public void test3() {
		try {
			float dinero = -1;
			boolean resu = false;

			// configure Mock para que devuelva un usuario nulo
			Mockito.doReturn("Carlos").when(mockedUser).getUser();
			Mockito.doNothing().when(dataAccess).actualizarMonedero(Mockito.any(User.class), Mockito.anyFloat(),
					Mockito.anyBoolean());

			// invoke System Under Test (sut)
			sut.actualizarMonedero(mockedUser, dinero, resu);

			// verify the results
//			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

			Mockito.verify(dataAccess, Mockito.times(1)).actualizarMonedero(mockedUser, dinero, resu);

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// Dinero es 0
	public void test4() {
		try {
			float dinero = 0;
			boolean resu = false;

			// configure Mock para que devuelva un usuario nulo
			Mockito.doReturn("Carlos").when(mockedUser).getUser();
			Mockito.doNothing().when(dataAccess).actualizarMonedero(Mockito.any(User.class), Mockito.anyFloat(),
					Mockito.anyBoolean());

			// invoke System Under Test (sut)
			sut.actualizarMonedero(mockedUser, dinero, resu);

			// verify the results
//			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

			Mockito.verify(dataAccess, Mockito.times(1)).actualizarMonedero(mockedUser, dinero, resu);

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	// Dinero es positivo
	public void test5() {
		try {

			float dinero = 1;
			boolean resu = true;

			// configure Mock para que devuelva un usuario nulo
			Mockito.doReturn("Carlos").when(mockedUser).getUser();
			Mockito.doNothing().when(dataAccess).actualizarMonedero(Mockito.any(User.class), Mockito.anyFloat(),
					Mockito.anyBoolean());

			// invoke System Under Test (sut)
			sut.actualizarMonedero(mockedUser, dinero, resu);

			// verify the results
//			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

			Mockito.verify(dataAccess, Mockito.times(1)).actualizarMonedero(mockedUser, dinero, resu);

		} catch (Exception e) {
			fail();
		}
	}

}

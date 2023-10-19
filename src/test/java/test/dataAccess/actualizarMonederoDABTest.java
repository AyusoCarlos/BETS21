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
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class actualizarMonederoDABTest {
	static DataAccess sut = new DataAccess();

	private User user;

	@Test
	// Valores de entrada validos
	public void test1() {
		try {
			float dinero = 1;
			boolean resu = false;
			sut.actualizarMonedero(null, dinero, resu);

		} catch (Exception e) {
			fail();
		}

	}

	

	@Test
	// El dinero es negativo
	public void test2() {
		try {
			float dinero = -1;
			boolean resu = false;
			user = sut.getAllUsers().firstElement();
			float dineroIni = user.getMonedero();
			sut.actualizarMonedero(user, dinero, resu);
			float dineroFin = user.getMonedero();
			assertEquals(dineroIni, dineroFin, 0.1);

		} catch (Exception e) {
			fail();
		}

	}
	@Test
	// El dinero es 0
	public void test3() {
		try {
			float dinero = 0;
			boolean resu = false;
			user = sut.getAllUsers().firstElement();
			float dineroIni = user.getMonedero();
			sut.actualizarMonedero(user, dinero, resu);
			float dineroFin = user.getMonedero();
			assertEquals(dineroIni, dineroFin, 0.1);

		} catch (Exception e) {
			fail();
		}

	}
	@Test
	// El dinero es positivo
	public void test4() {
		try {
			float dinero = 1;
			boolean resu = true;
			user = sut.getAllUsers().firstElement();
			float dineroIni = user.getMonedero();
			sut.actualizarMonedero(user, dinero, resu);
			float dineroFin = user.getMonedero();
			assertEquals(dineroIni+dinero, dineroFin, 0.1);

		} catch (Exception e) {
			fail();
		}

	}

	@Test
	// Usuario existe en bd
	public void test5() {
		try {
			float dinero = 2;
			boolean resu = true;
			user = new User("prueba","is",false);
			sut.actualizarMonedero(user, dinero, resu);
			fail();

		} catch (Exception e) {
			assertTrue(true);

		}
	}

}

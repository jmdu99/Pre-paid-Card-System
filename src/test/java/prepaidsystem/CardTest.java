package prepaidsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prepaidsystemexceptions.CreateCardException;
public class CardTest{
	
	@Test
	@DisplayName("Test constructor")
	void constructorTest1 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c = new Card ("José Manuel","Díaz Urraco" ,"123456789876","1234","12/03/19",50);
		assertNotEquals(new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/16",22),c);
		assertNotSame(c,new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50));
	}
	@Test
	@DisplayName("Test constructor2")
	void constructorTest2 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c = new Card();
		assertNotEquals(new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/16",22),c);
		assertNotSame(c,new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/16",22));
	}
	@Test
	@DisplayName("Test constructor3")
	void constructorTest3 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Card c = new Card ("José Manuel","Díaz Urraco", "123456789876","1236","12/03/19",50);
		Card c2 = new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50);
		assertNotEquals(c2.hashCode(),c.hashCode());
		assertNotSame(c,c2);
		assertNotSame(c.hashCode(),c2.hashCode());
	}
	@Test
	@DisplayName("Test constructor4")
	void constructorTest4 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c = new Card  ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",50);
		Card c2 = new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50);
		Card c3 = new Card ("José Manuel","Díaz Urraco", "123456789875","2456","12/03/19",50);
		Card c4 = new Card ("Eva","Tagarro López de Ayala", "123456789875","1234","12/02/19",50);
		Card c5 = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",40);
		Card c6 = new Card ("José Manuel","Díaz Urraco","123456789875","1234","12/03/19",40);
		assertNotEquals(c2,c);
		assertNotEquals(c3,c);
		assertNotEquals(c4,c);
		assertNotEquals(c5,c);
		assertNotEquals(c6,c);
		assertSame(c,c);
		assertEquals(c,new Card  ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",50));
	}
	@Test 
	@DisplayName("Test excepcion")
	void testCardException(){
		Throwable exception = assertThrows(CreateCardException.class, () -> { 
			Card c = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",50);
			Card c2 = new Card ("José Manuel","Díaz Urraco", "1","1234","12/03/19",50);
			assertNotEquals(c,c2);
			assertNotSame(c.toString(),c2.toString());
		});
		assertEquals("The cardNumber is not valid, it must have 12 digits", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion2")
	void testCardException2(){
		Throwable exception = assertThrows(CreateCardException.class, () -> {
			Card c = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",50);
			Card c3 = new Card ("José Manuel","Díaz Urraco", "123456789875","12","12/03/19",50);
			assertNotEquals(c,c3);
			assertNotSame(c,c3);
		});
		assertEquals("The pin is not valid, it must have 4 digits", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion3")
	void testCardException3(){
		Throwable exception = assertThrows(CreateCardException.class, () -> {
			Card c = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/03/19",50);
			Card c4 = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/02",50);
			assertNotEquals(c,c4);
			assertNotSame(c,c4);
		});
		assertEquals("The expirationDate is not valid, it must be like MM/DD/YY", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion4")
	void testCardException4(){
		Throwable exception = assertThrows(CreateCardException.class, () -> {
			Card c = new Card ("Eva","Tagarro", "123456789875","1234","12/03/19",50);
			Card c4 = new Card ("Eva","Tagarro", "123456789875","1234","12/02/19",0);
			assertNotEquals(c,c4);
			assertNotSame(c,c4);
		});
		assertEquals("Card must be charged with money", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion5")
	void testCardException5(){
		Throwable exception = assertThrows(CreateCardException.class, () -> {
			Card c = new Card ("","Díaz Urraco", "123456789875","1234","12/03/19",50);
			Card c4 = new Card ("José Manuel","Díaz Urraco", "123456789875","1234","12/02/19",0);
			assertNotEquals(c,c4);
			assertNotSame(c,c4);
		});
		assertEquals("User can not be created. Name and surname cannot be blank.", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion6")
	void testCardException6(){
		Throwable exception = assertThrows(CreateCardException.class, () -> {
			Card c = new Card ("Eva","", "123456789875","1234","12/03/19",50);
			Card c4 = new Card ("Eva","Tagarro", "123456789875","1234","12/02/19",0);
			assertNotEquals(c,c4);
			assertNotSame(c,c4);
		});
		assertEquals("User can not be created. Name and surname cannot be blank.", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}	@Test
	@DisplayName("Test getters")
	void gettersTest () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		String pinHash=Card.sha256("1234");
		Card c = new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50);
		Card c2 = new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50);
		c.equals(c2);
		assertEquals(50,c.getBalance());
		assertEquals("José Manuel",c.getName());
		assertEquals("Díaz Urraco",c.getSurname());
		assertEquals("123456789876",c.getCardNumber());
		assertEquals(pinHash,c.getPin());
		assertEquals("12/03/19",c.getExpirationDate());
		c.setExpirationDateLong(123456);
		assertNotNull(c.toString());
	}
	@Test
	@DisplayName("Test getters2")
	void gettersTest2 () throws CreateCardException{
		Card c = new Card ();
		assertEquals(0,c.getBalance());
		assertEquals(null,c.getName());
		assertEquals(null,c.getSurname());
		assertEquals(null,c.getCardNumber());
		assertEquals(null,c.getPin());
		assertEquals(null,c.getExpirationDate());
	}
	@Test
	@DisplayName("Test setters")
	void settersTest () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c = new Card ();
		String pinHash=Card.sha256("1234");
		c.setBalance(80);
		c.setName("José Manuel");
		c.setSurname("Díaz Urraco");
		c.setCardNumber("123454321234");
		c.setExpirationDate("02/12/34");
		c.setPin("1234");
		assertEquals(80,c.getBalance());
		assertEquals("José Manuel",c.getName());
		assertEquals("Díaz Urraco",c.getSurname());
		assertEquals("123454321234",c.getCardNumber());
		assertEquals(pinHash,c.getPin());
		assertEquals("02/12/34",c.getExpirationDate());
	}
	
	@Test 
	@DisplayName("Test Iterables iguales")
	void testIterableEquals () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c = new Card ("José Manuel","Díaz Urraco", "123456789876","1234","12/03/19",50);
		List<Card> l2 = new ArrayList<>();
		List<Card> l = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			l.add(c);
			l2.add(c);
		}
		assertIterableEquals(l, l2);
	}
	@Test 
	@DisplayName("Test Card equals1")
	void equalsTest1 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456789999", "1234", "12/03/19", 50);
		c1.equals(c1);
		assertEquals(50, c1.getBalance());

	}
	@Test 
	@DisplayName("Test Card NotEquals2")
	void equalsTest2 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "12/03/19", 50);
		c1.equals(null);
		assertEquals(50, c1.getBalance());
	}
	@Test 
	@DisplayName("Test Card NotEquals3")
	void equalsTest3 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "12/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro López de Ayala", "123456790002", "1234", "12/03/19", 50);
		c1.equals(c2);
		assertNotEquals(c1.getSurname(), c2.getSurname());
	}
	@Test 
	@DisplayName("Test Card NotEquals4")
	void equalsTest4 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "12/03/19", 50);
		Card c2 = new Card ("Evix","Tagarro", "123456790002", "1234", "12/03/19", 50);
		c1.equals(c2);
		assertNotEquals(c1.getName(), c2.getName());
	}
	@Test 
	@DisplayName("Test Card NotEquals5")
	void equalsTest5 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "12/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790003", "1234", "12/03/19", 50);
		c1.equals(c2);
		assertNotEquals(c1.getCardNumber(), c2.getCardNumber());
	}
	@Test 
	@DisplayName("Test Card NotEquals6")
	void equalsTest6 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790002", "1234", "12/03/19", 50);
		c1.equals(c2);
		assertNotEquals(c1.getExpirationDate(), c2.getExpirationDate());
	}
	@Test 
	@DisplayName("Test Card NotEquals7")
	void equalsTest7 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/04/19", 50);
		c1.setExpirationDateLong(190513);
		c1.equals(c2);
		assertNotEquals(c1.getExpirationDateLong(), c2.getExpirationDateLong());
	}
	@Test 
	@DisplayName("Test Card NotEquals8")
	void equalsTest8 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 60);
		c1.equals(c2);
		assertNotEquals(c1.getBalance(), c2.getBalance());
	}
	@Test 
	@DisplayName("Test Card NotEquals9")
	void equalsTest9 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790002", "1235", "13/03/19", 60);
		c1.equals(c2);
		assertNotEquals(c1.getPin(), c2.getPin());
	}
	@Test 
	@DisplayName("Test Card NotEquals19")
	void equalsTest19 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790003", "1235", "13/03/19", 50);
		c1.equals(c2);
		assertNotEquals(c1.getCardNumber(), c2.getCardNumber());
	}
	@Test 
	@DisplayName("Test Card NotEquals20")
	void equalsTest20 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException, JAXBException{
		Card c1 = new Card ("Eva","Tagarro", "123456790003", "1235", "13/03/19", 50);
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Movement m = CardMovements.lastMovement();
		c1.equals(m);
		assertEquals(50, c1.getBalance());
	}
	@Test 
	@DisplayName("Test Card NotEquals21")
	void equalsTest21 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Card c1 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		Card c2 = new Card ("Eva","Tagarro", "123456790002", "1234", "13/03/19", 50);
		c1.setExpirationDate("13/04/19");
		c1.equals(c2);
		assertNotEquals(c1.getExpirationDate(), c2.getExpirationDate());
	}
}


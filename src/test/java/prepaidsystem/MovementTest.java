package prepaidsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prepaidsystemexceptions.CreateMovementException;

public class MovementTest {
	@Test
	@DisplayName("Test constructor")
	void constructorTest1 (){
		Movement m = new Movement ("123456789876","12/03/19",50);
		assertNotEquals(new Movement ("123456789876","12/03/16",22),m);
		assertNotSame(m,new Movement ("123456789876","12/03/19",50));
	}
	@Test
	@DisplayName("Test constructor2")
	void constructorTest2 () throws CreateMovementException{
	    Movement m = new Movement ("123456789876","12/03/19",50);
		assertNotEquals(new Movement ("123456789876","12/03/16",22),m);
		assertNotSame(m,new Movement ("123456789876","12/03/19",50));
	}
	@Test
	@DisplayName("Test constructor3")
	void constructorTest3 (){
		Movement m2 = new Movement ("123456789875","12/03/19",50);
		Movement m = new Movement ("123456789876","12/03/19",50);
		assertNotEquals(m2,m);
		assertNotSame(m,m2);
		assertNotEquals(m2.hashCode(),m.hashCode());
		assertNotSame(m2.hashCode(),m.hashCode());
	}
	@Test
	@DisplayName("Test constructor4")
	void constructorTest4 (){
		Movement m = new Movement  ("123456789875","12/03/19",50);
		Movement m2 = new Movement ("123456789875","12/03/19",40);
		Movement m3 = new Movement ("123456789875","11/03/19",50);
		Movement m4 = new Movement ("123456789876","12/03/19",50);
		assertNotEquals(m2,m);
		assertNotEquals(m3,m);
		assertNotEquals(m4,m);
		assertSame(m,m);
	}
	@Test
	@DisplayName("Test getter")
	void gettersTest (){
		Movement m = new Movement("123456789875","12/03/19",50);
		Movement m2 = new Movement("123456789875","12/03/19",50);
		Movement m3 = new Movement("123456789874","12/03/19",50);
		assertTrue(m.equalMovements(m2));
		assertTrue(m.equals(m2));
		assertFalse(m.equalMovements(m3));
		assertFalse(m.equals(m3));
		assertEquals(50,m.getAmount());
		assertEquals("123456789875",m.getCardNumber());
		assertEquals("12/03/19",m.getDate());
}
	@Test
	@DisplayName("Test getter2")
	void gettersTest2 (){
		Movement m = new Movement("123456789875","12/03/19",50);
		Movement m2 = new Movement("123456789875","12/03/19",50);
		m2.setdateSort(190311);
		assertFalse(m.equals(m2));
}
	@Test 
	@DisplayName("Test excepcion")
	void testCourseException(){
		Throwable exception = assertThrows(CreateMovementException.class, () -> { 
	    Movement m = new Movement();
	    assertEquals(0,m.getAmount());
	    throw new CreateMovementException("");
		});
		assertNotEquals("The cardNumber is not valid, it must have 12 digits", exception.getMessage());
		assertEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test Iterables iguales")
	void testIterableEquals (){
		Movement m = new Movement("123456789875","12/03/19",50);
		List<Movement> l2 = new ArrayList<>();
		List<Movement> l = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			l.add(m);
			l2.add(m);
		}
		assertIterableEquals(l, l2);
	}
}
	
package prepaidsystem;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prepaidsystemexceptions.CreateCardException;

public class CardMovementsTest {
	@Test
	@DisplayName("Test lastMovement1")
	void lastmovement1() throws CreateCardException, JAXBException {
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Movement m = CardMovements.lastMovement();
		if (Lists.getMovementsList().getMovementsList().size() == 0) {
			assertNull(m);
		} else {
			assertNotNull(m);
		}
	}
	@Test
	@DisplayName("Test lastMovement2")
	void lastmovement2() throws CreateCardException, JAXBException {
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Movement m = CardMovements.lastMovement();
		if (m.compareTo(m)==2) {
			assertNull(m);
		} else {
			assertNotNull(m);
		}
	}
	@Test 
	@DisplayName("Test Iterables iguales")
	void testIterableEquals (){
		CardMovements c = new CardMovements();
		List<CardMovements> l2 = new ArrayList<>();
		List<CardMovements> l = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			l.add(c);
			l2.add(c);
		}
		assertIterableEquals(l, l2);
	}
	@Test 
	@DisplayName("Test Movement1")
	void movement1() throws CreateCardException, JAXBException {
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Movement m = CardMovements.lastMovement();
		if (m.equals(null)) {
			assertNull(m);
		} else {
			assertNotNull(m);
		}
	}
	@Test 
	@DisplayName("Test Movement2")
	void movement2() throws CreateCardException, JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Movement m = CardMovements.lastMovement();
		Card c1 = new Card ("Eva","Tagarro", "123456789999", "1234", "12/03/19", 50);
		if (m.equals(c1)) {
			assertNull(m);
		} else {
			assertNotNull(m);
		}
	}
}
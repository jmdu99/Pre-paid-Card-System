package prepaidsystem;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.DisplayName;

public class ListsTest { 
	 
	@Test
	@DisplayName("Test constructor")
	public void constructorTest1 () throws JAXBException {
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		CardList cardList = Lists.getCardList();
		CardMovements movementsList = Lists.getMovementsList();
		assertNotNull(cardList);
		assertNotNull(movementsList);
		Lists l=Lists.getListsConstructor();
		assertNotNull(l);
	}
	@Test 
	@DisplayName("Test Iterables iguales")
	public void testIterableEquals () {
		List<Lists> l2 = new ArrayList<>();
		List<Lists> l = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			Lists c = null;
			l.add(c);
			l2.add(c);
		}
		assertIterableEquals(l, l2);
	}

}

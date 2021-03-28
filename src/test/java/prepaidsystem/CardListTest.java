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
public class CardListTest {
	@Test
	@DisplayName("Test cardNumberInSystem")
	void cardNumberInSystem () throws CreateCardException, NoSuchAlgorithmException, JAXBException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Card c = new Card ("José Manuel","Díaz Urraco" ,"999999999999","1234","12/03/19",50);
		assertNull(CardList.cardNumberInSystem(c.getCardNumber()));
	}
	@Test
	@DisplayName("Test lastCard")
	void lastcard() throws CreateCardException, JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Card c = CardList.lastCard();
		if(Lists.getCardList().getCardList().size()==0) {
		assertNull(c);
		}
		else {
			assertNotNull(c);
		}
	}
	@Test 
	@DisplayName("Test Iterables iguales")
	void testIterableEquals (){
		CardList c = new CardList();
		List<CardList> l2 = new ArrayList<>();
		List<CardList> l = new LinkedList<>();
		for (int i = 0; i < 10; i ++) {
			l.add(c);
			l2.add(c);
		}
		assertIterableEquals(l, l2);
	}
	
}
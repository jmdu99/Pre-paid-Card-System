package prepaidsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.DataExchange;
import prepaidsystemexceptions.CreateCardException;
import prepaidsystemexceptions.CreateNumberFormatException;
import prepaidsystemexceptions.CreateSystemException;


public class PrepaidSystemTest{
	

	
	@Test 
	@DisplayName("Test excepcion")
	void testSystemException() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("999999999999999999", "1234", "50", false, null);
			PrepaidSystem.pay(data);
		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion2")
	void testSystemException2() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		String lastNumber=CardList.lastCard().getCardNumber();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(lastNumber, "zzzz", "50", false, null);
			PrepaidSystem.chargeMoney(data);
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion3")
	void testSystemException3() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/19",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 		
			DataExchange data = new DataExchange(c.getCardNumber(),"1234","99999999999999999999999999999", false, null);
			PrepaidSystem.pay(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The card does not have enough money", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion4")
	void testSystemException4() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "1234", "10", false, null);
			PrepaidSystem.pay(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The card has expired", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion5")
	void testSystemException5() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("", "Diaz", "4321", "4321", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Name and username cannot be blank", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion6")
	void testSystemException6() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("Jose", "", "4321", "4321", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Name and username cannot be blank", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion7")
	void testSystemException7() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(NumberFormatException.class, () -> { 
			DataExchange data = new DataExchange("Jose", "Diaz", "4321", "4321", "hola", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Amount not a number", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion8")
	void testSystemException8() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("Jose", "Diaz", "4321", "4312", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Pin numbers not valid", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion9")
	void testSystemException9() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		String lastNumber=CardList.lastCard().getCardNumber();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(lastNumber, "zzzz", "50", false, null);
			PrepaidSystem.pay(data);
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion10")
	void testSystemException10() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(NumberFormatException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "1234", "kaka", false, null);
			PrepaidSystem.pay(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("Amount not a number", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion11")
	void testSystemException11() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("999999999999999999", "1234", "50", false, null);
			PrepaidSystem.chargeMoney(data);

		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion12")
	void testSystemException12() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		c.setExpirationDate("09/12/15");
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "1234", "10", false, null);
			PrepaidSystem.chargeMoney(data);

		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The card has expired", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion13")
	void testSystemException13() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(NumberFormatException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "1234", "kaka", false, null);
			PrepaidSystem.chargeMoney(data);

		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("Amount not a number", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion14")
	void testSystemException14() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("999999999999999999", "1234", "1234");
			PrepaidSystem.changePin(data);
		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion15")
	void testSystemException15() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "0000", "0000");
			PrepaidSystem.changePin(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion16")
	void testSystemException16() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("999999999999999999", "4321", false, null);
			PrepaidSystem.consultBalance(data);
		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion17")
	void testSystemException17() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "0000", false, null);
			PrepaidSystem.consultBalance(data);
			PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion18")
	void testSystemException18() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("999999999999999999", "4321", false, null);
			PrepaidSystem.consultBalance(data);
		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion19")
	void testSystemException19() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "0000", false, null);
			PrepaidSystem.consultBalance(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion20")
	void testSystemException20() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("Eva", "Tagarro", "432", "4311", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Pin must have four digits", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion21")
	void testSystemException21() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("Eva", "Tagarro", "4321", "431", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Pin must have four digits", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion22")
	void testSystemException22() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(NumberFormatException.class, () -> { 
			DataExchange data = new DataExchange("Eva", "Tagarro", "aaaa", "0123", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Pin must only have numbers", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion23")
	void testSystemException23() throws JAXBException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Throwable exception = assertThrows(NumberFormatException.class, () -> { 
			DataExchange data = new DataExchange("Eva", "Tagarro", "0123", "aaaa", "10", false, null);
			PrepaidSystem.buyCard(data);
		});
		assertEquals("Pin must only have numbers", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test excepcion24")
	void testSystemException24() throws JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234","12/03/19",50);
		Lists.getCardList().getCardList().add(c);
		c.setCardNumber("-1");
		Card c1 = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234","12/03/19",50);
		Lists.getCardList().getCardList().add(c1);
		
		PrepaidSystem.marshalingDataBase();
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 		
			DataExchange data = new DataExchange(c1.getCardNumber(),"1234","99999999999999999999999999999", false, null);
			PrepaidSystem.pay(data);
		PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The card does not have enough money", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test buyCard")
	void testbuyCard() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
 
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c= new Card("Eva","Tagarro",Card.getNewCardNumber(),"0000",formattedDateTimePlusYear, 100);
		
		DataExchange data = new DataExchange("Eva","Tagarro", "0000", "0000", "100", false, null);
		
		PrepaidSystem.buyCard(data);
		PrepaidSystem.marshalingDataBase();
		assertEquals(c, CardList.lastCard());
	}
	@Test 
	@DisplayName("Test buyCard2")
	void testbuyCard2() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c= new Card("Eva","Tagarro",Card.getNewCardNumber(),"0001",formattedDateTimePlusYear, 100);
		DataExchange data = new DataExchange("Eva","Tagarro", "0001", "0001", "100", false, null);
		PrepaidSystem.buyCard(data);
		PrepaidSystem.marshalingDataBase();
		assertEquals(c, CardList.lastCard());
		Card c2= new Card("Eva","Tagarro",Card.getNewCardNumber(),"0002",formattedDateTimePlusYear, 100);
		data = new DataExchange("Eva","Tagarro", "0002", "0002", "100", false, null);
		PrepaidSystem.buyCard(data);
		PrepaidSystem.marshalingDataBase();
		assertEquals(c2, CardList.lastCard());
	}
	@Test 
	@DisplayName("Test Pay")
	void  testPay () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "10", false, null);
		PrepaidSystem.pay(data);

		PrepaidSystem.marshalingDataBase();
		assertEquals(40, CardList.lastCard().getBalance());
	}
	@Test 
	@DisplayName("Test Pay2")
	void testPay2 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "20", false, null);
		PrepaidSystem.pay(data);
		
		PrepaidSystem.marshalingDataBase();
		assertEquals(30, CardList.lastCard().getBalance());
		
		data = new DataExchange(c.getCardNumber(), "1234", "20", false, null);
		PrepaidSystem.pay(data);
		
		PrepaidSystem.marshalingDataBase();
		assertEquals(10, CardList.lastCard().getBalance());
	}
	@Test 
	@DisplayName("Test Pay3")
	void testPay3 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);

		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			final DataExchange data1 = new DataExchange(c.getCardNumber(), "1234", "0", false, null);
			PrepaidSystem.pay(data1);
		});
		assertEquals("Amount must be greater than zero", exception.getMessage());
		assertNotEquals("", exception.getMessage());
		PrepaidSystem.marshalingDataBase();

	}
	@Test 
	@DisplayName("Test ChargeMoney")
	void  testChargeMoney () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());	
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "10", false, null);
		PrepaidSystem.chargeMoney(data);
		double balance = CardList.cardNumberInSystem(c.getCardNumber()).getBalance();
		assertEquals(60.0 - balance, 0);
	}
	@Test 
	@DisplayName("Test ChargeMoney2")
	void testChargeMoney2 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "40", false, null);
		PrepaidSystem.chargeMoney(data);
		
		PrepaidSystem.marshalingDataBase();
		assertEquals(90, CardList.lastCard().getBalance());
		data = new DataExchange(c.getCardNumber(), "1234", "20", false, null);
		PrepaidSystem.chargeMoney(data);
		
		PrepaidSystem.marshalingDataBase();
		assertEquals(110, CardList.lastCard().getBalance());
	}
	@Test 
	@DisplayName("Test ChargeMoney3")
	void ChargeMoney3 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234",formattedDateTimePlusYear,50);
		Lists.getCardList().getCardList().add(c);

		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			final DataExchange data = new DataExchange(c.getCardNumber(), "1234", "0", false, null);
			PrepaidSystem.chargeMoney(data);
		});
		assertEquals("Amount must be greater than zero", exception.getMessage());
		assertNotEquals("", exception.getMessage());
		PrepaidSystem.marshalingDataBase();

	}
	@Test 
	@DisplayName("Test ChangePin")
	void  testChangePin () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException, NumberFormatException, CreateNumberFormatException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "0000");
		PrepaidSystem.changePin(data);
		
		PrepaidSystem.marshalingDataBase();
		String pin=Card.sha256("0000");
		assertEquals(pin, CardList.lastCard().getPin());
		
	}
	@Test 
	@DisplayName("Test ChangePin2")
	void testChangePin2 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException, NumberFormatException, CreateNumberFormatException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();

		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "1233");
		PrepaidSystem.changePin(data);
		
		PrepaidSystem.marshalingDataBase();
		String pin=Card.sha256("1233");
		assertEquals(pin, CardList.lastCard().getPin());

		data = new DataExchange(c.getCardNumber(),"1233", "4321");
		PrepaidSystem.changePin(data);
		
		PrepaidSystem.marshalingDataBase();
		String pin2=Card.sha256("4321");
		assertEquals(pin2, CardList.lastCard().getPin());
	}
	@Test 
	@DisplayName("Test ChangePin3")
	void testChangePin3 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "1234", "1234");
			PrepaidSystem.changePin(data);
		});
		
		assertEquals("The new pin cannot be the same as the current one", exception.getMessage());
		assertNotEquals("", exception.getMessage());
		PrepaidSystem.marshalingDataBase();
		
	}
	@Test 
	@DisplayName("Test ChangePin4")
	void testChangePin4 () throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("Eva","Tagarro", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", "123");	
		Throwable exception = assertThrows(CreateNumberFormatException.class, () -> { 
			PrepaidSystem.changePin(data);
		});
		assertEquals("Pin must have four digits", exception.getMessage());
		assertNotEquals("", exception.getMessage());
		PrepaidSystem.marshalingDataBase();

	}

	@Test 
	@DisplayName("Test Consult Balance")
	void  testConsultBalance() throws CreateSystemException, JAXBException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", false, null);
		String result=PrepaidSystem.consultBalance(data);
				
		PrepaidSystem.marshalingDataBase();
		assertEquals("", result);
	}
	@Test 
	@DisplayName("Test Consult Movements")
	void  testConsultMovements() throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		DataExchange data = new DataExchange(c.getCardNumber(), "1234", false, null);
		String result=PrepaidSystem.consultMovements(data);

		PrepaidSystem.marshalingDataBase();
		assertEquals("", result);
	}
	
	@Test 
	@DisplayName("Test Consult Movements 2")
	void  testConsultMovements2() throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange(c.getCardNumber(), "2234", false, null);
			PrepaidSystem.consultMovements(data);
			PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The pin is incorrect", exception.getMessage());
		assertNotEquals("", exception.getMessage());
		
	}
	
	@Test 
	@DisplayName("Test Consult Movements 3")
	void  testConsultMovements3() throws JAXBException, CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateCardException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		Card c = new Card ("José Manuel","Díaz Urraco", Card.getNewCardNumber(),"1234","12/03/15",50);
		Lists.getCardList().getCardList().add(c);
		PrepaidSystem.marshalingDataBase();
		
		Throwable exception = assertThrows(CreateSystemException.class, () -> { 
			DataExchange data = new DataExchange("9999999999999", "1234", false, null);
			PrepaidSystem.consultMovements(data);
			PrepaidSystem.marshalingDataBase();
		});
		assertEquals("The card is not registered in the system", exception.getMessage());
		assertNotEquals("", exception.getMessage());
	}
	@Test 
	@DisplayName("Test DataBase not exist1")
	void testDB1() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
	    File f1 = new File("cardList.xml");
	    if (f1.exists()){
	        f1.renameTo(new File("cardList.xml.bkp"));
	    } 
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c= new Card("Eva","Tagarro",Card.getNewCardNumber(),"0000",formattedDateTimePlusYear, 100);
		
		DataExchange data = new DataExchange("Eva","Tagarro", "0000", "0000", "100", false, null);
		
		PrepaidSystem.buyCard(data);
	
		PrepaidSystem.marshalingDataBase();
		assertEquals(c, CardList.lastCard());
		File f2 = new File("cardList.xml.bkp");
		f1 = new File("cardList.xml");
	    if (f2.exists()){
	    	if (f1.exists()) f1.delete();
	        f2.renameTo(new File("cardList.xml"));
	    }	
	}
	@Test 
	@DisplayName("Test DataBase not exist2")
	void testDB2() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
	    File f1 = new File("cardMovements.xml");
	    if (f1.exists()){
	        f1.renameTo(new File("cardMovements.xml.bkp"));
	    }
		PrepaidSystem.unMarshalingDataBase();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusYear = now.plusYears(1);
		String formattedDateTimePlusYear = nowPlusYear.format(dtf);
		Card c= new Card("Eva","Tagarro",Card.getNewCardNumber(),"0000",formattedDateTimePlusYear, 100);
		
		DataExchange data = new DataExchange("Eva","Tagarro", "0000", "0000", "100", false, null);
		
		PrepaidSystem.buyCard(data);
	
		PrepaidSystem.marshalingDataBase();
		assertEquals(c, CardList.lastCard());
		File f2 = new File("cardMovements.xml.bkp");
		f1 = new File("cardMovements.xml");
	    if (f2.exists()){
	    	if (f1.exists()) f1.delete();
	        f2.renameTo(new File("cardMovements.xml"));
	    }	
		
	}
	@Test 
	@DisplayName("Test DataBase 3")
	void testDB3() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
	    File f1a = new File("cardList.xml");
	    if (f1a.exists()){
	        f1a.renameTo(new File("cardList.xml.bkp"));
	    } 
	    File f1b = new File("cardMovements.xml");
	    if (f1b.exists()){
	        f1b.renameTo(new File("cardMovements.xml.bkp"));
	    }
		PrepaidSystem.marshalingDataBase();
		Card c= null;
		assertEquals(c, null);
		File f2a = new File("cardList.xml");
	    if (f2a.exists()){
	    	f2a.delete();
	    }	
    	f1a = new File("cardList.xml.bkp");
        f1a.renameTo(new File("cardList.xml"));
		File f2b = new File("cardMovements.xml");
	    if (f2b.exists()){
	    	f2b.delete();
	    }	
    	f1b = new File("cardMovements.xml.bkp");
        f1b.renameTo(new File("cardMovements.xml"));
	}
	@Test 
	@DisplayName("Test ListaVacia1")
	void ListaVacia1() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
	    File f1a = new File("cardList.xml");
	    if (f1a.exists()){
	        f1a.renameTo(new File("cardList.xml.bkp"));
	    } 
	    File f1b = new File("cardMovements.xml");
	    if (f1b.exists()){
	        f1b.renameTo(new File("cardMovements.xml.bkp"));
	    }    
		Movement m = CardMovements.lastMovement();		
		if (Lists.getMovementsList().getMovementsList().size() == 0) {
			assertNull(m);
		} else {
			assertNotNull(m);
		}
		File f2a = new File("cardList.xml");
	    if (f2a.exists()){
	    	f2a.delete();
	    }	
    	f1a = new File("cardList.xml.bkp");
        f1a.renameTo(new File("cardList.xml"));
		File f2b = new File("cardMovements.xml");
	    if (f2b.exists()){
	    	f2b.delete();
	    }	
    	f1b = new File("cardMovements.xml.bkp");
        f1b.renameTo(new File("cardMovements.xml"));
	}
	@Test 
	@DisplayName("Test ListaVacia2")
	void ListaVacia2() throws JAXBException, CreateSystemException, CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Lists.getCardList().setCardList(new ArrayList<Card>());
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
	    File f1a = new File("cardList.xml");
	    if (f1a.exists()){
	        f1a.renameTo(new File("cardList.xml.bkp"));
	    } 
	    File f1b = new File("cardMovements.xml");
	    if (f1b.exists()){
	        f1b.renameTo(new File("cardMovements.xml.bkp"));
	    }    
	    Lists.getCardList().setCardList(new ArrayList<Card>());
	    Card c = CardList.lastCard();
		if (Lists.getCardList().getCardList().size() == 0) {
			assertNull(c);
		} else {
			assertNotNull(c);
		}
		File f2a = new File("cardList.xml");
	    if (f2a.exists()){
	    	f2a.delete();
	    }	
    	f1a = new File("cardList.xml.bkp");
        f1a.renameTo(new File("cardList.xml"));
		File f2b = new File("cardMovements.xml");
	    if (f2b.exists()){
	    	f2b.delete();
	    }	
    	f1b = new File("cardMovements.xml.bkp");
        f1b.renameTo(new File("cardMovements.xml"));
	}
	@Test
	@DisplayName("Test PrepaidSystemTest1")
	void PrepaidSystemTest1 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		JPasswordField pinInputField;
		pinInputField = PrepaidSystem.preparePinField(350, 200, 100, 20);
		assertNotEquals(pinInputField, null);
	}
	@Test
	@DisplayName("Test PrepaidSystemTest2")
	void PrepaidSystemTest2 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		JFormattedTextField amountInputField;
		amountInputField = PrepaidSystem.prepareAmountInputField(100, 250, 100, 20);
		assertNotEquals(amountInputField, null);
	}
	@Test
	@DisplayName("Test PrepaidSystemTest3")
	void PrepaidSystemTest3 () throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException{
		Throwable exception = assertThrows(UnsupportedOperationException.class, () -> { 
			PrepaidSystem pc = new PrepaidSystem();
		});
		assertEquals("No needs instanciation", exception.getMessage());
		assertNotEquals("", exception.getMessage());

	}
}





package prepaidsystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import controller.Controller;
import controller.DataExchange;
import prepaidsystemexceptions.CreateCardException;
import prepaidsystemexceptions.CreateNumberFormatException;
import prepaidsystemexceptions.CreateSystemException;


public class PrepaidSystem {

	private static final Logger LOGGER = Logger.getLogger(PrepaidSystem.class.getName());
	
	protected PrepaidSystem() {
		throw new UnsupportedOperationException("No needs instanciation");
	}

	public static void buyCard(DataExchange data) throws 
		CreateSystemException,CreateCardException,NoSuchAlgorithmException,UnsupportedEncodingException {	

		LOGGER.info("Logger Name: " + LOGGER.getName());

		String nameInputField = data.getName();
		String surnameInputField = data.getSurname();
		String pinInputField = data.getPin();
		String repeatPinInputField = data.getPinRepeated();
		String amountInputField = data.getAmount();
		
		String errorField = "";
		int pinInputFieldNumber = 0;
		int repeatPinInputFieldNumber = 0;
		
		if (nameInputField.equals("") || surnameInputField.equals("")) {
			errorField = "Name and username cannot be blank";
			throw new CreateSystemException(errorField);
		}
		try {
			pinInputFieldNumber = Integer.parseInt(pinInputField);
		} catch (NumberFormatException e2) {
			errorField = "Pin must only have numbers";
			throw new NumberFormatException(errorField);
		}		
		try {
			repeatPinInputFieldNumber = Integer.parseInt(repeatPinInputField);
		} catch (NumberFormatException e2) {
			errorField = "Pin must only have numbers";
			throw new NumberFormatException(errorField);
		}		
		if (pinInputField.length() != 4) {
			errorField = "Pin must have four digits";
			throw new CreateSystemException(errorField);		
		}
		if (repeatPinInputField.length() != 4) {
			errorField = "Pin must have four digits";
			throw new CreateSystemException(errorField);		
		}
		if (pinInputFieldNumber==repeatPinInputFieldNumber) {
			double amount = 0;
			try {
				amount = Double.parseDouble(amountInputField);
			} catch (NumberFormatException e2) {
				errorField = "Amount not a number";
				throw new NumberFormatException(errorField);
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
			LocalDateTime now = LocalDateTime.now();
			String formattedDateTime = now.format(dtf);
			LocalDateTime nowPlusYear = now.plusYears(1);
			String formattedDateTimePlusYear = nowPlusYear.format(dtf);

			String cardNumber = Card.getNewCardNumber();
			String pin = pinInputField;
			String name = nameInputField;
			String surname = surnameInputField;

			Card card = new Card(name, surname, cardNumber, pin, formattedDateTimePlusYear, amount);

			Lists.getCardList().getCardList().add(card);
			Movement movement = new Movement(cardNumber, formattedDateTime, amount);
			Lists.getMovementsList().getMovementsList().add(movement);
				
			data.setCardNumber(cardNumber);
			
			Controller.openBuyCardTicketWindow (data);

		} else {
			errorField = "Pin numbers not valid";
			throw new CreateSystemException(errorField);
		}
	}

	public static void pay(DataExchange data) throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String errorField = "";

		String cardNumberInputField = Card.realFormatCardNumber(data.getCardNumber());
		String pinInputField = data.getPin();
		String amountInputField = data.getAmount();
		
		Card cardPay = CardList.cardNumberInSystem(cardNumberInputField);
		if (cardPay == null) {
			errorField = "The card is not registered in the system";
			throw new CreateSystemException(errorField);
		}
		if (!cardPay.getPin().equals(Card.sha256(pinInputField))) {
			errorField = "The pin is incorrect";
			throw new CreateSystemException(errorField);
		}
		double amount = 0;
		try {
			amount = Double.parseDouble(amountInputField);
		} catch (NumberFormatException e2) {
			errorField = "Amount not a number";
			throw new NumberFormatException(errorField);
		}
		if (cardPay.getBalance() < amount) {
			errorField = "The card does not have enough money";
			throw new CreateSystemException(errorField);
		}
		if (Double.parseDouble(amountInputField) <= 0) {
			errorField = "Amount must be greater than zero";
			throw new CreateSystemException(errorField);
		}

		int expirationDateLong = cardPay.getExpirationDateLong();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(dtf);

		String dateAux;
		dateAux = formattedDateTime.substring(6, 8) + formattedDateTime.substring(0, 2)
				+ formattedDateTime.substring(3, 5);
		int nowDateTime = Integer.parseInt(dateAux);

		if (expirationDateLong < nowDateTime) {
			errorField = "The card has expired";
			throw new CreateSystemException(errorField);
		}

		cardPay.setBalance(cardPay.getBalance() - amount);

		Movement movement = null;
		movement = new Movement(cardPay.getCardNumber(), formattedDateTime, amount * (-1));
		Lists.getMovementsList().getMovementsList().add(movement);
		
		data.setCard(cardPay);
		
		Controller.openChargeTicketWindow (data);
	}
	
	public static void chargeMoney(DataExchange data)
			throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String errorField = "";
		String cardNumberInputField = Card.realFormatCardNumber(data.getCardNumber());
		String pinInputField = data.getPin();
		String amountInputField = data.getAmount();
		
		Card cardChargeMoney = CardList.cardNumberInSystem(cardNumberInputField);
		if (cardChargeMoney == null) {
			errorField = "The card is not registered in the system";
			throw new CreateSystemException(errorField);
		}
		if (!cardChargeMoney.getPin().equals(Card.sha256(pinInputField))) {
			errorField = "The pin is incorrect";
			throw new CreateSystemException(errorField);
		}
		double amount = 0;
		try {
			amount = Double.parseDouble(amountInputField);
		} catch (NumberFormatException e2) {
			errorField = "Amount not a number";
			throw new NumberFormatException(errorField);
		}

		int expirationDateLong = cardChargeMoney.getExpirationDateLong();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(dtf);

		String dateAux;
		dateAux = formattedDateTime.substring(6, 8) + formattedDateTime.substring(0, 2)
				+ formattedDateTime.substring(3, 5);
		int nowDateTime = Integer.parseInt(dateAux);

		if (expirationDateLong < nowDateTime) {
			errorField = "The card has expired";
			throw new CreateSystemException(errorField);
		}
		if (Double.parseDouble(amountInputField) <= 0) {
			errorField = "Amount must be greater than zero";
			throw new CreateSystemException(errorField);
		}

		cardChargeMoney.setBalance(cardChargeMoney.getBalance() + amount);

		Movement movement = null;
		movement = new Movement(cardChargeMoney.getCardNumber(), formattedDateTime, amount);
		Lists.getMovementsList().getMovementsList().add(movement);
		
		data.setCard(cardChargeMoney);
		
		Controller.openChargeMoneyWindow (data);

	}
 
	public static void changePin(DataExchange data)
			throws CreateNumberFormatException, NoSuchAlgorithmException, UnsupportedEncodingException, CreateSystemException {
		
		String cardNumberInputField = Card.realFormatCardNumber(data.getCardNumber());
		String pinInputField = data.getPin();
		String newPinInputField = data.getPinRepeated();

		String errorField = "";
		Card cardChangePin = CardList.cardNumberInSystem(cardNumberInputField);
		if (cardChangePin == null) {
			errorField = "The card is not registered in the system";
			throw new CreateSystemException(errorField);
		}
		if (newPinInputField.length() != 4) {
			errorField = "Pin must have four digits";
			throw new CreateNumberFormatException(errorField);		
		}

		if (!cardChangePin.getPin().equals(Card.sha256(pinInputField)) ) {
			errorField = "The pin is incorrect";
			throw new CreateSystemException(errorField);
		}
		if (pinInputField.equals(newPinInputField) ) {
			errorField = "The new pin cannot be the same as the current one";
			throw new CreateSystemException(errorField);
		}

		cardChangePin.setPin(newPinInputField);
	}

	public static String consultBalance(DataExchange data)
			throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String cardNumberInputField = Card.realFormatCardNumber(data.getCardNumber());
		String pinInputField = data.getPin();
		
		String errorField = "";
		Card cardConsultBalance = CardList.cardNumberInSystem(cardNumberInputField);
		if (cardConsultBalance == null) {
			errorField = "The card is not registered in the system";
			throw new CreateSystemException(errorField);
		}
		if (!cardConsultBalance.getPin().equals(Card.sha256(pinInputField))) {
			errorField = "The pin is incorrect";
			throw new CreateSystemException(errorField);
		}
	
		data.setCard(cardConsultBalance);
		Controller.openConsultBalanceTicketWindow (data);

		return errorField;
	}

	

	public static String consultMovements(DataExchange data)
			throws CreateSystemException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String cardNumberInputField = Card.realFormatCardNumber(data.getCardNumber());
		String pinInputField = data.getPin();
 
		String errorField = "";
		Card consultMovements = CardList.cardNumberInSystem(cardNumberInputField);
		if (consultMovements == null) {
			errorField = "The card is not registered in the system";
			throw new CreateSystemException(errorField);
		}
		if (!consultMovements.getPin().equals(Card.sha256(pinInputField))) {
			errorField = "The pin is incorrect";
			throw new CreateSystemException(errorField);
		}
		
		Controller.openConsultMovementsTicketWindow (data);

		return errorField;
	}
	

	public static void marshalingDataBase() throws JAXBException {

		if (!Lists.getCardList().getCardList().isEmpty()) {
			JAXBContext jaxbContextCardList = JAXBContext.newInstance(CardList.class);
			Marshaller jaxbMarshallerCardList = jaxbContextCardList.createMarshaller();

			jaxbMarshallerCardList.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerCardList.marshal(Lists.getCardList(), new File("cardList.xml"));
		}

		if (!Lists.getMovementsList().getMovementsList().isEmpty()) {
			JAXBContext jaxbContextCardMovements = JAXBContext.newInstance(CardMovements.class);
			Marshaller jaxbMarshallerCardMovements = jaxbContextCardMovements.createMarshaller();

			jaxbMarshallerCardMovements.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerCardMovements.marshal(Lists.getMovementsList(), new File("cardMovements.xml"));
		}
	}

	public static void unMarshalingDataBase() throws JAXBException {

		File cardListFile = new File("cardList.xml");
		if (cardListFile.exists()) {
			JAXBContext jaxbContextCardList = JAXBContext.newInstance(CardList.class);
			Unmarshaller jaxbUnmarshallerCardList = jaxbContextCardList.createUnmarshaller();
			Lists.setCardList((CardList) jaxbUnmarshallerCardList.unmarshal(cardListFile));
		}
		File movementsListFile = new File("cardMovements.xml");
		if (movementsListFile.exists()) {
			JAXBContext jaxbContextCardMovements = JAXBContext.newInstance(CardMovements.class);
			Unmarshaller jaxbUnmarshallerCardMovements = jaxbContextCardMovements.createUnmarshaller();
			Lists.setMovementsList((CardMovements) jaxbUnmarshallerCardMovements.unmarshal(movementsListFile));
		}
	}
	
	public static JFormattedTextField prepareAmountInputField (int x1, int y1, int x2, int y2) {
		
		JFormattedTextField amountInputField;
		
		NumberFormat formatAmount = NumberFormat.getCurrencyInstance(Locale.US);
		DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) formatAmount).getDecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("");
		((DecimalFormat) formatAmount).setDecimalFormatSymbols(decimalFormatSymbols);
		formatAmount.setMaximumFractionDigits(2);
		NumberFormatter formatter = new NumberFormatter(formatAmount);
		formatter.setMinimum(0.01);
		formatter.setMaximum(10000000.0);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		amountInputField = new JFormattedTextField(formatter);
		amountInputField.setValue(0.0);
		amountInputField.setBounds(x1, y1, x2, y2);
		amountInputField.setEditable(true);
		return amountInputField;
	}
	
	public static JPasswordField preparePinField (int x1, int y1, int x2, int y2) {
		
		JPasswordField pinInputField;
		PlainDocument document;
		
		pinInputField = new JPasswordField();
		pinInputField.setBounds(x1, y1, x2, y2);
		pinInputField.setEditable(true);
		document = (PlainDocument) pinInputField.getDocument();
		
		Controller.setDocumentFilterAproach(document);  
		
		return pinInputField;
	}
	


}

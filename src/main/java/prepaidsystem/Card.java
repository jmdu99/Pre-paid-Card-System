package prepaidsystem;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import prepaidsystemexceptions.CreateCardException;


@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {
	private String name;
	private String surname;
	private String cardNumber;
	private String pin;
	private String expirationDate;
	private double balance;
	private int expirationDateLong;
	
	public Card(String name, String surname, String cardNumber, String pin, String expirationDate, double balance)
			throws CreateCardException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if (name.equals("") || surname.equals("")) {
			throw new CreateCardException("User can not be created. Name and surname cannot be blank.");
		} else if (cardNumber.length() != 12) {
			throw new CreateCardException("The cardNumber is not valid, it must have 12 digits");
		} else if (pin.length() != 4) {
			throw new CreateCardException("The pin is not valid, it must have 4 digits");
		} else if (expirationDate.length() != 8) {
			throw new CreateCardException("The expirationDate is not valid, it must be like MM/DD/YY");
		} else if (balance == 0.0) {
			throw new CreateCardException("Card must be charged with money");
		} else {
			this.name = name;
			this.surname = surname;
			this.cardNumber = cardNumber;
			this.pin = sha256(pin);
			this.expirationDate = expirationDate;
			this.balance = balance;
			
			String dateAux;
			dateAux = expirationDate.substring(6, 8) + expirationDate.substring(0, 2) + expirationDate.substring(3, 5);
			
			expirationDateLong = Integer.parseInt(dateAux);
		}
	}
	public Card() throws CreateCardException {
		this.name = null;
		this.surname = null;
		this.cardNumber = null;
		this.pin = null;
		this.expirationDate = null;
		this.balance = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.pin = sha256(pin);
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public int getExpirationDateLong() {
		return expirationDateLong;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Card [name=" + name + "surname=" + surname + ", cardNumber=" + cardNumber + ", pin=" + pin
				+ ", expirationDate=" + expirationDate + ", balance=" + balance + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(balance, cardNumber, expirationDate, expirationDateLong, name, pin, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		else if (obj == null) return false;
		else if (!(obj instanceof Card)) return false;
		
		Card other = (Card) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(cardNumber, other.cardNumber) 
				&& Objects.equals(expirationDateLong, other.expirationDateLong)
				&& Objects.equals(expirationDate, other.expirationDate)
				&& Objects.equals(name, other.name)
				&& Objects.equals(pin, other.pin) 
				&& Objects.equals(surname, other.surname);
	}
	
	public static String sha256(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(base.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public static String getNewCardNumber() {
		long lastCardNumber = 0;
		CardList cardList = Lists.getCardList();
		
		for (Card card : cardList.getCardList()) {
			if (Long.parseLong(card.getCardNumber()) > lastCardNumber) {
				lastCardNumber = Long.parseLong(card.getCardNumber());
			}
		}
		lastCardNumber++;
		String cardNumber = Long.toString(lastCardNumber);
		cardNumber = ("000000000000" + cardNumber).substring(cardNumber.length());
		return cardNumber;
	}
	
	public static String realFormatCardNumber(String cardNumber) {
		return ("000000000000" + cardNumber).substring(cardNumber.length());
	}
	
	public void setExpirationDateLong(int expirationDateLong) {
		this.expirationDateLong = expirationDateLong;
	}
}

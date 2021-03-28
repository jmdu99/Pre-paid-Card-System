package prepaidsystem;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import prepaidsystemexceptions.CreateMovementException;


@XmlRootElement(name = "movement")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movement implements Comparable<Movement> {

	private String cardNumber;
	private String date;
	private double amount;
	private int dateSort;

	public Movement(String cardNumber, String date, double amount) {
		this.cardNumber = cardNumber;
		this.date = date;
		this.amount = amount;

		String dateAux;
		dateAux = date.substring(6, 8) + date.substring(0, 2) + date.substring(3, 5);

		dateSort = Integer.parseInt(dateAux);
	}

	public Movement() throws CreateMovementException {
		this.cardNumber = null;
		this.date = null;
		this.amount = 0;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getDate() {
		return date;
	}

	public int getDateLong() {
		return dateSort;
	}

	public double getAmount() {
		return amount;
	}
	
	public void setdateSort(int dateSort) {
		this.dateSort = dateSort;
	}

	public boolean equalMovements(Movement m) {
		boolean equals = false;
		if (this.cardNumber.contentEquals(m.getCardNumber()) && 
				this.amount == m.getAmount() && 
				this.date == m.getDate() && 
				this.dateSort == m.getDateLong()) {
			equals = true;
		}
		return equals;
	}

	@Override
	public int compareTo(Movement m) {
		return (dateSort - m.getDateLong());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;
		return equalMovements((Movement) o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, date, amount, dateSort);
	}
}
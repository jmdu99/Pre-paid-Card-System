package controller;

import java.awt.Color;

import prepaidsystem.Card;


public class DataExchange {
	String name;
	String surname;
	String pin;
	String pinRepeated;
	String amount;
	String cardNumber;
	Card card;
	boolean gui;
	Color windowBackgroudColor;
	Object openerWindow; 
	int windowToOpenInt; 
	
	public DataExchange (String name, String surname, String pin, String pinRepeated, String amount, boolean gui, Object window) {
		this.name = name;
		this.surname = surname;
		this.pin = pin;
		this.pinRepeated = pinRepeated;
		this.amount = amount;
		this.gui = gui;
		this.openerWindow = window;
	}
	
	public DataExchange (String cardNumber, String pin, String amount, boolean gui, Object window) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.amount = amount;
		this.gui = gui;
		this.openerWindow = window;
	}
	
	public DataExchange (String cardNumber, String pin, String pinRepeated) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.pinRepeated = pinRepeated;
	}
	
	public DataExchange (String cardNumber, String pin, boolean gui, Object window) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.gui = gui;
		this.openerWindow = window;
	}
	
	public DataExchange (int windowToOpenInt) {
		this.windowToOpenInt = windowToOpenInt;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPin() {
		return pin;
	}

	public String getPinRepeated() {
		return pinRepeated;
	}

	public String getAmount() {
		return amount;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public boolean getGui() {
		return gui;
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public void setGUI(boolean gui) {
		this.gui = gui;
	}
	
	public int getwindowToOpenInt() {
		return windowToOpenInt;
	}
	
	public Object getOpenerWindow() {
		return openerWindow;
	}
}
 
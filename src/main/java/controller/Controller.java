package controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.xml.bind.JAXBException;

import prepaidsystem.Card;
import prepaidsystem.PrepaidSystem;

import java.util.Locale;
import prepaidsystemgui.BuyCardWindow;
import prepaidsystemgui.ChangePinWindow;
import prepaidsystemgui.ChargeMoneyWindow;
import prepaidsystemgui.ChargePayTicketWindow;
import prepaidsystemgui.ConsultBalanceTicketWindow;
import prepaidsystemgui.ConsultBalanceWindow;
import prepaidsystemgui.ConsultMovementsTicketWindow;
import prepaidsystemgui.ConsultMovementsWindow;
import prepaidsystemgui.MainWindow;
import prepaidsystemgui.PayWindow;
import prepaidsystemgui.BuyCardTicketWindow;


public class Controller {

	public static void openWindow (DataExchange data) {
		
		data.setGUI(true);
	    switch (data.getwindowToOpenInt())
	    {
	      case 1:
				BuyCardWindow buyCardWindow = new BuyCardWindow(data);
				buyCardWindow.setVisible(true);
				break;
	      case 2:
				PayWindow payWindow= new PayWindow(data);
				payWindow.setVisible(true);
				break;
	      case 3:
				ChargeMoneyWindow chargeMoneyWindow= new ChargeMoneyWindow(data);
				chargeMoneyWindow.setVisible(true);
				break;
	      case 4:
	    	    ChangePinWindow changePinWindow= new ChangePinWindow(data);
				changePinWindow.setVisible(true);
				break;
	      case 5:  			
				ConsultBalanceWindow consultBalanceWindow = new ConsultBalanceWindow(data);
				consultBalanceWindow.setVisible(true);
				break;
	      case 6:  			
				ConsultMovementsWindow consultMovements = new ConsultMovementsWindow(data);
				consultMovements.setVisible(true);
				break;
	      default:      
	    	  	break;
	    }
	 
	}
	

	public static void openBuyCardTicketWindow (DataExchange data) {
		
		String name = data.getName();
		String surname = data.getSurname();
		String cardNumber = Card.realFormatCardNumber(data.getCardNumber());
		BuyCardWindow window = (BuyCardWindow) data.getOpenerWindow();
		double amount = Double.parseDouble(data.getAmount());
		
		if (data.getGui()) {
			BuyCardTicketWindow ticket = new BuyCardTicketWindow(true, name, surname, cardNumber, amount);
			ticket.setVisible(true);
			window.setVisible(false);
			window.dispose();
		}
	}
	
	public static void openChargeMoneyWindow(DataExchange data) {
		
		ChargeMoneyWindow window = (ChargeMoneyWindow) data.getOpenerWindow();
		
		if (data.getGui()) {
			ChargePayTicketWindow chargeTicketWindow = new ChargePayTicketWindow(true, data);
			chargeTicketWindow.setVisible(true);
			window.setVisible(false);
			window.dispose();
		}
	}
	
	public static void openChargeTicketWindow (DataExchange data) {
		
		PayWindow window = (PayWindow) data.getOpenerWindow(); 
		
		if (data.getGui()) {
			ChargePayTicketWindow chargeTicketWindow = new ChargePayTicketWindow(true, data);

			chargeTicketWindow.setVisible(true);
			window.setVisible(false);
			window.dispose();
		}
	}
	
	public static void openConsultBalanceTicketWindow(DataExchange data) {
		
		ConsultBalanceWindow window = (ConsultBalanceWindow) data.getOpenerWindow(); 
		
		if (data.getGui()) {
			ConsultBalanceTicketWindow consultBalanceTicketWindow = new ConsultBalanceTicketWindow(true, data);

			consultBalanceTicketWindow.setVisible(true);
			window.setVisible(false);
			window.dispose();
		}
	}
	
	public static void openConsultMovementsTicketWindow(DataExchange data) {
				
		ConsultMovementsWindow window = (ConsultMovementsWindow) data.getOpenerWindow(); 
		
		if (data.getGui()) {
			ConsultMovementsTicketWindow consultMovementsTicketWindow = new ConsultMovementsTicketWindow(true, data);

			consultMovementsTicketWindow.setVisible(true);
			window.setVisible(false);
			window.dispose();
		}

	}
	
	public static void setDocumentFilterAproach(PlainDocument document) {
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
				if (string.length() <= 4) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
	}
	
	public static void main(String[] args) throws JAXBException {

		PrepaidSystem.unMarshalingDataBase();
		Locale.setDefault(Locale.US);

		MainWindow pantalla = new MainWindow();
		pantalla.setVisible(true);
	}
}

 
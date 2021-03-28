package prepaidsystemgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import controller.DataExchange;
import prepaidsystem.Card;


public class ConsultBalanceTicketWindow extends JFrame implements ActionListener {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private JButton acceptButton;

		JPanel panelConsultBalanceTicketWindow;
		JTextPane outputField;

		public ConsultBalanceTicketWindow(boolean openWindow, DataExchange data) {

			Card card = data.getCard();
			
			// Frame characteristics
			setTitle("BALANCE TICKET");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(350, 300);
			this.setResizable(false);
			this.setVisible(openWindow);
			this.setLocationRelativeTo(null);

			// Panel characteristics
			panelConsultBalanceTicketWindow = (JPanel) this.getContentPane();
			panelConsultBalanceTicketWindow.setLayout(null);

			// Output field
			outputField =  GenericFunctions.createOutputField(50, 50, 250, 150);
			
			String outputData = ("Dear " + card.getName() + " " + card.getSurname() + ", \n\nCard Number: XXXX XXXX " 
					+ card.getCardNumber().substring(8, 12) + "\n" + "Balance: " + card.getBalance() + 
					"\n\n" + "Thanks for using our system");
	
			outputField.setText(outputData);
			add(outputField);


			// Button to accept
			acceptButton =  GenericFunctions.createButton("Accept", 125, 220, 100, 40);
			panelConsultBalanceTicketWindow.add(acceptButton);
			acceptButton.addActionListener(this);

			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			setVisible(false);
			dispose();
		}
		


}

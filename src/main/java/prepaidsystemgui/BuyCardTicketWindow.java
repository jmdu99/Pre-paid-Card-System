package prepaidsystemgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class BuyCardTicketWindow extends JFrame implements ActionListener {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private JButton acceptButton;

		JPanel panelTicketWindow;

		JTextPane outputField;

		public BuyCardTicketWindow(boolean openWindow, String name, String surname, String cardNumber, double balance) {

			// Frame characteristics
			setTitle("TICKET");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(350, 300);
			this.setResizable(false);
			this.setVisible(openWindow);
			this.setLocationRelativeTo(null);

			// panel characteristics
			panelTicketWindow = (JPanel) this.getContentPane();
			panelTicketWindow.setLayout(null);

			// Output field
			outputField =  GenericFunctions.createOutputField(50, 50, 250, 150);
			
			outputField.setText("\nDear " + name + " " + surname + ", \n\n" + 
			"Card Number: " + cardNumber.substring(0, 4) + " " + cardNumber.substring(4, 8) + " " 
			+ cardNumber.substring(8, 12) + "\nBalance: " + balance + "\n\n" + "Thanks for using our system");
			add(outputField);

			// Button to accept
			acceptButton =  GenericFunctions.createButton("Accept", 125, 220, 100, 40);
			panelTicketWindow.add(acceptButton);
			acceptButton.addActionListener(this);

			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			setVisible(false);
			dispose();
		}

}

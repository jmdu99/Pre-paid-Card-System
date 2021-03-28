package prepaidsystemgui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import controller.DataExchange;
import prepaidsystem.Card;
import prepaidsystem.CardList;
import prepaidsystem.CardMovements;
import prepaidsystem.Lists;
import prepaidsystem.Movement;


public class ConsultMovementsTicketWindow extends JFrame implements ActionListener {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private JButton acceptButton;

		JPanel panelMovementListWindow;

		JTextPane outputField;
		JScrollPane scrollpanel;

		public ConsultMovementsTicketWindow(boolean openWindow, DataExchange data) {

			String cardNumber = Card.realFormatCardNumber(data.getCardNumber());
			// Frame characteristics
			setTitle("MOVEMENTS");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(350, 400);
			this.setResizable(false);
			this.setVisible(openWindow);
			this.setLocationRelativeTo(null);

			// Panel characteristics
			panelMovementListWindow = (JPanel) this.getContentPane();
			panelMovementListWindow.setLayout(null);

			// Output field
			outputField =  GenericFunctions.createOutputField(50, 50, 250, 150);
			
			CardMovements movementsCard = new CardMovements();
			movementsCard.setCardMovements(new ArrayList<Movement>());
			Movement m;
			
			for (int i = 0; i < Lists.getMovementsList().getMovementsList().size(); i++) {
				
				if (cardNumber.equals(Lists.getMovementsList().getMovementsList().get(i).getCardNumber())) {
					m = Lists.getMovementsList().getMovementsList().get(i);
					movementsCard.getMovementsList().add(m);
				}
			}	
			
			Collections.sort(movementsCard.getMovementsList()); 
			
			Card card = CardList.cardNumberInSystem(cardNumber);

			String outputData = ("Dear " + card.getName() + " " + card.getSurname() + ", \n\n" + 
			"Card Number: XXXX XXXX " + cardNumber.substring(8, 12) + "\n\n");
					
			Locale currentLocale = Locale.getDefault();

			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale); 
			otherSymbols.setDecimalSeparator('.'); 
			otherSymbols.setGroupingSeparator(','); 
			DecimalFormat df = new DecimalFormat("#####0.00", otherSymbols);

			double dd;
			double dd2dec = 0;
			for (int i = 0; i < movementsCard.getMovementsList().size(); i++) {
				dd = movementsCard.getMovementsList().get(i).getAmount();
				dd2dec = Double.parseDouble(df.format(dd));
				String dd2decString = Double.toString(dd2dec);
				String dot= dd2decString.substring(dd2decString.length()-2, dd2decString.length()-1);
				if (dot.equals(".")) {
					dd2decString = dd2decString + "0";
				}
				StringBuilder bld1 = new StringBuilder();
				for (int j = 0; j < 10 - dd2decString.length(); j++) {
					bld1.append(" ");
				}
				String blank = bld1.toString();
				dd2decString = blank + dd2decString;
				StringBuilder bld2 = new StringBuilder();
				bld2.append(outputData);
				bld2.append(movementsCard.getMovementsList().get(i).getDate());
				bld2.append("    ");
				bld2.append(dd2decString);
				bld2.append("\n");
				outputData = bld2.toString();
			}
			outputData = outputData + "\nThanks for using our system";
			outputField.setText(outputData);
			add(outputField);
	        scrollpanel = new JScrollPane();
	        scrollpanel.setBounds(new Rectangle(50, 50, 250, 200));
	        scrollpanel.setViewportView(outputField);
	        add(scrollpanel);
	        
			// Button to accept
			acceptButton =  GenericFunctions.createButton("Accept", 125, 300, 100, 40);
			panelMovementListWindow.add(acceptButton);
			acceptButton.addActionListener(this);

			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			setVisible(false);
			dispose();
		}

}

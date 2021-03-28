package prepaidsystemgui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import controller.Controller;
import controller.DataExchange;
import prepaidsystem.PrepaidSystem;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
	
	private static final long serialVersionUID = 1L;

	private ImageIcon logo = new ImageIcon(getClass().getResource("/Components/logo.jpg"));

	private JButton buyCardButton;
	private JButton payButton;
	private JButton chargeMoneyButton;
	private JButton changePinButton;
	private JButton consultBalanceButton;
	private JButton consultMovementsButton;

	private JLabel backgroundPanel;

	JPanel panelMainWindow;
	PrepaidSystem ourPrepaidSystem;

	public MainWindow() {

		LOGGER.info("Logger Name: "+LOGGER.getName());
		// Frame characteristics
		setTitle("EJAbank");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// Panel characteristics
		panelMainWindow = (JPanel) this.getContentPane();
		panelMainWindow.setLayout(null);

		// Logo in panel
		backgroundPanel = new JLabel();
		backgroundPanel.setIcon(logo);
		backgroundPanel.setBounds(0, -2, 700, 250);
		panelMainWindow.add(backgroundPanel);
		backgroundPanel.setOpaque(false);

		// Button to buy a card
		buyCardButton =  GenericFunctions.createButton("Buy a card", 150, 270, 400, 40);
		panelMainWindow.add(buyCardButton);
		buyCardButton.addActionListener(this);

		// Button to pay
		payButton =  GenericFunctions.createButton("Pay", 150, 310, 400, 40);
		panelMainWindow.add(payButton);
		payButton.addActionListener(this);

		// Button to charge money
		chargeMoneyButton =  GenericFunctions.createButton("Charge money", 150, 350, 400, 40);
		panelMainWindow.add(chargeMoneyButton);
		chargeMoneyButton.addActionListener(this);

		// Button to change pin
		changePinButton =  GenericFunctions.createButton("Change PIN", 150, 390, 400, 40);
		panelMainWindow.add(changePinButton);
		changePinButton.addActionListener(this);

		// Button to consult balance
		consultBalanceButton =  GenericFunctions.createButton("Consult balance", 150, 430, 400, 40);
		panelMainWindow.add(consultBalanceButton);
		consultBalanceButton.addActionListener(this);

		// Button to consult movements
		consultMovementsButton =  GenericFunctions.createButton("Consult movements", 150, 470, 400, 40);
		panelMainWindow.add(consultMovementsButton);
		consultMovementsButton.addActionListener(this);

		repaint();
	}

	@Override
	public void dispose() {
		try {
			PrepaidSystem.marshalingDataBase();
		} catch (JAXBException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
		super.dispose();
	}

	public void actionPerformed(ActionEvent e) {

		int ventana = 0;
		
		if (e.getSource() == buyCardButton) {
			ventana = 1;
		} else if (e.getSource() == payButton) {
			ventana = 2;
		} else if (e.getSource() == chargeMoneyButton) {
			ventana = 3;
		} else if (e.getSource() == changePinButton) {
			ventana = 4;
		} else if (e.getSource() == consultBalanceButton) {
			ventana = 5;
		} else if (e.getSource() == consultMovementsButton) {
			ventana = 6;
		}
		DataExchange data = new DataExchange(ventana);
		Controller.openWindow(data);
	}

}

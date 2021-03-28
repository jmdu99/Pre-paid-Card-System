package prepaidsystemgui;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import controller.DataExchange;
import prepaidsystem.PrepaidSystem;
import prepaidsystemexceptions.CreateCardException;
import prepaidsystemexceptions.CreateSystemException;


public class BuyCardWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton acceptButton;
	private JButton cancelButton;

	JPanel panelBuyCardWindow;

	JTextField nameInputField;
	JTextField surnameInputField; 
	JPasswordField pinInputFieldBuyCard; 
	JPasswordField repeatPinInputFieldBuyCard;
	JFormattedTextField amountInputFieldBuyCard;
	JTextPane errorFieldBuyCardWindow;
	
	PlainDocument document;

	public BuyCardWindow(DataExchange data) {

		// Frame characteristics
		setTitle("BUY A CARD");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 420);
		this.setResizable(false);
		this.setVisible(data.getGui());
		this.setLocationRelativeTo(null);

		// Panel characteristics
		panelBuyCardWindow = (JPanel) this.getContentPane();
		panelBuyCardWindow.setLayout(null);

		// Buy a card
		JLabel windowTitle = new JLabel("BUY A CARD");
		panelBuyCardWindow.add(windowTitle);
		windowTitle.setFont(windowTitle.getFont().deriveFont(32.0F));
		Dimension sizeWindowTitle = windowTitle.getPreferredSize();
		windowTitle.setBounds(150, 30, sizeWindowTitle.width, sizeWindowTitle.height);

		// Name label
		JLabel nameLabel = GenericFunctions.createLabel("Name", 40, 100);
		panelBuyCardWindow.add(nameLabel);

		// Name input field
		nameInputField = GenericFunctions.createInputField(100, 100, 350, 20);
		add(nameInputField);

		// Surname label
		JLabel surnameLabel = GenericFunctions.createLabel("Surname", 40, 150);
		panelBuyCardWindow.add(surnameLabel);

		// Surname input field
		surnameInputField = GenericFunctions.createInputField(100, 150, 350, 20);
		add(surnameInputField);

		// Pin label
		JLabel pinLabel = GenericFunctions.createLabel("PIN", 40, 200);
		panelBuyCardWindow.add(pinLabel);
		
		// Pin input field
		pinInputFieldBuyCard = PrepaidSystem.preparePinField(100, 200, 100, 20);
		add(pinInputFieldBuyCard);

		// Repeat Pin label
		JLabel repeatPinLabel = GenericFunctions.createLabel("Repeat PIN", 260, 200);
		panelBuyCardWindow.add(repeatPinLabel);

		// Repeat Pin input field
		repeatPinInputFieldBuyCard = PrepaidSystem.preparePinField(350, 200, 100, 20);
		add(repeatPinInputFieldBuyCard);

		// Amount label
		JLabel amountLabel = GenericFunctions.createLabel("Amount", 40, 250);
		panelBuyCardWindow.add(amountLabel);

		// € label
		JLabel eLabel = GenericFunctions.createLabel("€", 210, 250);
		panelBuyCardWindow.add(eLabel);

		// Amount input field   
		amountInputFieldBuyCard = PrepaidSystem.prepareAmountInputField(100, 250, 100, 20);
		add(amountInputFieldBuyCard);
		
		// Button to accept
		acceptButton =  GenericFunctions.createButton("Accept", 50, 300, 200, 40);
		panelBuyCardWindow.add(acceptButton);
		acceptButton.addActionListener(this);

		// Button to cancel
		cancelButton =  GenericFunctions.createButton("Cancel", 250, 300, 200, 40);
		panelBuyCardWindow.add(cancelButton);
		cancelButton.addActionListener(this);

		// Errors
		errorFieldBuyCardWindow = GenericFunctions.errorField(50, 350, 400, 40);
		add(errorFieldBuyCardWindow);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == acceptButton) {
			String errors = "";
			try {
				String pin1String = String.valueOf(pinInputFieldBuyCard.getPassword());
				String pin2String = String.valueOf(repeatPinInputFieldBuyCard.getPassword());
				
				DataExchange data = new DataExchange(nameInputField.getText(), surnameInputField.getText(), pin1String, 
						pin2String, amountInputFieldBuyCard.getValue().toString().replace(",", ""), true, this);
				PrepaidSystem.buyCard(data);
			} catch (CreateSystemException | CreateCardException  | NumberFormatException | NoSuchAlgorithmException | UnsupportedEncodingException e1) {
				errors=e1.getMessage();
			}
			errorFieldBuyCardWindow.setText(errors);
			if (errors.equals("")) {
				setVisible(false);
				dispose();
			}
			
		} else if (e.getSource() == cancelButton) {
			setVisible(false);
			dispose();
		}
	}
	

}
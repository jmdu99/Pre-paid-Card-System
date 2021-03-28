package prepaidsystemgui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import controller.DataExchange;
import java.util.logging.Logger;

import prepaidsystem.PrepaidSystem;
import prepaidsystemexceptions.CreateSystemException;

public class PayWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(PayWindow.class.getName());
	private static final long serialVersionUID = 1L;

	private JButton acceptButton;
	private JButton cancelButton;

	JPanel panelPayWindow;

	JTextField cardNumberInputField;
	JPasswordField pinInputFieldPay;
	JTextPane errorFieldPayWindow;
	JFormattedTextField amountInputField;
	
	PlainDocument document;

	public PayWindow(DataExchange data) {

		LOGGER.info("Logger Name: "+LOGGER.getName());
		// Frame characteristics
		setTitle("PAY");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(350, 360);
		this.setResizable(false);
		this.setVisible(data.getGui());
		this.setLocationRelativeTo(null);

		// Panel characteristics
		panelPayWindow = (JPanel) this.getContentPane();
		panelPayWindow.setLayout(null);

		// Pay
		JLabel windowTitle = new JLabel("PAY");
		panelPayWindow.add(windowTitle);
		windowTitle.setFont(windowTitle.getFont().deriveFont(24.0F));
		Dimension sizeWindowTitle = windowTitle.getPreferredSize();
		windowTitle.setBounds(150, 30, sizeWindowTitle.width, sizeWindowTitle.height);

		// cardNumber label
		JLabel cardNumberLabel = GenericFunctions.createLabel("Card number", 40, 100);
		panelPayWindow.add(cardNumberLabel);

		// cardNumber input field
		cardNumberInputField = GenericFunctions.createInputField(140, 100, 180, 20);
		add(cardNumberInputField);

		// Pin label
		JLabel pinLabel = new JLabel("PIN");
		panelPayWindow.add(pinLabel);
		Dimension sizePinLabel = pinLabel.getPreferredSize();
		pinLabel.setBounds(40, 150, sizePinLabel.width, sizePinLabel.height);

		// Pin input field
		pinInputFieldPay = PrepaidSystem.preparePinField(140, 150, 100, 20);
		add(pinInputFieldPay);
		
		// Amount label
		JLabel amountLabel = new JLabel("Amount");
		panelPayWindow.add(amountLabel);
		Dimension sizeAmountLabel = amountLabel.getPreferredSize();
		amountLabel.setBounds(40, 200, sizeAmountLabel.width, sizeAmountLabel.height);

		// € label
		JLabel eLabel = new JLabel("€");
		panelPayWindow.add(eLabel);
		Dimension sizeELabel = eLabel.getPreferredSize();
		eLabel.setBounds(250, 200, sizeELabel.width, sizeELabel.height);

		// Amount input field
		amountInputField = PrepaidSystem.prepareAmountInputField(140, 200, 100, 20);
		add(amountInputField);

		// Button to accept
		acceptButton =  GenericFunctions.createButton("Accept", 50, 250, 100, 40);
		panelPayWindow.add(acceptButton);
		acceptButton.addActionListener(this);

		// Button to cancel
		cancelButton =  GenericFunctions.createButton("Cancel", 200, 250, 100, 40);
		panelPayWindow.add(cancelButton);
		cancelButton.addActionListener(this);

		// Errors
		errorFieldPayWindow = GenericFunctions.errorField(50, 300, 300, 40);
		add(errorFieldPayWindow);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == acceptButton) {
			String errors = "";
			 try {
				String pin1String = String.valueOf(pinInputFieldPay.getPassword());
				DataExchange data = new DataExchange(cardNumberInputField.getText(), pin1String,
						amountInputField.getText().replace(",", ""), true, this);
				PrepaidSystem.pay(data);
			} catch (CreateSystemException | NumberFormatException  | NoSuchAlgorithmException | UnsupportedEncodingException e1) {
				errors=e1.getMessage();
			} 
			errorFieldPayWindow.setText(errors);
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
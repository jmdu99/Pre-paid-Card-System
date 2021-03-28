package prepaidsystemgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import controller.DataExchange;
import prepaidsystem.PrepaidSystem;
import prepaidsystemexceptions.CreateSystemException;


public class ConsultBalanceWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton acceptButton;
	private JButton cancelButton;

	JPanel panelConsultBalanceWindow;

	JTextField cardNumberInputField;
	JPasswordField pinInputFieldConsultBalance;
	JTextPane errorFieldConsultBalanceWindow;

	PlainDocument document;

	public ConsultBalanceWindow(DataExchange data) {

		// Frame characteristics
		setTitle("CONSULT BALANCE");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(350, 310);
		this.setResizable(false);
		this.setVisible(data.getGui());
		this.setLocationRelativeTo(null);

		// panel characteristics
		panelConsultBalanceWindow = (JPanel) this.getContentPane();
		panelConsultBalanceWindow.setLayout(null);

		// Consult balance
		JLabel windowTitle = new JLabel("CONSULT BALANCE");
		panelConsultBalanceWindow.add(windowTitle);
		windowTitle.setFont(windowTitle.getFont().deriveFont(24.0F));
		Dimension sizeWindowTitle = windowTitle.getPreferredSize();
		windowTitle.setBounds(50, 30, sizeWindowTitle.width, sizeWindowTitle.height);

		// cardNumber label
		JLabel cardNumberLabel = GenericFunctions.createLabel("Card number", 40, 100);
		panelConsultBalanceWindow.add(cardNumberLabel);

		// cardNumber input field
		cardNumberInputField = GenericFunctions.createInputField(140, 100, 180, 20);
		add(cardNumberInputField);

		// Pin label
		JLabel pinLabel = GenericFunctions.createLabel("PIN", 40, 150);
		panelConsultBalanceWindow.add(pinLabel);

		// Pin input field
		pinInputFieldConsultBalance = PrepaidSystem.preparePinField(140, 150, 100, 20);
		add(pinInputFieldConsultBalance);

		// Button to accept
		acceptButton =  GenericFunctions.createButton("Accept", 50, 200, 100, 40);
		panelConsultBalanceWindow.add(acceptButton);
		acceptButton.addActionListener(this);

		// Button to cancel
		cancelButton =  GenericFunctions.createButton("Cancel", 200, 200, 100, 40);
		panelConsultBalanceWindow.add(cancelButton);
		cancelButton.addActionListener(this);

		// Errors
		errorFieldConsultBalanceWindow = GenericFunctions.errorField(50, 250, 300, 40);
		add(errorFieldConsultBalanceWindow);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
      String errors="";
		if (e.getSource() == acceptButton) {
			 try {
				String pin1String = String.valueOf(pinInputFieldConsultBalance.getPassword());

				DataExchange data = new DataExchange(cardNumberInputField.getText(), pin1String, true, this);
				PrepaidSystem.consultBalance(data);
			} catch (CreateSystemException | NoSuchAlgorithmException | UnsupportedEncodingException e1) {
				errors=e1.getMessage();
			}
			
			errorFieldConsultBalanceWindow.setText(errors);
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
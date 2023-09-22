package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Combinada;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class anadirTarjeta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
//	private JTextField password;
	private static User usuario;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					anadirTarjeta frame = new anadirTarjeta(usuario);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public anadirTarjeta(User usuario) {
		this.usuario=usuario;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introducir numero tarjeta :");
		lblNewLabel.setBounds(30, 46, 131, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(202, 43, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Introducir contrase\u00F1a :");
		lblNewLabel_1.setBounds(30, 128, 131, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		JButton btnNewButton = new JButton("Guardar tarjeta");
		btnNewButton.setBounds(157, 200, 131, 23);
		contentPane.add(btnNewButton);
		
		password = new JPasswordField();
		password.setBounds(202, 125, 160, 20);
		contentPane.add(password);
		
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade facade = Inicio.getBusinessLogic();
				char pass[]= password.getPassword();
				String C= new String(pass);
				facade.anadirTarjeta(usuario, Integer.parseInt(textField.getText()), C);
				

			}
		});
	}
	
	
}

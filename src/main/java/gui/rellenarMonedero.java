package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Tarjeta;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class rellenarMonedero extends JFrame {

	private JPanel contentPane;
	private static User usuario;
	private JPasswordField password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rellenarMonedero frame = new rellenarMonedero(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public rellenarMonedero(User usuario) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		BLFacade facade = Inicio.getBusinessLogic();
		
		
		JLabel Monedero = new JLabel("Saldo: " + " "+ facade.sacarMonedero(usuario) );
		Monedero.setBounds(10, 125, 130, 32);
		contentPane.add(Monedero);
		
		
		JLabel lblNewLabel_1 = new JLabel("Numero de tarjeta");
		lblNewLabel_1.setBounds(234, 26, 120, 26);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(234, 230, 158, 22);
		contentPane.add(textArea_1);
		
		JTextArea numTarjeta = new JTextArea();
		numTarjeta.setBounds(234, 64, 158, 22);
		contentPane.add(numTarjeta);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setBounds(234, 110, 120, 26);
		contentPane.add(lblNewLabel_2);
		
//		JTextArea password = new JTextArea();
//		password.setBounds(234, 147, 158, 22);
//		contentPane.add(password);
//		
		JLabel lblNewLabel_3 = new JLabel("Dinero a introducir");
		lblNewLabel_3.setBounds(234, 194, 120, 26);
		contentPane.add(lblNewLabel_3);
		
		
		
		JButton introducir = new JButton("Intoducir");
		introducir.setBounds(60, 207, 103, 32);
		contentPane.add(introducir);
		
		password = new JPasswordField();
		password.setBounds(234, 147, 158, 20);
		contentPane.add(password);
		introducir.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade facade = Inicio.getBusinessLogic();
				char pass[]= password.getPassword();
				String C= new String(pass);
				Boolean encontrada= false;
				Vector<Tarjeta> tarjetas = facade.getAllTarjetas(usuario);
				for(Tarjeta T: tarjetas) {
					if (T.getId()== Integer.parseInt(numTarjeta.getText()) && T.getContrasena().equals(C)) {
						
						encontrada=true;
					
					}
					
				}
				if(encontrada==true) {
					facade.actualizarMonedero(usuario, Integer.parseInt(textArea_1.getText()),true);
				}else {
					introducir.setText("Error");
				}
				
				Monedero.setText("Saldo: " + " " +facade.sacarMonedero(usuario));
			}
		});
	}
}

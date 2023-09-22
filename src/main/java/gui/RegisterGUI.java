package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;


public class RegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextField user1;
	JRadioButton mayorDeEdad;
	JButton btnRegistrarse;
	static RegisterGUI frame;

	private boolean registrado=false;
	private JPasswordField password;
	private JPasswordField password3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterGUI();
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
	public RegisterGUI() {
		
		
		
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel crear_cuenta_label = new JLabel("CREAR CUENTA");
		crear_cuenta_label.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		crear_cuenta_label.setBounds(99, 34, 147, 14);
		contentPane.add(crear_cuenta_label);
		
		JTextField user1 = new JTextField();
		user1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 11));
		user1.setBounds(160, 87, 123, 20);
		contentPane.add(user1);
		user1.setColumns(10);
		
		JLabel user = new JLabel("Usuario");
		user.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		user.setBounds(36, 90, 60, 14);
		contentPane.add(user);
		
		JLabel contrasena1_label = new JLabel("Contrase\u00F1a :");
		contrasena1_label.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		contrasena1_label.setBounds(36, 136, 78, 14);
		contentPane.add(contrasena1_label);
		
		JLabel contrasena2_label = new JLabel("Repetir contrase\u00F1a :");
		contrasena2_label.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		contrasena2_label.setBounds(36, 181, 112, 14);
		contentPane.add(contrasena2_label);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnRegistrarse.setBounds(47, 294, 210, 38);
		contentPane.add(btnRegistrarse);
		
		mayorDeEdad = new JRadioButton("Soy mayor de edad");
		mayorDeEdad.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		mayorDeEdad.setBounds(87, 243, 147, 23);
		contentPane.add(mayorDeEdad);
		
		
	
		
		password = new JPasswordField();
		password.setBounds(160, 134, 123, 20);
		contentPane.add(password);
		
		password3 = new JPasswordField();
		password3.setBounds(160, 179, 122, 20);
		contentPane.add(password3);
		
		
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char pass[]= password.getPassword();
				String C= new String(pass);
				
				char pass2[]= password3.getPassword();
				String C2= new String(pass2);
				if(C!="" &&C.equals(C2)&& mayorDeEdad.isSelected()&& user.getText()!="" ) {
					
					BLFacade facade = Inicio.getBusinessLogic();
					 String usuario = user1.getText();
					
					
					
					 if(!facade.initUsuario(usuario)) {
						 User n =facade.createUser(usuario,C);
						 registrado=true;
						 JFrame a=new Inicio(registrado);
							a.setVisible(true);
					 }else {
						 btnRegistrarse.setText(" Usuario Existente");
					 }
					
//					frame.setVisible(false);
				}else {

					btnRegistrarse.setText(" Datos incorrectos");
				}
					
					
			
			}
		
					

				
				
			
			
		});
	}
}
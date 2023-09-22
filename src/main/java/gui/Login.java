package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import javax.swing.JButton;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JLabel usuario;
	private JLabel nombre;
	private JButton botonLogin;
//	private JTextField password;
	static Login frame;
	private JPasswordField password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setBounds(105, 72, 221, 39);
		contentPane.add(user);
		user.setColumns(10);
		
		usuario = new JLabel("Usuario");
		usuario.setFont(new Font("Serif", Font.BOLD, 16));
		usuario.setBounds(10, 84, 89, 23);
		contentPane.add(usuario);
		
		JLabel contrasena2 = new JLabel("Contrase\u00F1a");
		contrasena2.setFont(new Font("Serif", Font.BOLD, 16));
		contrasena2.setBounds(10, 152, 85, 27);
		contentPane.add(contrasena2);
		
		nombre = new JLabel("Login");
		nombre.setFont(new Font("Serif", Font.BOLD, 24));
		nombre.setBounds(184, 11, 170, 50);
		contentPane.add(nombre);
		
		botonLogin = new JButton("Enter");
		botonLogin.setFont(new Font("Serif", Font.BOLD, 16));
		botonLogin.setBounds(131, 205, 163, 47);
		contentPane.add(botonLogin);
		
		
		
		password = new JPasswordField();
		password.setBounds(105, 148, 221, 39);
		contentPane.add(password);
		
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = Inicio.getBusinessLogic();
				String use = user.getText();
				char pass[]= password.getPassword();
				String C= new String(pass);
				
				if(facade.init(use, C)==true) {
					
				User n=facade.getUser(use, C);
				if (n.getAdmin()) {
					JFrame a=new MainGui2(n);
					a.setVisible(true);
//					frame.setVisible(false);
				}else {
					JFrame a=new MenuUsu(n);
					a.setVisible(true);
//					frame.setVisible(false);
				}
				}else {

					botonLogin.setText(" Datos incorrectos");
				}
					
					
			
			}
		
					

				
				
			
			
		});
		
	}
	
}

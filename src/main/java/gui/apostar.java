package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apuestas;
import domain.Event;
import domain.Question;
import domain.User;
import domain.pronostico2;
import javax.swing.JComboBox;

public class apostar extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dinero;
	private static User usuario;
	private static Question Q;
	private static Event evento;
	private static pronostico2 p;
	
	/**
	 * Launch the application.
//	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					apostar frame = new apostar(usuario,evento,Q);
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
	public apostar(User usuario, Event evento, Question q) {
		super();
		this.usuario=usuario;
		this.evento=evento;
		this.Q=q;
	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("apostar");
		Title.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		Title.setEnabled(false);
		Title.setBounds(160, 24, 218, 44);
		contentPane.add(Title);
		
		JLabel Dinero = new JLabel("Introducir dinero");
		Dinero.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		Dinero.setEnabled(false);
		Dinero.setBounds(35, 123, 108, 27);
		contentPane.add(Dinero);
		
		dinero = new JTextField();
		dinero.setBounds(204, 125, 116, 24);
		contentPane.add(dinero);
		dinero.setColumns(10);
		
		JButton apostar = new JButton("apostar");
		apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			}
		});
		apostar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		apostar.setBounds(148, 225, 129, 27);
		contentPane.add(apostar);
		
		JComboBox comboBoxP = new JComboBox <pronostico2>();
		comboBoxP.setBounds(125, 79, 301, 33);
		contentPane.add(comboBoxP);
		
		
		for (pronostico2 p : Q.getPronosticos()) {
			comboBoxP.addItem(p.getTexto());
				}	
		
		JLabel lblNewLabel = new JLabel("Pronosticos");
		lblNewLabel.setBounds(10, 83, 82, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnRellenar = new JButton("Rellenar monedero");
		btnRellenar.setBounds(136, 175, 141, 23);
		contentPane.add(btnRellenar);
		btnRellenar.setVisible(false);
		
		apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = Inicio.getBusinessLogic();
				if(Integer.parseInt(dinero.getText())<=facade.sacarMonedero(usuario) ) {
					float money = Float.parseFloat(dinero.getText());
					p=facade.getPro((String) comboBoxP.getSelectedItem());
					float ganancias= (float) (money*Float.parseFloat(p.getCuota()));
					
					
					if(dinero.getText()!="" ) {
						Apuestas apu= new Apuestas(p,money,ganancias,evento,Q);
						facade.anadirApuesta(apu, usuario);
					
					}else {

						apostar.setText(" Datos incorrectos");
					}
					
				}else if(Integer.parseInt(dinero.getText())>facade.sacarMonedero(usuario)) {
					apostar.setText("Saldo insuficiente");
					
					btnRellenar.setVisible(true);
					

					btnRellenar.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JFrame a = new rellenarMonedero(usuario);
							a.setVisible(true);
						}
					});
					
				}else {
					apostar.setText("Datos incorrectos");
				}
			
					
					
			
			}
		
					

				
				
			
			
		});
		
		
	}
}
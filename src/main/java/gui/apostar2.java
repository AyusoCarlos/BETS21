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
import domain.Combinada;
import domain.Event;
import domain.Question;
import domain.User;
import domain.pronostico2;
import javax.swing.JComboBox;

public class apostar2 extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static User usuario;
	private static Question Q;
	private static Event evento;
	private static pronostico2 p;
	private JFrame este;
	private static Combinada combi;
	
	/**
	 * Launch the application.
//	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					apostar2 frame = new apostar2(usuario,evento,Q,combi);
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
	public apostar2(User usuario, Event evento, Question q,Combinada combi) {
		super();
		this.usuario=usuario;
		this.evento=evento;
		this.Q=q;
		this.combi=combi;
		este=this;
	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Apostar");
		Title.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		Title.setEnabled(false);
		Title.setBounds(160, 24, 218, 44);
		contentPane.add(Title);
		
		JButton apostar2 = new JButton("Añadir apuesta");
		apostar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
		apostar2.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		apostar2.setBounds(148, 225, 145, 27);
		contentPane.add(apostar2);
		
		JComboBox comboBoxP = new JComboBox <pronostico2>();
		comboBoxP.setBounds(125, 79, 301, 33);
		contentPane.add(comboBoxP);
		
		
		for (pronostico2 p : Q.getPronosticos()) {
			comboBoxP.addItem(p.getTexto());
				}	
		
		JLabel lblNewLabel = new JLabel("Pronosticos");
		lblNewLabel.setBounds(10, 83, 82, 24);
		contentPane.add(lblNewLabel);
		
		apostar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = Inicio.getBusinessLogic();
				float money = combi.getInversion();
				p=facade.getPro((String) comboBoxP.getSelectedItem());
				float ganancias= (float) (money*Float.parseFloat(p.getCuota()));
				
				facade.actualizarMonedero(usuario, (int)money, false);
				
					Apuestas apu= new Apuestas(p,money,ganancias,evento,Q);
					facade.actualizarCombi(combi, usuario, apu,ganancias);
	
			
			
					


//					facade.añadirApuesta(apu, usuario);
				
				}
					
					
			
			
		
					

				
				
			
			
		});
		
		
	}
}
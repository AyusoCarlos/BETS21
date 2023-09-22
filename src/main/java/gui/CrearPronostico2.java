package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.User;
import domain.pronostico2;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearPronostico2 extends JFrame {

	private JPanel contentPane;
	private static Event evento;
	private static User usuario;
	private static Question q;
	private JTextField textopronostico;
	private JTextField textocuota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPronostico2 frame = new CrearPronostico2(usuario,evento,q);
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
	public CrearPronostico2(User usuario, Event evento,Question q) {
		this.usuario=usuario;
		this.evento=evento;
		this.q=q;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titulo = new JLabel("Crear pronostico");
		Titulo.setFont(new Font("Serif", Font.BOLD, 22));
		Titulo.setBounds(134, 28, 165, 22);
		contentPane.add(Titulo);
		
		JLabel Etiqueta1 = new JLabel("Pronostico");
		Etiqueta1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		Etiqueta1.setBounds(78, 82, 65, 14);
		contentPane.add(Etiqueta1);
		
		JLabel Etiqueta2 = new JLabel("Cuota");
		Etiqueta2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		Etiqueta2.setBounds(78, 149, 46, 14);
		contentPane.add(Etiqueta2);
		
		textopronostico = new JTextField();
		textopronostico.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		textopronostico.setBounds(198, 80, 125, 20);
		contentPane.add(textopronostico);
		textopronostico.setColumns(10);
		
		textocuota = new JTextField();
		textocuota.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		textocuota.setBounds(198, 147, 125, 20);
		contentPane.add(textocuota);
		textocuota.setColumns(10);
		
		JButton Botonpronosticos = new JButton("Crear");
		Botonpronosticos.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		Botonpronosticos.setBounds(144, 217, 155, 33);
		contentPane.add(Botonpronosticos);
		
		
		Botonpronosticos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BLFacade facade = Inicio.getBusinessLogic();
				pronostico2 pronostico= new pronostico2(textopronostico.getText(),textocuota.getText());
				facade.anadirPronostico2(evento, q, pronostico);
			}
		});
	}
	
}

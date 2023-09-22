package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;
import domain.Question;
import domain.pronostico2;

import javax.swing.JLabel;
import java.awt.Font;

public class ProAnonimo extends JFrame {

	private JPanel contentPane;
	private static Event ev;
	private static Question q;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProAnonimo frame = new ProAnonimo(ev,q);
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
	public ProAnonimo(Event ev, Question q) {
		this.ev=ev;
		this.q=q;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pronosticos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(142, 36, 222, 50);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBoxP = new JComboBox <pronostico2>();
		comboBoxP.setBounds(53, 79, 301, 33);
		contentPane.add(comboBoxP);
		
		
		
		for (pronostico2 p : q.getPronosticos()) {
			String nuevo= p.getTexto() +" "+"Cuota:"+" "+ p.getCuota();
			comboBoxP.addItem(nuevo);
	}
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class historial extends JFrame {

	private JPanel contentPane;
	private static User usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historial frame = new historial(usuario);
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
	public historial(User usuario) {
		this.usuario=usuario;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Historial");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(161, 11, 84, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dinero Ganado:"+  "       " + usuario.getHistorial().getDineroGanado());
		lblNewLabel_1.setBounds(27, 50, 282, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Numero de apuestas terminadas:"+  "       " + usuario.getHistorial().getNumeroApuestas());
		lblNewLabel_2.setBounds(27, 87, 282, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apuestas Ganadas:"+  "       " + usuario.getHistorial().getApuestasGanadas());
		lblNewLabel_3.setBounds(27, 122, 282, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apuestas Perdidas:" +  "       " + usuario.getHistorial().getApuestasPerdidas());
		lblNewLabel_4.setBounds(27, 161, 282, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Combinadas Ganadas:" +  "       "  + usuario.getHistorial().getCombinadaGanadas());
		lblNewLabel_5.setBounds(27, 197, 218, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Combinadas Perdidas:" +  "       "  + usuario.getHistorial().getCombinadasPerdidas());
		lblNewLabel_6.setBounds(27, 238, 218, 14);
		contentPane.add(lblNewLabel_6);
		
		
	}
	
	
}

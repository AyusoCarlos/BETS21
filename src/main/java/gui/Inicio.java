package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

public class Inicio extends JFrame {
	private JPanel contentPane;
	JButton registerbtn;
	JButton iniciobtn;
	static Inicio frame;

private static  BLFacade appFacadeInterface;
private JButton VerApu;
	

	public static  BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new Inicio();
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
	public Inicio() {
		BLFacade facade = Inicio.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 registerbtn = new JButton("REGISTRO");
		registerbtn.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		registerbtn.setBounds(10, 11, 416, 65);
		contentPane.add(registerbtn);
		
		iniciobtn = new JButton("INICIAR SESI\u00D3N");
		iniciobtn.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		iniciobtn.setBounds(10, 97, 416, 65);
		contentPane.add(iniciobtn);
		
		VerApu = new JButton("Ver Apuestas");
		VerApu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new BuscarPreguntaAnonimo();
				a.setVisible(true);
			}
		});
		VerApu.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		VerApu.setBounds(10, 187, 416, 65);
		contentPane.add(VerApu);
		
		
	
	registerbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame a=new RegisterGUI();
			a.setVisible(true);
//			frame.setVisible(false);
		
		}
	});
	
	iniciobtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		
			JFrame a=new Login();
			a.setVisible(true);
//			frame.setVisible(false);
		
		}
	});
}
	public Inicio(boolean registrado) {
		BLFacade facade = Inicio.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		if(!registrado) {
			 registerbtn = new JButton("REGISTRO");
				registerbtn.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
				registerbtn.setBounds(10, 11, 416, 125);
				contentPane.add(registerbtn);
				
				registerbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame a=new RegisterGUI();
						a.setVisible(true);
//						frame.setVisible(false);
					
					}
				});
		}
		
		iniciobtn = new JButton("INICIAR SESI\u00D3N");
		iniciobtn.setFont(new Font("Segoe UI Symbol", Font.BOLD, 24));
		iniciobtn.setBounds(10, 136, 416, 127);
		contentPane.add(iniciobtn);
		
		
		
		
		
	

	
	iniciobtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		
			JFrame a=new Login();
			a.setVisible(true);
//			frame.setVisible(false);
		
		}
	});
}
	
}

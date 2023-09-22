package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Combinada;
import domain.User;

public class MenuUsu extends JFrame {

	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    
	protected JLabel jLabelSelectOption;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static User usuario;
	private boolean registrado;
	private JFrame este;
	private JButton btnNewButtonMonedero;
	private JButton btnNewButtonTarjeta;
	
	/**
	 * This is the default constructor
	 */
	public MenuUsu( User usuario) {
		super();
		this.usuario=usuario;
		este=this;
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		
		
	initialize();
}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			
			JButton VerHistorial = new JButton("Ver Historial");
			VerHistorial.setBounds(0, 130, 481, 17);
			jContentPane.add(VerHistorial);
			VerHistorial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new historial(usuario);
					a.setVisible(true);
					
					
				}
			});
			
			JButton Logout = new JButton("Cerrar Sesi\u00F3n");
			Logout.setBounds(0, 214, 481, 17);
			jContentPane.add(Logout);
			
			JButton btnCombi = new JButton("Apuesta Combinada");
			btnCombi.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e ) {
					BLFacade facade = Inicio.getBusinessLogic();
					Combinada combi= new Combinada(facade.getSizeCombi()+1);
					facade.anadirCombinada(combi, usuario);
					
					JFrame a = new buscarPreguntaCombinada(usuario, combi);
					a.setVisible(true);
				}
			});
			btnCombi.setBounds(0, 102, 479, 17);
			jContentPane.add(btnCombi);
			
			JButton btnNewButtonCate = new JButton("Buscar apuesta por categoria");
			btnNewButtonCate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnNewButtonCate.setBounds(0, 47, 479, 17);
			jContentPane.add(btnNewButtonCate);
			jContentPane.add(getBtnNewButtonMonedero());
			jContentPane.add(getBtnNewButtonTarjeta());
			btnNewButtonCate.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new buscarPreguntaPorCategoria(usuario);
					a.setVisible(true);
				}
			});
			Logout.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					este.setVisible(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JFrame a = new Inicio();
					

					a.setVisible(true);
				}
			});
		
			btnNewButtonMonedero.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					JFrame a = new rellenarMonedero(usuario);
					

					a.setVisible(true);
				}
			});
			
			btnNewButtonTarjeta.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					JFrame a = new anadirTarjeta( usuario);
					

					a.setVisible(true);
				}
			});
			
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 74, 481, 17);
			jButtonQueryQueries.setText("Apuesta \u00DAnica");
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new buscarPregunta(usuario);

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel("Select Option");
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnNewButtonMonedero() {
		if (btnNewButtonMonedero == null) {
			btnNewButtonMonedero = new JButton("Monedero");
			btnNewButtonMonedero.setBounds(2, 158, 479, 17);
		}
		return btnNewButtonMonedero;
	}
	
	
	
	private JButton getBtnNewButtonTarjeta() {
		if (btnNewButtonTarjeta == null) {
			btnNewButtonTarjeta = new JButton("Introducir nueva tarjeta");
			btnNewButtonTarjeta.setBounds(0, 186, 481, 17);
		}
		return btnNewButtonTarjeta;
	}
}
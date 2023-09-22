package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Event;
import domain.User;

public class MainGui2 extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;

    
	protected JLabel jLabelSelectOption;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private User usuario;
	private JButton CerrarApuesta;
	private JFrame este;
	/**
	 * This is the default constructor
	 */
	public MainGui2(User usuario) {
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
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton Logout = new JButton("Cerrar Sesi\u00F3n");
		Logout.setBounds(0, 207, 481, 23);
		jContentPane.add(Logout);
		Logout.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				este.setVisible(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JFrame a = new Inicio();
				

				a.setVisible(true);
			}
		});
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
			jContentPane.add(getBoton2());
			
			JButton CreateEvent = new JButton("Create Event");
			CreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame n = new CreateEvent();
					n.setVisible(true);
				}
			});
			CreateEvent.setBounds(0, 72, 481, 23);
			jContentPane.add(CreateEvent);
			
			JButton CrearPronostico = new JButton("Create Pronostico");
			CrearPronostico.setBounds(0, 140, 479, 23);
			jContentPane.add(CrearPronostico);
			jContentPane.add(getCerrarApuesta());
			CrearPronostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame n = new CrearPronostico(usuario);
					n.setVisible(true);
				}
			});
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(0, 106, 481, 23);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CrearPregunta(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 38, 481, 23);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
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
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, -15, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	
	

	
}
	private JButton getCerrarApuesta() {
		if (CerrarApuesta == null) {
			CerrarApuesta = new JButton("Cerrar Evento");
			CerrarApuesta.setBounds(0, 173, 481, 23);
			CerrarApuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CerrarApuesta(usuario);

					a.setVisible(true);
				}
			});
			
			
			
			
		}
		return CerrarApuesta;
		
	
	}

}
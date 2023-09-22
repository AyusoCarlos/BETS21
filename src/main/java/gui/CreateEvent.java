package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Question;
import domain.User;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.Categoria;
import domain.Event;
import javax.swing.JComboBox;

public class CreateEvent extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private    User usuario;
	public static buscarPregunta frame;

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private DefaultTableModel tableModelEvents;
	
	

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton crearEvento = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BuscarPregunta.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$


	private JPanel contentPane;
	private JTextField Evento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEvent frame = new CreateEvent();
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
	public CreateEvent() {
	
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
			this.usuario=usuario;
			try
			{
				jbInit();
			
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		
		private void jbInit() throws Exception
		{

			this.getContentPane().setLayout(null);
			this.setSize(new Dimension(700, 500));
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

			jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
			contentPane.setLayout(null);

			this.getContentPane().add(jLabelEventDate);


			jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

			BLFacade facade = Inicio.getBusinessLogic();
			datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
			CrearPregunta.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

			// Code for JCalendar
			this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent propertychangeevent)
				{

					if (propertychangeevent.getPropertyName().equals("locale"))
					{
						jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					}
					else if (propertychangeevent.getPropertyName().equals("calendar"))
					{
						calendarAnt = (Calendar) propertychangeevent.getOldValue();
						calendarAct = (Calendar) propertychangeevent.getNewValue();
						DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//						jCalendar1.setCalendar(calendarAct);
						Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

						 
						
						int monthAnt = calendarAnt.get(Calendar.MONTH);
						int monthAct = calendarAct.get(Calendar.MONTH);
						
						if (monthAct!=monthAnt) {
							if (monthAct==monthAnt+2) {
								// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
								// Con este código se dejará como 1 de febrero en el JCalendar
								calendarAct.set(Calendar.MONTH, monthAnt+1);
								calendarAct.set(Calendar.DAY_OF_MONTH, 1);
							}						
							
							jCalendar1.setCalendar(calendarAct);

							BLFacade facade = Inicio.getBusinessLogic();

							datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
						}



						CrearPregunta.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
														
						

						try {
							tableModelEvents.setDataVector(null, columnNamesEvents);
							tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

							BLFacade facade=Inicio.getBusinessLogic();

							Vector<domain.Event> events=facade.getEvents(firstDay);

							
							for (domain.Event ev:events){
								if(ev.isAcabado()== false) {
									Vector<Object> row = new Vector<Object>();

									System.out.println("Events "+ev);

									row.add(ev.getEventNumber());
									row.add(ev.getDescription());
									row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
									tableModelEvents.addRow(row);
								}		
							}
							// not shown in JTable
						} catch (Exception e1) {

							//jLabelQueries.setText(e1.getMessage());
						}

					}
				} 
			});

			this.getContentPane().add(jCalendar1);
			tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
			
			crearEvento.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
			crearEvento.setBounds(189, 315, 294, 59);
			
			getContentPane().add(crearEvento);
			
			Evento = new JTextField();
			Evento.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
			Evento.setBounds(387, 106, 266, 59);
			contentPane.add(Evento);
			Evento.setColumns(10);
			
			JLabel Event = new JLabel("Crear evento");
			Event.setEnabled(false);
			Event.setBounds(391, 70, 92, 25);
			contentPane.add(Event);
			
			JLabel lblNewLabel = new JLabel("Elegir categoria:");
			lblNewLabel.setBounds(327, 200, 84, 14);
			contentPane.add(lblNewLabel);
			
			JComboBox comboBoxCa = new JComboBox();
			comboBoxCa.setBounds(466, 196, 140, 22);
			contentPane.add(comboBoxCa);
			Categoria categorias= new Categoria();
			for(String q:categorias.getCategorias()) {
				String Nuevo= q;
				comboBoxCa.addItem(Nuevo);
			}
			
			
			
			
		
					crearEvento.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							BLFacade facade = Inicio.getBusinessLogic();
							Categoria n=facade.getCat((String) comboBoxCa.getSelectedItem());

								Event x = new Event(facade.getAllEventos()+1,Evento.getText(),jCalendar1.getDate(),n);
								facade.createEvent(x);
							}
							
							
							
							
							

							
						
						
						
					});

		}
	}
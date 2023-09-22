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
import domain.Apuestas;
import domain.Combinada;
import domain.Question;
import domain.User;
import domain.pronostico2;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CerrarApuesta extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private    User usuario;
	public static CerrarApuesta frame;

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private int cont;
	private int cont2;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnCerrarEvento = new JButton("Cerrar Evento");
	private final JLabel Resu = new JLabel("Resultado:");
	private final JButton btnCerrarPregunta = new JButton("Cerrar pregunta");
	private JComboBox comboBox = new JComboBox<pronostico2>();

	public CerrarApuesta(User usuario)
	{
		
		this.usuario=usuario;
		try
		{
			jbInit();
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
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
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);


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
//					jCalendar1.setCalendar(calendarAct);
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

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
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
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

//						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(136, 211, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

//				if (queries.isEmpty())
//					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
//				else 
//					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
					
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});
		
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				int h= tableQueries.getSelectedRow();
				String q=(String)tableModelQueries.getValueAt(h,1);
				BLFacade facade = Inicio.getBusinessLogic();
				 Question que=facade.getQuestion(q,ev);
				
				Vector<pronostico2> pronosticos=que.getPronosticos();
				for(pronostico2 p: pronosticos) {
					comboBox.addItem(p.getTexto());;
					
				}
				
			
					
						}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		btnCerrarEvento.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		btnCerrarEvento.setBounds(329, 426, 143, 24);
		
		getContentPane().add(btnCerrarEvento);
		Resu.setBounds(153, 355, 67, 14);
		
		getContentPane().add(Resu);
		btnCerrarPregunta.setBounds(176, 426, 143, 24);
		
		getContentPane().add(btnCerrarPregunta);
		comboBox.setBounds(264, 351, 208, 22);
		
		getContentPane().add(comboBox);

		
		
		
	
				btnCerrarEvento.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							int i=tableEvents.getSelectedRow();
							domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2);
						
							
							
							
							
							
							
							
							if(ev.getCont()==0) {
							
							BLFacade facade = Inicio.getBusinessLogic();
							facade.cerrarEvento(ev);
							
						}
							
						
						}
						
					});
				btnCerrarPregunta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BLFacade facade = Inicio.getBusinessLogic();
						int i=tableEvents.getSelectedRow();
						domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2);
						Question q=ev.getQuestions().get(tableQueries.getSelectedRow());
						facade.cerrarPreguntas(q,(String) comboBox.getSelectedItem());
						ev.setCont(ev.getCont()-1);
						
						
						for(User u:facade.getAllUsers()) {
							for(Apuestas x:u.getApuesta()) {
								if(x.getQ().equals(q.getQuestion()) && x.getPro().getTexto().equals((String) comboBox.getSelectedItem())) {
									facade.ActualizarHistorial(x, u, true);
								}else {
									facade.ActualizarHistorial(x, u, false);
								}
							}
							for(Combinada x:u.getCombi()) {
							for(Apuestas n:x.getApuestas()) {
								if(n.getQ().equals(q.getQuestion()) && n.getPro().getTexto().equals((String) comboBox.getSelectedItem())) {
								n.setGanada(true);
								n.setAcabada(true);
								//facade.actualizarCombi(x, u, n);
							}else if(n.getQ().equals(q.getQuestion())) {
								n.setAcabada(true);
							}
							}
						}
							int h=0;
							
								for(Combinada x:u.getCombi()) {
								h++;
								int g=0;
								int resu=0;
								for(Apuestas n:x.getApuestas()) {
									g++;
									resu=(int) (resu+n.getGanancia());
									if(n.isGanada()==true) {
									facade.setPregunta(x, true, h-1);
								}else if(n.isGanada()==false && n.isAcabada()==true) {
									facade.setPregunta(x, false, h-1);
								}
									System.out.println(x.getApuestas().get(g-1).isGanada());
								}
								System.out.println(x.getApuestas().size());
								System.out.println(x.getAcabadas());
								System.out.println(x.getGanadas());
								
							if(x.getGanadas()+1==x.getApuestas().size()) {
								
									facade.ActualizarHistorial2(x, u, true);
									facade.actualizarMonedero(u, resu, true);
									
								}else if(x.getAcabadas()+x.getGanadas()+1 == x.getApuestas().size()) {
									facade.ActualizarHistorial2(x, u, false);
								
								
							}
					}
							
						}
							
							
							
							
							
							
							
//							for(Combinada x:u.getCombi()) {
//								for(Apuestas n:x.getApuestas()) {
//									if(n.getQ().equals(q.getQuestion()) && n.getPro().getTexto().equals(comboBox.getText())) {
//									n.setGanada(true);
//									n.setAcabada(true);
//									//facade.actualizarCombi(x, u, n);
//								}else {
//									n.setAcabada(true);
//								}
//								}
//							}
//							for(Combinada x:u.getCombi()) {
//								
//								for(Apuestas n:x.getApuestas()) {
//									if(n.isGanada()==true) {
//									x.setGanadas(x.getGanadas()+1);
//								}else if(n.isGanada()==false && n.isAcabada()==true) {
//									x.setAcabadas(x.getAcabadas()+1);
//								}
//								}
//								System.out.println(x.getApuestas().size());
//								System.out.println(x.getAcabadas());
//								System.out.println(x.getGanadas());
//							if(x.getGanadas()==x.getApuestas().size()) {
//								
//									facade.ActualizarHistorial2(x, u, true);
//								}else if(x.getAcabadas()+x.getGanadas() == x.getApuestas().size()) {
//									facade.ActualizarHistorial2(x, u, false);
//								
//								
//							}
//					}
						
					}
				});

	}
}
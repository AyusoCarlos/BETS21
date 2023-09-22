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
import javax.swing.JComboBox;
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
import domain.Categoria;
import domain.Event;
import domain.Question;
import domain.User;

public class buscarPreguntaPorCategoria extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private    User usuario;
	public static buscarPreguntaPorCategoria frame;

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
	

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton apostar = new JButton("Apostar");
	private JComboBox comboBox;
	private JButton buscarEventos;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			
//			public void run() {
//				try {
//					frame = new buscarPreguntaPorCategoria(usuario);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	//}
	public  buscarPreguntaPorCategoria(User usuario){
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

	
	private void jbInit() throws Exception{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(10, 256, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
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

						comboBox.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tableModelEvents.setNumRows(0);
							}
							});
						
						buscarEventos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								;
							
								Vector<domain.Event> events=facade.getEvents(firstDay);
									
						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (Event ev:events){
							if(ev.isAcabado()== false) {
								Vector<Object> row = new Vector<Object>();
									if(comboBox.getSelectedItem().equals(ev.getCategoria().getName())) {
									
									System.out.println("Events "+ev);

									row.add(ev.getEventNumber());
									row.add(ev.getDescription());
									row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
									tableModelEvents.addRow(row);
									
									 
									}
										
									}
								}
								}
						});
						
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); 
							
						
						
					
						} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});
	
		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(10, 281, 382, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

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
		apostar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		apostar.setBounds(267, 423, 124, 29);
		getContentPane().add(apostar);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(458, 284, 129, 29);
		getContentPane().add(comboBox);
		 buscarEventos = new JButton("Buscar Eventos");
		buscarEventos.setBounds(498, 423, 124, 29);
		getContentPane().add(buscarEventos);
		
		Categoria categorias= new Categoria();
		for(String q:categorias.getCategorias()) {
			String Nuevo= q;
			comboBox.addItem(Nuevo);
		}
	

		
		
	apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						BLFacade facade = Inicio.getBusinessLogic();
				
				
						int i=tableEvents.getSelectedRow();
						domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2);
						Question q=ev.getQuestions().get(tableQueries.getSelectedRow());
							System.out.println(q.getQuestion() + ev.getDescription());	
						
							
							JFrame n = new apostar(usuario,ev,q);
								n.setVisible(true);		

						
					}	
					
					
				});
				

	}
}
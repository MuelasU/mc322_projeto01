package src.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import src.controller.Controller;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Moderador;
import src.model.agenda.Agenda;
import src.model.agenda.Evento;

public class Principal extends JFrame {
	
	private static final long serialVersionUID = 3;
	private JPanel contentPane;
	private boolean ehModerador;
	private boolean ehInstrutor;
	private boolean ehEstudante;
	private DefaultListModel<Evento> model;
	private DefaultListModel<Evento> model_1;
	JList<Evento> list;
	JList<Evento> list_1;

	public boolean ehModerador() {
		return ehModerador;
	}

	public boolean ehInstrutor() {
		return ehInstrutor;
	}

	public boolean ehEstudante() {
		return ehEstudante;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal(null);
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
	public Principal(Login login) {
		ehModerador = Controller.getUserSession() instanceof Moderador;
		ehInstrutor = Controller.getUserSession() instanceof Instrutor;
		ehEstudante = Controller.getUserSession() instanceof Estudante;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//#region Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuario");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Info do Usuario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InfoUsuario newInfo = new InfoUsuario(Principal.this);
				newInfo.setVisible(true);
				Principal.this.setVisible(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Sair");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.setVisible(true);
				if (Controller.logout()) Principal.this.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_1 = new JMenu("Eventos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Solicitar monitoria");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SolicitarMonitoria solicitacaoMonitoria = new SolicitarMonitoria(Principal.this);
				solicitacaoMonitoria.setVisible(true);
				Principal.this.setVisible(false);
			}
		});
		mntmNewMenuItem_1.setEnabled(ehEstudante);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Criar evento");
		mntmNewMenuItem_4.setEnabled(ehInstrutor || ehModerador);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CriarEvento criarEvento = new CriarEvento(Principal.this);
				criarEvento.setVisible(true);
				Principal.this.setVisible(false);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		//#endregion

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{203, 203, 0};
		gbl_contentPane.rowHeights = new int[]{17, 194, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Seus eventos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Todos os eventos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		model = new DefaultListModel<Evento>();
		model.addAll(Controller.getUserSession().getAgenda().getEventos());		
		list = new JList<Evento>(model);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList<Evento> list = (JList<Evento>) e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            InfoEvento evento = new InfoEvento(Principal.this, list.getModel().getElementAt(index));
		            evento.setVisible(true);
		            Principal.this.setVisible(false);
		        }
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.setCellRenderer(new EventRenderer());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		model_1 = new DefaultListModel<Evento>();
		model_1.addAll(Agenda.getTodosEventos());
		list_1 = new JList<Evento>(model_1);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList<Evento> list = (JList<Evento>)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            InfoEvento evento = new InfoEvento(Principal.this, list_1.getModel().getElementAt(index));
		            evento.setVisible(true);
		            Principal.this.setVisible(false);
		        }
			}
		});
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(list_1);
		list_1.setCellRenderer(new EventRenderer());
	}

	protected void atualizaListas() {
		model.clear();
		model_1.clear();
		model.addAll(Controller.getUserSession().getAgenda().getEventos());
		model_1.addAll(Agenda.getTodosEventos());
		list.setModel(model);
		list_1.setModel(model_1);
	}

	private static class EventRenderer extends JLabel implements ListCellRenderer<Evento> {

		private static final long serialVersionUID = 7;
		public EventRenderer() {
			setOpaque(true);
		}
	
		@Override
		public Component getListCellRendererComponent(JList<? extends Evento> list, Evento evento, int index, boolean isSelected, boolean cellHasFocus) {
			setText(evento.getNome());
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			return this;
		}
	}
}

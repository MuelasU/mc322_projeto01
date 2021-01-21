package src.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import src.controller.Controller;
import src.model.Instrutor;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;

public class InfoEvento extends JDialog {

	private static final long serialVersionUID = 8;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField duracaoTextField;
	private JTextField capacidadeTextField;
	private JTextField salaTextField;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InfoEvento dialog = new InfoEvento(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InfoEvento(Principal principal, Evento evento) {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.3, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		contentPanel.add(verticalStrut, gbc_verticalStrut);

		JLabel lblNewLabel = new JLabel("Nome");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField(evento.getNome());
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPanel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descricao");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_1 = new JTextField(evento.getDescricao());
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		contentPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Data");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		textField_2 = new JTextField(fmt.format(evento.getData().getTime()));
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		contentPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Duracao (em horas)");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		Duration duracao = evento.getDuracao();
		if (duracao != null) {
			int horas = (int) duracao.toHours();
			duracaoTextField = new JTextField(horas + "");
		} else {
			duracaoTextField = new JTextField();
		}
		duracaoTextField.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		contentPanel.add(duracaoTextField, gbc_textField_3);
		duracaoTextField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Capacidade");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		capacidadeTextField = new JTextField(evento.getCapacidade() + "");
		capacidadeTextField.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 5;
		contentPanel.add(capacidadeTextField, gbc_textField_4);
		capacidadeTextField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Link da Sala");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		salaTextField = new JTextField(evento.getSalaDeVideo());
		salaTextField.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 6;
		contentPanel.add(salaTextField, gbc_textField_5);
		salaTextField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Disciplina");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 7;
		contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_6 = new JTextField(evento.getDisciplina().getNome());
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 7;
		contentPanel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Responsavel");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		contentPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		textField_7 = new JTextField(evento.getInstrutor() == null ? "" : evento.getInstrutor().getPerfil().getNome());
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 8;
		contentPanel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Confirmado?");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 9;
		contentPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);

		textField_8 = new JTextField(evento.isConfirmado() + "");
		textField_8.setEditable(false);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 9;
		contentPanel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		//#region Botoes de Operacoes
		if (principal.ehEstudante()) {
			boolean checagem = evento.getParticipantes().contains(Controller.getUserSession());
			JToggleButton tglbtnNewToggleButton = new JToggleButton(checagem ? "Desinscrever-se" : "Inscrever-se");
			tglbtnNewToggleButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean inscrito = evento.getParticipantes().contains(Controller.getUserSession());
					if (inscrito) {
						tglbtnNewToggleButton.setText(Controller.desinscreverEvento(evento) ? "Inscrever-se" : "Desinscrever-se");
					} else {
						tglbtnNewToggleButton.setText(Controller.inscreverEvento(evento) ? "Desinscrever-se" : "Inscrever-se");
					}
				}
			});
			GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
			gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 0);
			gbc_tglbtnNewToggleButton.gridx = 1;
			gbc_tglbtnNewToggleButton.gridy = 10;
			contentPanel.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		} else {
			if (Controller.getUserSession() instanceof Instrutor && evento instanceof Monitoria && Monitoria.getSolicitacoes().contains(evento)) {
				capacidadeTextField.setEditable(true);
				salaTextField.setEditable(true);
				duracaoTextField.setEditable(true);

				JButton aceitarButton = new JButton("Aceitar monitoria");
				aceitarButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean aceitou = Controller.aceitarMonitoria((Monitoria) evento, capacidadeTextField.getText(), salaTextField.getText(), duracaoTextField.getText());
						if (aceitou) {
							principal.atualizaListas();
							principal.setVisible(true);
							InfoEvento.this.dispose();
						}
					}
				});
				GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
				gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 0);
				gbc_tglbtnNewToggleButton.gridx = 1;
				gbc_tglbtnNewToggleButton.gridy = 10;
				contentPanel.add(aceitarButton, gbc_tglbtnNewToggleButton);
			}
			JToggleButton newTglButton = new JToggleButton(evento.isConfirmado() ? "Cancelar evento" : "Confirmar evento");
			newTglButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controller.mudaConfirmacaoEvento(evento);
					newTglButton.setText(evento.isConfirmado() ? "Cancelar evento" : "Confirmar evento");
					textField_8.setText(evento.isConfirmado() + "");
				}
			});
			GridBagConstraints gbc_tglbtnNewToggleButton_1 = new GridBagConstraints();
			gbc_tglbtnNewToggleButton_1.insets = new Insets(0, 0, 5, 0);
			gbc_tglbtnNewToggleButton_1.gridx = 1;
			gbc_tglbtnNewToggleButton_1.gridy = 11;
			contentPanel.add(newTglButton, gbc_tglbtnNewToggleButton_1);
		}
		//#endregion

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				principal.atualizaListas();
				principal.setVisible(true);
				InfoEvento.this.dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
	}
}

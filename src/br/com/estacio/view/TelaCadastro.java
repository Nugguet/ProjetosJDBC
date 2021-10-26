package br.com.estacio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.estacio.DAO.ConexaoMySQL;
import br.com.estacio.entidade.Imovel;

public class TelaCadastro {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtCPF;
	private JLabel lblCpfCnpjPassaporte;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JLabel lblNewLabel_3;
	private JLabel lblDdd;
	private JTextField txtDDD;
	private JLabel lblTelefone;
	private JTextField txtFone;
	private JLabel lblEndereo;
	private JTextField txtEndereco;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro window = new TelaCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtNome = new JTextField();
		txtNome.setBounds(29, 45, 188, 26);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome Completo:");
		lblNewLabel.setBounds(32, 29, 111, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(255, 69, 135, 16);
		frame.getContentPane().add(lblNewLabel_1);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(245, 45, 268, 26);
		frame.getContentPane().add(txtSobrenome);
		txtSobrenome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(39, 69, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);

		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(29, 126, 188, 26);
		frame.getContentPane().add(txtCPF);

		lblCpfCnpjPassaporte = new JLabel("CPF, CNPJ, Passaporte:");
		lblCpfCnpjPassaporte.setBounds(32, 110, 141, 16);
		frame.getContentPane().add(lblCpfCnpjPassaporte);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(32, 180, 336, 26);
		frame.getContentPane().add(txtEmail);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(35, 164, 111, 16);
		frame.getContentPane().add(lblEmail);

		lblNewLabel_3 = new JLabel("exemplo@gmail.com");
		lblNewLabel_3.setBounds(39, 204, 178, 16);
		frame.getContentPane().add(lblNewLabel_3);

		lblDdd = new JLabel("DDD:");
		lblDdd.setBounds(35, 244, 111, 16);
		frame.getContentPane().add(lblDdd);

		txtDDD = new JTextField();
		txtDDD.setColumns(10);
		txtDDD.setBounds(32, 260, 83, 26);
		frame.getContentPane().add(txtDDD);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(130, 244, 111, 16);
		frame.getContentPane().add(lblTelefone);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(127, 260, 188, 26);
		frame.getContentPane().add(txtFone);

		lblEndereo = new JLabel("Endereço:");
		lblEndereo.setBounds(42, 298, 111, 16);
		frame.getContentPane().add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(39, 314, 420, 26);
		frame.getContentPane().add(txtEndereco);
		connection = ConexaoMySQL.ReiniciarConexao();

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imovel imovel = new Imovel();
				imovel.setNome(txtNome.getText());
				imovel.setSobreNome(txtSobrenome.getText());
				imovel.setTelefone(txtFone.getText());
				imovel.setEndereco(txtEndereco.getText());
				imovel.setEmail(txtEmail.getText());
			
				try {
					ConexaoMySQL.inserirImovel(connection, imovel);
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com SUCESSO!!");
					limpar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERRO :" + e1.getMessage());

				}

			}
		});
		btnNewButton.setForeground(new Color(34, 139, 34));
		btnNewButton.setBounds(466, 83, 117, 47);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.setForeground(new Color(34, 139, 34));
		btnNewButton_1.setBounds(466, 133, 117, 47);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Pesquisar");
		btnNewButton_1_1.setForeground(new Color(0, 128, 0));
		btnNewButton_1_1.setBounds(466, 180, 117, 47);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Excluir");
		btnNewButton_1_2.setForeground(Color.RED);
		btnNewButton_1_2.setBounds(466, 230, 117, 47);
		frame.getContentPane().add(btnNewButton_1_2);
	}

	public void limpar() {

		txtNome.setText("");
		txtDDD.setText("");
		txtFone.setText("");
		txtEndereco.setText("");
		txtCPF.setText("");
	}
}

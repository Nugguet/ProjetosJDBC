package br.com.estacio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.estacio.DAO.ConexaoMySQL;
import br.com.estacio.entidade.Imovel;

public class formulario {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtEmail;
	private JTextField txtFone;
	private JTextField txtEndereco;
	private JTextField txtSobrenome;
	private String[] colunas = { "Nome", "Sobrenome", "Cpf"};
	private Object[][] dados = { { "", "", "" } };
	private JTable tabela = new JTable(dados, colunas);
	DefaultTableModel model;
	private Connection connection;
	private List<Imovel> listaImovel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formulario window = new formulario();
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
	public formulario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblcadastroImoveis = new JLabel("Cadastro de Im\u00F3veis");
		lblcadastroImoveis.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblcadastroImoveis.setBounds(10, 11, 233, 36);
		frame.getContentPane().add(lblcadastroImoveis);
		
		JLabel lblcadastroImoveisForm = new JLabel("Formul\u00E1rio para cadastro de im\u00F3veis");
		lblcadastroImoveisForm.setFont(new Font("Arial", Font.BOLD, 13));
		lblcadastroImoveisForm.setBounds(10, 46, 250, 36);
		frame.getContentPane().add(lblcadastroImoveisForm);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		lblNome.setBounds(10, 93, 112, 36);
		frame.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(132, 101, 112, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(132, 140, 193, 20);
		frame.getContentPane().add(txtCPF);
		
		JLabel lblcpfCnpjPass = new JLabel("CPF, CNPJ, PASS:");
		lblcpfCnpjPass.setFont(new Font("Arial", Font.BOLD, 13));
		lblcpfCnpjPass.setBounds(10, 132, 132, 36);
		frame.getContentPane().add(lblcpfCnpjPass);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(132, 179, 193, 20);
		frame.getContentPane().add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		lblEmail.setBounds(10, 171, 112, 36);
		frame.getContentPane().add(lblEmail);
		
		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(132, 218, 193, 20);
		frame.getContentPane().add(txtFone);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.BOLD, 13));
		lblTelefone.setBounds(10, 210, 112, 36);
		frame.getContentPane().add(lblTelefone);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(132, 257, 193, 20);
		frame.getContentPane().add(txtEndereco);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setFont(new Font("Arial", Font.BOLD, 13));
		lblEndereco.setBounds(10, 249, 112, 36);
		frame.getContentPane().add(lblEndereco);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(254, 101, 112, 20);
		frame.getContentPane().add(txtSobrenome);
		
		//conex„o
		
			connection = ConexaoMySQL.ReiniciarConexao();
			if (connection != null) {
				try {
					montaTabela();

				} catch (SQLException e1) {
					System.out.println("Erro Conex√£o Invalida!");
				}
			}

		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(new Color(34, 139, 34));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

				Imovel imovel = new Imovel();
				imovel.setNome(txtNome.getText());
				imovel.setSobreNome(txtSobrenome.getText());
				imovel.setTelefone(txtFone.getText());
				imovel.setEndereco(txtEndereco.getText());
				imovel.setEmail(txtEmail.getText());
				imovel.setCpf(txtCPF.getText());
				try {
					ConexaoMySQL.inserirImovel(connection, imovel);
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com SUCESSO!!");
					limpar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERRO :" + e1.getMessage());

				}
				
			}
		});
		btnConfirmar.setBounds(10, 296, 112, 36);
		frame.getContentPane().add(btnConfirmar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setForeground(new Color(34, 139, 34));
		btnAlterar.setBounds(10, 343, 112, 36);
		frame.getContentPane().add(btnAlterar);
		
		JButton btnPesquisar = new JButton("Selecionar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		/*		int linha = tabela.getSelectedRow();

				if (linha != -1) {
					txtmatricula.setText(tabela.getValueAt(linha, 0) + "");
					txtNome.setText(tabela.getValueAt(linha, 1) + "");
					txtCurso.setText(tabela.getValueAt(linha, 2) + "");

				} else {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				}

			
			*/}
		});
		btnPesquisar.setForeground(new Color(34, 139, 34));
		btnPesquisar.setBounds(131, 296, 112, 36);
		frame.getContentPane().add(btnPesquisar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(34, 139, 34));
		btnLimpar.setBounds(132, 343, 112, 36);
		frame.getContentPane().add(btnLimpar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(Color.RED);
		btnExcluir.setBounds(254, 304, 112, 64);
		frame.getContentPane().add(btnExcluir);
		
		
		
		
		
		
	}
	
	
	
	
	public void limpar() {

		txtNome.setText("");
		txtSobrenome.setText("");
		txtFone.setText("");
		txtEndereco.setText("");
		txtCPF.setText("");
		txtEmail.setText("");
	}
	
	public void montaTabela() throws SQLException {
		listaImovel = ConexaoMySQL.pesquisar(connection);

		String[] linha = new String[3];
		model = new DefaultTableModel(dados, colunas);
		for (Imovel imovel : listaImovel) {
			linha[0] = imovel.getNome() + "";
			linha[1] = imovel.getSobreNome();
			linha[2] = imovel.getCpf();
			model.addRow(linha);
		}
		model.removeRow(0);
		tabela.setModel(model);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);
		scrollPane.setBounds(37, 390, 462, 128);
		scrollPane.show(true);
		frame.getContentPane().add(scrollPane);
		
	}
}
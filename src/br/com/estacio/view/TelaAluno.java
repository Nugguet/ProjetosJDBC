package br.com.estacio.view;

import java.awt.EventQueue;
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
import br.com.estacio.entidade.Aluno;
import com.toedter.calendar.JDateChooser;

public class TelaAluno {

	private JFrame frame;
	private JTextField txtmatricula;
	private JTextField txtNome;
	private JTextField txtCurso;
	private String[] colunas = { "Matricula", "Nome", "Curso" ,"Periodo" , "Sexo", "Nascimento"};
	private Object[][] dados = { { "", "", "","","","" } };
	private JTable tabela = new JTable(dados, colunas);
	DefaultTableModel model;
	private Connection connection;
	private List<Aluno> listaAluno;
	private JTextField txtPeriodo;
	private JTextField txtSexo;
	private JTextField txtAniversario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAluno window = new TelaAluno();
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
	public TelaAluno() {
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

		JLabel lblNewLabel = new JLabel("Matricula:");
		lblNewLabel.setBounds(10, 23, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		txtmatricula = new JTextField();
		txtmatricula.setBounds(66, 18, 130, 26);
		frame.getContentPane().add(txtmatricula);
		txtmatricula.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 55, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(66, 50, 152, 26);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Curso:");
		lblNewLabel_2.setBounds(10, 87, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);

		txtCurso = new JTextField();
		txtCurso.setBounds(66, 82, 152, 26);
		frame.getContentPane().add(txtCurso);
		txtCurso.setColumns(10);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Periodo:");
		lblNewLabel_1_1.setBounds(228, 23, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtPeriodo = new JTextField();
		txtPeriodo.setColumns(10);
		txtPeriodo.setBounds(284, 18, 152, 26);
		frame.getContentPane().add(txtPeriodo);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sexo:");
		lblNewLabel_1_2.setBounds(228, 55, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		txtSexo = new JTextField();
		txtSexo.setColumns(10);
		txtSexo.setBounds(284, 50, 152, 26);
		frame.getContentPane().add(txtSexo);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nascimento:");
		lblNewLabel_1_3.setBounds(228, 87, 81, 16);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		txtAniversario = new JTextField();
		txtAniversario.setColumns(10);
		txtAniversario.setBounds(306, 82, 130, 26);
		frame.getContentPane().add(txtAniversario);
		
		
		connection = ConexaoMySQL.ReiniciarConexao();
		if (connection != null) {
			try {
				montaTabela();

			} catch (SQLException e1) {
				System.out.println("Erro Conexão Invalida!");
			}
		}

		JButton btnConfrimar = new JButton("Cadastrar");
		btnConfrimar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno a = new Aluno();
				a.setMatricula(Integer.parseInt(txtmatricula.getText()));
				a.setCurso(txtCurso.getText());
				a.setNome(txtNome.getText());
				a.setPeriodo(Integer.parseInt(txtPeriodo.getText()));
				a.setSexo(txtSexo.getText());
				a.setDataNascimento(txtAniversario.getText());
				
				try {
					ConexaoMySQL.inserir(connection, a);
					montaTabela();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro Aluno já Exluido.");

				}
				JOptionPane.showMessageDialog(null, "Aluno Incluido com Sucesso.");
				limpar();

			}
		});
		btnConfrimar.setBounds(465, 73, 117, 41);
		frame.getContentPane().add(btnConfrimar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(465, 228, 117, 43);
		frame.getContentPane().add(btnLimpar);

		JButton btnPesquisar = new JButton("Selecionar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.getSelectedRow();

				if (linha != -1) {
					txtmatricula.setText(tabela.getValueAt(linha, 0) + "");
					txtNome.setText(tabela.getValueAt(linha, 1) + "");
					txtCurso.setText(tabela.getValueAt(linha, 2) + "");
					txtPeriodo.setText(tabela.getValueAt(linha, 3) + "");
					txtSexo.setText(tabela.getValueAt(linha, 4) + "");
					txtAniversario.setText(tabela.getValueAt(linha, 5) + "");

				} else {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				}

			}
		});
		btnPesquisar.setBounds(465, 18, 117, 43);
		frame.getContentPane().add(btnPesquisar);
		
		

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtmatricula.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				} else {
					Aluno a = new Aluno();
					a.setMatricula(Integer.parseInt(txtmatricula.getText()));
					a.setCurso(txtCurso.getText());
					a.setNome(txtNome.getText());
					a.setPeriodo(Integer.parseInt(txtPeriodo.getText()));
					a.setSexo(txtSexo.getText());
					a.setDataNascimento(txtAniversario.getText());					

					boolean resultado = ConexaoMySQL.excluir(connection, a);

					try {
						if (resultado) {
							JOptionPane.showMessageDialog(null, "Registro alterado com Sucesso.");
							limpar();
							montaTabela();

						} else {
							JOptionPane.showMessageDialog(null, "ERRO registro nao alterado.");

						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "ERRO registro nao alterado.");
					}
				}

			}
		});

		btnExcluir.setBounds(465, 175, 117, 41);
		frame.getContentPane().add(btnExcluir);

		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtmatricula.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				} else {
					Aluno a = new Aluno();
					a.setMatricula(Integer.parseInt(txtmatricula.getText()));
					a.setCurso(txtCurso.getText());
					a.setNome(txtNome.getText());
					a.setPeriodo(Integer.parseInt(txtPeriodo.getText()));
					a.setSexo(txtSexo.getText());
					a.setDataNascimento(txtAniversario.getText());	

					boolean resultado = ConexaoMySQL.alterar(connection, a);

					try {
						if (resultado) {
							JOptionPane.showMessageDialog(null, "Registro alterdo com Sucesso.");
							limpar();
							montaTabela();

						} else {
							JOptionPane.showMessageDialog(null, "ERRO registro nao alterado.");

						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "ERRO registro nao alterado.");
					}

				}

			}
		});
		btnNewButton.setBounds(465, 122, 117, 41);
		frame.getContentPane().add(btnNewButton);
		
	
		
		

	}

	private void limpar() {
		txtmatricula.setText("");
		txtCurso.setText("");
		txtNome.setText("");
		txtPeriodo.setText("");
		txtSexo.setText("");
		txtAniversario.setText("");
		

	}

	public void montaTabela() throws SQLException {
		listaAluno = ConexaoMySQL.pesquisar(connection);

		String[] linha = new String[6];
		model = new DefaultTableModel(dados, colunas);
		for (Aluno aluno : listaAluno) {
			linha[0] = aluno.getMatricula() + "";
			linha[1] = aluno.getNome();
			linha[2] = aluno.getCurso();
			linha[3] = aluno.getPeriodo() + "";
			linha[4] = aluno.getSexo();
			linha[5] = aluno.getDataNascimento();
			model.addRow(linha);
		}
		model.removeRow(0);
		tabela.setModel(model);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);
		scrollPane.setBounds(16, 152, 440, 170);
		scrollPane.show(true);
		frame.getContentPane().add(scrollPane);

	}
}

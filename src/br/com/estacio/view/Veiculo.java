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

import com.toedter.calendar.JDateChooser;

import br.com.estacio.DAO.ConexaoMySQL;
import br.com.estacio.entidade.Aluguel;


public class Veiculo {

	private JFrame frame;
	private JTextField txtPlaca;
	private JTextField txtModelo;
	private String[] colunas = { "Placa", "Modelo", "Ano", "Cor","Diaria" };
	private Object[][] dados = { { "", "", "", "", "" } };
	private JTable tabela = new JTable(dados, colunas);
	DefaultTableModel model;
	private Connection connection;
	private List<Aluguel> listaAluguel;
	private JTextField txtCor;
	private JTextField txtAno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Veiculo window = new Veiculo();
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
	public Veiculo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 610, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(18, 23, 39, 16);
		frame.getContentPane().add(lblPlaca);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(79, 18, 130, 26);
		frame.getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(18, 60, 69, 16);
		frame.getContentPane().add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(79, 55, 130, 26);
		frame.getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblRetirada = new JLabel("Retirada:");
		lblRetirada.setBounds(18, 98, 61, 16);
		frame.getContentPane().add(lblRetirada);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(243, 23, 29, 16);
		frame.getContentPane().add(lblCor);
		
		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(295, 18, 130, 26);
		frame.getContentPane().add(txtCor);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(243, 60, 29, 16);
		frame.getContentPane().add(lblAno);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		txtAno.setBounds(295, 55, 130, 26);
		frame.getContentPane().add(txtAno);
		
		JDateChooser dateRetirada = new JDateChooser();
		dateRetirada.setBounds(79, 92, 130, 27);
		frame.getContentPane().add(dateRetirada);
		
		JDateChooser dateEntrega = new JDateChooser();
		dateEntrega.setBounds(295, 92, 130, 27);
		frame.getContentPane().add(dateEntrega);
		
		JLabel lblDevolucao = new JLabel("Entrega:");
		lblDevolucao.setBounds(243, 98, 61, 16);
		frame.getContentPane().add(lblDevolucao);

				
		
		connection = ConexaoMySQL.ReiniciarConexao();
		if (connection != null) {
			try {
			

			} catch (SQLException e1) {
				System.out.println("Erro Conexão Invalida!");
			}
		}

		JButton btnConfrimar = new JButton("Cadastrar");
		btnConfrimar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluguel a = new Aluguel();
				a.setAno(Integer.parseInt(txtAno.getText()));
				a.setPlaca(txtPlaca.getText());
				a.setModelo(txtModelo.getText());
				a.setCor(txtCor.getText());
				try {
					ConexaoMySQL.inserirAluguel(connection, a);
					
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
					txtPlaca.setText(tabela.getValueAt(linha, 0) + "");
					txtModelo.setText(tabela.getValueAt(linha, 1) + "");
					txtAno.setText(tabela.getValueAt(linha, 2) + "");
					txtCor.setText(tabela.getValueAt(linha, 3) + "");

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
				if (txtPlaca.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				} else {
					Aluguel a = new Aluguel();
					a.setAno(Integer.parseInt(txtAno.getText()));
					a.setPlaca(txtPlaca.getText());
					a.setModelo(txtModelo.getText());
					a.setCor(txtCor.getText());

					boolean resultado = ConexaoMySQL.excluir(connection, a);

					try {
						if (resultado) {
							JOptionPane.showMessageDialog(null, "Registro alterado com Sucesso.");
							limpar();
							

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
				if (txtPlaca.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
				} else {
					Aluguel a = new Aluguel();
					a.setAno(Integer.parseInt(txtAno.getText()));
					a.setPlaca(txtPlaca.getText());
					a.setModelo(txtModelo.getText());
					a.setCor(txtCor.getText());


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
		txtPlaca.setText("");
		txtAno.setText("");
		txtModelo.setText("");

	}


}

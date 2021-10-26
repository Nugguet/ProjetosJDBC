package br.com.estacio.entidade;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class coneccao {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coneccao window = new coneccao();
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
	public coneccao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(127, 58, 147, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnNome = new JTextPane();
		txtpnNome.setText("NOME:");
		txtpnNome.setBounds(10, 58, 40, 20);
		frame.getContentPane().add(txtpnNome);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 89, 147, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane txtpnCurso = new JTextPane();
		txtpnCurso.setText("Curso");
		txtpnCurso.setBounds(10, 89, 40, 20);
		frame.getContentPane().add(txtpnCurso);
		
		JTextPane txtpnPeriodo = new JTextPane();
		txtpnPeriodo.setText("periodo");
		txtpnPeriodo.setBounds(10, 120, 40, 20);
		frame.getContentPane().add(txtpnPeriodo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(127, 120, 147, 20);
		frame.getContentPane().add(textField_2);
		
		JTextPane txtpnSexo = new JTextPane();
		txtpnSexo.setText("Sexo:");
		txtpnSexo.setBounds(10, 151, 40, 20);
		frame.getContentPane().add(txtpnSexo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(127, 151, 95, 20);
		frame.getContentPane().add(textField_3);
		
		JTextPane txtpnCadastro = new JTextPane();
		txtpnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnCadastro.setText("Cadastro");
		txtpnCadastro.setBounds(165, 11, 89, 36);
		frame.getContentPane().add(txtpnCadastro);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(40, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(139, 227, 89, 23);
		frame.getContentPane().add(btnDeletar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(238, 227, 89, 23);
		frame.getContentPane().add(btnAtualizar);
		
		JTextPane txtpnDataNascimento = new JTextPane();
		txtpnDataNascimento.setText("Data ");
		txtpnDataNascimento.setBounds(10, 179, 81, 20);
		frame.getContentPane().add(txtpnDataNascimento);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(127, 179, 95, 20);
		frame.getContentPane().add(textField_4);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int linha = tabela.getSelectedRow();

                if (linha != -1) {
                    txtX.setText(tabela.getValueAt(linha, 0) + "");
                    txtX.setText(tabela.getValueAt(linha, 1) + "");
                    txtX.setText(tabela.getValueAt(linha, 2) + "");
                    txtX.setText(tabela.getValueAt(linha, 3) + "");
                    txtX.setText(tabela.getValueAt(linha, 4) + "");

                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro Selecionado.");
                }

            }
        });
				
			}
		});
		btnPesquisar.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnPesquisar);
	}

	public void setCurso(String string) {
		// TODO Auto-generated method stub
		
	}
}

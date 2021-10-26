
package br.com.estacio.DAO;

//Classes necessárias para uso de Banco de dados //

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.estacio.entidade.Aluguel;
import br.com.estacio.entidade.Aluno;
import br.com.estacio.entidade.Imovel;

//Início da classe de conexão//

public class ConexaoMySQL {

	public static String status = "Não conectou...";

//Método Construtor da Classe//

	public ConexaoMySQL() {

	}

//Método de Conexão//

	public static java.sql.Connection getConexaoMySQL() {

		Connection connection = null; // atributo do tipo Connection

		try {

// Carregando o JDBC Driver padrão

			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

// Configurando a nossa conexão com um banco de dados//

			String serverName = "localhost"; // caminho do servidor do BD

			String mydatabase = "aula"; // nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root"; // nome de um usuário de seu BD

			String password = ""; // sua senha de acesso

			connection = DriverManager.getConnection(url, username, password);

			// Testa sua conexão//

			if (connection != null) {

				status = ("STATUS--->Conectado com sucesso!");
				System.out.println("STATUS--->Conectado com sucesso!\"");

			} else {

				status = ("STATUS--->Não foi possivel realizar conexão");

			}

			return connection;

		} catch (ClassNotFoundException e) { // Driver não encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

//Não conseguindo se conectar ao banco

			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;

		}

	}

	// Método que retorna o status da sua conexão//

	public static String statusConection() {

		return status;

	}

	// Método que fecha sua conexão//

	public static boolean FecharConexao() {

		try {

			ConexaoMySQL.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	// Método que reinicia sua conexão//

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return ConexaoMySQL.getConexaoMySQL();

	}

	public static void main(String[] args) {
		Connection connection = ReiniciarConexao();
		try {
			if (connection != null) {
				Aluno a = new Aluno();
				a.setCurso("ADS Alterado");
				a.setMatricula(1);
				a.setNome("Nelson Alterado");

				// inserir(connection, a);
				// pesquisar(connection);

				// alterar(connection, a);

				excluir(connection, a);

			}

			connection.close();
		} catch (Exception e) {
			System.out.println("Erro  de Conexão!");
		}

	}

	public static void inserir(Connection connection, Aluno aluno) throws SQLException {
		Statement stmt = connection.createStatement();

		int insert = stmt.executeUpdate("insert into aluno (matricula, nome, curso, periodo, sexo, dataNascimento) VALUES(" + aluno.getMatricula()
				+ ", " + aluno.getNome() + "," + aluno.getCurso() +  "," + aluno.getPeriodo() +  "," + aluno.getSexo() +  "," + aluno.getDataNascimento() + ") ");
		System.out.println("Insert: " + insert);

	}
	
	
	public static void inserirImovel(Connection connection, Imovel imovel) throws SQLException {
		Statement stmt = connection.createStatement();

		 int insert = stmt.executeUpdate("insert into imovel (nome, sobrenome, cpf, email, telefone, endereco)"
		 		
				 + " VALUES (" + imovel.getNome() + "," + imovel.getSobreNome() + "," + imovel.getCpf() + "," + imovel.getEmail()  + "," + imovel.getTelefone() + "," + imovel.getEndereco() + ") ");
		 System.out.println("Insert: " + insert);
		 
	

	}
	
	
	public static void inserirAluguel(Connection connection, Aluguel veiculo) throws SQLException {
		Statement stmt = connection.createStatement();

		 int insert = stmt.executeUpdate("insert into imovel (placa, modelo, ano, cor)"
		 		
				 + " VALUES (" + veiculo.getPlaca() + "," + veiculo.getModelo()  + "," + veiculo.getAno() + "," + veiculo.getCor() + ") ");
		 System.out.println("Insert: " + insert);
		 
	

	}

	
	
	public static List<Aluno> pesquisar(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery("select * from aluno ");
		List<Aluno> listaAluno = new ArrayList<Aluno>();

		while (res.next()) {
			Aluno a = new Aluno();
			a.setNome(res.getString("nome"));
			a.setCurso(res.getString("curso"));
			a.setMatricula(res.getInt("matricula"));
			a.setPeriodo(res.getInt("Periodo"));
			a.setSexo(res.getString("Sexo"));
			a.setDataNascimento(res.getString("dataNascimento"));
			listaAluno.add(a);
		}

		res.close();
		stmt.close();
		return listaAluno;
	}
	
	
	/*/
	
	public static List<Imovel> pesquisar(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery("select * from aluno ");
		List<Imovel> listaImovel = new ArrayList<Imovel>();

		while (res.next()) {
			Imovel a = new Imovel();
			a.setNome(res.getString("nome"));
			a.setSobreNome(res.getString("sobrenome"));
			a.setCpf(res.getString("cpf"));
			listaImovel.add(a);
		}

		res.close();
		stmt.close();
		return listaImovel;
	}

/*/
	

	public static boolean excluir(Connection connection, Aluno a) {
		try {
			// Create a statement
			Statement stmt = connection.createStatement();

			// Prepare a statement to insert a record
			String sql = "DELETE FROM aluno WHERE matricula = " + a.getMatricula();

			// Execute the delete statement
			int deleteCount = stmt.executeUpdate(sql);

			if (deleteCount > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Erro na excluisão do Registro!");
			return false;
		}

	}

	public static boolean alterar(Connection connection, Aluno a) {
		try {
			// Create a statement
			String sql = "UPDATE aluno SET matricula = " + a.getMatricula() + ", nome = " + " ' " + a.getNome() + " ' "
					+ ", curso = " + " ' " + a.getCurso() + " ' " + ", periodo = " + " ' " + a.getPeriodo() + " ' " + ", sexo = " + " ' " + a.getSexo() + " ' " +  ", dataNascimento = " + " ' " + a.getDataNascimento() + " ' " + " WHERE matricula = " + a.getMatricula();

			// Execute the insert statement
			Statement stmt = connection.createStatement();
			int updateCount = stmt.executeUpdate(sql);

			if (updateCount > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Erro na excluisão do Registro!");
			return false;
		}

	}

}
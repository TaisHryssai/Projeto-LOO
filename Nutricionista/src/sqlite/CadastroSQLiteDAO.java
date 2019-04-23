package sqlite;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;

public class CadastroSQLiteDAO extends SQLiteBaseCadastro {

	public CadastroSQLiteDAO() {
		open();
		try {
			PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS cadastro ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome TEXT, " + "cpf TEXT,  " + "nascimento DATE, "
					+ "endereco TEXT, " + "numero INTEGER, " + "bairro TEXT, " + "cidade TEXT, " + "telefone TEXT, "
					+ "celular TEXT, " + "email TEXT, " + "peso FLOAT, " + "altura FLOAT, " + "cintura FLOAT, "
					+ "busto FLOAT, " + "quadril FLOAT, " + "calculoImc FLOAT);");

			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void create(Paciente pacientes) {
		open();
		try {
			PreparedStatement stm = conn.prepareStatement("INSERT INTO cadastro VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			stm.setString(2, pacientes.getNome());
			stm.setString(3, pacientes.getCpf());
			stm.setDate(4, new Date(0));
			stm.setString(5, pacientes.getEndereco());
			stm.setInt(6, pacientes.getNumero());
			stm.setString(7, pacientes.getBairro());
			stm.setString(8, pacientes.getCidade());
			stm.setString(9, pacientes.getTelefone()); 
			stm.setString(10, pacientes.getCelular());
			stm.setString(11, pacientes.getEmail());
			stm.setFloat(12, pacientes.getPeso());
			stm.setFloat(13, pacientes.getAltura());
			stm.setFloat(14, pacientes.getCintura());
			stm.setFloat(15, pacientes.getBusto());
			stm.setFloat(16, pacientes.getQuadril());
			stm.setFloat(17, pacientes.getCalculoImc());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void update(Paciente pacientes) {
		conn = open();
		try {
			PreparedStatement stm = conn.prepareStatement("UPDATE cadastro SET " + "nome = ?, " + "cpf = ?,"
					+ "nascimento = ?, " + "endereco = ?," + "numero = ? , " + "bairro = ?," + "cidade = ?,"
					+ "telefone = ?," + "celular = ?," + "email = ?," + "peso = ?," + "altura = ?," + "cintura = ?,"
					+ "busto = ?," + "quadril = ?," + "calculoImc = ? " + "WHERE id = ? ;");
			stm.setString(1, pacientes.getNome());
			stm.setString(2, pacientes.getCpf());
			stm.setDate(3, new Date(0));
			stm.setString(4, pacientes.getEndereco());
			stm.setInt(5, pacientes.getNumero());
			stm.setString(6, pacientes.getBairro());
			stm.setString(7, pacientes.getCidade());
			stm.setString(8, pacientes.getTelefone());
			stm.setString(9, pacientes.getCelular());
			stm.setString(10, pacientes.getEmail());
			stm.setFloat(11, pacientes.getPeso());
			stm.setFloat(12, pacientes.getAltura());
			stm.setFloat(13, pacientes.getCintura());
			stm.setFloat(14, pacientes.getBusto());
			stm.setFloat(15, pacientes.getQuadril());
			stm.setFloat(16, pacientes.getCalculoImc());
			stm.setInt(17, pacientes.getId());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}



	public void delete(Paciente pacientes) {
		conn = open();

		try {
			PreparedStatement stm = conn.prepareStatement("DELETE FROM cadastro WHERE id = ?;");

			stm.setInt(1, pacientes.getId());

			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public Paciente find(int busca) {
		Paciente pacientes = null;
		conn = open();

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM cadastro WHERE id = ?;");

			stm.setInt(1, busca);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				Paciente p = new Paciente(rs.getInt(1), // id
						rs.getString(2), // nome
						rs.getString(3), // cpf
						rs.getDate(4), // nascimento
						rs.getString(5), // endereco
						rs.getInt(6), // numero
						rs.getString(7), // bairro
						rs.getString(8), // cidade
						rs.getString(9), // telefone
						rs.getString(10), // celular
						rs.getString(11), // email
						rs.getFloat(12), // peso
						rs.getFloat(13), // altura
						rs.getFloat(14), // cintura
						rs.getFloat(15), // busto
						rs.getFloat(16), // quadril
						rs.getFloat(17));// calculoImc
				pacientes = p;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return pacientes;

	}

		public List<Paciente> all() {
		ArrayList<Paciente> result = new ArrayList<>();

		open();
		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM cadastro ORDER BY id ASC;");
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Paciente p = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getFloat(12), rs.getFloat(13), rs.getFloat(14),
						rs.getFloat(15), rs.getFloat(16), rs.getFloat(17));
				result.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;

	}

}

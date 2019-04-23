package model;

import java.sql.Date;
import java.util.List;

import sqlite.CadastroSQLiteDAO;



public class Paciente {
	private Integer id;
	private String nome;
	private String cpf, telefone, celular;
	private int numero;
	private String endereco, bairro, cidade, email;
	private float peso, altura, cintura, busto, quadril, calculoImc;
	private Date nascimento;

	public Paciente(String nome, String cpf, Date nascimento, String endereco, int numero, String bairro, String cidade,
			String telefone, String celular, String email, float peso, float altura, float cintura, float busto,
			float quadril, float calculoImc) {

		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.peso = peso;
		this.altura = altura;
		this.cintura = cintura;
		this.busto = busto;
		this.quadril = quadril;
		this.calculoImc = calculoImc;

	}

	public Paciente(int id, String nome, String cpf, Date nascimento, String endereco, int numero, String bairro,
			String cidade, String telefone, String celular, String email, float peso, float altura, float cintura,
			float busto, float quadril, float calculoImc) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.peso = peso;
		this.altura = altura;
		this.cintura = cintura;
		this.busto = busto;
		this.quadril = quadril;
		this.calculoImc = calculoImc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getCintura() {
		return cintura;
	}

	public void setCintura(float cintura) {
		this.cintura = cintura;
	}

	public float getBusto() {
		return busto;
	}

	public void setBusto(float busto) {
		this.busto = busto;
	}

	public float getQuadril() {
		return quadril;
	}

	public void setQuadril(float quadril) {
		this.quadril = quadril;
	}

	public float getCalculoImc() {
		return calculoImc;
	}

	public void setCalculoImc(float calculoImc) {
		this.calculoImc = calculoImc;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	@Override
	public String toString() {
		return "Pacientes [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nascimento =" + nascimento + "numero="
				+ numero + "," + ", telefone=" + telefone + ", celular=" + celular + ", endereco=" + endereco
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", email=" + email + ", peso=" + peso + ", altura="
				+ altura + ", cintura=" + cintura + ", busto=" + busto + ", quadril=" + quadril + ", calculoImc="
				+ calculoImc + "]";
	}

	private static CadastroSQLiteDAO dao = new CadastroSQLiteDAO();

	public void save() {
		if (id != null && dao.find(id) != null) {
			dao.update(this);
		} else
			dao.create(this);
	}

	public void delete() {
		if (dao.find(id) != null) {
			dao.delete(this);
		}
	}

	public static List<Paciente> all() {
		return dao.all();
	}

	public static Paciente find(int pk) {
		return dao.find(pk);
	}

}

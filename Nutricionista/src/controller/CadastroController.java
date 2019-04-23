package controller;

import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.management.relation.RelationServiceNotRegisteredException;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mascara.TextFieldFormatter;
import model.Paciente;
import sqlite.SQLiteBaseCadastro;


public class CadastroController {

	private Paciente paciente;

	// @FXML
	// private TextField tfNascimento;

	@FXML
	private TextField tfEndereco;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfCintura;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfBusto;

	@FXML
	private TextField tfCPF;

	@FXML
	private Label lbIMC;

	@FXML
	private Button btIMC;

	@FXML
	private TextField tfCidade;

	@FXML
	private TextField tfBairro;

	@FXML
	private TextField tfQuadril;

	@FXML
	private TextField tfN;

	@FXML
	private TextField tfCelular;

	@FXML
	private TextField tfNome;

	@FXML
	private javafx.scene.control.Button fecharTela;

	@FXML
	private TextField tfAltura;
	@FXML
	private TextField tfPeso;

	@FXML
	private TextField tfIMC;

	@FXML
	private DatePicker datePicker;

	/**
	 * Convert LocalDate to SQL Date
	 * 
	 * @param ld
	 * @return
	 */
	public Date toSQLDate(LocalDate ld) {
		return java.sql.Date.valueOf(ld);
	}
	
	
	public LocalDate toLocalDate(java.sql.Date lb) {
		return lb.toLocalDate();

	}

	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.onChangeScreen() {

			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if (newScreen.equals("cadastrar")) {

					if (userData != null) {
						paciente = (Paciente) userData;

						tfNome.setText(paciente.getNome());
						tfCPF.setText(paciente.getCpf());
						datePicker.setValue(toLocalDate(paciente.getNascimento()));
						tfEndereco.setText(paciente.getEndereco());
						tfN.setText(String.valueOf(paciente.getNumero()));
						tfBairro.setText(paciente.getBairro());
						tfCidade.setText(paciente.getCidade());
						tfTelefone.setText(paciente.getTelefone());
						tfCelular.setText(paciente.getCelular());
						tfEmail.setText(paciente.getEmail());
						tfPeso.setText(String.valueOf(paciente.getPeso()));
						tfAltura.setText(String.valueOf(paciente.getAltura()));
						tfCintura.setText(String.valueOf(paciente.getCintura()));
						tfBusto.setText(String.valueOf(paciente.getBusto()));
						tfQuadril.setText(String.valueOf(paciente.getQuadril()));
						tfIMC.setText(String.valueOf(paciente.getCalculoImc()));
					} else {
						paciente = null;

						tfNome.setText("");
						tfCPF.setText("");
						datePicker.setValue(LocalDate.now());
						tfEndereco.setText("");
						tfN.setText("");
						tfBairro.setText("");
						tfCidade.setText("");
						tfTelefone.setText("");
						tfCelular.setText("");
						tfEmail.setText("");
						tfPeso.setText("");
						tfAltura.setText("");
						tfCintura.setText("");
						tfBusto.setText("");
						tfQuadril.setText("");
						tfIMC.setText("");
					}
				}

			}
		});
	}

	@FXML
	protected void btCadastroAction(ActionEvent e) {
		try {
			if (tfNome.getText().isEmpty())
				throw new RuntimeException("O atributo Nome nao pode estar vazio");
			if (tfEndereco.getText().isEmpty())
				throw new RuntimeException("O atributo Endereço não pode estar vazio");
			if (tfBairro.getText().isEmpty())
				throw new RuntimeException("O atributo Bairro nao pode estar vazio");
			if (tfCidade.getText().isEmpty())
				throw new RuntimeException("O atributo Cidade nao pode estar vazio");
			if (tfEmail.getText().isEmpty())
				throw new RuntimeException("O atributo Email nao pode estar vazio");

			if (paciente != null) {
				paciente.setNome(tfNome.getText());
				paciente.setCpf(tfCPF.getText());

				paciente.setNascimento(toSQLDate(datePicker.getValue()));

				paciente.setEndereco(tfEndereco.getText());
				paciente.setNumero(Integer.parseInt(tfN.getText()));
				paciente.setBairro(tfBairro.getText());
				paciente.setCidade(tfCidade.getText());
				paciente.setTelefone(tfTelefone.getText());
				paciente.setCelular(tfCelular.getText());
				paciente.setEmail(tfEmail.getText());
				paciente.setPeso(Float.parseFloat(tfPeso.getText()));
				paciente.setAltura(Float.parseFloat(tfAltura.getText()));
				paciente.setCintura(Float.parseFloat(tfCintura.getText()));
				paciente.setBusto(Float.parseFloat(tfBusto.getText()));
				paciente.setQuadril(Float.parseFloat(tfQuadril.getText()));
				paciente.setCalculoImc(Float.parseFloat(tfIMC.getText()));

				paciente.save();
			} else {
				Paciente p = new Paciente(tfNome.getText(), tfCPF.getText(), toSQLDate(datePicker.getValue()), // date
						tfEndereco.getText(), Integer.parseInt(tfN.getText()), tfBairro.getText(), tfCidade.getText(),
						tfTelefone.getText(), tfCelular.getText(), tfEmail.getText(),
						Float.parseFloat(tfPeso.getText()), Float.parseFloat(tfAltura.getText()),
						Float.parseFloat(tfCintura.getText()), Float.parseFloat(tfBusto.getText()),
						Float.parseFloat(tfQuadril.getText()), Float.parseFloat(tfIMC.getText()));
				p.save();
			}
			Main.changeScreen("pacientes");
		} catch (NumberFormatException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("erro");
			alert.setHeaderText("erro ao criar Cadastro");
			alert.setContentText("os atributos devem ser valores numericos");
			alert.showAndWait();

		} catch (RuntimeException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("erro");
			alert.setHeaderText("erro ao criar Cadastro");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}

	@FXML
	private void fecharTelaAction(ActionEvent e) {
		Stage stage = (Stage) fecharTela.getScene().getWindow(); 
		stage.close(); 
	}

	@FXML
	protected void btCancelar(ActionEvent e) {
		Main.changeScreen("inicio");
	}

	@FXML
	private void calculoIMC(ActionEvent e) {

		float altura = Float.parseFloat(tfAltura.getText().replaceAll(",", "."));
		float peso = Float.parseFloat(tfPeso.getText().replaceAll(",", "."));
		float imc = peso / (altura * altura);


		tfIMC.setText(String.valueOf(new DecimalFormat("#0.00").format(imc).replaceAll(",", ".")));

	}

	@FXML
	private void ValidaCelular() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfCelular);
		tff.formatter();

	}

	@FXML
	private void ValidaTelefoneFixo() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfTelefone);
		tff.formatter();

	}

	@FXML
	private void ValidaCpf() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfCPF);
		tff.formatter();
	}
}

package controller;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Paciente;

public class ListaController {

	@FXML
	protected ListView<Paciente> lvPacientes;

	@FXML
	protected void initialize() {
		Main.addOnChangeScreenListener(new Main.onChangeScreen() {

			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if (newScreen.equals("pacientes"))
					updateList();
			}
		});

		updateList();
	}

	@FXML
	protected void btCancelar(ActionEvent e) {
		Main.changeScreen("inicio");
	}

	@FXML
	private javafx.scene.control.Button close;

	@FXML
	private void fecharTelaAction(ActionEvent e) {
		Stage stage = (Stage) close.getScene().getWindow(); 
		stage.close(); 
	}

	@FXML
	protected void btCadastroPaciente(ActionEvent e) {
		Main.changeScreen("cadastrar");
	}

	@FXML
	protected void btEditarAction(ActionEvent e) {
		ObservableList<Paciente> ol = lvPacientes.getSelectionModel().getSelectedItems();

		if (!ol.isEmpty()) {
			Paciente p = ol.get(0);
			Main.changeScreen("cadastrar", p);
		}
	}

	@FXML
	protected void btDeleteAction(ActionEvent e) {
		ObservableList<Paciente> ol = lvPacientes.getSelectionModel().getSelectedItems();

		if (!ol.isEmpty()) {
			Paciente p = ol.get(0);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("confirmação");
			alert.setHeaderText("Deseja realmente remover o paciente?");
			alert.setContentText(p.toString());
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				p.delete();
				updateList();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("informação");
			alert.setHeaderText("Nenhum Paciente selecionado");
			alert.setContentText("Selecione algum paciente para remover.");
			alert.showAndWait();
		}
	}

	private void updateList() {
		lvPacientes.getItems().clear();
		for (Paciente p : Paciente.all()) {
			lvPacientes.getItems().add(p);
		}
	}

}

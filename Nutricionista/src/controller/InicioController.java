package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InicioController {
	@FXML
	protected void btCadastro(ActionEvent e) {
		Main.changeScreen("cadastrar");
	}
	
	@FXML
	protected void btLista(ActionEvent e) {
		Main.changeScreen("pacientes");
	}
	
	@FXML
	private javafx.scene.control.Button fechar;
	
	@FXML
	private void fecharTelaAction(ActionEvent e) {
		Stage stage = (Stage) fechar.getScene().getWindow();
		stage.close(); 
	}
}

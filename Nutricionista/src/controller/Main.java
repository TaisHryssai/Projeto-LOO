package controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Paciente;
import sqlite.CadastroSQLiteDAO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class Main extends Application {
	private static Stage stage;

	private static Scene cadastroScene;

	private static Scene inicioScene;

	private static Scene listaScene;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		primaryStage.setTitle("Cadastro ");

		Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("../view/CadastroPacientes.fxml"));
		cadastroScene = new Scene(fxmlCadastro, 890, 850);

		Parent fxmlLista = FXMLLoader.load(getClass().getResource("../view/ListaPacientes.fxml"));
		listaScene = new Scene(fxmlLista, 890, 850);

		Parent fxmlInicio = FXMLLoader.load(getClass().getResource("../view/InicioTela.fxml"));
		inicioScene = new Scene(fxmlInicio, 650, 310);

		primaryStage.setScene(inicioScene);

		primaryStage.getIcons().add(new Image("file:src/resources/img/nutricionista.jpg"));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void changeScreen(String scr, Object userData) {
		switch (scr) {
		case "cadastrar":
			stage.setScene(cadastroScene);
			notifyAllListeners("cadastrar", userData);
			break;
		case "pacientes":
			stage.setScene(listaScene);
			notifyAllListeners("pacientes", userData);
			break;
		case "inicio":
			stage.setScene(inicioScene);
			notifyAllListeners("inicio", userData);
		}
	}

	public static void changeScreen(String scr) {
		changeScreen(scr, null);
	}

	private static ArrayList<onChangeScreen> listeners = new ArrayList<>();

	public static interface onChangeScreen {
		void onScreenChanged(String newScreen, Object userData);
	}

	public static void addOnChangeScreenListener(onChangeScreen newListener) {
		listeners.add(newListener);
	}

	private static void notifyAllListeners(String newScreen, Object userData) {
		for (onChangeScreen l : listeners) {
			l.onScreenChanged(newScreen, userData);
		}
	}

}

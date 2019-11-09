package br.ufrn.imd;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetectorDePessoas extends Application {

	private static Stage stage;
	
	private static Scene main_scene;
	private static Scene simulation_settings_scene;
	private static Scene input_settings_scene;
	private static Scene about_scene;
	
	@Override
	public void start(Stage primary_stage) throws IOException {
		stage = primary_stage;
		stage.setTitle("Detector de Pessoas");
		
		Parent fxml_main = FXMLLoader.load(getClass().getResource("visao/main_screen.fxml"));
		main_scene = new Scene(fxml_main, 450, 400);
		
		Parent fxml_simulation_settings = FXMLLoader.load(getClass().getResource("visao/simulation_settings_screen.fxml"));
		simulation_settings_scene = new Scene(fxml_simulation_settings, 450, 400);
		
		Parent fxml_input_settings = FXMLLoader.load(getClass().getResource("visao/input_settings_screen.fxml"));
		input_settings_scene = new Scene(fxml_input_settings, 450, 400);
		
		Parent fxml_about = FXMLLoader.load(getClass().getResource("visao/about_screen.fxml"));
		about_scene = new Scene(fxml_about, 450, 400);
		
		stage.setScene(main_scene);
		stage.show();
	}

	public static void changeScreen(String src, Object data) {
		switch(src) {
			case "main":
				stage.setScene(main_scene);
				notifyAllListeners("main", data);
				break;
			case "simulation_settings":
				stage.setScene(simulation_settings_scene);
				notifyAllListeners("simulation_settings", data);
				break;
			case "input_settings":
				stage.setScene(input_settings_scene);
				notifyAllListeners("input_settings", data);
				break;
			case "about":
				stage.setScene(about_scene);
				notifyAllListeners("about", data);
				break;
		}
	}

	public static void changeScreen(String src) {
		changeScreen(src, null);
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
	
	public static interface OnChangeScreen{
		void onScreenChanged(String newScreen, Object data);
	}
	
	public static void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}
	
	private static void notifyAllListeners(String newScreen, Object data) {
		for (OnChangeScreen l : listeners) {
			l.onScreenChanged(newScreen, data);
		}
	}
}